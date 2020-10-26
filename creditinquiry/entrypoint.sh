#!/bin/bash

echo "Waiting PostgreSQL"

while ! nc -z inquiry-db 5432; do
  sleep 0.1
done

java -jar creditinquiry-0.0.1-SNAPSHOT.jar