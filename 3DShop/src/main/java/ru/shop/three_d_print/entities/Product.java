package ru.shop.three_d_print.entities;

import ru.shop.three_d_print.enums.ProductCategory;
import ru.shop.three_d_print.formatting.FormatText;
import ru.shop.three_d_print.search.Search;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private ProductCategory category;
    private String color;
    private int price;
    private String description;
    @Transient
    private List<String> imageLinks = new ArrayList<>();;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public int getPrice() { return price; }

    public String getViewPrice() { return FormatText.formatIntToViewPrice(price); }

    public void setPrice(int price) { this.price = price; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<String> getImageLinks() { return imageLinks; }

    public void setImageLinks(List<String> imageLinks) { this.imageLinks = imageLinks; }

    public void loadImageLinks()
    {
        Search search = new Search();
        List<String> imageNames = search.getDirectoryFileNames("static/images/products/" + id);

        if(imageNames.size() > 0)
        {
            for (String imageName : imageNames)
                imageLinks.add("/static/images/products/" + id + "/" + imageName);
        }
        else imageLinks.add("/static/images/elements/no-image.jpg");
    }
}
