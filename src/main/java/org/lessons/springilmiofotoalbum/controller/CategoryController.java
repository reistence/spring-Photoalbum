package org.lessons.springilmiofotoalbum.controller;


import jakarta.validation.Valid;
import org.lessons.springilmiofotoalbum.exceptions.CategoryNotFoundException;
import org.lessons.springilmiofotoalbum.model.Category;
import org.lessons.springilmiofotoalbum.service.CategoryService;
import org.lessons.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PhotoService photoService;


    @GetMapping
    public String index(Model model){
        model.addAttribute("list", categoryService.getAll());
        model.addAttribute("bho", photoService.getAllPhotos() );
        model.addAttribute("categoryObj", new Category());
        return "/categories/index";

    }

    @PostMapping("/save")
    public String doSave(@Valid @ModelAttribute(name = "categoriesObj") Category category, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("list", categoryService.getAll());
            return "/categories/index";
        }

        categoryService.create(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        try {
            Category category = categoryService.getById(id);
            model.addAttribute("category", category );

            return "/categories/edit";
        } catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category id: " + id + " not found");
        }
    }


    @PostMapping("/edit/{id}")
    public String editCategory(@PathVariable Integer id, @Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult) {
        try {
            Category updateIngredient = categoryService.update(formCategory, id);
            return "redirect:/categories";
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category id: " + id + " not found");
        }
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try {
            Category category = categoryService.getById(id);
            redirectAttributes.addFlashAttribute("danger", "Category: " + category.getName() + " has been successfully deleted");
            categoryService.deleteById(id);
            return "redirect:/categories";
        } catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category id: " + id + " not found");
        }
    }
}
