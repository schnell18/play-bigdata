package org.tinker.big.data.word.count;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reduce class which is executed after the map class and takes key(word) and
 * corresponding values, sums all the values and write the word along with the
 * corresponding total occurrences in the output
 *
 */
public class ReduceClass extends Reducer<Text, LongWritable, Text, LongWritable> {

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context)
            throws IOException, InterruptedException {

        long sum = 0;
        for (LongWritable value : values) {
            sum = sum + value.get();
        }

        context.write(key, new LongWritable(sum));
    }
}
