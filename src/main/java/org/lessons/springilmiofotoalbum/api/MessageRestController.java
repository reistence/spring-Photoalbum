package org.lessons.springilmiofotoalbum.api;


import jakarta.validation.Valid;
import org.lessons.springilmiofotoalbum.model.Message;
import org.lessons.springilmiofotoalbum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/messages")
public class MessageRestController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message create(@Valid @RequestBody Message message){
        return messageService.createMessage(message);
    }

}
