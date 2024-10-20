package com.sword.web.controller.i18n;

import com.sword.domain.AjaxResult;
import com.sword.domain.model.LoginBody;
import com.sword.utils.MessageUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;

/**
 * @author louis
 * @version 1.0
 * @date 2024/10/17 15:46
 */
@RestController
@RequestMapping("/test/i18n")
@Validated
public class I18nTestController {

    @PostMapping("login")
    public AjaxResult login(@Validated @RequestBody LoginBody loginBody) {
        return AjaxResult.success(MessageUtils.message("common.success"));
    }

    @GetMapping
    public AjaxResult getInfo(@RequestParam("id") @Max(value = 100, message = "{id_length.limit}") Integer id) {
        return AjaxResult.success();
    }
}
