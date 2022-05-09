package ru.shop.three_d_print.entities;

import javax.persistence.*;

@Entity
@Table(name = "t_order")
public class Order
{
    @Id
    private Long id;
    @OneToOne
    private Product product;
    private int productsCount;
}
