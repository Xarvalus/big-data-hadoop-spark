#!/usr/bin/env bash

command=${1}

if [[ $command == "hadoop-simple-word-count" ]];then
    hadoop jar \
    big-data-hadoop-spark/big-data-hadoop-spark-assembly-0.1.jar \
    hadoop.SimpleWordCount \
    big-data-hadoop-spark/hadoop/SimpleWordCount/file01 \
    /jobs/hadoop/SimpleWordCount

    hadoop fs -cat /jobs/hadoop/SimpleWordCount/part-00000
fi

if [[ $command == "spark-word-count" ]];then
    spark-submit --class spark.SparkWordCount --master local \
    big-data-hadoop-spark/big-data-hadoop-spark-assembly-0.1.jar

    cat spark-wordcount-results/part-00000
fi
