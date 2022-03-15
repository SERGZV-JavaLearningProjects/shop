package ru.shop.three_d_print.models;

import java.util.ArrayList;
import java.util.List;

public class Order
{
    private List<Bundle> bundles = new ArrayList<>();

    private double getPurchaseAmount()
    {
        double result = 0;

        for (Bundle bundle : bundles)
        {
            result += bundle.getQuantity() * bundle.getProduct().getPrice();
        }

        return result;
    }

    public List<Bundle> getBundles()
    {
        return bundles;
    }

    public void setBundles(List<Bundle> bundles)
    {
        this.bundles = bundles;
    }
}
