package com.sword.web.controller.system;

import com.sword.core.controller.BaseController;
import com.sword.domain.AjaxResult;
import com.sword.domain.entity.SysUser;
import com.sword.system.service.ISysUserService;
import com.sword.utils.SecurityUtils;
import com.sword.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class SysUserController extends BaseController {

    private ISysUserService userService;

    @GetMapping("/{id}")
    public SysUser get(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    /**
     * 新增用户
     */
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUser user) {
        //deptService.checkDeptDataScope(user.getDeptId());
        //roleService.checkRoleDataScope(user.getRoleIds());
        if (!userService.checkUserNameUnique(user)) {
            return error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (StringUtils.isNotEmpty(user.getPhoneNumber()) && !userService.checkPhoneUnique(user)) {
            return error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(user)) {
            return error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }
}
