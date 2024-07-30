package com.icarus.sword.business.controller;

import com.icarus.sword.business.entity.User;
import com.icarus.sword.business.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.list();
    }
}
