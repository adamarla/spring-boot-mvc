package com.adamarla.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by adamarla on 8/24/17.
 */

@Entity
@Table(name = "skills")
public class Skill extends Stockable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skills_generator")
    @SequenceGenerator(name = "skills_generator", sequenceName = "skills_id_seq")
    @Override
    public int getId() {
        return id;
    }


    @Column(name = "parent_skill_id")
    private Integer parentSkillId;

    public Integer getParentSkillId() {
        return parentSkillId;
    }

    public void setParentSkillId(Integer parentSkillId) {
        this.parentSkillId = parentSkillId;
    }

}
