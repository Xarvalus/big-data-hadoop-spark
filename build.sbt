name := "big-data-hadoop-spark"

version := "0.1"

scalaVersion := "2.12.8"

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")
scalacOptions := Seq("-target:jvm-1.7")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

libraryDependencies += "org.apache.hadoop" % "hadoop-core" % "1.2.1"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0"
