# Introduction

Hadoop map-reduce word count program.

## bootstrap an empty project

    mvn archetype:generate \
        -DgroupId=org.tinker.big.data.word.count \
        -DartifactId=wordcount \
        -DarchetypeArtifactId=maven-archetype-quickstart \
        -DinteractiveMode=false

## Add map-reduce dependencies

    <dependency>
      <groupId>org.apache.hadoop</groupId>
      <artifactId>hadoop-mapreduce-client-common</artifactId>
      <version>3.3.6</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>org.apache.hadoop</groupId>
      <artifactId>hadoop-common</artifactId>
      <version>3.3.6</version>
      <scope>provided</scope>
    </dependency>

## Register task

    org.tinker.big.data.word.count.WordCount
    s3://bigdata-mr-lab-jus01/bigdata-mr-lab/input
    s3://bigdata-mr-lab-jus01/bigdata-mr-lab/output
