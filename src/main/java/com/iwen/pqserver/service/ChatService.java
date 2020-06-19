package com.iwen.pqserver.service;

import com.iwen.pqserver.entity.PqUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private UserService userService;


    /**
     * 验证该id的email与这个token存储的email是否相等
     *
     * @param id    需要检验的user id
     * @param email token中存储的email
     * @return
     */
    public boolean verifyIdAndEmail(Integer id, String email) {
        List<PqUser> userById = userService.findUserById(id);
        PqUser pqUser = userById.get(0);
        if (pqUser.getEmail().equals(email)) {
            return true;
        }
        return false;
    }

}
