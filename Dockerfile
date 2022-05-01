FROM maven:3.8.5-openjdk-17
WORKDIR /devOpsify
COPY . .
RUN mvn clean package
CMD java -jar devopsify-0.0.1-SNAPSHOT.jar