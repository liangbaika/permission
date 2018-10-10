package com.lq.repository;

import com.lq.entity.SysRoleAcl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleAclRepository {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(@Param("sysroleacl") SysRoleAcl sysroleacl);

    int insertSelective(@Param("sysroleacl") SysRoleAcl sysroleacl);

    SysRoleAcl selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(@Param("sysroleacl") SysRoleAcl sysroleacl);

    int updateByPrimaryKey(@Param("sysroleacl") SysRoleAcl sysroleacl);

    int selectCount(@Param("sysroleacl") SysRoleAcl sysroleacl);

    List<com.lq.entity.SysRoleAcl> selectPage(@Param("sysroleacl") SysRoleAcl sysroleacl, @Param("pageable") Pageable pageable);

    List<Integer> getAclIdListByRoleIdList(@org.apache.ibatis.annotations.Param("roleIdList") List<Integer> userRoleIdList);

    void deleteByRoleId(@org.apache.ibatis.annotations.Param("roleId") int roleId);

    int batchInsert(@org.apache.ibatis.annotations.Param("roleAclList") List<SysRoleAcl> roleAclList);

    List<Integer> getRoleIdListByAclId(@org.apache.ibatis.annotations.Param("aclId") int aclId);
}