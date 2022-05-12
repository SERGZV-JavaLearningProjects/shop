package ru.shop.three_d_print.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_order")
public class UserOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderedProduct> orderedProducts;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public List<OrderedProduct> getOrderedProducts() { return orderedProducts; }

    public void setOrderedProducts(List<OrderedProduct> orderedProducts) { this.orderedProducts = orderedProducts; }
}
