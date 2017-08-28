package com.adamarla.spring.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by adamarla on 8/23/17.
 */

@Component
public class SourceSearcher {

    private String index = "/home/adamarla/work/alt-bank/index";

    public SourceSearcher() {
        try(IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)))) {
            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer = new StandardAnalyzer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getMatches(String searchString) {
        return null;
    }
}
