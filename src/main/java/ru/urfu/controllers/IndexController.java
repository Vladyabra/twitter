package ru.urfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.urfu.model.User;
import ru.urfu.storage.UserStorage;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    private UserStorage users;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registrationForm(Model model) {
        model.addAttribute(new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registrationForm(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "register";
        }

        if (users.findUser(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "busy_username", "The username is already taken");
            return "register";
        }

        users.addUser(user);
        return "redirect:/";
    }
}
