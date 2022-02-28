package ru.shop.three_d_print.models;

import java.util.ArrayList;
import java.util.List;

public class Cart
{
    List<Order> orders = new ArrayList<>();

    private double getPurchaseAmount()
    {
        double result = 0;

        for (Order order : orders)
        {
            for (int i = 0; i < order.getQuantity(); i++)
            {
                result += order.getProduct().getPrice();
            }
        }

        return result;
    }
}
