package com.sword.web.controller.mock;

import com.sword.domain.AjaxResult;
import com.sword.domain.model.LoginBody;
import com.sword.utils.MessageUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * @version 1.0
 * @date 2025/3/24 16:24
 */
@RestController
@RequestMapping("/test/mock")
public class MockController {

    @GetMapping("no-fish")
    public AjaxResult mockNoFish() {
        return AjaxResult.success("常空军");
    }

    @GetMapping("sun-shine")
    public AjaxResult mockFisher() {
        return AjaxResult.success("经得晒");
    }

}
