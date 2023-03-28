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
@Table(name = "CUSTOMER_ORDER")
public class Admision {
    private @Id @GeneratedValue Long id;
    private String description;
    private Status status;

    public Admision(String description, Status status) {
        this.description = description;
        this.status = status;
    }
}
