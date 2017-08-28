package com.adamarla.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by adamarla on 8/24/17.
 */

@Entity
@Table(name = "riddles")
public class Riddle extends Stockable {

    @Column(name = "type")
    private String riddleType;

    @Column(name = "original_id")
    private int originalId;

    public String getRiddleType() {
        return riddleType;
    }

    public void setRiddleType(String riddleType) {
        this.riddleType = riddleType;
    }

    public int getOriginalId() {
        return originalId;
    }

    public void setOriginalId(int originalId) {
        this.originalId = originalId;
    }

}
