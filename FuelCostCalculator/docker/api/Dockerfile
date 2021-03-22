FROM openjdk:8-alpine

#Fetch repository
RUN apk update && apk add git wget tar
WORKDIR /home
RUN git clone https://github.com/kartik-pawar/mbfuelcalculator.git

#Build jar
RUN wget https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
RUN tar -xzf apache-maven-3.6.3-bin.tar.gz && rm apache-maven-3.6.3-bin.tar.gz

#initialize db
RUN mysql -u root -ppassword < /home/mbfuelcalculator/FuelCostCalculator/dbinit

RUN /home/apache-maven-3.6.3/bin/mvn -f /home/mbfuelcalculator/FuelCostCalculator clean package -Dmaven.test.skip=true
