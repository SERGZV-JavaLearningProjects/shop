package ru.shop.three_d_print.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderDAO
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDAO(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }
}
