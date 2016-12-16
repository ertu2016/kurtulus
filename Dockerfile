FROM frolvlad/alpine-oraclejdk8:slim
ADD iyzicotodolist-1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]