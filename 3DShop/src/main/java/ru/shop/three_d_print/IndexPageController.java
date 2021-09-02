package ru.shop.three_d_print;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController
{
    @GetMapping
    public String test()
    {
        System.out.println("Дошло");
        return "3d_shop";
    }

    @GetMapping("/3d_shop")
    public String sayHello()
    {
        System.out.println("Дошло");
        return "3d_shop";
    }
}
