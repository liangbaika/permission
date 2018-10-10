package com.lq.repository;

import com.lq.entity.SysRole;
import java.util.List;

import com.lq.model.SysRoleModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleRepository {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(@Param("sysrole") SysRole sysrole);

    int insertSelective(@Param("sysrole") SysRole sysrole);

    SysRole selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(@Param("sysrole") SysRole sysrole);

    int updateByPrimaryKey(@Param("sysrole") SysRole sysrole);

    int selectCount(@Param("sysrole") SysRole sysrole);

    List<com.lq.entity.SysRole> selectPage(@Param("sysrole") SysRole sysrole, @Param("pageable") Pageable pageable);

    List<SysRoleModel> getRoleByIdList(@org.apache.ibatis.annotations.Param("idList") List<Integer> idList);
}