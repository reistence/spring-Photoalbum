package org.lessons.springilmiofotoalbum.service;


import org.lessons.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import org.lessons.springilmiofotoalbum.model.Photo;
import org.lessons.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {


    @Autowired
    PhotoRepository photoRepo;


    public Photo createPhoto(Photo formPhoto){

        Photo photoToSave = new Photo();

        photoToSave.setTitle(formPhoto.getTitle());
        photoToSave.setDescription(formPhoto.getDescription());
        photoToSave.setUrl(formPhoto.getUrl());
        photoToSave.setVisible(formPhoto.getVisible());
        photoToSave.setCategories(formPhoto.getCategories());
        return photoRepo.save(photoToSave);
    }



    public List<Photo> getAllPhotos(){
        return photoRepo.findAll(Sort.by("title"));
    }


    public List<Photo> getFilteredPhotos(String keyword){
        return photoRepo.findByTitleContainingIgnoreCase(keyword);
    }


    public Photo getById(Integer id) throws PhotoNotFoundException{
        Optional<Photo> result = photoRepo.findById(id);
        if (result.isPresent()){
            return result.get();
        } else {
            throw new PhotoNotFoundException(Integer.toString(id));
        }
    }



    public Photo updatePhoto(Photo photoForm, Integer id ) throws PhotoNotFoundException{
        Photo photoToUpdate = getById(id);
        photoToUpdate.setTitle(photoForm.getTitle());
        photoToUpdate.setDescription(photoForm.getDescription());
        photoToUpdate.setUrl(photoForm.getUrl());
        photoToUpdate.setVisible(photoForm.getVisible());
        photoToUpdate.setCategories(photoForm.getCategories());
        return photoRepo.save(photoForm);
    }


    public boolean deleteById(Integer id){
        photoRepo.findById(id).orElseThrow(() -> new PhotoNotFoundException((Integer.toString(id))));
        try {
            photoRepo.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
