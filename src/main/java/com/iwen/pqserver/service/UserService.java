package com.iwen.pqserver.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.iwen.pqserver.api.CommonResult;
import com.iwen.pqserver.controller.ChatWebSocket;
import com.iwen.pqserver.dao.PqUserDao;
import com.iwen.pqserver.dao.PqUserInfoDao;
import com.iwen.pqserver.entity.*;
import com.iwen.pqserver.utils.EncodeHelper;
import com.iwen.pqserver.utils.JwtTokenUtil;
import com.iwen.pqserver.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    public static final String KEY_SUFFIX = ":key";


    @Resource
    private PqUserDao pqUserDao;

    @Resource
    private PqUserInfoDao pqUserInfoDao;

    @Autowired
    private GroupsService groupsService;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private RedisUtil redisUtil;


    @Value("${verify.code.prefix}")
    private String verifyCodePrefix;


    @Autowired
    private SendEmailService sendEmailService;


    @Autowired
    private EncodeHelper encodeHelper;

    @Value("${pqchat.not-group-chat-prefix}")
    private String notGroupChatPrefix;


    @Value("${pqchat.group-chat-prefix}")
    private String groupChatPrefix;

    @Value("${pqchat.apply-friend}")
    private String applyFriendPrefix;

    private static final String MEMBERS = "members";

    @Autowired
    private ChatWebSocket chatWebSocket;



    public List<PqUser> findUserByEmail(String email) {
        PqUserExample exm = new PqUserExample();
        PqUserExample.Criteria criteria = exm.createCriteria();
        criteria.andEmailEqualTo(email);
        LOGGER.info("查找email为 {} 的用户", email);
        return pqUserDao.selectByExample(exm);
    }

    public List<PqUser> findUserById(int id) {
        PqUserExample exm = new PqUserExample();
        PqUserExample.Criteria criteria = exm.createCriteria();
        criteria.andIdEqualTo(id);
        LOGGER.info("查找email为 {} 的用户", id);
        return pqUserDao.selectByExample(exm);
    }


    public boolean hasUserByEmail(String email) {
        PqUserExample exm = new PqUserExample();
        PqUserExample.Criteria criteria = exm.createCriteria();
        criteria.andEmailEqualTo(email);
        LOGGER.info("是否存在email为 {} 的用户", email);
        return pqUserDao.countByExample(exm) > 0;
    }


    public boolean register(PqUser user, String nickName) {


        String password = encodeHelper.encodePassword(user.getPassword());
        user.setPassword(password);

        boolean b = hasUserByEmail(user.getEmail());
        if (b) {
            LOGGER.info("email为 {} 的用户，已存在", user.getEmail());
            return false;
        }
        LOGGER.info("注册 email为 {} 的用户，成功", user.getEmail());

        PqUserInfo info = new PqUserInfo();
        info.setNickName(nickName);


        pqUserInfoDao.insertSelective(info);
        user.setUserInfoId(info.getId());

        pqUserDao.insertSelective(user);
        return true;
    }





    public Map<String, String> login(String email, String password) {
        PqUserExample example = new PqUserExample();
        PqUserExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        List<PqUser> pqUsers = pqUserDao.selectByExample(example);
        if (pqUsers == null || pqUsers.isEmpty()) {
            LOGGER.info("登录email为 {} 的用户，失败", email);
            return null;
        }


        PqUser user = pqUsers.get(0);
        String origin = encodeHelper.encodePassword(password);
        if (!user.getPassword().equals(origin)) {
            LOGGER.info("登录email为 {} 的用户，密码或账号不匹配", email);

            return null;
        }
        LOGGER.info("登录email为 {} 的用户，登录成功", email);

        example = new PqUserExample();
        criteria = example.createCriteria();
        criteria.andIdEqualTo(user.getId());

        user.setLastLogin(new Date());

        pqUserDao.updateByExample(user, example);

        String token = jwtTokenUtil.generateToken(new UserDetails(user));
        PqUserInfo userNickName = getUserNickName(user.getUserInfoId());

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("nickName", userNickName.getNickName());
        tokenMap.put("user", user.getEmail());
        tokenMap.put("id", String.valueOf(user.getId()));


        return tokenMap;

    }

    public Map<String, Object> getUserFriends(String email, String time) {
        List<PqUser> self = findUserByEmail(email);

        if (self == null) {
            return null;
        }

        PqUser pqUser = self.get(0);
        if (StringUtils.isEmpty(time)) {
            time = "0";
        }
        Date date = new Date(Long.valueOf(time));


        if (pqUser.getLastFriendsUpdateTime().compareTo(date) <= 0) {
            return null;
        }


        String friends = pqUser.getFriends();
        JSONArray objects = JSONUtil.parseArray(friends);

        final List<Map<String, String>> friendsList = new ArrayList<>(objects.size());
        LOGGER.info("获取email:{} 的 好友成功", email);

        objects.forEach(id -> {
            Map<String, String> m = new HashMap<>();
            List<PqUser> userOne = findUserById(Integer.valueOf(id.toString()));
            if (userOne != null && !userOne.isEmpty()) {
                PqUser user = userOne.get(0);
                PqUserInfo nickName = getUserNickName(user.getUserInfoId());
                m.put("email", user.getEmail());
                m.put("id", String.valueOf(user.getId()));
                m.put("nickName", nickName.getNickName());
                friendsList.add(m);
            }
        });

        Map<String, Object> m = new HashMap<>();
        m.put("lastFriendsUpdateTime", pqUser.getLastFriendsUpdateTime().getTime());
        m.put("friends", friendsList);
        return m;
    }


    public PqUserInfo getUserNickName(int id) {
        PqUserInfoExample userInfo = new PqUserInfoExample();
        PqUserInfoExample.Criteria criteria1 = userInfo.createCriteria();
        criteria1.andIdEqualTo(id);
        List<PqUserInfo> infos = pqUserInfoDao.selectByExample(userInfo);
        return infos.get(0);

    }

    public Map<String, Object> getUserGroups(String email, String time) {
        List<PqUser> user = findUserByEmail(email);
        PqUser pqUser = user.get(0);

        if (StringUtils.isEmpty(time)) {
            time = "0";
        }
        Date date = new Date(Long.valueOf(time));


        if (pqUser.getLastGroupsUpdateTime().compareTo(date) <= 0) {
            return null;
        }

        String groups = pqUser.getGroups();
        JSONArray objects = JSONUtil.parseArray(groups);


        final List<Map<String, String>> groupsList = new ArrayList<>(objects.size());
        LOGGER.info("获取email:{} 的 组成功", email);

        objects.forEach(id -> {
            Map<String, String> m = new HashMap<>();
            List<PqChatGroup> groups1 = groupsService.findGroupById(Integer.valueOf(id.toString()));
            PqChatGroup chatGroup = groups1.get(0);
            int groupSize = JSONUtil.parseArray(chatGroup.getMenbers()).size();
            m.put("groupSize", String.valueOf(groupSize));
            m.put("id", String.valueOf(chatGroup.getId()));
            m.put("groupName", chatGroup.getGroupName());
            m.put("createTime", String.valueOf(chatGroup.getCreateTime().getTime()));
            groupsList.add(m);
        });

        Map<String, Object> m = new HashMap<>();
        m.put("lastGroupsUpdateTime", pqUser.getLastGroupsUpdateTime().getTime());
        m.put("groups", groupsList);

        return m;
    }

    public Set<Object> getUnrecvMessage(String uid) {
        Set<Object> messageSet = redisUtil.getAllMessageSet(notGroupChatPrefix + ":" + uid);
        redisUtil.del(notGroupChatPrefix + ":" + uid);
        return messageSet;
    }

    public Set<Object> getUnrecvGroupMessage(String gid, String uid) {
        String membersKey = groupChatPrefix + ":" + gid + ":" + MEMBERS;
        boolean isMember = redisUtil.isMember(membersKey, uid);
        if (!isMember) {
            return null;
        }

        Set<Object> messageSet = redisUtil.getAllMessageSet(groupChatPrefix + ":" + gid);

        redisUtil.setRemove(membersKey, uid);
        if (redisUtil.sSetMemberSize(membersKey) == 0) {
            redisUtil.del(membersKey);
            redisUtil.del(groupChatPrefix + ":" + gid);
        }
        return messageSet;
    }

    public List<Map<String, String>> getUserGroupMembers(String uid, String gid) {
        PqUser user = findUserById(Integer.valueOf(uid)).get(0);
        String groups = user.getGroups();
        JSONArray array = JSONUtil.parseArray(groups);
        boolean[] flags = {false};
        array.forEach(d -> {
            if (String.valueOf(d).equals(gid)) {
                flags[0] = true;
                return;
            }
        });

        if (!flags[0]) {
            return null;
        }

        String userGroupMembers = groupsService.getGroupMembers(Integer.valueOf(gid));
        if (userGroupMembers.length() == 2) {
            return null;
        }

        JSONArray memberArray = JSONUtil.parseArray(userGroupMembers);

        final List<Map<String, String>> members = new ArrayList<>(memberArray.size());

        memberArray.forEach(d -> {
            PqUser pqUser = findUserById(Integer.valueOf(d.toString())).get(0);
            Map<String, String> m = new HashMap<>();
            m.put("email", pqUser.getEmail());
            m.put("uid", String.valueOf(pqUser.getId()));
            members.add(m);
        });

        return members;


    }

    public void removeBeforeVerifyCode(String mail){
        String isSend = (String)redisUtil.get(verifyCodePrefix + ":" + mail);
        if (!StringUtils.isEmpty(isSend)) {
            redisUtil.del(verifyCodePrefix + ":" + mail, verifyCodePrefix + ":" + isSend);
        }
    }

    public CommonResult sendRegisterVerifyCode(String mail) {
        removeBeforeVerifyCode(mail);

        boolean b = hasUserByEmail(mail);
        if (b) {
            LOGGER.error("该Email地址已经注册:{}", mail);
            return CommonResult.failed("该Email地址已经注册");
        }
        sendVerifyCode(mail);
        LOGGER.info("发送验证码成功:{}", mail);
        return CommonResult.success("发送验证码成功");
    }


    public CommonResult sendForgetPasswordVerifyCode(String mail) {
        removeBeforeVerifyCode(mail);

        boolean b = hasUserByEmail(mail);
        if (!b) {
            LOGGER.error("该mail没有注册，找回密码失败", mail);
            return CommonResult.failed("找回密码失败");
        }
        sendVerifyCodeForforget(mail);
        LOGGER.info("发送验证码成功:{}", mail);
        return CommonResult.success("发送验证码成功");
    }

    public void sendVerifyCodeForforget(String mail) {
        String code = encodeHelper.generateVerifyCode();
        redisUtil.set(verifyCodePrefix + ":" + code, mail, 60 * 5);
        redisUtil.set(verifyCodePrefix + ":" + mail, code, 60 * 5);
        sendEmailService.sendVerifyCodeForForget(code, mail);
    }




    public void sendVerifyCode(String mail) {
        String code = encodeHelper.generateVerifyCode();
        redisUtil.set(verifyCodePrefix + ":" + code, mail, 60 * 5);
        redisUtil.set(verifyCodePrefix + ":" + mail, code, 60 * 5);
        sendEmailService.sendVerifyCode(code, mail);
    }

    public boolean register(String code, String password, String nickName) {
        String mail = (String)redisUtil.get(verifyCodePrefix + ":" + code);
        if (StringUtils.isEmpty(mail)) {
            return false;
        }
        redisUtil.del(verifyCodePrefix + ":" + code, verifyCodePrefix + ":" + mail);
        PqUser pqUser = new PqUser();
        pqUser.setPassword(password);
        pqUser.setEmail(mail);
        return register(pqUser, nickName);
    }

    public boolean forgetPassword(String code, String password) {
        String mail = (String)redisUtil.get(verifyCodePrefix + ":" + code);
        if (StringUtils.isEmpty(mail)) {
            return false;
        }
        redisUtil.del(verifyCodePrefix + ":" + code, verifyCodePrefix + ":" + mail);
        PqUser pqUser = new PqUser();
        pqUser.setPassword(password);
        password = encodeHelper.encodePassword(password);
        pqUser.setPassword(password);

        boolean b = hasUserByEmail(mail);
        if (!b) {
            LOGGER.info("用户:{} 不存在,不能找回密码", mail);
            return false;
        }
        PqUserExample example = new PqUserExample();
        example.createCriteria().andEmailEqualTo(mail);
        pqUserDao.updateByExampleSelective(pqUser, example);
        return true;
    }


    public void deleteFriend(String ownerId, String targetId) {

      deleteFriendOne(ownerId, targetId);
      deleteFriendOne(targetId, ownerId);

    }

    /*
    * 从目标方删除自己id
    * */
    public void deleteFriendOne(String ownerId, String targetId) {
        PqUserExample example = new PqUserExample();
        PqUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(Integer.valueOf(targetId));

        PqUser targetUser = pqUserDao.selectByPrimaryKey(Integer.valueOf(targetId));
        String friends = targetUser.getFriends();
        JSONArray objects = JSONUtil.parseArray(friends);
        objects.remove(Integer.valueOf(ownerId));
        targetUser.setFriends(JSONUtil.toJsonStr(objects));
        targetUser.setLastFriendsUpdateTime(new Date());
        pqUserDao.updateByExampleSelective(targetUser, example);
    }

    public Map<String, Object> search(String key) {
        //找friend
        //返回昵称id email
        PqUserExample example = new PqUserExample();
        PqUserExample.Criteria criteria = example.createCriteria();
        criteria.andEmailLike(key+"%");
        List<PqUser> pqUsers = pqUserDao.selectByExample(example);
        Map<String, Object> m = new HashMap<>();
        List<Map<String, String>> list = new ArrayList<>();
        pqUsers.forEach(u -> {
            Map<String, String> tm = new HashMap<>();
            PqUserInfo infoById = findPqUserInfoById(u.getUserInfoId());
            tm.put("nickName", infoById.getNickName());
            tm.put("id", String.valueOf(u.getId()));
            tm.put("email", u.getEmail());
            list.add(tm);
        });

        m.put("friends", list);


        return m;
    }


    public Map<String, Object> userBrief(String uid) {
        PqUser user= pqUserDao.selectByPrimaryKey(Integer.valueOf(uid));
        Map<String, Object> tm = new HashMap<>();
        PqUserInfo infoById = findPqUserInfoById(user.getUserInfoId());
        tm.put("nickName", infoById.getNickName());
        tm.put("id", String.valueOf(user.getId()));
        tm.put("email", user.getEmail());
        return tm;
    }

    public PqUserInfo findPqUserInfoById(int id) {
        return pqUserInfoDao.selectByPrimaryKey(id);
    }

    public void applyFriend(String uid, String friendId, String sayHello) {
        Map<String, Object> brief = userBrief(uid);
        Map<String, String> tm = new HashMap<>();
        tm.put("sendTime", String.valueOf(System.currentTimeMillis()));
        tm.put("text", sayHello);

        brief.put("sayHello", tm);
        brief.put("event", "friendApply");
        List<Map<String, Object>> t = new ArrayList<>();
        t.add(brief);
        boolean b = chatWebSocket.sendFriendApply(t, friendId);
        if (!b) {
            brief.remove("sayHello");
            String key = applyFriendPrefix + ":" + friendId + ":" + uid;
            Object o = redisUtil.get( KEY_SUFFIX + key );
            if (o == null) {
                redisUtil.set(KEY_SUFFIX + key , brief, 60 * 60 * 24 * 14);
            }
            redisUtil.set( key, tm);
        }
    }

    public List<Map<String, Object>> applyFriendList(String uid) {
        String key = applyFriendPrefix + ":" + uid;
        Set<String> keys = redisUtil.scan(key);
        if (keys.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> res = new ArrayList<>();
        keys.forEach(s -> {
            HashMap<String, Object> m = (HashMap<String, Object>)redisUtil.get( KEY_SUFFIX + s);
            HashMap<String, String> sayHello = (HashMap<String, String>)redisUtil.get(s);
            m.put("sayHello", sayHello);
            res.add(m);
            redisUtil.del(KEY_SUFFIX + s, s);
        });
        return res;
    }

    public void agreeFriendApply(String uid, String friendId) {
        //判断是否在线，是在线则直接发送给对应用户,通知其更新好友列表
        //不在线则，直接存储到数据库
        chatWebSocket.sendAgreeFriendApply(friendId);
        addFriendToUser(uid, friendId);
        addFriendToUser(friendId, uid);
    }

    private void addFriendToUser(String uid, String friendId) {
        PqUser self = pqUserDao.selectByPrimaryKey(Integer.valueOf(uid));
        String friends = self.getFriends();
        JSONArray array = JSONUtil.parseArray(friends);
        if (array.contains(Integer.valueOf(friendId))) {
            return;
        }
        array.add(Integer.valueOf(friendId));
        String s = JSONUtil.toJsonStr(array);
        self.setFriends(s);
        self.setLastFriendsUpdateTime(new Date());
        pqUserDao.updateByPrimaryKey(self);
    }
}
