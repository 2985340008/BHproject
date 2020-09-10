package com.bhsoftware.projectserver.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Table(name = "user")
@Entity
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String username;
    private String password;
    private String phone;
    private  String email;
    private String salt;

}
