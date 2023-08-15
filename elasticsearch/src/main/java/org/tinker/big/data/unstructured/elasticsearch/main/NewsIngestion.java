package org.tinker.big.data.unstructured.elasticsearch.main;

import org.tinker.big.data.unstructured.elasticsearch.util.ESWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

public class NewsIngestion {

    public static void main(String[] args) {

        File directoryPath = new File("./news");
        //List of all files and directories
        File[] newsList = directoryPath.listFiles();
        if (newsList == null) {
            return;
        }

        ESWrapper esWrapper = ESWrapper.getInstance();
        try {
            for (File f : newsList) {
                Map dataMap = convertJson2Map(f.getAbsolutePath());
                String id = UUID.randomUUID().toString();
                IndexRequest indexRequest = new IndexRequest("news").source(dataMap);
                IndexResponse response = esWrapper.index(indexRequest);
                System.out.println(response);
            }
        } catch (ElasticsearchException e) {
            e.getDetailedMessage();
        } catch (java.io.IOException ex) {
            ex.getLocalizedMessage();
        }
        finally {
            esWrapper.closeConnection();
        }
    }

    private static Map convertJson2Map(String fileFullPath) {
        Map<?, ?> map = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(Paths.get(fileFullPath).toFile(), Map.class);
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return map;
    }

}
