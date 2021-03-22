FROM openjdk:8-alpine

#Fetch repository
RUN apk update && apk add wget tar
WORKDIR /home
RUN mkdir kafka

#Build jar
RUN wget https://downloads.apache.org/kafka/2.7.0/kafka_2.13-2.7.0.tgz
RUN tar -xf kafka_2.13-2.7.0.tgz -C /home/kafka --strip-components 1

WORKDIR /home/kafka/bin

RUN kafka-topics.sh --bootstrap-server localhost:9092 --topic car_event --create --partitions 1 --replication-factor 1
RUN zookeeper-server-start.sh -daemon ../config/zookeeper.properties
RUN kafka-server-start.sh -daemon ../config/server.properties