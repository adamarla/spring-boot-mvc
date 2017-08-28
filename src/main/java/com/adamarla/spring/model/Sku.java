package com.adamarla.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by adamarla on 8/23/17.
 */

@Entity
@Table(name = "skus")
public class Sku {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skus_generator")
    @SequenceGenerator(name = "skus_generator", sequenceName = "skus_id_seq")
    private int id;

    @OneToOne
    @JoinColumn(name = "stockable_id")
    private Stockable stockable;

    private String path;

    public int getId() {
        return id;
    }

    public Stockable getStockable() {
        return stockable;
    }

    public void setStockable(Stockable stockable) {
        this.stockable = stockable;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
