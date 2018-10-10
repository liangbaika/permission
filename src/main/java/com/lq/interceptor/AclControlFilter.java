package com.lq.interceptor;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.lq.enums.ErrorCode;
import com.lq.exception.PermissionException;
import com.lq.model.SysUserModel;
import com.lq.service.impl.SysCoreService;
import com.lq.utils.ApplicationContextHelper;
import com.lq.utils.LoginHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @Auther: LQ
 * @Date: 2018/10/10 22:01
 * @Description:权限拦截
 */
@Slf4j
public class AclControlFilter implements Filter {

    private static Set<String> includeUrlsSet = Sets.newConcurrentHashSet();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //不需拦截的url集
        String includeUrls = filterConfig.getInitParameter("includeUrls");
        List<String> includeUrlsList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(includeUrls);
        includeUrlsSet = Sets.newConcurrentHashSet(includeUrlsList);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getServletPath();
        log.debug("the visit  url is " + url);
        if (includeUrlsSet.contains(url)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        SysUserModel user = LoginHolder.getUser();
        if (user == null) {
            doNoAuth(request, response);
            return;
        }
        //注意 这里的filter没让spring管理 只能从applicationcontext里取出
        SysCoreService sysCoreService = ApplicationContextHelper.popBean(SysCoreService.class);
        Boolean hasAuth = sysCoreService.hasAclOfThisUrl(url);
        if (!hasAuth) {
            doNoAuth(request, response);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    private void doNoAuth(HttpServletRequest request, HttpServletResponse response) {
        //可以跳转无权限页面 或者直接给出无权限信息 以json串返回
        log.debug("no  auth");
        throw new PermissionException(ErrorCode.NO_AUTH.getMsg());
    }

    @Override
    public void destroy() {
        includeUrlsSet = null;
    }
}
