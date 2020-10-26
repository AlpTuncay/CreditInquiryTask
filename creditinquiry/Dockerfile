FROM openjdk:11
RUN apt-get update && apt-get -y install netcat
COPY entrypoint.sh .
COPY target/creditinquiry-0.0.1-SNAPSHOT.jar .

CMD ["/bin/bash", "entrypoint.sh"]