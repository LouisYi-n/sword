package com.icarus.sword.watermark;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum WatermarkFormEnum {
    SINGLE(1),
    MULTIPLE(2);

    private int form;

    WatermarkFormEnum(int form) {
        this.form = form;
    }

    @JsonProperty
    public int getForm() {
        return form;
    }

    @JsonCreator
    public static WatermarkFormEnum fromValue(String value) {
        for (WatermarkFormEnum form : values()) {
            if (form.name().equalsIgnoreCase(value)) {
                return form;
            }
        }
        throw new IllegalArgumentException("Unknown enum form " + value);
    }
}
