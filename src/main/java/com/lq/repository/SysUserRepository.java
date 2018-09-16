package com.lq.repository;

import com.lq.entity.SysUser;
import java.util.List;

import com.lq.model.SysUserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(@Param("sysuser") SysUser sysuser);

    int insertSelective(@Param("sysuser") SysUser sysuser);

    SysUser selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(@Param("sysuser") SysUser sysuser);

    int updateByPrimaryKey(@Param("sysuser") SysUser sysuser);

    int selectCount(@Param("sysuser") SysUser sysuser);

    List<com.lq.entity.SysUser> selectPage(@Param("sysuser") SysUser sysuser, @Param("pageable") Pageable pageable);

    SysUserModel selectUserByKeyWord(@org.apache.ibatis.annotations.Param("keyWord") String keyWord);
}