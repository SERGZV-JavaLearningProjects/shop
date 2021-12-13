package ru.shop.three_d_print.сontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.shop.three_d_print.dao.AccountDAO;
import ru.shop.three_d_print.models.Account;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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
    public String accountCreated(@ModelAttribute @Valid Account account, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()) return "account/create";

        List<ObjectError> errors = accountDAO.checkForMatches(account);

        for (ObjectError error : errors)
            bindingResult.rejectValue(error.getObjectName(), "", Objects.requireNonNull(error.getDefaultMessage()));

        if(bindingResult.hasErrors()) return "account/create";

        accountDAO.createAccount(account);
        int id = accountDAO.getLastAccountId();
        model.addAttribute("id", id);
        return "account/created";
    }
}
