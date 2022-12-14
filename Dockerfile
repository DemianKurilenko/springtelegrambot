FROM openjdk:11 as builder

WORKDIR /tmp
COPY . /tmp

RUN ./gradlew clean build -x test

FROM openjdk:11-jre

COPY --from=builder /tmp/build/libs/workoutbot-0.0.1-SNAPSHOT.jar /tmp

EXPOSE 8080

CMD java -Duser.timezone=GMT -jar /tmp/workoutbot-0.0.1-SNAPSHOT.jar