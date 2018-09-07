package com.lq.exception;

import com.lq.factory.ResponseEnvelopFactory;
import com.lq.vo.ResponseEnvelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class PermissionExceptionResolver implements HandlerExceptionResolver {

    //json请求
    private static final String JSON_SUFFIX = "json";
    //页面请求
    private static final String PAGE_SUFFIX = "page";

    private static final String DEFAULT_ERROR = "System Error";

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String url = request.getRequestURL().toString();
        ModelAndView modelAndView = null;
        ResponseEnvelope fail = null;
        Map<String, Object> parameterMap = request.getParameterMap();
        Set<String> stringSet = parameterMap.keySet();
        StringBuilder paramsStringBuilder = new StringBuilder();
        for (String e : stringSet) {
            Object value = parameterMap.get(e);
            paramsStringBuilder.append("key:" + e + " ---> value:" + value);
        }
        log.error("the handler  is {}  error happened the exception e is {}, the request" +
                " url is {},the request  parmas is {}", handler, ex.getMessage(), url, paramsStringBuilder.toString());
        if (url.endsWith(JSON_SUFFIX)) {
            if (ex instanceof PermissionException) {
                fail = ResponseEnvelopFactory.fail(ex.getMessage());
                modelAndView = new ModelAndView("jsonView", fail.toMap());
            } else {
                fail = ResponseEnvelopFactory.fail(DEFAULT_ERROR + ex.getMessage());
                modelAndView = new ModelAndView("jsonView", fail.toMap());
            }
        } else if (url.endsWith(PAGE_SUFFIX)) {
            fail = ResponseEnvelopFactory.fail(DEFAULT_ERROR + ex.getMessage());
            modelAndView = new ModelAndView("error", fail.toMap());
        } else {
            fail = ResponseEnvelopFactory.fail(DEFAULT_ERROR + ex.getMessage());
            modelAndView = new ModelAndView("error", fail.toMap());
        }
        return modelAndView;
    }
}
