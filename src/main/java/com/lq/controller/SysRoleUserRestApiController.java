package com.lq.controller;

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

import com.wlw.pylon.core.beans.mapping.BeanMapper;
import com.wlw.pylon.web.rest.ResponseEnvelope;
import com.wlw.pylon.web.rest.annotation.RestApiController;

import com.lq.service.SysRoleUserService;
import com.lq.model.SysRoleUserModel;
import com.lq.vo.SysRoleUserVO;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class SysRoleUserRestApiController {

	private final Logger logger = LoggerFactory.getLogger(SysRoleUserRestApiController.class);

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private SysRoleUserService sysRoleUserService;

	@GetMapping(value = "/lq/sysRoleUser/{id}")
	public ResponseEnvelope<SysRoleUserVO> getSysRoleUserById(@PathVariable Integer id){
		SysRoleUserModel sysRoleUserModel = sysRoleUserService.findByPrimaryKey(id);
		SysRoleUserVO sysRoleUserVO =beanMapper.map(sysRoleUserModel, SysRoleUserVO.class);
		ResponseEnvelope<SysRoleUserVO> responseEnv = new ResponseEnvelope<>(sysRoleUserVO,true);
		return responseEnv;
	}

	@GetMapping(value = "/lq/sysRoleUser")
    public ResponseEnvelope<Page<SysRoleUserModel>> listSysRoleUser(SysRoleUserVO sysRoleUserVO,Pageable pageable){

		SysRoleUserModel param = beanMapper.map(sysRoleUserVO, SysRoleUserModel.class);
        List<SysRoleUserModel> sysRoleUserModelModels = sysRoleUserService.selectPage(param,pageable);
        long count=sysRoleUserService.selectCount(param);
        Page<SysRoleUserModel> page = new PageImpl<>(sysRoleUserModelModels,pageable,count);
        ResponseEnvelope<Page<SysRoleUserModel>> responseEnv = new ResponseEnvelope<>(page,true);
        return responseEnv;
    }

	@PostMapping(value = "/lq/sysRoleUser")
	public ResponseEnvelope<Integer> createSysRoleUser(@RequestBody SysRoleUserVO sysRoleUserVO){
		SysRoleUserModel sysRoleUserModel = beanMapper.map(sysRoleUserVO, SysRoleUserModel.class);
		Integer  result = sysRoleUserService.create(sysRoleUserModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}

    @DeleteMapping(value = "/lq/sysRoleUser/{id}")
	public ResponseEnvelope<Integer> deleteSysRoleUserByPrimaryKey(@PathVariable Integer id){
		Integer  result = sysRoleUserService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}


    @PutMapping(value = "/lq/sysRoleUser/{id}")
	public ResponseEnvelope<Integer> updateSysRoleUserByPrimaryKeySelective(@PathVariable Integer id,
					@RequestBody SysRoleUserVO sysRoleUserVO){
		SysRoleUserModel sysRoleUserModel = beanMapper.map(sysRoleUserVO, SysRoleUserModel.class);
		sysRoleUserModel.setId(id);
		Integer  result = sysRoleUserService.updateByPrimaryKeySelective(sysRoleUserModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result,true);
        return responseEnv;
	}

}
