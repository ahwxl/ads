package com.bplow.netconn.aom.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TbWebServerExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public TbWebServerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    protected TbWebServerExample(TbWebServerExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_web_server
     *
     * @mbggenerated Sat Apr 23 12:36:53 CST 2016
     */
    protected abstract static class GeneratedCriteria {
        protected List<String> criteriaWithoutValue;

        protected List<Map<String, Object>> criteriaWithSingleValue;

        protected List<Map<String, Object>> criteriaWithListValue;

        protected List<Map<String, Object>> criteriaWithBetweenValue;

        protected GeneratedCriteria() {
            super();
            criteriaWithoutValue = new ArrayList<String>();
            criteriaWithSingleValue = new ArrayList<Map<String, Object>>();
            criteriaWithListValue = new ArrayList<Map<String, Object>>();
            criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List<String> getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List<Map<String, Object>> getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List<Map<String, Object>> getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List<Map<String, Object>> getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List<? extends Object> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List<Object> list = new ArrayList<Object>();
            list.add(value1);
            list.add(value2);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andHostIdIsNull() {
            addCriterion("host_id is null");
            return (Criteria) this;
        }

        public Criteria andHostIdIsNotNull() {
            addCriterion("host_id is not null");
            return (Criteria) this;
        }

        public Criteria andHostIdEqualTo(String value) {
            addCriterion("host_id =", value, "hostId");
            return (Criteria) this;
        }

        public Criteria andHostIdNotEqualTo(String value) {
            addCriterion("host_id <>", value, "hostId");
            return (Criteria) this;
        }

        public Criteria andHostIdGreaterThan(String value) {
            addCriterion("host_id >", value, "hostId");
            return (Criteria) this;
        }

        public Criteria andHostIdGreaterThanOrEqualTo(String value) {
            addCriterion("host_id >=", value, "hostId");
            return (Criteria) this;
        }

        public Criteria andHostIdLessThan(String value) {
            addCriterion("host_id <", value, "hostId");
            return (Criteria) this;
        }

        public Criteria andHostIdLessThanOrEqualTo(String value) {
            addCriterion("host_id <=", value, "hostId");
            return (Criteria) this;
        }

        public Criteria andHostIdLike(String value) {
            addCriterion("host_id like", value, "hostId");
            return (Criteria) this;
        }

        public Criteria andHostIdNotLike(String value) {
            addCriterion("host_id not like", value, "hostId");
            return (Criteria) this;
        }

        public Criteria andHostIdIn(List<String> values) {
            addCriterion("host_id in", values, "hostId");
            return (Criteria) this;
        }

        public Criteria andHostIdNotIn(List<String> values) {
            addCriterion("host_id not in", values, "hostId");
            return (Criteria) this;
        }

        public Criteria andHostIdBetween(String value1, String value2) {
            addCriterion("host_id between", value1, value2, "hostId");
            return (Criteria) this;
        }

        public Criteria andHostIdNotBetween(String value1, String value2) {
            addCriterion("host_id not between", value1, value2, "hostId");
            return (Criteria) this;
        }

        public Criteria andServerNameIsNull() {
            addCriterion("server_name is null");
            return (Criteria) this;
        }

        public Criteria andServerNameIsNotNull() {
            addCriterion("server_name is not null");
            return (Criteria) this;
        }

        public Criteria andServerNameEqualTo(String value) {
            addCriterion("server_name =", value, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameNotEqualTo(String value) {
            addCriterion("server_name <>", value, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameGreaterThan(String value) {
            addCriterion("server_name >", value, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameGreaterThanOrEqualTo(String value) {
            addCriterion("server_name >=", value, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameLessThan(String value) {
            addCriterion("server_name <", value, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameLessThanOrEqualTo(String value) {
            addCriterion("server_name <=", value, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameLike(String value) {
            addCriterion("server_name like", value, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameNotLike(String value) {
            addCriterion("server_name not like", value, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameIn(List<String> values) {
            addCriterion("server_name in", values, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameNotIn(List<String> values) {
            addCriterion("server_name not in", values, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameBetween(String value1, String value2) {
            addCriterion("server_name between", value1, value2, "serverName");
            return (Criteria) this;
        }

        public Criteria andServerNameNotBetween(String value1, String value2) {
            addCriterion("server_name not between", value1, value2, "serverName");
            return (Criteria) this;
        }

        public Criteria andWebPathIsNull() {
            addCriterion("web_path is null");
            return (Criteria) this;
        }

        public Criteria andWebPathIsNotNull() {
            addCriterion("web_path is not null");
            return (Criteria) this;
        }

        public Criteria andWebPathEqualTo(String value) {
            addCriterion("web_path =", value, "webPath");
            return (Criteria) this;
        }

        public Criteria andWebPathNotEqualTo(String value) {
            addCriterion("web_path <>", value, "webPath");
            return (Criteria) this;
        }

        public Criteria andWebPathGreaterThan(String value) {
            addCriterion("web_path >", value, "webPath");
            return (Criteria) this;
        }

        public Criteria andWebPathGreaterThanOrEqualTo(String value) {
            addCriterion("web_path >=", value, "webPath");
            return (Criteria) this;
        }

        public Criteria andWebPathLessThan(String value) {
            addCriterion("web_path <", value, "webPath");
            return (Criteria) this;
        }

        public Criteria andWebPathLessThanOrEqualTo(String value) {
            addCriterion("web_path <=", value, "webPath");
            return (Criteria) this;
        }

        public Criteria andWebPathLike(String value) {
            addCriterion("web_path like", value, "webPath");
            return (Criteria) this;
        }

        public Criteria andWebPathNotLike(String value) {
            addCriterion("web_path not like", value, "webPath");
            return (Criteria) this;
        }

        public Criteria andWebPathIn(List<String> values) {
            addCriterion("web_path in", values, "webPath");
            return (Criteria) this;
        }

        public Criteria andWebPathNotIn(List<String> values) {
            addCriterion("web_path not in", values, "webPath");
            return (Criteria) this;
        }

        public Criteria andWebPathBetween(String value1, String value2) {
            addCriterion("web_path between", value1, value2, "webPath");
            return (Criteria) this;
        }

        public Criteria andWebPathNotBetween(String value1, String value2) {
            addCriterion("web_path not between", value1, value2, "webPath");
            return (Criteria) this;
        }

        public Criteria andWarNameIsNull() {
            addCriterion("war_name is null");
            return (Criteria) this;
        }

        public Criteria andWarNameIsNotNull() {
            addCriterion("war_name is not null");
            return (Criteria) this;
        }

        public Criteria andWarNameEqualTo(String value) {
            addCriterion("war_name =", value, "warName");
            return (Criteria) this;
        }

        public Criteria andWarNameNotEqualTo(String value) {
            addCriterion("war_name <>", value, "warName");
            return (Criteria) this;
        }

        public Criteria andWarNameGreaterThan(String value) {
            addCriterion("war_name >", value, "warName");
            return (Criteria) this;
        }

        public Criteria andWarNameGreaterThanOrEqualTo(String value) {
            addCriterion("war_name >=", value, "warName");
            return (Criteria) this;
        }

        public Criteria andWarNameLessThan(String value) {
            addCriterion("war_name <", value, "warName");
            return (Criteria) this;
        }

        public Criteria andWarNameLessThanOrEqualTo(String value) {
            addCriterion("war_name <=", value, "warName");
            return (Criteria) this;
        }

        public Criteria andWarNameLike(String value) {
            addCriterion("war_name like", value, "warName");
            return (Criteria) this;
        }

        public Criteria andWarNameNotLike(String value) {
            addCriterion("war_name not like", value, "warName");
            return (Criteria) this;
        }

        public Criteria andWarNameIn(List<String> values) {
            addCriterion("war_name in", values, "warName");
            return (Criteria) this;
        }

        public Criteria andWarNameNotIn(List<String> values) {
            addCriterion("war_name not in", values, "warName");
            return (Criteria) this;
        }

        public Criteria andWarNameBetween(String value1, String value2) {
            addCriterion("war_name between", value1, value2, "warName");
            return (Criteria) this;
        }

        public Criteria andWarNameNotBetween(String value1, String value2) {
            addCriterion("war_name not between", value1, value2, "warName");
            return (Criteria) this;
        }

        public Criteria andServerStatusIsNull() {
            addCriterion("server_status is null");
            return (Criteria) this;
        }

        public Criteria andServerStatusIsNotNull() {
            addCriterion("server_status is not null");
            return (Criteria) this;
        }

        public Criteria andServerStatusEqualTo(String value) {
            addCriterion("server_status =", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusNotEqualTo(String value) {
            addCriterion("server_status <>", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusGreaterThan(String value) {
            addCriterion("server_status >", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusGreaterThanOrEqualTo(String value) {
            addCriterion("server_status >=", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusLessThan(String value) {
            addCriterion("server_status <", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusLessThanOrEqualTo(String value) {
            addCriterion("server_status <=", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusLike(String value) {
            addCriterion("server_status like", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusNotLike(String value) {
            addCriterion("server_status not like", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusIn(List<String> values) {
            addCriterion("server_status in", values, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusNotIn(List<String> values) {
            addCriterion("server_status not in", values, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusBetween(String value1, String value2) {
            addCriterion("server_status between", value1, value2, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusNotBetween(String value1, String value2) {
            addCriterion("server_status not between", value1, value2, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNull() {
            addCriterion("gmt_modify is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNotNull() {
            addCriterion("gmt_modify is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyEqualTo(Date value) {
            addCriterion("gmt_modify =", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotEqualTo(Date value) {
            addCriterion("gmt_modify <>", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThan(Date value) {
            addCriterion("gmt_modify >", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modify >=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThan(Date value) {
            addCriterion("gmt_modify <", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modify <=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIn(List<Date> values) {
            addCriterion("gmt_modify in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotIn(List<Date> values) {
            addCriterion("gmt_modify not in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyBetween(Date value1, Date value2) {
            addCriterion("gmt_modify between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modify not between", value1, value2, "gmtModify");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_web_server
     *
     * @mbggenerated do_not_delete_during_merge Sat Apr 23 12:36:53 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}