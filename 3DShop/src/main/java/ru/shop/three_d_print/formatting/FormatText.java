package ru.shop.three_d_print.formatting;

public class FormatText
{
    public static String formatIntToViewPrice(int price)
    {
        var priceString = Integer.toString(price);
        StringBuilder sb = new StringBuilder(priceString);
        for (int i = sb.length() - 3; i > 0; i -= 3)
        {
            sb.insert(i, ' ');
        }

        return sb.toString() + "₽";
    }
}
