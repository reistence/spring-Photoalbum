package org.lessons.springilmiofotoalbum.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 5, max = 255, message = "Email has to be greater than 5 and less than 255 chars")
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Size(min = 5, max = 20000, message = "Message has to be greater than 5 and less than 255 chars")
    @Column(nullable = false)
    private String message;



    //Constructors
    public Message() {
        super();
    }


    public Message(Integer id, String email, String message) {
        this.id = id;
        this.email = email;
        this.message = message;
    }


    //S/Getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
