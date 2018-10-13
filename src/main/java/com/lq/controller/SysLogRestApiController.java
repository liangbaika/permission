package com.lq.controller;

import com.lq.mapping.BeanMapper;
import com.lq.vo.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import com.lq.service.SysLogService;
import com.lq.model.SysLogModel;
import com.lq.vo.SysLogVO;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class SysLogRestApiController {

    private final Logger logger = LoggerFactory.getLogger(SysLogRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysLogService sysLogService;

    @GetMapping(value = "/lq/sysLog/{id}")
    public ResponseEnvelope<SysLogVO> getSysLogById(@PathVariable Integer id) {
        SysLogModel sysLogModel = sysLogService.findByPrimaryKey(id);
        SysLogVO sysLogVO = beanMapper.map(sysLogModel, SysLogVO.class);
        ResponseEnvelope<SysLogVO> responseEnv = new ResponseEnvelope<>(sysLogVO, true);
        return responseEnv;
    }

    @GetMapping(value = "/lq/sysLog")
    public ResponseEnvelope<Page<SysLogModel>> listSysLog(SysLogVO sysLogVO, Pageable pageable) {

        SysLogModel param = beanMapper.map(sysLogVO, SysLogModel.class);
        List<SysLogModel> sysLogModelModels = sysLogService.selectPage(param, pageable);
        long count = sysLogService.selectCount(param);
        Page<SysLogModel> page = new PageImpl<>(sysLogModelModels, pageable, count);
        ResponseEnvelope<Page<SysLogModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return responseEnv;
    }

    @PostMapping(value = "/lq/sysLog")
    public ResponseEnvelope<Integer> createSysLog(@RequestBody SysLogVO sysLogVO) {
        SysLogModel sysLogModel = beanMapper.map(sysLogVO, SysLogModel.class);
        Integer result = sysLogService.create(sysLogModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @DeleteMapping(value = "/lq/sysLog/{id}")
    public ResponseEnvelope<Integer> deleteSysLogByPrimaryKey(@PathVariable Integer id) {
        Integer result = sysLogService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }


    @PutMapping(value = "/lq/sysLog/{id}")
    public ResponseEnvelope<Integer> updateSysLogByPrimaryKeySelective(@PathVariable Integer id,
                                                                       @RequestBody SysLogVO sysLogVO) {
        SysLogModel sysLogModel = beanMapper.map(sysLogVO, SysLogModel.class);
        sysLogModel.setId(id);
        Integer result = sysLogService.updateByPrimaryKeySelective(sysLogModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result, true);
        return responseEnv;
    }

    @PutMapping(value = "/lq/sysLog/recover/{id}")
    public ResponseEnvelope recover(@PathVariable Integer id) {
        Integer res = sysLogService.recover(id);
        return res > 0 ? new ResponseEnvelope(res, true) : new ResponseEnvelope(res, false);
    }

}
