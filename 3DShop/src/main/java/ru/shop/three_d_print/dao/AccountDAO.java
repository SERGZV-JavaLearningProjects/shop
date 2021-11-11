package ru.shop.three_d_print.dao;

import org.springframework.stereotype.Component;
import ru.shop.three_d_print.models.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO
{
    private static int ACCOUNT_ID = 0;

//  jdbc:postgresql://localhost:5432/three_d_shop_db
    private static final String URL = "jdbc:postgresql://localhost:5432/three_d_shop_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "666999Qw";

    private static Connection connection;

    public AccountDAO()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        try
        {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public Account GetAccount(int id)
    {
        Account account = new Account();

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM account";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next())
            {
                if(resultSet.getInt("id") == id)
                {
                    account.setId(id);
                    account.setFirstName(resultSet.getString("fname"));
                    account.setMiddleName(resultSet.getString("mname"));
                    account.setLastName(resultSet.getString("lname"));
                    account.setAge(resultSet.getInt("age"));
                    account.setGender(resultSet.getString("gender"));
                    account.setEmail(resultSet.getString("email"));
                }
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return account;
    }
}
