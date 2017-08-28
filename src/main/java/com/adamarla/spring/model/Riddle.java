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
@Table(name = "riddles")
public class Riddle extends Stockable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "riddles_generator")
    @SequenceGenerator(name = "riddles_generator", sequenceName = "riddles_id_seq")
    @Override
    public int getId() {
        return id;
    }

    @Column(name = "type")
    private String type;

    @Column(name = "original_id")
    private int originalId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOriginalId() {
        return originalId;
    }

    public void setOriginalId(int originalId) {
        this.originalId = originalId;
    }

}
