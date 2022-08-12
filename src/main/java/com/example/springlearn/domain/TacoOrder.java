package com.example.springlearn.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "TACO_ORDERS")
public class TacoOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date placedAt;

    @NotBlank(
            message = "Delivery name is required"
    )
    private String deliveryName;
    @NotBlank(
            message = "Street is required"
    )
    private String deliveryStreet;
    @NotBlank(
            message = "City is required"
    )
    private String deliveryCity;
    @NotBlank(
            message = "State is required"
    )
    private String deliveryState;
    @NotBlank(
            message = "Zip code is required"
    )
    private String deliveryZip;
    @CreditCardNumber(
            message = "Not a valid credit card number"
    )
    private String ccNumber;
    @Pattern(
            regexp = "^(0[1-9]|1[0-2])/([2-9][0-9])$"
    )
    private String ccExpiration;
    @Digits(
            integer = 3,
            fraction = 0,
            message = "Invalid CVV"
    )
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TacoOrder tacoOrder = (TacoOrder) o;
        return id != null && Objects.equals(id, tacoOrder.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
