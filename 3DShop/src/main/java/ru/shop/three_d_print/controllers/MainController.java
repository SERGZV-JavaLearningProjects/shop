package ru.shop.three_d_print.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.shop.three_d_print.service.PaidOrderService;
import ru.shop.three_d_print.service.UserService;

@Controller
public class MainController
{
    UserService userService;
    PaidOrderService paidOrderService;

    @Autowired
    public MainController(UserService userService, PaidOrderService paidOrderService)
    {
        this.userService = userService;
        this.paidOrderService = paidOrderService;
    }

    @GetMapping
    public String index()
    {
        return "main/main";
    }

    @GetMapping("/test")
    public String test()
    {
//        userService.TestOrder();
        paidOrderService.TestPaidOrder();
        return "main/main";
    }
}
