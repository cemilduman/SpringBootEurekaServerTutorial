package com.duman.department.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Department {

    @Id
    private long id;

    private String name;

    @Column(name = "city_id")
    private long cityId;
}
