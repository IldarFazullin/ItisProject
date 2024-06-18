package com.example.ItisProject.controller;

import com.example.ItisProject.entity.User;
import com.example.ItisProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('secure')")
@RequestMapping("allUsers")
public class UsersListController {
    @Autowired
    private UserService userService;
    private User user;

    @GetMapping
    public String findAllUsers(Model model) {
        List<User> users = userService.findAllUsersInDatabase();
        model.addAttribute("users", users);
        return "admin/allUsers";
    }
}
