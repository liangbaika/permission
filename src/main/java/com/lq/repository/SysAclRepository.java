package com.lq.repository;

import java.util.List;

import com.lq.entity.SysAcl;
import com.lq.model.SysAclModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAclRepository {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(@Param("sysacl") SysAcl sysacl);

    int insertSelective(@Param("sysacl") SysAcl sysacl);

    SysAcl selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(@Param("sysacl") SysAcl sysacl);

    int updateByPrimaryKey(@Param("sysacl") SysAcl sysacl);

    int selectCount(@Param("sysacl") SysAcl sysacl);

    List<com.lq.entity.SysAcl> selectPage(@Param("sysacl") SysAcl sysacl, @Param("pageable") Pageable pageable);

    int checkExist(@org.apache.ibatis.annotations.Param("aclModuleId") Integer aclModuleId, @org.apache.ibatis.annotations.Param("name") String name);

    List<SysAclModel> getAll();

    List<SysAclModel> getByIdList(@org.apache.ibatis.annotations.Param("ids") List<Integer> aclIdList);
}