package com.lq.controller;

import com.lq.mapping.BeanMapper;
import com.lq.service.SysAclModuleService;
import com.lq.service.SysRoleService;
import com.lq.utils.StringUtils;
import com.lq.vo.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.lq.service.SysRoleAclService;
import com.lq.model.SysRoleAclModel;
import com.lq.vo.SysRoleAclVO;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class SysRoleAclRestApiController {

    private final Logger logger = LoggerFactory.getLogger(SysRoleAclRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysRoleAclService sysRoleAclService;


    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping(value = "/lq/sysRoleAcl/{id}")
    public ResponseEnvelope<SysRoleAclVO> getSysRoleAclById(@PathVariable Integer id) {
        SysRoleAclModel sysRoleAclModel = sysRoleAclService.findByPrimaryKey(id);
        SysRoleAclVO sysRoleAclVO = beanMapper.map(sysRoleAclModel, SysRoleAclVO.class);
        ResponseEnvelope<SysRoleAclVO> responseEnv = new ResponseEnvelope<>(sysRoleAclVO, true);
        return responseEnv;
    }

    @GetMapping(value = "/lq/sysRoleAcl")
    public ResponseEnvelope<Page<SysRoleAclModel>> listSysRoleAcl(SysRoleAclVO sysRoleAclVO, Pageable pageable) {

        SysRoleAclModel param = beanMapper.map(sysRoleAclVO, SysRoleAclModel.class);
        List<SysRoleAclModel> sysRoleAclModelModels = sysRoleAclService.selectPage(param, pageable);
        long count = sysRoleAclService.selectCount(param);
        Page<SysRoleAclModel> page = new PageImpl<>(sysRoleAclModelModels, pageable, count);
        ResponseEnvelope<Page<SysRoleAclModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return responseEnv;
    }

    @PostMapping(value = "/lq/sysRoleAcl")
    public ResponseEnvelope<Integer> createSysRoleAcl(@RequestBody SysRoleAclVO sysRoleAclVO) {
        SysRoleAclModel sysRoleAclModel = beanMapper.map(sysRoleAclVO, SysRoleAclModel.class);
        Integer result = sysRoleAclService.create(sysRoleAclModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @DeleteMapping(value = "/lq/sysRoleAcl/{id}")
    public ResponseEnvelope<Integer> deleteSysRoleAclByPrimaryKey(@PathVariable Integer id) {
        Integer result = sysRoleAclService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }


    @PutMapping(value = "/lq/sysRoleAcl/{id}")
    public ResponseEnvelope<Integer> updateSysRoleAclByPrimaryKeySelective(@PathVariable Integer id,
                                                                           @RequestBody SysRoleAclVO sysRoleAclVO) {
        SysRoleAclModel sysRoleAclModel = beanMapper.map(sysRoleAclVO, SysRoleAclModel.class);
        sysRoleAclModel.setId(id);
        Integer result = sysRoleAclService.updateByPrimaryKeySelective(sysRoleAclModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result, true);
        return responseEnv;
    }

    /**
     * 角色权限树
     *
     * @param roleId 角色id
     * @return
     */
    @GetMapping("/roleTree")
    public ResponseEnvelope getRoleTree(@RequestParam("roleId") int roleId) {
        return new ResponseEnvelope(sysRoleService.roleTree(roleId), true);
    }

    /**
     * 保存（或者修改）角色权限关系
     *
     * @param roleId
     * @param aclIds
     * @return
     */
    @PutMapping("/changeAcls")
    public ResponseEnvelope changeAcls(@RequestParam("roleId") int roleId,
                                       @RequestParam(value = "aclIds", required = false, defaultValue = "") String aclIds) {
        List<Integer> aclIdList = StringUtils.split2ListInt(aclIds);
        int res = sysRoleAclService.update(roleId, aclIdList);
        return res > 0 ? new ResponseEnvelope(true) : new ResponseEnvelope(false);
    }


}
