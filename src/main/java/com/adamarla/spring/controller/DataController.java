package com.adamarla.spring.controller;

import com.adamarla.spring.model.Chapter;
import com.adamarla.spring.model.DemoModel;
import com.adamarla.spring.model.Skill;
import com.adamarla.spring.model.Stockable;
import com.adamarla.spring.repo.ChaptersRepo;
import com.adamarla.spring.repo.DemoRepo;
import com.adamarla.spring.repo.SearchRepo;
import com.adamarla.spring.repo.SkillRepo;
import com.adamarla.spring.repo.SkuRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by adamarla on 8/14/17.
 */

@RestController
@RequestMapping("/data")
public class DataController {

    DemoRepo repository;
    ChaptersRepo chaptersRepo;
    SearchRepo searchRepo;
    SkillRepo skillRepo;
    SkuRepo skuRepo;

    @Autowired
    public DataController(DemoRepo repository, ChaptersRepo chaptersRepo, SearchRepo searchRepo,
                          SkillRepo skillRepo, SkuRepo skuRepo) {
        this.repository = repository;
        this.chaptersRepo = chaptersRepo;
        this.searchRepo = searchRepo;
        this.skillRepo = skillRepo;
        this.skuRepo =skuRepo;
    }

    public List<String> findMatches(@RequestParam("search_string") String searchString) {
        return searchRepo.findMatches(searchString);
    }

    @RequestMapping(value = {"", "/"})
    public Iterable<DemoModel> findAll() {
        return repository.findAll();
    }

    @RequestMapping(value = "/skills")
    public Iterable<Skill> findAllSkills() {
        return skillRepo.findAll();
    }

    @RequestMapping(value = "/skus")
    public List<SkuReturnable> findAllSkus() {
        return StreamSupport.stream(skuRepo.findAll().spliterator(), false)
                .filter(sku -> sku.getStockable().getChapterId() != null)
                .map(sku -> {
                    Stockable stockable = sku.getStockable();
                    return new SkuReturnable(sku.getId(), stockable.getChapterId(),
                            stockable instanceof Skill ? 4 :
                                    (sku.getPath().contains("snippet") ? 2 : 1),
                            sku.getPath());
                })
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/chapters")
    public Iterable<Chapter> findAllChapters() {
        return chaptersRepo.findAll();
    }

    @RequestMapping(value = "/byId", method = RequestMethod.GET)
    public DemoModel findById(@RequestParam("id") long id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "/byLname", method = RequestMethod.GET)
    public List<DemoModel> findByLastName(String lname) {
        return repository.findByLastName(lname);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public DemoModel create(@RequestParam("fname") String fname,
                            @RequestParam("lname") String lname) {
        DemoModel dm = new DemoModel();
        dm.setFirstName(fname);
        dm.setLastName(lname);
        return repository.save(dm);
    }

}

class SkuReturnable {

    public SkuReturnable(int id, int chapterId, int type, String path) {
        this.id = id; this.chapter = chapterId; this.type = type; this.path = path;
    }

    public int getId() {
        return id;
    }

    public int getChapter() {
        return chapter;
    }

    public int getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    int id, chapter, type;
    String path;
}