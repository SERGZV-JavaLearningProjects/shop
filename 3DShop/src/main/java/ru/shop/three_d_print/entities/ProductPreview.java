package ru.shop.three_d_print.entities;

public class ProductPreview
{
    private final String imageAddress;
    private final String name;
    private final String price;
    private final Long id;

    public ProductPreview(String image, String name, String price, Long id)
    {
        this.imageAddress = image;
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public String getImageAdress() { return imageAddress; }
    public String getName() { return name; }
    public String getPrice() { return price; }
    public Long getId() { return id; }
}
