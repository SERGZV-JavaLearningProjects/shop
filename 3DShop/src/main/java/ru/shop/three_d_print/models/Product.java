package ru.shop.three_d_print.models;

public class Product
{
    private double price;
    private String name;
    private String imageLink;
    private String description;

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImage()
    {
        return imageLink;
    }

    public void setImage(String image)
    {
        this.imageLink = image;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
