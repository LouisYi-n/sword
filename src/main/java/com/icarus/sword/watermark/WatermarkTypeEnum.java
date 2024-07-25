package com.icarus.sword.watermark;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author louis
 * @version 1.0
 * @date 2024/7/25 15:40
 */
public enum WatermarkTypeEnum {
    TEXT("text"),
    IMAGE("image");
    private String type;

    WatermarkTypeEnum(String type) {
        this.type = type;
    }

    @JsonProperty
    public String getType() {
        return type;
    }

    @JsonCreator
    public static WatermarkTypeEnum fromValue(String value) {
        for (WatermarkTypeEnum type : values()) {
            if (type.name().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value);
    }
}
