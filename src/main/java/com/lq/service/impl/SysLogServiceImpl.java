package com.lq.service.impl;

import com.lq.entity.SysLogWithBLOBs;
import com.lq.enums.DataUseful;
import com.lq.enums.ErrorCode;
import com.lq.enums.LogType;
import com.lq.exception.PermissionException;
import com.lq.mapping.BeanMapper;
import com.lq.utils.JsonMapper;
import com.lq.utils.LoginHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.lq.entity.SysLog;
import com.lq.repository.SysLogRepository;
import com.lq.model.SysLogModel;
import com.lq.service.SysLogService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@Service
@EnableAsync
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysLogRepository sysLogRepo;

    @Transactional
    @Override
    @Async
    public int create(SysLogModel sysLogModel) {
        return sysLogRepo.insert(beanMapper.map(sysLogModel, SysLogWithBLOBs.class));
    }

    @Transactional
    @Override
    @Async
    public int createSelective(SysLogModel sysLogModel) {
        return sysLogRepo.insertSelective(beanMapper.map(sysLogModel, SysLogWithBLOBs.class));
    }


    @Transactional
    @Async
    public <T> int createSelectiveByCustomerOfLog(T before, T after, LogType logType, Integer id) {
        if (id == null || logType == null) {
            throw new PermissionException(ErrorCode.PARAM_MISS.getMsg());
        }
        if (before == null && after == null) {
            throw new PermissionException(ErrorCode.PARAM_MISS.getMsg());
        }
        try {
            SysLogWithBLOBs sysLogWithBLOBs = new SysLogWithBLOBs();
            sysLogWithBLOBs.setTargetId(id);
            sysLogWithBLOBs.setType(logType.getCode());
            sysLogWithBLOBs.setOldValue(before == null ? "" : JsonMapper.obj2String(before));
            sysLogWithBLOBs.setNewValue(after == null ? "" : JsonMapper.obj2String(after));
            sysLogWithBLOBs.setOperator(LoginHolder.getUser().getUsername());
            sysLogWithBLOBs.setOperateIp("127.0.0.1");
            sysLogWithBLOBs.setOperateTime(new Date());
            sysLogWithBLOBs.setStatus(DataUseful.USEFUL.getCode());
            return sysLogRepo.insertSelective(sysLogWithBLOBs);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Integer recover(Integer id) {
      //todo
        return null;
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysLogRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public SysLogModel findByPrimaryKey(Integer id) {
        SysLog sysLog = sysLogRepo.selectByPrimaryKey(id);
        return beanMapper.map(sysLog, SysLogModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectCount(SysLogModel sysLogModel) {
        return sysLogRepo.selectCount(beanMapper.map(sysLogModel, SysLog.class));
    }

    @Transactional(readOnly = true)
    @Override
    public List<SysLogModel> selectPage(SysLogModel sysLogModel, Pageable pageable) {
        SysLog sysLog = beanMapper.map(sysLogModel, SysLog.class);
        return beanMapper.mapAsList(sysLogRepo.selectPage(sysLog, pageable), SysLogModel.class);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(SysLogModel sysLogModel) {
        return sysLogRepo.updateByPrimaryKey(beanMapper.map(sysLogModel, SysLog.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(SysLogModel sysLogModel) {
        return sysLogRepo.updateByPrimaryKeySelective(beanMapper.map(sysLogModel, SysLogWithBLOBs.class));
    }


}
