FROM openjdk:17
COPY ./target/charging-station-task-0.0.1-SNAPSHOT.jar /tmp/charging-station-task-0.0.1-SNAPSHOT.jar
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "/tmp/charging-station-task-0.0.1-SNAPSHOT.jar"]