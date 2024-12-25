package com.sword.api.tianxing.enums;

/**
 * 天行数据API接口，每新增一个接口，都需要在这里添加一个枚举，对应的值为接口的名称
 * @author louis
 * @version 1.0
 * @date 2024/12/24 17:07
 */
public enum TianxingEndpointKey {

    RAINBOW_FART, // 彩虹屁
    SAY_LOVE, // 土味情话
    TIAN_GOU, // 舔狗日记
    GOD_REPLY, // 神回复
    WANG_YI_YUN_HOT_REVIEW, // 网易云热评
    WASTE_SORTING, // 垃圾分类
    ;

    public String getEndpoint() {
        return this.name().toLowerCase();
    }
}
