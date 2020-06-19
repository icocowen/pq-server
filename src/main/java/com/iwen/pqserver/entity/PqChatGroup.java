package com.iwen.pqserver.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * pq_chat_group
 * @author 
 */
public class PqChatGroup implements Serializable {
    private Integer id;

    /**
     * 组名
     */
    private String groupName;

    /**
     * 建组时间
     */
    private Date createTime;

    /**
     * pq_user_info id
     */
    private String menbers;

    /**
     * 组成员最后一次更新的时间
     */
    private Date lastMenberUpdateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMenbers() {
        return menbers;
    }

    public void setMenbers(String menbers) {
        this.menbers = menbers;
    }

    public Date getLastMenberUpdateTime() {
        return lastMenberUpdateTime;
    }

    public void setLastMenberUpdateTime(Date lastMenberUpdateTime) {
        this.lastMenberUpdateTime = lastMenberUpdateTime;
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
        PqChatGroup other = (PqChatGroup) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGroupName() == null ? other.getGroupName() == null : this.getGroupName().equals(other.getGroupName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getMenbers() == null ? other.getMenbers() == null : this.getMenbers().equals(other.getMenbers()))
            && (this.getLastMenberUpdateTime() == null ? other.getLastMenberUpdateTime() == null : this.getLastMenberUpdateTime().equals(other.getLastMenberUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGroupName() == null) ? 0 : getGroupName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getMenbers() == null) ? 0 : getMenbers().hashCode());
        result = prime * result + ((getLastMenberUpdateTime() == null) ? 0 : getLastMenberUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupName=").append(groupName);
        sb.append(", createTime=").append(createTime);
        sb.append(", menbers=").append(menbers);
        sb.append(", lastMenberUpdateTime=").append(lastMenberUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}