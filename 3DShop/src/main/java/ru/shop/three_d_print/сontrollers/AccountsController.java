package ru.shop.three_d_print.сontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shop.three_d_print.dao.AccountDAO;
import ru.shop.three_d_print.models.Account;

import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountsController
{
    private AccountDAO accountDAO;

    @Autowired
    public AccountsController(AccountDAO accountDAO) { this.accountDAO = accountDAO; }

    @GetMapping("/{id}")
    public String showAccount(@PathVariable int id, Model model)
    {
        model.addAttribute("account", accountDAO.getAccount(id));
        return "account/show";
    }

    @GetMapping("/new")
    public String createAccount()
    {
        return "account/create";
    }

    @PostMapping("/created")
    public String accountCreated(@RequestParam Map<String, Object> params)
    {
        System.out.println("Account created");

        Account account = new Account
        (
            (String)params.get("fname"),
            (String)params.get("mname"),
            (String)params.get("lname"),
            Integer.parseInt((String)params.get("age")),
            (String)params.get("sex"),
            (String)params.get("email"),
            (String)params.get("login"),
            (String)params.get("password")
        );

        accountDAO.createAccount(account);
        return "account/created";
    }
}
