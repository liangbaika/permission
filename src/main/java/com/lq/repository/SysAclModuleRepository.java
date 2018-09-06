package com.lq.repository;

import java.util.List;

import com.lq.entity.SysAclModule;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface SysAclModuleRepository {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(@Param("sysaclmodule") SysAclModule sysaclmodule);

    int insertSelective(@Param("sysaclmodule") SysAclModule sysaclmodule);

    SysAclModule selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(@Param("sysaclmodule") SysAclModule sysaclmodule);

    int updateByPrimaryKey(@Param("sysaclmodule") SysAclModule sysaclmodule);

    int selectCount(@Param("sysaclmodule") SysAclModule sysaclmodule);

    List<com.lq.entity.SysAclModule> selectPage(@Param("sysaclmodule") SysAclModule sysaclmodule, @Param("pageable") Pageable pageable);
}