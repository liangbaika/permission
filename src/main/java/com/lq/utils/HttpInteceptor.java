package com.lq.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Component
public class HttpInteceptor implements HandlerInterceptor {
    private static final String START_TIME = "start_time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURL().toString();
        Map parameterMap = request.getParameterMap();
        String paramStrings = JsonMapper.obj2String(parameterMap);
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME, start);
        log.info("request start: url {}  parameterMap {}  handler {}", requestUrl, paramStrings, handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long start = (Long) request.getAttribute(START_TIME);
        Long now = System.currentTimeMillis();
        log.info("request complete:  cost {} millis  ", now - start);
    }
}
