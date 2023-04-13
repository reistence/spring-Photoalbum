package org.lessons.springilmiofotoalbum.service;

import org.lessons.springilmiofotoalbum.exceptions.CategoryNotFoundException;
import org.lessons.springilmiofotoalbum.model.Category;
import org.lessons.springilmiofotoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    public List<Category> getAll(){
        return categoryRepo.findAll(Sort.by("name"));
    }

    public Category create(Category formCategories){
        Category categoryToCreate = new Category();
        categoryToCreate.setName(formCategories.getName());
        return categoryRepo.save(categoryToCreate);
    }


    public Category getById(Integer id) throws CategoryNotFoundException{
        Optional<Category> result = categoryRepo.findById(id);
        if (result.isPresent()){
            return result.get();
        } else {
            throw new CategoryNotFoundException(Integer.toString(id));
        }

    }

    public Category update(Category formCategories, Integer id){
        Category categoryToUpdate = new Category();
        categoryToUpdate.setName(formCategories.getName());
        return categoryRepo.save(categoryToUpdate);

    }


    public boolean deleteById(Integer id){
        categoryRepo.findById(id).orElseThrow(() -> new CategoryNotFoundException(Integer.toString(id)));
        try {
            categoryRepo.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }


}
