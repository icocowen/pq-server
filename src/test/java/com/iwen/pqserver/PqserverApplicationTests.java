package com.iwen.pqserver;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.iwen.pqserver.dao.PqUserDao;
import com.iwen.pqserver.entity.PqUser;
import com.iwen.pqserver.entity.PqUserExample;
import com.iwen.pqserver.service.UserService;
import com.iwen.pqserver.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PqserverApplicationTests {

    private PqUserDao pqUserDao;
    @Autowired
    private UserService userService;


    @Autowired
    RedisUtil redisUtil;

    @Resource
    public void setPqUserDao(PqUserDao pqUserDao) {
        this.pqUserDao = pqUserDao;
    }



    @Test
    void contextLoads() {

        PqUserExample pqUserExample = new PqUserExample();
        List<PqUser> pqUsers = pqUserDao.selectByExample(pqUserExample);
        pqUsers.forEach(d -> {
            System.out.println(d);
        });
    }

    @Test
    void testArray() {
        String s = "[1,2,3,4,4]";
        JSONArray array = JSONUtil.parseArray(s);
        array.forEach(d -> {
            System.out.println(d);
        });
    }

    @Test
    void testRedis() {

        PqUser pqUser = new PqUser();
        pqUser.setPassword("1231");

        redisUtil.set("iwen1", pqUser);
        redisUtil.set("iwen2", pqUser);
        Object iwen = redisUtil.get("iwen1");
        System.out.println(iwen);
    }


    @Test
    void testRe() {
        String s = "dasdasd {verifyCode}";
        String s1 = s.replaceAll("\\{.*?verifyCode.*?\\}", 2131 + "");
        System.out.println(s1);
    }


    @Test
    void testUpdate() {
        PqUserExample example = new PqUserExample();
        example.createCriteria().andEmailEqualTo("1558165507@qq.com");
        PqUser pqUser = new PqUser();
        pqUser.setPassword("1231");
        pqUser.setEmail("1558165507@qq.com");
        pqUser.setId(13);
        pqUserDao.updateByExample(pqUser, example);
    }
    @Test
    void testSelect() {
        PqUserExample example = new PqUserExample();
        example.createCriteria().andIdEqualTo(13);
        List<PqUser> pqUsers = pqUserDao.selectByExample(example);
        PqUser pqUser = pqUsers.get(0);
        System.out.println(pqUser);

    }


//    @Test
//    void testParseDate() {
//        String date = "2020-06-08T22:13:34.000+00:00";
////        Date date1 = new Date(date);
//
//        System.out.println(new Date());
//    }


    @Test
    void testDeleteFriend() {
        userService.deleteFriend("9", "7");
    }

    @Test
    void testScan() {
        Set<String> iw =
                redisUtil.scan("iw");
        System.out.println(iw);
    }
}
