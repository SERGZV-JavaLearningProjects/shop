package ru.shop.three_d_print;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController
{
    @GetMapping
    public String test()
    {
        return "index";
    }

    @GetMapping("/3d_shop")
    public String sayHello()
    {
        return "3d_shop";
    }
}
