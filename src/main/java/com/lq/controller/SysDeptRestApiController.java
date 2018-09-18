package com.lq.controller;

import com.lq.factory.ResponseEnvelopFactory;
import com.lq.mapping.BeanMapper;
import com.lq.model.SysDeptModel;
import com.lq.service.SysDeptService;
import com.lq.vo.ResponseEnvelope;
import com.lq.vo.SysDeptVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class SysDeptRestApiController {

    private final Logger logger = LoggerFactory.getLogger(SysDeptRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysDeptService sysDeptService;

    @GetMapping(value = "/lq/sysDept/{id}")
    public ResponseEnvelope<SysDeptVO> getSysDeptById(@PathVariable Integer id) {
        SysDeptModel sysDeptModel = sysDeptService.findByPrimaryKey(id);
        SysDeptVO sysDeptVO = beanMapper.map(sysDeptModel, SysDeptVO.class);
        ResponseEnvelope<SysDeptVO> responseEnv = new ResponseEnvelope<>(sysDeptVO, true);
        return responseEnv;
    }

    @GetMapping(value = "/sysDept.json")
    public ResponseEnvelope<Page<SysDeptModel>> listSysDept(SysDeptVO sysDeptVO, Pageable pageable) {
        SysDeptModel param = beanMapper.map(sysDeptVO, SysDeptModel.class);
        List<SysDeptModel> sysDeptModelModels = sysDeptService.selectPage(param, pageable);
        long count = sysDeptService.selectCount(param);
        Page<SysDeptModel> page = new PageImpl<>(sysDeptModelModels, pageable, count);
        ResponseEnvelope<Page<SysDeptModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return responseEnv;
    }

    @GetMapping(value = "/lq/sysDept/tree")
    public ResponseEnvelope<List<SysDeptModel>> getSysDeptTree() {
        List<SysDeptModel> sysDeptModelTree = sysDeptService.getSysDeptTree();
        return ResponseEnvelopFactory.success(sysDeptModelTree);
    }

    @PostMapping(value = "/save")
    public ResponseEnvelope<Integer> createSysDept(@RequestBody SysDeptVO sysDeptVO) {
        SysDeptModel sysDeptModel = beanMapper.map(sysDeptVO, SysDeptModel.class);
        Integer result = sysDeptService.create(sysDeptModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }

    @DeleteMapping(value = "/lq/sysDept/{id}")
    public ResponseEnvelope<Integer> deleteSysDeptByPrimaryKey(@PathVariable Integer id) {
        Integer result = sysDeptService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return responseEnv;
    }


    @PutMapping(value = "/lq/sysDept/{id}")
    public ResponseEnvelope<Integer> updateSysDeptByPrimaryKeySelective(@PathVariable Integer id,
                                                                        @RequestBody SysDeptVO sysDeptVO) {
        SysDeptModel sysDeptModel = beanMapper.map(sysDeptVO, SysDeptModel.class);
        Integer result = sysDeptService.updateByPrimaryKeySelective(sysDeptModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result, true);
        return responseEnv;
    }

}
