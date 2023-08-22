package com.codespark.springbootresthateoas.mappings.many_to_may_uni;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    private int id;

    private String title;

}
