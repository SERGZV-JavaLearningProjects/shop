package ru.shop.three_d_print.models;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

public class Account
{
    private int id;
    @NotEmpty(message = "The name field must not be empty")
    @Size(min = 2, max = 40, message = "Name length must be between 2 and 40 letters")
    private final String firstName;
    private final String middleName;
    @NotEmpty(message = "The last name field must not be empty")
    private final String lastName;
    @NotEmpty(message = "The age field must not be empty")
    @Range(min = 0, max = 130, message = "Age must be between 0 and 130")
    private final int age;
    @NotEmpty(message = "The sex field must not be empty")
    private final String sex;
    @NotEmpty(message = "The email field must not be empty")
    @Email(message = "Email must be valid")
    private final String email;
    @NotEmpty(message = "The login field must not be empty")
    private final String login;
    @NotEmpty(message = "The password field must not be empty")
    private final String password;

    private Cart cart;

    public Account
    (
        int _id,
        String firstName,
        String middleName,
        String lastName,
        int age,
        String sex,
        String email,
        String login,
        String password
    )
    {
        this(firstName, middleName, lastName, age, sex, email, login, password);
        this.id = _id;
    }

    public Account
    (
        String firstName,
        String middleName,
        String lastName,
        int age,
        String sex,
        String email,
        String login,
        String password
    )
    {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public int getId() { return id; }

    public String getFirstName() { return firstName; }

    public String getMiddleName() { return middleName; }

    public String getLastName() { return lastName; }

    public int getAge() { return age; }

    public String getSex() { return sex; }

    public String getEmail() { return email; }

    public String getLogin() { return login; }

    public String getPassword() { return password; }

    public Cart getCart() { return cart; }

    public void setCart(Cart cart) { this.cart = cart; }
}
