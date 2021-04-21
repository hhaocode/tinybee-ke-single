/*
 Navicat MySQL Data Transfer

 Source Server         : selfpro
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 49.235.152.166:3306
 Source Schema         : tinybee-ke

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 30/10/2020 09:40:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_base_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_base_dict`;
CREATE TABLE `t_base_dict`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典代码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
  `src_sql` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行sql',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `available` tinyint(1) NULL DEFAULT 1 COMMENT '状态 true 可用 false 不可用',
  `creator` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `modifier` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_base_dict
-- ----------------------------
INSERT INTO `t_base_dict` VALUES (1, 'education', '学历', NULL, NULL, 1, NULL, NULL, '2020-08-17 18:20:06', '2020-08-17 18:20:06');
INSERT INTO `t_base_dict` VALUES (2, 'gender', '性别', NULL, NULL, 1, NULL, NULL, '2020-08-17 18:25:54', '2020-08-17 18:25:54');
INSERT INTO `t_base_dict` VALUES (3, 'whether', '是否', '', '', 1, NULL, 1, '2020-08-17 23:19:16', '2020-08-17 23:19:17');
INSERT INTO `t_base_dict` VALUES (5, 'working_state', '在职状态', '', '', 1, NULL, 1, '2020-08-17 23:40:51', '2020-08-17 23:40:52');

-- ----------------------------
-- Table structure for t_base_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `t_base_dict_item`;
CREATE TABLE `t_base_dict_item`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '字典ID',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典标签',
  `value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典值',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `is_default` tinyint(1) NULL DEFAULT 0 COMMENT '是否默认',
  `sort` int(0) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序',
  `available` tinyint(1) NULL DEFAULT 1 COMMENT '状态 true 可用 false 不可用',
  `creator` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `modifier` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典子项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_base_dict_item
-- ----------------------------
INSERT INTO `t_base_dict_item` VALUES (1, 1, '小学', '1', NULL, 0, 1, 1, NULL, NULL, '2020-08-17 18:22:32', '2020-08-17 18:22:32');
INSERT INTO `t_base_dict_item` VALUES (2, 1, '初中', '2', NULL, 0, 1, 1, NULL, NULL, '2020-08-17 18:22:40', '2020-08-17 18:22:40');
INSERT INTO `t_base_dict_item` VALUES (3, 1, '高中', '3', NULL, 0, 1, 1, NULL, NULL, '2020-08-17 18:22:51', '2020-08-17 18:22:51');
INSERT INTO `t_base_dict_item` VALUES (4, 1, '大专', '4', NULL, 0, 1, 1, NULL, NULL, '2020-08-17 18:23:04', '2020-08-17 18:23:04');
INSERT INTO `t_base_dict_item` VALUES (5, 1, '本科', '5', NULL, 0, 1, 1, NULL, NULL, '2020-08-17 18:23:17', '2020-08-17 18:23:17');
INSERT INTO `t_base_dict_item` VALUES (6, 1, '研究生', '6', NULL, 0, 1, 1, NULL, NULL, '2020-08-17 18:23:32', '2020-08-17 18:23:32');
INSERT INTO `t_base_dict_item` VALUES (7, 1, '博士', '7', NULL, 0, 1, 1, NULL, NULL, '2020-08-17 18:23:45', '2020-08-17 18:23:45');
INSERT INTO `t_base_dict_item` VALUES (8, 1, '博士后', '8', NULL, 0, 1, 1, NULL, NULL, '2020-08-17 18:23:54', '2020-08-17 18:23:54');
INSERT INTO `t_base_dict_item` VALUES (9, 1, '其他', '9', NULL, 0, 1, 1, NULL, NULL, '2020-08-17 18:24:10', '2020-08-17 18:24:10');
INSERT INTO `t_base_dict_item` VALUES (10, 2, '男', 'M', NULL, 0, 1, 1, NULL, NULL, '2020-08-17 18:26:22', '2020-08-17 18:26:22');
INSERT INTO `t_base_dict_item` VALUES (11, 2, '女', 'F', NULL, 0, 1, 1, NULL, NULL, '2020-08-17 18:26:33', '2020-08-17 18:26:33');
INSERT INTO `t_base_dict_item` VALUES (12, 2, '保密', 'U', NULL, 0, 1, 1, NULL, NULL, '2020-08-17 18:26:50', '2020-08-17 18:26:50');
INSERT INTO `t_base_dict_item` VALUES (13, 3, '是', '1', '是', 0, 0, 1, NULL, 1, '2020-08-17 23:37:47', '2020-08-17 23:37:49');
INSERT INTO `t_base_dict_item` VALUES (14, 3, '否', '0', '否', 0, 0, 1, NULL, 1, '2020-08-17 23:37:47', '2020-08-17 23:37:49');
INSERT INTO `t_base_dict_item` VALUES (15, 5, '在职', '1', '', 0, 0, 1, NULL, 1, '2020-08-17 23:41:37', '2020-08-17 23:41:39');
INSERT INTO `t_base_dict_item` VALUES (16, 5, '休假', '2', '', 0, 0, 1, NULL, 1, '2020-08-17 23:41:37', '2020-08-17 23:41:39');
INSERT INTO `t_base_dict_item` VALUES (17, 5, '离职', '3', '', 0, 0, 1, NULL, 1, '2020-08-17 23:41:37', '2020-08-17 23:41:39');
INSERT INTO `t_base_dict_item` VALUES (18, 5, '退休', '4', '', 0, 0, 1, NULL, 1, '2020-08-17 23:41:38', '2020-08-17 23:41:39');

-- ----------------------------
-- Table structure for t_base_express
-- ----------------------------
DROP TABLE IF EXISTS `t_base_express`;
CREATE TABLE `t_base_express`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '快递公司代码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '快递公司名称',
  `sort` int(0) NOT NULL COMMENT '排序',
  `available` tinyint(1) NOT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '快递表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_cms_adv
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_adv`;
CREATE TABLE `t_cms_adv`  (
  `id` int(0) UNSIGNED NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告标题',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '广告图片',
  `target_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告链接',
  `target` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '_blank' COMMENT '目标',
  `location` tinyint(0) NOT NULL DEFAULT 1 COMMENT '广告位置(1首页轮播)',
  `begin_time` datetime(0) NOT NULL COMMENT '开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `platform` tinyint(0) NOT NULL DEFAULT 0 COMMENT '位置',
  `available` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `sort` int(0) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_cms_article
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_article`;
CREATE TABLE `t_cms_article`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content_id` int(0) UNSIGNED NOT NULL COMMENT '内容ID',
  `chapter_id` int(0) UNSIGNED NOT NULL COMMENT '章节ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `subtitle` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `cover` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主题图片',
  `bgcolor` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '背景颜色',
  `share_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分享标题',
  `sort` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '顺序',
  `creator` int(0) UNSIGNED NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_content_id_chapter_id`(`content_id`, `chapter_id`) USING BTREE,
  INDEX `idx_chapter_id`(`chapter_id`) USING BTREE,
  INDEX `idx_sort`(`sort`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '内容文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_cms_category
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_category`;
CREATE TABLE `t_cms_category`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int(0) UNSIGNED NULL DEFAULT 0 COMMENT '排序',
  `available` tinyint(1) NULL DEFAULT 1 COMMENT '可用状态',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_pid`(`pid`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '内容分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cms_category
-- ----------------------------
INSERT INTO `t_cms_category` VALUES (1, 0, 'IT·互联网', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (2, 0, '设计·创作', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (3, 0, '语言·留学', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (4, 0, '职业·考证', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (5, 0, '升学·考研', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (6, 0, '兴趣·生活', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (7, 0, '电商·营销', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (8, 7, '电商平台', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (9, 8, '淘宝营销', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (10, 8, '拼多多营销', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (11, 8, '京东营销', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (12, 7, '跨境电商', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (13, 12, '亚马逊', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (14, 12, '速卖通', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (15, 7, '社交电商', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (16, 7, '其他', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (17, 1, '互联网产品', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (18, 1, '编程语言', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (19, 1, '移动开发', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (20, 1, '前端开发', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (21, 1, '网络与运维', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (22, 1, '软件测试', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (23, 1, '云计算大数据', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (24, 1, '游戏开发', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (25, 1, '认证考试', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (26, 1, '硬件研发', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (27, 17, '产品策划', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (28, 17, '游戏策划', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (29, 17, '产品运营', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (30, 17, '游戏运营', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (31, 17, '网络营销理论', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (32, 17, 'SEO', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (33, 17, 'SEM', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (34, 17, '新媒体营销', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (35, 18, 'C', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (36, 18, 'C++', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (37, 18, 'Java进阶', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (38, 2, '平面设计', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (39, 3, '实用英语', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (40, 4, '公考求职', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (41, 40, '公务员', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (42, 5, '考研', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (43, 5, '高中', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (44, 6, '投资理财', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (45, 44, '证券投资', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (46, 6, '生活百科', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (47, 46, '婚恋交友', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (48, 46, '时尚美妆', '', 0, 1, '');
INSERT INTO `t_cms_category` VALUES (49, 19, 'uniapp', '', 0, 1, '');

-- ----------------------------
-- Table structure for t_cms_chapter
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_chapter`;
CREATE TABLE `t_cms_chapter`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` int(0) UNSIGNED NOT NULL COMMENT '内容ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` tinyint(0) NOT NULL COMMENT '状态',
  `sort` int(0) NOT NULL DEFAULT 1 COMMENT '顺序',
  `creator` int(0) UNSIGNED NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_content_id`(`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '章节表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cms_chapter
-- ----------------------------
INSERT INTO `t_cms_chapter` VALUES (1, 2, '第1节 先导课：极简家居芭蕾', NULL, 1, 0, 1, '2020-04-21 09:25:07');
INSERT INTO `t_cms_chapter` VALUES (2, 2, '第2节 Part1：纤体计划', '纤体计划', 1, 0, 1, '2020-04-21 09:25:55');
INSERT INTO `t_cms_chapter` VALUES (3, 2, '第3节 Part2：美腿养成', NULL, 1, 0, 1, '2020-04-21 09:26:25');
INSERT INTO `t_cms_chapter` VALUES (4, 2, '第4节 Part3：美体进阶', '美体进阶', 1, 0, 1, '2020-04-21 09:28:11');
INSERT INTO `t_cms_chapter` VALUES (5, 3, '第1节 4大类型3个思维, 让你明确学什么', '', 1, 0, 1, '2020-04-22 20:57:58');
INSERT INTO `t_cms_chapter` VALUES (6, 3, '第2节 场景变换法: 打破变化，迅速提取知识记忆', '', 1, 0, 1, '2020-04-22 20:58:15');
INSERT INTO `t_cms_chapter` VALUES (7, 3, '第3节 时间管理法: 顺应节律，让学习事半功倍', '', 1, 0, 1, '2020-04-22 20:58:30');
INSERT INTO `t_cms_chapter` VALUES (8, 3, '第4节 交替练习法: 摆脱似懂非懂， 让你胸有成竹', '', 1, 0, 1, '2020-04-22 20:58:44');
INSERT INTO `t_cms_chapter` VALUES (9, 3, '第5节 遗忘式法: 掌握学习频率，不再学了就忘', '', 1, 0, 1, '2020-04-22 20:58:54');
INSERT INTO `t_cms_chapter` VALUES (10, 3, '第6节 暂时停机法: 休息与警惕，教你应对分心和被打断', '', 1, 0, 1, '2020-04-22 20:59:08');
INSERT INTO `t_cms_chapter` VALUES (11, 3, '第7节 策略学习法: 跟上时代脚步，不被社会淘汰', '', 1, 0, 1, '2020-04-22 20:59:22');
INSERT INTO `t_cms_chapter` VALUES (12, 3, '第8节 3个职场燃料, 让学习助力职场发展', '', 1, 0, 1, '2020-04-22 20:59:30');
INSERT INTO `t_cms_chapter` VALUES (13, 5, 'test', '1', 1, 0, 1, '2020-05-09 15:31:53');

-- ----------------------------
-- Table structure for t_cms_course
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_course`;
CREATE TABLE `t_cms_course`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `subtitle` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '副标题',
  `type` tinyint(0) NOT NULL DEFAULT 1 COMMENT '1视频 2专栏',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态 1草稿 2提交审核 3审核通过 4 审核不通过 5 上架 6 下架',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `cover` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '广告图片',
  `category` int(0) NOT NULL COMMENT '类别',
  `free` tinyint(1) NULL DEFAULT 1 COMMENT '是否免费',
  `original_price` decimal(12, 2) NULL DEFAULT NULL COMMENT '原价',
  `discount_price` decimal(12, 2) NULL DEFAULT NULL COMMENT ' 折扣价',
  `total_hours` decimal(10, 2) NOT NULL COMMENT '总课时',
  `total_period` decimal(10, 2) NOT NULL COMMENT '总周期',
  `apply_num` int(0) NOT NULL DEFAULT 0 COMMENT '报名人数',
  `live_status` tinyint(0) NULL DEFAULT NULL COMMENT '直播状态 1是正在直播, 其他为没有直播状态',
  `creator` int(0) NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_title`(`title`) USING BTREE,
  INDEX `idx_type`(`type`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE,
  INDEX `idx_free`(`free`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '内容主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cms_course
-- ----------------------------
INSERT INTO `t_cms_course` VALUES (2, '在家就能练的极简芭蕾课，塑身姿/塑体态/美曲线/修气质', '在家就能练的极简芭蕾课，塑身姿/塑体态/美曲线/修气质', 1, 1, 0, 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/images/1599459782645.png?Expires=1914819860&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=HCTtXQXcBZHn7ihO%2BtNiuf7FjNU%3D', 27, 0, 120.00, NULL, 21.00, 7.00, 0, NULL, 1, '2020-04-15 21:11:24');
INSERT INTO `t_cms_course` VALUES (3, '韩焱：10倍高效学习力，教你如何摆脱低效学习困难', '本次课程,韩焱老师帮你重新梳理学习应该学什么, 理清学习的底层逻辑。更提供了高效又实用的 学习方法，帮你快速摆脱学习的各种困境。 让你有法可依，学业精进，职场得意, 把握好人生的每一个关键时刻。', 1, 1, 0, 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/images/1599994914264.png?Expires=1915354912&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=vQcNByO5UV73fwNMOIuELV%2FYnFY%3D', 29, 1, NULL, NULL, 45.00, 9.00, 0, NULL, 1, '2020-04-15 22:12:24');
INSERT INTO `t_cms_course` VALUES (4, '用营养呵护孕妈，高级营养师的孕期食谱', '怀孕对于一个家庭来说，是一件非常高兴的事情。想要让自己的孕期顺利度过，想要生一个健康的宝宝，科学备孕是孕育一个健康生命体的基础保障。那么，备孕期到底该如何饮食，另外还需要注意哪些问题呢？ 我将会为你提供备孕期间科学膳食的完整指南，从食物的多样化，动吃平衡，维生素补充，生活作息等方面为宝宝提供一个良好的生存环境。', 1, 1, 0, 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/images/1599994928696.png?Expires=1915354920&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=B5ggeMe6myxZ9%2BoVQ3jOspxKVlQ%3D', 20, 0, 120.00, NULL, 24.00, 5.00, 0, NULL, 1, '2020-04-15 22:17:00');
INSERT INTO `t_cms_course` VALUES (5, '2443', '4343', 1, 1, 0, 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/images/1599559227625.png?Expires=1914919218&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=KJmrp50l2VnMUjSNKQMgWa3wsOE%3D', 28, 1, NULL, NULL, 12.00, 12.00, 0, NULL, 1, '2020-05-05 22:26:33');
INSERT INTO `t_cms_course` VALUES (6, '从0打造音视频直播系统', '手把手教你打造实时互动音视频直播系统', 2, 1, 0, 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/images/1599458550801.png?Expires=1914818550&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=aH4699kO1DENn3JKeNaQyKGsWtk%3D', 37, 1, NULL, NULL, 21.00, 8.00, 0, NULL, 1, '2020-05-31 19:18:35');
INSERT INTO `t_cms_course` VALUES (7, 'Java高级教程', 'Java高级教程-为你', 1, 1, 0, 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/images/1599458584846.png?Expires=1914818577&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=dL5cyvFqdHwXmTKSJv3Cha8Eo0k%3D', 37, 1, NULL, NULL, 12.00, 12.00, 0, NULL, 1, '2020-06-08 12:03:05');
INSERT INTO `t_cms_course` VALUES (8, 'gsafsdfsad', 'sadfsdfsadf', 1, 1, 0, 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/images/1600923759103.png?Expires=1916283758&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=OeXh%2B0p%2B8gWQvnlU5zawUONu%2FPk%3D', 27, 1, NULL, NULL, 12.00, 12.00, 0, NULL, 1, '2020-09-24 13:03:28');
INSERT INTO `t_cms_course` VALUES (9, 'uniapp开发小蜜蜂课堂', 'uniapp开发小蜜蜂课堂', 1, 1, 0, '', 49, 1, NULL, NULL, 20.00, 4.00, 0, NULL, 1, '2020-10-16 12:41:54');

-- ----------------------------
-- Table structure for t_cms_course_intro
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_course_intro`;
CREATE TABLE `t_cms_course_intro`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` int(0) UNSIGNED NOT NULL COMMENT '内容ID',
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '描述内容',
  `creator` int(0) UNSIGNED NOT NULL COMMENT '创建着',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_content_id`(`course_id`) USING BTREE,
  INDEX `idx_content_id`(`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '内容介绍' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cms_course_intro
-- ----------------------------
INSERT INTO `t_cms_course_intro` VALUES (1, 2, '<p style=\"text-align: center;\"><img src=\"https://res.shiguangkey.com/file/202004/11/20200411171225273524514.jpg\" style=\"max-width:100%;\"><br></p>', 1, '2020-04-15 21:11:24');
INSERT INTO `t_cms_course_intro` VALUES (2, 3, '<p style=\"text-align: center;\"><img src=\"https://res.shiguangkey.com/file/201912/30/20191230174854191144443.jpg\" style=\"max-width:100%;\"><br></p>', 1, '2020-04-15 22:12:24');
INSERT INTO `t_cms_course_intro` VALUES (3, 4, '<p style=\"text-align: center;\"><img src=\"https://res.shiguangkey.com/file/202004/11/20200411163635058203329.jpg\" style=\"max-width:100%;\"><br></p>', 1, '2020-04-15 22:17:00');
INSERT INTO `t_cms_course_intro` VALUES (4, 5, '<p>434</p>', 1, '2020-05-05 22:26:33');
INSERT INTO `t_cms_course_intro` VALUES (5, 6, '<p><br></p><div><div><h2>你将获得</h2></div><div><div><ul><li>深入掌握WebRTC实时通讯技术；</li><li>实现1对1通话和多人音视频实时通话；</li><li>搭建支持万人同时在线的直播系统；</li><li>具备5G时代音视频技术能力。</li></ul></div></div><div></div></div><div><div><h2>讲师介绍</h2></div><div><div><p><strong>李超，前新东方音视频直播技术专家，前沪江音视频架构师</strong>。在视频直播/点播、在线教育、网络音视频会议行业深耕近十年，具有丰富的音视频研发经验。</p><p>李超对WebRTC、 FFmpeg等音视频库进行了长期的追踪与研究：</p><ul><li>深谙WebRTC 整体架构、音视频处理流程；</li><li>对常见的 3A 问题（回音消除、降噪、增益）具有非常丰富的实战经验；</li><li>对各种网络传输协议了然于胸，如RTP/RTCP、DTLS/SRTP/SRTCP、P2P等。</li></ul><p>基于 WebRTC 和 FFmpeg 库，李超已经开发了众多音视频产品，如在线直播系统、音视频会议系统、即时通讯系统、自研播放器等。</p><p>除了音视频，李超对Linux内核也做过深入研究，对大型系统构建如灾备、高负载、高并发系统有丰富经验，其研发的流媒体服务器上线后长年工作无事故。</p><p>另外，李超还具有7年多的团队管理经验，曾带领团队研发自主知识产权的音视频会议引擎，该系统于2011年正式上线为多家世界 500 强企业（如通用电气、海尔公司）提供服务。</p></div></div><div></div></div><div><div><h2>课程介绍</h2></div><div><div><p>现如今音视频技术已经非常成熟了，越来越被广泛地应用于各行各业，比如平时常见的抖音、微信短视频、娱乐直播、教育直播、音视频会议等，已经逐渐渗透到生活的各个角落。就连大热的AI技术也与音视频技术联系非常紧密，比如智能音箱、自动驾驶、人脸识别等都离不开音视频技术。</p><p>而千呼万唤的5G时代的到来，也会为音视频的发展插上飞翔的翅膀。有人总结说过：2G时代看文字，3G时代看图片，4G时代看视频，未来的5G时代可能看的就是更高效高清的直播和视频，这是网速提升的最直观体现，也是时代给的新机遇。</p><p>所以，可以预见在未来两三年内，音视频技术会是大势，也必定会像当年移动互联网一样出现井喷的人才需求，<strong>音视频人才会成为新的宠儿</strong>。面对这样的机遇，你若能掌握音视频技术的核心技术，一定可以在未来职场上获得丰厚的回报和满满的成就感。</p><p>虽说音视频技术比较纷繁和复杂，但本课程会<strong>从 0 开始讲解音视频的相关知识，以保证非专业人员也可以快速学习和上手</strong>；采取环环相扣、循序渐进、各个击破的办法来为你介绍各个知识点，最终让你掌握音视频的核心知识，并能够即学即用，依靠这些知识亲手做出你想要的音视频产品。</p><p>课程共38讲，分为3大模块。</p><p><strong>1. WebRTC 1对1通话</strong></p><p>主要讲解如何在浏览器间实现1对1通话，比如一个人在北京，另一个人在上海，他们打开浏览器进入同一个房间后，就可以进行音视频通话了。这一模块精编了环环相扣的 22 篇文章，每篇文章对应一个实现 WebRTC 1对1通话的主题。也就是说，这 22 篇文章是可以串联为一个即学即用的1对1实时通话的例子。</p><p><strong>2. WebRTC多人音视频实时通话</strong><br>主要探讨如何实现多人音视频实时互动。首先为你介绍几种多人音视频实时互动的架构，以及它们的优劣；然后，再重点讲解如何使用 SFU 架构实现多人音视频实时通话（SFU是现在最流行的多人实时互动架构）。学完本模块内容后，你就可以亲手实现多人音视频实时通话了。</p><p><strong>3. 支持上万人同时在线的直播系统</strong><br>重点介绍 CDN 原理、RTMP、HLS 协议，以及如何使用各种播放器从 CDN 拉取媒体流。其中，CDN是支持上万人同时在线直播系统的主要技术，而RTMP 和 HLS是其使用的底层传输协议。学完本模块内容后，你就会清楚地知道上万人同时在线直播的原理，并可以自己实现一套这样的直播系统。</p></div></div><div><h2>课程目录</h2></div><div><div><br></div></div></div><p><img src=\"https://static001.geekbang.org/resource/image/25/4e/25cdc65699c21185896f8b2f31809f4e.jpg\" style=\"max-width:100%;\"><br></p>', 1, '2020-05-31 19:18:35');
INSERT INTO `t_cms_course_intro` VALUES (6, 7, '<p>121212</p>', 1, '2020-06-08 12:03:05');
INSERT INTO `t_cms_course_intro` VALUES (8, 9, '', 1, '2020-10-16 12:41:54');

-- ----------------------------
-- Table structure for t_cms_course_recommend
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_course_recommend`;
CREATE TABLE `t_cms_course_recommend`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `platform` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '平台, h5 pc app',
  `recommend_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '推荐类型',
  `available` tinyint(1) NOT NULL DEFAULT 1 COMMENT '记录状态，true有效 false无效',
  `creator` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `modifier` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '修改人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_platform`(`platform`) USING BTREE,
  INDEX `idx_available`(`available`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '推荐表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_cms_course_recommend_item
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_course_recommend_item`;
CREATE TABLE `t_cms_course_recommend_item`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `recommend_id` int(0) UNSIGNED NOT NULL COMMENT '推荐类型',
  `start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `available` tinyint(1) NOT NULL DEFAULT 1 COMMENT '记录状态，true有效 false无效',
  `creator` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `modifier` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '修改人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_recommend_id`(`recommend_id`) USING BTREE,
  INDEX `idx_start_time`(`start_time`) USING BTREE,
  INDEX `idx_end_time`(`end_time`) USING BTREE,
  INDEX `idx_available`(`available`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '推荐内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_cms_file
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_file`;
CREATE TABLE `t_cms_file`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键ID',
  `md5` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'md值',
  `sha1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'sha1值',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件名',
  `file_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'oss上文件信息',
  `suffix` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '后缀',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径',
  `content_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容类型',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件类型(1:附件;2;图片;3:视频)',
  `channel` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存放渠道',
  `size` bigint(0) NOT NULL COMMENT '文件大小',
  `available` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `vod_length` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '音视频时长',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '上传人员',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` bigint(0) NULL DEFAULT NULL COMMENT '修改人员',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'oss上文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_cms_file_info
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_file_info`;
CREATE TABLE `t_cms_file_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_no` bigint(0) NOT NULL COMMENT '文件编号',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件地址',
  `biz_id` int(0) UNSIGNED NOT NULL COMMENT '业务id',
  `biz_type` int(0) NOT NULL COMMENT '业务类型',
  `suffix` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '后缀',
  `type` tinyint(0) NOT NULL COMMENT '文件类型(1:附件;2;图片;3:视频)',
  `size` bigint(0) NOT NULL COMMENT '文件大小',
  `availabe` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件存储表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_cms_image
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_image`;
CREATE TABLE `t_cms_image`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `md5` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'md5码',
  `file_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件地址',
  `content_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件类型',
  `extension` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '扩展名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cms_image
-- ----------------------------
INSERT INTO `t_cms_image` VALUES (1, '8775b4cb118d20f1d4fe73f674eee192', '微信图片_20200824161150.jpg', 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/images/1601689877570.jpg?Expires=1917049887&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=kgLG8mOpYOHRk7eRGjDXLFHZl8g%3D', 'image/jpeg', 'jpg');
INSERT INTO `t_cms_image` VALUES (2, '8775b4cb118d20f1d4fe73f674eee192', '微信图片_20200824161150.jpg', 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/images/1601691591682.jpg?Expires=1917051608&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=VMzbDPdoDM8aV4RFYftzZ3UGfSo%3D', 'image/jpeg', 'jpg');
INSERT INTO `t_cms_image` VALUES (3, 'a4181fefd63ce67489763c085b0e6100', '微信图片_20200824161155.jpg', 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/images/1601691610262.jpg?Expires=1917051608&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=dR%2F478WKLNjTLZIrZtels0prFGo%3D', 'image/jpeg', 'jpg');

-- ----------------------------
-- Table structure for t_cms_media
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_media`;
CREATE TABLE `t_cms_media`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `media_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件UUID',
  `extension` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件扩展名',
  `prefix_dir` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件中间路径',
  `module` int(0) NULL DEFAULT NULL COMMENT '模块',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型 1 视频  2 音频',
  `transcoded` tinyint(1) NULL DEFAULT NULL COMMENT '已转码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '视频信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cms_media
-- ----------------------------
INSERT INTO `t_cms_media` VALUES (17, 'test.mp4', '2c921f67-e20d-44d0-bbda-f0344f809b27', 'mp4', '2020/07/28/', 1, 1, 1);

-- ----------------------------
-- Table structure for t_cms_period
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_period`;
CREATE TABLE `t_cms_period`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` int(0) UNSIGNED NOT NULL COMMENT '内容ID',
  `chapter_id` int(0) UNSIGNED NOT NULL COMMENT '章节ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `subtitle` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `cover` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主题图片',
  `bgcolor` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '背景颜色',
  `share_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分享标题',
  `media_id` int(0) NULL DEFAULT NULL COMMENT '媒体ID',
  `article_id` int(0) NULL DEFAULT NULL COMMENT '文章ID',
  `sort` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '顺序',
  `creator` int(0) UNSIGNED NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_content_id_chapter_id`(`course_id`, `chapter_id`) USING BTREE,
  INDEX `idx_chapter_id`(`chapter_id`) USING BTREE,
  INDEX `idx_sort`(`sort`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '内容文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cms_period
-- ----------------------------
INSERT INTO `t_cms_period` VALUES (1, 2, 1, '1.1 先导课-高贵优雅的秘密，其实你也可以', '1.1 先导课-高贵优雅的秘密，其实你也可以', 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/images/1320967155252072448.jpg?Expires=3180578087&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=d%2B%2FJhP0Q9rGz56NTUWaEXRSRQqs%3D', NULL, '1.1 先导课-高贵优雅的秘密，其实你也可以', 17, NULL, 1, 1, '2020-04-21 09:49:45');
INSERT INTO `t_cms_period` VALUES (3, 2, 2, '2.1 芭蕾手位，纤细的手臂与优雅的手指', '2.1 芭蕾手位，纤细的手臂与优雅的手指', 'http://arcus-file.oss-cn-shanghai.aliyuncs.com/images/1593661998757.jpg?Expires=1909021989&OSSAccessKeyId=LTAI4G4Nnz5wpDbK5EvWn3MN&Signature=SVk3xEIkmUcqrKE8texwTixqaNw%3D', '#841F1F', '2.1 芭蕾手位，纤细的手臂与优雅的手指', 17, NULL, 0, 1, '2020-04-21 23:04:18');
INSERT INTO `t_cms_period` VALUES (5, 2, 4, '4.1 旋转跳跃，请享受芭蕾给你轻盈的身体', '旋转跳跃，请享受芭蕾给你轻盈的身体', 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/images/1599559278873.png?Expires=1914919276&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=o2cQZS2UaDDmZloVz4lmz8XDF4g%3D', '', '旋转跳跃，请享受芭蕾给你轻盈的身体', 17, NULL, 0, 1, '2020-04-22 20:38:01');
INSERT INTO `t_cms_period` VALUES (6, 3, 5, '1.1 先导课-高贵优雅的秘密，其实你也可以', '先导课-高贵优雅的秘密，其实你也可以', '', '', '先导课-高贵优雅的秘密，其实你也可以', 17, NULL, 0, 1, '2020-04-22 21:01:46');
INSERT INTO `t_cms_period` VALUES (7, 3, 6, '2.1 芭蕾手位，纤细的手臂与优雅的手指', '芭蕾手位，纤细的手臂与优雅的手指', 'http://arcus-file.oss-cn-shanghai.aliyuncs.com/images/1598106037905.png?Expires=1913466032&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=jGw4XICTAQHKdbpgDMkSclbRAQU%3D', '#7AAD16', '芭蕾手位，纤细的手臂与优雅的手指', 17, NULL, 0, 1, '2020-04-22 21:04:07');
INSERT INTO `t_cms_period` VALUES (8, 2, 3, '3.1 蹲与立，一套动作让你的小腿纤细修长', '蹲与立，一套动作让你的小腿纤细修长', '', '#A71818', '蹲与立，一套动作让你的小腿纤细修长', 17, NULL, 0, 1, '2020-04-25 11:15:34');
INSERT INTO `t_cms_period` VALUES (17, 2, 1, '1.2 asdfasfsafas', '测试', '', '', '', NULL, NULL, 0, 1, '2020-06-11 12:46:03');

-- ----------------------------
-- Table structure for t_cms_period_vod
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_period_vod`;
CREATE TABLE `t_cms_period_vod`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `course_id` bigint(0) NOT NULL COMMENT '课程ID',
  `chapter_id` bigint(0) NULL DEFAULT NULL COMMENT '章节ID',
  `period_id` bigint(0) NULL DEFAULT NULL COMMENT '课时ID',
  `vod_id` bigint(0) NULL DEFAULT NULL COMMENT '音视频ID',
  `available` tinyint(1) NOT NULL COMMENT '有效1 无效0',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '上传人员',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` bigint(0) NULL DEFAULT NULL COMMENT '修改人员',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cms_period_vod
-- ----------------------------
INSERT INTO `t_cms_period_vod` VALUES (1, '测试视频', 2, 1, 1, 2, 1, 1, '2020-10-26 16:17:00', 1, '2020-10-27 13:57:13');
INSERT INTO `t_cms_period_vod` VALUES (2, '测试', 2, 1, 1, 3, 1, 1, '2020-10-27 13:57:13', 1, '2020-10-27 13:57:13');
INSERT INTO `t_cms_period_vod` VALUES (3, '测试视频', 3, 5, 6, 2, 1, 1, '2020-10-29 13:12:05', 1, '2020-10-29 13:12:05');
INSERT INTO `t_cms_period_vod` VALUES (4, '测试影片2', 3, 5, 6, 4, 1, 1, '2020-10-29 13:12:05', 1, '2020-10-29 13:12:05');
INSERT INTO `t_cms_period_vod` VALUES (5, '测试视频', 3, 6, 7, 2, 1, 1, '2020-10-29 13:12:13', 1, '2020-10-29 13:12:13');

-- ----------------------------
-- Table structure for t_cms_video
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_video`;
CREATE TABLE `t_cms_video`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `file_id` bigint(0) NOT NULL COMMENT '文件ID',
  `requestId` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传请求ID',
  `vod` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '音视频ID',
  `upload_address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传地址ID MediaUrl',
  `upload_auth` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '音视频ID',
  `cover_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签',
  `channel` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '渠道 aliyun tencent',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '上传人员',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` bigint(0) NULL DEFAULT NULL COMMENT '修改人员',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '视频点播' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_cms_video_1
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_video_1`;
CREATE TABLE `t_cms_video_1`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `available` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `biz_type` int(0) NOT NULL COMMENT '业务类型 1 课程',
  `biz_id` bigint(0) NULL DEFAULT NULL COMMENT '内容ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频名称',
  `video_no` bigint(0) NULL DEFAULT NULL COMMENT '视频编号',
  `status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '视频状态(1待上传，2上传成功，3上传失败)',
  `video_length` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时长',
  `file_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频ID',
  `media_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '播放地址',
  `cover_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面',
  `oss_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ossId',
  `sort` int(0) NOT NULL DEFAULT 1 COMMENT '排序',
  `md5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '视频信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_cms_vod
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_vod`;
CREATE TABLE `t_cms_vod`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `requestId` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传请求ID',
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '介绍',
  `vod_type` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `vid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '音视频ID',
  `upload_address` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传地址ID MediaUrl',
  `upload_auth` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '音视频ID',
  `cover_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签',
  `channel` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '渠道 aliyun tencent',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '上传人员',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` bigint(0) NULL DEFAULT NULL COMMENT '修改人员',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '视频点播' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cms_vod
-- ----------------------------
INSERT INTO `t_cms_vod` VALUES (1, NULL, 'test.mp4', '测试vod上传1', '测试vod上传1', 'video', '01c61146069f4db5b8fb91753bb70a91', NULL, NULL, NULL, NULL, 'aliyun', NULL, '2020-10-16 13:50:22', NULL, '2020-10-16 13:50:22');
INSERT INTO `t_cms_vod` VALUES (2, 'EE102B44-813D-4310-8409-FBA43022E164', 'test.mp4', '打饭打水', '阿斯蒂芬撒旦', 'video', '9ca1207d13a34a0ba09d418d1a4ef41f', NULL, NULL, NULL, NULL, 'aliyun', 0, '2020-10-16 14:10:16', 0, '2020-10-16 14:10:16');

-- ----------------------------
-- Table structure for t_cms_vod_1
-- ----------------------------
DROP TABLE IF EXISTS `t_cms_vod_1`;
CREATE TABLE `t_cms_vod_1`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `media_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件UUID或者VID',
  `source_type` tinyint(1) NULL DEFAULT NULL COMMENT '1,本地  2 阿里云 3 腾讯云',
  `extension` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件扩展名',
  `prefix_dir` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件中间路径',
  `module` int(0) NULL DEFAULT NULL COMMENT '模块',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型 1 视频  2 音频',
  `transcoded` tinyint(1) NULL DEFAULT NULL COMMENT '已转码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '视频信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cms_vod_1
-- ----------------------------
INSERT INTO `t_cms_vod_1` VALUES (7, 'test.mp4', 'fa95eb62-6790-41f7-8d2d-c63d2548ba17', NULL, 'mp4', '2020/06/07/', 1, 1, 1);
INSERT INTO `t_cms_vod_1` VALUES (8, 'test.mp4', 'd549e2c1-b9e7-4f8e-abd1-01d37d6dd5ad', NULL, 'mp4', '2020/06/07/', 1, 1, 0);
INSERT INTO `t_cms_vod_1` VALUES (9, 'test.mp4', 'fdc63050-9e82-43b8-979c-0788bdcdd323', NULL, 'mp4', '2020/06/08/', 1, 1, 0);
INSERT INTO `t_cms_vod_1` VALUES (10, 'test.mp4', 'e43155a6-9fbb-4c54-8ca2-b31d87011574', NULL, 'mp4', '2020/06/08/', 1, 1, 0);
INSERT INTO `t_cms_vod_1` VALUES (11, 'test.mp4', 'df39b766-97e1-4c94-a5bd-66df6f0cc755', NULL, 'mp4', '2020/07/08/', 1, 1, 0);

-- ----------------------------
-- Table structure for t_dd
-- ----------------------------
DROP TABLE IF EXISTS `t_dd`;
CREATE TABLE `t_dd`  (
  `dd` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dd
-- ----------------------------
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:42:47');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:43');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:43');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:43');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:44');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:45');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');
INSERT INTO `t_dd` VALUES ('2020-08-07 19:44:46');

-- ----------------------------
-- Table structure for t_material_file
-- ----------------------------
DROP TABLE IF EXISTS `t_material_file`;
CREATE TABLE `t_material_file`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `group_id` bigint(0) NULL DEFAULT NULL COMMENT '文件分组ID',
  `oss_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '对象存储ID',
  `file_uid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '自动生成唯一文件名',
  `channel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存储渠道(ALIYUN,TENCENT,QINIU)',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型  image | file',
  `extension` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '扩展名',
  `content_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容类型',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问地址',
  `size` bigint(0) NULL DEFAULT NULL COMMENT '文件大小',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '上传人员',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` bigint(0) NULL DEFAULT NULL COMMENT '修改人员',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_material_file
-- ----------------------------
INSERT INTO `t_material_file` VALUES (1, '图怪兽_6f4f68aa00dee2089f4f37dfadbc7fc2_80279.png', NULL, '254A9CA85D0E6B2B7F312398E02BADD4', '1603436480042png', 'aliyun', 'file', 'png', 'image/png', 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/file/1603436480042png?Expires=3180236476&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=vcSs%2Bc1Zppomtk2BMAnRzIOuaTM%3D', NULL, 1, '2020-10-23 15:00:59', 1, '2020-10-23 15:00:59');
INSERT INTO `t_material_file` VALUES (2, '图怪兽_6f4f68aa00dee2089f4f37dfadbc7fc2_80279.png', NULL, '254A9CA85D0E6B2B7F312398E02BADD4', '1603437147522png', 'aliyun', 'file', 'png', 'image/png', 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/file/1603437147522png?Expires=3180237161&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=F%2FsfhNNAD5hVt9t%2FZ9cBqkkbyUM%3D', NULL, 1, '2020-10-23 15:12:02', 1, '2020-10-23 15:12:02');
INSERT INTO `t_material_file` VALUES (3, '图怪兽_6f4f68aa00dee2089f4f37dfadbc7fc2_80279.png', NULL, '254A9CA85D0E6B2B7F312398E02BADD4', '1603437219693.png', 'aliyun', 'file', 'png', 'image/png', 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/file/1603437219693.png?Expires=3180237212&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=eTQs3TXsx2ZzDg8aNW7swgZznLM%3D', NULL, 1, '2020-10-23 15:13:27', 1, '2020-10-23 15:13:27');
INSERT INTO `t_material_file` VALUES (4, '图怪兽_6f4f68aa00dee2089f4f37dfadbc7fc2_80279.png', NULL, '254A9CA85D0E6B2B7F312398E02BADD4', '1319539859458756608.png', 'aliyun', 'file', 'png', 'image/png', 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/file/1319539859458756608.png?Expires=3180237793&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=VdztPk51s8bj%2BbukrXoaxU0uTR4%3D', 162996, 1, '2020-10-23 15:23:13', 1, '2020-10-23 15:23:13');
INSERT INTO `t_material_file` VALUES (5, 'Free-Converter.com-favicon-94851919.png', NULL, '86EF5993D5A4EEF1B0FE136B1AA64FC8', '1320532496466710528.png', 'aliyun', 'file', 'png', 'image/png', 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/file/1320532496466710528.png?Expires=3180474456&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=0rbEHON1tlqNN7NJbUN8WI7GGbU%3D', 2686, 1, '2020-10-26 09:07:36', 1, '2020-10-26 09:07:36');
INSERT INTO `t_material_file` VALUES (6, '图怪兽_6f4f68aa00dee2089f4f37dfadbc7fc2_80279.png', NULL, '254A9CA85D0E6B2B7F312398E02BADD4', '1320534717409398784.png', 'aliyun', 'image', 'png', 'image/png', 'http://tinybee-ke.oss-cn-shanghai.aliyuncs.com/image/1320534717409398784.png?Expires=3180474985&OSSAccessKeyId=LTAI4GKYyaJ6R1xoEr9UkSMJ&Signature=GFJiIGC2iO4kUFSUiEd%2BSA%2BdH%2Fo%3D', 162996, 1, '2020-10-26 09:16:26', 1, '2020-10-26 09:16:26');

-- ----------------------------
-- Table structure for t_material_file_group
-- ----------------------------
DROP TABLE IF EXISTS `t_material_file_group`;
CREATE TABLE `t_material_file_group`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型 file(文件) image(图片)',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '上传人员',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` bigint(0) NULL DEFAULT NULL COMMENT '修改人员',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_material_vod
-- ----------------------------
DROP TABLE IF EXISTS `t_material_vod`;
CREATE TABLE `t_material_vod`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `classify_id` int(0) NULL DEFAULT NULL COMMENT '分类',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `vid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '渠道视频ID',
  `cover_id` int(0) NULL DEFAULT NULL COMMENT '封面ID',
  `channel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存储渠道(ALIYUN,TENCENT,QINIU)',
  `type` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'audio video',
  `extension` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '扩展名',
  `content_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容类型',
  `video_length` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '音视频长度',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '上传人员',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` bigint(0) NULL DEFAULT NULL COMMENT '修改人员',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_material_vod
-- ----------------------------
INSERT INTO `t_material_vod` VALUES (2, '测试视频', NULL, '2020-10-26_10-51-00.mkv', 'c0b5bafcd2754b3da944df083cdc6625', NULL, 'aliyun', 'video', 'mkv', 'video/x-matroska', NULL, 1, '2020-10-26 10:59:00', 1, '2020-10-26 17:26:16');
INSERT INTO `t_material_vod` VALUES (3, '测试', NULL, 'Ferank丶 - наби Музыка-小丑魔性笑声（наби Музыка remix）.mp3', 'f79a8bc38fff40509103542dfcce709d', NULL, 'aliyun', 'audio', 'mp3', 'audio/mpeg', NULL, 1, '2020-10-26 16:58:27', 1, '2020-10-26 17:23:10');
INSERT INTO `t_material_vod` VALUES (4, '测试影片2', NULL, 'Ferank丶 - наби Музыка-小丑魔性笑声（наби Музыка remix）.mp3', '2f86a3643c1e4193b14bab6754b7e808', NULL, 'aliyun', 'audio', 'mp3', 'audio/mpeg', NULL, 1, '2020-10-26 17:01:15', 1, '2020-10-26 17:24:29');
INSERT INTO `t_material_vod` VALUES (5, '测试音频1', NULL, 'Ferank丶 - наби Музыка-小丑魔性笑声（наби Музыка remix）.mp3', '8230f865cb964cbba68b6e478b01f543', NULL, 'aliyun', 'audio', 'mp3', 'audio/mpeg', NULL, 1, '2020-10-26 17:04:08', 1, '2020-10-26 17:04:24');
INSERT INTO `t_material_vod` VALUES (6, '12121212', NULL, 'Ferank丶 - наби Музыка-小丑魔性笑声（наби Музыка remix）.mp3', 'ab697c5b842345648c124e490d10ee19', NULL, 'aliyun', 'audio', 'mp3', 'audio/mpeg', NULL, 1, '2020-10-26 17:07:17', 1, '2020-10-26 17:07:24');

-- ----------------------------
-- Table structure for t_material_vod_classify
-- ----------------------------
DROP TABLE IF EXISTS `t_material_vod_classify`;
CREATE TABLE `t_material_vod_classify`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `pid` bigint(0) NOT NULL COMMENT '上级ID 0最高级',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `cover_id` bigint(0) NULL DEFAULT NULL COMMENT '封面ID',
  `type` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型 video  audio',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '上传人员',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` bigint(0) NULL DEFAULT NULL COMMENT '修改人员',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_material_vod_classify
-- ----------------------------
INSERT INTO `t_material_vod_classify` VALUES (1, 0, '财经', NULL, 'audio', NULL, '2020-10-26 12:46:05', NULL, '2020-10-26 12:46:05');
INSERT INTO `t_material_vod_classify` VALUES (2, 0, '动漫', NULL, 'audio', NULL, '2020-10-26 12:47:35', NULL, '2020-10-26 12:47:35');
INSERT INTO `t_material_vod_classify` VALUES (3, 0, '体育', NULL, 'video', NULL, '2020-10-26 13:19:54', NULL, '2020-10-26 13:19:54');
INSERT INTO `t_material_vod_classify` VALUES (4, 3, 'CBA', NULL, 'video', NULL, '2020-10-26 13:20:07', NULL, '2020-10-26 13:20:07');
INSERT INTO `t_material_vod_classify` VALUES (5, 3, 'NBA', NULL, 'video', 1, '2020-10-26 14:21:45', 1, '2020-10-26 14:21:55');
INSERT INTO `t_material_vod_classify` VALUES (6, 0, '搞笑', NULL, 'audio', 1, '2020-10-26 14:23:58', 1, '2020-10-26 14:23:58');
INSERT INTO `t_material_vod_classify` VALUES (7, 0, '生活', NULL, 'video', 1, '2020-10-26 14:26:30', 1, '2020-10-26 14:26:30');
INSERT INTO `t_material_vod_classify` VALUES (8, 7, '生活百科', NULL, 'video', 1, '2020-10-26 14:26:42', 1, '2020-10-26 14:26:42');

-- ----------------------------
-- Table structure for t_oss_file
-- ----------------------------
DROP TABLE IF EXISTS `t_oss_file`;
CREATE TABLE `t_oss_file`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `md5` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'md值',
  `sha1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'sha1值',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件名',
  `res_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'oss上文件信息',
  `suffix` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '后缀',
  `type` tinyint(0) NOT NULL COMMENT '文件类型(1:附件;2;图片;3:视频)',
  `size` bigint(0) NOT NULL COMMENT '文件大小',
  `availabe` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `vod_length` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '音视频时长',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '上传人员',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` bigint(0) NULL DEFAULT NULL COMMENT '修改人员',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'oss上文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_platform_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_dept`;
CREATE TABLE `t_platform_dept`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dept_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '代码',
  `pid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级ID',
  `leader_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '负责人',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构名称',
  `creator` int(0) UNSIGNED NULL DEFAULT 0 COMMENT '创建着',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `record_status` tinyint(0) NULL DEFAULT NULL COMMENT '记录状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_org_code`(`dept_code`) USING BTREE,
  INDEX `idx_pid`(`pid`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '平台组织表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_platform_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_emp`;
CREATE TABLE `t_platform_emp`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别M F U',
  `married` tinyint(0) NOT NULL COMMENT '婚否',
  `education` tinyint(0) NOT NULL COMMENT '学历：1大专,2本科,3研究生,4博士,5其他',
  `mobile` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机',
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '住址',
  `post_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '职务ID',
  `org_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '机构ID',
  `dept_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '部门ID',
  `hiredate` date NOT NULL COMMENT '入职日期',
  `termdate` date NULL DEFAULT NULL COMMENT '离职日期',
  `status` tinyint(0) UNSIGNED NOT NULL COMMENT '状态：1在职,2休假,3离职,4死亡',
  `creator` bigint(0) UNSIGNED NULL DEFAULT 0 COMMENT '创建着',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` bigint(0) UNSIGNED NULL DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `record_status` tinyint(0) NULL DEFAULT NULL COMMENT '记录状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_job_no`(`job_no`) USING BTREE,
  INDEX `idx_post_id`(`post_id`) USING BTREE,
  INDEX `idx_dept_id`(`dept_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE,
  INDEX `idx_org_id`(`org_id`) USING BTREE,
  INDEX `idx_job_no`(`job_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '员工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_platform_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_platform_organization`;
CREATE TABLE `t_platform_organization`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `org_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '代码',
  `pid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构名称',
  `leader_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '负责人',
  `creator` int(0) UNSIGNED NULL DEFAULT 0 COMMENT '创建着',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `record_status` tinyint(0) NULL DEFAULT NULL COMMENT '记录状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_org_code`(`org_code`) USING BTREE,
  INDEX `idx_pid`(`pid`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '平台机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sem_order
-- ----------------------------
DROP TABLE IF EXISTS `t_sem_order`;
CREATE TABLE `t_sem_order`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_system_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_system_dept`;
CREATE TABLE `t_system_dept`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `short_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简称',
  `sort` int(0) NOT NULL DEFAULT 1 COMMENT '顺序',
  `deleted` tinyint(0) UNSIGNED NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_dept
-- ----------------------------
INSERT INTO `t_system_dept` VALUES (2, 0, 'Arcus集团', 'Arcus', 0, 0);
INSERT INTO `t_system_dept` VALUES (3, 2, '董事会', '董事会', 0, 0);
INSERT INTO `t_system_dept` VALUES (4, 2, '事业部', '事业部', 0, 0);
INSERT INTO `t_system_dept` VALUES (5, 4, '保险行业事业部', '保险', 0, 0);
INSERT INTO `t_system_dept` VALUES (6, 4, '银行事业部', '银行事业部', 0, 0);
INSERT INTO `t_system_dept` VALUES (7, 4, 'AI实验室', 'AI', 0, 0);
INSERT INTO `t_system_dept` VALUES (8, 4, '大数据实验室', '大数据', 0, 0);

-- ----------------------------
-- Table structure for t_system_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_system_emp`;
CREATE TABLE `t_system_emp`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别M F U',
  `married` tinyint(0) NOT NULL COMMENT '婚否',
  `education` tinyint(0) NOT NULL COMMENT '学历：1大专,2本科,3研究生,4博士,5其他',
  `mobile` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机',
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '住址',
  `post_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '职务ID',
  `dept_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '部门ID',
  `mgr_id` int(0) UNSIGNED NULL DEFAULT NULL COMMENT '上司ID',
  `hiredate` date NOT NULL COMMENT '入职日期',
  `termdate` date NULL DEFAULT NULL COMMENT '离职日期',
  `status` tinyint(0) UNSIGNED NOT NULL COMMENT '状态：1在职,2休假,3离职,4死亡',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `creator` int(0) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_job_no`(`job_no`) USING BTREE,
  INDEX `idx_post_id`(`post_id`) USING BTREE,
  INDEX `idx_dept_id`(`dept_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE,
  INDEX `idx_mgr_id`(`mgr_id`) USING BTREE,
  INDEX `idx_job_no`(`job_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '员工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_emp
-- ----------------------------
INSERT INTO `t_system_emp` VALUES (3, '200000', '黄浩', 'U', 0, 5, '18321429296', '1570356753@qq.com', '宇宙银河系太阳系地球亚洲神州大地中国', 1, 2, NULL, '2020-03-30', NULL, 1, '宇宙银河系太阳系地球亚洲神州大地中国威武', 0, 1, '2020-03-30 21:29:58');

-- ----------------------------
-- Table structure for t_system_post
-- ----------------------------
DROP TABLE IF EXISTS `t_system_post`;
CREATE TABLE `t_system_post`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位代码',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `available` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态',
  `creator` int(0) UNSIGNED NOT NULL COMMENT '创建人',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE,
  INDEX `idx_code`(`code`) USING BTREE,
  INDEX `unq_name`(`name`) USING BTREE,
  INDEX `unq_code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_post
-- ----------------------------
INSERT INTO `t_system_post` VALUES (1, '董事长', 'ceo', 1, '董事长', 1, 1, 0, '2020-03-30 09:33:27');
INSERT INTO `t_system_post` VALUES (3, '讲师', 'lecturer', 0, '讲师', 1, 1, 0, '2020-07-08 10:06:35');
INSERT INTO `t_system_post` VALUES (4, '运营', 'operation', 0, '', 1, 1, 0, '2020-08-17 18:02:45');
INSERT INTO `t_system_post` VALUES (5, '运营', 'operation', 0, '运营', 1, 1, 0, '2020-08-17 18:02:45');
INSERT INTO `t_system_post` VALUES (6, '运营', 'operation', 0, '运营', 1, 1, 0, '2020-08-17 18:02:45');

-- ----------------------------
-- Table structure for t_system_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_system_resource`;
CREATE TABLE `t_system_resource`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `permission_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限代码',
  `type` tinyint(0) UNSIGNED NOT NULL COMMENT '资源类型 1菜单 2 c操作 3页面元素',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源路径',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源ICON',
  `sort` int(0) UNSIGNED NULL DEFAULT 0 COMMENT '资源顺序',
  `available` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_pid`(`pid`) USING BTREE,
  INDEX `idx_type`(`type`) USING BTREE,
  INDEX `idx_available`(`available`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_resource
-- ----------------------------
INSERT INTO `t_system_resource` VALUES (1, 0, 'Dashboard', 'Dashboard', 1, '/dashboard', 'el-icon-house', 0, 1);
INSERT INTO `t_system_resource` VALUES (2, 0, '系统管理', 'system-manage', 1, '/system-manage', 'el-icon-setting', 1, 1);
INSERT INTO `t_system_resource` VALUES (3, 2, '权限管理', 'permission-manage', 1, '/system-manage/permission-manage', 'el-icon-warning', 0, 1);
INSERT INTO `t_system_resource` VALUES (4, 3, '系统用户管理', 'system-user-manage', 1, '/system-manage/permission-manage/user-manage', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (5, 3, '角色管理', 'role-manage', 1, '/system-manage/permission-manage/role-manage', NULL, 1, 1);
INSERT INTO `t_system_resource` VALUES (6, 3, '资源管理', 'resource-manage', 1, '/system-manage/permission-manage/resource-manage', NULL, 2, 1);
INSERT INTO `t_system_resource` VALUES (7, 2, '系统监控', 'system-monitor', 1, '/system-manage/system-monitor', 'el-icon-video-camera', 1, 1);
INSERT INTO `t_system_resource` VALUES (8, 7, '服务监控', 'service-monitor', 1, '/system-manage/system-monitor/service-monitor', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (9, 7, 'Druid监控', 'druid-monitor', 1, '/system-manage/system-monitor/druid-monitor', NULL, 1, 1);
INSERT INTO `t_system_resource` VALUES (10, 2, '系统设置', 'system-setting', 1, '/system-manage/system-manage', 'el-icon-s-help', 2, 1);
INSERT INTO `t_system_resource` VALUES (11, 10, '字典管理', 'dictionary-manage', 1, '/system-manage/system-manage/dictionary-manage', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (12, 3, '部门管理', 'dept-manage', 1, '/system-manage/permission-manage/dept-manage', NULL, 3, 1);
INSERT INTO `t_system_resource` VALUES (13, 3, '岗位管理', 'post-manage', 1, '/system-manage/permission-manage/post-manage', NULL, 4, 1);
INSERT INTO `t_system_resource` VALUES (14, 3, '员工管理', 'emp-manage', 1, '/system-manage/permission-manage/emp-manage', NULL, 5, 1);
INSERT INTO `t_system_resource` VALUES (15, 0, '课程中心', 'content-center', 1, '/content-center', 'el-icon-school', 2, 1);
INSERT INTO `t_system_resource` VALUES (16, 15, '分类管理', 'course-category', 1, '/course-center/course-category', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (17, 15, '课程管理', 'course-manage', 1, '/course-center/course-manage', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (18, 0, '营销中心', 'sem-center', 1, '/sem-center', 'el-icon-shopping-cart-full', 3, 1);
INSERT INTO `t_system_resource` VALUES (19, 18, '订单中心', 'order-center', 1, '/sem-center/order-center', 'el-icon-s-order', 0, 1);
INSERT INTO `t_system_resource` VALUES (20, 19, '订单管理', 'order-manage', 1, '/sem-center/order-center/order-manage', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (21, 19, '退款管理', 'refund-manage', 1, '/sem-center/order-center/refund-manage', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (22, 19, '订单设置', 'order-setting', 1, '/sem-center/order-center/order-setting', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (23, 18, '促销中心', 'promotion-center', 1, '/sem-center/promotion-center', 'el-icon-s-promotion', 0, 1);
INSERT INTO `t_system_resource` VALUES (24, 23, '推荐营销', 'recommend-sem', 1, '/sem-center/promotion-center/recommend-sem', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (25, 23, '秒杀', 'seckill', 1, '/sem-center/promotion-center/seckill', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (26, 23, '积分', 'integral-sem', 1, '/sem-center/promotion-center/integral-sem', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (27, 23, '拼团', 'group-booking', 1, '/sem-center/promotion-center/group-booking', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (32, 18, '微营销', 'micro-sem', 1, '/sem-center/micro-sem', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (33, 32, '推广', 'micro-promote', 1, '/sem-center/micro-sem/micro-promote', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (34, 23, '优惠券', 'coupon', 1, '/sem-center/promotion-center/coupon', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (35, 23, '专题营销', 'topic-sem', 1, '/sem-center/promotion-center/topic-sem', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (38, 32, '卡券', 'micro-card', 1, '/sem-center/micro-sem/micro-card', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (39, 0, '用户中心', 'user-center', 1, '/user-center', 'el-icon-user', 5, 1);
INSERT INTO `t_system_resource` VALUES (40, 39, '用户管理', 'user-manage', 1, '/user-center/user-manage', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (41, 15, '编辑内容', 'edit-course', 1, '/course-center/edit-course', NULL, 0, 0);
INSERT INTO `t_system_resource` VALUES (42, 4, '查询', 'system-user-manage:query', 2, '', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (43, 4, '删除', 'system-user-manage:delete', 2, '', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (44, 4, '授权', 'system-user-manage:authorize', 2, '', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (45, 5, '查询', 'role-manage:query', 2, '', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (46, 5, '新增', 'role-manage:add', 2, '', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (47, 5, '编辑', 'role-manage:edit', 2, '', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (48, 5, '删除', 'role-manage:delete', 2, '', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (49, 5, '授权', 'role-manage:authorize', 2, '', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (50, 6, '查询', '', 2, '', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (51, 6, '新增', '', 2, '', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (52, 6, '修改', '', 2, '', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (53, 6, '删除', '', 2, '', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (55, 39, '讲师管理', 'lecturer-manage', 1, '/user-center/lecturer-manage', NULL, 0, 1);
INSERT INTO `t_system_resource` VALUES (57, 0, '报表中心', '', 1, '', 'el-icon-s-data', 0, 1);
INSERT INTO `t_system_resource` VALUES (60, 2, '平台管理', '', 1, '', 'el-icon-cloudy', 0, 1);
INSERT INTO `t_system_resource` VALUES (61, 18, '活动中心', '', 1, '', 'el-icon-cloudy', 0, 1);
INSERT INTO `t_system_resource` VALUES (62, 12, '保存部门', 'system-dept:save', 2, '', 'el-icon-cloudy', 0, 1);
INSERT INTO `t_system_resource` VALUES (63, 12, '删除部门', 'system-dept:delete', 2, '', 'el-icon-cloudy', 0, 1);
INSERT INTO `t_system_resource` VALUES (64, 12, '查询部门', 'system-dept:query', 2, '', 'el-icon-cloudy', 0, 1);
INSERT INTO `t_system_resource` VALUES (65, 14, '保存员工', 'system-emp:save', 2, '', 'el-icon-cloudy', 0, 1);
INSERT INTO `t_system_resource` VALUES (66, 14, '查询员工', 'system-emp:query', 2, '', 'el-icon-cloudy', 0, 1);
INSERT INTO `t_system_resource` VALUES (67, 14, '删除员工', 'system-emp:delete', 2, '', 'el-icon-cloudy', 0, 1);
INSERT INTO `t_system_resource` VALUES (68, 14, '转为讲师', 'system-emp:turn-lecturer', 2, '', 'el-icon-cloudy', 0, 1);
INSERT INTO `t_system_resource` VALUES (69, 0, '素材管理', 'material-manage', 1, '/material-manage', 'el-icon-cloudy', 0, 1);
INSERT INTO `t_system_resource` VALUES (70, 69, '多媒体素材', 'file-manage', 1, '/material-manage/file-manage', 'el-icon-cloudy', 0, 1);
INSERT INTO `t_system_resource` VALUES (71, 69, '图文素材', 'article-manage', 1, '/material-manage/article-manage', 'el-icon-cloudy', 0, 1);
INSERT INTO `t_system_resource` VALUES (72, 69, '音视频分类', 'vod-classify', 1, '/material-manage/vod-classify', 'el-icon-cloudy', 0, 1);

-- ----------------------------
-- Table structure for t_system_role
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role`;
CREATE TABLE `t_system_role`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `permission_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限代码',
  `sort` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '显示顺序',
  `available` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用',
  `data_scope` tinyint(0) NOT NULL DEFAULT 0 COMMENT '权限范围',
  `dept_ids` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限',
  `creator` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idex_name_permission_code`(`name`, `permission_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_role
-- ----------------------------
INSERT INTO `t_system_role` VALUES (1, '超级管理员', '所有权限', 'super-administrator', 0, 1, 0, NULL, 1, '2020-03-29 08:49:51');
INSERT INTO `t_system_role` VALUES (2, '管理员', '管理员', 'administrator', 1, 1, 0, NULL, 1, '2020-03-29 08:57:52');

-- ----------------------------
-- Table structure for t_system_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role_resource`;
CREATE TABLE `t_system_role_resource`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(0) UNSIGNED NOT NULL COMMENT '角色ID',
  `resource_id` int(0) UNSIGNED NOT NULL COMMENT '资源ID',
  `creator` int(0) UNSIGNED NOT NULL COMMENT '创建人员',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_role_resouce_id`(`role_id`, `resource_id`) USING BTREE,
  INDEX `idx_role_resouce_id`(`role_id`, `resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色与资源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_role_resource
-- ----------------------------
INSERT INTO `t_system_role_resource` VALUES (15, 2, 1, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (16, 2, 2, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (17, 2, 3, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (18, 2, 4, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (19, 2, 5, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (20, 2, 6, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (21, 2, 12, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (22, 2, 13, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (23, 2, 14, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (24, 2, 7, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (25, 2, 8, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (26, 2, 9, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (27, 2, 10, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (28, 2, 11, 1, '2020-03-30 19:50:31');
INSERT INTO `t_system_role_resource` VALUES (30, 1, 1, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (31, 1, 2, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (32, 1, 3, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (33, 1, 4, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (34, 1, 5, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (35, 1, 6, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (36, 1, 12, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (37, 1, 13, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (38, 1, 14, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (39, 1, 7, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (40, 1, 8, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (41, 1, 9, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (42, 1, 10, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (43, 1, 11, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (44, 1, 15, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (45, 1, 16, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (46, 1, 17, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (47, 1, 18, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (48, 1, 19, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (49, 1, 20, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (50, 1, 21, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (51, 1, 22, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (52, 1, 23, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (53, 1, 24, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (54, 1, 25, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (55, 1, 26, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (56, 1, 27, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (57, 1, 34, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (58, 1, 35, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (59, 1, 32, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (60, 1, 33, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (61, 1, 38, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (62, 1, 28, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (63, 1, 29, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (64, 1, 30, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (65, 1, 31, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (66, 1, 36, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (67, 1, 37, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (68, 1, 39, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (69, 1, 40, 1, '2020-04-12 10:31:37');
INSERT INTO `t_system_role_resource` VALUES (70, 4, 1, 1, '2020-06-17 10:02:41');
INSERT INTO `t_system_role_resource` VALUES (71, 4, 8, 1, '2020-06-17 10:02:41');
INSERT INTO `t_system_role_resource` VALUES (72, 4, 2, 1, '2020-06-17 10:02:41');
INSERT INTO `t_system_role_resource` VALUES (73, 4, 7, 1, '2020-06-17 10:02:41');

-- ----------------------------
-- Table structure for t_system_user
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user`;
CREATE TABLE `t_system_user`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `emp_id` int(0) UNSIGNED NOT NULL COMMENT '员工ID',
  `available` tinyint(1) NULL DEFAULT 1 COMMENT '可用状态',
  `salt` int(0) NULL DEFAULT NULL COMMENT '盐值',
  `super_admin` tinyint(1) NULL DEFAULT 0,
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `last_change_password_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近一次修改密码时间',
  `creator` int(0) UNSIGNED NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_username`(`username`) USING BTREE,
  INDEX `idx_emp_id`(`emp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_user
-- ----------------------------
INSERT INTO `t_system_user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1, 1, 1, 1, '2020-10-29 13:11:08', '2020-10-29 13:11:07', 1, '2020-03-27 08:25:53');
INSERT INTO `t_system_user` VALUES (2, '200000', 'b8a2ff87d7ea487c6272c0071ec67331', 3, 1, NULL, 0, '2020-04-08 15:13:33', '2020-04-11 16:39:22', 1, '2020-03-31 08:42:54');

-- ----------------------------
-- Table structure for t_system_user_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user_resource`;
CREATE TABLE `t_system_user_resource`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(0) UNSIGNED NOT NULL COMMENT '用户ID',
  `resource_id` int(0) UNSIGNED NOT NULL COMMENT '资源ID',
  `creator` int(0) UNSIGNED NOT NULL COMMENT '创建人员',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_user_resource_id`(`user_id`, `resource_id`) USING BTREE,
  INDEX `idx_role_resource_id`(`user_id`, `resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与资源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user_role`;
CREATE TABLE `t_system_user_role`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(0) UNSIGNED NOT NULL COMMENT '用户ID',
  `role_id` int(0) UNSIGNED NOT NULL COMMENT '角色ID',
  `creator` int(0) UNSIGNED NOT NULL COMMENT '创建人员',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_user_role_id`(`user_id`, `role_id`) USING BTREE,
  INDEX `idx_role_resouce_id`(`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_system_user_role
-- ----------------------------
INSERT INTO `t_system_user_role` VALUES (4, 1, 1, 2, '2020-04-08 15:13:58');
INSERT INTO `t_system_user_role` VALUES (6, 2, 2, 1, '2020-09-24 13:06:11');
INSERT INTO `t_system_user_role` VALUES (7, 2, 1, 1, '2020-09-24 13:06:11');

-- ----------------------------
-- Table structure for t_ums_lecturer
-- ----------------------------
DROP TABLE IF EXISTS `t_ums_lecturer`;
CREATE TABLE `t_ums_lecturer`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `lecturer_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '讲师编号',
  `emp_id` int(0) NULL DEFAULT NULL COMMENT '员工ID 内部员工有empno',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '讲师用户ID',
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '讲师姓名',
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '讲师昵称',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '讲师头像',
  `position` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职位',
  `intro` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '简介',
  `mobile` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号码',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `type` tinyint(0) NOT NULL COMMENT '讲师类型1：机构内,2：自主申请',
  `available` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '讲师信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ums_lecturer
-- ----------------------------
INSERT INTO `t_ums_lecturer` VALUES (1, NULL, 3, NULL, '黄浩', NULL, NULL, NULL, '1212', '18321429296', '1570356753@qq.com', 1, 1);

-- ----------------------------
-- Table structure for t_ums_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_ums_organization`;
CREATE TABLE `t_ums_organization`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构名称',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构类型(个人 机构)',
  `org_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构编号',
  `contacts_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人姓名',
  `mobile` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系邮箱',
  `wechat` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系微信',
  `business_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '经营名',
  `signature` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '一句话介绍',
  `logo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'logo',
  `category` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主营类目',
  `intro` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_ums_user
-- ----------------------------
DROP TABLE IF EXISTS `t_ums_user`;
CREATE TABLE `t_ums_user`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `available` tinyint(1) NULL DEFAULT NULL COMMENT '帐号启用状态:0->禁用；1->启用',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `city` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所做城市',
  `job` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职业',
  `signature` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `source_type` tinyint(1) NULL DEFAULT NULL COMMENT '用户来源 1 来源',
  `create_time` datetime(0) NOT NULL COMMENT '注册时间',
  `latest_pwd_chg_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次密码修改时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username`) USING BTREE,
  UNIQUE INDEX `idx_mobile`(`mobile`) USING BTREE,
  UNIQUE INDEX `idx_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ums_user
-- ----------------------------
INSERT INTO `t_ums_user` VALUES (1, '18321429296', '$2a$10$XK3m3Ng/N64mLdkCSrTmZO2AWY6Zot/7kYflqWmxL8UflfC7PLqCu', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-03-29 21:04:22', NULL, NULL);

-- ----------------------------
-- Table structure for t_ums_user_course
-- ----------------------------
DROP TABLE IF EXISTS `t_ums_user_course`;
CREATE TABLE `t_ums_user_course`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `course_id` int(0) UNSIGNED NOT NULL COMMENT '内容ID',
  `user_id` int(0) UNSIGNED NOT NULL COMMENT '会员ID',
  `available` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用',
  `study_hours` int(0) UNSIGNED NULL DEFAULT 0 COMMENT '已学习时长',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '会员拥有内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ums_user_course
-- ----------------------------
INSERT INTO `t_ums_user_course` VALUES (1, 3, 1, 1, 0, '2020-06-24 17:01:45', '2020-06-24 17:01:45');
INSERT INTO `t_ums_user_course` VALUES (2, 7, 1, 1, 0, '2020-08-22 22:38:00', '2020-08-22 22:38:00');
INSERT INTO `t_ums_user_course` VALUES (3, 5, 1, 1, 0, '2020-10-30 00:36:34', '2020-10-30 00:36:34');

-- ----------------------------
-- Table structure for t_ums_user_course_log
-- ----------------------------
DROP TABLE IF EXISTS `t_ums_user_course_log`;
CREATE TABLE `t_ums_user_course_log`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(0) UNSIGNED NOT NULL COMMENT '会员ID',
  `course_id` int(0) UNSIGNED NOT NULL COMMENT '内容ID',
  `chapter_id` int(0) UNSIGNED NOT NULL COMMENT '章节ID',
  `period_id` int(0) UNSIGNED NOT NULL COMMENT '内容详情ID',
  `progress` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '学习进度',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_member_id`(`user_id`) USING BTREE,
  INDEX `idx_content_id`(`course_id`) USING BTREE,
  INDEX `idx_chapter_id`(`chapter_id`) USING BTREE,
  INDEX `idx_content_item_id`(`period_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '会员学习日志表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;