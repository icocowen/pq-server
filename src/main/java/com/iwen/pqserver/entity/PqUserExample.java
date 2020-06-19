package com.iwen.pqserver.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PqUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PqUserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("`password` is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("`password` is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("`password` =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("`password` <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("`password` >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("`password` >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("`password` <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("`password` <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("`password` like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("`password` not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("`password` in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("`password` not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("`password` between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("`password` not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andLastLoginIsNull() {
            addCriterion("last_login is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginIsNotNull() {
            addCriterion("last_login is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginEqualTo(Date value) {
            addCriterion("last_login =", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginNotEqualTo(Date value) {
            addCriterion("last_login <>", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginGreaterThan(Date value) {
            addCriterion("last_login >", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginGreaterThanOrEqualTo(Date value) {
            addCriterion("last_login >=", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginLessThan(Date value) {
            addCriterion("last_login <", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginLessThanOrEqualTo(Date value) {
            addCriterion("last_login <=", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginIn(List<Date> values) {
            addCriterion("last_login in", values, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginNotIn(List<Date> values) {
            addCriterion("last_login not in", values, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginBetween(Date value1, Date value2) {
            addCriterion("last_login between", value1, value2, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginNotBetween(Date value1, Date value2) {
            addCriterion("last_login not between", value1, value2, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andGroupsIsNull() {
            addCriterion("groups is null");
            return (Criteria) this;
        }

        public Criteria andGroupsIsNotNull() {
            addCriterion("groups is not null");
            return (Criteria) this;
        }

        public Criteria andGroupsEqualTo(String value) {
            addCriterion("groups =", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsNotEqualTo(String value) {
            addCriterion("groups <>", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsGreaterThan(String value) {
            addCriterion("groups >", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsGreaterThanOrEqualTo(String value) {
            addCriterion("groups >=", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsLessThan(String value) {
            addCriterion("groups <", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsLessThanOrEqualTo(String value) {
            addCriterion("groups <=", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsLike(String value) {
            addCriterion("groups like", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsNotLike(String value) {
            addCriterion("groups not like", value, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsIn(List<String> values) {
            addCriterion("groups in", values, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsNotIn(List<String> values) {
            addCriterion("groups not in", values, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsBetween(String value1, String value2) {
            addCriterion("groups between", value1, value2, "groups");
            return (Criteria) this;
        }

        public Criteria andGroupsNotBetween(String value1, String value2) {
            addCriterion("groups not between", value1, value2, "groups");
            return (Criteria) this;
        }

        public Criteria andFriendsIsNull() {
            addCriterion("friends is null");
            return (Criteria) this;
        }

        public Criteria andFriendsIsNotNull() {
            addCriterion("friends is not null");
            return (Criteria) this;
        }

        public Criteria andFriendsEqualTo(String value) {
            addCriterion("friends =", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsNotEqualTo(String value) {
            addCriterion("friends <>", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsGreaterThan(String value) {
            addCriterion("friends >", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsGreaterThanOrEqualTo(String value) {
            addCriterion("friends >=", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsLessThan(String value) {
            addCriterion("friends <", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsLessThanOrEqualTo(String value) {
            addCriterion("friends <=", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsLike(String value) {
            addCriterion("friends like", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsNotLike(String value) {
            addCriterion("friends not like", value, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsIn(List<String> values) {
            addCriterion("friends in", values, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsNotIn(List<String> values) {
            addCriterion("friends not in", values, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsBetween(String value1, String value2) {
            addCriterion("friends between", value1, value2, "friends");
            return (Criteria) this;
        }

        public Criteria andFriendsNotBetween(String value1, String value2) {
            addCriterion("friends not between", value1, value2, "friends");
            return (Criteria) this;
        }

        public Criteria andLastFriendsUpdateTimeIsNull() {
            addCriterion("last_friends_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLastFriendsUpdateTimeIsNotNull() {
            addCriterion("last_friends_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastFriendsUpdateTimeEqualTo(Date value) {
            addCriterion("last_friends_update_time =", value, "lastFriendsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastFriendsUpdateTimeNotEqualTo(Date value) {
            addCriterion("last_friends_update_time <>", value, "lastFriendsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastFriendsUpdateTimeGreaterThan(Date value) {
            addCriterion("last_friends_update_time >", value, "lastFriendsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastFriendsUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_friends_update_time >=", value, "lastFriendsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastFriendsUpdateTimeLessThan(Date value) {
            addCriterion("last_friends_update_time <", value, "lastFriendsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastFriendsUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_friends_update_time <=", value, "lastFriendsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastFriendsUpdateTimeIn(List<Date> values) {
            addCriterion("last_friends_update_time in", values, "lastFriendsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastFriendsUpdateTimeNotIn(List<Date> values) {
            addCriterion("last_friends_update_time not in", values, "lastFriendsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastFriendsUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("last_friends_update_time between", value1, value2, "lastFriendsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastFriendsUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_friends_update_time not between", value1, value2, "lastFriendsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastGroupsUpdateTimeIsNull() {
            addCriterion("last_groups_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLastGroupsUpdateTimeIsNotNull() {
            addCriterion("last_groups_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastGroupsUpdateTimeEqualTo(Date value) {
            addCriterion("last_groups_update_time =", value, "lastGroupsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastGroupsUpdateTimeNotEqualTo(Date value) {
            addCriterion("last_groups_update_time <>", value, "lastGroupsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastGroupsUpdateTimeGreaterThan(Date value) {
            addCriterion("last_groups_update_time >", value, "lastGroupsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastGroupsUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_groups_update_time >=", value, "lastGroupsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastGroupsUpdateTimeLessThan(Date value) {
            addCriterion("last_groups_update_time <", value, "lastGroupsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastGroupsUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_groups_update_time <=", value, "lastGroupsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastGroupsUpdateTimeIn(List<Date> values) {
            addCriterion("last_groups_update_time in", values, "lastGroupsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastGroupsUpdateTimeNotIn(List<Date> values) {
            addCriterion("last_groups_update_time not in", values, "lastGroupsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastGroupsUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("last_groups_update_time between", value1, value2, "lastGroupsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastGroupsUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_groups_update_time not between", value1, value2, "lastGroupsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andUserInfoIdIsNull() {
            addCriterion("user_info_id is null");
            return (Criteria) this;
        }

        public Criteria andUserInfoIdIsNotNull() {
            addCriterion("user_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserInfoIdEqualTo(Integer value) {
            addCriterion("user_info_id =", value, "userInfoId");
            return (Criteria) this;
        }

        public Criteria andUserInfoIdNotEqualTo(Integer value) {
            addCriterion("user_info_id <>", value, "userInfoId");
            return (Criteria) this;
        }

        public Criteria andUserInfoIdGreaterThan(Integer value) {
            addCriterion("user_info_id >", value, "userInfoId");
            return (Criteria) this;
        }

        public Criteria andUserInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_info_id >=", value, "userInfoId");
            return (Criteria) this;
        }

        public Criteria andUserInfoIdLessThan(Integer value) {
            addCriterion("user_info_id <", value, "userInfoId");
            return (Criteria) this;
        }

        public Criteria andUserInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_info_id <=", value, "userInfoId");
            return (Criteria) this;
        }

        public Criteria andUserInfoIdIn(List<Integer> values) {
            addCriterion("user_info_id in", values, "userInfoId");
            return (Criteria) this;
        }

        public Criteria andUserInfoIdNotIn(List<Integer> values) {
            addCriterion("user_info_id not in", values, "userInfoId");
            return (Criteria) this;
        }

        public Criteria andUserInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("user_info_id between", value1, value2, "userInfoId");
            return (Criteria) this;
        }

        public Criteria andUserInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_info_id not between", value1, value2, "userInfoId");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}