FROM maven:
WORKDIR /moroccan-pixels-backend
COPY . .
RUN mvn clean package
CMD java -jar moroccanpixels-0.0.1-SNAPSHOT.jar