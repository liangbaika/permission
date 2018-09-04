package com.lq.repository;

import com.lq.entity.SysLog;
import com.lq.entity.SysLogWithBLOBs;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysLogRepository {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(@Param("syslogwithblobs") SysLogWithBLOBs syslogwithblobs);

    int insertSelective(@Param("syslogwithblobs") SysLogWithBLOBs syslogwithblobs);

    SysLogWithBLOBs selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(@Param("syslogwithblobs") SysLogWithBLOBs syslogwithblobs);

    int updateByPrimaryKeyWithBLOBs(@Param("syslogwithblobs") SysLogWithBLOBs syslogwithblobs);

    int updateByPrimaryKey(@Param("syslog") SysLog syslog);

    int selectCount(@Param("syslog") SysLog syslog);

    List<com.lq.entity.SysLog> selectPage(@Param("syslog") SysLog syslog, @Param("pageable") Pageable pageable);
}