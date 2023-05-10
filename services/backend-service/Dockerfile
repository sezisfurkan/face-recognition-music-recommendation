FROM  openjdk:17-jdk-slim
VOLUME /tmp
ADD target/frmr-0.0.1-SNAPSHOT.jar springboot-docker-frmr.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/springboot-docker-frmr.jar"]
