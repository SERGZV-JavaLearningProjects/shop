package ru.shop.three_d_print.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shop.three_d_print.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController
{
    private final UserService userService;

    public CartController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getCart(Model model)
    {
        model.addAttribute("userOrder", userService.getOrder());
        return "cart/cart";
    }

    @DeleteMapping("/delete-bundle")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String deleteBundle()
    {
        System.out.println("Сюда пришло");
        return "";
    }
}
