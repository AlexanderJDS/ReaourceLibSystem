/*
Navicat MySQL Data Transfer

Source Server         : .
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : libdb

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2016-10-16 01:01:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `type` int(10) NOT NULL DEFAULT '1',
  `telephone` varchar(255) NOT NULL,
  `create_time` varchar(255) NOT NULL,
  `status` int(10) NOT NULL,
  `area_id` int(255) NOT NULL,
  `auth` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `adminname` (`name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '4501195721962DCDADBFC33F8C0DD6E3', 'admin@qq.com', '0', '', '2016-10-12 15:13:12', '1', '0', '0');

-- ----------------------------
-- Table structure for `area`
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `area_id` int(11) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(50) NOT NULL,
  `area_type` int(20) NOT NULL COMMENT '使用0代表市,1代表县区',
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES ('1', '东营市', '0');
INSERT INTO `area` VALUES ('2', '东营区', '1');
INSERT INTO `area` VALUES ('3', ' 河口区', '1');
INSERT INTO `area` VALUES ('4', '广饶县', '1');
INSERT INTO `area` VALUES ('5', ' 垦利县', '1');
INSERT INTO `area` VALUES ('6', '利津县', '1');

-- ----------------------------
-- Table structure for `declare_type`
-- ----------------------------
DROP TABLE IF EXISTS `declare_type`;
CREATE TABLE `declare_type` (
  `dec_id` int(11) NOT NULL AUTO_INCREMENT,
  `decname` varchar(20) NOT NULL,
  PRIMARY KEY (`dec_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of declare_type
-- ----------------------------
INSERT INTO `declare_type` VALUES ('1', '音乐');
INSERT INTO `declare_type` VALUES ('2', '舞蹈');
INSERT INTO `declare_type` VALUES ('3', '戏剧曲艺');
INSERT INTO `declare_type` VALUES ('4', '朗诵主持');
INSERT INTO `declare_type` VALUES ('5', ' 文学创作');
INSERT INTO `declare_type` VALUES ('6', ' 书画');
INSERT INTO `declare_type` VALUES ('7', ' 摄影');
INSERT INTO `declare_type` VALUES ('8', ' 杂技');
INSERT INTO `declare_type` VALUES ('9', ' 民间文艺');
INSERT INTO `declare_type` VALUES ('10', '电影电视');

-- ----------------------------
-- Table structure for `degree`
-- ----------------------------
DROP TABLE IF EXISTS `degree`;
CREATE TABLE `degree` (
  `degree_id` int(11) NOT NULL AUTO_INCREMENT,
  `degreename` varchar(20) NOT NULL,
  PRIMARY KEY (`degree_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of degree
-- ----------------------------
INSERT INTO `degree` VALUES ('1', '学士');
INSERT INTO `degree` VALUES ('2', '硕士');
INSERT INTO `degree` VALUES ('3', ' 博士');

-- ----------------------------
-- Table structure for `edu`
-- ----------------------------
DROP TABLE IF EXISTS `edu`;
CREATE TABLE `edu` (
  `edu_id` int(11) NOT NULL,
  `eduname` varchar(20) NOT NULL,
  PRIMARY KEY (`edu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of edu
-- ----------------------------
INSERT INTO `edu` VALUES ('0', ' 小学');
INSERT INTO `edu` VALUES ('1', ' 初中');
INSERT INTO `edu` VALUES ('2', ' 中专');
INSERT INTO `edu` VALUES ('3', ' 高中');
INSERT INTO `edu` VALUES ('4', ' 专科');
INSERT INTO `edu` VALUES ('5', ' 本科');
INSERT INTO `edu` VALUES ('6', ' 硕士研究生');
INSERT INTO `edu` VALUES ('7', ' 博士研究生');

-- ----------------------------
-- Table structure for `mz`
-- ----------------------------
DROP TABLE IF EXISTS `mz`;
CREATE TABLE `mz` (
  `mz_id` int(11) NOT NULL AUTO_INCREMENT,
  `mzname` varchar(20) NOT NULL,
  PRIMARY KEY (`mz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mz
-- ----------------------------
INSERT INTO `mz` VALUES ('1', '汉族');
INSERT INTO `mz` VALUES ('2', '蒙古族');
INSERT INTO `mz` VALUES ('3', '回族');
INSERT INTO `mz` VALUES ('4', '藏族');
INSERT INTO `mz` VALUES ('5', '维吾尔族');
INSERT INTO `mz` VALUES ('6', '苗族');
INSERT INTO `mz` VALUES ('7', '彝族');
INSERT INTO `mz` VALUES ('8', '壮族');
INSERT INTO `mz` VALUES ('9', '布依族');
INSERT INTO `mz` VALUES ('10', '朝鲜族');
INSERT INTO `mz` VALUES ('11', '满族');
INSERT INTO `mz` VALUES ('12', '侗族');
INSERT INTO `mz` VALUES ('13', '瑶族');
INSERT INTO `mz` VALUES ('14', '白族');
INSERT INTO `mz` VALUES ('15', '土家族');
INSERT INTO `mz` VALUES ('16', '哈尼族');
INSERT INTO `mz` VALUES ('17', '哈萨克族');
INSERT INTO `mz` VALUES ('18', '傣族');
INSERT INTO `mz` VALUES ('19', '黎族');
INSERT INTO `mz` VALUES ('20', '傈僳族');
INSERT INTO `mz` VALUES ('21', '佤族');
INSERT INTO `mz` VALUES ('22', '畲族');
INSERT INTO `mz` VALUES ('23', '高山族');
INSERT INTO `mz` VALUES ('24', '拉祜族');
INSERT INTO `mz` VALUES ('25', '水族');
INSERT INTO `mz` VALUES ('26', '东乡族');
INSERT INTO `mz` VALUES ('27', '纳西族');
INSERT INTO `mz` VALUES ('28', '景颇族');
INSERT INTO `mz` VALUES ('29', '柯尔克孜族');
INSERT INTO `mz` VALUES ('30', '土族');
INSERT INTO `mz` VALUES ('31', '达斡尔族');
INSERT INTO `mz` VALUES ('32', '仫佬族');
INSERT INTO `mz` VALUES ('33', '羌族');
INSERT INTO `mz` VALUES ('34', ' 布朗族');
INSERT INTO `mz` VALUES ('35', ' 撒拉族');
INSERT INTO `mz` VALUES ('36', ' 毛难族');
INSERT INTO `mz` VALUES ('37', ' 仡佬族');
INSERT INTO `mz` VALUES ('38', ' 锡伯族');
INSERT INTO `mz` VALUES ('39', ' 阿昌族');
INSERT INTO `mz` VALUES ('40', ' 普米族');
INSERT INTO `mz` VALUES ('41', ' 塔吉克族');
INSERT INTO `mz` VALUES ('42', ' 怒族');
INSERT INTO `mz` VALUES ('43', ' 乌孜别克族');
INSERT INTO `mz` VALUES ('44', ' 俄罗斯族');
INSERT INTO `mz` VALUES ('45', ' 鄂温克族');
INSERT INTO `mz` VALUES ('46', ' 崩龙族');
INSERT INTO `mz` VALUES ('47', ' 保安族');
INSERT INTO `mz` VALUES ('48', ' 裕固族');
INSERT INTO `mz` VALUES ('49', ' 京族');
INSERT INTO `mz` VALUES ('50', ' 塔塔尔族');
INSERT INTO `mz` VALUES ('51', ' 独龙族');
INSERT INTO `mz` VALUES ('52', ' 鄂伦春族');
INSERT INTO `mz` VALUES ('53', ' 赫哲族');
INSERT INTO `mz` VALUES ('54', ' 门巴族');
INSERT INTO `mz` VALUES ('55', ' 珞巴族');
INSERT INTO `mz` VALUES ('56', ' 基诺族');
INSERT INTO `mz` VALUES ('57', ' 其他');

-- ----------------------------
-- Table structure for `system`
-- ----------------------------
DROP TABLE IF EXISTS `system`;
CREATE TABLE `system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `keywords` text,
  `description` text,
  `copyright` varchar(255) DEFAULT NULL,
  `record` varchar(255) DEFAULT NULL,
  `statistics_code` text,
  `open` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system
-- ----------------------------
INSERT INTO `system` VALUES ('1', '东营市社会文艺人才信息资源库', '关键词设置2', '网站描述设置', '山东汇佳软件科技有限公司版权所有', '鲁ICP备00000000001号', ' 统计代码设置', '0');

-- ----------------------------
-- Table structure for `uploads`
-- ----------------------------
DROP TABLE IF EXISTS `uploads`;
CREATE TABLE `uploads` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NOT NULL,
  `type` varchar(100) NOT NULL,
  `path` varchar(255) NOT NULL,
  `create_time` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uploads
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL,
  `true_name` varchar(255) DEFAULT NULL,
  `age` int(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `dec_id` int(11) DEFAULT NULL,
  `sbdate` varchar(30) DEFAULT NULL,
  `usersex` int(11) DEFAULT NULL,
  `mz_id` int(10) DEFAULT NULL,
  `zzmm_id` int(10) DEFAULT NULL,
  `degree_id` int(11) DEFAULT NULL,
  `edu_id` int(11) DEFAULT NULL,
  `gzdate` varchar(30) DEFAULT NULL,
  `person` varchar(50) DEFAULT NULL,
  `card` varchar(255) DEFAULT NULL,
  `area_id` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `edu__full_time` int(10) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `specialty` varchar(255) DEFAULT NULL,
  `zywork` varchar(20) DEFAULT NULL,
  `ysjj` varchar(255) DEFAULT NULL,
  `health` varchar(255) DEFAULT NULL,
  `reg_date` varchar(255) NOT NULL,
  `status` int(10) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `photo_path` varchar(255) DEFAULT NULL,
  `technical_position` varchar(255) DEFAULT NULL,
  `degree_full_time` int(10) DEFAULT NULL,
  `company_tel` varchar(255) DEFAULT NULL,
  `socio_part_time` varchar(255) DEFAULT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  `awards` varchar(255) DEFAULT NULL,
  `opinion` varchar(255) DEFAULT NULL,
  `business_achievement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`uname`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('31', 'py', '张三', '22', '5D23494D3D93A7EF5BDA2FCE1091C7A4', '1', '111', '1', '18', '1', '1', '0', '122', null, '372321199999999999', '1', '山东省东营市', null, '山东汇佳', '唱歌舞蹈', null, '呵呵呵呵呵', '健康', '2016-10-08 16:05:55', '1', '11111', 'defaultheadpic.jpg', '青年志愿者', null, '8086998', '社会兼职', '15762188888', '表演一等奖', '希望申请通过', '销售实习');
INSERT INTO `user` VALUES ('33', 'pangyong', '张三2', '22', '7B7047748C146671550A41036E2333EC', '2', '111', '0', '6', '4', '3', '0', '111', null, '372321199999999999', '0', '山东省东营市', null, '山东汇佳', '唱歌舞蹈', null, '中央戏剧表演', '健康', '2016-10-11 08:24:51', '1', '1111', 'defaultheadpic.jpg', '红年志愿者', null, '8086998', '社会兼职', '15762188888', ' 表演二等奖', '希望申请通过', '销售实习');
INSERT INTO `user` VALUES ('35', '汇佳软件', '张三3', '22', '9A42B2FDE562AD64942EE3058498251D', '1', '111', '1', '1', '1', '1', '0', '232', null, '372321199999999999', '1', '山东省东营市', null, '山东汇佳', '唱歌舞蹈', null, '艺术简介艺术简介', '健康', '2016-10-11 08:29:21', '1', '1111', 'defaultheadpic.jpg', '中年志愿者', null, '8086998', '社会兼职', '15762188888', '表演三等奖', '希望申请通过', '销售实习');
INSERT INTO `user` VALUES ('36', '山东汇佳软件', '张三4', '22', '3501B4E771BA5C7A744E8B6E8CEF916E', '1', '111', '1', '1', '1', '1', '0', '33', null, '372321199999999999', '1', '山东省东营市', null, '山东汇佳', '唱歌舞蹈', null, '艺术简介艺术简介', '健康', '2016-10-11 15:45:06', '1', '1111', 'defaultheadpic.jpg', '老年志愿者', null, '8086998', '社会兼职', '15762188888', ' 表演四等奖', '希望申请通过', '销售实习');
INSERT INTO `user` VALUES ('37', 'huijia', '张三5', '22', '4501195721962DCDADBFC33F8C0DD6E3', '1', '111', '1', '1', '1', '1', '0', '44', null, '372321199999999999', '1', '山东省东营市', null, '山东汇佳', '唱歌舞蹈', null, '呵呵呵呵呵', '健康', '2016-10-12 15:13:12', '0', '1111', 'defaultheadpic.jpg', '青年志愿', null, '8086998', '社会兼职', '15762188888', ' 表演五等奖', '希望申请通过', '销售实习');
INSERT INTO `user` VALUES ('38', '庞勇', '张三6', '22', '9D46A6083C6EBDDCFB898DAB58EAFD7F', '1', '111', '1', '1', '1', '1', '0', '55', null, '372321199999999999', '1', '山东省东营市', null, '山东汇佳', '唱歌舞蹈', null, '艺术简介艺术简介', '健康', '2016-10-13 22:47:39', '0', '1111', 'defaultheadpic.jpg', '青年者远着', null, '8086998', '社会兼职', '15762188888', ' 表演特等奖', '希望申请通过', '销售实习');
INSERT INTO `user` VALUES ('39', 'admin', '张三7', '22', '6E0F084E2078D7E28093027420CDD776', '1', '111', '0', '6', '4', '2', '0', '66', null, '372321199999999999', '0', '山东省东营市', null, '山东汇佳', '唱歌舞蹈', null, ' 中国表演大学', '健康', '2016-10-14 09:49:17', '1', '1111', 'defaultheadpic.jpg', '志愿者', null, '8086998', '社会兼职', '15762188888', ' 表演奖', '希望申请通过', '销售实习');
INSERT INTO `user` VALUES ('40', '庞永', '张三8', '22', 'DD015661785A22BDED41F84D206AF94E', '3', '111', '0', '6', '4', '3', '0', '77', null, '372321199999999999', '0', '山东省东营市', null, '山东汇佳', '唱歌舞蹈', null, '中央戏剧学院', '健康', '2016-10-14 09:49:59', '1', '1111', 'defaultheadpic.jpg', ' 山区只教', null, '8086998', '社会兼职', '15762188888', ' 参与奖', '希望申请通过', '销售实习');

-- ----------------------------
-- Table structure for `zzmm`
-- ----------------------------
DROP TABLE IF EXISTS `zzmm`;
CREATE TABLE `zzmm` (
  `zzmm_id` int(11) NOT NULL AUTO_INCREMENT,
  `zzmmname` varchar(20) NOT NULL,
  PRIMARY KEY (`zzmm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zzmm
-- ----------------------------
INSERT INTO `zzmm` VALUES ('1', '中共党员');
INSERT INTO `zzmm` VALUES ('2', ' 中共预备党员');
INSERT INTO `zzmm` VALUES ('3', ' 共青团员');
INSERT INTO `zzmm` VALUES ('4', ' 民革党员');
INSERT INTO `zzmm` VALUES ('5', ' 民盟盟员');
INSERT INTO `zzmm` VALUES ('6', ' 民建会员');
INSERT INTO `zzmm` VALUES ('7', ' 民进会员');
INSERT INTO `zzmm` VALUES ('8', ' 农工党党员');
INSERT INTO `zzmm` VALUES ('9', ' 致公党党员');
INSERT INTO `zzmm` VALUES ('10', ' 九三学社社员');
INSERT INTO `zzmm` VALUES ('11', ' 台盟盟员');
INSERT INTO `zzmm` VALUES ('12', ' 无党派民族人士');
INSERT INTO `zzmm` VALUES ('13', ' 普通公民(群众)');
