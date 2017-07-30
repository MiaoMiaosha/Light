/*
Navicat MySQL Data Transfer

Source Server         : 106.14.79.44
Source Server Version : 50540
Source Host           : 106.14.79.44:3306
Source Database       : light_project

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2017-07-30 18:16:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for lg_accounting
-- ----------------------------
DROP TABLE IF EXISTS `lg_accounting`;
CREATE TABLE `lg_accounting` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `receive_date` int(10) DEFAULT NULL,
  `market_id` int(11) DEFAULT NULL COMMENT '保留字段',
  `market_name` varchar(50) DEFAULT NULL COMMENT '(市场名称，调用工程表)保留字段',
  `type_id` mediumint(3) NOT NULL DEFAULT '0' COMMENT '款项类型0为未知',
  `receive_bank` mediumint(3) DEFAULT NULL COMMENT '收款银行',
  `receive_money` int(13) DEFAULT NULL COMMENT '到账金额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `invoice_status` mediumint(3) DEFAULT '0' COMMENT '开票状态：0-未开；1-已开',
  `invoice_time` int(10) DEFAULT NULL,
  `invoice_pic` text COMMENT '上传发票',
  `update_time` int(10) DEFAULT NULL COMMENT '审核时间',
  `operate_no` int(11) DEFAULT NULL COMMENT '操作员id',
  `login_user_id` int(11) DEFAULT NULL COMMENT '提交者id',
  `status` mediumint(3) DEFAULT NULL COMMENT '入账状态：0-未审核；1-已审核；2-已关闭；3-标记删除',
  `project_id` int(11) DEFAULT NULL COMMENT '工程id',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_accounting_extra
-- ----------------------------
DROP TABLE IF EXISTS `lg_accounting_extra`;
CREATE TABLE `lg_accounting_extra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(25) DEFAULT NULL,
  `type_content` mediumint(3) DEFAULT NULL COMMENT '额外信息内容：0-款项类型；1-收款银行',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_bonus
-- ----------------------------
DROP TABLE IF EXISTS `lg_bonus`;
CREATE TABLE `lg_bonus` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `market_id` int(11) DEFAULT NULL COMMENT '保留字段',
  `contractIds` varchar(30) DEFAULT NULL COMMENT '合同人id们,以：隔开',
  `project_id` int(11) DEFAULT NULL,
  `market_name` varchar(50) DEFAULT '',
  `type_id` int(4) DEFAULT NULL COMMENT '分红类型，对应extra表中',
  `receive_money` int(13) DEFAULT '0' COMMENT '到账金额',
  `travel_money` int(13) DEFAULT '0' COMMENT '差旅费',
  `rebate_money` int(13) DEFAULT '0' COMMENT '回扣',
  `other_money` int(13) DEFAULT '0',
  `real_money` int(13) DEFAULT '0' COMMENT '真实收入',
  `bonus_num` int(5) DEFAULT NULL COMMENT '分红人数',
  `person_bus_salary` int(13) DEFAULT '0' COMMENT '个人业绩收入',
  `dividend_radio` int(5) DEFAULT NULL COMMENT '分红比例，1.50%-100%',
  `bonus_level` int(13) DEFAULT NULL COMMENT '分红级别，累计业绩总和',
  `bonus_money` int(13) DEFAULT NULL COMMENT '分红金额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `bonus_time` int(11) DEFAULT NULL COMMENT '分红记录产生时间',
  `operate_no` int(11) DEFAULT NULL COMMENT '操作员id',
  `update_time` int(10) DEFAULT NULL COMMENT '分红记录审核通过时间',
  `login_user_id` int(11) DEFAULT NULL COMMENT '提交者Id',
  `status` mediumint(3) DEFAULT NULL COMMENT '分红记录状态：0-未审核；1-审核通过；2-审核关闭；3-标记删除',
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=423 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_bonus_extra
-- ----------------------------
DROP TABLE IF EXISTS `lg_bonus_extra`;
CREATE TABLE `lg_bonus_extra` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `bonus_type` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_bonus_level
-- ----------------------------
DROP TABLE IF EXISTS `lg_bonus_level`;
CREATE TABLE `lg_bonus_level` (
  `id` mediumint(3) NOT NULL AUTO_INCREMENT,
  `bonus_rate` mediumint(5) DEFAULT NULL COMMENT '分红比例, 15 = 0.15%',
  `bonus_level_name` varchar(20) DEFAULT NULL COMMENT '分红级别名称',
  `bonus_level_money` int(13) DEFAULT NULL COMMENT '分红级别对应金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_case
-- ----------------------------
DROP TABLE IF EXISTS `lg_case`;
CREATE TABLE `lg_case` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `img_url` text COMMENT '图片地址，用#分隔',
  `case_name` varchar(50) DEFAULT NULL COMMENT '案例名称',
  `level` int(4) DEFAULT '0' COMMENT '案例星级',
  `description` varchar(255) DEFAULT NULL COMMENT '市场描述',
  `create_time` int(10) DEFAULT NULL COMMENT '发布时间',
  `area` varchar(50) DEFAULT NULL COMMENT '占地面积',
  `state` int(4) DEFAULT '0' COMMENT '0-未发布，1-使用中，2-已关闭',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_case_apply
-- ----------------------------
DROP TABLE IF EXISTS `lg_case_apply`;
CREATE TABLE `lg_case_apply` (
  `caid` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '申请用户id',
  `case_id` int(11) DEFAULT NULL COMMENT '案例id',
  `create_time` int(10) DEFAULT NULL COMMENT '申请时间',
  `state` int(4) DEFAULT NULL COMMENT '0-申请中，1-申请成功，2-申请失败',
  PRIMARY KEY (`caid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_comment
-- ----------------------------
DROP TABLE IF EXISTS `lg_comment`;
CREATE TABLE `lg_comment` (
  `id` int(13) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `nick_name` varchar(20) DEFAULT NULL,
  `img_url` text,
  `create_time` int(10) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL COMMENT '相对应类型的id',
  `content` text,
  `type` mediumint(3) DEFAULT NULL COMMENT '评论类型：1-案例；2-市场；3-新闻；4-设备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_config
-- ----------------------------
DROP TABLE IF EXISTS `lg_config`;
CREATE TABLE `lg_config` (
  `id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `name` varchar(64) DEFAULT NULL COMMENT '配置的key键名',
  `value` varchar(512) DEFAULT NULL,
  `inc_type` varchar(64) DEFAULT NULL COMMENT '配置分组',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_cooperate_company
-- ----------------------------
DROP TABLE IF EXISTS `lg_cooperate_company`;
CREATE TABLE `lg_cooperate_company` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `full_name` varchar(100) NOT NULL COMMENT '公司全称',
  `country` int(11) DEFAULT NULL COMMENT '国家id',
  `province` int(11) DEFAULT NULL COMMENT '省份id',
  `city` int(11) DEFAULT NULL COMMENT '城市id',
  `district` int(11) DEFAULT NULL COMMENT '地区',
  `town` int(11) DEFAULT NULL COMMENT '乡镇id',
  `main_business` varchar(20) DEFAULT NULL COMMENT '主营业务ids,如“1:3:5”',
  `company_intro` varchar(255) DEFAULT NULL COMMENT '公司简介',
  `create_time` int(10) DEFAULT NULL COMMENT '入驻时间',
  `status` mediumint(2) DEFAULT '0' COMMENT '状态：0-未发布；1-已合作；2-合作关闭',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_cooperate_company_apply
-- ----------------------------
DROP TABLE IF EXISTS `lg_cooperate_company_apply`;
CREATE TABLE `lg_cooperate_company_apply` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `cooperate_company_id` int(11) DEFAULT NULL,
  `create_time` int(10) DEFAULT NULL,
  `state` mediumint(3) DEFAULT NULL COMMENT '0-申请中，1-申请成功，2-申请失败',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_customer
-- ----------------------------
DROP TABLE IF EXISTS `lg_customer`;
CREATE TABLE `lg_customer` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(50) DEFAULT '' COMMENT '单位名称',
  `manager` varchar(20) DEFAULT '' COMMENT '负责人',
  `mobile` varchar(20) DEFAULT '',
  `address` varchar(255) DEFAULT '' COMMENT '快递地址',
  `receive_person` varchar(20) DEFAULT '' COMMENT '收件人名字',
  `receive_mobile` varchar(20) DEFAULT '' COMMENT '收件人电话',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `status` mediumint(3) DEFAULT '0' COMMENT '状态：0-正常；1-冻结; 3-标记删除',
  `create_time` int(10) DEFAULT NULL COMMENT '客户档案提交时间',
  `login_user_id` int(11) DEFAULT NULL COMMENT '提交人id',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_employee
-- ----------------------------
DROP TABLE IF EXISTS `lg_employee`;
CREATE TABLE `lg_employee` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  `job` int(4) DEFAULT NULL COMMENT '职称，1-设计师，2-合同人，3-会计',
  `company_name` varchar(60) DEFAULT NULL,
  `company_address` varchar(255) DEFAULT NULL,
  `company_account` varchar(30) DEFAULT NULL COMMENT '单位账户，银行账号',
  `account_bank` varchar(255) DEFAULT NULL COMMENT '银行支行',
  `birthday` int(10) DEFAULT NULL,
  `sex` int(4) DEFAULT NULL COMMENT '0-保密；1-男；2-女',
  `idcard_number` varchar(18) DEFAULT '',
  `idcard_address` varchar(255) DEFAULT NULL COMMENT '身份证地址',
  `idcard_front_url` text COMMENT '身份证正面照',
  `idcard_behind_url` text COMMENT '身份证背面照',
  `education` text COMMENT '学历上传',
  `contact` text,
  `probation_base_salary` int(10) DEFAULT NULL COMMENT '试用底薪',
  `official_salary_one` int(10) DEFAULT '0' COMMENT '正式底薪一',
  `official_salary_two` int(10) DEFAULT NULL COMMENT '正式底薪一',
  `official_salary_three` int(10) DEFAULT NULL COMMENT '正式底薪一',
  `official_salary_four` int(10) DEFAULT NULL COMMENT '正式底薪一',
  `mobile` varchar(25) DEFAULT '',
  `qq` varchar(100) DEFAULT NULL COMMENT 'QQ号',
  `wx` varchar(100) DEFAULT NULL COMMENT '微信号',
  `dear_friend_name` varchar(16) DEFAULT NULL,
  `dear_friend_mobile` varchar(25) DEFAULT NULL,
  `register_time` int(10) DEFAULT NULL COMMENT '注册时间',
  `entry_time` int(10) DEFAULT NULL COMMENT '入职时间',
  `status` mediumint(3) DEFAULT '0' COMMENT '员工状态：0-正常；1-冻结；2-标记删除',
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_employee_bonus
-- ----------------------------
DROP TABLE IF EXISTS `lg_employee_bonus`;
CREATE TABLE `lg_employee_bonus` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工-分红多对多映射',
  `employee_id` int(11) NOT NULL,
  `bonus_id` int(11) DEFAULT NULL,
  `employee_name` varchar(20) DEFAULT NULL COMMENT '员工姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=423 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_employee_project
-- ----------------------------
DROP TABLE IF EXISTS `lg_employee_project`;
CREATE TABLE `lg_employee_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL COMMENT '工程id',
  `employee_name` varchar(20) DEFAULT NULL COMMENT '客户名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_floor
-- ----------------------------
DROP TABLE IF EXISTS `lg_floor`;
CREATE TABLE `lg_floor` (
  `fid` int(8) NOT NULL AUTO_INCREMENT,
  `floor_name` varchar(50) DEFAULT NULL COMMENT '楼层名称',
  `is_used` tinyint(1) DEFAULT NULL COMMENT '是否在使用',
  `market_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_goods
-- ----------------------------
DROP TABLE IF EXISTS `lg_goods`;
CREATE TABLE `lg_goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备id',
  `goods_name` varchar(100) DEFAULT NULL,
  `goods_price` int(11) DEFAULT NULL COMMENT '设备价格',
  `sku_json` text COMMENT '{sku_info:[{"sku_name":"17寸_4G内存_电信","price":"100.0"}]}',
  `description` varchar(255) DEFAULT NULL COMMENT '设备说明',
  `brand` varchar(50) DEFAULT NULL COMMENT '品牌',
  `mobile` varchar(20) DEFAULT NULL COMMENT '电话',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `status` mediumint(2) DEFAULT '0',
  `user_id` int(11) DEFAULT NULL COMMENT '申请商家id',
  `last_update_time` int(10) DEFAULT NULL COMMENT '最后更新时间（上、下架）',
  `img` text COMMENT '商品自带缩略图',
  `type` mediumint(3) DEFAULT NULL COMMENT '1-智能电子；2-成品货架；3-专用材料',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_goods_apply
-- ----------------------------
DROP TABLE IF EXISTS `lg_goods_apply`;
CREATE TABLE `lg_goods_apply` (
  `gaid` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `create_time` int(10) DEFAULT NULL,
  `status` mediumint(9) DEFAULT NULL,
  PRIMARY KEY (`gaid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_goods_image
-- ----------------------------
DROP TABLE IF EXISTS `lg_goods_image`;
CREATE TABLE `lg_goods_image` (
  `id` int(11) NOT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `img_url` text,
  `type` mediumint(3) DEFAULT NULL COMMENT '1-一般图片，2-证书等其他图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_hotwords
-- ----------------------------
DROP TABLE IF EXISTS `lg_hotwords`;
CREATE TABLE `lg_hotwords` (
  `id` int(13) NOT NULL AUTO_INCREMENT,
  `contentId` int(11) DEFAULT NULL,
  `content` varchar(150) DEFAULT NULL,
  `type` mediumint(3) DEFAULT NULL,
  `create_time` int(10) DEFAULT NULL,
  `heat` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_introduce
-- ----------------------------
DROP TABLE IF EXISTS `lg_introduce`;
CREATE TABLE `lg_introduce` (
  `id` int(5) NOT NULL,
  `content` text,
  `typeName` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_like
-- ----------------------------
DROP TABLE IF EXISTS `lg_like`;
CREATE TABLE `lg_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `content_id` int(11) DEFAULT NULL COMMENT 'type为物件时，content存相关名字;type为网页时，content存对应字段',
  `content` varchar(20) DEFAULT NULL,
  `type` mediumint(3) DEFAULT NULL COMMENT '收藏的类型：1案例，2.招商，3设备 ,4 新闻，5页面',
  `create_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_login_cuslist
-- ----------------------------
DROP TABLE IF EXISTS `lg_login_cuslist`;
CREATE TABLE `lg_login_cuslist` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `open_id` text,
  `login_user_id` int(11) DEFAULT NULL COMMENT '登录id',
  `headimgurl` text,
  `nickname` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_login_user
-- ----------------------------
DROP TABLE IF EXISTS `lg_login_user`;
CREATE TABLE `lg_login_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type_id` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role_ids` varchar(50) DEFAULT NULL COMMENT '角色 ： 1-管理员；2-会计；3-员工；4-客户',
  `is_locked` mediumint(3) DEFAULT NULL COMMENT '状态：0-正常；1-锁定；2-标记删除',
  `user_type` mediumint(3) DEFAULT NULL COMMENT '用户类型：1-员工；2-客户',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '用户昵称，从employee_name或company_name获得',
  `open_id` varchar(255) DEFAULT NULL,
  `unionid` varchar(255) DEFAULT NULL,
  `head_img` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_main_business
-- ----------------------------
DROP TABLE IF EXISTS `lg_main_business`;
CREATE TABLE `lg_main_business` (
  `mbid` mediumint(4) NOT NULL AUTO_INCREMENT,
  `main_business_name` varchar(100) DEFAULT NULL COMMENT '主营业务名称',
  PRIMARY KEY (`mbid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_market
-- ----------------------------
DROP TABLE IF EXISTS `lg_market`;
CREATE TABLE `lg_market` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '市场管理用户id',
  `market_name` varchar(50) DEFAULT NULL,
  `country` int(11) DEFAULT NULL COMMENT '国家id',
  `province` int(11) DEFAULT NULL COMMENT '省份id',
  `city` int(11) DEFAULT NULL COMMENT '城市id',
  `district` int(11) DEFAULT NULL COMMENT '地区',
  `town` int(11) DEFAULT NULL COMMENT '乡镇id',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `area` varchar(50) DEFAULT NULL COMMENT '预估面积,平方米',
  `market_company` varchar(50) DEFAULT NULL COMMENT '市场单位（选填）',
  `market_intro` varchar(255) DEFAULT NULL COMMENT '市场简介（选填）',
  `market_activity` varchar(255) DEFAULT NULL COMMENT '市场优惠活动说明',
  `create_time` int(10) DEFAULT NULL COMMENT '生成时间',
  `contact_name` varchar(20) DEFAULT NULL COMMENT '联系人',
  `contact_mobile` varchar(20) DEFAULT NULL COMMENT '联系人电话',
  `meet_address` varchar(255) DEFAULT NULL COMMENT '招商洽谈地址',
  `img_url` text COMMENT '图片地址，用#分隔',
  `status` mediumint(1) DEFAULT NULL COMMENT '市场状态：0-未发布；1-使用中；2-已关闭',
  `contract_url` text COMMENT '上传的合同',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_market_apply
-- ----------------------------
DROP TABLE IF EXISTS `lg_market_apply`;
CREATE TABLE `lg_market_apply` (
  `maid` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '发布者id',
  `market_id` int(11) DEFAULT NULL,
  `create_time` int(10) DEFAULT NULL,
  `status` mediumint(3) DEFAULT NULL COMMENT '0-申请中，1-申请成功，2-申请失败',
  PRIMARY KEY (`maid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_market_files
-- ----------------------------
DROP TABLE IF EXISTS `lg_market_files`;
CREATE TABLE `lg_market_files` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `market_id` int(11) DEFAULT NULL COMMENT '市场id',
  `file_url` text COMMENT '文件相对地址',
  `mineType` varchar(20) DEFAULT NULL COMMENT '文件类型',
  `create_time` int(10) DEFAULT NULL COMMENT '记录生成时间',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_memo
-- ----------------------------
DROP TABLE IF EXISTS `lg_memo`;
CREATE TABLE `lg_memo` (
  `id` int(13) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `process_id` mediumint(3) DEFAULT NULL,
  `login_user_id` int(11) DEFAULT NULL,
  `content` text,
  `create_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_news
-- ----------------------------
DROP TABLE IF EXISTS `lg_news`;
CREATE TABLE `lg_news` (
  `nid` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻Id',
  `user_id` int(11) DEFAULT NULL COMMENT '发布者id',
  `user_name` varchar(30) DEFAULT NULL COMMENT '发布者姓名',
  `content` text COMMENT '新闻内容',
  `status` mediumint(3) DEFAULT NULL COMMENT '状态：0-未发布；1-发布中；2-发布关闭',
  `news_title` varchar(50) DEFAULT NULL COMMENT '新闻标题',
  `create_time` int(10) DEFAULT NULL COMMENT '创建时间',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览次数',
  `type` mediumint(3) DEFAULT '0' COMMENT '1-公司新闻 2-行业资讯',
  `img_url` text COMMENT '新闻首页图片',
  `pc_content` text COMMENT 'pc内容',
  `content_type` mediumint(3) DEFAULT '0' COMMENT '内容类型：0-手机；1-pc',
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_news_apply
-- ----------------------------
DROP TABLE IF EXISTS `lg_news_apply`;
CREATE TABLE `lg_news_apply` (
  `naid` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `news_id` int(11) DEFAULT NULL,
  `create_time` int(10) DEFAULT NULL,
  `status` mediumint(3) DEFAULT NULL COMMENT '0-申请中，1-申请成功，2-申请失败',
  PRIMARY KEY (`naid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_news_comment
-- ----------------------------
DROP TABLE IF EXISTS `lg_news_comment`;
CREATE TABLE `lg_news_comment` (
  `ncid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '评论者id',
  `user_name` varchar(30) DEFAULT NULL COMMENT '评论者昵称',
  `content` varchar(255) DEFAULT NULL COMMENT '新闻评论内容',
  `create_time` int(10) DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`ncid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_news_image
-- ----------------------------
DROP TABLE IF EXISTS `lg_news_image`;
CREATE TABLE `lg_news_image` (
  `ni_id` int(11) NOT NULL AUTO_INCREMENT,
  `news_id` int(11) DEFAULT NULL,
  `img_url` text,
  PRIMARY KEY (`ni_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_other_apply
-- ----------------------------
DROP TABLE IF EXISTS `lg_other_apply`;
CREATE TABLE `lg_other_apply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `type_name` varchar(100) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `apply_status` mediumint(3) DEFAULT '0' COMMENT '0-申请中，1-申请成功，2-申请失败',
  `type` mediumint(3) DEFAULT NULL COMMENT '0-合作申请 1-电商申请',
  `extra_info` text COMMENT '其他信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_partner
-- ----------------------------
DROP TABLE IF EXISTS `lg_partner`;
CREATE TABLE `lg_partner` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `cooperate_content` varchar(255) DEFAULT NULL COMMENT '合作内容介绍',
  `chief_person` varchar(20) DEFAULT NULL COMMENT '负责人姓名',
  `mobile` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `bank_account` varchar(50) DEFAULT NULL COMMENT '银行账户',
  `bank_account_no` varchar(25) DEFAULT NULL COMMENT '账号',
  `bank_branch` varchar(50) DEFAULT NULL COMMENT '支行名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` int(11) DEFAULT NULL COMMENT '合作记录时间',
  `status` mediumint(3) DEFAULT NULL COMMENT '合作状态：0-审核中；1-合作中；2-合作关闭；3-标记删除',
  `cooperate_name` varchar(50) DEFAULT NULL COMMENT '合作方名称',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_post
-- ----------------------------
DROP TABLE IF EXISTS `lg_post`;
CREATE TABLE `lg_post` (
  `pid` int(13) NOT NULL AUTO_INCREMENT,
  `login_user_id` int(11) NOT NULL COMMENT '发帖人id',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '发帖人昵称',
  `post_title` varchar(50) DEFAULT '' COMMENT '帖子标题,你想发布什么',
  `type` mediumint(3) DEFAULT NULL COMMENT '帖子所属阶段id，在project_process中找',
  `content` text COMMENT '帖子具体咨询内容，可存html代码',
  `upload_img` text COMMENT '上传图片s',
  `upload_doc` text COMMENT '上传文档s',
  `create_time` int(10) DEFAULT NULL COMMENT '发帖时间',
  `project_id` int(10) DEFAULT NULL COMMENT '帖子对应工程id',
  `status` mediumint(3) DEFAULT '0' COMMENT '状态: 0-正常；1-b标记删除',
  `open_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_post_comment
-- ----------------------------
DROP TABLE IF EXISTS `lg_post_comment`;
CREATE TABLE `lg_post_comment` (
  `pcid` int(13) NOT NULL AUTO_INCREMENT,
  `login_user_id` int(11) NOT NULL COMMENT '发表评论人',
  `login_user_name` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `parent_id` int(10) DEFAULT '0' COMMENT '父id',
  `parent_user_name` varchar(20) DEFAULT '' COMMENT '当parent_id不为0时，获取此字段',
  `img_url` text COMMENT '评论图片',
  `create_time` int(10) DEFAULT NULL COMMENT '评论时间',
  `post_id` int(13) DEFAULT NULL COMMENT '帖子id',
  `content` text COMMENT '帖子评论',
  `open_id` varchar(100) DEFAULT NULL COMMENT '发评论用户openId',
  PRIMARY KEY (`pcid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_post_employee
-- ----------------------------
DROP TABLE IF EXISTS `lg_post_employee`;
CREATE TABLE `lg_post_employee` (
  `peid` int(13) NOT NULL AUTO_INCREMENT,
  `post_id` int(13) NOT NULL COMMENT '帖子id',
  `employee_id` int(13) DEFAULT NULL COMMENT '相关员工id',
  PRIMARY KEY (`peid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='帖子-负责员工映射表,@员工的';

-- ----------------------------
-- Table structure for lg_project
-- ----------------------------
DROP TABLE IF EXISTS `lg_project`;
CREATE TABLE `lg_project` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` int(10) DEFAULT NULL COMMENT '项目合同日期',
  `market_id` int(11) DEFAULT '0' COMMENT '保留字段',
  `market_name` varchar(50) DEFAULT NULL COMMENT '市场名称,用户自填',
  `customer_id` int(11) DEFAULT NULL COMMENT '单位id，客户档案调用',
  `customer_name` varchar(100) DEFAULT NULL COMMENT '单位名称，客户档案调用',
  `real_contact_money` int(13) DEFAULT '0' COMMENT '实际合同价,等于=总价+差旅金额+回扣+其他',
  `contact_money` int(13) DEFAULT '0' COMMENT '合同价',
  `first_money` int(13) DEFAULT '0',
  `first_finish_time` int(10) DEFAULT NULL,
  `second_money` int(13) DEFAULT '0',
  `second_finish_time` int(10) DEFAULT NULL,
  `third_money` int(13) DEFAULT '0',
  `third_finish_time` int(10) DEFAULT NULL,
  `four_money` int(13) DEFAULT '0',
  `four_finish_time` int(10) DEFAULT NULL,
  `five_money` int(13) DEFAULT '0',
  `five_finish_time` int(10) DEFAULT NULL,
  `remark` varchar(255) DEFAULT '',
  `bus_travel_type` int(2) DEFAULT '0' COMMENT '差旅类型：0-甲方全报销；1-甲方报销交通；2-甲方报销住宿；3-乙方全报销',
  `travel_money` int(13) DEFAULT '0' COMMENT '差旅金额',
  `rebate_money` int(13) DEFAULT '0' COMMENT '回扣金额',
  `extra_money` int(13) DEFAULT '0' COMMENT '其他金额',
  `signing_company` int(2) DEFAULT '0' COMMENT '签约公司：0-光影；1-菜源；2-其他',
  `province` int(11) DEFAULT NULL,
  `city` int(11) DEFAULT NULL,
  `district` int(11) DEFAULT NULL,
  `town` int(11) DEFAULT NULL,
  `market_address` varchar(255) DEFAULT '' COMMENT '市场地址',
  `market_intro` varchar(255) DEFAULT '' COMMENT '市场介绍',
  `status` mediumint(3) DEFAULT '0' COMMENT '工程状态：0-未开始；1-进行中；2-审核失败；3-审核成功；4-成功结束；5-标记删除',
  `process_id` mediumint(3) DEFAULT '0' COMMENT '记录当前工程阶段0表示未开始',
  `login_user_id` int(11) DEFAULT NULL COMMENT '提交用户id',
  `upload_pic` text COMMENT '上传的市场图片',
  `upload_doc` text COMMENT '上传的市场合同',
  `process_str` varchar(100) DEFAULT NULL COMMENT '2:3:4',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_project_process
-- ----------------------------
DROP TABLE IF EXISTS `lg_project_process`;
CREATE TABLE `lg_project_process` (
  `id` mediumint(3) NOT NULL COMMENT '父id',
  `name` varchar(20) NOT NULL COMMENT '阶段名称',
  `level` mediumint(3) NOT NULL COMMENT '阶段层级',
  `parent_id` mediumint(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_record
-- ----------------------------
DROP TABLE IF EXISTS `lg_record`;
CREATE TABLE `lg_record` (
  `rid` int(13) NOT NULL AUTO_INCREMENT COMMENT '记事本-暂时不管',
  `user_id` int(11) DEFAULT NULL,
  `user_type` mediumint(3) DEFAULT '0' COMMENT '用户类型：0-项目客户_customer_id；1-员工型用户_employee_id',
  `record_time` int(10) DEFAULT NULL,
  `person_name` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `process_one` varchar(255) DEFAULT NULL,
  `process_two` varchar(255) DEFAULT NULL,
  `process_three` varchar(255) DEFAULT NULL,
  `amount` varchar(20) DEFAULT NULL,
  `record_result` mediumint(3) DEFAULT '0' COMMENT '结果：0-未处理；1-已完成；2-已关闭',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_region
-- ----------------------------
DROP TABLE IF EXISTS `lg_region`;
CREATE TABLE `lg_region` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `name` varchar(32) DEFAULT NULL COMMENT '地区名称',
  `level` tinyint(4) DEFAULT NULL COMMENT '地区等级 分省市县区',
  `parent_id` int(10) DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47498 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for lg_reimburse
-- ----------------------------
DROP TABLE IF EXISTS `lg_reimburse`;
CREATE TABLE `lg_reimburse` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'reimburse_id',
  `reimburse_user_id` int(11) NOT NULL COMMENT '报销人id',
  `reimburse_user_type` mediumint(3) NOT NULL COMMENT '报销人类型:0-员工档案；1-合作方档案-partner_id',
  `money` int(13) DEFAULT '0' COMMENT '报销金额',
  `market_name` varchar(50) DEFAULT '' COMMENT '市场名称（可以不填）',
  `event` varchar(255) DEFAULT '' COMMENT '报销相关事件说明',
  `type_id` int(4) DEFAULT NULL COMMENT '报销类型id',
  `invoice_status` int(4) DEFAULT '0' COMMENT '发票状态：0-未提供；1-提供；2-部分提供',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` int(10) DEFAULT NULL COMMENT '报销申请时间',
  `status` mediumint(3) DEFAULT '0' COMMENT '报销状态：0-申请中；1-申请成功；2-关闭；3-标记删除',
  `operate_no` int(11) DEFAULT NULL,
  `update_time` int(10) DEFAULT NULL COMMENT '审核操作时间',
  `login_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_reimburse_extra
-- ----------------------------
DROP TABLE IF EXISTS `lg_reimburse_extra`;
CREATE TABLE `lg_reimburse_extra` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_salary
-- ----------------------------
DROP TABLE IF EXISTS `lg_salary`;
CREATE TABLE `lg_salary` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `person_commit_time` int(10) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `salary_type` int(4) DEFAULT '0' COMMENT '收入类型，对应类型表id',
  `design_content_type` int(4) NOT NULL COMMENT '设计内容，对应表id',
  `salary_money` int(13) DEFAULT NULL COMMENT '收入金额',
  `remark` varchar(255) DEFAULT NULL,
  `salary_other` varchar(20) DEFAULT NULL COMMENT '收入其他备注',
  `design_other` varchar(20) DEFAULT NULL COMMENT '设计其他备注',
  `design_modify` varchar(20) DEFAULT NULL COMMENT '设计（修改）',
  `create_time` int(10) DEFAULT NULL COMMENT '记录产生时间',
  `finish_time` int(10) DEFAULT NULL COMMENT '审核通过日期',
  `operate_no` int(11) DEFAULT NULL COMMENT '操作员号',
  `login_user_id` int(11) DEFAULT NULL,
  `status` mediumint(3) DEFAULT NULL COMMENT '状态：0-未审核；1-审核成功；2-已关闭；3-标记删除',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=537 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_salary_extra
-- ----------------------------
DROP TABLE IF EXISTS `lg_salary_extra`;
CREATE TABLE `lg_salary_extra` (
  `tid` int(4) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(30) DEFAULT NULL COMMENT '类型内容',
  `type` int(4) DEFAULT NULL COMMENT '0-收入类型；1-设计内容',
  `type_money` int(13) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_stall
-- ----------------------------
DROP TABLE IF EXISTS `lg_stall`;
CREATE TABLE `lg_stall` (
  `sid` int(8) NOT NULL AUTO_INCREMENT COMMENT '摊位id',
  `market_id` int(8) NOT NULL COMMENT '归属于市场id',
  `floor_id` int(11) NOT NULL,
  `stall_name` varchar(20) NOT NULL,
  `contact_mobile` varchar(20) DEFAULT NULL,
  `contact_name` varchar(50) DEFAULT NULL COMMENT '联系人名称',
  `create_time` int(10) DEFAULT NULL,
  `status` mediumint(3) DEFAULT NULL COMMENT '状态：0-未使用；1-申请中；2-使用中；3-已关闭--改成0-未使用，1-使用中，2-已关闭',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_stall_apply
-- ----------------------------
DROP TABLE IF EXISTS `lg_stall_apply`;
CREATE TABLE `lg_stall_apply` (
  `said` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `create_time` int(10) DEFAULT NULL,
  `status` mediumint(3) DEFAULT NULL COMMENT '0-申请中，1-申请成功，2-申请失败',
  `contact_name` varchar(50) DEFAULT NULL,
  `contact_mobile` varchar(20) DEFAULT NULL,
  `stall_id` int(11) DEFAULT NULL,
  `stall_name` varchar(20) DEFAULT '' COMMENT '摊位名称',
  `market_id` int(11) DEFAULT NULL,
  `market_name` varchar(50) DEFAULT NULL,
  `floor_id` int(11) DEFAULT NULL,
  `floor_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`said`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `lg_sys_menu`;
CREATE TABLE `lg_sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Table structure for lg_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `lg_sys_role`;
CREATE TABLE `lg_sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` int(10) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Table structure for lg_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `lg_sys_role_menu`;
CREATE TABLE `lg_sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Table structure for lg_token
-- ----------------------------
DROP TABLE IF EXISTS `lg_token`;
CREATE TABLE `lg_token` (
  `id` int(11) NOT NULL,
  `token` text,
  `token_expire` int(10) DEFAULT '0',
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_total_count
-- ----------------------------
DROP TABLE IF EXISTS `lg_total_count`;
CREATE TABLE `lg_total_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `date_no` int(10) DEFAULT NULL COMMENT '时间编号，以月份为单位',
  `total_salary` int(13) DEFAULT '0' COMMENT '底薪总额',
  `commission` int(13) DEFAULT '0' COMMENT '提成',
  `reward` int(13) DEFAULT '0' COMMENT '奖金',
  `travel_money` int(13) DEFAULT '0' COMMENT '出差',
  `bonus` int(13) DEFAULT '0' COMMENT '分红总金额，实际分红到的金额',
  `person_salary` int(13) DEFAULT '0' COMMENT '个人业绩收入总和，即分红级别-分红的业绩收入概念',
  `status` mediumint(3) DEFAULT NULL COMMENT '统计状态：0-未审核；1-审核通过；2-审核关闭;3-员工删除后，标记删除',
  `other_money` int(13) DEFAULT NULL COMMENT '其他金额统计',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_train_book
-- ----------------------------
DROP TABLE IF EXISTS `lg_train_book`;
CREATE TABLE `lg_train_book` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(50) DEFAULT NULL COMMENT '管理教材名',
  `img_url` text,
  `content` text,
  `create_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_train_magzine
-- ----------------------------
DROP TABLE IF EXISTS `lg_train_magzine`;
CREATE TABLE `lg_train_magzine` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '期刊名称',
  `img_url` text,
  `content` text,
  `title` varchar(50) DEFAULT NULL COMMENT '期刊标题',
  `create_time` int(10) DEFAULT NULL COMMENT '添加日期',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_train_signup_apply
-- ----------------------------
DROP TABLE IF EXISTS `lg_train_signup_apply`;
CREATE TABLE `lg_train_signup_apply` (
  `suid` int(11) NOT NULL AUTO_INCREMENT COMMENT '培训报名id',
  `user_id` int(11) DEFAULT NULL COMMENT '对应用户id',
  `contact_name` varchar(50) DEFAULT NULL,
  `contact_mobile` varchar(20) DEFAULT NULL,
  `create_time` int(10) DEFAULT NULL COMMENT '报名时间',
  PRIMARY KEY (`suid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_train_software
-- ----------------------------
DROP TABLE IF EXISTS `lg_train_software`;
CREATE TABLE `lg_train_software` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `img_url` text,
  `content` text,
  `create_time` int(10) DEFAULT NULL,
  `software_intro` varchar(255) DEFAULT NULL COMMENT '管理软件介绍',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_train_teacher
-- ----------------------------
DROP TABLE IF EXISTS `lg_train_teacher`;
CREATE TABLE `lg_train_teacher` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(25) DEFAULT NULL COMMENT '讲师名称',
  `mobile` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `sex` int(4) DEFAULT NULL COMMENT '0-保密；1-男；2-女',
  `teacher_intro` varchar(255) DEFAULT NULL COMMENT '讲师简介',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_uid
-- ----------------------------
DROP TABLE IF EXISTS `lg_uid`;
CREATE TABLE `lg_uid` (
  `id` int(13) NOT NULL AUTO_INCREMENT,
  `uid` varchar(14) NOT NULL,
  `create_time` int(10) DEFAULT NULL,
  `status` mediumint(3) DEFAULT NULL COMMENT '0-未处理，1-已登录，2-已过期',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_user
-- ----------------------------
DROP TABLE IF EXISTS `lg_user`;
CREATE TABLE `lg_user` (
  `user_id` int(8) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(24) DEFAULT NULL,
  `is_locked` tinyint(1) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL COMMENT '0-保密；1-男；2-女',
  `reg_time` int(10) DEFAULT NULL COMMENT '注册时间,为上次更新用户信息的时间，间隔三天',
  `headimgurl` text COMMENT '头像',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(60) DEFAULT NULL COMMENT '电子邮件',
  `last_login_time` int(10) DEFAULT NULL,
  `openid` varchar(100) DEFAULT NULL COMMENT '第三方唯一标示',
  `unionid` varchar(100) DEFAULT NULL,
  `province` varchar(10) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `country` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lg_wx_user
-- ----------------------------
DROP TABLE IF EXISTS `lg_wx_user`;
CREATE TABLE `lg_wx_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `uid` int(11) DEFAULT NULL COMMENT 'uid',
  `wxname` varchar(60) DEFAULT NULL COMMENT '公众号名称',
  `aeskey` varchar(256) DEFAULT '' COMMENT 'aeskey',
  `encode` tinyint(1) DEFAULT '0' COMMENT 'encode',
  `appid` varchar(50) NOT NULL DEFAULT '' COMMENT 'appid',
  `appsecret` varchar(50) NOT NULL DEFAULT '' COMMENT 'appsecret',
  `wxid` varchar(20) DEFAULT NULL COMMENT '公众号原始ID',
  `weixin` char(20) DEFAULT NULL COMMENT '微信号',
  `headerpic` text COMMENT '头像地址',
  `token` text COMMENT 'token',
  `w_token` varchar(150) DEFAULT '' COMMENT '微信对接token',
  `create_time` int(13) DEFAULT NULL COMMENT 'create_time',
  `updatetime` int(13) DEFAULT NULL COMMENT 'updatetime',
  `authorizer_access_token` text COMMENT 'authorizer_access_token',
  `authorizer_refresh_token` text COMMENT 'authorizer_refresh_token',
  `authorizer_expires` int(10) DEFAULT NULL COMMENT 'authorizer_expires',
  `web_access_token` text COMMENT ' 网页授权token',
  `web_refresh_token` text COMMENT 'web_refresh_token',
  `web_expires` int(10) DEFAULT NULL COMMENT '过期时间',
  `qr` text COMMENT 'qr',
  `menu_config` text COMMENT '菜单',
  `wait_access` tinyint(1) DEFAULT '0' COMMENT '微信接入状态,0待接入1已接入',
  `type` mediumint(3) DEFAULT NULL COMMENT '0-未认证订阅号；1-已认证订阅号；2-未认证服务号；3-已认证服务号',
  `jsapi_ticket` text,
  `jsapi_ticket_expires` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`) USING BTREE,
  KEY `uid_2` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='微信公共帐号';
