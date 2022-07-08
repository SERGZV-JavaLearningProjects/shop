package ru.shop.three_d_print.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        Bundle bundle = new Bundle(productService.findById(id), 1);
        model.addAttribute("bundle", bundle);
        model.addAttribute("openModalValue", false);

        return "product/product";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addToCart(@PathVariable Long id, @RequestParam int quantity, Model model)
    {
        Bundle bundle = new Bundle(productService.findById(id), quantity);
        model.addAttribute("bundle", bundle);
        model.addAttribute("openModalValue", true);
        userService.addOrder(bundle);

        return "product/product";
    }
}
