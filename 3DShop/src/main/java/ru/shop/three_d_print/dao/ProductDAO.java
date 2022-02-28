package ru.shop.three_d_print.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.shop.three_d_print.models.Product;

import java.util.List;

@Component
public class ProductDAO
{
    private final JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Product getProduct(int id)
    {
        String SQL = "SELECT * FROM product WHERE id = ?";
        List<Product> products = jdbcTemplate.query(SQL, new Object[]{id}, new ProductMapper());
        Product product = products.stream().findAny().orElse(null);
        return product;
    }
}
