package com.adamarla.spring.repo;

import com.adamarla.spring.util.SourceSearcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by adamarla on 8/23/17.
 */

@Component
public class SearchRepo {

    SourceSearcher searcher;

    @Autowired
    public SearchRepo(SourceSearcher searcher) {
        this.searcher = searcher;
    }

    public List<String> findMatches(String searchString) {
        return searcher.getMatches(searchString);
    }
}
