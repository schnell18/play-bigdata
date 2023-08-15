package org.tinker.big.data.unstructured.document.main;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class DocumentExtractor {

    public static void main(String[] args) {
        String docFile = "report.pdf";
        if (args != null && args.length >= 1) {
            docFile = args[0];
        }
        File file = new File(docFile);
        try {
            String content = autoParse(file);
            System.out.println(content);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String autoParse(File file) throws IOException, SAXException, TikaException {
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();

        try (InputStream stream = Files.newInputStream(file.toPath())) {
            parser.parse(stream, handler, metadata);
            for (String name: metadata.names() ) {
                System.out.println(name + ":\t" + metadata.get(name));
            }
            return handler.toString();
        }
    }
}
