package ru.shop.three_d_print.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    private float price;
    private String description;
    @Transient
    private List<String> imageLinks;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public float getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<String> getImageLinks() { return imageLinks; }

    public void setImageLinks(List<String> imageLinks) { this.imageLinks = imageLinks; }
}
