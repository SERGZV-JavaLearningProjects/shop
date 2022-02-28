package ru.shop.three_d_print.models;

import java.util.List;

public class Product
{
    private int id;
    private String name;
    private String color;
    private float price;
    private String description;
    private List<String> imageLinks;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public double getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<String> getImages() { return imageLinks; }

    public void setImages(List<String> image) { this.imageLinks = image; }
}
