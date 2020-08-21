#私有化部署步骤
1.  准备好hostname和hosts
2.  安装k8s
3.  安装nfs
4.  安装helm
5.  部署业务
6.  安装nginx负载均衡
7.  (如有需求)安装tomcat校园平台

##安装K8s
```bash
#安装yum-utils
yum -y install yum-utils

#安装docker
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum makecache

yum update && yum install \
  containerd.io-1.2.10 \
  docker-ce-19.03.4 \
  docker-ce-cli-19.03.4
mkdir /etc/docker

cat > /etc/docker/daemon.json <<EOF
{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "100m"
  },
  "storage-driver": "overlay2",
  "storage-opts": [
    "overlay2.override_kernel_check=true"
  ]
}
EOF

mkdir -p /etc/systemd/system/docker.service.d

systemctl daemon-reload
systemctl restart docker
systemctl enable docker

systemctl stop firewalld
systemctl disable firewalld

ip link
ifconfig -a
cat /sys/class/dmi/id/product_uuid

# Set SELinux in permissive mode (effectively disabling it)
setenforce 0
sed -i 's/^SELINUX=enforcing$/SELINUX=permissive/' /etc/selinux/config

modprobe br_netfilter
lsmod | grep br_netfilter

cat <<EOF >  /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sysctl --system

swapoff -a

#安装kubernetes
cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=http://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=0
repo_gpgcheck=0
gpgkey=http://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg
        http://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
EOF
yum makecache

yum install -y kubelet-1.17.0 kubeadm-1.17.0 kubectl-1.17.0 --disableexcludes=kubernetes

systemctl enable --now kubelet

#拉取运行集群所需要的镜像，注意更改最新的版本号
images=("kube-apiserver:v1.17.5" "kube-controller-manager:v1.17.5" "kube-scheduler:v1.17.5" "kube-proxy:v1.17.5" "pause:3.1" "etcd:3.4.3-0" "coredns:1.6.5")
for i in ${images[*]}; do
    docker pull registry.cn-hangzhou.aliyuncs.com/google_containers/$i 
    docker tag registry.cn-hangzhou.aliyuncs.com/google_containers/$i k8s.gcr.io/$i 
    docker rmi registry.cn-hangzhou.aliyuncs.com/google_containers/$i 
done

#初始化集群，使用DNS域名初始化，使用IP初始化使用--apiserver-advertise-address=192.168.199.237
kubeadm init --pod-network-cidr=10.244.0.0/16 --control-plane-endpoint=node01 --v=5

#将master设置为worker
kubectl taint nodes --all node-role.kubernetes.io/master-

#设置kubectl
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
export KUBECONFIG=/etc/kubernetes/admin.conf

#安装网络
kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml

#安装dashboard
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0-beta1/aio/deploy/recommended.yaml

#导出dashboard的p12证书
grep 'client-certificate-data' /etc/kubernetes/admin.conf | head -n 1 | awk '{print $2}' | base64 -d >> kubecfg.crt
grep 'client-key-data' /etc/kubernetes/admin.conf | head -n 1 | awk '{print $2}' | base64 -d >> kubecfg.key
openssl pkcs12 -export -clcerts -inkey kubecfg.key -in kubecfg.crt -out kubecfg.p12 -name "kubernetes-client"

#获取dashboard的token
kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep kubernetes-dashboard-token | awk '{print $1}')
```

如果有slave，复制生成的slave添加语句并加入集群
```bash
kubeadm join 192.168.199.237:6443 --token k62rvt.c14ofwoi3jor6ycj \
    --discovery-token-ca-cert-hash sha256:1db66e2d9a3eb91098d0fb8276e00694a8636177fdc977ab2048d17f7f4cc0ad
```

如果没有复制语句，则按如下规则生成
```bash
kubeadm join --token <token> <master-ip>:<master-port> --discovery-token-ca-cert-hash sha256:<hash>
```
ip为master的ip或域名，port一般为6443

token使用命令kubeadm token list查看，如果已经过期使用kubeadm token create创建

--discovery-token-ca-cert-hash使用如下命令查看：
```bash
openssl x509 -pubkey -in /etc/kubernetes/pki/ca.crt | openssl rsa -pubin -outform der 2>/dev/null | openssl dgst -sha256 -hex | sed 's/^.* //'
```


##安装NFS的方法
```bash
# install nfs utils
yum -y install nfs-utils rpcbind
systemctl enable rpcbind
systemctl enable nfs-server
systemctl enable nfs-lock
systemctl enable nfs-idmap
systemctl start rpcbind
systemctl start nfs-server
systemctl start nfs-lock
systemctl start nfs-idmap
echo "/home/nfs    192.168.199.0/24(rw,sync,no_root_squash,no_all_squash)" >> /etc/exports

#mkdir
mkdir /home/nfs
mkdir /home/nfs/rabbit
mkdir /home/nfs/redis
mkdir /home/nfs/mysql
mkdir /home/nfs/redis/0
mkdir /home/nfs/redis/1
mkdir /home/nfs/redis/2
mkdir /home/nfs/rabbit/0
mkdir /home/nfs/rabbit/1
mkdir /home/nfs/rabbit/2
chmod 777 /home/nfs/rabbit
chmod 777 /home/nfs/redis
chmod 777 /home/nfs/mysql
chown -R polkitd:nfsnobody /home/nfs/mysql
chown -R 100:101 /home/nfs/rabbit
chown -R 1000:root /home/nfs/redis

# restart to load
systemctl restart nfs-server

# show mount to check
showmount -e localhost
```

##安装Helm
```bash
wget https://get.helm.sh/helm-v3.1.1-linux-amd64.tar.gz
tar -zxf helm-v3.1.1-linux-amd64.tar.gz
cd linux-amd64
cp helm /usr/bin
```

##部署业务
上传helm文件夹
cd /path/to/helm
执行
```bash
kubectl apply -f namespace.yaml
helm install pv pv

cd shell
./install_redis.sh
./install_rabbit.sh
./install_emqx.sh
./install_paas.sh
```

##安装外层Nginx负载均衡
nginx.conf详见nginx.conf

1.安装nginx
```bash
#install nginx
rpm -Uvh http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm
yum install -y nginx
```
2.上传证书
上传.docs/k8s/SystemTest/nginx/backup/cert 至 /etc/nginx下

3.修改nginx.conf

##端口列表
至此，外部可访问的基础软件端口如下：
* rabbitmq-epmd 32671
* rabbitmq-amqp 32672
* rabbitmq-dashboard 32673
* redis 30678
* redis-sentinel 30679
* mysql 31306

##(可选)本地安装tomcat
###安装java
上传jdk-7u71-linux-x64.rpm
```bash
rpm -ivh jdk-7u71-linux-x64.rpm
```
获取JCE无限制权限策略文件US_export_policy.jar\local_policy.jar，上传至/usr/java/jdk1.7.0_71/jre/lib/security/

###安装tomcat
```bash
wget https://mirror.bit.edu.cn/apache/tomcat/tomcat-7/v7.0.103/bin/apache-tomcat-7.0.103.tar.gz
tar -zxf apache-tomcat-7.0.103.tar.gz
mv apache-tomcat-7.0.103 /home/
mv /home/apache-tomcat-7.0.103/ /home/tomcat
```
###添加tomcat参数
vi /home/tomcat/bin/catalina.sh

添加一行
```
JAVA_OPTS="-server -Xms1024m -Xmx1024m -XX:PermSize=512M -XX:MaxNewSize=256m -XX:MaxPermSize=128m -Djava.awt.headless=true "
```

###上传war包
上传校园平台war包至/home/tomcat/webapps/

###导入表结构

###启动
```bash
cd /home/tomcat/bin
./startup.sh
```
如果出现警告
```plantuml
Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
```
则mysql连接地址最后添加
```
useSSL=false
```
e.g.
```
cd /home/tomcat/webapps/siglifeUsams/WEB-INF/classes
vi database.properties
修改连接地址为
mysql://127.0.0.1:31306/usams2?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false
```

