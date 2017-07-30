package com.light.pojo;

import java.util.ArrayList;
import java.util.List;

public class LgBonusLevelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LgBonusLevelExample() {
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

        public Criteria andBonusRateIsNull() {
            addCriterion("bonus_rate is null");
            return (Criteria) this;
        }

        public Criteria andBonusRateIsNotNull() {
            addCriterion("bonus_rate is not null");
            return (Criteria) this;
        }

        public Criteria andBonusRateEqualTo(Integer value) {
            addCriterion("bonus_rate =", value, "bonusRate");
            return (Criteria) this;
        }

        public Criteria andBonusRateNotEqualTo(Integer value) {
            addCriterion("bonus_rate <>", value, "bonusRate");
            return (Criteria) this;
        }

        public Criteria andBonusRateGreaterThan(Integer value) {
            addCriterion("bonus_rate >", value, "bonusRate");
            return (Criteria) this;
        }

        public Criteria andBonusRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("bonus_rate >=", value, "bonusRate");
            return (Criteria) this;
        }

        public Criteria andBonusRateLessThan(Integer value) {
            addCriterion("bonus_rate <", value, "bonusRate");
            return (Criteria) this;
        }

        public Criteria andBonusRateLessThanOrEqualTo(Integer value) {
            addCriterion("bonus_rate <=", value, "bonusRate");
            return (Criteria) this;
        }

        public Criteria andBonusRateIn(List<Integer> values) {
            addCriterion("bonus_rate in", values, "bonusRate");
            return (Criteria) this;
        }

        public Criteria andBonusRateNotIn(List<Integer> values) {
            addCriterion("bonus_rate not in", values, "bonusRate");
            return (Criteria) this;
        }

        public Criteria andBonusRateBetween(Integer value1, Integer value2) {
            addCriterion("bonus_rate between", value1, value2, "bonusRate");
            return (Criteria) this;
        }

        public Criteria andBonusRateNotBetween(Integer value1, Integer value2) {
            addCriterion("bonus_rate not between", value1, value2, "bonusRate");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameIsNull() {
            addCriterion("bonus_level_name is null");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameIsNotNull() {
            addCriterion("bonus_level_name is not null");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameEqualTo(String value) {
            addCriterion("bonus_level_name =", value, "bonusLevelName");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameNotEqualTo(String value) {
            addCriterion("bonus_level_name <>", value, "bonusLevelName");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameGreaterThan(String value) {
            addCriterion("bonus_level_name >", value, "bonusLevelName");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("bonus_level_name >=", value, "bonusLevelName");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameLessThan(String value) {
            addCriterion("bonus_level_name <", value, "bonusLevelName");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameLessThanOrEqualTo(String value) {
            addCriterion("bonus_level_name <=", value, "bonusLevelName");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameLike(String value) {
            addCriterion("bonus_level_name like", value, "bonusLevelName");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameNotLike(String value) {
            addCriterion("bonus_level_name not like", value, "bonusLevelName");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameIn(List<String> values) {
            addCriterion("bonus_level_name in", values, "bonusLevelName");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameNotIn(List<String> values) {
            addCriterion("bonus_level_name not in", values, "bonusLevelName");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameBetween(String value1, String value2) {
            addCriterion("bonus_level_name between", value1, value2, "bonusLevelName");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNameNotBetween(String value1, String value2) {
            addCriterion("bonus_level_name not between", value1, value2, "bonusLevelName");
            return (Criteria) this;
        }

        public Criteria andBonusLevelMoneyIsNull() {
            addCriterion("bonus_level_money is null");
            return (Criteria) this;
        }

        public Criteria andBonusLevelMoneyIsNotNull() {
            addCriterion("bonus_level_money is not null");
            return (Criteria) this;
        }

        public Criteria andBonusLevelMoneyEqualTo(Integer value) {
            addCriterion("bonus_level_money =", value, "bonusLevelMoney");
            return (Criteria) this;
        }

        public Criteria andBonusLevelMoneyNotEqualTo(Integer value) {
            addCriterion("bonus_level_money <>", value, "bonusLevelMoney");
            return (Criteria) this;
        }

        public Criteria andBonusLevelMoneyGreaterThan(Integer value) {
            addCriterion("bonus_level_money >", value, "bonusLevelMoney");
            return (Criteria) this;
        }

        public Criteria andBonusLevelMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("bonus_level_money >=", value, "bonusLevelMoney");
            return (Criteria) this;
        }

        public Criteria andBonusLevelMoneyLessThan(Integer value) {
            addCriterion("bonus_level_money <", value, "bonusLevelMoney");
            return (Criteria) this;
        }

        public Criteria andBonusLevelMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("bonus_level_money <=", value, "bonusLevelMoney");
            return (Criteria) this;
        }

        public Criteria andBonusLevelMoneyIn(List<Integer> values) {
            addCriterion("bonus_level_money in", values, "bonusLevelMoney");
            return (Criteria) this;
        }

        public Criteria andBonusLevelMoneyNotIn(List<Integer> values) {
            addCriterion("bonus_level_money not in", values, "bonusLevelMoney");
            return (Criteria) this;
        }

        public Criteria andBonusLevelMoneyBetween(Integer value1, Integer value2) {
            addCriterion("bonus_level_money between", value1, value2, "bonusLevelMoney");
            return (Criteria) this;
        }

        public Criteria andBonusLevelMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("bonus_level_money not between", value1, value2, "bonusLevelMoney");
            return (Criteria) this;
        }
    }

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