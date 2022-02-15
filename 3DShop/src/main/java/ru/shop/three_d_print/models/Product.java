package ru.shop.three_d_print.models;

import java.util.List;

public class Product
{
    private float price;
    private List<String> imageLinks;
    private String name;
    private String description;
    private int quantity;

    public double getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<String> getImages() { return imageLinks; }

    public void setImages(List<String> image) { this.imageLinks = image; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
