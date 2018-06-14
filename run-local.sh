#!/bin/bash





mvn clean package -Plocal -DskipTests


java -jar test-eureka/target/netty-test.jar




