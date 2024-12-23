package com.sword.api.tianxing;

import com.sword.domain.AjaxResult;
import com.sword.domain.tianxing.SampleContentResult;
import com.sword.domain.tianxing.TianXingResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author louis
 * @version 1.0
 * @date 2024/12/23 17:31
 * @description 天行数据API
 */
@Log4j2
@Component
public class TianXingClient {

    @Autowired
    private TianxingConfig tianxingConfig;

    /**
     * 彩虹屁
     *
     * @return
     */
    public AjaxResult getRainbowFart() {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromHttpUrl(tianxingConfig.getBaseUrl() + tianxingConfig.getRainbowFart())
                .queryParam("key", tianxingConfig.getKey())
                .build().toUri();
        //TianXingResponse<SampleContentResult> response = restTemplate.getForObject(url, TianXingResponse.class);
        ParameterizedTypeReference<TianXingResponse<SampleContentResult>> responseType =
                new ParameterizedTypeReference<TianXingResponse<SampleContentResult>>() {};

        ResponseEntity<TianXingResponse<SampleContentResult>> responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, null, responseType);

        if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            log.error("调用天行数据API失败, responseEntity: {}", responseEntity);
            return null;
        }

        TianXingResponse<SampleContentResult> response = responseEntity.getBody();
        if (response == null) {
            return AjaxResult.error("调用天行数据API失败");
        }
        if (response.getCode() != 200) {
            return AjaxResult.error("调用天行数据API失败, code: " + response.getCode() + ", msg: " + response.getMsg());
        }
        return AjaxResult.success("success", response.getResult().getContent());
    }

}
