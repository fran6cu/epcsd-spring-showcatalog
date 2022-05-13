package edu.uoc.epcsd.showcatalog.services.impl;

import edu.uoc.epcsd.showcatalog.entities.Category;
import edu.uoc.epcsd.showcatalog.entities.Show;
import edu.uoc.epcsd.showcatalog.repositories.CategoryRepository;
import edu.uoc.epcsd.showcatalog.repositories.ShowRepository;
import edu.uoc.epcsd.showcatalog.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {



    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category queryCategory(Long idCategory) {
        return categoryRepository.findById(idCategory).orElse(null);
    }

    @Override
    public List<Category> queryAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean addShow(Long idCat, Long idShow) throws Exception {
        try{
            Category category = categoryRepository.findById(idCat).orElse(null);
            Show show = showRepository.findById(idShow).orElse(null);

            category.addShow(show);
            categoryRepository.saveAndFlush(category);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean delCategory(Long idCategory) {
        try{
            categoryRepository.deleteById(idCategory);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public Category newCategory(Category category) {
        try{
            Category newCategory = new Category(category);
            return categoryRepository.saveAndFlush(newCategory);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
