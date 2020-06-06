package org.jjhome.hadoop.hdfs.access;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;

public class FileCopyWithProgress {

    public static void main(String[] args) throws IOException {
        String localSrc = args[0];
        String dst = args[1];
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));

        try {
            Configuration config = new Configuration();
            FileSystem fs = FileSystem.get(URI.create(dst), config);
            OutputStream out = fs.create(new Path(dst), () -> System.out.print("."));
            IOUtils.copyBytes(in, out, 4096, false);
        }
        finally {
            IOUtils.closeStream(in);
        }
    }
}

