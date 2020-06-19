package com.iwen.pqserver.dao;

import com.iwen.pqserver.entity.PqChatGroup;
import com.iwen.pqserver.entity.PqChatGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PqChatGroupDao {
    long countByExample(PqChatGroupExample example);

    int deleteByExample(PqChatGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PqChatGroup record);

    int insertSelective(PqChatGroup record);

    List<PqChatGroup> selectByExample(PqChatGroupExample example);

    PqChatGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PqChatGroup record, @Param("example") PqChatGroupExample example);

    int updateByExample(@Param("record") PqChatGroup record, @Param("example") PqChatGroupExample example);

    int updateByPrimaryKeySelective(PqChatGroup record);

    int updateByPrimaryKey(PqChatGroup record);
}