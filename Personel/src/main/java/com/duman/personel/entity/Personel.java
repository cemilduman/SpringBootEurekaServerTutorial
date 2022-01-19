package com.duman.personel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personel {
    @Id
    private long id;
    private String name;
    private String surname;
    @Column(name = "department_id")
    private long departmentId;
}
