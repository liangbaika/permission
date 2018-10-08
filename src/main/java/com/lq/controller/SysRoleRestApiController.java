package com.lq.controller;

import com.lq.mapping.BeanMapper;
import com.lq.service.SysAclModuleService;
import com.lq.vo.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import com.lq.service.SysRoleService;
import com.lq.model.SysRoleModel;
import com.lq.vo.SysRoleVO;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class SysRoleRestApiController {

    private final Logger logger = LoggerFactory.getLogger(SysRoleRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysRoleService sysRoleService;



    @GetMapping(value = "/lq/sysRole/{id}")
    public ResponseEnvelope<SysRoleVO> getSysRoleById(@PathVariable Integer id) {
        SysRoleModel sysRoleModel = sysRoleService.findByPrimaryKey(id);
        SysRoleVO sysRoleVO = beanMapper.map(sysRoleModel, SysRoleVO.class);
        ResponseEnvelope<SysRoleVO> responseEnv = new ResponseEnvelope<>(sysRoleVO, true);
        return responseEnv;
    }

    @GetMapping(value = "/lq/sysRole")
    public ResponseEnvelope<Page<SysRoleModel>> listSysRole(SysRoleVO sysRoleVO, Pageable pageable) {

        SysRoleModel param = beanMapper.map(sysRoleVO, SysRoleModel.class);
        List<SysRoleModel> sysRoleModelModels = sysRoleService.selectPage(param, pageable);
        long count = sysRoleService.selectCount(param);
        Page<SysRoleModel> page = new PageImpl<>(sysRoleModelModels, pageable, count);
        ResponseEnvelope<Page<SysRoleModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return responseEnv;
    }

    @PostMapping(value = "/lq/sysRole")
    public ResponseEnvelope<Integer> createSysRole(@RequestBody SysRoleVO sysRoleVO) {
        SysRoleModel sysRoleModel = beanMapper.map(sysRoleVO, SysRoleModel.class);
        Integer result = sysRoleService.create(sysRoleModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @DeleteMapping(value = "/lq/sysRole/{id}")
    public ResponseEnvelope<Integer> deleteSysRoleByPrimaryKey(@PathVariable Integer id) {
        Integer result = sysRoleService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }


    @PutMapping(value = "/lq/sysRole/{id}")
    public ResponseEnvelope<Integer> updateSysRoleByPrimaryKeySelective(@PathVariable Integer id,
                                                                        @RequestBody SysRoleVO sysRoleVO) {
        SysRoleModel sysRoleModel = beanMapper.map(sysRoleVO, SysRoleModel.class);
        sysRoleModel.setId(id);
        Integer result = sysRoleService.updateByPrimaryKeySelective(sysRoleModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result, true);
        return responseEnv;
    }



}
