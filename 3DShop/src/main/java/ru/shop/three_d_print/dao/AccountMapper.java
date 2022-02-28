package ru.shop.three_d_print.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.shop.three_d_print.models.Account;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account>
{
    @Override
    public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException
    {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setFirstName(resultSet.getString("fname"));
        account.setMiddleName(resultSet.getString("mname"));
        account.setLastName(resultSet.getString("lname"));
        account.setAge(resultSet.getInt("age"));
        account.setSex(resultSet.getString("sex"));
        account.setEmail(resultSet.getString("email"));
        account.setLogin(resultSet.getString("login"));
        account.setPassword(resultSet.getString("password"));

        return account;
    }
}
