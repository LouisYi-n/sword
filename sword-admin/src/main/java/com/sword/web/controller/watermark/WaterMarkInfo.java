package com.sword.web.controller.watermark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaterMarkInfo implements Serializable {
    // 水印文字
    private String text;
    // font size
    private Float fontSize;
    // 透明度 （0 到 1 之间的浮点数）
    private Float opacity;
    // 旋转角度
    private String rotation;
    // 字体颜色 #C0C0C0
    private String colourRgb;
    // 图片还是文字
    private WatermarkTypeEnum type;
    // 单行还是多行
    private WatermarkFormEnum form;

    private String imageUrl;
    // 图片相对于其原始大小的比例
    private Float scale=0.1F;
}
