package ru.shop.three_d_print.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController
{
    @GetMapping
    public String index()
    {
        return "main/main";
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String test()
    {
        return "main/main";
    }
}
