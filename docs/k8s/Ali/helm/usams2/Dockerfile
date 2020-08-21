FROM centos:latest
MAINTAINER SIGLIFE

RUN mkdir /home/cloud10/
RUN mkdir /home/cloud10/web/
ADD web /home/cloud10/web/
ADD jdk-7u71-linux-x64.rpm /root/

RUN rpm -ivh /root/jdk-7u71-linux-x64.rpm
COPY local_policy.jar /usr/java/jdk1.7.0_71/jre/lib/security/
COPY US_export_policy.jar /usr/java/jdk1.7.0_71/jre/lib/security/
RUN chmod u+x /home/cloud10/web/bin/*.sh

CMD ["/home/cloud10/web/bin/catalina.sh","run"]