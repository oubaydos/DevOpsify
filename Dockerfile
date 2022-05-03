FROM maven:3.8.5-openjdk-17
WORKDIR /devOpsify
COPY . .
RUN mvn clean package
WORKDIR ./target
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
CMD java -jar application.jar

