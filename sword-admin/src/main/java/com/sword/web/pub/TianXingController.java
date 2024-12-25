package com.sword.web.pub;

import com.sword.api.tianxing.TianXingService;
import com.sword.domain.AjaxResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * @version 1.0
 * @date 2024/12/5 16:51
 * @description 调用 天聚数行的接口
 */
@Log4j2
@RestController
@RequestMapping("/pub/tianxing")
public class TianXingController {

    @Autowired
    private TianXingService tianXingService;

    @GetMapping("/rainbow-fart")
    public AjaxResult rainbowFart() {
        try {
            return tianXingService.rainbowFart();
        } catch (Exception e) {
            return AjaxResult.error();
        }
    }

    @GetMapping("/say-love")
    public AjaxResult sayLove() {
        try {
            return tianXingService.sayLove();
        } catch (Exception e) {
            return AjaxResult.error();
        }
    }

    @GetMapping("/tian-gou")
    public AjaxResult tianGou() {
        try {
            return tianXingService.tianGou();
        } catch (Exception e) {
            return AjaxResult.error();
        }
    }

    @GetMapping("/god-reply")
    public AjaxResult godreply() {
        try {
            return tianXingService.godreply();
        } catch (Exception e) {
            return AjaxResult.error();
        }
    }

    @GetMapping("/wang-yi-yun-hot-review")
    public AjaxResult wangYiYunHotReview() {
        try {
            return tianXingService.wangYiYunHotReview();
        } catch (Exception e) {
            return AjaxResult.error();
        }
    }

    @GetMapping("/waste-sorting")
    public AjaxResult wasteSorting(@RequestParam(value = "keyWord", required = false) String keyWord) {
        try {
            return tianXingService.wasteSorting("word", keyWord);
        } catch (Exception e) {
            return AjaxResult.error();
        }
    }

}
