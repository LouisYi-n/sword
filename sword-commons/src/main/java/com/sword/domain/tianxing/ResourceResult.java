package com.sword.domain.tianxing;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author louis
 * @version 1.0
 * @date 2024/12/24 15:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceResult extends SampleContentResult implements Serializable {
    private String resource;
}
