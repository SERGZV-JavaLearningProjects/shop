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
import ru.shop.three_d_print.entities.User;
import ru.shop.three_d_print.service.UserService;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/login")
    public String getLogin()
    {
        return "account/login";
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
        model.addAttribute("user", userService.newUser());
        return "account/create";
    }

    @PostMapping("/created")
    public String getCreated(@ModelAttribute @Valid User user, BindingResult bindingResult, HttpServletRequest request)
    {
        if(bindingResult.hasErrors()) return "account/create";

        var errors = userService.trySaveUser(user);
        for(ObjectError error : errors)
            bindingResult.rejectValue(error.getObjectName(), "", Objects.requireNonNull(error.getDefaultMessage()));
        if(bindingResult.hasErrors()) return "account/create";

        authenticateUserAndSetSession(user, request);

        return "account/created";
    }

    private void authenticateUserAndSetSession(User user, HttpServletRequest request)
    {
        String username = user.getUsername();
        String password = user.getUnencryptedPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}

