package ru.shop.three_d_print.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.shop.three_d_print.service.PaidOrderService;
import ru.shop.three_d_print.service.UserOrderService;
import ru.shop.three_d_print.service.UserService;


@Controller
@RequestMapping("/paid-orders")
public class PaidOrdersController
{
    private final UserService userService;
    private final PaidOrderService paidOrderService;
    private final UserOrderService userOrderService;

    @Autowired
    public PaidOrdersController(UserService userService, PaidOrderService paidOrderService, UserOrderService userOrderService)
    {
        this.userService = userService;
        this.paidOrderService = paidOrderService;
        this.userOrderService = userOrderService;
    }

    @PostMapping("/order")
    public String makeAnOrder()
    {
        var user = userService.getCurrentUser();
        paidOrderService.makeAnOrder(user);

        return "paid_orders/show";
    }
}
