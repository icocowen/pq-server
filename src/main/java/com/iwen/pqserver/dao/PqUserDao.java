package com.iwen.pqserver.dao;

import com.iwen.pqserver.entity.PqUser;
import com.iwen.pqserver.entity.PqUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PqUserDao {
    long countByExample(PqUserExample example);

    int deleteByExample(PqUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PqUser record);

    int insertSelective(PqUser record);

    List<PqUser> selectByExample(PqUserExample example);

    PqUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PqUser record, @Param("example") PqUserExample example);

    int updateByExample(@Param("record") PqUser record, @Param("example") PqUserExample example);

    int updateByPrimaryKeySelective(PqUser record);

    int updateByPrimaryKey(PqUser record);
}