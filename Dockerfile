FROM openjdk:11-slim

WORKDIR /app

ADD ./target/iam-tools-0.0.1.jar ./iam-tools.jar

ENTRYPOINT [ "java", "-jar", "iam-tools.jar" ]