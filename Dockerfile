FROM jenkins/jenkins:lts-alpine-jdk17

USER root

ARG APP_NAME="fnb"

ARG APP_VERSION="0.0.1-SNAPSHOT"

ARG JAR_FILE="/build/libs/${APP_NAME}-${APP_VERSION}.jar"

COPY ${JAR_FILE} /app.jar

ENV JAR_ARGS=""

ENV SYS_PROPS=""

ENV LOGS="./logs"

RUN mkdir -p ${LOGS}
