package ru.shop.three_d_print.entities;

import javax.persistence.*;

@Entity
@Table(name = "t_paid_bundle")
public class PaidBundle
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paid_order_id")
    PaidOrder paidOrder;
    @OneToOne
    private Product product;
    private int quantity;
    private boolean delivered;

    public PaidBundle(){}

    public PaidBundle(PaidOrder paidOrder, Bundle bundle)
    {
        this.paidOrder = paidOrder;
        product = bundle.getProduct();
        quantity = bundle.getQuantity();
    }

    public PaidBundle(PaidOrder paidOrder, Product product, int quantity)
    {
        this.paidOrder = paidOrder;
        this.product = product;
        this.quantity = quantity;
    }

    public String getName() { return product.getName(); }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
