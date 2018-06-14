#!/bin/bash


echo "$1"

mvn clean package -P "$1" -DskipTests
mkdir /c/Users/zykj/Data/deploy/"$1" -p
cp test-eureka/target/netty-test.jar /c/Users/zykj/Data/deploy/"$1"
cd /c/Users/zykj/Data/deploy/"$1"

java -jar netty-test.jar




