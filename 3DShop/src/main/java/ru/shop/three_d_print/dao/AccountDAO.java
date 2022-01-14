package ru.shop.three_d_print.dao;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import ru.shop.three_d_print.models.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO
{
    private static int ACCOUNT_ID = 0;

    private static final String URL = "jdbc:postgresql://localhost:5432/three_d_shop_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "666999Qw";

    private static Connection connection;

    public AccountDAO()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e)
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

    public List<ObjectError> checkForMatches(Account account)
    {
        List<ObjectError> errors = new ArrayList();
        boolean emailError = false;
        boolean loginError = false;

        try
        {
            String SQL = "SELECT * FROM account WHERE email = ? OR login = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getLogin());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                if(resultSet.getString("email").equals(account.getEmail()))
                    emailError = true;

                if(resultSet.getString("login").equals(account.getLogin()))
                    loginError = true;

                if(emailError && loginError) break;
            }

            if (emailError)
                errors.add(new ObjectError("email", "Email " + account.getEmail() + " already exists"));

            if (loginError)
                errors.add(new ObjectError("login", "Login " + account.getLogin() + " already exists"));
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }

        return errors;
    }

    public void createAccount(Account account)
    {
        try
        {
            String SQL = "INSERT INTO account(fname, mname, lname, age, sex, email, login, password) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, account.getFirstName());
            statement.setString(2, account.getMiddleName());
            statement.setString(3, account.getLastName());
            statement.setInt(4, account.getAge());
            statement.setString(5, account.getSex());
            statement.setString(6, account.getEmail());
            statement.setString(7, account.getLogin());
            statement.setString(8, account.getPassword());
            statement.executeUpdate();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }

    public int getLastAccountId()
    {
        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT MAX(id) FROM account;";
            ResultSet resultSet = statement.executeQuery(SQL);

            if(resultSet.next())
            {
                 int id = resultSet.getInt(1);
                 if(id != 0) return id;
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        return 0;
    }

    public Account getAccount(int id)
    {
        Account account = null;

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM account";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next())
            {
                if(resultSet.getInt("id") == id)
                {
                    account = new Account();
                    account.setId(id);
                    account.setFirstName(resultSet.getString("fname"));
                    account.setMiddleName(resultSet.getString("mname"));
                    account.setLastName(resultSet.getString("lname"));
                    account.setAge(resultSet.getInt("age"));
                    account.setSex(resultSet.getString("sex"));
                    account.setEmail(resultSet.getString("email"));
                    account.setLogin(resultSet.getString("login"));
                    account.setPassword(resultSet.getString("password"));
                }
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }

        return account;
    }
}
