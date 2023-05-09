package com.anjali.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Student {
    private @Id
    @GeneratedValue Long id;
    private String firstname;
    private String lastname;
    private String emailId;
    private int rollNum;
    private char section;
    private String username;
    private String password;

    public Student(String firstname, String lastname, String emailId, int rollNum, char section, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailId = emailId;
        this.rollNum = rollNum;
        this.section = section;
        this.username = username;
        this.password = password;
    }

    public String getName(){
        return this.firstname + " " + lastname;
    }
    public void setName(String name){
        String[] parts = name.split(" ");
        this.firstname = parts[0];
        this.lastname = parts[1];
    }
}
