# Introduction

This file documents spark setup on debian 10.  For your convenience, we
provides a Vagrantfile to create a multi-node spark cluster. The cluster is
based on debian 10.6 w/ Adopt-OpenJDK 8 installed.
The installation and setup steps are automated by running ansible inside
Vagrant. The ansible inventory is managed by Vagrant. If you want to run
ansible manually, you using the generated inventory file
`.vagrant/provisioners/ansible/inventory/vagrant_ansible_inventory` as
follows:

    ansible-playbook -i .vagrant/provisioners/ansible/inventory/vagrant_ansible_inventory provision/playbook.yml

## Pre-requisite

In order to setup and use the virtualized spark cluster, you need
following softwares installed:

- [vagrant][1]
- [virtualbox 6.x][2]
- [Python 3.x][3]
- [ansible][4]
- [pyspark 2.4.x][5]

You also need the vagrant box [schnell18/buster64][9].
Alternatively, you can build the box yourself by following
instruction from [this github repository][10].

Optinally, you may install following softwares to ease spark development
under command line:
- [tmux][6]
- [vim][7] or [neovim][8]


## start spark

Login the `primary.spark.vn` host:

    vagrant ssh primary

Then run the spark master and worker:

    start-all.sh

## install spark client

The Python `pyspark` package includes common spark client. To install
this package, please run this command on the host machine:

    cd spark
    pip install --user -r requirements.txt

## connect to spark cluster

Once you have successfully installed pyspark, you have access to a
number of spark client tools such as:

* spark-shell
* spark-sql
* pyspark

You can connect to spark master like:

    spark-shell --master spark://primary.spark.vn:7077

You can also run the tmux script under `labs/record-linkage/tmux.sh` to
get a spark command line session.

[1]: https://www.vagrantup.com
[2]: https://www.virtualbox.org
[3]: https://www.python.org
[4]: https://www.ansible.com
[5]: https://pypi.org/project/pyspark/
[6]: https://en.wikipedia.org/wiki/Tmux
[7]: https://www.vim.org
[8]: https://www.neovim.io
[9]: https://app.vagrantup.com/schnell18/boxes/buster64
[10]: https://github.com/schnell18/vmbot
