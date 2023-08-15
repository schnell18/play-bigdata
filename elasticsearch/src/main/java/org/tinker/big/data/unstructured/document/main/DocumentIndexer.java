package org.tinker.big.data.unstructured.document.main;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.tinker.big.data.unstructured.elasticsearch.util.ESWrapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.xcontent.XContentFactory.jsonBuilder;

public class DocumentIndexer {

    public static void main(String[] args) {
        String docFile = "report.pdf";
        if (args != null && args.length >= 1) {
            docFile = args[0];
        }
        File file = new File(docFile);
        try {
            parseAndIndex(file);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void parseAndIndex(File file) throws IOException {
        ESWrapper esWrapper = ESWrapper.getInstance();
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        Map<String, String> source = new HashMap<>();
        InputStream stream = null;

        try {
            String indexName = "reports";
            stream = Files.newInputStream(file.toPath());
            parser.parse(stream, handler, metadata);
            for (String name: metadata.names() ) {
                source.put(name,metadata.get(name));
            }
            source.put("content", handler.toString());
            createIndex(esWrapper, indexName);

            IndexRequest indexRequest = new IndexRequest(indexName).source(source);
            IndexResponse response = esWrapper.index(indexRequest);
            System.out.println(response);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            esWrapper.closeConnection();
            if (stream != null) {
                stream.close();
            }
        }

    }

    private static void createIndex(ESWrapper esWrapper, String indexName) throws IOException {
        // set custom analyzer for index `articles`
        // set mappings for `content` field to make it full-text search friendly

        // "{ \"analysis\": { \"analyzer\": { \"my_custom_analyzer\": { \"type\": \"custom\", \"char_filter\": [ \"html_strip\" ], \"tokenizer\": \"standard\", \"filter\": [ \"lowercase\", \"stop\", \"stemmer\" ] } } } }",
        Settings.Builder settingsBuilder = Settings.builder().loadFromSource(
            "{ \"analysis\": { \"analyzer\": { \"my_custom_analyzer\": { \"type\": \"custom\", \"char_filter\": [ \"html_strip\" ], \"tokenizer\": \"standard\", \"filter\": [ \"lowercase\", \"stop\", \"stemmer\" ] } } } }",
            XContentType.JSON
        );
        XContentBuilder mappingBuilder = jsonBuilder()
                .startObject()
                .startObject("properties")
                .startObject("content")
                .field("type", "text")
                .field("analyzer", "my_custom_analyzer")
                .endObject()
                .endObject()
                .endObject();
        esWrapper.createIndex(indexName, mappingBuilder, settingsBuilder);

    }
}
