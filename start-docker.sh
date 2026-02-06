#!/bin/sh

./mvnw package

docker build -t grocery-shop .
docker run --name grocery-shop -p 8080:8080 grocery-shop