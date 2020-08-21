# k8s 测试服相关

### k8s端口使用一览（30000-32767）

|       IP        | 端口  |  协议   |              用途               |
| :-------------: | :---: | :-----: | :-----------------------------: |
| 192.168.199.238 | 30080 |  http   |            http api             |
| 192.168.199.238 | 30443 |  https  |            http api             |
| 192.168.199.238 | 31083 |   tcp   |            emqx tcp             |
| 192.168.199.238 | 31085 |   tcp   |             emqx ws             |
| 192.168.199.238 | 30083 |  http   |         emqx dashboard          |
| 192.168.199.238 | 31306 |   tcp   |      mysql(root/root@123)       |
| 192.168.199.238 | 31379 |   tcp   |        redis(未设置密码)        |
| 192.168.199.238 | 30672 |  http   | rabbitmq dashboard(guest/guest) |
| 192.168.199.238 | 31672 |   tcp   |            rabbitmq             |
| 192.168.199.238 | 31650 |   UDP   |       nb,udp端口（7650）        |
| 192.168.199.238 | 31651 | tcp,ssl |     mqtts 业务端口（7651）      |
| 192.168.199.238 | 31653 |   tcp   |      mqtt 业务端口（7653）      |
| 192.168.199.238 | 31090 | tcp,ssl |     mqtts 负载端口（8090）      |
| 192.168.199.238 | 31091 |   tcp   |      mqtt 负载端口（8091）      |
| 192.168.199.237 | 30601 |  http   |             kibana              |

### 外网映射

| FROM(39.108.249.126) | TO(192.168.199.238) |      用途      |
| :------------------: | :-----------------: | :------------: |
|         7080         |        30080        |    api http    |
|         7443         |        30443        |   api https    |
|         7650         |        31650        |     nb,udp     |
|         7651         |        31651        | mqtts 业务端口 |
|         7653         |        31653        | mqtt 业务端口  |
|         8090         |        31090        | mqtts 负载端口 |
|         8091         |        31091        | mqtt 负载端口  |
|         5601         |        30601        |     kibana     |



### dashboard连接

采用apiservice的方式访问dashboard：

1.浏览器中导入私有证书文件<a href="/k8s/dashboard/kubecfg.p12">kubecfg.p12</a>，密码为123456。
  使用Firefox浏览器（Chrome不支持私有密钥）

2.访问 https://192.168.199.237:6443/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/#
需要的token为

eyJhbGciOiJSUzI1NiIsImtpZCI6IjFCY09kMkl6dWxSelBRalFBS2QwWFdRYjVEZ3hMbHRveUR3enF2OG1mTmMifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJkYXNoYm9hcmQtYWRtaW4tdG9rZW4tNG56djkiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiZGFzaGJvYXJkLWFkbWluIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiNzcwMGNmZWYtMjZmZC00NzY3LWJjOTUtYTJkMzQzNTBiMDdkIiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50Omt1YmVybmV0ZXMtZGFzaGJvYXJkOmRhc2hib2FyZC1hZG1pbiJ9.kcIYNjEGrKIepnGEFaGM8oMNdC4FJHabwbj-XDnrQQpPwwBaSvflr0933qLcAUHbEDbTi9MZwXlNf9BlDxKhlV8Ue7V4lUN9fsltactijTLViYq_iW1uhM7C81GTzr43Jr1MG0e1UxFx8Ouz5my16E96WNO9zSlH7JjVFP6N-BjD46nH4pXYoP_cwhuChELEolMuUctZlIGliZaAGd8hElPcDGWo7LMbXGMEOQR3hsXRcHSRvSnVax-S-MP5skWfTKQ_OCwigbIYGslS2LF2LNXI_WCuX5uAEhMlQdHbnwihVQzx642fYtRA4jejGqV0CeZIqBfKRsGbqJPgxxPk1Q



如果token被重置，在192.168.199.237（dev8）上，使用

```shell
kubectl describe secret -n kubernetes-dashboard dashboard-admin 
```

获取新的token



### nfs持久化存储

dev8(192.168.199.237)  /root/nfs/data/

|      目录      |      数据      |
| :------------: | :------------: |
|     /mysql     |   mysql 数据   |
|     /redis     |   redis数据    |
|   /rabbitmq    | rabbit mq数据  |
|     /nginx     | nginx 配置文件 |
| /elasticsearch |    日志数据    |

### redis集群

由于redis命令较为复杂，在节点运行成功后，进行手动集群配置。

在其中一个节点上运行

```shell
#目前redis-cli只能使用ip进行集群配置
#如果后面redis支持，可以使用redis-app-0.redis-service.siglife-cloud.svc.cluster.local

#初始化master集群
redis-cli --cluster create\
10.244.1.35:6379\
10.244.2.9:6379\
10.244.2.10:6379

#增加mater
redis-cli --cluster add-node 10.244.1.36:6379 10.244.1.35:6379

#增加slave
redis-cli --cluster add-node 10.244.1.36:6379 10.244.1.35:6379   --cluster-slave
```

###  拉取镜像

```shell
docker login -u docker-push -p docker@2019 nexus.sigsmart.com.cn:8001

images=("connector-joymeter" "connector-mqtt" "connector-nb" "gateway-command" "service-alarm" "service-device" "service-lock" "service-message" "service-meter" "service-route" "service-user")

for i in ${images[*]}; do
	docker rmi nexus.sigsmart.com.cn:8001/cloudv3/$i:3.1.1;
	docker pull nexus.sigsmart.com.cn:8001/cloudv3/$i:3.1.1;
done
```

### 创建docker秘钥

```shell
kubectl create secret docker-registry siglife-docker-registry --docker-server=nexus.sigsmart.com.cn:8001 --docker-username=docker-push --docker-password=docker@2019
```

