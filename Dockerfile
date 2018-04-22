FROM wisvch/openjdk:8-jdk AS builder
COPY . /src
WORKDIR /src
RUN ./gradlew build

FROM wisvch/spring-boot-base:1
COPY --from=builder /src/build/libs/feedback-tool.jar /srv/feedback-tool.jar
CMD ["/srv/feedback-tool.jar"]
