# Introduction
Max temperature finder.

## Run w/ Unix shell

    cat ../data/190? | ./max-temp-mapper.rb | ./max-temp-reducer.rb

## Run w/ Hadoop

    hadoop jar $HADOOP_HOME/share/hadoop/tools/lib/hadoop-streaming-*.jar \
    -input ../data/190? \
    -output output \
    -mapper ./max-temp-mapper.rb \
    -reducer ./max-temp-reducer.rb
