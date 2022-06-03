package ru.shop.three_d_print.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.shop.three_d_print.enums.ProductCategory;
import ru.shop.three_d_print.service.PaidOrderService;
import ru.shop.three_d_print.service.ProductService;
import ru.shop.three_d_print.service.UserService;

@Controller
public class MainController
{
    UserService userService;
    ProductService productService;
    PaidOrderService paidOrderService;

    @Autowired
    public MainController(UserService userService, ProductService productService, PaidOrderService paidOrderService)
    {
        this.userService = userService;
        this.productService = productService;
        this.paidOrderService = paidOrderService;
    }

    @GetMapping
    public String getMain(Model model)
    {
        model.addAttribute("printer", ProductCategory.PRINTER.getViewName());
        model.addAttribute("scanner", ProductCategory.SCANNER.getViewName());
        model.addAttribute("consumable", ProductCategory.CONSUMABLE.getViewName());
        
        return "main/main";
    }

    @GetMapping("/test")
    public String getTestPage()
    {
//        userService.TestOrder();
//        paidOrderService.TestPaidOrder();
//        var test = productService.findAllWithCategory(ProductCategory.CONSUMABLE);
        return "main/main";
    }
}
