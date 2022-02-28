package ru.shop.three_d_print.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaidOrderDAO
{
    private final JdbcTemplate jdbcTemplate;

    public PaidOrderDAO(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }
}
