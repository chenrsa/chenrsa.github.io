version=3.0.2-beta2
dirname=/root/imagetar/paas/${version}`date "+%Y-%m-%d.%H:%M:%S"`
mkdir ${dirname}
cd ${dirname}
echo ${dirname}
modules=("connector-mqtt" "connector-nb" "connector-joymeter" "gateway-command" "service-alarm" "service-device" "service-lock" "service-message" "service-meter" "service-route" "service-user" "gateway-nginx")
for i in ${modules[*]}; do
	echo "save ${i}.tar"
    docker save -o ${i}.tar nexus.sigsmart.com.cn:8001/cloudv3/${i}:${version}
done