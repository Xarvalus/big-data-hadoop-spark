# Scala Hadoop Spark

- MapReduce Hadoop jobs
- Spark RDD computations

Purely written in Scala, deployed on local Cloudera Docker Quickstart (CDH).

### Prerequisites

- Docker (At least 2 CPUs and 4GB+ RAM set in preferences)
- Cloudera QuickStart CDH Image ([Download](https://www.cloudera.com/downloads/quickstart_vms/5-13.html))
- Scala, Sbt & JDK

Extract downloaded docker image into `cloudera-quickstart/` ([Setup details](https://www.cloudera.com/documentation/enterprise/5-13-x/topics/quickstart_docker_container.html)).

### Development

Import extracted `*.tar` image:
```bash
sh setup.sh cloudera-import
```

Start docker image: 
```bash
sh setup.sh docker-up
```

Build jobs & computations: 
```bash
sh setup.sh build
```

Enter the Cloudera bash & execute chosen job/computation:
```bash
sh setup.sh cloudera-bash

# inside container
sh big-data-hadoop-spark/run.sh <command>
```
