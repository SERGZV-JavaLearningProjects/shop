package ru.shop.three_d_print.Enums;

import java.util.HashMap;
import java.util.Map;

public enum ProductCategory
{
    PRINTER("3d-printer"),
    SCANNER("3d-scanner"),
    CONSUMABLE("consumable");

    private final String workName;

    ProductCategory(String category)
    {
        this.workName = category;
    }

    public String getWorkName() { return workName; }

    public static ProductCategory getCategoryByArgument(String category)
    {
        Map<String, ProductCategory> map = new HashMap<>();
        map.put(PRINTER.workName, PRINTER);
        map.put(SCANNER.workName, SCANNER);
        map.put(CONSUMABLE.workName, CONSUMABLE);

        return map.get(category);
    }

    public String getViewName()
    {
        Map<String, String> map = new HashMap<>();
        map.put(PRINTER.name(), "3D Принтеры");
        map.put(SCANNER.name(), "3D Сканеры");
        map.put(CONSUMABLE.name(), "Расходные материалы");

        return map.get(this.name());
    }

    // This method is needed in case someone wants to swap the enum in places,
    // the same number for this enum remains in the database
    public static int getDatabaseId(ProductCategory category)
    {
        Map<String, Integer> map = new HashMap<>();
        map.put(PRINTER.name(), 0);
        map.put(SCANNER.name(), 1);
        map.put(CONSUMABLE.name(), 2);

        return map.get(category.name());
    }
}
