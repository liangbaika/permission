package com.lq.controller;

import com.lq.mapping.BeanMapper;
import com.lq.utils.LoginHolder;
import com.lq.vo.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import com.lq.service.SysUserService;
import com.lq.model.SysUserModel;
import com.lq.vo.SysUserVO;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class SysUserRestApiController {

    private final Logger logger = LoggerFactory.getLogger(SysUserRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysUserService sysUserService;

    @GetMapping(value = "/lq/sysUser/{id}/json")
    public ResponseEnvelope<SysUserVO> getSysUserById(@PathVariable Integer id) {
        SysUserModel sysUserModel = sysUserService.findByPrimaryKey(id);
        SysUserVO sysUserVO = beanMapper.map(sysUserModel, SysUserVO.class);
        ResponseEnvelope<SysUserVO> responseEnv = new ResponseEnvelope<>(sysUserVO, true);
        return responseEnv;
    }

    @GetMapping(value = "/lq/sysUser/page")
    public ResponseEnvelope<Page<SysUserModel>> listSysUser(SysUserVO sysUserVO, Pageable pageable) {
        SysUserModel param = beanMapper.map(sysUserVO, SysUserModel.class);
        List<SysUserModel> sysUserModelModels = sysUserService.selectPage(param, pageable);
        long count = sysUserService.selectCount(param);
        SysUserModel user = LoginHolder.getUser();
        if (user != null) {
            System.out.println("  现在的user:" + user.getId());

        }
        Page<SysUserModel> page = new PageImpl<>(sysUserModelModels, pageable, count);
        ResponseEnvelope<Page<SysUserModel>> responseEnv = new ResponseEnvelope<>(page, true);

        return responseEnv;
    }

    @PostMapping(value = "/lq/sysUser")
    public ResponseEnvelope<Integer> createSysUser(@RequestBody SysUserVO sysUserVO) {
        SysUserModel sysUserModel = beanMapper.map(sysUserVO, SysUserModel.class);
        Integer result = sysUserService.create(sysUserModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }


    @GetMapping(value = "/lq/sysUser/login")
    public ResponseEnvelope<Integer> login(SysUserVO sysUserVO) {
        SysUserModel sysUserModel = beanMapper.map(sysUserVO, SysUserModel.class);
        Integer result = sysUserService.login(sysUserModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @DeleteMapping(value = "/lq/sysUser/{id}")
    public ResponseEnvelope<Integer> deleteSysUserByPrimaryKey(@PathVariable Integer id) {
        Integer result = sysUserService.deleteByPrimaryKey(id);

        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }


    @PutMapping(value = "/lq/sysUser/{id}")
    public ResponseEnvelope<Integer> updateSysUserByPrimaryKeySelective(@PathVariable Integer id,
                                                                        @RequestBody SysUserVO sysUserVO) {
        SysUserModel sysUserModel = beanMapper.map(sysUserVO, SysUserModel.class);
        sysUserModel.setId(id);
        Integer result = sysUserService.updateByPrimaryKeySelective(sysUserModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result, true);
        return responseEnv;
    }

}
