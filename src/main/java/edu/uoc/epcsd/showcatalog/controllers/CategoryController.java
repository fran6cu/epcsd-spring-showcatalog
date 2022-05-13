package edu.uoc.epcsd.showcatalog.controllers;

import edu.uoc.epcsd.showcatalog.entities.Category;
import edu.uoc.epcsd.showcatalog.repositories.CategoryRepository;
import edu.uoc.epcsd.showcatalog.repositories.ShowRepository;
import edu.uoc.epcsd.showcatalog.services.CategoryService;
import edu.uoc.epcsd.showcatalog.services.ShowService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Log4j2
@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ShowService showService;

    @Autowired
    private ShowRepository showRepository;


    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories() {
        log.trace("getAllCategories");
        return categoryRepository.findAll();
    }

    @PostMapping("/Add")
    @ResponseStatus(HttpStatus.OK)
    public Category addCategory(@RequestBody Category category) {
        log.trace("add category");
        return categoryRepository.save(category);

    }


    @GetMapping(path = "/consult", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity queryCategory(@RequestParam(required = false) Long idCategory) throws ObjectNotFoundException {
        log.trace("Consult");
        Category category = categoryService.queryCategory(idCategory);
        return new ResponseEntity<>(categoryService.queryAllCategories(), HttpStatus.OK);
    }


    @PostMapping("/{idCategory}/addShow/{idShow}")
    @ResponseStatus(HttpStatus.OK)
    public boolean addShow(@PathVariable Long idCategory, @PathVariable Long idShow) throws Exception {
        log.trace("Add show category");
        categoryService.addShow(idCategory, idShow);
        return true;

    }

    @DeleteMapping("/delete/{idCategory}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity delCategory(@PathVariable Long idCat) {
        log.trace("delete category");
        categoryService.delCategory(idCat);
        return new ResponseEntity<>("Category deleted", HttpStatus.OK);

    }



}
