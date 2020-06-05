# Introduction
Max temperature finder.

## Run w/ Unix shell

    cat ../data/190? | ./max-temp-mapper.py | ./max-temp-reducer.py

## Run w/ Hadoop

    hadoop jar $HADOOP_HOME/share/hadoop/tools/lib/hadoop-streaming-*.jar \
    -input ../data/190? \
    -output output \
    -mapper ./max-temp-mapper.py \
    -reducer ./max-temp-reducer.py
