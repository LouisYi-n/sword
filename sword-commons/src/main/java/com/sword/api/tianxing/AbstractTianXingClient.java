package com.sword.api.tianxing;

import com.sword.api.tianxing.enums.TianxingEndpointKey;
import com.sword.domain.tianxing.TianXingResponse;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author louis
 * @version 1.0
 * @date 2024/12/24 16:01
 */
@Log4j2
public abstract class AbstractTianXingClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    protected TianXingConfig tianxingConfig;

    protected URI buildUrl(TianxingEndpointKey endpointKey, String paramName,
        String queryParam) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                tianxingConfig.getBaseUrl() + tianxingConfig.getUrls().get(endpointKey.getEndpoint()))
            .queryParam("key", tianxingConfig.getKey());

        if (paramName != null && queryParam != null) {
            builder.queryParam(paramName, queryParam);
        }
        return builder.build().toUri();
    }

    protected <T> ResponseEntity<TianXingResponse<T>> sendRequest(TianxingEndpointKey endpointKey,
        String paramName, String queryParam,
        ParameterizedTypeReference<TianXingResponse<T>> responseType) {
        URI uri = buildUrl(endpointKey, paramName, queryParam);
        ResponseEntity<TianXingResponse<T>> responseEntity = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            responseType);
        if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            log.error("调用天行数据API失败, responseEntity: {}", responseEntity);
            throw new RuntimeException("调用天行数据API失败");
        }
        return responseEntity;
    }
}
