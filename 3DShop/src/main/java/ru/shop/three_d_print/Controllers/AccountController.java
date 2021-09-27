package ru.shop.three_d_print.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController
{
    @GetMapping
    public String myAccount()
    {
        return "account/my-account";
    }
}