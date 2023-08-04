package com.example.springbootproj.controller;

import com.example.springbootproj.entity.Reader;
import com.example.springbootproj.entity.security.User;
import com.example.springbootproj.service.ReaderService;
import com.example.springbootproj.service.security.UserService;
import com.example.springbootproj.utils.userFormReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReaderService readerService;
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /*@RequestMapping("/setting")
    public String setting(Model model) {
        User user = userService.getMaxUser();
        Reader reader = new Reader(Math.toIntExact(user.getId()));

        System.out.println("setting:" + user);
        model.addAttribute("reader",reader);
        return "readerInfo";
    }*/
    @GetMapping("/registration")
    public String registration(Model model) {

        model.addAttribute("userForm", new userFormReader(new Reader(),new User()));

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid userFormReader userFormReader, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userFormReader.getUser().getPassword().equals(userFormReader.getUser().getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userFormReader.getUser())){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        User user = userService.getMaxUser();

        userFormReader.getReader().setUser_id(Math.toIntExact(user.getId()));
        readerService.saveReader(userFormReader.getReader());

        user.setReader_id(readerService.getReaderMaxId());
        userService.saveUser(user);
        System.out.println("reg ok");
        return "redirect:/";
    }
}