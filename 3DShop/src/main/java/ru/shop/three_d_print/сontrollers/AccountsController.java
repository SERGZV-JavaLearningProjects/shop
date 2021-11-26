package ru.shop.three_d_print.сontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shop.three_d_print.dao.AccountDAO;
import ru.shop.three_d_print.models.Account;

import javax.validation.Valid;

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
    public String createAccount(Model model)
    {
        model.addAttribute("account", new Account());
        return "account/create";
    }

    @PostMapping("/created")
    public String accountCreated(@ModelAttribute @Valid Account account, BindingResult bindingResult)
    {
        System.out.println("");
        if(bindingResult.hasErrors()) return "account/create";

        accountDAO.createAccount(account);
        return "account/created";
    }
}
