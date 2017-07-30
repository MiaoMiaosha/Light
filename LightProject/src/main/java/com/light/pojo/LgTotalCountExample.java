package com.light.pojo;

import java.util.ArrayList;
import java.util.List;

public class LgTotalCountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LgTotalCountExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andDateNoIsNull() {
            addCriterion("date_no is null");
            return (Criteria) this;
        }

        public Criteria andDateNoIsNotNull() {
            addCriterion("date_no is not null");
            return (Criteria) this;
        }

        public Criteria andDateNoEqualTo(Integer value) {
            addCriterion("date_no =", value, "dateNo");
            return (Criteria) this;
        }

        public Criteria andDateNoNotEqualTo(Integer value) {
            addCriterion("date_no <>", value, "dateNo");
            return (Criteria) this;
        }

        public Criteria andDateNoGreaterThan(Integer value) {
            addCriterion("date_no >", value, "dateNo");
            return (Criteria) this;
        }

        public Criteria andDateNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("date_no >=", value, "dateNo");
            return (Criteria) this;
        }

        public Criteria andDateNoLessThan(Integer value) {
            addCriterion("date_no <", value, "dateNo");
            return (Criteria) this;
        }

        public Criteria andDateNoLessThanOrEqualTo(Integer value) {
            addCriterion("date_no <=", value, "dateNo");
            return (Criteria) this;
        }

        public Criteria andDateNoIn(List<Integer> values) {
            addCriterion("date_no in", values, "dateNo");
            return (Criteria) this;
        }

        public Criteria andDateNoNotIn(List<Integer> values) {
            addCriterion("date_no not in", values, "dateNo");
            return (Criteria) this;
        }

        public Criteria andDateNoBetween(Integer value1, Integer value2) {
            addCriterion("date_no between", value1, value2, "dateNo");
            return (Criteria) this;
        }

        public Criteria andDateNoNotBetween(Integer value1, Integer value2) {
            addCriterion("date_no not between", value1, value2, "dateNo");
            return (Criteria) this;
        }

        public Criteria andTotalSalaryIsNull() {
            addCriterion("total_salary is null");
            return (Criteria) this;
        }

        public Criteria andTotalSalaryIsNotNull() {
            addCriterion("total_salary is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSalaryEqualTo(Integer value) {
            addCriterion("total_salary =", value, "totalSalary");
            return (Criteria) this;
        }

        public Criteria andTotalSalaryNotEqualTo(Integer value) {
            addCriterion("total_salary <>", value, "totalSalary");
            return (Criteria) this;
        }

        public Criteria andTotalSalaryGreaterThan(Integer value) {
            addCriterion("total_salary >", value, "totalSalary");
            return (Criteria) this;
        }

        public Criteria andTotalSalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_salary >=", value, "totalSalary");
            return (Criteria) this;
        }

        public Criteria andTotalSalaryLessThan(Integer value) {
            addCriterion("total_salary <", value, "totalSalary");
            return (Criteria) this;
        }

        public Criteria andTotalSalaryLessThanOrEqualTo(Integer value) {
            addCriterion("total_salary <=", value, "totalSalary");
            return (Criteria) this;
        }

        public Criteria andTotalSalaryIn(List<Integer> values) {
            addCriterion("total_salary in", values, "totalSalary");
            return (Criteria) this;
        }

        public Criteria andTotalSalaryNotIn(List<Integer> values) {
            addCriterion("total_salary not in", values, "totalSalary");
            return (Criteria) this;
        }

        public Criteria andTotalSalaryBetween(Integer value1, Integer value2) {
            addCriterion("total_salary between", value1, value2, "totalSalary");
            return (Criteria) this;
        }

        public Criteria andTotalSalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("total_salary not between", value1, value2, "totalSalary");
            return (Criteria) this;
        }

        public Criteria andCommissionIsNull() {
            addCriterion("commission is null");
            return (Criteria) this;
        }

        public Criteria andCommissionIsNotNull() {
            addCriterion("commission is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionEqualTo(Integer value) {
            addCriterion("commission =", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotEqualTo(Integer value) {
            addCriterion("commission <>", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionGreaterThan(Integer value) {
            addCriterion("commission >", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionGreaterThanOrEqualTo(Integer value) {
            addCriterion("commission >=", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionLessThan(Integer value) {
            addCriterion("commission <", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionLessThanOrEqualTo(Integer value) {
            addCriterion("commission <=", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionIn(List<Integer> values) {
            addCriterion("commission in", values, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotIn(List<Integer> values) {
            addCriterion("commission not in", values, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionBetween(Integer value1, Integer value2) {
            addCriterion("commission between", value1, value2, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotBetween(Integer value1, Integer value2) {
            addCriterion("commission not between", value1, value2, "commission");
            return (Criteria) this;
        }

        public Criteria andRewardIsNull() {
            addCriterion("reward is null");
            return (Criteria) this;
        }

        public Criteria andRewardIsNotNull() {
            addCriterion("reward is not null");
            return (Criteria) this;
        }

        public Criteria andRewardEqualTo(Integer value) {
            addCriterion("reward =", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardNotEqualTo(Integer value) {
            addCriterion("reward <>", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardGreaterThan(Integer value) {
            addCriterion("reward >", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardGreaterThanOrEqualTo(Integer value) {
            addCriterion("reward >=", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardLessThan(Integer value) {
            addCriterion("reward <", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardLessThanOrEqualTo(Integer value) {
            addCriterion("reward <=", value, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardIn(List<Integer> values) {
            addCriterion("reward in", values, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardNotIn(List<Integer> values) {
            addCriterion("reward not in", values, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardBetween(Integer value1, Integer value2) {
            addCriterion("reward between", value1, value2, "reward");
            return (Criteria) this;
        }

        public Criteria andRewardNotBetween(Integer value1, Integer value2) {
            addCriterion("reward not between", value1, value2, "reward");
            return (Criteria) this;
        }

        public Criteria andTravelMoneyIsNull() {
            addCriterion("travel_money is null");
            return (Criteria) this;
        }

        public Criteria andTravelMoneyIsNotNull() {
            addCriterion("travel_money is not null");
            return (Criteria) this;
        }

        public Criteria andTravelMoneyEqualTo(Integer value) {
            addCriterion("travel_money =", value, "travelMoney");
            return (Criteria) this;
        }

        public Criteria andTravelMoneyNotEqualTo(Integer value) {
            addCriterion("travel_money <>", value, "travelMoney");
            return (Criteria) this;
        }

        public Criteria andTravelMoneyGreaterThan(Integer value) {
            addCriterion("travel_money >", value, "travelMoney");
            return (Criteria) this;
        }

        public Criteria andTravelMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("travel_money >=", value, "travelMoney");
            return (Criteria) this;
        }

        public Criteria andTravelMoneyLessThan(Integer value) {
            addCriterion("travel_money <", value, "travelMoney");
            return (Criteria) this;
        }

        public Criteria andTravelMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("travel_money <=", value, "travelMoney");
            return (Criteria) this;
        }

        public Criteria andTravelMoneyIn(List<Integer> values) {
            addCriterion("travel_money in", values, "travelMoney");
            return (Criteria) this;
        }

        public Criteria andTravelMoneyNotIn(List<Integer> values) {
            addCriterion("travel_money not in", values, "travelMoney");
            return (Criteria) this;
        }

        public Criteria andTravelMoneyBetween(Integer value1, Integer value2) {
            addCriterion("travel_money between", value1, value2, "travelMoney");
            return (Criteria) this;
        }

        public Criteria andTravelMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("travel_money not between", value1, value2, "travelMoney");
            return (Criteria) this;
        }

        public Criteria andBonusIsNull() {
            addCriterion("bonus is null");
            return (Criteria) this;
        }

        public Criteria andBonusIsNotNull() {
            addCriterion("bonus is not null");
            return (Criteria) this;
        }

        public Criteria andBonusEqualTo(Integer value) {
            addCriterion("bonus =", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusNotEqualTo(Integer value) {
            addCriterion("bonus <>", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusGreaterThan(Integer value) {
            addCriterion("bonus >", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusGreaterThanOrEqualTo(Integer value) {
            addCriterion("bonus >=", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusLessThan(Integer value) {
            addCriterion("bonus <", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusLessThanOrEqualTo(Integer value) {
            addCriterion("bonus <=", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusIn(List<Integer> values) {
            addCriterion("bonus in", values, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusNotIn(List<Integer> values) {
            addCriterion("bonus not in", values, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusBetween(Integer value1, Integer value2) {
            addCriterion("bonus between", value1, value2, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusNotBetween(Integer value1, Integer value2) {
            addCriterion("bonus not between", value1, value2, "bonus");
            return (Criteria) this;
        }

        public Criteria andPersonSalaryIsNull() {
            addCriterion("person_salary is null");
            return (Criteria) this;
        }

        public Criteria andPersonSalaryIsNotNull() {
            addCriterion("person_salary is not null");
            return (Criteria) this;
        }

        public Criteria andPersonSalaryEqualTo(Integer value) {
            addCriterion("person_salary =", value, "personSalary");
            return (Criteria) this;
        }

        public Criteria andPersonSalaryNotEqualTo(Integer value) {
            addCriterion("person_salary <>", value, "personSalary");
            return (Criteria) this;
        }

        public Criteria andPersonSalaryGreaterThan(Integer value) {
            addCriterion("person_salary >", value, "personSalary");
            return (Criteria) this;
        }

        public Criteria andPersonSalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("person_salary >=", value, "personSalary");
            return (Criteria) this;
        }

        public Criteria andPersonSalaryLessThan(Integer value) {
            addCriterion("person_salary <", value, "personSalary");
            return (Criteria) this;
        }

        public Criteria andPersonSalaryLessThanOrEqualTo(Integer value) {
            addCriterion("person_salary <=", value, "personSalary");
            return (Criteria) this;
        }

        public Criteria andPersonSalaryIn(List<Integer> values) {
            addCriterion("person_salary in", values, "personSalary");
            return (Criteria) this;
        }

        public Criteria andPersonSalaryNotIn(List<Integer> values) {
            addCriterion("person_salary not in", values, "personSalary");
            return (Criteria) this;
        }

        public Criteria andPersonSalaryBetween(Integer value1, Integer value2) {
            addCriterion("person_salary between", value1, value2, "personSalary");
            return (Criteria) this;
        }

        public Criteria andPersonSalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("person_salary not between", value1, value2, "personSalary");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOtherMoneyIsNull() {
            addCriterion("other_money is null");
            return (Criteria) this;
        }

        public Criteria andOtherMoneyIsNotNull() {
            addCriterion("other_money is not null");
            return (Criteria) this;
        }

        public Criteria andOtherMoneyEqualTo(Integer value) {
            addCriterion("other_money =", value, "otherMoney");
            return (Criteria) this;
        }

        public Criteria andOtherMoneyNotEqualTo(Integer value) {
            addCriterion("other_money <>", value, "otherMoney");
            return (Criteria) this;
        }

        public Criteria andOtherMoneyGreaterThan(Integer value) {
            addCriterion("other_money >", value, "otherMoney");
            return (Criteria) this;
        }

        public Criteria andOtherMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("other_money >=", value, "otherMoney");
            return (Criteria) this;
        }

        public Criteria andOtherMoneyLessThan(Integer value) {
            addCriterion("other_money <", value, "otherMoney");
            return (Criteria) this;
        }

        public Criteria andOtherMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("other_money <=", value, "otherMoney");
            return (Criteria) this;
        }

        public Criteria andOtherMoneyIn(List<Integer> values) {
            addCriterion("other_money in", values, "otherMoney");
            return (Criteria) this;
        }

        public Criteria andOtherMoneyNotIn(List<Integer> values) {
            addCriterion("other_money not in", values, "otherMoney");
            return (Criteria) this;
        }

        public Criteria andOtherMoneyBetween(Integer value1, Integer value2) {
            addCriterion("other_money between", value1, value2, "otherMoney");
            return (Criteria) this;
        }

        public Criteria andOtherMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("other_money not between", value1, value2, "otherMoney");
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