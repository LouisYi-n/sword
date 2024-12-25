package com.sword.api.tianxing;

import com.sword.api.tianxing.enums.TianxingEndpointKey;
import com.sword.domain.AjaxResult;
import com.sword.domain.tianxing.TianXingResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author louis
 * @version 1.0
 * @date 2024/12/23 17:31
 * @description 天行数据API
 */
@Log4j2
@Component
public class TianXingClient extends AbstractTianXingClient {


    public <T> AjaxResult get(TianxingEndpointKey endpointKey, ParameterizedTypeReference<TianXingResponse<T>> responseType) {
        return wrapResponse(sendRequest(endpointKey, null, null, responseType));
    }

    public <T> AjaxResult get(TianxingEndpointKey endpointKey, String paramName, String queryParam, ParameterizedTypeReference<TianXingResponse<T>> responseType) {
        return wrapResponse(sendRequest(endpointKey, paramName, queryParam, responseType));
    }

    private <T> AjaxResult wrapResponse(ResponseEntity<TianXingResponse<T>> responseEntity) {
        TianXingResponse<T> body = responseEntity.getBody();
        if (body != null) {
            return new AjaxResult(body.getCode(), body.getMsg(), body.getResult());
        } else {
            return AjaxResult.error("No response from server");
        }
    }
}
