package com.light.pojo;

import java.util.ArrayList;
import java.util.List;

public class LgBonusExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LgBonusExample() {
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

        public Criteria andBidIsNull() {
            addCriterion("bid is null");
            return (Criteria) this;
        }

        public Criteria andBidIsNotNull() {
            addCriterion("bid is not null");
            return (Criteria) this;
        }

        public Criteria andBidEqualTo(Integer value) {
            addCriterion("bid =", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotEqualTo(Integer value) {
            addCriterion("bid <>", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThan(Integer value) {
            addCriterion("bid >", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThanOrEqualTo(Integer value) {
            addCriterion("bid >=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThan(Integer value) {
            addCriterion("bid <", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThanOrEqualTo(Integer value) {
            addCriterion("bid <=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidIn(List<Integer> values) {
            addCriterion("bid in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotIn(List<Integer> values) {
            addCriterion("bid not in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidBetween(Integer value1, Integer value2) {
            addCriterion("bid between", value1, value2, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotBetween(Integer value1, Integer value2) {
            addCriterion("bid not between", value1, value2, "bid");
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

        public Criteria andContractidsIsNull() {
            addCriterion("contractIds is null");
            return (Criteria) this;
        }

        public Criteria andContractidsIsNotNull() {
            addCriterion("contractIds is not null");
            return (Criteria) this;
        }

        public Criteria andContractidsEqualTo(String value) {
            addCriterion("contractIds =", value, "contractids");
            return (Criteria) this;
        }

        public Criteria andContractidsNotEqualTo(String value) {
            addCriterion("contractIds <>", value, "contractids");
            return (Criteria) this;
        }

        public Criteria andContractidsGreaterThan(String value) {
            addCriterion("contractIds >", value, "contractids");
            return (Criteria) this;
        }

        public Criteria andContractidsGreaterThanOrEqualTo(String value) {
            addCriterion("contractIds >=", value, "contractids");
            return (Criteria) this;
        }

        public Criteria andContractidsLessThan(String value) {
            addCriterion("contractIds <", value, "contractids");
            return (Criteria) this;
        }

        public Criteria andContractidsLessThanOrEqualTo(String value) {
            addCriterion("contractIds <=", value, "contractids");
            return (Criteria) this;
        }

        public Criteria andContractidsLike(String value) {
            addCriterion("contractIds like", value, "contractids");
            return (Criteria) this;
        }

        public Criteria andContractidsNotLike(String value) {
            addCriterion("contractIds not like", value, "contractids");
            return (Criteria) this;
        }

        public Criteria andContractidsIn(List<String> values) {
            addCriterion("contractIds in", values, "contractids");
            return (Criteria) this;
        }

        public Criteria andContractidsNotIn(List<String> values) {
            addCriterion("contractIds not in", values, "contractids");
            return (Criteria) this;
        }

        public Criteria andContractidsBetween(String value1, String value2) {
            addCriterion("contractIds between", value1, value2, "contractids");
            return (Criteria) this;
        }

        public Criteria andContractidsNotBetween(String value1, String value2) {
            addCriterion("contractIds not between", value1, value2, "contractids");
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

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andReceiveMoneyIsNull() {
            addCriterion("receive_money is null");
            return (Criteria) this;
        }

        public Criteria andReceiveMoneyIsNotNull() {
            addCriterion("receive_money is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveMoneyEqualTo(Integer value) {
            addCriterion("receive_money =", value, "receiveMoney");
            return (Criteria) this;
        }

        public Criteria andReceiveMoneyNotEqualTo(Integer value) {
            addCriterion("receive_money <>", value, "receiveMoney");
            return (Criteria) this;
        }

        public Criteria andReceiveMoneyGreaterThan(Integer value) {
            addCriterion("receive_money >", value, "receiveMoney");
            return (Criteria) this;
        }

        public Criteria andReceiveMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive_money >=", value, "receiveMoney");
            return (Criteria) this;
        }

        public Criteria andReceiveMoneyLessThan(Integer value) {
            addCriterion("receive_money <", value, "receiveMoney");
            return (Criteria) this;
        }

        public Criteria andReceiveMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("receive_money <=", value, "receiveMoney");
            return (Criteria) this;
        }

        public Criteria andReceiveMoneyIn(List<Integer> values) {
            addCriterion("receive_money in", values, "receiveMoney");
            return (Criteria) this;
        }

        public Criteria andReceiveMoneyNotIn(List<Integer> values) {
            addCriterion("receive_money not in", values, "receiveMoney");
            return (Criteria) this;
        }

        public Criteria andReceiveMoneyBetween(Integer value1, Integer value2) {
            addCriterion("receive_money between", value1, value2, "receiveMoney");
            return (Criteria) this;
        }

        public Criteria andReceiveMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("receive_money not between", value1, value2, "receiveMoney");
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

        public Criteria andRealMoneyIsNull() {
            addCriterion("real_money is null");
            return (Criteria) this;
        }

        public Criteria andRealMoneyIsNotNull() {
            addCriterion("real_money is not null");
            return (Criteria) this;
        }

        public Criteria andRealMoneyEqualTo(Integer value) {
            addCriterion("real_money =", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyNotEqualTo(Integer value) {
            addCriterion("real_money <>", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyGreaterThan(Integer value) {
            addCriterion("real_money >", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_money >=", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyLessThan(Integer value) {
            addCriterion("real_money <", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("real_money <=", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyIn(List<Integer> values) {
            addCriterion("real_money in", values, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyNotIn(List<Integer> values) {
            addCriterion("real_money not in", values, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyBetween(Integer value1, Integer value2) {
            addCriterion("real_money between", value1, value2, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("real_money not between", value1, value2, "realMoney");
            return (Criteria) this;
        }

        public Criteria andBonusNumIsNull() {
            addCriterion("bonus_num is null");
            return (Criteria) this;
        }

        public Criteria andBonusNumIsNotNull() {
            addCriterion("bonus_num is not null");
            return (Criteria) this;
        }

        public Criteria andBonusNumEqualTo(Integer value) {
            addCriterion("bonus_num =", value, "bonusNum");
            return (Criteria) this;
        }

        public Criteria andBonusNumNotEqualTo(Integer value) {
            addCriterion("bonus_num <>", value, "bonusNum");
            return (Criteria) this;
        }

        public Criteria andBonusNumGreaterThan(Integer value) {
            addCriterion("bonus_num >", value, "bonusNum");
            return (Criteria) this;
        }

        public Criteria andBonusNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("bonus_num >=", value, "bonusNum");
            return (Criteria) this;
        }

        public Criteria andBonusNumLessThan(Integer value) {
            addCriterion("bonus_num <", value, "bonusNum");
            return (Criteria) this;
        }

        public Criteria andBonusNumLessThanOrEqualTo(Integer value) {
            addCriterion("bonus_num <=", value, "bonusNum");
            return (Criteria) this;
        }

        public Criteria andBonusNumIn(List<Integer> values) {
            addCriterion("bonus_num in", values, "bonusNum");
            return (Criteria) this;
        }

        public Criteria andBonusNumNotIn(List<Integer> values) {
            addCriterion("bonus_num not in", values, "bonusNum");
            return (Criteria) this;
        }

        public Criteria andBonusNumBetween(Integer value1, Integer value2) {
            addCriterion("bonus_num between", value1, value2, "bonusNum");
            return (Criteria) this;
        }

        public Criteria andBonusNumNotBetween(Integer value1, Integer value2) {
            addCriterion("bonus_num not between", value1, value2, "bonusNum");
            return (Criteria) this;
        }

        public Criteria andPersonBusSalaryIsNull() {
            addCriterion("person_bus_salary is null");
            return (Criteria) this;
        }

        public Criteria andPersonBusSalaryIsNotNull() {
            addCriterion("person_bus_salary is not null");
            return (Criteria) this;
        }

        public Criteria andPersonBusSalaryEqualTo(Integer value) {
            addCriterion("person_bus_salary =", value, "personBusSalary");
            return (Criteria) this;
        }

        public Criteria andPersonBusSalaryNotEqualTo(Integer value) {
            addCriterion("person_bus_salary <>", value, "personBusSalary");
            return (Criteria) this;
        }

        public Criteria andPersonBusSalaryGreaterThan(Integer value) {
            addCriterion("person_bus_salary >", value, "personBusSalary");
            return (Criteria) this;
        }

        public Criteria andPersonBusSalaryGreaterThanOrEqualTo(Integer value) {
            addCriterion("person_bus_salary >=", value, "personBusSalary");
            return (Criteria) this;
        }

        public Criteria andPersonBusSalaryLessThan(Integer value) {
            addCriterion("person_bus_salary <", value, "personBusSalary");
            return (Criteria) this;
        }

        public Criteria andPersonBusSalaryLessThanOrEqualTo(Integer value) {
            addCriterion("person_bus_salary <=", value, "personBusSalary");
            return (Criteria) this;
        }

        public Criteria andPersonBusSalaryIn(List<Integer> values) {
            addCriterion("person_bus_salary in", values, "personBusSalary");
            return (Criteria) this;
        }

        public Criteria andPersonBusSalaryNotIn(List<Integer> values) {
            addCriterion("person_bus_salary not in", values, "personBusSalary");
            return (Criteria) this;
        }

        public Criteria andPersonBusSalaryBetween(Integer value1, Integer value2) {
            addCriterion("person_bus_salary between", value1, value2, "personBusSalary");
            return (Criteria) this;
        }

        public Criteria andPersonBusSalaryNotBetween(Integer value1, Integer value2) {
            addCriterion("person_bus_salary not between", value1, value2, "personBusSalary");
            return (Criteria) this;
        }

        public Criteria andDividendRadioIsNull() {
            addCriterion("dividend_radio is null");
            return (Criteria) this;
        }

        public Criteria andDividendRadioIsNotNull() {
            addCriterion("dividend_radio is not null");
            return (Criteria) this;
        }

        public Criteria andDividendRadioEqualTo(Integer value) {
            addCriterion("dividend_radio =", value, "dividendRadio");
            return (Criteria) this;
        }

        public Criteria andDividendRadioNotEqualTo(Integer value) {
            addCriterion("dividend_radio <>", value, "dividendRadio");
            return (Criteria) this;
        }

        public Criteria andDividendRadioGreaterThan(Integer value) {
            addCriterion("dividend_radio >", value, "dividendRadio");
            return (Criteria) this;
        }

        public Criteria andDividendRadioGreaterThanOrEqualTo(Integer value) {
            addCriterion("dividend_radio >=", value, "dividendRadio");
            return (Criteria) this;
        }

        public Criteria andDividendRadioLessThan(Integer value) {
            addCriterion("dividend_radio <", value, "dividendRadio");
            return (Criteria) this;
        }

        public Criteria andDividendRadioLessThanOrEqualTo(Integer value) {
            addCriterion("dividend_radio <=", value, "dividendRadio");
            return (Criteria) this;
        }

        public Criteria andDividendRadioIn(List<Integer> values) {
            addCriterion("dividend_radio in", values, "dividendRadio");
            return (Criteria) this;
        }

        public Criteria andDividendRadioNotIn(List<Integer> values) {
            addCriterion("dividend_radio not in", values, "dividendRadio");
            return (Criteria) this;
        }

        public Criteria andDividendRadioBetween(Integer value1, Integer value2) {
            addCriterion("dividend_radio between", value1, value2, "dividendRadio");
            return (Criteria) this;
        }

        public Criteria andDividendRadioNotBetween(Integer value1, Integer value2) {
            addCriterion("dividend_radio not between", value1, value2, "dividendRadio");
            return (Criteria) this;
        }

        public Criteria andBonusLevelIsNull() {
            addCriterion("bonus_level is null");
            return (Criteria) this;
        }

        public Criteria andBonusLevelIsNotNull() {
            addCriterion("bonus_level is not null");
            return (Criteria) this;
        }

        public Criteria andBonusLevelEqualTo(Integer value) {
            addCriterion("bonus_level =", value, "bonusLevel");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNotEqualTo(Integer value) {
            addCriterion("bonus_level <>", value, "bonusLevel");
            return (Criteria) this;
        }

        public Criteria andBonusLevelGreaterThan(Integer value) {
            addCriterion("bonus_level >", value, "bonusLevel");
            return (Criteria) this;
        }

        public Criteria andBonusLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("bonus_level >=", value, "bonusLevel");
            return (Criteria) this;
        }

        public Criteria andBonusLevelLessThan(Integer value) {
            addCriterion("bonus_level <", value, "bonusLevel");
            return (Criteria) this;
        }

        public Criteria andBonusLevelLessThanOrEqualTo(Integer value) {
            addCriterion("bonus_level <=", value, "bonusLevel");
            return (Criteria) this;
        }

        public Criteria andBonusLevelIn(List<Integer> values) {
            addCriterion("bonus_level in", values, "bonusLevel");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNotIn(List<Integer> values) {
            addCriterion("bonus_level not in", values, "bonusLevel");
            return (Criteria) this;
        }

        public Criteria andBonusLevelBetween(Integer value1, Integer value2) {
            addCriterion("bonus_level between", value1, value2, "bonusLevel");
            return (Criteria) this;
        }

        public Criteria andBonusLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("bonus_level not between", value1, value2, "bonusLevel");
            return (Criteria) this;
        }

        public Criteria andBonusMoneyIsNull() {
            addCriterion("bonus_money is null");
            return (Criteria) this;
        }

        public Criteria andBonusMoneyIsNotNull() {
            addCriterion("bonus_money is not null");
            return (Criteria) this;
        }

        public Criteria andBonusMoneyEqualTo(Integer value) {
            addCriterion("bonus_money =", value, "bonusMoney");
            return (Criteria) this;
        }

        public Criteria andBonusMoneyNotEqualTo(Integer value) {
            addCriterion("bonus_money <>", value, "bonusMoney");
            return (Criteria) this;
        }

        public Criteria andBonusMoneyGreaterThan(Integer value) {
            addCriterion("bonus_money >", value, "bonusMoney");
            return (Criteria) this;
        }

        public Criteria andBonusMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("bonus_money >=", value, "bonusMoney");
            return (Criteria) this;
        }

        public Criteria andBonusMoneyLessThan(Integer value) {
            addCriterion("bonus_money <", value, "bonusMoney");
            return (Criteria) this;
        }

        public Criteria andBonusMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("bonus_money <=", value, "bonusMoney");
            return (Criteria) this;
        }

        public Criteria andBonusMoneyIn(List<Integer> values) {
            addCriterion("bonus_money in", values, "bonusMoney");
            return (Criteria) this;
        }

        public Criteria andBonusMoneyNotIn(List<Integer> values) {
            addCriterion("bonus_money not in", values, "bonusMoney");
            return (Criteria) this;
        }

        public Criteria andBonusMoneyBetween(Integer value1, Integer value2) {
            addCriterion("bonus_money between", value1, value2, "bonusMoney");
            return (Criteria) this;
        }

        public Criteria andBonusMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("bonus_money not between", value1, value2, "bonusMoney");
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

        public Criteria andBonusTimeIsNull() {
            addCriterion("bonus_time is null");
            return (Criteria) this;
        }

        public Criteria andBonusTimeIsNotNull() {
            addCriterion("bonus_time is not null");
            return (Criteria) this;
        }

        public Criteria andBonusTimeEqualTo(Integer value) {
            addCriterion("bonus_time =", value, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeNotEqualTo(Integer value) {
            addCriterion("bonus_time <>", value, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeGreaterThan(Integer value) {
            addCriterion("bonus_time >", value, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("bonus_time >=", value, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeLessThan(Integer value) {
            addCriterion("bonus_time <", value, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeLessThanOrEqualTo(Integer value) {
            addCriterion("bonus_time <=", value, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeIn(List<Integer> values) {
            addCriterion("bonus_time in", values, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeNotIn(List<Integer> values) {
            addCriterion("bonus_time not in", values, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeBetween(Integer value1, Integer value2) {
            addCriterion("bonus_time between", value1, value2, "bonusTime");
            return (Criteria) this;
        }

        public Criteria andBonusTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("bonus_time not between", value1, value2, "bonusTime");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Integer value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Integer value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Integer value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Integer value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Integer value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Integer> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Integer> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Integer value1, Integer value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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