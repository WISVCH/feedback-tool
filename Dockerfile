FROM wisvch/openjdk:8-jdk AS builder
COPY . /src
WORKDIR /src
RUN ./gradlew build

FROM wisvch/openjdk:8-jre
COPY --from=builder /src/build/libs/feedback-tool.jar /srv/feedback-tool.jar
WORKDIR /srv
RUN groupadd -r feedback-tool && useradd --no-log-init -r -g feedback-tool feedback-tool
USER feedback-tool
CMD java -jar $JAVA_OPTS /srv/feedback-tool.jar
