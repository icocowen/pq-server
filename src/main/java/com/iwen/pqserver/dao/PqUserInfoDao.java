package com.iwen.pqserver.dao;

import com.iwen.pqserver.entity.PqUserInfo;
import com.iwen.pqserver.entity.PqUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PqUserInfoDao {
    long countByExample(PqUserInfoExample example);

    int deleteByExample(PqUserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PqUserInfo record);

    int insertSelective(PqUserInfo record);

    List<PqUserInfo> selectByExample(PqUserInfoExample example);

    PqUserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PqUserInfo record, @Param("example") PqUserInfoExample example);

    int updateByExample(@Param("record") PqUserInfo record, @Param("example") PqUserInfoExample example);

    int updateByPrimaryKeySelective(PqUserInfo record);

    int updateByPrimaryKey(PqUserInfo record);
}