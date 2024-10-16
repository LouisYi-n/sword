

package com.sword.config.i18n;

import com.sword.constant.Constants;
import com.sword.utils.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author louis
 * @version 1.0
 * @date 2024/10/16 16:19
 */
public class I18NLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获取请求中的语言参数
        String language = httpServletRequest.getHeader("Lang");
        //如果没有就使用默认的（根据主机的语言环境生成一个 Locale ）。
        //Locale locale = Locale.getDefault();
        //默认为中文
        Locale locale = Constants.DEFAULT_LOCALE;
        //如果请求的链接中携带了 国际化的参数
        if (StringUtils.hasText(language)) {
            //国家，地区
            locale = new Locale(language);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
    }
}