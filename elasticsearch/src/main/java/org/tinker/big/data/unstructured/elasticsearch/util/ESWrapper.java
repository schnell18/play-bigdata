package org.tinker.big.data.unstructured.elasticsearch.util;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;

public class ESWrapper {

    private static final String HOST = "localhost";
    private static final int PORT_ONE = 9200;
    private static final int PORT_TWO = 9201;
    private static final String SCHEME = "http";

    private static final RequestOptions REQ_OPTS = RequestOptions.DEFAULT;
    private static ESWrapper esWrapper;
    private RestHighLevelClient restHighLevelClient;

    private ESWrapper(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    public static synchronized ESWrapper getInstance() {
        if (esWrapper == null) {
            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(HOST, PORT_ONE, SCHEME),
                            new HttpHost(HOST, PORT_TWO, SCHEME)
                    )
            );
            esWrapper = new ESWrapper(client);
        }
        return esWrapper;
    }

    public SearchResponse search(SearchRequest searchRequest) throws IOException {
        return this.restHighLevelClient.search(searchRequest, REQ_OPTS);
    }

    public synchronized void closeConnection() {
        if (this.restHighLevelClient != null) {
            try {
                this.restHighLevelClient.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            restHighLevelClient = null;
        }
    }

    public BulkResponse bulk(BulkRequest request) throws IOException {
        return this.restHighLevelClient.bulk(request, REQ_OPTS);
    }

    public IndexResponse index(IndexRequest indexRequest) throws IOException {
        return this.restHighLevelClient.index(indexRequest, REQ_OPTS);
    }
    public GetResponse get(GetRequest getRequest) throws IOException {
        return this.restHighLevelClient.get(getRequest, REQ_OPTS);
    }

    public UpdateResponse update(UpdateRequest updateRequest) throws IOException {
        return this.restHighLevelClient.update(updateRequest, REQ_OPTS);
    }
    public DeleteResponse delete(DeleteRequest deleteRequest) throws IOException {
        return this.restHighLevelClient.delete(deleteRequest, REQ_OPTS);
    }

    public AcknowledgedResponse createIndex(String indexName, XContentBuilder mapping, Settings.Builder settings) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        createIndexRequest.mapping("article", mapping);
        createIndexRequest.settings(settings.build());
        return this.restHighLevelClient.indices().create(createIndexRequest, REQ_OPTS);
    }
}
