package com.lq.repository;

import java.util.List;

import com.lq.entity.SysDept;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDeptRepository {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(@Param("sysdept") SysDept sysdept);

    int insertSelective(@Param("sysdept") SysDept sysdept);

    SysDept selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(@Param("sysdept") SysDept sysdept);

    int updateByPrimaryKey(@Param("sysdept") SysDept sysdept);

    int selectCount(@Param("sysdept") SysDept sysdept);

    List<SysDept> selectPage(@Param("sysdept") SysDept sysdept, @Param("pageable") Pageable pageable);

    /**
     * 检查 在此parentid 下是否已重复
     *
     * @param parentId 父id
     * @param name     组织或部门名
     * @return
     */
    int selectDeptRepeat( @org.apache.ibatis.annotations.Param("parentId") Integer parentId, @org.apache.ibatis.annotations.Param("name")String name);
}