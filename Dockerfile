FROM openjdk:17
LABEL maintainer="EXPTIMEDATA"
ADD target/TimeSheet-Backent-0.0.1-SNAPSHOT.jar TimeSheet-Backent.jar
ENTRYPOINT ["java","-jar","TimeSheet-Backent.jar"]