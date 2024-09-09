package com.sword.web.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sword.page.PageRequest;
import com.sword.system.entity.User;
import com.sword.system.service.IUserService;
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

    @GetMapping("/{id}")
    public User get(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
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
