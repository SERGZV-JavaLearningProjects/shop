package ru.shop.three_d_print.entities;

/**
 * This class is a wrapper around the product class and stores the number of products of the same type.
 */
public class Bundle
{
    private Product product;
    private int quantity;

    public Bundle(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    public String getName() { return product.getName(); }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
