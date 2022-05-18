package ru.shop.three_d_print.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ru.shop.three_d_print.entities.Bundle;
import ru.shop.three_d_print.entities.Product;
import ru.shop.three_d_print.search.Search;
import ru.shop.three_d_print.service.ProductService;

import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController
{
    ProductService productService;

    public ProductController(ProductService productService) { this.productService = productService; }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable Long id, Model model)
    {
        Optional<Product> product = productService.findById(id);
        product.orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Такой продукт не был найден"));

        Search search = new Search();
        List<String> imageNames = search.getDirectoryFileNames("static/images/products/" + id);
        List<String> imageLinks = new ArrayList<>();

        if(imageNames.size() > 0)
        {
            for (String imageName : imageNames)
                imageLinks.add("/static/images/products/" + id + "/" + imageName);
        }
        else imageLinks.add("/static/images/elements/no-image.jpg");

        product.get().setImageLinks(imageLinks);

        Bundle bundle = new Bundle(product.get(), 1);
        model.addAttribute("bundle", bundle);

        return "product/product";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@ModelAttribute Bundle bundle, Model model)
    {
        System.out.println("Метод вызван");

        model.addAttribute("bundle", bundle);
        return "product/product";
    }
}
