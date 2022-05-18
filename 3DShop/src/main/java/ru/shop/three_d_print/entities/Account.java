package ru.shop.three_d_print.entities;

public class Account extends User
{
    private String passwordConfirm;
    private String unencryptedPassword;

    public String getPasswordConfirm() { return passwordConfirm; }

    public String getUnencryptedPassword() { return unencryptedPassword; }

    public void setUnencryptedPassword(String unencryptedPassword) { this.unencryptedPassword = unencryptedPassword; }

    public void setPasswordConfirm(String passwordConfirm) { this.passwordConfirm = passwordConfirm; }
}
