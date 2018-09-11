package com.lq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: LQ
 * @Date: 2018/9/11 23:30
 * @Description:页面路由
 */
@Controller
public class RouteController {


    @RequestMapping("")
    public String toIndex() {
        return "index";
    }

}
