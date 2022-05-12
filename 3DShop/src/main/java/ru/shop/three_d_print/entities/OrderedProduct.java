package ru.shop.three_d_print.entities;

import javax.persistence.*;

@Entity
@Table(name = "t_ordered_product")
public class OrderedProduct
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long row_id;
    @OneToOne
    private Product product;
    private int productsCount;

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    public int getProductsCount() { return productsCount; }

    public void setProductsCount(int productsCount) { this.productsCount = productsCount; }
}
