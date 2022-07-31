package com.example.springlearn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Taco implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(
            min = 5,
            message = "Name must be at least 5 characters long"
    )
    private String name;

    @NotNull
    @Size(
            min = 1,
            message = "You must choose at least 1 ingredient"
    )
    private List<Ingredient> ingredients;

}
