package com.lq.interceptor;

import com.lq.entity.SysUser;
import com.lq.model.SysUserModel;
import com.lq.utils.LoginHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: LQ
 * @Date: 2018/9/16 20:50
 * @Description:
 */
@Slf4j
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        SysUserModel user = (SysUserModel) request.getSession().getAttribute("user");
        if (user != null) {
            LoginHolder.addUser(user);
            LoginHolder.addRequest(request);
            log.info("add user and  request  to  LoginHolder success");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
