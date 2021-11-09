package ru.shop.three_d_print.dao;

import org.springframework.stereotype.Component;
import ru.shop.three_d_print.models.Account;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO
{
    private static int ACCOUNT_ID = 0;
    private List<Account> accounts = new ArrayList<>();
//  jdbc:postgresql://localhost:5432/three_d_shop_db

    {
        accounts.add(new Account(++ACCOUNT_ID, "Bob"));
        accounts.add(new Account(++ACCOUNT_ID, "Greg"));
        accounts.add(new Account(++ACCOUNT_ID, "Tom"));
        accounts.add(new Account(++ACCOUNT_ID, "Katy"));
    }

    public Account GetAccount(int id)
    {
        return accounts.stream().filter(account -> account.getId() == id).findFirst().orElse(null);
    }
}
