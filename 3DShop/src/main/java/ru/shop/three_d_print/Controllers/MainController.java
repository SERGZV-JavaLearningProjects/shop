package ru.shop.three_d_print.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController
{
    @GetMapping
    public String test()
    {
        return "main/index";
    }
}
