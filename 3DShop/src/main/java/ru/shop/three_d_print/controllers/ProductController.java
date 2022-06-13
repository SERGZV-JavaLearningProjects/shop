package ru.shop.three_d_print.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ru.shop.three_d_print.enums.ProductCategory;
import ru.shop.three_d_print.entities.Bundle;
import ru.shop.three_d_print.entities.Product;
import ru.shop.three_d_print.entities.ProductPreview;
import ru.shop.three_d_print.search.Search;
import ru.shop.three_d_print.service.ProductService;
import ru.shop.three_d_print.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController
{
    UserService userService;
    ProductService productService;

    public ProductController(UserService userService, ProductService productService)
    {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/category/{category}")
    public String getCategory(@PathVariable String category, Model model)
    {
        var enumCategory = ProductCategory.getCategoryByArgument(category);
        List<Product> products = productService.findAllWithCategory(enumCategory);

        Search search = new Search();
        List<ProductPreview> previews = new ArrayList<>();
        for (var product : products)
        {
            String imageName = search.getFirstFileNameInDirectory("static/images/products/" + product.getId());
            String address = "/static/images/products/" + product.getId() + "/" + imageName;
            ProductPreview preview = new ProductPreview(address, product.getName(), product.getViewPrice(), product.getId());
            previews.add(preview);
        }

        model.addAttribute("categoryName", enumCategory.getViewName());
        model.addAttribute("previews", previews);

        return "product/category";
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable Long id, Model model)
    {
        Bundle bundle = buildBundle(id);
        model.addAttribute("bundle", bundle);
        model.addAttribute("openModal", false);

        return "product/product";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addToCart(@PathVariable Long id, @RequestParam int quantity, Model model)
    {
        Bundle bundle = buildBundle(id);
        bundle.setQuantity(quantity);
        model.addAttribute("bundle", bundle);
        model.addAttribute("openModal", true);

        userService.addOrder(bundle);

        return "product/product";
    }

    private Bundle buildBundle(Long productId)
    {
        Optional<Product> product = productService.findById(productId);
        product.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Такой продукт не был найден"));

        Search search = new Search();
        List<String> imageNames = search.getDirectoryFileNames("static/images/products/" + productId);
        List<String> imageLinks = new ArrayList<>();

        if(imageNames.size() > 0)
        {
            for (String imageName : imageNames)
                imageLinks.add("/static/images/products/" + productId + "/" + imageName);
        }
        else imageLinks.add("/static/images/elements/no-image.jpg");

        product.get().setImageLinks(imageLinks);

        return new Bundle(product.get(), 1);
    }
}
