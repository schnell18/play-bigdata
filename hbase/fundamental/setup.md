# Introduction

This document describes the instructions to setup an hbase development environment.

## download binary release

The following command use hbase 2.2.5 as example:

    wget https://mirror.bit.edu.cn/apache/hbase/2.2.5/hbase-2.2.5-bin.tar.gz


## install

Just exact the hbase-2.2.5.tar.gz to any directory, say `HBASE_HOME`, then setup the
environment variable `JAVA_HOME`.

## start hbase

Run the following commands:

    cd $HBASE_HOME/bin
    ./start-hbase.sh

The log files are located under `$HBASE_HOME/logs`.
You may access hbase via web interface by browse:

    http://localhost:16010/

## access hbase shell

You may connection hbase server to perform administration tasks via
hbase hell. To launch the shell, you type:


    cd $HBASE_HOME/bin
    ./hbase shell

## manage table



### create table
### load data
### query data
