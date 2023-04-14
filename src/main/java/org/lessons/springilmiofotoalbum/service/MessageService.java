package org.lessons.springilmiofotoalbum.service;

import org.lessons.springilmiofotoalbum.exceptions.MessageNotFoundException;
import org.lessons.springilmiofotoalbum.model.Message;
import org.lessons.springilmiofotoalbum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepo;


    public List<Message> getAllMessages(){
        return messageRepo.findAll(Sort.by("email"));

    }

    public Message getById(Integer id) throws MessageNotFoundException {

        Optional<Message> res = messageRepo.findById(id);
        if (res.isPresent()){
            return res.get();
        } else {
            throw new MessageNotFoundException(Integer.toString(id));
        }

    }

    public Message createMessage(Message formMessage){

        Message messageToSave = new Message();

        messageToSave.setEmail(formMessage.getEmail());
        messageToSave.setMessage(formMessage.getMessage());
        return messageRepo.save(messageToSave);
    }


}
