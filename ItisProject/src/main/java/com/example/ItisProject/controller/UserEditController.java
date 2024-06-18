package com.example.ItisProject.controller;

import com.example.ItisProject.entity.User;
import com.example.ItisProject.exception.ClientException;
import com.example.ItisProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAuthority('unsecure')")
@RequestMapping("edit")
public class UserEditController {

    @Autowired
    private UserService userService;

    private User user;
    @GetMapping("{id}")
    public String findUser(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userService.findOneUserInDatabase(id));
        return "userEdit";
    }
    @PostMapping
    public String updateUser(@ModelAttribute("user") User user) throws ClientException {
        this.user = user;
        userService.updateUserInDatabase(user);

        return "redirect:/account";
    }



//    @PostMapping
//    public String updateUser(@ModelAttribute("user") User user) throws ClientException {
//        this.user = user;
//        userService.updateUserInDatabase(user);
//        return "redirect:/account";
//    }
}
