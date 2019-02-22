#!/usr/bin/env bash

command=${1}

if [[ $command == "cloudera-import" ]];then
    docker import $(ls -t cloudera-quickstart/*.tar | head -1) cloudera/quickstart:imported-from-download
fi

if [[ $command == "docker-up" ]];then
    docker-compose up
fi

if [[ $command == "docker-stop" ]];then
    docker-compose stop
fi

if [[ $command == "cloudera-bash" ]];then
    docker exec -it big-data-hadoop-spark_cloudera_1 bash
fi

if [[ $command == "build" ]];then
    rm ./jobs/big-data-hadoop-spark-assembly-0.1.jar
    sbt clean compile assembly
    mv ./target/scala-2.12/big-data-hadoop-spark-assembly-0.1.jar ./jobs
fi
