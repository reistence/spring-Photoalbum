package org.lessons.springilmiofotoalbum.api;


import org.lessons.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import org.lessons.springilmiofotoalbum.model.Photo;
import org.lessons.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/photos")
public class PhotoRestController {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public List<Photo> list(@RequestParam(name="q") Optional<String> search){
        if (search.isPresent()) return photoService.getFilteredPhotos(search.get());
        return photoService.getAllPhotos();
    }

    @GetMapping("/{id}")
    public Photo getById(@PathVariable Integer id){
        try {
            return photoService.getById(id);
        } catch (PhotoNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
