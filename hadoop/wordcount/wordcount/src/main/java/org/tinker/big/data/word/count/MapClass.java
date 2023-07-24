package org.tinker.big.data.word.count;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Map Class which extends MaReduce.Mapper class Map is passed a single line at
 * a time, it splits the line based on space and generated the token which are
 * output by map with value as one to be consumed by reduce class
 *
 */
public class MapClass extends Mapper<LongWritable, Text, Text, LongWritable> {

    private final static LongWritable one = new LongWritable(1);
    private final Text word = new Text();

    private static final Pattern WORD = Pattern.compile("\\w+");

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();
        Matcher m = WORD.matcher(line);

        while (m.find()) {
            word.set(m.group());
            context.write(word, one);
        }

    }
}
