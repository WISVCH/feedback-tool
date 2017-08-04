FROM wisvch/alpine-java:8_server-jre_unlimited
ADD build/libs/feedbacktool.jar /srv/feedbacktool.jar
WORKDIR /srv
CMD "/srv/feedbacktool.jar"