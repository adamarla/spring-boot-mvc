package com.adamarla.spring.repo;

import com.adamarla.spring.model.Skill;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by adamarla on 8/24/17.
 */

public interface SkillRepo extends CrudRepository<Skill, Long> {
}
