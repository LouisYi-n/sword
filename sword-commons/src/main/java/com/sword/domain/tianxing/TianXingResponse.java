package com.sword.domain.tianxing;

import lombok.Data;

import java.io.Serializable;

/**
 * @author louis
 * @version 1.0
 * @date 2024/12/23 17:25
 */
@Data
public class TianXingResponse<T> implements Serializable {
    private Integer code;
    private String msg;
    private T result;
}
