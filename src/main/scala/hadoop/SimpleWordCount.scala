package hadoop

import java.util
import java.util.StringTokenizer

import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapred._

/**
 * Based on: https://hadoop.apache.org/docs/r1.2.1/mapred_tutorial.html
 */
class SimpleWordCount {
  class Map extends MapReduceBase with Mapper[LongWritable, Text, Text, IntWritable] {
    val one = new IntWritable(1)
    val word = new Text()

    override def map(k1: LongWritable, v1: Text,
                     outputCollector: OutputCollector[Text, IntWritable],
                     reporter: Reporter): Unit = {
      val tokenizer = new StringTokenizer(v1.toString)

      while (tokenizer.hasMoreTokens) {
        word.set(tokenizer.nextToken())

        outputCollector.collect(word, one)
      }
    }
  }

  class Reduce extends MapReduceBase with Reducer[Text, IntWritable, Text, IntWritable] {
    override def reduce(k2: Text, iterator: util.Iterator[IntWritable],
                        outputCollector: OutputCollector[Text, IntWritable],
                        reporter: Reporter): Unit = {
      var sum = 0

      while (iterator.hasNext) {
        sum += iterator.next().get()
      }

      outputCollector.collect(k2, new IntWritable(sum))
    }
  }

  def main(args: Array[String]): Unit = {
    val config = new JobConf(classOf[SimpleWordCount])

    config.setJobName("SimpleWordCount")

    config.setOutputKeyClass(classOf[Text])
    config.setOutputValueClass(classOf[IntWritable])

    config.setMapperClass(classOf[Map])
    config.setCombinerClass(classOf[Reduce])
    config.setReducerClass(classOf[Reduce])

    config.setInputFormat(classOf[TextInputFormat])
    config.setOutputFormat(classOf[TextOutputFormat[Text, IntWritable]])

    FileInputFormat.setInputPaths(config, new Path(args(0)))
    FileOutputFormat.setOutputPath(config, new Path(args(1)))

    JobClient.runJob(config)
  }
}
