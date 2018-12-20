package com.lq.controller;

import com.lq.entity.SysAclModule;
import com.lq.factory.ResponseEnvelopFactory;
import com.lq.mapping.BeanMapper;
import com.lq.model.SysAclModuleModel;
import com.lq.service.SysAclModuleService;
import com.lq.vo.ResponseEnvelope;
import com.lq.vo.SysAclModuleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result,true);
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



	@GetMapping(value = "/lq/sysDept/tree")
	public ResponseEnvelope<List<SysAclModule>> getSysAclModuleTree() {
		List<SysAclModule> sysAclModules = sysAclModuleService.getSysAclModuleTree();
		return ResponseEnvelopFactory.success(sysAclModules);
	}
}
