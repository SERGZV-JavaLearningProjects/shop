package ru.shop.three_d_print.сontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController
{
    @GetMapping("/{id}")
    public String showItem(@PathVariable int id, Model model)
    {
        List<String> imageLinks = new ArrayList<>();
        imageLinks.add("/resources/static/images/products/1/product_test_image.jpg");
        imageLinks.add("/resources/static/images/products/1/product_test_image2.jpg");
        imageLinks.add("/resources/static/images/products/1/product_test_image3.jpg");
//        imageLinks.add("/resources/static/images/products/1/product_test_image.jpg");
//        imageLinks.add("/resources/static/images/products/1/product_test_image.jpg");
//        imageLinks.add("/resources/static/images/products/1/product_test_image.jpg");
//        imageLinks.add("/resources/static/images/products/1/product_test_image.jpg");
//        imageLinks.add("/resources/static/images/products/1/product_test_image.jpg");
//        imageLinks.add("/resources/static/images/products/1/product_test_image.jpg");
//        imageLinks.add("/resources/static/images/products/1/product_test_image.jpg");
//        imageLinks.add("/resources/static/images/products/1/product_test_image.jpg");

        model.addAttribute("_links", imageLinks);
        return "item/item";
    }

//    @GetMapping("/{test}")
//    public String test()
//    {
//        return "item/test";
//    }
}
