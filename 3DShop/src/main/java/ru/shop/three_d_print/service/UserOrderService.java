package ru.shop.three_d_print.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shop.three_d_print.entities.Bundle;
import ru.shop.three_d_print.entities.UserOrder;

@Service
public class UserOrderService
{
    UserService userService;

    @Autowired
    public UserOrderService(UserService userService)
    {
        this.userService = userService;
    }

    public void addBundleToUserCart(Bundle bundle)
    {
        var user = userService.getCurrentUser();
        var order = user.getOrder();

        if (order == null) order = new UserOrder();
        order.addBundle(bundle);
        user.setOrder(order);

        userService.saveUser(user);
    }

    public boolean deleteBundleFromUserCart(Long productId)
    {
        var user = userService.getCurrentUser();

        var bundleRemoved = user.getOrder().deleteBundle(productId);
        userService.saveUser(user);

        return bundleRemoved;
    }

    public UserOrder getCurrentUserOrder()
    {
        var user = userService.getCurrentUser();
        return user.getOrder();
    }
}
