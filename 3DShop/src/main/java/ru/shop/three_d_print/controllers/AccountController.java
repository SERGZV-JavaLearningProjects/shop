package ru.shop.three_d_print.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.shop.three_d_print.entities.Account;
import ru.shop.three_d_print.entities.User;
import ru.shop.three_d_print.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.Objects;

@Controller
@RequestMapping("/account")
public class AccountController
{
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountController(UserService userService, AuthenticationManager authenticationManager)
    {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/cart")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getCart(Model model)
    {
        var test = userService.getOrder();
        model.addAttribute("userOrder", userService.getOrder());
        return "account/cart";
    }

    @GetMapping("/login")
    public String getLogin() { return "account/login"; }

    @GetMapping("/success-login")
    public String afterLogin(HttpServletRequest request)
    {
        String username = userService.getCurrentUsername();
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        return "redirect:/";
    }

    @GetMapping("/show")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getShow(Model model)
    {
        String username = userService.getCurrentUsername();
        User user = (User)userService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return "account/show";
    }

    @GetMapping("/new")
    public String getCreate(Model model)
    {
        model.addAttribute("account", userService.newAccount());
        return "account/create";
    }

    @PostMapping("/created")
    public String getCreated(@ModelAttribute @Valid Account account, BindingResult bindingResult, HttpServletRequest request)
    {
        if(bindingResult.hasErrors()) return "account/create";

        var errors = userService.trySaveUser(account);
        for(ObjectError error : errors)
            bindingResult.rejectValue(error.getObjectName(), "", Objects.requireNonNull(error.getDefaultMessage()));
        if(bindingResult.hasErrors()) return "account/create";

        authenticateUserAndSetSession(account, request);

        return "account/created";
    }

    private void authenticateUserAndSetSession(Account account, HttpServletRequest request)
    {
        String username = account.getUsername();
        String password = account.getUnencryptedPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}

