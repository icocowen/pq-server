package com.iwen.pqserver.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PqChatGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PqChatGroupExample() {
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

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andMenbersIsNull() {
            addCriterion("menbers is null");
            return (Criteria) this;
        }

        public Criteria andMenbersIsNotNull() {
            addCriterion("menbers is not null");
            return (Criteria) this;
        }

        public Criteria andMenbersEqualTo(String value) {
            addCriterion("menbers =", value, "menbers");
            return (Criteria) this;
        }

        public Criteria andMenbersNotEqualTo(String value) {
            addCriterion("menbers <>", value, "menbers");
            return (Criteria) this;
        }

        public Criteria andMenbersGreaterThan(String value) {
            addCriterion("menbers >", value, "menbers");
            return (Criteria) this;
        }

        public Criteria andMenbersGreaterThanOrEqualTo(String value) {
            addCriterion("menbers >=", value, "menbers");
            return (Criteria) this;
        }

        public Criteria andMenbersLessThan(String value) {
            addCriterion("menbers <", value, "menbers");
            return (Criteria) this;
        }

        public Criteria andMenbersLessThanOrEqualTo(String value) {
            addCriterion("menbers <=", value, "menbers");
            return (Criteria) this;
        }

        public Criteria andMenbersLike(String value) {
            addCriterion("menbers like", value, "menbers");
            return (Criteria) this;
        }

        public Criteria andMenbersNotLike(String value) {
            addCriterion("menbers not like", value, "menbers");
            return (Criteria) this;
        }

        public Criteria andMenbersIn(List<String> values) {
            addCriterion("menbers in", values, "menbers");
            return (Criteria) this;
        }

        public Criteria andMenbersNotIn(List<String> values) {
            addCriterion("menbers not in", values, "menbers");
            return (Criteria) this;
        }

        public Criteria andMenbersBetween(String value1, String value2) {
            addCriterion("menbers between", value1, value2, "menbers");
            return (Criteria) this;
        }

        public Criteria andMenbersNotBetween(String value1, String value2) {
            addCriterion("menbers not between", value1, value2, "menbers");
            return (Criteria) this;
        }

        public Criteria andLastMenberUpdateTimeIsNull() {
            addCriterion("last_menber_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLastMenberUpdateTimeIsNotNull() {
            addCriterion("last_menber_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastMenberUpdateTimeEqualTo(Date value) {
            addCriterion("last_menber_update_time =", value, "lastMenberUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastMenberUpdateTimeNotEqualTo(Date value) {
            addCriterion("last_menber_update_time <>", value, "lastMenberUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastMenberUpdateTimeGreaterThan(Date value) {
            addCriterion("last_menber_update_time >", value, "lastMenberUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastMenberUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_menber_update_time >=", value, "lastMenberUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastMenberUpdateTimeLessThan(Date value) {
            addCriterion("last_menber_update_time <", value, "lastMenberUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastMenberUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_menber_update_time <=", value, "lastMenberUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastMenberUpdateTimeIn(List<Date> values) {
            addCriterion("last_menber_update_time in", values, "lastMenberUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastMenberUpdateTimeNotIn(List<Date> values) {
            addCriterion("last_menber_update_time not in", values, "lastMenberUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastMenberUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("last_menber_update_time between", value1, value2, "lastMenberUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastMenberUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_menber_update_time not between", value1, value2, "lastMenberUpdateTime");
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