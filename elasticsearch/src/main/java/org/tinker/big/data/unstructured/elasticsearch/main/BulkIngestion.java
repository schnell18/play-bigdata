package org.tinker.big.data.unstructured.elasticsearch.main;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.tinker.big.data.unstructured.elasticsearch.util.ESWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BulkIngestion {

    public static void main(String[] args) {
        BulkRequest request = new BulkRequest();
        Map<String, String> m = new HashMap<>();
        m.put("p1", "v1");
        m.put("p2", "v2");
        m.put("p3", "v3");

        //index must start with lower case! 
        request.add(new IndexRequest("bulk_index_test")
                .source(XContentType.JSON, "field", "foo"));
        request.add(new IndexRequest("bulk_index_test")
                .source(XContentType.JSON, "field", "bar"));
        request.add(new IndexRequest("bulk_index_test")
                .source(XContentType.JSON, "field", "baz"));
        request.add(new IndexRequest("bulk_index_test")
                .source(m));

        ESWrapper esWrapper = ESWrapper.getInstance();
        try {
            BulkResponse bulkResponse = esWrapper.bulk(request);
            System.out.println(bulkResponse);
        }
        catch (IOException ex) {
            Logger.getLogger(BulkIngestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            esWrapper.closeConnection();
        }

    }
}
