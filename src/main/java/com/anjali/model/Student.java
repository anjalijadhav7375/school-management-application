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
    private int rollNum;
    private char section;

    public Student( String firstname,String lastname, int rollNum, char section) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.rollNum = rollNum;
        this.section = section;
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
