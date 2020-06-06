# Introduction

Various programs to access HDFS file system in Java.

## URLCat

Concat file in HDFS identified by URL. Type command as follows to run
this program:

    export HADOOP_CLASSPATH=./target/hdfs-access-1.0.0-SNAPSHOT.jar
    hadoop org.jjhome.hadoop.hdfs.access.URLCat hdfs://localhost:9000/user/justin/1902

## FileSystemCat

Concat file in HDFS identified by URL. This implementation avoids
URLFactory issue which could affect previous URLCat implementation. Type
command as follows to run this program:

    export HADOOP_CLASSPATH=./target/hdfs-access-1.0.0-SNAPSHOT.jar
    hadoop org.jjhome.hadoop.hdfs.access.FileSystemCat hdfs://localhost:9000/user/justin/1902

## FileSystemDoubleCat

Concat file in HDFS identified by URL and repeat the content twice. Type
command as follows to run this program:

    export HADOOP_CLASSPATH=./target/hdfs-access-1.0.0-SNAPSHOT.jar
    hadoop org.jjhome.hadoop.hdfs.access.FileSystemDoubleCat hdfs://localhost:9000/user/justin/1902

## FileCopyWithProgress

Copy file and show progress. Type command as follows to run this
program:

    export HADOOP_CLASSPATH=./target/hdfs-access-1.0.0-SNAPSHOT.jar
    hadoop org.jjhome.hadoop.hdfs.access.FileCopyWithProgress README.md hdfs://localhost:9000/user/justin/README.md

## ListStatus

Type command as follows to run this program:

    export HADOOP_CLASSPATH=./target/hdfs-access-1.0.0-SNAPSHOT.jar
    hadoop org.jjhome.hadoop.hdfs.access.ListStatus hdfs://localhost:9000/ hdfs://localhost:9000/user/justin/logs

## GlobStatus

Play w/ HDFS path glob feature. Type command as follows to run this program:

    export HADOOP_CLASSPATH=./target/hdfs-access-1.0.0-SNAPSHOT.jar
    hadoop org.jjhome.hadoop.hdfs.access.GlobStatus hdfs://localhost:9000/user/justin/logs/*/*/*
