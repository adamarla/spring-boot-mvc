package com.adamarla.spring.repo;

import com.adamarla.spring.model.DemoModel;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by adamarla on 8/14/17.
 */

public interface DemoRepo extends CrudRepository<DemoModel, Long> {

    List<DemoModel> findByLastName(String lastName);

}
