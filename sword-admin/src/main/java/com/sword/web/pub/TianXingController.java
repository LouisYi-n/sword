package com.sword.web.pub;

import com.sword.api.tianxing.TianXingClient;
import com.sword.domain.AjaxResult;
import com.sword.domain.tianxing.SampleContentResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private TianXingClient tianXingClient;

    @GetMapping("/rainbow-fart")
    public AjaxResult getRainbowFart() {
        try {
            return tianXingClient.getRainbowFart();
        } catch (Exception e) {
            log.error("调用天行数据彩虹屁API失败", e);
            return AjaxResult.error();
        }

    }

}
