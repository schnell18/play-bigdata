# Introduction

This file documents spark setup on debian 10.  For your convenience, we
provides a Vagrantfile to create two node spark cluster. The cluster is
based on debian 10.6 w/ Adopt-OpenJDK 8 installed.

## Pre-requisite

In order to setup and use the virtualized spark cluster, you need
following softwares installed:

- [vagrant][1]
- [virtualbox 6.x][2]
- [Python 3.x][3]
- [pyspark 2.4.x][4]

Optinally, you may install following software to ease spark development
under command line:
- [tmux][5]
- [vim][6] or [neovim][7]



## install JDK8

Debain 10 does not offer JDK8 package officially. You can use
adopt-openjdk instead.

Add repo and key:

    curl -sl https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | sudo apt-key add -
    sudo add-apt-repository --yes https://adoptopenjdk.jfrog.io/adoptopenjdk/deb/

Install openjdk:

    sudo apt-get update
    sudo apt-get install adoptopenjdk-8-hotspot -y

Set JAVA\_HOME:

    export JAVA_HOME=/usr/lib/jvm/adoptopenjdk-8-hotspot-amd64

You may find the location of `JAVA_HOME` by:

    sudo update-alternatives --config java

## install spark

https://archive.apache.org/dist/spark/spark-2.4.7/spark-2.4.7-bin-hadoop2.7.tgz

[1]: https://www.vagrantup.com/
[1]: https://www.vim.org/
