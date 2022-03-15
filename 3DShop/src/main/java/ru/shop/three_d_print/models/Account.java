package ru.shop.three_d_print.models;

import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Account
{
    private int id;
    @Size(min = 2, max = 40, message = "Name length must be between 2 and 40 letters")
    private String firstName;
    private String middleName;
    @Size(min = 2, max = 40, message = "Last Name length must be between 2 and 40 letters")
    private String lastName;
    @Range(min = 0, max = 130, message = "Age must be between 0 and 130")
    private int age;
    private String sex;
    @NotEmpty(message = "The email field must not be empty")
    @Email(message = "Email must be valid")
    private String email;
    @Size(min = 4, max = 40, message = "Nickname length must be between 4 and 40 letters")
    private String login;
    @Size(min = 8, max = 40, message = "Password length must be between 8 and 40 letters")
    private String password;

    private Order order;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }

    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
