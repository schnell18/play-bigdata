package org.jjhome.hadoop.hdfs.access;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class ListStatus {

    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        for (String arg : args) {
            Path path = new Path(arg);
            FileStatus[] fileStatuses = fs.listStatus(path);
            for (FileStatus fileStatus : fileStatuses) {
                Path fileStatusPath = fileStatus.getPath();
                System.out.println(fileStatusPath);
            }
        }

    }
}

