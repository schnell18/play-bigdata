package org.tinker.big.data.go.dep;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.parquet.example.data.Group;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GoDepMapper extends Mapper<Void, Group, Text, LongWritable> {

    private final Text outputKey = new Text();
    private final LongWritable outputValue = new LongWritable(1);

    @Override
    protected void map(Void key, Group value, Context context) throws IOException, InterruptedException {
        // Extract go.mod content from the Parquet record
        String goModContent = value.getString("content", 0);
        List<GoModuleRef> refs = parseGoModeFile(goModContent);

        for (GoModuleRef ref:refs) {
            outputKey.set(ref.moduleName);
            outputValue.set(1L);
            context.write(outputKey, outputValue);
        }
    }

    private List<GoModuleRef> parseGoModeFile(String goModContent) {
        // incomplete implementation
        String[] lines = goModContent.split("\n");
        return Arrays.stream(lines).filter(GoDepMapper::isDepDeclaration)
                .map(GoDepMapper::toGoModuleRef)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static boolean isDepDeclaration(String line) {
        return !line.contains("// indirect")
                && !line.contains("replace")
                && !line.contains("retract")
                ;
    }

    private static GoModuleRef toGoModuleRef(String line) {
        String[] comps = line.split("\\s+");
        if (comps.length == 2) {
            return new GoModuleRef(comps[0], comps[1]);
        }
        return null;
    }

    private static class GoModuleRef implements Serializable {
        public String moduleName;

        public String version;

        public GoModuleRef(String moduleName, String version) {
            this.moduleName = moduleName;
            this.version = version;
        }
    }
}
