package com.lq.controller;

import com.lq.model.SysAclModuleModel;
import com.lq.service.SysAclModuleService;
import com.lq.vo.SysAclModuleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;


import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/permission")
public class SysAclModuleRestApiController {

	private final Logger logger = LoggerFactory.getLogger(SysAclModuleRestApiController.class);

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private SysAclModuleService sysAclModuleService;

	@GetMapping(value = "/lq/sysAclModule/{id}")
	public ResponseEnvelope<SysAclModuleVO> getSysAclModuleById(@PathVariable Integer id){
		SysAclModuleModel sysAclModuleModel = sysAclModuleService.findByPrimaryKey(id);
		SysAclModuleVO sysAclModuleVO =beanMapper.map(sysAclModuleModel, SysAclModuleVO.class);
		ResponseEnvelope<SysAclModuleVO> responseEnv = new ResponseEnvelope<>(sysAclModuleVO,true);
		return responseEnv;
	}

	@GetMapping(value = "/lq/sysAclModule")
    public ResponseEnvelope<Page<SysAclModuleModel>> listSysAclModule(SysAclModuleVO sysAclModuleVO, Pageable pageable){

		SysAclModuleModel param = beanMapper.map(sysAclModuleVO, SysAclModuleModel.class);
        List<SysAclModuleModel> sysAclModuleModelModels = sysAclModuleService.selectPage(param,pageable);
        long count=sysAclModuleService.selectCount(param);
        Page<SysAclModuleModel> page = new PageImpl<>(sysAclModuleModelModels,pageable,count);
        ResponseEnvelope<Page<SysAclModuleModel>> responseEnv = new ResponseEnvelope<>(page,true);
        return responseEnv;
    }

	@PostMapping(value = "/lq/sysAclModule")
	public ResponseEnvelope<Integer> createSysAclModule(@RequestBody SysAclModuleVO sysAclModuleVO){
		SysAclModuleModel sysAclModuleModel = beanMapper.map(sysAclModuleVO, SysAclModuleModel.class);
		Integer  result = sysAclModuleService.create(sysAclModuleModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}

    @DeleteMapping(value = "/lq/sysAclModule/{id}")
	public ResponseEnvelope<Integer> deleteSysAclModuleByPrimaryKey(@PathVariable Integer id){
		Integer  result = sysAclModuleService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result,true);
        return responseEnv;
	}


    @PutMapping(value = "/lq/sysAclModule/{id}")
	public ResponseEnvelope<Integer> updateSysAclModuleByPrimaryKeySelective(@PathVariable Integer id,
					@RequestBody SysAclModuleVO sysAclModuleVO){
		SysAclModuleModel sysAclModuleModel = beanMapper.map(sysAclModuleVO, SysAclModuleModel.class);
		sysAclModuleModel.setId(id);
		Integer  result = sysAclModuleService.updateByPrimaryKeySelective(sysAclModuleModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result,true);
        return responseEnv;
	}

}
