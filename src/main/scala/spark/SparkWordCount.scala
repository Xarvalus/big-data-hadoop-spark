package spark

import org.apache.spark.SparkContext

/**
 * Based on: https://www.tutorialspoint.com/apache_spark/apache_spark_deployment.htm
 */
object SparkWordCount {
  def main(args: Array[String]) {
    val sparkContext = new SparkContext( "local", "Word Count",
      "/usr/local/spark", Nil, Map())

    val input = sparkContext.textFile("big-data-hadoop-spark/spark/WordCount/input")

    val count = input.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

      count.saveAsTextFile("spark-wordcount-results")
  }
}
