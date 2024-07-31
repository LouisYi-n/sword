package com.icarus.sword.business.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icarus.sword.business.entity.User;
import com.icarus.sword.business.service.IUserService;
import com.icarus.sword.core.page.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/page")
    public Page<User> getUsersByPage(@RequestBody PageRequest pageRequest) {
        return userService.getUserByPage(pageRequest);
    }

    @GetMapping("/page2")
    public Page<User> getUsersByPage2(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        return userService.getUserByPage2(pageNo, pageSize);
    }
}
