package com.light.pojo;

import java.util.ArrayList;
import java.util.List;

public class LgRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LgRecordExample() {
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

        public Criteria andRidIsNull() {
            addCriterion("rid is null");
            return (Criteria) this;
        }

        public Criteria andRidIsNotNull() {
            addCriterion("rid is not null");
            return (Criteria) this;
        }

        public Criteria andRidEqualTo(Integer value) {
            addCriterion("rid =", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotEqualTo(Integer value) {
            addCriterion("rid <>", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidGreaterThan(Integer value) {
            addCriterion("rid >", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidGreaterThanOrEqualTo(Integer value) {
            addCriterion("rid >=", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidLessThan(Integer value) {
            addCriterion("rid <", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidLessThanOrEqualTo(Integer value) {
            addCriterion("rid <=", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidIn(List<Integer> values) {
            addCriterion("rid in", values, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotIn(List<Integer> values) {
            addCriterion("rid not in", values, "rid");
            return (Criteria) this;
        }

        public Criteria andRidBetween(Integer value1, Integer value2) {
            addCriterion("rid between", value1, value2, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotBetween(Integer value1, Integer value2) {
            addCriterion("rid not between", value1, value2, "rid");
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

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(Integer value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(Integer value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(Integer value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(Integer value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(Integer value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<Integer> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<Integer> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(Integer value1, Integer value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("user_type not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andRecordTimeIsNull() {
            addCriterion("record_time is null");
            return (Criteria) this;
        }

        public Criteria andRecordTimeIsNotNull() {
            addCriterion("record_time is not null");
            return (Criteria) this;
        }

        public Criteria andRecordTimeEqualTo(Integer value) {
            addCriterion("record_time =", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeNotEqualTo(Integer value) {
            addCriterion("record_time <>", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeGreaterThan(Integer value) {
            addCriterion("record_time >", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("record_time >=", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeLessThan(Integer value) {
            addCriterion("record_time <", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeLessThanOrEqualTo(Integer value) {
            addCriterion("record_time <=", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeIn(List<Integer> values) {
            addCriterion("record_time in", values, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeNotIn(List<Integer> values) {
            addCriterion("record_time not in", values, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeBetween(Integer value1, Integer value2) {
            addCriterion("record_time between", value1, value2, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("record_time not between", value1, value2, "recordTime");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNull() {
            addCriterion("person_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNotNull() {
            addCriterion("person_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonNameEqualTo(String value) {
            addCriterion("person_name =", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotEqualTo(String value) {
            addCriterion("person_name <>", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThan(String value) {
            addCriterion("person_name >", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_name >=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThan(String value) {
            addCriterion("person_name <", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThanOrEqualTo(String value) {
            addCriterion("person_name <=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLike(String value) {
            addCriterion("person_name like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotLike(String value) {
            addCriterion("person_name not like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameIn(List<String> values) {
            addCriterion("person_name in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotIn(List<String> values) {
            addCriterion("person_name not in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameBetween(String value1, String value2) {
            addCriterion("person_name between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotBetween(String value1, String value2) {
            addCriterion("person_name not between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andProcessOneIsNull() {
            addCriterion("process_one is null");
            return (Criteria) this;
        }

        public Criteria andProcessOneIsNotNull() {
            addCriterion("process_one is not null");
            return (Criteria) this;
        }

        public Criteria andProcessOneEqualTo(String value) {
            addCriterion("process_one =", value, "processOne");
            return (Criteria) this;
        }

        public Criteria andProcessOneNotEqualTo(String value) {
            addCriterion("process_one <>", value, "processOne");
            return (Criteria) this;
        }

        public Criteria andProcessOneGreaterThan(String value) {
            addCriterion("process_one >", value, "processOne");
            return (Criteria) this;
        }

        public Criteria andProcessOneGreaterThanOrEqualTo(String value) {
            addCriterion("process_one >=", value, "processOne");
            return (Criteria) this;
        }

        public Criteria andProcessOneLessThan(String value) {
            addCriterion("process_one <", value, "processOne");
            return (Criteria) this;
        }

        public Criteria andProcessOneLessThanOrEqualTo(String value) {
            addCriterion("process_one <=", value, "processOne");
            return (Criteria) this;
        }

        public Criteria andProcessOneLike(String value) {
            addCriterion("process_one like", value, "processOne");
            return (Criteria) this;
        }

        public Criteria andProcessOneNotLike(String value) {
            addCriterion("process_one not like", value, "processOne");
            return (Criteria) this;
        }

        public Criteria andProcessOneIn(List<String> values) {
            addCriterion("process_one in", values, "processOne");
            return (Criteria) this;
        }

        public Criteria andProcessOneNotIn(List<String> values) {
            addCriterion("process_one not in", values, "processOne");
            return (Criteria) this;
        }

        public Criteria andProcessOneBetween(String value1, String value2) {
            addCriterion("process_one between", value1, value2, "processOne");
            return (Criteria) this;
        }

        public Criteria andProcessOneNotBetween(String value1, String value2) {
            addCriterion("process_one not between", value1, value2, "processOne");
            return (Criteria) this;
        }

        public Criteria andProcessTwoIsNull() {
            addCriterion("process_two is null");
            return (Criteria) this;
        }

        public Criteria andProcessTwoIsNotNull() {
            addCriterion("process_two is not null");
            return (Criteria) this;
        }

        public Criteria andProcessTwoEqualTo(String value) {
            addCriterion("process_two =", value, "processTwo");
            return (Criteria) this;
        }

        public Criteria andProcessTwoNotEqualTo(String value) {
            addCriterion("process_two <>", value, "processTwo");
            return (Criteria) this;
        }

        public Criteria andProcessTwoGreaterThan(String value) {
            addCriterion("process_two >", value, "processTwo");
            return (Criteria) this;
        }

        public Criteria andProcessTwoGreaterThanOrEqualTo(String value) {
            addCriterion("process_two >=", value, "processTwo");
            return (Criteria) this;
        }

        public Criteria andProcessTwoLessThan(String value) {
            addCriterion("process_two <", value, "processTwo");
            return (Criteria) this;
        }

        public Criteria andProcessTwoLessThanOrEqualTo(String value) {
            addCriterion("process_two <=", value, "processTwo");
            return (Criteria) this;
        }

        public Criteria andProcessTwoLike(String value) {
            addCriterion("process_two like", value, "processTwo");
            return (Criteria) this;
        }

        public Criteria andProcessTwoNotLike(String value) {
            addCriterion("process_two not like", value, "processTwo");
            return (Criteria) this;
        }

        public Criteria andProcessTwoIn(List<String> values) {
            addCriterion("process_two in", values, "processTwo");
            return (Criteria) this;
        }

        public Criteria andProcessTwoNotIn(List<String> values) {
            addCriterion("process_two not in", values, "processTwo");
            return (Criteria) this;
        }

        public Criteria andProcessTwoBetween(String value1, String value2) {
            addCriterion("process_two between", value1, value2, "processTwo");
            return (Criteria) this;
        }

        public Criteria andProcessTwoNotBetween(String value1, String value2) {
            addCriterion("process_two not between", value1, value2, "processTwo");
            return (Criteria) this;
        }

        public Criteria andProcessThreeIsNull() {
            addCriterion("process_three is null");
            return (Criteria) this;
        }

        public Criteria andProcessThreeIsNotNull() {
            addCriterion("process_three is not null");
            return (Criteria) this;
        }

        public Criteria andProcessThreeEqualTo(String value) {
            addCriterion("process_three =", value, "processThree");
            return (Criteria) this;
        }

        public Criteria andProcessThreeNotEqualTo(String value) {
            addCriterion("process_three <>", value, "processThree");
            return (Criteria) this;
        }

        public Criteria andProcessThreeGreaterThan(String value) {
            addCriterion("process_three >", value, "processThree");
            return (Criteria) this;
        }

        public Criteria andProcessThreeGreaterThanOrEqualTo(String value) {
            addCriterion("process_three >=", value, "processThree");
            return (Criteria) this;
        }

        public Criteria andProcessThreeLessThan(String value) {
            addCriterion("process_three <", value, "processThree");
            return (Criteria) this;
        }

        public Criteria andProcessThreeLessThanOrEqualTo(String value) {
            addCriterion("process_three <=", value, "processThree");
            return (Criteria) this;
        }

        public Criteria andProcessThreeLike(String value) {
            addCriterion("process_three like", value, "processThree");
            return (Criteria) this;
        }

        public Criteria andProcessThreeNotLike(String value) {
            addCriterion("process_three not like", value, "processThree");
            return (Criteria) this;
        }

        public Criteria andProcessThreeIn(List<String> values) {
            addCriterion("process_three in", values, "processThree");
            return (Criteria) this;
        }

        public Criteria andProcessThreeNotIn(List<String> values) {
            addCriterion("process_three not in", values, "processThree");
            return (Criteria) this;
        }

        public Criteria andProcessThreeBetween(String value1, String value2) {
            addCriterion("process_three between", value1, value2, "processThree");
            return (Criteria) this;
        }

        public Criteria andProcessThreeNotBetween(String value1, String value2) {
            addCriterion("process_three not between", value1, value2, "processThree");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(String value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(String value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(String value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(String value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(String value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(String value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLike(String value) {
            addCriterion("amount like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotLike(String value) {
            addCriterion("amount not like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<String> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<String> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(String value1, String value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(String value1, String value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andRecordResultIsNull() {
            addCriterion("record_result is null");
            return (Criteria) this;
        }

        public Criteria andRecordResultIsNotNull() {
            addCriterion("record_result is not null");
            return (Criteria) this;
        }

        public Criteria andRecordResultEqualTo(Integer value) {
            addCriterion("record_result =", value, "recordResult");
            return (Criteria) this;
        }

        public Criteria andRecordResultNotEqualTo(Integer value) {
            addCriterion("record_result <>", value, "recordResult");
            return (Criteria) this;
        }

        public Criteria andRecordResultGreaterThan(Integer value) {
            addCriterion("record_result >", value, "recordResult");
            return (Criteria) this;
        }

        public Criteria andRecordResultGreaterThanOrEqualTo(Integer value) {
            addCriterion("record_result >=", value, "recordResult");
            return (Criteria) this;
        }

        public Criteria andRecordResultLessThan(Integer value) {
            addCriterion("record_result <", value, "recordResult");
            return (Criteria) this;
        }

        public Criteria andRecordResultLessThanOrEqualTo(Integer value) {
            addCriterion("record_result <=", value, "recordResult");
            return (Criteria) this;
        }

        public Criteria andRecordResultIn(List<Integer> values) {
            addCriterion("record_result in", values, "recordResult");
            return (Criteria) this;
        }

        public Criteria andRecordResultNotIn(List<Integer> values) {
            addCriterion("record_result not in", values, "recordResult");
            return (Criteria) this;
        }

        public Criteria andRecordResultBetween(Integer value1, Integer value2) {
            addCriterion("record_result between", value1, value2, "recordResult");
            return (Criteria) this;
        }

        public Criteria andRecordResultNotBetween(Integer value1, Integer value2) {
            addCriterion("record_result not between", value1, value2, "recordResult");
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