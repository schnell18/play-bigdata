package org.jjhome.hadoop.hdfs.access;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class FileSystemCat {

    public static void main(String[] args) throws IOException {
        String uri = args[0];
        InputStream in = null;
        Configuration config = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), config);
        try {
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
        }
        finally {
            IOUtils.closeStream(in);
        }
    }
}

