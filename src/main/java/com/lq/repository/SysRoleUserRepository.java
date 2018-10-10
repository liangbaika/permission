package com.lq.repository;

import com.lq.entity.SysRoleUser;

import java.util.List;

import com.lq.model.SysRoleModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleUserRepository {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(@Param("sysroleuser") SysRoleUser sysroleuser);

    int insertSelective(@Param("sysroleuser") SysRoleUser sysroleuser);

    SysRoleUser selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(@Param("sysroleuser") SysRoleUser sysroleuser);

    int updateByPrimaryKey(@Param("sysroleuser") SysRoleUser sysroleuser);

    int selectCount(@Param("sysroleuser") SysRoleUser sysroleuser);

    List<com.lq.entity.SysRoleUser> selectPage(@Param("sysroleuser") SysRoleUser sysroleuser, @Param("pageable") Pageable pageable);

    List<Integer> getRoleIDListByUserId(@org.apache.ibatis.annotations.Param("userId") int userId);

    void deleteByRoleId(@org.apache.ibatis.annotations.Param("roleId") int roleId);

    int batchInsert(@org.apache.ibatis.annotations.Param("roleUserList") List<SysRoleUser> roleUserList);

    List<Integer> getUserIdListByRoleIdList(@org.apache.ibatis.annotations.Param("roleIdList") List<Integer> roleIdList);
}