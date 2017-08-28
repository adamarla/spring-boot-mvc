package com.adamarla.spring.repo;

import com.adamarla.spring.model.Sku;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by adamarla on 8/24/17.
 */

public interface SkuRepo extends CrudRepository<Sku, Long> {
}
