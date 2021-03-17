/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : joining

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2021-03-17 14:38:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administrator_tb
-- ----------------------------
DROP TABLE IF EXISTS `administrator_tb`;
CREATE TABLE `administrator_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员账号',
  `name` varchar(32) NOT NULL COMMENT '管理员名字',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `power` char(1) NOT NULL DEFAULT '1' COMMENT '管理员权限，默认为1，设定为管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of administrator_tb
-- ----------------------------
INSERT INTO `administrator_tb` VALUES ('1', '吴陈锋', '123456', '1');
INSERT INTO `administrator_tb` VALUES ('2', '吴陈锋', '123456', '1');
INSERT INTO `administrator_tb` VALUES ('3', '吴陈锋', '123456', '1');

-- ----------------------------
-- Table structure for car_tb
-- ----------------------------
DROP TABLE IF EXISTS `car_tb`;
CREATE TABLE `car_tb` (
  `id` varchar(32) NOT NULL COMMENT '拼车订单号',
  `start_point` varchar(255) NOT NULL COMMENT '起点',
  `end_point` varchar(255) NOT NULL COMMENT '目的地',
  `start_time` time NOT NULL COMMENT '出发时间',
  `identify_id` int(11) NOT NULL COMMENT '外键，在拼团信息表中的标识',
  PRIMARY KEY (`id`),
  KEY `identify_id` (`identify_id`),
  CONSTRAINT `car_tb_ibfk_1` FOREIGN KEY (`identify_id`) REFERENCES `order_tb` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_tb
-- ----------------------------
INSERT INTO `car_tb` VALUES ('1', '广金', '步行街', '18:38:50', '4');

-- ----------------------------
-- Table structure for house_tb
-- ----------------------------
DROP TABLE IF EXISTS `house_tb`;
CREATE TABLE `house_tb` (
  `id` varchar(32) NOT NULL COMMENT '拼房订单号',
  `gender_require` char(1) NOT NULL COMMENT '拼房对象性别要求',
  `housing_time` date NOT NULL COMMENT '入住日期',
  `identify_id` int(11) NOT NULL COMMENT '外键，在拼团信息表中的标识',
  PRIMARY KEY (`id`),
  KEY `identify_id` (`identify_id`),
  CONSTRAINT `house_tb_ibfk_1` FOREIGN KEY (`identify_id`) REFERENCES `order_tb` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of house_tb
-- ----------------------------
INSERT INTO `house_tb` VALUES ('1', '男', '2021-03-03', '2');
INSERT INTO `house_tb` VALUES ('2', '女', '2021-03-09', '3');

-- ----------------------------
-- Table structure for order_tb
-- ----------------------------
DROP TABLE IF EXISTS `order_tb`;
CREATE TABLE `order_tb` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '拼团信息标记ID号',
  `type` char(1) NOT NULL DEFAULT '0' COMMENT '类型，0表示拼购，1表示拼车，2表示拼房',
  `publish_date` date NOT NULL COMMENT '发布日期',
  `publish_time` time NOT NULL COMMENT '发布时间',
  `publisher` int(9) NOT NULL COMMENT '发布人账号',
  `image_route` varchar(255) DEFAULT NULL COMMENT '相关图片路径',
  `status` char(1) NOT NULL COMMENT '拼单状态，默认为0，表示拼团中，1表示拼团完成，2表示拼团已撤销',
  `content` text COMMENT '相关描述（备注）',
  PRIMARY KEY (`order_id`),
  KEY `publisher` (`publisher`),
  CONSTRAINT `order_tb_ibfk_1` FOREIGN KEY (`publisher`) REFERENCES `student_tb` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_tb
-- ----------------------------
INSERT INTO `order_tb` VALUES ('1', '0', '2021-03-09', '11:59:33', '1', 'images/bg.jpg', '1', '买');
INSERT INTO `order_tb` VALUES ('2', '0', '2021-03-09', '18:13:49', '1', 'images/下载.jpg', '1', '开房');
INSERT INTO `order_tb` VALUES ('3', '0', '2021-03-19', '18:18:59', '1', 'images/bg.jpg', '1', '有帅哥一起开房吗？');
INSERT INTO `order_tb` VALUES ('4', '0', '2021-03-09', '18:37:30', '171543140', 'images/bg.jpg', '1', '开车');

-- ----------------------------
-- Table structure for report_tb
-- ----------------------------
DROP TABLE IF EXISTS `report_tb`;
CREATE TABLE `report_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '信息标记号',
  `whistleblower_num` int(9) NOT NULL COMMENT '举报人账号',
  `defendant_num` int(9) NOT NULL COMMENT '被举报人账号',
  `content` text NOT NULL COMMENT '举报内容',
  `image_route` varchar(255) NOT NULL COMMENT '证明材料',
  `publish_date` date NOT NULL COMMENT '发布日期',
  `publish_time` time NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`),
  KEY `whistleblower_num` (`whistleblower_num`),
  KEY `defendant_num` (`defendant_num`),
  CONSTRAINT `report_tb_ibfk_1` FOREIGN KEY (`whistleblower_num`) REFERENCES `student_tb` (`id`),
  CONSTRAINT `report_tb_ibfk_2` FOREIGN KEY (`defendant_num`) REFERENCES `student_tb` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report_tb
-- ----------------------------

-- ----------------------------
-- Table structure for shopping_tb
-- ----------------------------
DROP TABLE IF EXISTS `shopping_tb`;
CREATE TABLE `shopping_tb` (
  `id` varchar(32) NOT NULL COMMENT '拼购订单号',
  `order_id` int(11) NOT NULL COMMENT '外键，在拼团信息表中的标识',
  `product_type` varchar(32) NOT NULL COMMENT '拼单商品类型',
  `price_cad` int(32) NOT NULL COMMENT '需要拼的价格上限（即差多少钱拼够）',
  PRIMARY KEY (`id`),
  KEY `identify_id` (`order_id`),
  CONSTRAINT `shopping_tb_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_tb` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopping_tb
-- ----------------------------
INSERT INTO `shopping_tb` VALUES ('1', '1', '拼单', '10000');

-- ----------------------------
-- Table structure for student_tb
-- ----------------------------
DROP TABLE IF EXISTS `student_tb`;
CREATE TABLE `student_tb` (
  `id` int(9) NOT NULL COMMENT '学生账号',
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `dormitory` varchar(32) NOT NULL COMMENT '宿舍楼',
  `room` int(11) DEFAULT NULL COMMENT '宿舍号',
  `gender` char(1) NOT NULL DEFAULT '1' COMMENT '性别,默认为1，设定为男',
  `wechat` varchar(32) DEFAULT NULL COMMENT '微信联系方式',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `status` char(1) DEFAULT '' COMMENT '状态，分为1正常和2异常,3审核',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `cardfront` varchar(255) DEFAULT NULL,
  `cardback` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_tb
-- ----------------------------
INSERT INTO `student_tb` VALUES ('0', 'q', '18栋', '22', '男', '', '', '3', '', null, '', null);
INSERT INTO `student_tb` VALUES ('1', 'wu', 'wu', '2222', '1', 'wwwww', '123', '1', 'www', 'identify/23b244d5ac-578c-43df-b713-2ddec36745ab_NIKNu6w57qYo2b933de49603d7d6e388e1ca9393c56f.jpg', '', '');
INSERT INTO `student_tb` VALUES ('23', '2', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/23b244d5ac-578c-43df-b713-2ddec36745ab_NIKNu6w57qYo2b933de49603d7d6e388e1ca9393c56f.jpg', null);
INSERT INTO `student_tb` VALUES ('25', '2', '18栋', '2', '男', '2', '2', '3', '2', 'head/head.jpg', 'identify/253ab3e04f-eee8-4315-9b8a-60adac4af24b_wLsqxamfBWjT39772138055e8acbf3e3bff4837c8fbd.jpg', null);
INSERT INTO `student_tb` VALUES ('42', '12', '18栋', '124', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/4236c99c87-1f4e-4fa4-bdc5-af6b33b3bad6_NSGbBFeKBNcc91f9f4a075ce2b72bf09113610417b81.jpg', null);
INSERT INTO `student_tb` VALUES ('76', '1', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/7601fd05b3-4777-4c7d-91c5-04d843d3e60b_IXLuIl4WXEMz1f9815c789dd14fdf362e2d7ade9542d.jpg', null);
INSERT INTO `student_tb` VALUES ('212', '121', '18栋', '21', '男', '', '', '3', '', 'head/head.jpg', 'identify/2129caaa9dd-28e7-40c9-a771-4edb37b98a2d_MvCi18sOtXfr83aa2dc7bdade7faa8b78321bdd0af2b.jpg', null);
INSERT INTO `student_tb` VALUES ('231', '22', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/231ead63416-9e45-4a87-a8c7-4d4a7f51a106_py2yTMFNcehjd1a26ec87f884adcc3ed35105be3f14a.jpg', null);
INSERT INTO `student_tb` VALUES ('311', '2', '18栋', '2', '男', '', '2', '3', '', 'head/head.jpg', 'identify/31196b230d9-46d0-49a9-93c0-01b95d0e21c8_sIBwwbYtIMUJ83aa2dc7bdade7faa8b78321bdd0af2b.jpg', null);
INSERT INTO `student_tb` VALUES ('324', '9', '18栋', '9', '男', '9', '', '3', '9', '', '', null);
INSERT INTO `student_tb` VALUES ('342', '22', '18栋', '2', '男', '2', '2', '3', '12', 'head/head.jpg', 'identify/3426af49a09-78c2-4b59-9ee6-4bd5256c7e89_vAPWcQwCHFuIc04c584aff3d498842740faed3ebcb40.jpg', null);
INSERT INTO `student_tb` VALUES ('542', '1', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/542494eaf0b-ae07-443d-9677-9ff1889bf776_wRNbaKMScucy91f9f4a075ce2b72bf09113610417b81.jpg', null);
INSERT INTO `student_tb` VALUES ('897', '1', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/897f53c15ea-ab8d-41ac-9719-3655adefdde6_Zy8ZVxosEZbW1f9815c789dd14fdf362e2d7ade9542d.jpg', null);
INSERT INTO `student_tb` VALUES ('899', '1', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/899d10bbb7c-19e6-4d90-a482-20cf4c86a910_ukeRTr2Vr2bV2b933de49603d7d6e388e1ca9393c56f.jpg', null);
INSERT INTO `student_tb` VALUES ('908', '1', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/908eb294421-4fc4-4f60-9764-a7d8e8bdb74a_40loCTbyDkpy83aa2dc7bdade7faa8b78321bdd0af2b.jpg', null);
INSERT INTO `student_tb` VALUES ('2131', '1', '18栋', '2', '男', '', '2', '3', '', 'head/head.jpg', 'identify/2131679cbb62-e482-4780-98d7-0ca8b3e11bdc_ATSt1x06iyM02b933de49603d7d6e388e1ca9393c56f.jpg', null);
INSERT INTO `student_tb` VALUES ('3245', '1', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/3245568a531c-fb84-4291-97ea-66d7fe38e46a_rpg1OUaGqkG283aa2dc7bdade7faa8b78321bdd0af2b.jpg', null);
INSERT INTO `student_tb` VALUES ('4532', '1', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/45321d206ad9-b354-4e1d-9970-a7636e5fe710_VsvgOURCbnV883aa2dc7bdade7faa8b78321bdd0af2b.jpg', null);
INSERT INTO `student_tb` VALUES ('5555', '5', '18栋', '5', '男', '5', '5', '3', '5', 'head/head.jpg', 'identify/5555b6b4bdcc-2cde-4b14-8cbd-b39118763024_mjY7a5JB5vSu39772138055e8acbf3e3bff4837c8fbd.jpg', null);
INSERT INTO `student_tb` VALUES ('6444', '645654', '18栋', '465', '男', '', '', '3', '', 'head/head.jpg', 'identify/6444278e3aef-ba37-4c7e-8dc1-303313589371_P9T0rw2uc5Fic04c584aff3d498842740faed3ebcb40.jpg', null);
INSERT INTO `student_tb` VALUES ('7777', '1', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/77777a3f446a-e400-4d3e-827e-21369c267113_SM0EzJxFCpTm2b933de49603d7d6e388e1ca9393c56f.jpg', null);
INSERT INTO `student_tb` VALUES ('8989', '11', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/8989ef12e729-104c-4ba1-bfd9-65cb7a745972_SY4ARg19KZyLd1a26ec87f884adcc3ed35105be3f14a.jpg', null);
INSERT INTO `student_tb` VALUES ('12313', '12', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/123133d582077-5974-4ec3-b292-ec73a1e13262_6pjat0pEFLJB2b933de49603d7d6e388e1ca9393c56f.jpg', null);
INSERT INTO `student_tb` VALUES ('12345', 'wu', '18栋', '212', '男', '432', '12345', '3', '123', 'head/head.jpg', 'identify/123450fa99579-d3b0-4413-a79f-6a5a840dc33f_hmUGy9Rx9tYsaad425b4ecca340fe574c5b586dcb4aa.jpg', null);
INSERT INTO `student_tb` VALUES ('31231', '321', '18栋', '312', '男', '', '', '3', '', 'head/head.jpg', 'identify/31231ec74bd4c-38db-4dea-9c2a-1840400ef8e0_ld1FykENN20qd1a26ec87f884adcc3ed35105be3f14a.jpg', null);
INSERT INTO `student_tb` VALUES ('54288', '1', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/54288860ed2ce-517b-4510-a69f-230fafbb75a3_wRNbaKMScucy91f9f4a075ce2b72bf09113610417b81.jpg', null);
INSERT INTO `student_tb` VALUES ('89823', '1', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/89823bf0cf547-e7ba-41c8-8cca-386ebaca81b9_ukeRTr2Vr2bV2b933de49603d7d6e388e1ca9393c56f.jpg', null);
INSERT INTO `student_tb` VALUES ('213213', '321321', '18栋', '321', '男', '', '', '3', '', 'head/head.jpg', 'identify/21321302933493-e9a5-42ff-b0c0-93631e36fd87_GH1MNG8VzrKFd1a26ec87f884adcc3ed35105be3f14a.jpg', null);
INSERT INTO `student_tb` VALUES ('233245', '11', '18栋', '1', '男', '', '1', '3', '', 'head/head.jpg', 'identify/233245975d2fec-f724-4c6f-931f-58335ed2d74d_nwZ6BmutnEB439772138055e8acbf3e3bff4837c8fbd.jpg', null);
INSERT INTO `student_tb` VALUES ('898990', '11', '18栋', '1', '男', '1', '1', '3', '1', 'head/head.jpg', 'identify/8989907f27481f-16a3-4a03-a863-b4d810f2af7b_SY4ARg19KZyLd1a26ec87f884adcc3ed35105be3f14a.jpg', null);
INSERT INTO `student_tb` VALUES ('1715431', '吴陈锋', '18栋', '123', '男', '', '123', '1', '', 'head/head.jpg', 'identify/1715431458e59df-662c-4797-9cd1-a6b9034ddb49_tmp_e761504f512ca33aad92f615b7f7d8ffc0656c67e6c3116d.jpg', null);
INSERT INTO `student_tb` VALUES ('1715432', 'haha', '18栋', '212', '男', '1', '123', '1', '1', 'head/head.jpg', 'identify/17154322cf9f4be-496c-4d0e-bece-337e520de5a8_xtMmOg2XrGxx811159d8ff35e52e37f0520f4a8aafc4.jpg', null);
INSERT INTO `student_tb` VALUES ('4324521', '5145', '18栋', '5345', '男', '', '', '3', '', 'head/head.jpg', 'identify/4324521c30c5ded-d75a-4e6b-9376-ec1a954ae759_dBSqAXAN4Us0d1a26ec87f884adcc3ed35105be3f14a.jpg', null);
INSERT INTO `student_tb` VALUES ('171543140', '吴', '13-906', '906', '1', '挖挖挖挖', '123456', '1', '挖挖挖挖', 'head/head.jpg', '', '');
