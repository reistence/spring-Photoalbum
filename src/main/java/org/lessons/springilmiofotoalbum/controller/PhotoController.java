package org.lessons.springilmiofotoalbum.controller;


import jakarta.validation.Valid;
import org.lessons.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import org.lessons.springilmiofotoalbum.model.Category;
import org.lessons.springilmiofotoalbum.model.Photo;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {


    @Autowired
    private PhotoService photoService;

    @Autowired
    private CategoryService categoryService;

   @GetMapping("/home")
    public String home(Model model) {
       return "/photo/home";
   }

   @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword){
       List<Photo> photos;
       if (keyword.isEmpty()){
           photos = photoService.getAllPhotos();
       } else {
           photos = photoService.getFilteredPhotos(keyword.get());
           model.addAttribute("keyword", keyword.get());
       }

       model.addAttribute("photos", photos);
       return "/photo/index";
   }


   @GetMapping("/{photoId}")
    public String show(@PathVariable("photoId") Integer id, Model model){
       try {
           Photo photo = photoService.getById(id);
           model.addAttribute("photo", photo);
           return "/photo/show";
       } catch (PhotoNotFoundException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo id: " + id + " not found");
       }
   }


   @GetMapping("/create")
    public String create(Model model){
       model.addAttribute("photo", new Photo());
       List<Category> category = categoryService.getAll();
       model.addAttribute("categoriesList", category);
       return "/photo/create";
   }


   @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model){
       if (bindingResult.hasErrors()){
           return "/photo/create";
       }
       photoService.createPhoto(formPhoto);
       return "redirect:/photos/index";

   }


   @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){

       try {
           Photo photo = photoService.getById(id);
           List<Category> categories = categoryService.getAll();
           model.addAttribute("photo", photo);
           model.addAttribute("categoriesList", categories);
           return "/photo/edit";

       } catch (PhotoNotFoundException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo id: " + id + " not found.");
       }

   }



   @PostMapping("/edit/{id}")
    public String editPhoto(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult){
       try {
           Photo updatePhoto = photoService.updatePhoto(formPhoto, id);
           return "redirect:/photos/{id}";
       } catch (RuntimeException e ){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Photo id: " + id + " not found." );
       }
   }



   @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
       try {
           Photo photo = photoService.getById(id);
           redirectAttributes.addFlashAttribute("danger", "Photo with " + photo.getTitle() + " has been successfully deleted");
           photoService.deleteById(id);
           return "redirect:/photos/index";

       } catch (PhotoNotFoundException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Photo id: " + id + " not found." );
       }
   }



}
