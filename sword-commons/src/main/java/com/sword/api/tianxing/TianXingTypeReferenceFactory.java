package com.sword.api.tianxing;

import com.sword.domain.tianxing.TianXingResponse;
import org.springframework.core.ParameterizedTypeReference;

/**
 * @author louis
 * @version 1.0
 * @date 2024/12/24 17:45
 */
public class TianXingTypeReferenceFactory {
    public static <T> ParameterizedTypeReference<TianXingResponse<T>> forType(Class<T> clazz) {
        return new ParameterizedTypeReference<TianXingResponse<T>>() {};
    }
}
