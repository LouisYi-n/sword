package com.sword.api.tianxing;

import com.sword.api.tianxing.enums.TianxingEndpointKey;
import com.sword.domain.AjaxResult;
import com.sword.domain.tianxing.ResourceResult;
import com.sword.domain.tianxing.SampleContentResult;
import com.sword.domain.tianxing.TitleResult;
import com.sword.domain.tianxing.WasteSortingResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author louis
 * @version 1.0
 * @description 天行数据API
 * @date 2024/12/24 17:46
 */
@Log4j2
@Component
public class TianXingService {

    @Autowired
    private TianXingClient tianXingClient;

    public AjaxResult rainbowFart() {
        try {
            return tianXingClient.get(TianxingEndpointKey.RAINBOW_FART,
                TianXingTypeReferenceFactory.forType(SampleContentResult.class));
        } catch (Exception e) {
            log.error("调用彩虹屁API失败", e);
            return AjaxResult.error();
        }
    }

    public AjaxResult sayLove() {
        try {
            return tianXingClient.get(TianxingEndpointKey.SAY_LOVE,
                TianXingTypeReferenceFactory.forType(SampleContentResult.class));
        } catch (Exception e) {
            log.error("调用土味情话API失败", e);
            return AjaxResult.error();
        }
    }

    public AjaxResult tianGou() {
        try {
            return tianXingClient.get(TianxingEndpointKey.TIAN_GOU,
                TianXingTypeReferenceFactory.forType(SampleContentResult.class));
        } catch (Exception e) {
            log.error("调用舔狗API失败", e);
            return AjaxResult.error();
        }
    }

    public AjaxResult godreply() {
        try {
            return tianXingClient.get(TianxingEndpointKey.GOD_REPLY,
                TianXingTypeReferenceFactory.forType(TitleResult.class));
        } catch (Exception e) {
            log.error("调用神回复API失败", e);
            return AjaxResult.error();
        }
    }

    public AjaxResult wangYiYunHotReview() {
        try {
            return tianXingClient.get(TianxingEndpointKey.WANG_YI_YUN_HOT_REVIEW,
                TianXingTypeReferenceFactory.forType(ResourceResult.class));
        } catch (Exception e) {
            log.error("调用网易云热评API失败", e);
            return AjaxResult.error();
        }
    }

    public AjaxResult wasteSorting(String paramName, String queryParam) {
        try {
            return tianXingClient.get(TianxingEndpointKey.WASTE_SORTING, paramName, queryParam,
                TianXingTypeReferenceFactory.forType(WasteSortingResult.class));
        } catch (Exception e) {
            log.error("调用垃圾分类API失败", e);
            return AjaxResult.error();
        }
    }
}
