package com.example.springlearn.domain;


import lombok.Value;

import java.io.Serializable;

@Value
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;

    String id;
    String name;
    Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
