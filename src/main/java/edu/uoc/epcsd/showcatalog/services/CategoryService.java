package edu.uoc.epcsd.showcatalog.services;

import edu.uoc.epcsd.showcatalog.entities.Category;

import java.util.List;

public interface CategoryService {




    public Category queryCategory(Long idCat);

    public List<Category> queryAllCategories();

    public boolean addShow(Long idCat, Long idShow) throws Exception;


    public boolean delCategory(Long idCat);

    public Category newCategory(Category category);
}
