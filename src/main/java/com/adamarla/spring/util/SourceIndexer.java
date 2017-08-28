package com.adamarla.spring.util;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by adamarla on 8/21/17.
 */

public class SourceIndexer {

    private String indexDirPath = "/home/adamarla/work/alt-bank/index";
    private String bankPath = "/home/adamarla/work/alt-bank";

    public SourceIndexer() {
        try (Directory directory = FSDirectory.open(Paths.get(indexDirPath))) {
            IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            IndexWriter indexWriter = new IndexWriter(directory, config);
            indexDocs(indexWriter, Paths.get(bankPath));
            indexWriter.commit(); indexWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void indexDocs(IndexWriter writer, Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes)
                    throws IOException {
                if (path.endsWith("source.xml"))
                    indexDoc(writer, path);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void indexDoc(IndexWriter writer, Path path) throws IOException {
        System.out.println(path.toString());
        Document document = new Document();
        Field pathField = new StringField("path", path.toString(), Field.Store.YES);
        document.add(pathField);

        String statement = extractStatementTeX(path);
        document.add(new TextField("statement", statement, Field.Store.NO));

        writer.updateDocument(new Term("path", path.toString()), document);
    }

    private String extractStatementTeX(Path path) {
        QuestionXMLHandler handler = null;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            handler = new QuestionXMLHandler();
            parser.parse(path.toFile(), handler);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return handler.statementText;
    }

}

class QuestionXMLHandler extends DefaultHandler {

    String statementText;
    private boolean firstTeX = false;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (firstTeX) {
            statementText = new String(ch, start, length);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("tex") && !firstTeX) {
            firstTeX = true;
        }
    }

}