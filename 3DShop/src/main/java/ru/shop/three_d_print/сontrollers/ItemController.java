package ru.shop.three_d_print.сontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shop.three_d_print.models.Product;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ItemController
{
    @GetMapping("/{id}")
    public String showItem(@PathVariable int id, Model model)
    {
        Product product = new Product();
        product.setName("ABS plastic 1,75 SEM blue");
        product.setDescription
        (
            "The domestic trademark SEM presents to your attention a classic plastic for 3D printing" +
            " - ABS plastic 1.75 SEM blue. This is a practical and affordable material that" +
            " can be used to print various designs, body parts, souvenirs and medical supplies," +
            " car parts and various devices.\n" +
            "\n" +
            "This plastic is a universal consumable material that can be used in almost all 3D" +
            " printers that print using FDM technology, both expensive and quite budget models.\n" +
            "\n" +
            "The blue ABS plastic SEM is characterized by high impact and heat resistance."
        );

        product.setPrice(19.25f);

        List<String> imageLinks = new ArrayList<>();
        imageLinks.add("/resources/static/images/products/1/1.jpg");
        imageLinks.add("/resources/static/images/products/1/korobka.png");
        imageLinks.add("/resources/static/images/products/1/primer-plastika-sem-cvet-sinij.jpg");

        product.setImages(imageLinks);
        product.setQuantity(1);

        model.addAttribute("product", product);
        return "product/product";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@ModelAttribute Product product, Model model)
    {
        System.out.println("Метод вызван");

        model.addAttribute("product", product);
        return "product/product";
    }
}
