package com.light.pojo;

import java.util.ArrayList;
import java.util.List;

public class LgSalaryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LgSalaryExample() {
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

        public Criteria andSidIsNull() {
            addCriterion("sid is null");
            return (Criteria) this;
        }

        public Criteria andSidIsNotNull() {
            addCriterion("sid is not null");
            return (Criteria) this;
        }

        public Criteria andSidEqualTo(Integer value) {
            addCriterion("sid =", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotEqualTo(Integer value) {
            addCriterion("sid <>", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThan(Integer value) {
            addCriterion("sid >", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sid >=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThan(Integer value) {
            addCriterion("sid <", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThanOrEqualTo(Integer value) {
            addCriterion("sid <=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidIn(List<Integer> values) {
            addCriterion("sid in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotIn(List<Integer> values) {
            addCriterion("sid not in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidBetween(Integer value1, Integer value2) {
            addCriterion("sid between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotBetween(Integer value1, Integer value2) {
            addCriterion("sid not between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andPersonCommitTimeIsNull() {
            addCriterion("person_commit_time is null");
            return (Criteria) this;
        }

        public Criteria andPersonCommitTimeIsNotNull() {
            addCriterion("person_commit_time is not null");
            return (Criteria) this;
        }

        public Criteria andPersonCommitTimeEqualTo(Integer value) {
            addCriterion("person_commit_time =", value, "personCommitTime");
            return (Criteria) this;
        }

        public Criteria andPersonCommitTimeNotEqualTo(Integer value) {
            addCriterion("person_commit_time <>", value, "personCommitTime");
            return (Criteria) this;
        }

        public Criteria andPersonCommitTimeGreaterThan(Integer value) {
            addCriterion("person_commit_time >", value, "personCommitTime");
            return (Criteria) this;
        }

        public Criteria andPersonCommitTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("person_commit_time >=", value, "personCommitTime");
            return (Criteria) this;
        }

        public Criteria andPersonCommitTimeLessThan(Integer value) {
            addCriterion("person_commit_time <", value, "personCommitTime");
            return (Criteria) this;
        }

        public Criteria andPersonCommitTimeLessThanOrEqualTo(Integer value) {
            addCriterion("person_commit_time <=", value, "personCommitTime");
            return (Criteria) this;
        }

        public Criteria andPersonCommitTimeIn(List<Integer> values) {
            addCriterion("person_commit_time in", values, "personCommitTime");
            return (Criteria) this;
        }

        public Criteria andPersonCommitTimeNotIn(List<Integer> values) {
            addCriterion("person_commit_time not in", values, "personCommitTime");
            return (Criteria) this;
        }

        public Criteria andPersonCommitTimeBetween(Integer value1, Integer value2) {
            addCriterion("person_commit_time between", value1, value2, "personCommitTime");
            return (Criteria) this;
        }

        public Criteria andPersonCommitTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("person_commit_time not between", value1, value2, "personCommitTime");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNull() {
            addCriterion("employee_id is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNotNull() {
            addCriterion("employee_id is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdEqualTo(Integer value) {
            addCriterion("employee_id =", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotEqualTo(Integer value) {
            addCriterion("employee_id <>", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThan(Integer value) {
            addCriterion("employee_id >", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("employee_id >=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThan(Integer value) {
            addCriterion("employee_id <", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThanOrEqualTo(Integer value) {
            addCriterion("employee_id <=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIn(List<Integer> values) {
            addCriterion("employee_id in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotIn(List<Integer> values) {
            addCriterion("employee_id not in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdBetween(Integer value1, Integer value2) {
            addCriterion("employee_id between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("employee_id not between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andSalaryTypeIsNull() {
            addCriterion("salary_type is null");
            return (Criteria) this;
        }

        public Criteria andSalaryTypeIsNotNull() {
            addCriterion("salary_type is not null");
            return (Criteria) this;
        }

        public Criteria andSalaryTypeEqualTo(Integer value) {
            addCriterion("salary_type =", value, "salaryType");
            return (Criteria) this;
        }

        public Criteria andSalaryTypeNotEqualTo(Integer value) {
            addCriterion("salary_type <>", value, "salaryType");
            return (Criteria) this;
        }

        public Criteria andSalaryTypeGreaterThan(Integer value) {
            addCriterion("salary_type >", value, "salaryType");
            return (Criteria) this;
        }

        public Criteria andSalaryTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("salary_type >=", value, "salaryType");
            return (Criteria) this;
        }

        public Criteria andSalaryTypeLessThan(Integer value) {
            addCriterion("salary_type <", value, "salaryType");
            return (Criteria) this;
        }

        public Criteria andSalaryTypeLessThanOrEqualTo(Integer value) {
            addCriterion("salary_type <=", value, "salaryType");
            return (Criteria) this;
        }

        public Criteria andSalaryTypeIn(List<Integer> values) {
            addCriterion("salary_type in", values, "salaryType");
            return (Criteria) this;
        }

        public Criteria andSalaryTypeNotIn(List<Integer> values) {
            addCriterion("salary_type not in", values, "salaryType");
            return (Criteria) this;
        }

        public Criteria andSalaryTypeBetween(Integer value1, Integer value2) {
            addCriterion("salary_type between", value1, value2, "salaryType");
            return (Criteria) this;
        }

        public Criteria andSalaryTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("salary_type not between", value1, value2, "salaryType");
            return (Criteria) this;
        }

        public Criteria andDesignContentTypeIsNull() {
            addCriterion("design_content_type is null");
            return (Criteria) this;
        }

        public Criteria andDesignContentTypeIsNotNull() {
            addCriterion("design_content_type is not null");
            return (Criteria) this;
        }

        public Criteria andDesignContentTypeEqualTo(Integer value) {
            addCriterion("design_content_type =", value, "designContentType");
            return (Criteria) this;
        }

        public Criteria andDesignContentTypeNotEqualTo(Integer value) {
            addCriterion("design_content_type <>", value, "designContentType");
            return (Criteria) this;
        }

        public Criteria andDesignContentTypeGreaterThan(Integer value) {
            addCriterion("design_content_type >", value, "designContentType");
            return (Criteria) this;
        }

        public Criteria andDesignContentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("design_content_type >=", value, "designContentType");
            return (Criteria) this;
        }

        public Criteria andDesignContentTypeLessThan(Integer value) {
            addCriterion("design_content_type <", value, "designContentType");
            return (Criteria) this;
        }

        public Criteria andDesignContentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("design_content_type <=", value, "designContentType");
            return (Criteria) this;
        }

        public Criteria andDesignContentTypeIn(List<Integer> values) {
            addCriterion("design_content_type in", values, "designContentType");
            return (Criteria) this;
        }

        public Criteria andDesignContentTypeNotIn(List<Integer> values) {
            addCriterion("design_content_type not in", values, "designContentType");
            return (Criteria) this;
        }

        public Criteria andDesignContentTypeBetween(Integer value1, Integer value2) {
            addCriterion("design_content_type between", value1, value2, "designContentType");
            return (Criteria) this;
        }

        public Criteria andDesignContentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("design_content_type not between", value1, value2, "designContentType");
            return (Criteria) this;
        }

        public Criteria andSalaryMoneyIsNull() {
            addCriterion("salary_money is null");
            return (Criteria) this;
        }

        public Criteria andSalaryMoneyIsNotNull() {
            addCriterion("salary_money is not null");
            return (Criteria) this;
        }

        public Criteria andSalaryMoneyEqualTo(Integer value) {
            addCriterion("salary_money =", value, "salaryMoney");
            return (Criteria) this;
        }

        public Criteria andSalaryMoneyNotEqualTo(Integer value) {
            addCriterion("salary_money <>", value, "salaryMoney");
            return (Criteria) this;
        }

        public Criteria andSalaryMoneyGreaterThan(Integer value) {
            addCriterion("salary_money >", value, "salaryMoney");
            return (Criteria) this;
        }

        public Criteria andSalaryMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("salary_money >=", value, "salaryMoney");
            return (Criteria) this;
        }

        public Criteria andSalaryMoneyLessThan(Integer value) {
            addCriterion("salary_money <", value, "salaryMoney");
            return (Criteria) this;
        }

        public Criteria andSalaryMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("salary_money <=", value, "salaryMoney");
            return (Criteria) this;
        }

        public Criteria andSalaryMoneyIn(List<Integer> values) {
            addCriterion("salary_money in", values, "salaryMoney");
            return (Criteria) this;
        }

        public Criteria andSalaryMoneyNotIn(List<Integer> values) {
            addCriterion("salary_money not in", values, "salaryMoney");
            return (Criteria) this;
        }

        public Criteria andSalaryMoneyBetween(Integer value1, Integer value2) {
            addCriterion("salary_money between", value1, value2, "salaryMoney");
            return (Criteria) this;
        }

        public Criteria andSalaryMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("salary_money not between", value1, value2, "salaryMoney");
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

        public Criteria andSalaryOtherIsNull() {
            addCriterion("salary_other is null");
            return (Criteria) this;
        }

        public Criteria andSalaryOtherIsNotNull() {
            addCriterion("salary_other is not null");
            return (Criteria) this;
        }

        public Criteria andSalaryOtherEqualTo(String value) {
            addCriterion("salary_other =", value, "salaryOther");
            return (Criteria) this;
        }

        public Criteria andSalaryOtherNotEqualTo(String value) {
            addCriterion("salary_other <>", value, "salaryOther");
            return (Criteria) this;
        }

        public Criteria andSalaryOtherGreaterThan(String value) {
            addCriterion("salary_other >", value, "salaryOther");
            return (Criteria) this;
        }

        public Criteria andSalaryOtherGreaterThanOrEqualTo(String value) {
            addCriterion("salary_other >=", value, "salaryOther");
            return (Criteria) this;
        }

        public Criteria andSalaryOtherLessThan(String value) {
            addCriterion("salary_other <", value, "salaryOther");
            return (Criteria) this;
        }

        public Criteria andSalaryOtherLessThanOrEqualTo(String value) {
            addCriterion("salary_other <=", value, "salaryOther");
            return (Criteria) this;
        }

        public Criteria andSalaryOtherLike(String value) {
            addCriterion("salary_other like", value, "salaryOther");
            return (Criteria) this;
        }

        public Criteria andSalaryOtherNotLike(String value) {
            addCriterion("salary_other not like", value, "salaryOther");
            return (Criteria) this;
        }

        public Criteria andSalaryOtherIn(List<String> values) {
            addCriterion("salary_other in", values, "salaryOther");
            return (Criteria) this;
        }

        public Criteria andSalaryOtherNotIn(List<String> values) {
            addCriterion("salary_other not in", values, "salaryOther");
            return (Criteria) this;
        }

        public Criteria andSalaryOtherBetween(String value1, String value2) {
            addCriterion("salary_other between", value1, value2, "salaryOther");
            return (Criteria) this;
        }

        public Criteria andSalaryOtherNotBetween(String value1, String value2) {
            addCriterion("salary_other not between", value1, value2, "salaryOther");
            return (Criteria) this;
        }

        public Criteria andDesignOtherIsNull() {
            addCriterion("design_other is null");
            return (Criteria) this;
        }

        public Criteria andDesignOtherIsNotNull() {
            addCriterion("design_other is not null");
            return (Criteria) this;
        }

        public Criteria andDesignOtherEqualTo(String value) {
            addCriterion("design_other =", value, "designOther");
            return (Criteria) this;
        }

        public Criteria andDesignOtherNotEqualTo(String value) {
            addCriterion("design_other <>", value, "designOther");
            return (Criteria) this;
        }

        public Criteria andDesignOtherGreaterThan(String value) {
            addCriterion("design_other >", value, "designOther");
            return (Criteria) this;
        }

        public Criteria andDesignOtherGreaterThanOrEqualTo(String value) {
            addCriterion("design_other >=", value, "designOther");
            return (Criteria) this;
        }

        public Criteria andDesignOtherLessThan(String value) {
            addCriterion("design_other <", value, "designOther");
            return (Criteria) this;
        }

        public Criteria andDesignOtherLessThanOrEqualTo(String value) {
            addCriterion("design_other <=", value, "designOther");
            return (Criteria) this;
        }

        public Criteria andDesignOtherLike(String value) {
            addCriterion("design_other like", value, "designOther");
            return (Criteria) this;
        }

        public Criteria andDesignOtherNotLike(String value) {
            addCriterion("design_other not like", value, "designOther");
            return (Criteria) this;
        }

        public Criteria andDesignOtherIn(List<String> values) {
            addCriterion("design_other in", values, "designOther");
            return (Criteria) this;
        }

        public Criteria andDesignOtherNotIn(List<String> values) {
            addCriterion("design_other not in", values, "designOther");
            return (Criteria) this;
        }

        public Criteria andDesignOtherBetween(String value1, String value2) {
            addCriterion("design_other between", value1, value2, "designOther");
            return (Criteria) this;
        }

        public Criteria andDesignOtherNotBetween(String value1, String value2) {
            addCriterion("design_other not between", value1, value2, "designOther");
            return (Criteria) this;
        }

        public Criteria andDesignModifyIsNull() {
            addCriterion("design_modify is null");
            return (Criteria) this;
        }

        public Criteria andDesignModifyIsNotNull() {
            addCriterion("design_modify is not null");
            return (Criteria) this;
        }

        public Criteria andDesignModifyEqualTo(String value) {
            addCriterion("design_modify =", value, "designModify");
            return (Criteria) this;
        }

        public Criteria andDesignModifyNotEqualTo(String value) {
            addCriterion("design_modify <>", value, "designModify");
            return (Criteria) this;
        }

        public Criteria andDesignModifyGreaterThan(String value) {
            addCriterion("design_modify >", value, "designModify");
            return (Criteria) this;
        }

        public Criteria andDesignModifyGreaterThanOrEqualTo(String value) {
            addCriterion("design_modify >=", value, "designModify");
            return (Criteria) this;
        }

        public Criteria andDesignModifyLessThan(String value) {
            addCriterion("design_modify <", value, "designModify");
            return (Criteria) this;
        }

        public Criteria andDesignModifyLessThanOrEqualTo(String value) {
            addCriterion("design_modify <=", value, "designModify");
            return (Criteria) this;
        }

        public Criteria andDesignModifyLike(String value) {
            addCriterion("design_modify like", value, "designModify");
            return (Criteria) this;
        }

        public Criteria andDesignModifyNotLike(String value) {
            addCriterion("design_modify not like", value, "designModify");
            return (Criteria) this;
        }

        public Criteria andDesignModifyIn(List<String> values) {
            addCriterion("design_modify in", values, "designModify");
            return (Criteria) this;
        }

        public Criteria andDesignModifyNotIn(List<String> values) {
            addCriterion("design_modify not in", values, "designModify");
            return (Criteria) this;
        }

        public Criteria andDesignModifyBetween(String value1, String value2) {
            addCriterion("design_modify between", value1, value2, "designModify");
            return (Criteria) this;
        }

        public Criteria andDesignModifyNotBetween(String value1, String value2) {
            addCriterion("design_modify not between", value1, value2, "designModify");
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

        public Criteria andFinishTimeIsNull() {
            addCriterion("finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Integer value) {
            addCriterion("finish_time =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Integer value) {
            addCriterion("finish_time <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Integer value) {
            addCriterion("finish_time >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("finish_time >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Integer value) {
            addCriterion("finish_time <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Integer value) {
            addCriterion("finish_time <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Integer> values) {
            addCriterion("finish_time in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Integer> values) {
            addCriterion("finish_time not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Integer value1, Integer value2) {
            addCriterion("finish_time between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("finish_time not between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andOperateNoIsNull() {
            addCriterion("operate_no is null");
            return (Criteria) this;
        }

        public Criteria andOperateNoIsNotNull() {
            addCriterion("operate_no is not null");
            return (Criteria) this;
        }

        public Criteria andOperateNoEqualTo(Integer value) {
            addCriterion("operate_no =", value, "operateNo");
            return (Criteria) this;
        }

        public Criteria andOperateNoNotEqualTo(Integer value) {
            addCriterion("operate_no <>", value, "operateNo");
            return (Criteria) this;
        }

        public Criteria andOperateNoGreaterThan(Integer value) {
            addCriterion("operate_no >", value, "operateNo");
            return (Criteria) this;
        }

        public Criteria andOperateNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("operate_no >=", value, "operateNo");
            return (Criteria) this;
        }

        public Criteria andOperateNoLessThan(Integer value) {
            addCriterion("operate_no <", value, "operateNo");
            return (Criteria) this;
        }

        public Criteria andOperateNoLessThanOrEqualTo(Integer value) {
            addCriterion("operate_no <=", value, "operateNo");
            return (Criteria) this;
        }

        public Criteria andOperateNoIn(List<Integer> values) {
            addCriterion("operate_no in", values, "operateNo");
            return (Criteria) this;
        }

        public Criteria andOperateNoNotIn(List<Integer> values) {
            addCriterion("operate_no not in", values, "operateNo");
            return (Criteria) this;
        }

        public Criteria andOperateNoBetween(Integer value1, Integer value2) {
            addCriterion("operate_no between", value1, value2, "operateNo");
            return (Criteria) this;
        }

        public Criteria andOperateNoNotBetween(Integer value1, Integer value2) {
            addCriterion("operate_no not between", value1, value2, "operateNo");
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