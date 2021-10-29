package ru.shop.three_d_print.models;

import java.util.ArrayList;
import java.util.List;

public class Cart
{
    List<Product> products = new ArrayList<>();

    private double getPurchaseAmount()
    {
        double result = 0;

        for (Product product : products) { result += product.getPrice(); }

        return result;
    }
}
