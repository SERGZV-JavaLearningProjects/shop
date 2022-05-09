package ru.shop.three_d_print.entities;

import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_user")
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private String username;
    private String password;
    @Transient
    private String passwordConfirm;
    @Transient
    private String unencryptedPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Order> orders;

    public User() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    @Override
    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    @Override
    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getPasswordConfirm() { return passwordConfirm; }

    public String getUnencryptedPassword() { return unencryptedPassword; }

    public void setUnencryptedPassword(String unencryptedPassword) { this.unencryptedPassword = unencryptedPassword; }

    public void setPasswordConfirm(String passwordConfirm) { this.passwordConfirm = passwordConfirm; }

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return getRoles(); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
