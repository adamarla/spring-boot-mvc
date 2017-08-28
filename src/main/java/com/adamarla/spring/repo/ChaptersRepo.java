package com.adamarla.spring.repo;

import com.adamarla.spring.model.Chapter;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by adamarla on 8/16/17.
 */

public interface ChaptersRepo extends CrudRepository<Chapter, Long> {

}
