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


import com.lq.service.SysAclService;
import com.lq.model.SysAclModel;
import com.lq.vo.SysAclVO;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class SysAclRestApiController {

	private final Logger logger = LoggerFactory.getLogger(SysAclRestApiController.class);

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private SysAclService sysAclService;

	@GetMapping(value = "/lq/sysAcl/{id}")
	public ResponseEnvelope<SysAclVO> getSysAclById(@PathVariable Integer id){
		SysAclModel sysAclModel = sysAclService.findByPrimaryKey(id);
		SysAclVO sysAclVO =beanMapper.map(sysAclModel, SysAclVO.class);
		ResponseEnvelope<SysAclVO> responseEnv = new ResponseEnvelope<>(sysAclVO,true);
		return responseEnv;
	}

	@GetMapping(value = "/lq/sysAcl")
    public ResponseEnvelope<Page<SysAclModel>> listSysAcl(SysAclVO sysAclVO,Pageable pageable){

		SysAclModel param = beanMapper.map(sysAclVO, SysAclModel.class);
        List<SysAclModel> sysAclModelModels = sysAclService.selectPage(param,pageable);
        long count=sysAclService.selectCount(param);
        Page<SysAclModel> page = new PageImpl<>(sysAclModelModels,pageable,count);
        ResponseEnvelope<Page<SysAclModel>> responseEnv = new ResponseEnvelope<>(page,true);
        return responseEnv;
    }

	@PostMapping(value = "/lq/sysAcl")
	public ResponseEnvelope<Integer> createSysAcl(@RequestBody SysAclVO sysAclVO){
		SysAclModel sysAclModel = beanMapper.map(sysAclVO, SysAclModel.class);
		Integer  result = sysAclService.create(sysAclModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}

    @DeleteMapping(value = "/lq/sysAcl/{id}")
	public ResponseEnvelope<Integer> deleteSysAclByPrimaryKey(@PathVariable Integer id){
		Integer  result = sysAclService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}


    @PutMapping(value = "/lq/sysAcl/{id}")
	public ResponseEnvelope<Integer> updateSysAclByPrimaryKeySelective(@PathVariable Integer id,
					@RequestBody SysAclVO sysAclVO){
		SysAclModel sysAclModel = beanMapper.map(sysAclVO, SysAclModel.class);
		sysAclModel.setId(id);
		Integer  result = sysAclService.updateByPrimaryKeySelective(sysAclModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result,true);
        return responseEnv;
	}

}
