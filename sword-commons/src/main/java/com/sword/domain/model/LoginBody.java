package com.sword.domain.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录对象
 *
 * @author louis
 * @version 1.0
 * @date 2024/9/9 17:57
 */
public class LoginBody {

    @NotBlank(message = "{use.name.can.not.be.empty}")
    private String username;

    @NotBlank(message = "{password.can.not.be.empty}")
    @Length(min = 5, max = 20, message = "{password.length.limit}")
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
