package ru.shop.three_d_print.entities;

public class ProductPreview
{
    private final String imageAddress;
    private final String name;
    private final float price;

    public ProductPreview(String image, String name, float price)
    {
        this.imageAddress = image;
        this.name = name;
        this.price = price;
    }

    public String getImageAdress() { return imageAddress; }
    public String getName() { return name; }
    public float getPrice() { return price; }
}
