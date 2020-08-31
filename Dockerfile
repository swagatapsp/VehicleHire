FROM maven:3.5-jdk-8 AS build

WORKDIR /app
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:8-jdk-alpine
LABEL maintainer="swagatapsp@gmail.com"
ENV LOGGER_FILE_LOCATION="/home/vehcile-hire-folder"
COPY --from=build /home/app/target/vehcile-hire-*.jar /usr/local/lib/vehcile-hire.jar
RUN wget -O apm-agent.jar https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/1.2.0/elastic-apm-agent-1.2.0.jar

CMD java -javaagent:apm-agent.jar $JVM_OPTIONS -jar /usr/local/lib/vehcile-hire.jar