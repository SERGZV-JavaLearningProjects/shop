package ru.shop.three_d_print.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shop.three_d_print.service.UserOrderService;
import ru.shop.three_d_print.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController
{
    private final UserOrderService userOrderService;

    @Autowired
    public CartController( UserOrderService userOrderService)
    {
        this.userOrderService = userOrderService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getCart(Model model)
    {
        model.addAttribute("userOrder", userOrderService.getCurrentUserOrder());
        return "cart/cart";
    }

    @DeleteMapping("/delete-bundle")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String deleteBundle(@RequestParam Long currentBundleProductId, Model model)
    {
        // If we have not deleted anything and use some kind of Logger, we write an error there
        userOrderService.deleteBundleFromUserCart(currentBundleProductId);

        model.addAttribute("userOrder", userOrderService.getCurrentUserOrder());
        return "cart/cart";
    }
}
