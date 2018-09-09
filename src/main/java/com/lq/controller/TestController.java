package com.lq.controller;

import com.lq.entity.SysAclModule;
import com.lq.entity.SysUser;
import com.lq.factory.ResponseEnvelopFactory;
import com.lq.repository.SysAclModuleRepository;
import com.lq.repository.SysUserRepository;
import com.lq.utils.ApplicationContextHelper;
import com.lq.utils.JsonMapper;
import com.lq.utils.ParamValidator;
import com.lq.vo.ResponseEnvelope;
import com.lq.vo.TestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {


    @GetMapping("/hello.json")
    public String test() {
        return "test  hi  sucess";
    }

    @GetMapping("/validate.json")
    public ResponseEnvelope testParam(TestVO vo) {
        log.info("validating... vo {}", vo);
        SysUserRepository sysAclModuleRepository = ApplicationContextHelper.popBean(SysUserRepository.class);
        SysUser sysUser = sysAclModuleRepository.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(sysUser));
//        ParamValidator.check(vo);
        return ResponseEnvelopFactory.success("success");
    }


}
