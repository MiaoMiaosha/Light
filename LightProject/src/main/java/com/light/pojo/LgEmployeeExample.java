package com.light.pojo;

import java.util.ArrayList;
import java.util.List;

public class LgEmployeeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LgEmployeeExample() {
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

        public Criteria andEidIsNull() {
            addCriterion("eid is null");
            return (Criteria) this;
        }

        public Criteria andEidIsNotNull() {
            addCriterion("eid is not null");
            return (Criteria) this;
        }

        public Criteria andEidEqualTo(Integer value) {
            addCriterion("eid =", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotEqualTo(Integer value) {
            addCriterion("eid <>", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThan(Integer value) {
            addCriterion("eid >", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidGreaterThanOrEqualTo(Integer value) {
            addCriterion("eid >=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThan(Integer value) {
            addCriterion("eid <", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidLessThanOrEqualTo(Integer value) {
            addCriterion("eid <=", value, "eid");
            return (Criteria) this;
        }

        public Criteria andEidIn(List<Integer> values) {
            addCriterion("eid in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotIn(List<Integer> values) {
            addCriterion("eid not in", values, "eid");
            return (Criteria) this;
        }

        public Criteria andEidBetween(Integer value1, Integer value2) {
            addCriterion("eid between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andEidNotBetween(Integer value1, Integer value2) {
            addCriterion("eid not between", value1, value2, "eid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andJobIsNull() {
            addCriterion("job is null");
            return (Criteria) this;
        }

        public Criteria andJobIsNotNull() {
            addCriterion("job is not null");
            return (Criteria) this;
        }

        public Criteria andJobEqualTo(Integer value) {
            addCriterion("job =", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotEqualTo(Integer value) {
            addCriterion("job <>", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobGreaterThan(Integer value) {
            addCriterion("job >", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobGreaterThanOrEqualTo(Integer value) {
            addCriterion("job >=", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobLessThan(Integer value) {
            addCriterion("job <", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobLessThanOrEqualTo(Integer value) {
            addCriterion("job <=", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobIn(List<Integer> values) {
            addCriterion("job in", values, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotIn(List<Integer> values) {
            addCriterion("job not in", values, "job");
            return (Criteria) this;
        }

        public Criteria andJobBetween(Integer value1, Integer value2) {
            addCriterion("job between", value1, value2, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotBetween(Integer value1, Integer value2) {
            addCriterion("job not between", value1, value2, "job");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNull() {
            addCriterion("company_address is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNotNull() {
            addCriterion("company_address is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressEqualTo(String value) {
            addCriterion("company_address =", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotEqualTo(String value) {
            addCriterion("company_address <>", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThan(String value) {
            addCriterion("company_address >", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("company_address >=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThan(String value) {
            addCriterion("company_address <", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThanOrEqualTo(String value) {
            addCriterion("company_address <=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLike(String value) {
            addCriterion("company_address like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotLike(String value) {
            addCriterion("company_address not like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIn(List<String> values) {
            addCriterion("company_address in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotIn(List<String> values) {
            addCriterion("company_address not in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressBetween(String value1, String value2) {
            addCriterion("company_address between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotBetween(String value1, String value2) {
            addCriterion("company_address not between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountIsNull() {
            addCriterion("company_account is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountIsNotNull() {
            addCriterion("company_account is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountEqualTo(String value) {
            addCriterion("company_account =", value, "companyAccount");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountNotEqualTo(String value) {
            addCriterion("company_account <>", value, "companyAccount");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountGreaterThan(String value) {
            addCriterion("company_account >", value, "companyAccount");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountGreaterThanOrEqualTo(String value) {
            addCriterion("company_account >=", value, "companyAccount");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountLessThan(String value) {
            addCriterion("company_account <", value, "companyAccount");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountLessThanOrEqualTo(String value) {
            addCriterion("company_account <=", value, "companyAccount");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountLike(String value) {
            addCriterion("company_account like", value, "companyAccount");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountNotLike(String value) {
            addCriterion("company_account not like", value, "companyAccount");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountIn(List<String> values) {
            addCriterion("company_account in", values, "companyAccount");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountNotIn(List<String> values) {
            addCriterion("company_account not in", values, "companyAccount");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountBetween(String value1, String value2) {
            addCriterion("company_account between", value1, value2, "companyAccount");
            return (Criteria) this;
        }

        public Criteria andCompanyAccountNotBetween(String value1, String value2) {
            addCriterion("company_account not between", value1, value2, "companyAccount");
            return (Criteria) this;
        }

        public Criteria andAccountBankIsNull() {
            addCriterion("account_bank is null");
            return (Criteria) this;
        }

        public Criteria andAccountBankIsNotNull() {
            addCriterion("account_bank is not null");
            return (Criteria) this;
        }

        public Criteria andAccountBankEqualTo(String value) {
            addCriterion("account_bank =", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankNotEqualTo(String value) {
            addCriterion("account_bank <>", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankGreaterThan(String value) {
            addCriterion("account_bank >", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankGreaterThanOrEqualTo(String value) {
            addCriterion("account_bank >=", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankLessThan(String value) {
            addCriterion("account_bank <", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankLessThanOrEqualTo(String value) {
            addCriterion("account_bank <=", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankLike(String value) {
            addCriterion("account_bank like", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankNotLike(String value) {
            addCriterion("account_bank not like", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankIn(List<String> values) {
            addCriterion("account_bank in", values, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankNotIn(List<String> values) {
            addCriterion("account_bank not in", values, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankBetween(String value1, String value2) {
            addCriterion("account_bank between", value1, value2, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankNotBetween(String value1, String value2) {
            addCriterion("account_bank not between", value1, value2, "accountBank");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Integer value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Integer value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Integer value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Integer value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Integer value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Integer> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Integer> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Integer value1, Integer value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Integer value1, Integer value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Integer value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Integer value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Integer value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Integer value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Integer value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Integer> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Integer value1, Integer value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Integer value1, Integer value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberIsNull() {
            addCriterion("idcard_number is null");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberIsNotNull() {
            addCriterion("idcard_number is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberEqualTo(String value) {
            addCriterion("idcard_number =", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberNotEqualTo(String value) {
            addCriterion("idcard_number <>", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberGreaterThan(String value) {
            addCriterion("idcard_number >", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_number >=", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberLessThan(String value) {
            addCriterion("idcard_number <", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberLessThanOrEqualTo(String value) {
            addCriterion("idcard_number <=", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberLike(String value) {
            addCriterion("idcard_number like", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberNotLike(String value) {
            addCriterion("idcard_number not like", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberIn(List<String> values) {
            addCriterion("idcard_number in", values, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberNotIn(List<String> values) {
            addCriterion("idcard_number not in", values, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberBetween(String value1, String value2) {
            addCriterion("idcard_number between", value1, value2, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberNotBetween(String value1, String value2) {
            addCriterion("idcard_number not between", value1, value2, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressIsNull() {
            addCriterion("idcard_address is null");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressIsNotNull() {
            addCriterion("idcard_address is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressEqualTo(String value) {
            addCriterion("idcard_address =", value, "idcardAddress");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressNotEqualTo(String value) {
            addCriterion("idcard_address <>", value, "idcardAddress");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressGreaterThan(String value) {
            addCriterion("idcard_address >", value, "idcardAddress");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_address >=", value, "idcardAddress");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressLessThan(String value) {
            addCriterion("idcard_address <", value, "idcardAddress");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressLessThanOrEqualTo(String value) {
            addCriterion("idcard_address <=", value, "idcardAddress");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressLike(String value) {
            addCriterion("idcard_address like", value, "idcardAddress");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressNotLike(String value) {
            addCriterion("idcard_address not like", value, "idcardAddress");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressIn(List<String> values) {
            addCriterion("idcard_address in", values, "idcardAddress");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressNotIn(List<String> values) {
            addCriterion("idcard_address not in", values, "idcardAddress");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressBetween(String value1, String value2) {
            addCriterion("idcard_address between", value1, value2, "idcardAddress");
            return (Criteria) this;
        }

        public Criteria andIdcardAddressNotBetween(String value1, String value2) {
            addCriterion("idcard_address not between", value1, value2, "idcardAddress");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlIsNull() {
            addCriterion("idcard_front_url is null");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlIsNotNull() {
            addCriterion("idcard_front_url is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlEqualTo(String value) {
            addCriterion("idcard_front_url =", value, "idcardFrontUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlNotEqualTo(String value) {
            addCriterion("idcard_front_url <>", value, "idcardFrontUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlGreaterThan(String value) {
            addCriterion("idcard_front_url >", value, "idcardFrontUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_front_url >=", value, "idcardFrontUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlLessThan(String value) {
            addCriterion("idcard_front_url <", value, "idcardFrontUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlLessThanOrEqualTo(String value) {
            addCriterion("idcard_front_url <=", value, "idcardFrontUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlLike(String value) {
            addCriterion("idcard_front_url like", value, "idcardFrontUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlNotLike(String value) {
            addCriterion("idcard_front_url not like", value, "idcardFrontUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlIn(List<String> values) {
            addCriterion("idcard_front_url in", values, "idcardFrontUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlNotIn(List<String> values) {
            addCriterion("idcard_front_url not in", values, "idcardFrontUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlBetween(String value1, String value2) {
            addCriterion("idcard_front_url between", value1, value2, "idcardFrontUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontUrlNotBetween(String value1, String value2) {
            addCriterion("idcard_front_url not between", value1, value2, "idcardFrontUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlIsNull() {
            addCriterion("idcard_behind_url is null");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlIsNotNull() {
            addCriterion("idcard_behind_url is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlEqualTo(String value) {
            addCriterion("idcard_behind_url =", value, "idcardBehindUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlNotEqualTo(String value) {
            addCriterion("idcard_behind_url <>", value, "idcardBehindUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlGreaterThan(String value) {
            addCriterion("idcard_behind_url >", value, "idcardBehindUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_behind_url >=", value, "idcardBehindUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlLessThan(String value) {
            addCriterion("idcard_behind_url <", value, "idcardBehindUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlLessThanOrEqualTo(String value) {
            addCriterion("idcard_behind_url <=", value, "idcardBehindUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlLike(String value) {
            addCriterion("idcard_behind_url like", value, "idcardBehindUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlNotLike(String value) {
            addCriterion("idcard_behind_url not like", value, "idcardBehindUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlIn(List<String> values) {
            addCriterion("idcard_behind_url in", values, "idcardBehindUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlNotIn(List<String> values) {
            addCriterion("idcard_behind_url not in", values, "idcardBehindUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlBetween(String value1, String value2) {
            addCriterion("idcard_behind_url between", value1, value2, "idcardBehindUrl");
            return (Criteria) this;
        }

        public Criteria andIdcardBehindUrlNotBetween(String value1, String value2) {
            addCriterion("idcard_behind_url not between", value1, value2, "idcardBehindUrl");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("education like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("education not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andContactIsNull() {
            addCriterion("contact is null");
            return (Criteria) this;
        }

        public Criteria andContactIsNotNull() {
            addCriterion("contact is not null");
            return (Criteria) this;
        }

        public Criteria andContactEqualTo(String value) {
            addCriterion("contact =", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotEqualTo(String value) {
            addCriterion("contact <>", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThan(String value) {
            addCriterion("contact >", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThanOrEqualTo(String value) {
            addCriterion("contact >=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThan(String value) {
            addCriterion("contact <", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThanOrEqualTo(String value) {
            addCriterion("contact <=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLike(String value) {
            addCriterion("contact like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotLike(String value) {
            addCriterion("contact not like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactIn(List<String> values) {
            addCriterion("contact in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotIn(List<String> values) {
            addCriterion("contact not in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactBetween(String value1, String value2) {
            addCriterion("contact between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotBetween(String value1, String value2) {
            addCriterion("contact not between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andProbationBaseSalaryIsNull() {
            addCriterion("probation_base_salary is null");
            return (Criteria) this;
        }

        public Criteria andProbationBaseSalaryIsNotNull() {
            addCriterion("probation_base_salary is not null");
            return (Criteria) this;
        }

        public Criteria andProbationBaseSalaryEqualTo(Integer value) {
            addCriterion("probation_base_salary =", value, "probationBaseSalary");
            return (Criteria) this;
        }

        public Criteria andProbationBaseSalaryNotEqualTo(Integer value) {
            addCriterion("probation_base_salary <>", value, "probationBaseSalary");
            return (Criteria) this;
        }

        public Criteria andProbationBaseSalaryGreaterThan(Integer value) {
            addCriterion("probation_base_salary >", value, "probationBaseSalary");
            return (Criteria) this;
        }

        public Criteria andProbationBaseSalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("probation_base_salary >=", value, "probationBaseSalary");
            return (Criteria) this;
        }

        public Criteria andProbationBaseSalaryLessThan(Integer value) {
            addCriterion("probation_base_salary <", value, "probationBaseSalary");
            return (Criteria) this;
        }

        public Criteria andProbationBaseSalaryLessThanOrEqualTo(Integer value) {
            addCriterion("probation_base_salary <=", value, "probationBaseSalary");
            return (Criteria) this;
        }

        public Criteria andProbationBaseSalaryIn(List<Integer> values) {
            addCriterion("probation_base_salary in", values, "probationBaseSalary");
            return (Criteria) this;
        }

        public Criteria andProbationBaseSalaryNotIn(List<Integer> values) {
            addCriterion("probation_base_salary not in", values, "probationBaseSalary");
            return (Criteria) this;
        }

        public Criteria andProbationBaseSalaryBetween(Integer value1, Integer value2) {
            addCriterion("probation_base_salary between", value1, value2, "probationBaseSalary");
            return (Criteria) this;
        }

        public Criteria andProbationBaseSalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("probation_base_salary not between", value1, value2, "probationBaseSalary");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryOneIsNull() {
            addCriterion("official_salary_one is null");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryOneIsNotNull() {
            addCriterion("official_salary_one is not null");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryOneEqualTo(Integer value) {
            addCriterion("official_salary_one =", value, "officialSalaryOne");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryOneNotEqualTo(Integer value) {
            addCriterion("official_salary_one <>", value, "officialSalaryOne");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryOneGreaterThan(Integer value) {
            addCriterion("official_salary_one >", value, "officialSalaryOne");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryOneGreaterThanOrEqualTo(Integer value) {
            addCriterion("official_salary_one >=", value, "officialSalaryOne");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryOneLessThan(Integer value) {
            addCriterion("official_salary_one <", value, "officialSalaryOne");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryOneLessThanOrEqualTo(Integer value) {
            addCriterion("official_salary_one <=", value, "officialSalaryOne");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryOneIn(List<Integer> values) {
            addCriterion("official_salary_one in", values, "officialSalaryOne");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryOneNotIn(List<Integer> values) {
            addCriterion("official_salary_one not in", values, "officialSalaryOne");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryOneBetween(Integer value1, Integer value2) {
            addCriterion("official_salary_one between", value1, value2, "officialSalaryOne");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryOneNotBetween(Integer value1, Integer value2) {
            addCriterion("official_salary_one not between", value1, value2, "officialSalaryOne");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryTwoIsNull() {
            addCriterion("official_salary_two is null");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryTwoIsNotNull() {
            addCriterion("official_salary_two is not null");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryTwoEqualTo(Integer value) {
            addCriterion("official_salary_two =", value, "officialSalaryTwo");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryTwoNotEqualTo(Integer value) {
            addCriterion("official_salary_two <>", value, "officialSalaryTwo");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryTwoGreaterThan(Integer value) {
            addCriterion("official_salary_two >", value, "officialSalaryTwo");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryTwoGreaterThanOrEqualTo(Integer value) {
            addCriterion("official_salary_two >=", value, "officialSalaryTwo");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryTwoLessThan(Integer value) {
            addCriterion("official_salary_two <", value, "officialSalaryTwo");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryTwoLessThanOrEqualTo(Integer value) {
            addCriterion("official_salary_two <=", value, "officialSalaryTwo");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryTwoIn(List<Integer> values) {
            addCriterion("official_salary_two in", values, "officialSalaryTwo");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryTwoNotIn(List<Integer> values) {
            addCriterion("official_salary_two not in", values, "officialSalaryTwo");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryTwoBetween(Integer value1, Integer value2) {
            addCriterion("official_salary_two between", value1, value2, "officialSalaryTwo");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryTwoNotBetween(Integer value1, Integer value2) {
            addCriterion("official_salary_two not between", value1, value2, "officialSalaryTwo");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryThreeIsNull() {
            addCriterion("official_salary_three is null");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryThreeIsNotNull() {
            addCriterion("official_salary_three is not null");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryThreeEqualTo(Integer value) {
            addCriterion("official_salary_three =", value, "officialSalaryThree");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryThreeNotEqualTo(Integer value) {
            addCriterion("official_salary_three <>", value, "officialSalaryThree");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryThreeGreaterThan(Integer value) {
            addCriterion("official_salary_three >", value, "officialSalaryThree");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryThreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("official_salary_three >=", value, "officialSalaryThree");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryThreeLessThan(Integer value) {
            addCriterion("official_salary_three <", value, "officialSalaryThree");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryThreeLessThanOrEqualTo(Integer value) {
            addCriterion("official_salary_three <=", value, "officialSalaryThree");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryThreeIn(List<Integer> values) {
            addCriterion("official_salary_three in", values, "officialSalaryThree");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryThreeNotIn(List<Integer> values) {
            addCriterion("official_salary_three not in", values, "officialSalaryThree");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryThreeBetween(Integer value1, Integer value2) {
            addCriterion("official_salary_three between", value1, value2, "officialSalaryThree");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryThreeNotBetween(Integer value1, Integer value2) {
            addCriterion("official_salary_three not between", value1, value2, "officialSalaryThree");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryFourIsNull() {
            addCriterion("official_salary_four is null");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryFourIsNotNull() {
            addCriterion("official_salary_four is not null");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryFourEqualTo(Integer value) {
            addCriterion("official_salary_four =", value, "officialSalaryFour");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryFourNotEqualTo(Integer value) {
            addCriterion("official_salary_four <>", value, "officialSalaryFour");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryFourGreaterThan(Integer value) {
            addCriterion("official_salary_four >", value, "officialSalaryFour");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryFourGreaterThanOrEqualTo(Integer value) {
            addCriterion("official_salary_four >=", value, "officialSalaryFour");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryFourLessThan(Integer value) {
            addCriterion("official_salary_four <", value, "officialSalaryFour");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryFourLessThanOrEqualTo(Integer value) {
            addCriterion("official_salary_four <=", value, "officialSalaryFour");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryFourIn(List<Integer> values) {
            addCriterion("official_salary_four in", values, "officialSalaryFour");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryFourNotIn(List<Integer> values) {
            addCriterion("official_salary_four not in", values, "officialSalaryFour");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryFourBetween(Integer value1, Integer value2) {
            addCriterion("official_salary_four between", value1, value2, "officialSalaryFour");
            return (Criteria) this;
        }

        public Criteria andOfficialSalaryFourNotBetween(Integer value1, Integer value2) {
            addCriterion("official_salary_four not between", value1, value2, "officialSalaryFour");
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

        public Criteria andQqIsNull() {
            addCriterion("qq is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("qq =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("qq <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("qq >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("qq >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("qq <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("qq <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("qq like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("qq not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("qq in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("qq not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("qq between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("qq not between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andWxIsNull() {
            addCriterion("wx is null");
            return (Criteria) this;
        }

        public Criteria andWxIsNotNull() {
            addCriterion("wx is not null");
            return (Criteria) this;
        }

        public Criteria andWxEqualTo(String value) {
            addCriterion("wx =", value, "wx");
            return (Criteria) this;
        }

        public Criteria andWxNotEqualTo(String value) {
            addCriterion("wx <>", value, "wx");
            return (Criteria) this;
        }

        public Criteria andWxGreaterThan(String value) {
            addCriterion("wx >", value, "wx");
            return (Criteria) this;
        }

        public Criteria andWxGreaterThanOrEqualTo(String value) {
            addCriterion("wx >=", value, "wx");
            return (Criteria) this;
        }

        public Criteria andWxLessThan(String value) {
            addCriterion("wx <", value, "wx");
            return (Criteria) this;
        }

        public Criteria andWxLessThanOrEqualTo(String value) {
            addCriterion("wx <=", value, "wx");
            return (Criteria) this;
        }

        public Criteria andWxLike(String value) {
            addCriterion("wx like", value, "wx");
            return (Criteria) this;
        }

        public Criteria andWxNotLike(String value) {
            addCriterion("wx not like", value, "wx");
            return (Criteria) this;
        }

        public Criteria andWxIn(List<String> values) {
            addCriterion("wx in", values, "wx");
            return (Criteria) this;
        }

        public Criteria andWxNotIn(List<String> values) {
            addCriterion("wx not in", values, "wx");
            return (Criteria) this;
        }

        public Criteria andWxBetween(String value1, String value2) {
            addCriterion("wx between", value1, value2, "wx");
            return (Criteria) this;
        }

        public Criteria andWxNotBetween(String value1, String value2) {
            addCriterion("wx not between", value1, value2, "wx");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameIsNull() {
            addCriterion("dear_friend_name is null");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameIsNotNull() {
            addCriterion("dear_friend_name is not null");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameEqualTo(String value) {
            addCriterion("dear_friend_name =", value, "dearFriendName");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameNotEqualTo(String value) {
            addCriterion("dear_friend_name <>", value, "dearFriendName");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameGreaterThan(String value) {
            addCriterion("dear_friend_name >", value, "dearFriendName");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameGreaterThanOrEqualTo(String value) {
            addCriterion("dear_friend_name >=", value, "dearFriendName");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameLessThan(String value) {
            addCriterion("dear_friend_name <", value, "dearFriendName");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameLessThanOrEqualTo(String value) {
            addCriterion("dear_friend_name <=", value, "dearFriendName");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameLike(String value) {
            addCriterion("dear_friend_name like", value, "dearFriendName");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameNotLike(String value) {
            addCriterion("dear_friend_name not like", value, "dearFriendName");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameIn(List<String> values) {
            addCriterion("dear_friend_name in", values, "dearFriendName");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameNotIn(List<String> values) {
            addCriterion("dear_friend_name not in", values, "dearFriendName");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameBetween(String value1, String value2) {
            addCriterion("dear_friend_name between", value1, value2, "dearFriendName");
            return (Criteria) this;
        }

        public Criteria andDearFriendNameNotBetween(String value1, String value2) {
            addCriterion("dear_friend_name not between", value1, value2, "dearFriendName");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileIsNull() {
            addCriterion("dear_friend_mobile is null");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileIsNotNull() {
            addCriterion("dear_friend_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileEqualTo(String value) {
            addCriterion("dear_friend_mobile =", value, "dearFriendMobile");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileNotEqualTo(String value) {
            addCriterion("dear_friend_mobile <>", value, "dearFriendMobile");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileGreaterThan(String value) {
            addCriterion("dear_friend_mobile >", value, "dearFriendMobile");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileGreaterThanOrEqualTo(String value) {
            addCriterion("dear_friend_mobile >=", value, "dearFriendMobile");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileLessThan(String value) {
            addCriterion("dear_friend_mobile <", value, "dearFriendMobile");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileLessThanOrEqualTo(String value) {
            addCriterion("dear_friend_mobile <=", value, "dearFriendMobile");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileLike(String value) {
            addCriterion("dear_friend_mobile like", value, "dearFriendMobile");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileNotLike(String value) {
            addCriterion("dear_friend_mobile not like", value, "dearFriendMobile");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileIn(List<String> values) {
            addCriterion("dear_friend_mobile in", values, "dearFriendMobile");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileNotIn(List<String> values) {
            addCriterion("dear_friend_mobile not in", values, "dearFriendMobile");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileBetween(String value1, String value2) {
            addCriterion("dear_friend_mobile between", value1, value2, "dearFriendMobile");
            return (Criteria) this;
        }

        public Criteria andDearFriendMobileNotBetween(String value1, String value2) {
            addCriterion("dear_friend_mobile not between", value1, value2, "dearFriendMobile");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeIsNull() {
            addCriterion("register_time is null");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeIsNotNull() {
            addCriterion("register_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeEqualTo(Integer value) {
            addCriterion("register_time =", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeNotEqualTo(Integer value) {
            addCriterion("register_time <>", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeGreaterThan(Integer value) {
            addCriterion("register_time >", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("register_time >=", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeLessThan(Integer value) {
            addCriterion("register_time <", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeLessThanOrEqualTo(Integer value) {
            addCriterion("register_time <=", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeIn(List<Integer> values) {
            addCriterion("register_time in", values, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeNotIn(List<Integer> values) {
            addCriterion("register_time not in", values, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeBetween(Integer value1, Integer value2) {
            addCriterion("register_time between", value1, value2, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("register_time not between", value1, value2, "registerTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeIsNull() {
            addCriterion("entry_time is null");
            return (Criteria) this;
        }

        public Criteria andEntryTimeIsNotNull() {
            addCriterion("entry_time is not null");
            return (Criteria) this;
        }

        public Criteria andEntryTimeEqualTo(Integer value) {
            addCriterion("entry_time =", value, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeNotEqualTo(Integer value) {
            addCriterion("entry_time <>", value, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeGreaterThan(Integer value) {
            addCriterion("entry_time >", value, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("entry_time >=", value, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeLessThan(Integer value) {
            addCriterion("entry_time <", value, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeLessThanOrEqualTo(Integer value) {
            addCriterion("entry_time <=", value, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeIn(List<Integer> values) {
            addCriterion("entry_time in", values, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeNotIn(List<Integer> values) {
            addCriterion("entry_time not in", values, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeBetween(Integer value1, Integer value2) {
            addCriterion("entry_time between", value1, value2, "entryTime");
            return (Criteria) this;
        }

        public Criteria andEntryTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("entry_time not between", value1, value2, "entryTime");
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