# Introduction

This file documents the procedure to setup a pseudo distributed hadoop cluster.

## file system and replication factor

    cp core-site.xml $HADOOP_HOME/etc/hadoop/
    cp hdfs-site.xml $HADOOP_HOME/etc/hadoop/

## localhost ssh setup

Make sure passwordless SSH login works. Otherwise, type the commands as follows:

    ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
    cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
    chmod 0600 ~/.ssh/authorized_keys

## HDFS initialization

    hdfs namenode -format

## HDFS startup

    start-dfs.sh

Then you can browse the namenode via web by click: [http://localhost:9870/][1]

## Create directory

    hdfs dfs -mkdir /user
    hdfs dfs -mkdir /user/justin

## Copy files

    hdfs dfs -mkdir /user/justin/input
    hdfs dfs -put $HADOOP_HOME/etc/hadoop/*.xml /user/justin/input


[1]: http://localhost:9870/