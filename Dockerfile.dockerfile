# FROM openjdk:8-jdk-alpine
# VOLUME /tmp

# RUN groupadd --gid 5000 newuser \ && useradd --home-dir /home/newuser --create-home --uid 5000 \ --gid 5000 --shell /bin/sh --skel /dev/null newuser
# USER newuser

# COPY target/*.jar app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","/app.jar"]
