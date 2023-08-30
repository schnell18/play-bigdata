package org.tinker.big.data.go.dep;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class GoDepReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    private final LongWritable outputValue = new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        // Iterate over the values and calculate the sum
        for (LongWritable value : values) {
            sum += value.get();
        }
        // Emit the key-value pair
        outputValue.set(sum);
        context.write(key, outputValue);
    }
}
