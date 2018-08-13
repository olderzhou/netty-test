#!/bin/bash


echo "$1"

mvn clean package -P "$1" -DskipTests
mkdir ~/Data/deploy/"$1" -p
cp test-eureka/target/test-eureka.jar ~/Data/deploy/"$1"
cd ~/Data/deploy/"$1"

java -jar test-eureka.jar




