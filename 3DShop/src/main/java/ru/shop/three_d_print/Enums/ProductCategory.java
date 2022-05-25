package ru.shop.three_d_print.Enums;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public enum ProductCategory
{
    PRINTERS("3d-printers"),
    SCANNERS("3d-scanners"),
    PLASTIC_THREADS("plastic-threads");

    private String category;

    ProductCategory(String category)
    {
        this.category = category;
    }

    public static ProductCategory GetCategoryByArgument(String category)
    {
        Map<String, ProductCategory > enumMap = new HashMap<>();
        enumMap.put(PRINTERS.category, PRINTERS);
        enumMap.put(SCANNERS.category, SCANNERS);
        enumMap.put(PLASTIC_THREADS.category, PLASTIC_THREADS);

        return enumMap.get(category);
    }
}
