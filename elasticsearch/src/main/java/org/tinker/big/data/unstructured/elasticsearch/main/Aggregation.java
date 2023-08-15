package org.tinker.big.data.unstructured.elasticsearch.main;

import org.tinker.big.data.unstructured.elasticsearch.util.ESWrapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Aggregation {

    public static void main(String[] args) {
        SearchRequest searchRequest = new SearchRequest("kibana_sample_data_ecommerce");
        TermsAggregationBuilder aggregation = AggregationBuilders
                .terms("Terms_Aggregation")
                .field("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(aggregation);
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);
        ESWrapper esWrapper = ESWrapper.getInstance();
        try {
            SearchResponse searchResponse = esWrapper.search(searchRequest);
            System.out.println(searchResponse);
        } catch (IOException ex) {
            Logger.getLogger(Aggregation.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            esWrapper.closeConnection();
        }
    }
}
