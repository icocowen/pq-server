package com.iwen.pqserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * pq_user
 * @author 
 */
public class PqUser implements Serializable {
    private Integer id;

    private String email;

    private String password;

    /**
     * 最后登录时间
     */
    private Date lastLogin;

    /**
     * pq_chat_group id
     */
    private String groups;

    /**
     * 好友的id
     */
    private String friends;

    /**
     * 联系人最后一次更新的时间
     */
    private Date lastFriendsUpdateTime;

    /**
     * 加入的组的最后一次更新的时间
     */
    private Date lastGroupsUpdateTime;

    /**
     * pq_user_info id
     */
    private Integer userInfoId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public Date getLastFriendsUpdateTime() {
        return lastFriendsUpdateTime;
    }

    public void setLastFriendsUpdateTime(Date lastFriendsUpdateTime) {
        this.lastFriendsUpdateTime = lastFriendsUpdateTime;
    }

    public Date getLastGroupsUpdateTime() {
        return lastGroupsUpdateTime;
    }

    public void setLastGroupsUpdateTime(Date lastGroupsUpdateTime) {
        this.lastGroupsUpdateTime = lastGroupsUpdateTime;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PqUser other = (PqUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getLastLogin() == null ? other.getLastLogin() == null : this.getLastLogin().equals(other.getLastLogin()))
            && (this.getGroups() == null ? other.getGroups() == null : this.getGroups().equals(other.getGroups()))
            && (this.getFriends() == null ? other.getFriends() == null : this.getFriends().equals(other.getFriends()))
            && (this.getLastFriendsUpdateTime() == null ? other.getLastFriendsUpdateTime() == null : this.getLastFriendsUpdateTime().equals(other.getLastFriendsUpdateTime()))
            && (this.getLastGroupsUpdateTime() == null ? other.getLastGroupsUpdateTime() == null : this.getLastGroupsUpdateTime().equals(other.getLastGroupsUpdateTime()))
            && (this.getUserInfoId() == null ? other.getUserInfoId() == null : this.getUserInfoId().equals(other.getUserInfoId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getLastLogin() == null) ? 0 : getLastLogin().hashCode());
        result = prime * result + ((getGroups() == null) ? 0 : getGroups().hashCode());
        result = prime * result + ((getFriends() == null) ? 0 : getFriends().hashCode());
        result = prime * result + ((getLastFriendsUpdateTime() == null) ? 0 : getLastFriendsUpdateTime().hashCode());
        result = prime * result + ((getLastGroupsUpdateTime() == null) ? 0 : getLastGroupsUpdateTime().hashCode());
        result = prime * result + ((getUserInfoId() == null) ? 0 : getUserInfoId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", lastLogin=").append(lastLogin);
        sb.append(", groups=").append(groups);
        sb.append(", friends=").append(friends);
        sb.append(", lastFriendsUpdateTime=").append(lastFriendsUpdateTime);
        sb.append(", lastGroupsUpdateTime=").append(lastGroupsUpdateTime);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}