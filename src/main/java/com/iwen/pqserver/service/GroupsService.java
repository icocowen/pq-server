package com.iwen.pqserver.service;

import com.iwen.pqserver.dao.PqChatGroupDao;
import com.iwen.pqserver.entity.PqChatGroup;
import com.iwen.pqserver.entity.PqChatGroupExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GroupsService {

    @Resource
    private PqChatGroupDao pqChatGroupDao;


    public List<PqChatGroup> findGroupById(Integer id) {

        PqChatGroupExample groupExample = new PqChatGroupExample();
        PqChatGroupExample.Criteria criteria = groupExample.createCriteria();
        criteria.andIdEqualTo(id);
        return pqChatGroupDao.selectByExample(groupExample);

    }


    public String getGroupMembers(Integer id) {
        List<PqChatGroup> group = findGroupById(id);
        PqChatGroup chatGroup = group.get(0);
        return chatGroup.getMenbers();
    }
}
