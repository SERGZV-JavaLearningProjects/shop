package ru.shop.three_d_print.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.shop.three_d_print.models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product>
{
    @Override
    public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException
    {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setColor(resultSet.getString("color"));
        product.setPrice(resultSet.getFloat("price"));
        product.setDescription(resultSet.getString("descr"));

        return product;
    }
}
