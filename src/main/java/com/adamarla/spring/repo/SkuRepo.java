package com.adamarla.spring.repo;

import com.adamarla.spring.model.Sku;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by adamarla on 8/24/17.
 */

public interface SkuRepo extends CrudRepository<Sku, Long> {

    List<Sku> findByIdGreaterThan(int id);
}
