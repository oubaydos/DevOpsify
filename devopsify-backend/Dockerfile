FROM maven:3.8.5-openjdk-17
WORKDIR /devOpsify
COPY . .
RUN mvn clean package -DskipTests
CMD java -jar ./target/*.jar

