package com.lq.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lq.enums.DataUseful;
import com.lq.mapping.BeanMapper;
import com.lq.service.SysRoleUserService;
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
import java.util.Map;

@RestController
@RequestMapping("/permission")
public class SysUserRestApiController {

    private final Logger logger = LoggerFactory.getLogger(SysUserRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysUserService sysUserService;


    @Autowired
    private SysRoleUserService sysRoleUserService;

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


    @PostMapping(value = "/lq/sysUser/login")
    public ResponseEnvelope<Integer> login(SysUserVO sysUserVO) {
        SysUserModel sysUserModel = beanMapper.map(sysUserVO, SysUserModel.class);
        Integer result = sysUserService.login(sysUserModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @GetMapping(value = "/lq/sysUser/logout")
    public ResponseEnvelope<Integer> logout() {
        Integer result = sysUserService.logout();
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

    /**
     * 根据角色id获取角色下已有用户 和未选用户列表
     *
     * @param roleId
     * @return
     */
    @GetMapping("/users")
    public ResponseEnvelope getUsersByRoleId(@RequestParam("roleId") int roleId) {
        Map<String, List<SysUserModel>> map = getUsersOfSelectedAndUnSelectedByRoleId(roleId);
        return new ResponseEnvelope(map, true);
    }

    private Map<String, List<SysUserModel>> getUsersOfSelectedAndUnSelectedByRoleId(int roleId) {
        List<SysUserModel> selectedUserModels = sysRoleUserService.getUsersByRoleId(roleId);
        SysUserModel param = new SysUserModel();
        param.setStatus(DataUseful.USEFUL.getCode());
        List<SysUserModel> allUserModels = sysUserService.selectPage(param, null);
        Map<String, List<SysUserModel>> map = Maps.newHashMap();
        List<SysUserModel> unSelectedUserModels = Lists.newArrayList();
        for (SysUserModel userModel : allUserModels) {
            if (selectedUserModels.contains(userModel)) {
                unSelectedUserModels.add(userModel);
            }
        }
        map.put("selected", selectedUserModels);
        map.put("unselected", unSelectedUserModels);
        return map;
    }


}
