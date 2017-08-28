package com.adamarla.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by adamarla on 8/24/17.
 */

@Entity
@Table(name = "skills")
public class Skill extends Stockable {

    @Column(name = "parent_skill_id")
    private Integer parentSkillId;

    public Integer getParentSkillId() {
        return parentSkillId;
    }

    public void setParentSkillId(Integer parentSkillId) {
        this.parentSkillId = parentSkillId;
    }
}
