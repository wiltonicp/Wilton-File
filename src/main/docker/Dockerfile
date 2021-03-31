FROM openjdk:8u212-jre
MAINTAINER Ranger wilton.icp@gmail.com
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
  && echo 'Asia/Shanghai' >/etc/timezone \

VOLUME /tmp
EXPOSE 8127

ADD wilton-file-1.0.0.jar /wilton/wilton-file-1.0.0.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/wilton/wilton-file-1.0.0.jar"]