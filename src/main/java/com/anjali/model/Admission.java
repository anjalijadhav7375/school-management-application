package com.anjali.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "Student_Admission")
public class Admission {
    private @Id @GeneratedValue Long id;
    private String studentName;
    private Status status;

    public Admission(String studentName, Status status) {
        this.studentName = studentName;
        this.status = status;
    }
}
