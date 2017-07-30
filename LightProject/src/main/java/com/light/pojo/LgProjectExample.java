package com.light.pojo;

import java.util.ArrayList;
import java.util.List;

public class LgProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LgProjectExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
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

        public Criteria andCreateTimeEqualTo(Integer value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Integer value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Integer value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Integer value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Integer value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Integer> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Integer> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Integer value1, Integer value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andMarketIdIsNull() {
            addCriterion("market_id is null");
            return (Criteria) this;
        }

        public Criteria andMarketIdIsNotNull() {
            addCriterion("market_id is not null");
            return (Criteria) this;
        }

        public Criteria andMarketIdEqualTo(Integer value) {
            addCriterion("market_id =", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotEqualTo(Integer value) {
            addCriterion("market_id <>", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdGreaterThan(Integer value) {
            addCriterion("market_id >", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("market_id >=", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdLessThan(Integer value) {
            addCriterion("market_id <", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdLessThanOrEqualTo(Integer value) {
            addCriterion("market_id <=", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdIn(List<Integer> values) {
            addCriterion("market_id in", values, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotIn(List<Integer> values) {
            addCriterion("market_id not in", values, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdBetween(Integer value1, Integer value2) {
            addCriterion("market_id between", value1, value2, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotBetween(Integer value1, Integer value2) {
            addCriterion("market_id not between", value1, value2, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketNameIsNull() {
            addCriterion("market_name is null");
            return (Criteria) this;
        }

        public Criteria andMarketNameIsNotNull() {
            addCriterion("market_name is not null");
            return (Criteria) this;
        }

        public Criteria andMarketNameEqualTo(String value) {
            addCriterion("market_name =", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameNotEqualTo(String value) {
            addCriterion("market_name <>", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameGreaterThan(String value) {
            addCriterion("market_name >", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameGreaterThanOrEqualTo(String value) {
            addCriterion("market_name >=", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameLessThan(String value) {
            addCriterion("market_name <", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameLessThanOrEqualTo(String value) {
            addCriterion("market_name <=", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameLike(String value) {
            addCriterion("market_name like", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameNotLike(String value) {
            addCriterion("market_name not like", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameIn(List<String> values) {
            addCriterion("market_name in", values, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameNotIn(List<String> values) {
            addCriterion("market_name not in", values, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameBetween(String value1, String value2) {
            addCriterion("market_name between", value1, value2, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameNotBetween(String value1, String value2) {
            addCriterion("market_name not between", value1, value2, "marketName");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Integer value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Integer value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Integer value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Integer value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Integer> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Integer> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andRealContactMoneyIsNull() {
            addCriterion("real_contact_money is null");
            return (Criteria) this;
        }

        public Criteria andRealContactMoneyIsNotNull() {
            addCriterion("real_contact_money is not null");
            return (Criteria) this;
        }

        public Criteria andRealContactMoneyEqualTo(Integer value) {
            addCriterion("real_contact_money =", value, "realContactMoney");
            return (Criteria) this;
        }

        public Criteria andRealContactMoneyNotEqualTo(Integer value) {
            addCriterion("real_contact_money <>", value, "realContactMoney");
            return (Criteria) this;
        }

        public Criteria andRealContactMoneyGreaterThan(Integer value) {
            addCriterion("real_contact_money >", value, "realContactMoney");
            return (Criteria) this;
        }

        public Criteria andRealContactMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_contact_money >=", value, "realContactMoney");
            return (Criteria) this;
        }

        public Criteria andRealContactMoneyLessThan(Integer value) {
            addCriterion("real_contact_money <", value, "realContactMoney");
            return (Criteria) this;
        }

        public Criteria andRealContactMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("real_contact_money <=", value, "realContactMoney");
            return (Criteria) this;
        }

        public Criteria andRealContactMoneyIn(List<Integer> values) {
            addCriterion("real_contact_money in", values, "realContactMoney");
            return (Criteria) this;
        }

        public Criteria andRealContactMoneyNotIn(List<Integer> values) {
            addCriterion("real_contact_money not in", values, "realContactMoney");
            return (Criteria) this;
        }

        public Criteria andRealContactMoneyBetween(Integer value1, Integer value2) {
            addCriterion("real_contact_money between", value1, value2, "realContactMoney");
            return (Criteria) this;
        }

        public Criteria andRealContactMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("real_contact_money not between", value1, value2, "realContactMoney");
            return (Criteria) this;
        }

        public Criteria andContactMoneyIsNull() {
            addCriterion("contact_money is null");
            return (Criteria) this;
        }

        public Criteria andContactMoneyIsNotNull() {
            addCriterion("contact_money is not null");
            return (Criteria) this;
        }

        public Criteria andContactMoneyEqualTo(Integer value) {
            addCriterion("contact_money =", value, "contactMoney");
            return (Criteria) this;
        }

        public Criteria andContactMoneyNotEqualTo(Integer value) {
            addCriterion("contact_money <>", value, "contactMoney");
            return (Criteria) this;
        }

        public Criteria andContactMoneyGreaterThan(Integer value) {
            addCriterion("contact_money >", value, "contactMoney");
            return (Criteria) this;
        }

        public Criteria andContactMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("contact_money >=", value, "contactMoney");
            return (Criteria) this;
        }

        public Criteria andContactMoneyLessThan(Integer value) {
            addCriterion("contact_money <", value, "contactMoney");
            return (Criteria) this;
        }

        public Criteria andContactMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("contact_money <=", value, "contactMoney");
            return (Criteria) this;
        }

        public Criteria andContactMoneyIn(List<Integer> values) {
            addCriterion("contact_money in", values, "contactMoney");
            return (Criteria) this;
        }

        public Criteria andContactMoneyNotIn(List<Integer> values) {
            addCriterion("contact_money not in", values, "contactMoney");
            return (Criteria) this;
        }

        public Criteria andContactMoneyBetween(Integer value1, Integer value2) {
            addCriterion("contact_money between", value1, value2, "contactMoney");
            return (Criteria) this;
        }

        public Criteria andContactMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("contact_money not between", value1, value2, "contactMoney");
            return (Criteria) this;
        }

        public Criteria andFirstMoneyIsNull() {
            addCriterion("first_money is null");
            return (Criteria) this;
        }

        public Criteria andFirstMoneyIsNotNull() {
            addCriterion("first_money is not null");
            return (Criteria) this;
        }

        public Criteria andFirstMoneyEqualTo(Integer value) {
            addCriterion("first_money =", value, "firstMoney");
            return (Criteria) this;
        }

        public Criteria andFirstMoneyNotEqualTo(Integer value) {
            addCriterion("first_money <>", value, "firstMoney");
            return (Criteria) this;
        }

        public Criteria andFirstMoneyGreaterThan(Integer value) {
            addCriterion("first_money >", value, "firstMoney");
            return (Criteria) this;
        }

        public Criteria andFirstMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("first_money >=", value, "firstMoney");
            return (Criteria) this;
        }

        public Criteria andFirstMoneyLessThan(Integer value) {
            addCriterion("first_money <", value, "firstMoney");
            return (Criteria) this;
        }

        public Criteria andFirstMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("first_money <=", value, "firstMoney");
            return (Criteria) this;
        }

        public Criteria andFirstMoneyIn(List<Integer> values) {
            addCriterion("first_money in", values, "firstMoney");
            return (Criteria) this;
        }

        public Criteria andFirstMoneyNotIn(List<Integer> values) {
            addCriterion("first_money not in", values, "firstMoney");
            return (Criteria) this;
        }

        public Criteria andFirstMoneyBetween(Integer value1, Integer value2) {
            addCriterion("first_money between", value1, value2, "firstMoney");
            return (Criteria) this;
        }

        public Criteria andFirstMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("first_money not between", value1, value2, "firstMoney");
            return (Criteria) this;
        }

        public Criteria andFirstFinishTimeIsNull() {
            addCriterion("first_finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFirstFinishTimeIsNotNull() {
            addCriterion("first_finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFirstFinishTimeEqualTo(Integer value) {
            addCriterion("first_finish_time =", value, "firstFinishTime");
            return (Criteria) this;
        }

        public Criteria andFirstFinishTimeNotEqualTo(Integer value) {
            addCriterion("first_finish_time <>", value, "firstFinishTime");
            return (Criteria) this;
        }

        public Criteria andFirstFinishTimeGreaterThan(Integer value) {
            addCriterion("first_finish_time >", value, "firstFinishTime");
            return (Criteria) this;
        }

        public Criteria andFirstFinishTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("first_finish_time >=", value, "firstFinishTime");
            return (Criteria) this;
        }

        public Criteria andFirstFinishTimeLessThan(Integer value) {
            addCriterion("first_finish_time <", value, "firstFinishTime");
            return (Criteria) this;
        }

        public Criteria andFirstFinishTimeLessThanOrEqualTo(Integer value) {
            addCriterion("first_finish_time <=", value, "firstFinishTime");
            return (Criteria) this;
        }

        public Criteria andFirstFinishTimeIn(List<Integer> values) {
            addCriterion("first_finish_time in", values, "firstFinishTime");
            return (Criteria) this;
        }

        public Criteria andFirstFinishTimeNotIn(List<Integer> values) {
            addCriterion("first_finish_time not in", values, "firstFinishTime");
            return (Criteria) this;
        }

        public Criteria andFirstFinishTimeBetween(Integer value1, Integer value2) {
            addCriterion("first_finish_time between", value1, value2, "firstFinishTime");
            return (Criteria) this;
        }

        public Criteria andFirstFinishTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("first_finish_time not between", value1, value2, "firstFinishTime");
            return (Criteria) this;
        }

        public Criteria andSecondMoneyIsNull() {
            addCriterion("second_money is null");
            return (Criteria) this;
        }

        public Criteria andSecondMoneyIsNotNull() {
            addCriterion("second_money is not null");
            return (Criteria) this;
        }

        public Criteria andSecondMoneyEqualTo(Integer value) {
            addCriterion("second_money =", value, "secondMoney");
            return (Criteria) this;
        }

        public Criteria andSecondMoneyNotEqualTo(Integer value) {
            addCriterion("second_money <>", value, "secondMoney");
            return (Criteria) this;
        }

        public Criteria andSecondMoneyGreaterThan(Integer value) {
            addCriterion("second_money >", value, "secondMoney");
            return (Criteria) this;
        }

        public Criteria andSecondMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("second_money >=", value, "secondMoney");
            return (Criteria) this;
        }

        public Criteria andSecondMoneyLessThan(Integer value) {
            addCriterion("second_money <", value, "secondMoney");
            return (Criteria) this;
        }

        public Criteria andSecondMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("second_money <=", value, "secondMoney");
            return (Criteria) this;
        }

        public Criteria andSecondMoneyIn(List<Integer> values) {
            addCriterion("second_money in", values, "secondMoney");
            return (Criteria) this;
        }

        public Criteria andSecondMoneyNotIn(List<Integer> values) {
            addCriterion("second_money not in", values, "secondMoney");
            return (Criteria) this;
        }

        public Criteria andSecondMoneyBetween(Integer value1, Integer value2) {
            addCriterion("second_money between", value1, value2, "secondMoney");
            return (Criteria) this;
        }

        public Criteria andSecondMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("second_money not between", value1, value2, "secondMoney");
            return (Criteria) this;
        }

        public Criteria andSecondFinishTimeIsNull() {
            addCriterion("second_finish_time is null");
            return (Criteria) this;
        }

        public Criteria andSecondFinishTimeIsNotNull() {
            addCriterion("second_finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andSecondFinishTimeEqualTo(Integer value) {
            addCriterion("second_finish_time =", value, "secondFinishTime");
            return (Criteria) this;
        }

        public Criteria andSecondFinishTimeNotEqualTo(Integer value) {
            addCriterion("second_finish_time <>", value, "secondFinishTime");
            return (Criteria) this;
        }

        public Criteria andSecondFinishTimeGreaterThan(Integer value) {
            addCriterion("second_finish_time >", value, "secondFinishTime");
            return (Criteria) this;
        }

        public Criteria andSecondFinishTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("second_finish_time >=", value, "secondFinishTime");
            return (Criteria) this;
        }

        public Criteria andSecondFinishTimeLessThan(Integer value) {
            addCriterion("second_finish_time <", value, "secondFinishTime");
            return (Criteria) this;
        }

        public Criteria andSecondFinishTimeLessThanOrEqualTo(Integer value) {
            addCriterion("second_finish_time <=", value, "secondFinishTime");
            return (Criteria) this;
        }

        public Criteria andSecondFinishTimeIn(List<Integer> values) {
            addCriterion("second_finish_time in", values, "secondFinishTime");
            return (Criteria) this;
        }

        public Criteria andSecondFinishTimeNotIn(List<Integer> values) {
            addCriterion("second_finish_time not in", values, "secondFinishTime");
            return (Criteria) this;
        }

        public Criteria andSecondFinishTimeBetween(Integer value1, Integer value2) {
            addCriterion("second_finish_time between", value1, value2, "secondFinishTime");
            return (Criteria) this;
        }

        public Criteria andSecondFinishTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("second_finish_time not between", value1, value2, "secondFinishTime");
            return (Criteria) this;
        }

        public Criteria andThirdMoneyIsNull() {
            addCriterion("third_money is null");
            return (Criteria) this;
        }

        public Criteria andThirdMoneyIsNotNull() {
            addCriterion("third_money is not null");
            return (Criteria) this;
        }

        public Criteria andThirdMoneyEqualTo(Integer value) {
            addCriterion("third_money =", value, "thirdMoney");
            return (Criteria) this;
        }

        public Criteria andThirdMoneyNotEqualTo(Integer value) {
            addCriterion("third_money <>", value, "thirdMoney");
            return (Criteria) this;
        }

        public Criteria andThirdMoneyGreaterThan(Integer value) {
            addCriterion("third_money >", value, "thirdMoney");
            return (Criteria) this;
        }

        public Criteria andThirdMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("third_money >=", value, "thirdMoney");
            return (Criteria) this;
        }

        public Criteria andThirdMoneyLessThan(Integer value) {
            addCriterion("third_money <", value, "thirdMoney");
            return (Criteria) this;
        }

        public Criteria andThirdMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("third_money <=", value, "thirdMoney");
            return (Criteria) this;
        }

        public Criteria andThirdMoneyIn(List<Integer> values) {
            addCriterion("third_money in", values, "thirdMoney");
            return (Criteria) this;
        }

        public Criteria andThirdMoneyNotIn(List<Integer> values) {
            addCriterion("third_money not in", values, "thirdMoney");
            return (Criteria) this;
        }

        public Criteria andThirdMoneyBetween(Integer value1, Integer value2) {
            addCriterion("third_money between", value1, value2, "thirdMoney");
            return (Criteria) this;
        }

        public Criteria andThirdMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("third_money not between", value1, value2, "thirdMoney");
            return (Criteria) this;
        }

        public Criteria andThirdFinishTimeIsNull() {
            addCriterion("third_finish_time is null");
            return (Criteria) this;
        }

        public Criteria andThirdFinishTimeIsNotNull() {
            addCriterion("third_finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andThirdFinishTimeEqualTo(Integer value) {
            addCriterion("third_finish_time =", value, "thirdFinishTime");
            return (Criteria) this;
        }

        public Criteria andThirdFinishTimeNotEqualTo(Integer value) {
            addCriterion("third_finish_time <>", value, "thirdFinishTime");
            return (Criteria) this;
        }

        public Criteria andThirdFinishTimeGreaterThan(Integer value) {
            addCriterion("third_finish_time >", value, "thirdFinishTime");
            return (Criteria) this;
        }

        public Criteria andThirdFinishTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("third_finish_time >=", value, "thirdFinishTime");
            return (Criteria) this;
        }

        public Criteria andThirdFinishTimeLessThan(Integer value) {
            addCriterion("third_finish_time <", value, "thirdFinishTime");
            return (Criteria) this;
        }

        public Criteria andThirdFinishTimeLessThanOrEqualTo(Integer value) {
            addCriterion("third_finish_time <=", value, "thirdFinishTime");
            return (Criteria) this;
        }

        public Criteria andThirdFinishTimeIn(List<Integer> values) {
            addCriterion("third_finish_time in", values, "thirdFinishTime");
            return (Criteria) this;
        }

        public Criteria andThirdFinishTimeNotIn(List<Integer> values) {
            addCriterion("third_finish_time not in", values, "thirdFinishTime");
            return (Criteria) this;
        }

        public Criteria andThirdFinishTimeBetween(Integer value1, Integer value2) {
            addCriterion("third_finish_time between", value1, value2, "thirdFinishTime");
            return (Criteria) this;
        }

        public Criteria andThirdFinishTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("third_finish_time not between", value1, value2, "thirdFinishTime");
            return (Criteria) this;
        }

        public Criteria andFourMoneyIsNull() {
            addCriterion("four_money is null");
            return (Criteria) this;
        }

        public Criteria andFourMoneyIsNotNull() {
            addCriterion("four_money is not null");
            return (Criteria) this;
        }

        public Criteria andFourMoneyEqualTo(Integer value) {
            addCriterion("four_money =", value, "fourMoney");
            return (Criteria) this;
        }

        public Criteria andFourMoneyNotEqualTo(Integer value) {
            addCriterion("four_money <>", value, "fourMoney");
            return (Criteria) this;
        }

        public Criteria andFourMoneyGreaterThan(Integer value) {
            addCriterion("four_money >", value, "fourMoney");
            return (Criteria) this;
        }

        public Criteria andFourMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("four_money >=", value, "fourMoney");
            return (Criteria) this;
        }

        public Criteria andFourMoneyLessThan(Integer value) {
            addCriterion("four_money <", value, "fourMoney");
            return (Criteria) this;
        }

        public Criteria andFourMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("four_money <=", value, "fourMoney");
            return (Criteria) this;
        }

        public Criteria andFourMoneyIn(List<Integer> values) {
            addCriterion("four_money in", values, "fourMoney");
            return (Criteria) this;
        }

        public Criteria andFourMoneyNotIn(List<Integer> values) {
            addCriterion("four_money not in", values, "fourMoney");
            return (Criteria) this;
        }

        public Criteria andFourMoneyBetween(Integer value1, Integer value2) {
            addCriterion("four_money between", value1, value2, "fourMoney");
            return (Criteria) this;
        }

        public Criteria andFourMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("four_money not between", value1, value2, "fourMoney");
            return (Criteria) this;
        }

        public Criteria andFourFinishTimeIsNull() {
            addCriterion("four_finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFourFinishTimeIsNotNull() {
            addCriterion("four_finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFourFinishTimeEqualTo(Integer value) {
            addCriterion("four_finish_time =", value, "fourFinishTime");
            return (Criteria) this;
        }

        public Criteria andFourFinishTimeNotEqualTo(Integer value) {
            addCriterion("four_finish_time <>", value, "fourFinishTime");
            return (Criteria) this;
        }

        public Criteria andFourFinishTimeGreaterThan(Integer value) {
            addCriterion("four_finish_time >", value, "fourFinishTime");
            return (Criteria) this;
        }

        public Criteria andFourFinishTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("four_finish_time >=", value, "fourFinishTime");
            return (Criteria) this;
        }

        public Criteria andFourFinishTimeLessThan(Integer value) {
            addCriterion("four_finish_time <", value, "fourFinishTime");
            return (Criteria) this;
        }

        public Criteria andFourFinishTimeLessThanOrEqualTo(Integer value) {
            addCriterion("four_finish_time <=", value, "fourFinishTime");
            return (Criteria) this;
        }

        public Criteria andFourFinishTimeIn(List<Integer> values) {
            addCriterion("four_finish_time in", values, "fourFinishTime");
            return (Criteria) this;
        }

        public Criteria andFourFinishTimeNotIn(List<Integer> values) {
            addCriterion("four_finish_time not in", values, "fourFinishTime");
            return (Criteria) this;
        }

        public Criteria andFourFinishTimeBetween(Integer value1, Integer value2) {
            addCriterion("four_finish_time between", value1, value2, "fourFinishTime");
            return (Criteria) this;
        }

        public Criteria andFourFinishTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("four_finish_time not between", value1, value2, "fourFinishTime");
            return (Criteria) this;
        }

        public Criteria andFiveMoneyIsNull() {
            addCriterion("five_money is null");
            return (Criteria) this;
        }

        public Criteria andFiveMoneyIsNotNull() {
            addCriterion("five_money is not null");
            return (Criteria) this;
        }

        public Criteria andFiveMoneyEqualTo(Integer value) {
            addCriterion("five_money =", value, "fiveMoney");
            return (Criteria) this;
        }

        public Criteria andFiveMoneyNotEqualTo(Integer value) {
            addCriterion("five_money <>", value, "fiveMoney");
            return (Criteria) this;
        }

        public Criteria andFiveMoneyGreaterThan(Integer value) {
            addCriterion("five_money >", value, "fiveMoney");
            return (Criteria) this;
        }

        public Criteria andFiveMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("five_money >=", value, "fiveMoney");
            return (Criteria) this;
        }

        public Criteria andFiveMoneyLessThan(Integer value) {
            addCriterion("five_money <", value, "fiveMoney");
            return (Criteria) this;
        }

        public Criteria andFiveMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("five_money <=", value, "fiveMoney");
            return (Criteria) this;
        }

        public Criteria andFiveMoneyIn(List<Integer> values) {
            addCriterion("five_money in", values, "fiveMoney");
            return (Criteria) this;
        }

        public Criteria andFiveMoneyNotIn(List<Integer> values) {
            addCriterion("five_money not in", values, "fiveMoney");
            return (Criteria) this;
        }

        public Criteria andFiveMoneyBetween(Integer value1, Integer value2) {
            addCriterion("five_money between", value1, value2, "fiveMoney");
            return (Criteria) this;
        }

        public Criteria andFiveMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("five_money not between", value1, value2, "fiveMoney");
            return (Criteria) this;
        }

        public Criteria andFiveFinishTimeIsNull() {
            addCriterion("five_finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFiveFinishTimeIsNotNull() {
            addCriterion("five_finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFiveFinishTimeEqualTo(Integer value) {
            addCriterion("five_finish_time =", value, "fiveFinishTime");
            return (Criteria) this;
        }

        public Criteria andFiveFinishTimeNotEqualTo(Integer value) {
            addCriterion("five_finish_time <>", value, "fiveFinishTime");
            return (Criteria) this;
        }

        public Criteria andFiveFinishTimeGreaterThan(Integer value) {
            addCriterion("five_finish_time >", value, "fiveFinishTime");
            return (Criteria) this;
        }

        public Criteria andFiveFinishTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("five_finish_time >=", value, "fiveFinishTime");
            return (Criteria) this;
        }

        public Criteria andFiveFinishTimeLessThan(Integer value) {
            addCriterion("five_finish_time <", value, "fiveFinishTime");
            return (Criteria) this;
        }

        public Criteria andFiveFinishTimeLessThanOrEqualTo(Integer value) {
            addCriterion("five_finish_time <=", value, "fiveFinishTime");
            return (Criteria) this;
        }

        public Criteria andFiveFinishTimeIn(List<Integer> values) {
            addCriterion("five_finish_time in", values, "fiveFinishTime");
            return (Criteria) this;
        }

        public Criteria andFiveFinishTimeNotIn(List<Integer> values) {
            addCriterion("five_finish_time not in", values, "fiveFinishTime");
            return (Criteria) this;
        }

        public Criteria andFiveFinishTimeBetween(Integer value1, Integer value2) {
            addCriterion("five_finish_time between", value1, value2, "fiveFinishTime");
            return (Criteria) this;
        }

        public Criteria andFiveFinishTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("five_finish_time not between", value1, value2, "fiveFinishTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andBusTravelTypeIsNull() {
            addCriterion("bus_travel_type is null");
            return (Criteria) this;
        }

        public Criteria andBusTravelTypeIsNotNull() {
            addCriterion("bus_travel_type is not null");
            return (Criteria) this;
        }

        public Criteria andBusTravelTypeEqualTo(Integer value) {
            addCriterion("bus_travel_type =", value, "busTravelType");
            return (Criteria) this;
        }

        public Criteria andBusTravelTypeNotEqualTo(Integer value) {
            addCriterion("bus_travel_type <>", value, "busTravelType");
            return (Criteria) this;
        }

        public Criteria andBusTravelTypeGreaterThan(Integer value) {
            addCriterion("bus_travel_type >", value, "busTravelType");
            return (Criteria) this;
        }

        public Criteria andBusTravelTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("bus_travel_type >=", value, "busTravelType");
            return (Criteria) this;
        }

        public Criteria andBusTravelTypeLessThan(Integer value) {
            addCriterion("bus_travel_type <", value, "busTravelType");
            return (Criteria) this;
        }

        public Criteria andBusTravelTypeLessThanOrEqualTo(Integer value) {
            addCriterion("bus_travel_type <=", value, "busTravelType");
            return (Criteria) this;
        }

        public Criteria andBusTravelTypeIn(List<Integer> values) {
            addCriterion("bus_travel_type in", values, "busTravelType");
            return (Criteria) this;
        }

        public Criteria andBusTravelTypeNotIn(List<Integer> values) {
            addCriterion("bus_travel_type not in", values, "busTravelType");
            return (Criteria) this;
        }

        public Criteria andBusTravelTypeBetween(Integer value1, Integer value2) {
            addCriterion("bus_travel_type between", value1, value2, "busTravelType");
            return (Criteria) this;
        }

        public Criteria andBusTravelTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("bus_travel_type not between", value1, value2, "busTravelType");
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

        public Criteria andRebateMoneyIsNull() {
            addCriterion("rebate_money is null");
            return (Criteria) this;
        }

        public Criteria andRebateMoneyIsNotNull() {
            addCriterion("rebate_money is not null");
            return (Criteria) this;
        }

        public Criteria andRebateMoneyEqualTo(Integer value) {
            addCriterion("rebate_money =", value, "rebateMoney");
            return (Criteria) this;
        }

        public Criteria andRebateMoneyNotEqualTo(Integer value) {
            addCriterion("rebate_money <>", value, "rebateMoney");
            return (Criteria) this;
        }

        public Criteria andRebateMoneyGreaterThan(Integer value) {
            addCriterion("rebate_money >", value, "rebateMoney");
            return (Criteria) this;
        }

        public Criteria andRebateMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("rebate_money >=", value, "rebateMoney");
            return (Criteria) this;
        }

        public Criteria andRebateMoneyLessThan(Integer value) {
            addCriterion("rebate_money <", value, "rebateMoney");
            return (Criteria) this;
        }

        public Criteria andRebateMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("rebate_money <=", value, "rebateMoney");
            return (Criteria) this;
        }

        public Criteria andRebateMoneyIn(List<Integer> values) {
            addCriterion("rebate_money in", values, "rebateMoney");
            return (Criteria) this;
        }

        public Criteria andRebateMoneyNotIn(List<Integer> values) {
            addCriterion("rebate_money not in", values, "rebateMoney");
            return (Criteria) this;
        }

        public Criteria andRebateMoneyBetween(Integer value1, Integer value2) {
            addCriterion("rebate_money between", value1, value2, "rebateMoney");
            return (Criteria) this;
        }

        public Criteria andRebateMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("rebate_money not between", value1, value2, "rebateMoney");
            return (Criteria) this;
        }

        public Criteria andExtraMoneyIsNull() {
            addCriterion("extra_money is null");
            return (Criteria) this;
        }

        public Criteria andExtraMoneyIsNotNull() {
            addCriterion("extra_money is not null");
            return (Criteria) this;
        }

        public Criteria andExtraMoneyEqualTo(Integer value) {
            addCriterion("extra_money =", value, "extraMoney");
            return (Criteria) this;
        }

        public Criteria andExtraMoneyNotEqualTo(Integer value) {
            addCriterion("extra_money <>", value, "extraMoney");
            return (Criteria) this;
        }

        public Criteria andExtraMoneyGreaterThan(Integer value) {
            addCriterion("extra_money >", value, "extraMoney");
            return (Criteria) this;
        }

        public Criteria andExtraMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("extra_money >=", value, "extraMoney");
            return (Criteria) this;
        }

        public Criteria andExtraMoneyLessThan(Integer value) {
            addCriterion("extra_money <", value, "extraMoney");
            return (Criteria) this;
        }

        public Criteria andExtraMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("extra_money <=", value, "extraMoney");
            return (Criteria) this;
        }

        public Criteria andExtraMoneyIn(List<Integer> values) {
            addCriterion("extra_money in", values, "extraMoney");
            return (Criteria) this;
        }

        public Criteria andExtraMoneyNotIn(List<Integer> values) {
            addCriterion("extra_money not in", values, "extraMoney");
            return (Criteria) this;
        }

        public Criteria andExtraMoneyBetween(Integer value1, Integer value2) {
            addCriterion("extra_money between", value1, value2, "extraMoney");
            return (Criteria) this;
        }

        public Criteria andExtraMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("extra_money not between", value1, value2, "extraMoney");
            return (Criteria) this;
        }

        public Criteria andSigningCompanyIsNull() {
            addCriterion("signing_company is null");
            return (Criteria) this;
        }

        public Criteria andSigningCompanyIsNotNull() {
            addCriterion("signing_company is not null");
            return (Criteria) this;
        }

        public Criteria andSigningCompanyEqualTo(Integer value) {
            addCriterion("signing_company =", value, "signingCompany");
            return (Criteria) this;
        }

        public Criteria andSigningCompanyNotEqualTo(Integer value) {
            addCriterion("signing_company <>", value, "signingCompany");
            return (Criteria) this;
        }

        public Criteria andSigningCompanyGreaterThan(Integer value) {
            addCriterion("signing_company >", value, "signingCompany");
            return (Criteria) this;
        }

        public Criteria andSigningCompanyGreaterThanOrEqualTo(Integer value) {
            addCriterion("signing_company >=", value, "signingCompany");
            return (Criteria) this;
        }

        public Criteria andSigningCompanyLessThan(Integer value) {
            addCriterion("signing_company <", value, "signingCompany");
            return (Criteria) this;
        }

        public Criteria andSigningCompanyLessThanOrEqualTo(Integer value) {
            addCriterion("signing_company <=", value, "signingCompany");
            return (Criteria) this;
        }

        public Criteria andSigningCompanyIn(List<Integer> values) {
            addCriterion("signing_company in", values, "signingCompany");
            return (Criteria) this;
        }

        public Criteria andSigningCompanyNotIn(List<Integer> values) {
            addCriterion("signing_company not in", values, "signingCompany");
            return (Criteria) this;
        }

        public Criteria andSigningCompanyBetween(Integer value1, Integer value2) {
            addCriterion("signing_company between", value1, value2, "signingCompany");
            return (Criteria) this;
        }

        public Criteria andSigningCompanyNotBetween(Integer value1, Integer value2) {
            addCriterion("signing_company not between", value1, value2, "signingCompany");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(Integer value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(Integer value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(Integer value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(Integer value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(Integer value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(Integer value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<Integer> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<Integer> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(Integer value1, Integer value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(Integer value1, Integer value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(Integer value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(Integer value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(Integer value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(Integer value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(Integer value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(Integer value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<Integer> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<Integer> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(Integer value1, Integer value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(Integer value1, Integer value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNull() {
            addCriterion("district is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNotNull() {
            addCriterion("district is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictEqualTo(Integer value) {
            addCriterion("district =", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotEqualTo(Integer value) {
            addCriterion("district <>", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThan(Integer value) {
            addCriterion("district >", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThanOrEqualTo(Integer value) {
            addCriterion("district >=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThan(Integer value) {
            addCriterion("district <", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThanOrEqualTo(Integer value) {
            addCriterion("district <=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictIn(List<Integer> values) {
            addCriterion("district in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotIn(List<Integer> values) {
            addCriterion("district not in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictBetween(Integer value1, Integer value2) {
            addCriterion("district between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotBetween(Integer value1, Integer value2) {
            addCriterion("district not between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andTownIsNull() {
            addCriterion("town is null");
            return (Criteria) this;
        }

        public Criteria andTownIsNotNull() {
            addCriterion("town is not null");
            return (Criteria) this;
        }

        public Criteria andTownEqualTo(Integer value) {
            addCriterion("town =", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownNotEqualTo(Integer value) {
            addCriterion("town <>", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownGreaterThan(Integer value) {
            addCriterion("town >", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownGreaterThanOrEqualTo(Integer value) {
            addCriterion("town >=", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownLessThan(Integer value) {
            addCriterion("town <", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownLessThanOrEqualTo(Integer value) {
            addCriterion("town <=", value, "town");
            return (Criteria) this;
        }

        public Criteria andTownIn(List<Integer> values) {
            addCriterion("town in", values, "town");
            return (Criteria) this;
        }

        public Criteria andTownNotIn(List<Integer> values) {
            addCriterion("town not in", values, "town");
            return (Criteria) this;
        }

        public Criteria andTownBetween(Integer value1, Integer value2) {
            addCriterion("town between", value1, value2, "town");
            return (Criteria) this;
        }

        public Criteria andTownNotBetween(Integer value1, Integer value2) {
            addCriterion("town not between", value1, value2, "town");
            return (Criteria) this;
        }

        public Criteria andMarketAddressIsNull() {
            addCriterion("market_address is null");
            return (Criteria) this;
        }

        public Criteria andMarketAddressIsNotNull() {
            addCriterion("market_address is not null");
            return (Criteria) this;
        }

        public Criteria andMarketAddressEqualTo(String value) {
            addCriterion("market_address =", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressNotEqualTo(String value) {
            addCriterion("market_address <>", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressGreaterThan(String value) {
            addCriterion("market_address >", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressGreaterThanOrEqualTo(String value) {
            addCriterion("market_address >=", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressLessThan(String value) {
            addCriterion("market_address <", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressLessThanOrEqualTo(String value) {
            addCriterion("market_address <=", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressLike(String value) {
            addCriterion("market_address like", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressNotLike(String value) {
            addCriterion("market_address not like", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressIn(List<String> values) {
            addCriterion("market_address in", values, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressNotIn(List<String> values) {
            addCriterion("market_address not in", values, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressBetween(String value1, String value2) {
            addCriterion("market_address between", value1, value2, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressNotBetween(String value1, String value2) {
            addCriterion("market_address not between", value1, value2, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketIntroIsNull() {
            addCriterion("market_intro is null");
            return (Criteria) this;
        }

        public Criteria andMarketIntroIsNotNull() {
            addCriterion("market_intro is not null");
            return (Criteria) this;
        }

        public Criteria andMarketIntroEqualTo(String value) {
            addCriterion("market_intro =", value, "marketIntro");
            return (Criteria) this;
        }

        public Criteria andMarketIntroNotEqualTo(String value) {
            addCriterion("market_intro <>", value, "marketIntro");
            return (Criteria) this;
        }

        public Criteria andMarketIntroGreaterThan(String value) {
            addCriterion("market_intro >", value, "marketIntro");
            return (Criteria) this;
        }

        public Criteria andMarketIntroGreaterThanOrEqualTo(String value) {
            addCriterion("market_intro >=", value, "marketIntro");
            return (Criteria) this;
        }

        public Criteria andMarketIntroLessThan(String value) {
            addCriterion("market_intro <", value, "marketIntro");
            return (Criteria) this;
        }

        public Criteria andMarketIntroLessThanOrEqualTo(String value) {
            addCriterion("market_intro <=", value, "marketIntro");
            return (Criteria) this;
        }

        public Criteria andMarketIntroLike(String value) {
            addCriterion("market_intro like", value, "marketIntro");
            return (Criteria) this;
        }

        public Criteria andMarketIntroNotLike(String value) {
            addCriterion("market_intro not like", value, "marketIntro");
            return (Criteria) this;
        }

        public Criteria andMarketIntroIn(List<String> values) {
            addCriterion("market_intro in", values, "marketIntro");
            return (Criteria) this;
        }

        public Criteria andMarketIntroNotIn(List<String> values) {
            addCriterion("market_intro not in", values, "marketIntro");
            return (Criteria) this;
        }

        public Criteria andMarketIntroBetween(String value1, String value2) {
            addCriterion("market_intro between", value1, value2, "marketIntro");
            return (Criteria) this;
        }

        public Criteria andMarketIntroNotBetween(String value1, String value2) {
            addCriterion("market_intro not between", value1, value2, "marketIntro");
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

        public Criteria andProcessIdIsNull() {
            addCriterion("process_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessIdIsNotNull() {
            addCriterion("process_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessIdEqualTo(Integer value) {
            addCriterion("process_id =", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotEqualTo(Integer value) {
            addCriterion("process_id <>", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdGreaterThan(Integer value) {
            addCriterion("process_id >", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("process_id >=", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLessThan(Integer value) {
            addCriterion("process_id <", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLessThanOrEqualTo(Integer value) {
            addCriterion("process_id <=", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdIn(List<Integer> values) {
            addCriterion("process_id in", values, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotIn(List<Integer> values) {
            addCriterion("process_id not in", values, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdBetween(Integer value1, Integer value2) {
            addCriterion("process_id between", value1, value2, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotBetween(Integer value1, Integer value2) {
            addCriterion("process_id not between", value1, value2, "processId");
            return (Criteria) this;
        }

        public Criteria andLoginUserIdIsNull() {
            addCriterion("login_user_id is null");
            return (Criteria) this;
        }

        public Criteria andLoginUserIdIsNotNull() {
            addCriterion("login_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoginUserIdEqualTo(Integer value) {
            addCriterion("login_user_id =", value, "loginUserId");
            return (Criteria) this;
        }

        public Criteria andLoginUserIdNotEqualTo(Integer value) {
            addCriterion("login_user_id <>", value, "loginUserId");
            return (Criteria) this;
        }

        public Criteria andLoginUserIdGreaterThan(Integer value) {
            addCriterion("login_user_id >", value, "loginUserId");
            return (Criteria) this;
        }

        public Criteria andLoginUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("login_user_id >=", value, "loginUserId");
            return (Criteria) this;
        }

        public Criteria andLoginUserIdLessThan(Integer value) {
            addCriterion("login_user_id <", value, "loginUserId");
            return (Criteria) this;
        }

        public Criteria andLoginUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("login_user_id <=", value, "loginUserId");
            return (Criteria) this;
        }

        public Criteria andLoginUserIdIn(List<Integer> values) {
            addCriterion("login_user_id in", values, "loginUserId");
            return (Criteria) this;
        }

        public Criteria andLoginUserIdNotIn(List<Integer> values) {
            addCriterion("login_user_id not in", values, "loginUserId");
            return (Criteria) this;
        }

        public Criteria andLoginUserIdBetween(Integer value1, Integer value2) {
            addCriterion("login_user_id between", value1, value2, "loginUserId");
            return (Criteria) this;
        }

        public Criteria andLoginUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("login_user_id not between", value1, value2, "loginUserId");
            return (Criteria) this;
        }

        public Criteria andUploadPicIsNull() {
            addCriterion("upload_pic is null");
            return (Criteria) this;
        }

        public Criteria andUploadPicIsNotNull() {
            addCriterion("upload_pic is not null");
            return (Criteria) this;
        }

        public Criteria andUploadPicEqualTo(String value) {
            addCriterion("upload_pic =", value, "uploadPic");
            return (Criteria) this;
        }

        public Criteria andUploadPicNotEqualTo(String value) {
            addCriterion("upload_pic <>", value, "uploadPic");
            return (Criteria) this;
        }

        public Criteria andUploadPicGreaterThan(String value) {
            addCriterion("upload_pic >", value, "uploadPic");
            return (Criteria) this;
        }

        public Criteria andUploadPicGreaterThanOrEqualTo(String value) {
            addCriterion("upload_pic >=", value, "uploadPic");
            return (Criteria) this;
        }

        public Criteria andUploadPicLessThan(String value) {
            addCriterion("upload_pic <", value, "uploadPic");
            return (Criteria) this;
        }

        public Criteria andUploadPicLessThanOrEqualTo(String value) {
            addCriterion("upload_pic <=", value, "uploadPic");
            return (Criteria) this;
        }

        public Criteria andUploadPicLike(String value) {
            addCriterion("upload_pic like", value, "uploadPic");
            return (Criteria) this;
        }

        public Criteria andUploadPicNotLike(String value) {
            addCriterion("upload_pic not like", value, "uploadPic");
            return (Criteria) this;
        }

        public Criteria andUploadPicIn(List<String> values) {
            addCriterion("upload_pic in", values, "uploadPic");
            return (Criteria) this;
        }

        public Criteria andUploadPicNotIn(List<String> values) {
            addCriterion("upload_pic not in", values, "uploadPic");
            return (Criteria) this;
        }

        public Criteria andUploadPicBetween(String value1, String value2) {
            addCriterion("upload_pic between", value1, value2, "uploadPic");
            return (Criteria) this;
        }

        public Criteria andUploadPicNotBetween(String value1, String value2) {
            addCriterion("upload_pic not between", value1, value2, "uploadPic");
            return (Criteria) this;
        }

        public Criteria andUploadDocIsNull() {
            addCriterion("upload_doc is null");
            return (Criteria) this;
        }

        public Criteria andUploadDocIsNotNull() {
            addCriterion("upload_doc is not null");
            return (Criteria) this;
        }

        public Criteria andUploadDocEqualTo(String value) {
            addCriterion("upload_doc =", value, "uploadDoc");
            return (Criteria) this;
        }

        public Criteria andUploadDocNotEqualTo(String value) {
            addCriterion("upload_doc <>", value, "uploadDoc");
            return (Criteria) this;
        }

        public Criteria andUploadDocGreaterThan(String value) {
            addCriterion("upload_doc >", value, "uploadDoc");
            return (Criteria) this;
        }

        public Criteria andUploadDocGreaterThanOrEqualTo(String value) {
            addCriterion("upload_doc >=", value, "uploadDoc");
            return (Criteria) this;
        }

        public Criteria andUploadDocLessThan(String value) {
            addCriterion("upload_doc <", value, "uploadDoc");
            return (Criteria) this;
        }

        public Criteria andUploadDocLessThanOrEqualTo(String value) {
            addCriterion("upload_doc <=", value, "uploadDoc");
            return (Criteria) this;
        }

        public Criteria andUploadDocLike(String value) {
            addCriterion("upload_doc like", value, "uploadDoc");
            return (Criteria) this;
        }

        public Criteria andUploadDocNotLike(String value) {
            addCriterion("upload_doc not like", value, "uploadDoc");
            return (Criteria) this;
        }

        public Criteria andUploadDocIn(List<String> values) {
            addCriterion("upload_doc in", values, "uploadDoc");
            return (Criteria) this;
        }

        public Criteria andUploadDocNotIn(List<String> values) {
            addCriterion("upload_doc not in", values, "uploadDoc");
            return (Criteria) this;
        }

        public Criteria andUploadDocBetween(String value1, String value2) {
            addCriterion("upload_doc between", value1, value2, "uploadDoc");
            return (Criteria) this;
        }

        public Criteria andUploadDocNotBetween(String value1, String value2) {
            addCriterion("upload_doc not between", value1, value2, "uploadDoc");
            return (Criteria) this;
        }

        public Criteria andProcessStrIsNull() {
            addCriterion("process_str is null");
            return (Criteria) this;
        }

        public Criteria andProcessStrIsNotNull() {
            addCriterion("process_str is not null");
            return (Criteria) this;
        }

        public Criteria andProcessStrEqualTo(String value) {
            addCriterion("process_str =", value, "processStr");
            return (Criteria) this;
        }

        public Criteria andProcessStrNotEqualTo(String value) {
            addCriterion("process_str <>", value, "processStr");
            return (Criteria) this;
        }

        public Criteria andProcessStrGreaterThan(String value) {
            addCriterion("process_str >", value, "processStr");
            return (Criteria) this;
        }

        public Criteria andProcessStrGreaterThanOrEqualTo(String value) {
            addCriterion("process_str >=", value, "processStr");
            return (Criteria) this;
        }

        public Criteria andProcessStrLessThan(String value) {
            addCriterion("process_str <", value, "processStr");
            return (Criteria) this;
        }

        public Criteria andProcessStrLessThanOrEqualTo(String value) {
            addCriterion("process_str <=", value, "processStr");
            return (Criteria) this;
        }

        public Criteria andProcessStrLike(String value) {
            addCriterion("process_str like", value, "processStr");
            return (Criteria) this;
        }

        public Criteria andProcessStrNotLike(String value) {
            addCriterion("process_str not like", value, "processStr");
            return (Criteria) this;
        }

        public Criteria andProcessStrIn(List<String> values) {
            addCriterion("process_str in", values, "processStr");
            return (Criteria) this;
        }

        public Criteria andProcessStrNotIn(List<String> values) {
            addCriterion("process_str not in", values, "processStr");
            return (Criteria) this;
        }

        public Criteria andProcessStrBetween(String value1, String value2) {
            addCriterion("process_str between", value1, value2, "processStr");
            return (Criteria) this;
        }

        public Criteria andProcessStrNotBetween(String value1, String value2) {
            addCriterion("process_str not between", value1, value2, "processStr");
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