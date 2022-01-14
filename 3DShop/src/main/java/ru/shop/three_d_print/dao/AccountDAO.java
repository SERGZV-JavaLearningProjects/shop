package ru.shop.three_d_print.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import ru.shop.three_d_print.models.Account;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDAO(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<ObjectError> checkForMatches(Account newAccount)
    {
        List<ObjectError> errors = new ArrayList();
        boolean emailError = false;
        boolean loginError = false;

        String SQL = "SELECT * FROM account WHERE email = ? OR login = ?";
        String email = newAccount.getEmail();
        String login = newAccount.getLogin();

        List<Account> accounts = jdbcTemplate.query(SQL, new Object[]{email, login}, new AccountMapper());
        for (Account dbAccount : accounts)
        {
            if(dbAccount.getEmail().equals(email)) emailError = true;
            if(dbAccount.getLogin().equals(login)) loginError = true;
            if(emailError && loginError) break;
        }

        if(emailError) errors.add(new ObjectError("email", "Email " + email + " already exists"));
        if(loginError) errors.add(new ObjectError("login", "Login " + login + " already exists"));

        return errors;
    }

    public void createAccount(Account account)
    {
        String SQL = "INSERT INTO account(fname, mname, lname, age, sex, email, login, password) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update
        (
            SQL,
            account.getFirstName(),
            account.getMiddleName(),
            account.getLastName(),
            account.getAge(),
            account.getSex(),
            account.getEmail(),
            account.getLogin(),
            account.getPassword()
        );
    }

    public int getLastAccountId()
    {
        String SQL = "SELECT * FROM account WHERE id = (SELECT MAX(id) FROM account)";
        List<Account> accounts = jdbcTemplate.query(SQL, new AccountMapper());
        return accounts.get(0).getId();
    }

    public Account getAccount(int id)
    {
        String SQL = "SELECT * FROM account WHERE id=?";
        List<Account> accounts = jdbcTemplate.query(SQL, new Object[]{id}, new AccountMapper());
        Account account = accounts.stream().findAny().orElse(null);
        if (account == null) throw new Error("Person with this id was not found");
        return account;
    }
}
