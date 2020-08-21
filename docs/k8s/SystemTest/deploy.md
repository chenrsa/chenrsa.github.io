### 安装docker

1.安装 yum-config-manager 

```shell
yum -y install yum-utils 
```

2.加载docker镜像地址

```shell
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum makecache
```

3.安装

```bash
## Install Docker CE.
yum update && yum install \
  containerd.io-1.2.10 \
  docker-ce-19.03.4 \
  docker-ce-cli-19.03.4

## Create /etc/docker directory.
mkdir /etc/docker

# Setup daemon.
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

# Restart Docker
systemctl daemon-reload
systemctl restart docker
```

### 环境check

关闭防火墙

```shell
systemctl stop firewalld
```

验证mac和product_uuid

```bash
ip link
ifconfig -a
cat /sys/class/dmi/id/product_uuid
```

检查网络适配器

关闭seLinux

```bash
# Set SELinux in permissive mode (effectively disabling it)
setenforce 0
sed -i 's/^SELINUX=enforcing$/SELINUX=permissive/' /etc/selinux/config
```

加载br_netfilter

```shell
modprobe br_netfilter
lsmod | grep br_netfilter
```

保证 net.bridge.bridge-nf-call-iptables 被设置为1

```bash
cat <<EOF >  /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sysctl --system
```

关闭swap

```shell
swapoff -a
```



### 安装kubeadm kubelet和kubectl

设置yum仓库

```shell
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
```

下载，启动kubelet

```shell
yum install -y kubelet-1.17.0 kubeadm-1.17.0 kubectl-1.17.0 --disableexcludes=kubernetes

systemctl enable --now kubelet
```

### 拉取运行需要的镜像

```bash
#从自己的仓库镜像
#docker login -u docker-push -p docker@2019 nexus.sigsmart.com.cn:8001

#images=("k8s.gcr.io/kube-apiserver:v1.17.0" "k8s.gcr.io/kube-controller-manager:v1.17.0" "k8s.gcr.io/kube-scheduler:v1.17.0" "k8s.gcr.io/kube-proxy:v1.17.0" "k8s.gcr.io/pause:3.1" "k8s.gcr.io/etcd:3.4.3-0" "k8s.gcr.io/coredns:1.6.5")

#for i in ${images[*]}; do
#	echo $i
#done

#从阿里的仓库镜像
images=("kube-apiserver:v1.17.0" "kube-controller-manager:v1.17.0" "kube-scheduler:v1.17.0" "kube-proxy:v1.17.0" "pause:3.1" "etcd:3.4.3-0" "coredns:1.6.5")
for i in ${images[*]}; do
    docker pull registry.cn-hangzhou.aliyuncs.com/google_containers/$i 
    docker tag registry.cn-hangzhou.aliyuncs.com/google_containers/$i k8s.gcr.io/$i 
    docker rmi registry.cn-hangzhou.aliyuncs.com/google_containers/$i 
done
```

### 启动master

```bash
kubeadm init --pod-network-cidr=10.244.0.0/16 --apiserver-advertise-address=192.168.199.237
```

### 启动slave

```shell
kubeadm join 192.168.199.237:6443 --token k62rvt.c14ofwoi3jor6ycj \
    --discovery-token-ca-cert-hash sha256:1db66e2d9a3eb91098d0fb8276e00694a8636177fdc977ab2048d17f7f4cc0ad
```

### 授权使用kubectl

```bash
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
```

```bash
export KUBECONFIG=/etc/kubernetes/admin.conf
```

### 安装网络

```shell
#flannel
kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml
#calico
#kubectl apply -f https://docs.projectcalico.org/v3.8/manifests/calico.yaml
```

### 安装dashboard

```shell
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0-beta1/aio/deploy/recommended.yaml
```



### 其它问题

https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/troubleshooting-kubeadm/