# Host: 127.0.0.1  (Version: 5.0.22-community)
# Date: 2014-09-19 10:32:12
# Generator: MySQL-Front 5.3  (Build 4.155)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "album"
#

DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `author` varchar(50) default NULL,
  `authorEmail` varchar(50) default NULL,
  `introduction` longtext,
  `conclusion` longtext,
  `createTime` datetime default NULL,
  `lastEditTime` datetime default NULL,
  `lastViewIp` varchar(64) default NULL,
  `lastViewTime` datetime default NULL,
  `salt` varchar(6) default NULL,
  `password` varchar(64) default NULL,
  `locking` bit(1) default NULL,
  `views` bigint(20) default NULL,
  `investigationTitle` varchar(255) default NULL,
  `agrees` bigint(20) default NULL,
  `opposes` bigint(20) default NULL,
  `htmlCreated` bit(1) default NULL,
  `htmlUrlPath` varchar(255) default NULL,
  `htmlURLFile` varchar(255) default NULL,
  `encryptedParmStr` varchar(64) default NULL,
  `state` bit(1) default NULL,
  `def` bit(1) default NULL,
  `leaderID` bigint(20) default NULL,
  `passerID` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK5897E6FEF9D5FA3` (`leaderID`),
  KEY `FK5897E6FDB13C1D8` (`passerID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "album_actor"
#

DROP TABLE IF EXISTS `album_actor`;
CREATE TABLE `album_actor` (
  `id` bigint(20) NOT NULL auto_increment,
  `msg` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `state` bit(1) default NULL,
  `applyTime` datetime default NULL,
  `userID` bigint(20) default NULL,
  `albumID` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKE368BC25E7B06BA5` (`albumID`),
  KEY `FKE368BC2560BD5DE5` (`userID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "album_process"
#

DROP TABLE IF EXISTS `album_process`;
CREATE TABLE `album_process` (
  `id` bigint(20) NOT NULL auto_increment,
  `proTitle` varchar(255) default NULL,
  `mainImg` varchar(255) default NULL,
  `proText` varchar(255) default NULL,
  `displayOrder` int(11) default NULL,
  `albumID` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKDEF4649FE7B06BA5` (`albumID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "attachment"
#

DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` bigint(20) NOT NULL auto_increment,
  `hostId` bigint(20) default NULL,
  `hostType` int(11) default NULL,
  `title` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `fileSize` bigint(20) default NULL,
  `price` int(11) default NULL,
  `downloads` bigint(20) default NULL,
  `media` bit(1) default NULL,
  `mediaType` int(11) default NULL,
  `addTime` datetime default NULL,
  `local` bit(1) default NULL,
  `orderNum` int(11) default NULL,
  `encryptedParmStr` varchar(64) default NULL,
  `userID` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK8AF7592360BD5DE5` (`userID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "bbs_bm"
#

DROP TABLE IF EXISTS `bbs_bm`;
CREATE TABLE `bbs_bm` (
  `id` bigint(20) NOT NULL auto_increment,
  `bf_id` int(11) default NULL,
  `user_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKACD5A3D789CD19CE` (`user_id`),
  KEY `FKACD5A3D778499A6F` (`bf_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "bbs_forum"
#

DROP TABLE IF EXISTS `bbs_forum`;
CREATE TABLE `bbs_forum` (
  `id` int(11) NOT NULL auto_increment,
  `forumName` varchar(100) default NULL,
  `description` varchar(255) default NULL,
  `displayOrder` varchar(255) default NULL,
  `footerLeft` int(11) default NULL,
  `footerRight` int(11) default NULL,
  `share` bit(1) default NULL,
  `asClass` bit(1) default NULL,
  `state` bit(1) default NULL,
  `hostsAllow` varchar(255) default NULL,
  `icoUrl` varchar(255) default NULL,
  `pollFmt` varchar(3) default NULL,
  `rootForumID` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKF1C03A15DFBBBE53` (`rootForumID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "bbs_info"
#

DROP TABLE IF EXISTS `bbs_info`;
CREATE TABLE `bbs_info` (
  `id` int(11) NOT NULL,
  `name` varchar(50) default NULL,
  `state` bit(1) default NULL,
  `postMustInGroup` bit(1) default NULL,
  `closeAnnounce` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `pageSizeOfTitle` int(11) default NULL,
  `pageSizeOfTheme` int(11) default NULL,
  `timesOfLockTheme` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "bbs_scoregroups"
#

DROP TABLE IF EXISTS `bbs_scoregroups`;
CREATE TABLE `bbs_scoregroups` (
  `id` int(11) NOT NULL auto_increment,
  `groupName` varchar(20) NOT NULL,
  `powerMask` varchar(255) default NULL,
  `valueLowerLimit` int(11) default NULL,
  `valueUpperLimit` int(11) default NULL,
  `schemeID` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK58D688DA40E63C1B` (`schemeID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "bbs_scoreschemes"
#

DROP TABLE IF EXISTS `bbs_scoreschemes`;
CREATE TABLE `bbs_scoreschemes` (
  `gid` int(11) NOT NULL auto_increment,
  `schemeName` varchar(20) NOT NULL,
  `author` varchar(255) default NULL,
  `state` bit(1) default NULL,
  `description` varchar(255) default NULL,
  `valueOfNewTheme` int(11) default NULL,
  `valueOfReply` int(11) default NULL,
  `valueOfSoul` int(11) default NULL,
  `valueOfUpload` int(11) default NULL,
  `valueOfDownload` int(11) default NULL,
  `valueOfMin` int(11) default NULL,
  PRIMARY KEY  (`gid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "bbs_style"
#

DROP TABLE IF EXISTS `bbs_style`;
CREATE TABLE `bbs_style` (
  `id` int(11) NOT NULL auto_increment,
  `styleName` varchar(50) default NULL,
  `author` varchar(50) default NULL,
  `description` varchar(255) default NULL,
  `state` bit(1) default NULL,
  `def` bit(1) default NULL,
  `locationSplitStr` varchar(255) default NULL,
  `hrefLineFormatStrOverAll` varchar(255) default NULL,
  `hrefLineFormatWithSnStrOverAll` varchar(255) default NULL,
  `bmShowFormat` varchar(255) default NULL,
  `lastEditorShowFormat` varchar(255) default NULL,
  `addThemeButtomCode` longtext,
  `editThemeAreaCode` longtext,
  `replieThreadAreaCode` longtext,
  `icoFolderUrl` varchar(255) default NULL,
  `topActCode` longtext,
  `shieldedShowCode` longtext,
  `afterReplyShowCode` longtext,
  `quoteButtomCode` varchar(255) default NULL,
  `quoteAreaCode` longtext,
  `indexShowLastThreadFormatStr` longtext,
  `resultPageCode` longtext,
  `publicCode1` longtext,
  `publicCode2` longtext,
  `publicCode3` longtext,
  `publicCode4` longtext,
  `customFormatCode1` longtext,
  `customFormatCode2` longtext,
  `customFormatCode3` longtext,
  `customFormatCode4` longtext,
  `customFormatCode5` longtext,
  `customFormatCode6` longtext,
  `customFormatCode7` longtext,
  `customFormatCode8` longtext,
  `pid` int(11) default NULL,
  `iid` int(11) default NULL,
  `fid` int(11) default NULL,
  `eid` int(11) default NULL,
  `tid` int(11) default NULL,
  `sid` int(11) default NULL,
  `gid` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKF279CAA51D0932E6` (`fid`),
  KEY `FKF279CAA51D0963B3` (`sid`),
  KEY `FKF279CAA51D093E29` (`iid`),
  KEY `FKF279CAA51D095870` (`pid`),
  KEY `FKF279CAA51D096774` (`tid`),
  KEY `FKF279CAA51D0936A7` (`gid`),
  KEY `FKF279CAA51D092F25` (`eid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "bbs_style_element"
#

DROP TABLE IF EXISTS `bbs_style_element`;
CREATE TABLE `bbs_style_element` (
  `id` int(11) NOT NULL auto_increment,
  `htmlTemplate` longtext,
  `cssCode` longtext,
  `htmlCode` longtext,
  `topCode` longtext,
  `footerCode` longtext,
  `targetStr` varchar(255) default NULL,
  `titleFormat` varchar(255) default NULL,
  `hrefLineFormat` longtext,
  `memberPanelCodeForLogIn` longtext,
  `memberPanelCodeForLogOut` longtext,
  `dateFormatOnLine` varchar(255) default NULL,
  `dateFormatInText` varchar(255) default NULL,
  `majorLoopCodeInLump` longtext,
  `minorLoopCodeInLump` longtext,
  `bmDataFormat` longtext,
  `titleOfMajorLoopCodeInLump` longtext,
  `titleOfMinorLoopCodeInLump` longtext,
  `editAreaCode` longtext,
  `functionAreaCode` longtext,
  `searchAreaCode` longtext,
  `specialCode1` longtext,
  `specialCode2` longtext,
  `specialCode3` longtext,
  `specialCode4` longtext,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "bbs_theme"
#

DROP TABLE IF EXISTS `bbs_theme`;
CREATE TABLE `bbs_theme` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(100) default NULL,
  `state` bit(1) default NULL,
  `pollAllow` bit(1) default NULL,
  `proponents` bigint(20) default NULL,
  `opponents` bigint(20) default NULL,
  `neutrals` bigint(20) default NULL,
  `top` bit(1) default NULL,
  `shield` bit(1) default NULL,
  `soul` bit(1) default NULL,
  `views` int(11) default NULL,
  `topMod` int(11) default NULL,
  `addTime` datetime default NULL,
  `addTimeUnix` bigint(20) default NULL,
  `chgTime` datetime default NULL,
  `lastEditTime` datetime default NULL,
  `lastViewIp` varchar(64) default NULL,
  `quote` varchar(255) default NULL,
  `lastViewTime` datetime default NULL,
  `body` longtext,
  `secCode` varchar(255) default NULL,
  `exoticActors` int(11) default NULL,
  `reps` int(11) default NULL,
  `sink` bit(1) default NULL,
  `addIp` varchar(255) default NULL,
  `lastEditIp` varchar(255) default NULL,
  `seeAfterReply` bit(1) default NULL,
  `rootThemeID` bigint(20) default NULL,
  `forumID` int(11) default NULL,
  `userID` bigint(20) default NULL,
  `passerID` bigint(20) default NULL,
  `lastEditUserID` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKF28222BDE38A324B` (`rootThemeID`),
  KEY `FKF28222BDDB13C1D8` (`passerID`),
  KEY `FKF28222BDD5C56B85` (`lastEditUserID`),
  KEY `FKF28222BD4A57A175` (`forumID`),
  KEY `FKF28222BD60BD5DE5` (`userID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "comment"
#

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL auto_increment,
  `publisher` varchar(30) default NULL,
  `title` varchar(255) default NULL,
  `body` longtext,
  `ip` varchar(64) default NULL,
  `addTime` datetime default NULL,
  `email` varchar(255) default NULL,
  `phone` varchar(20) default NULL,
  `publisherFrom` varchar(30) default NULL,
  `state` bit(1) default NULL,
  `threadId` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK38A5EE5FCE7186FA` (`threadId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "custom_code"
#

DROP TABLE IF EXISTS `custom_code`;
CREATE TABLE `custom_code` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(40) default NULL,
  `description` varchar(40) default NULL,
  `code` longtext,
  `state` bit(1) default NULL,
  `current` bit(1) default NULL,
  `ccID` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK960559FB4E415D9C` (`ccID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "custom_code_category"
#

DROP TABLE IF EXISTS `custom_code_category`;
CREATE TABLE `custom_code_category` (
  `id` int(11) NOT NULL auto_increment,
  `name` longtext,
  `currentCodeId` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "draw"
#

DROP TABLE IF EXISTS `draw`;
CREATE TABLE `draw` (
  `id` int(11) NOT NULL auto_increment,
  `state` bit(1) default NULL,
  `title` varchar(255) default NULL,
  `resultNum` int(11) default NULL,
  `drawStartTime` datetime default NULL,
  `drawEndTime` datetime default NULL,
  `password` varchar(255) default NULL,
  `votesRange` varchar(255) default NULL,
  `resultRecStr` longtext,
  `exceptedRecStr` longtext,
  `drawStyleId` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2F2D4478F365CB` (`drawStyleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "draw_style"
#

DROP TABLE IF EXISTS `draw_style`;
CREATE TABLE `draw_style` (
  `id` int(11) NOT NULL auto_increment,
  `styleName` varchar(50) default NULL,
  `author` varchar(50) default NULL,
  `description` varchar(255) default NULL,
  `state` bit(1) default NULL,
  `htmlTemplate` longtext,
  `cssCode` longtext,
  `titleFormat` longtext,
  `resultLineFormat` longtext,
  `htmlCode` longtext,
  `startCode` longtext,
  `resultCode` longtext,
  `clearCode` longtext,
  `maskFormat` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "external_host_charset"
#

DROP TABLE IF EXISTS `external_host_charset`;
CREATE TABLE `external_host_charset` (
  `id` int(11) NOT NULL auto_increment,
  `host` varchar(30) default NULL,
  `port` int(11) default NULL,
  `type` int(11) default NULL,
  `charset` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "integral_rule"
#

DROP TABLE IF EXISTS `integral_rule`;
CREATE TABLE `integral_rule` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `localPostion` int(11) default NULL,
  `createTime` datetime default NULL,
  `valueOfAdd` int(11) default NULL,
  `valueOfDel` int(11) default NULL,
  `valueOfReg` int(11) default NULL,
  `valueOfLogin` int(11) default NULL,
  `state` bit(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "qa_item"
#

DROP TABLE IF EXISTS `qa_item`;
CREATE TABLE `qa_item` (
  `id` bigint(20) NOT NULL auto_increment,
  `salt` varchar(10) default NULL,
  `title` varchar(50) default NULL,
  `author` varchar(20) default NULL,
  `question` longtext,
  `answer` longtext,
  `questioner` varchar(255) default NULL,
  `views` int(11) default NULL,
  `replier` varchar(255) default NULL,
  `addIp` varchar(255) default NULL,
  `addTime` datetime default NULL,
  `replyTime` datetime default NULL,
  `lastViewIp` varchar(64) default NULL,
  `lastViewTime` datetime default NULL,
  `email` varchar(255) default NULL,
  `addr` varchar(255) default NULL,
  `qq` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `open` bit(1) default NULL,
  `state` bit(1) default NULL,
  `password` varchar(255) default NULL,
  `htmlCreated` bit(1) default NULL,
  `htmlUrlPath` varchar(255) default NULL,
  `htmlURLFile` varchar(255) default NULL,
  `extInf1` varchar(255) default NULL,
  `extInf2` varchar(255) default NULL,
  `extInf3` varchar(255) default NULL,
  `extInf4` varchar(255) default NULL,
  `extInf5` varchar(255) default NULL,
  `extInf6` varchar(255) default NULL,
  `addUserID` bigint(20) default NULL,
  `replyUserID` bigint(20) default NULL,
  `qnID` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK4926FE2C150194F` (`replyUserID`),
  KEY `FK4926FE2CC9019C6` (`addUserID`),
  KEY `FK4926FE264564A52` (`qnID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "qa_nav"
#

DROP TABLE IF EXISTS `qa_nav`;
CREATE TABLE `qa_nav` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(50) default NULL,
  `state` bit(1) default NULL,
  `loginNeed` bit(1) default NULL,
  `sendMail` bit(1) default NULL,
  `description` varchar(255) default NULL,
  `powerMask` varchar(255) default NULL,
  `adminEmail` varchar(255) default NULL,
  `sendEmail` varchar(255) default NULL,
  `serverOfSendEmail` varchar(255) default NULL,
  `usernameOfSendEmail` varchar(255) default NULL,
  `passwordOfSendEmail` varchar(255) default NULL,
  `sendTemplateForAdd` longtext,
  `sendTemplateForReply` longtext,
  `sendTitle` varchar(255) default NULL,
  `displayOrder` varchar(255) default NULL,
  `numberShowOn` int(11) default NULL,
  `staticHtmlFolder` varchar(255) default NULL,
  `refuseStaticHtml` bit(1) default NULL,
  `parentId` int(11) default NULL,
  `styleId` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC6575E74CB3F57B7` (`styleId`),
  KEY `FKC6575E74AA2CD59F` (`parentId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "qa_style"
#

DROP TABLE IF EXISTS `qa_style`;
CREATE TABLE `qa_style` (
  `id` int(11) NOT NULL auto_increment,
  `styleName` varchar(50) default NULL,
  `author` varchar(50) default NULL,
  `description` varchar(255) default NULL,
  `state` bit(1) default NULL,
  `addAreaCode` longtext,
  `replyAreaCode` longtext,
  `ajaxRealTimeCode` longtext,
  `locationSplitStr` varchar(255) default NULL,
  `publicCode1` longtext,
  `publicCode2` longtext,
  `publicCode3` longtext,
  `publicCode4` longtext,
  `processedStr` varchar(255) default NULL,
  `noProcessedStr` varchar(255) default NULL,
  `openStr` varchar(255) default NULL,
  `noOpenStr` varchar(255) default NULL,
  `pid` int(11) default NULL,
  `iid` int(11) default NULL,
  `nid` int(11) default NULL,
  `qid` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK8E48C2C2DD5BD2C0` (`nid`),
  KEY `FK8E48C2C2DD5BBFFB` (`iid`),
  KEY `FK8E48C2C2DD5BDA42` (`pid`),
  KEY `FK8E48C2C2DD5BDE03` (`qid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "qa_style_element"
#

DROP TABLE IF EXISTS `qa_style_element`;
CREATE TABLE `qa_style_element` (
  `id` int(11) NOT NULL auto_increment,
  `htmlTemplate` longtext,
  `cssCode` longtext,
  `htmlCode` longtext,
  `topCode` longtext,
  `footerCode` longtext,
  `memberPanelCodeForLogIn` longtext,
  `memberPanelCodeForLogOut` longtext,
  `itemsLoopFormat` longtext,
  `itemsLoopFormatNoStateInf` longtext,
  `navsLoopFormat` longtext,
  `titleFormat` varchar(255) default NULL,
  `hrefLineFormat` longtext,
  `specialCode1` longtext,
  `specialCode2` longtext,
  `specialCode3` longtext,
  `specialCode4` longtext,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "site_article_group"
#

DROP TABLE IF EXISTS `site_article_group`;
CREATE TABLE `site_article_group` (
  `id` int(11) NOT NULL auto_increment,
  `groupName` varchar(50) default NULL,
  `displayOrder` varchar(255) default NULL,
  `footerLeft` int(11) default NULL,
  `footerRight` int(11) default NULL,
  `share` bit(1) default NULL,
  `changed` bit(1) default NULL,
  `asClass` bit(1) default NULL,
  `state` bit(1) default NULL,
  `pollFmt` varchar(3) default NULL,
  `showAll` bit(1) default NULL,
  `price` int(11) default NULL,
  `iconUrl` varchar(255) default NULL,
  `hostsAllow` varchar(255) default NULL,
  `jumpUrl` varchar(255) default NULL,
  `staticHtmlFolder` varchar(255) default NULL,
  `refuseStaticHtml` bit(1) default NULL,
  `showOnIndex` bit(1) default NULL,
  `lengthShowOnIndex` int(11) default NULL,
  `numberShowOnIndex` int(11) default NULL,
  `soulOnIndex` bit(1) default NULL,
  `formatOnIndex` longtext,
  `showOnParent` bit(1) default NULL,
  `lengthShowOnParent` int(11) default NULL,
  `numberShowOnParent` int(11) default NULL,
  `soulOnParent` bit(1) default NULL,
  `formatOnParent` longtext,
  `compulsionDocStyle` bit(1) default NULL,
  `articlePassMode` int(11) default NULL,
  `numberAppearRestrict` int(11) default NULL,
  `numberList` int(11) default NULL,
  `commentMode` int(11) default NULL,
  `privateHtml` longtext,
  `views` bigint(20) default NULL,
  `parentGroupID` int(11) default NULL,
  `styleID` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK4F5FEFEA3C44745` (`parentGroupID`),
  KEY `FK4F5FEFE7C4A4A49` (`styleID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "site_article_thread"
#

DROP TABLE IF EXISTS `site_article_thread`;
CREATE TABLE `site_article_thread` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(100) default NULL,
  `eyeCatching` bit(1) default NULL,
  `topOne` bit(1) default NULL,
  `accessionalTitle` varchar(100) default NULL,
  `pensileTitle` varchar(100) default NULL,
  `shortTitle` varchar(100) default NULL,
  `location` int(11) default NULL,
  `state` bit(1) default NULL,
  `pollAllow` bit(1) default NULL,
  `proponents` bigint(20) default NULL,
  `opponents` bigint(20) default NULL,
  `neutrals` bigint(20) default NULL,
  `comment` bit(1) default NULL,
  `soul` bit(1) default NULL,
  `notice` bit(1) default NULL,
  `byEditor` bit(1) default NULL,
  `noticeShowBody` bit(1) default NULL,
  `views` int(11) default NULL,
  `price` int(11) default NULL,
  `synopsis` varchar(255) default NULL,
  `author` varchar(50) default NULL,
  `authorDept` varchar(100) default NULL,
  `authorEmail` varchar(50) default NULL,
  `authorUrl` varchar(255) default NULL,
  `addTime` datetime default NULL,
  `addTimeLong` bigint(20) default NULL,
  `lastEditTime` datetime default NULL,
  `lastEditTimeLong` bigint(20) default NULL,
  `lastViewIp` varchar(64) default NULL,
  `lastViewTime` datetime default NULL,
  `lastViewTimeLong` bigint(20) default NULL,
  `member` varchar(30) default NULL,
  `passer` varchar(30) default NULL,
  `passingTime` datetime default NULL,
  `passingTimeLong` bigint(20) default NULL,
  `mainImg` varchar(255) default NULL,
  `thumbnail` varchar(255) default NULL,
  `mainImgExplain` varchar(255) default NULL,
  `journal` varchar(100) default NULL,
  `linkTitle` varchar(255) default NULL,
  `linkUrl` varchar(255) default NULL,
  `linkJump` bit(1) default NULL,
  `mentor` varchar(100) default NULL,
  `body` longtext,
  `htmlCreated` bit(1) default NULL,
  `htmlUrlPath` varchar(255) default NULL,
  `htmlURLFile` varchar(255) default NULL,
  `encryptedParmStr` varchar(64) default NULL,
  `defaultAttaMediaFormat` int(11) default NULL,
  `attaLineOrderFormatStr` varchar(255) default NULL,
  `articleGroupID` int(11) default NULL,
  `userID` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKAF6D156B44AB5579` (`articleGroupID`),
  KEY `FKAF6D156B60BD5DE5` (`userID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "site_info"
#

DROP TABLE IF EXISTS `site_info`;
CREATE TABLE `site_info` (
  `id` int(11) NOT NULL,
  `fullSiteName` varchar(50) default NULL,
  `shortSiteName` varchar(20) default NULL,
  `state` bit(1) default NULL,
  `sessionKey` varchar(64) default NULL,
  `keyWords` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `host` varchar(50) default NULL,
  `closeAnnounce` varchar(255) default NULL,
  `hostVisitAllow` varchar(255) default NULL,
  `welcomeStr` varchar(255) default NULL,
  `spaceName` varchar(30) default NULL,
  `bbsName` varchar(30) default NULL,
  `bbsState` bit(1) default NULL,
  `spaceState` bit(1) default NULL,
  `staticHtmlMode` int(11) default NULL,
  `mailTitleFromSite` varchar(50) default NULL,
  `mailBodyForReg` longtext,
  `mailBodyForQaAdd` longtext,
  `mailBodyForQaReply` longtext,
  `mailSmtpServer` varchar(30) default NULL,
  `mailSmtpUser` varchar(20) default NULL,
  `mailSmtpPws` varchar(30) default NULL,
  `mailSenderAddr` varchar(50) default NULL,
  `fileUploadExtName` varchar(255) default NULL,
  `filterWords` varchar(255) default NULL,
  `userRegAllow` bit(1) default NULL,
  `actAfterReg` int(11) default NULL,
  `userGroupOfAutoPassed` int(11) default NULL,
  `oneMailForReg` bit(1) default NULL,
  `userLoginAllow` bit(1) default NULL,
  `articleAutoPass` bit(1) default NULL,
  `userGroupWhenNotLoginAllow` varchar(255) default NULL,
  `modeOfComment` int(11) default NULL,
  `userGroupsForStat` varchar(255) default NULL,
  `licenseAgreement` longtext,
  `commentsOpen` bit(1) default NULL,
  `views` bigint(20) default NULL,
  `nviews` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "site_style"
#

DROP TABLE IF EXISTS `site_style`;
CREATE TABLE `site_style` (
  `id` int(11) NOT NULL auto_increment,
  `styleName` varchar(50) default NULL,
  `author` varchar(50) default NULL,
  `description` varchar(255) default NULL,
  `state` bit(1) default NULL,
  `def` bit(1) default NULL,
  `rootResFolder` varchar(255) default NULL,
  `locationSplitStr` varchar(10) default NULL,
  `hrefLineFormatStrOverAll` longtext,
  `hrefLineFormatWithSnStrOverAll` longtext,
  `mediaPlayCode1` longtext,
  `mediaPlayCode2` longtext,
  `mediaPlayCode3` longtext,
  `mediaPlayOuterLayerCodeForArtPage` longtext,
  `mediaPlayOuterLayerCodeForAttaPage` longtext,
  `attachmentLineShowForImg` longtext,
  `attachmentLineShowForDownload` longtext,
  `attachmentLineShowForPlay` longtext,
  `lineBlockFormat` varchar(255) default NULL,
  `columnBlockFormat` varchar(255) default NULL,
  `publicCode1` longtext,
  `publicCode2` longtext,
  `publicCode3` longtext,
  `publicCode4` longtext,
  `customFormatCode1` longtext,
  `customFormatCode2` longtext,
  `customFormatCode3` longtext,
  `customFormatCode4` longtext,
  `customFormatCode5` longtext,
  `customFormatCode6` longtext,
  `customFormatCode7` longtext,
  `customFormatCode8` longtext,
  `ajaxStrFormat` varchar(255) default NULL,
  `resultPageCode` longtext,
  `lastArticleForwardCode` varchar(255) default NULL,
  `nextArticleForwardCode` varchar(255) default NULL,
  `eyeCatchingCode` varchar(255) default NULL,
  `passedStr` longtext,
  `noPassedStr` longtext,
  `noPassedAltStr` varchar(255) default NULL,
  `threadAjaxShowStr` varchar(255) default NULL,
  `ajaxOfArticlePrice` text,
  `ajaxOfBeforeView` varchar(255) default NULL,
  `ajaxOfArtPoll` text,
  `statCode` longtext,
  `pid` int(11) default NULL,
  `iid` int(11) default NULL,
  `cid` int(11) default NULL,
  `tid` int(11) default NULL,
  `rid` int(11) default NULL,
  `lid` int(11) default NULL,
  `aid` int(11) default NULL,
  `eid` int(11) default NULL,
  `sid` int(11) default NULL,
  `gid` int(11) default NULL,
  `uid` int(11) default NULL,
  `mid` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK26948EB97B7A1F19` (`uid`),
  KEY `FK26948EB97B7A1797` (`sid`),
  KEY `FK26948EB97B79FD50` (`lid`),
  KEY `FK26948EB97B79F20D` (`iid`),
  KEY `FK26948EB97B7A13D6` (`rid`),
  KEY `FK26948EB97B7A0C54` (`pid`),
  KEY `FK26948EB97B7A0111` (`mid`),
  KEY `FK26948EB97B7A1B58` (`tid`),
  KEY `FK26948EB97B79EA8B` (`gid`),
  KEY `FK26948EB97B79DB87` (`cid`),
  KEY `FK26948EB97B79D405` (`aid`),
  KEY `FK26948EB97B79E309` (`eid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "site_style_element"
#

DROP TABLE IF EXISTS `site_style_element`;
CREATE TABLE `site_style_element` (
  `id` int(11) NOT NULL auto_increment,
  `htmlTemplate` longtext,
  `cssCode` longtext,
  `htmlCode` longtext,
  `topCode` longtext,
  `footerCode` longtext,
  `targetStr` varchar(255) default NULL,
  `titleFormat` varchar(255) default NULL,
  `hrefLineFormat` longtext,
  `memberPanelCodeForLogIn` longtext,
  `memberPanelCodeForLogOut` longtext,
  `dateFormatOnLine` varchar(255) default NULL,
  `dateFormatInText` varchar(255) default NULL,
  `majorLoopCodeInLump` longtext,
  `minorLoopCodeInLump` longtext,
  `editAreaCode` longtext,
  `searchAreaCode` longtext,
  `specialCode1` longtext,
  `specialCode2` longtext,
  `specialCode3` longtext,
  `specialCode4` longtext,
  `formTemplate` longtext,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "sys_admin"
#

DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `salt` varchar(50) default NULL,
  `lastLoginIP` varchar(15) default NULL,
  `lastLoginDatetime` datetime default NULL,
  `state` bit(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "sys_chinese_character_unicode"
#

DROP TABLE IF EXISTS `sys_chinese_character_unicode`;
CREATE TABLE `sys_chinese_character_unicode` (
  `id` int(11) NOT NULL,
  `cc` char(1) default NULL,
  `unicode` varchar(4) default NULL,
  `strokes` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "sys_interconnection"
#

DROP TABLE IF EXISTS `sys_interconnection`;
CREATE TABLE `sys_interconnection` (
  `id` bigint(20) NOT NULL auto_increment,
  `type` int(11) default NULL,
  `openID` varchar(64) default NULL,
  `createTimstamp` datetime default NULL,
  `uid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK624A5B489293F00F` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "sys_passers"
#

DROP TABLE IF EXISTS `sys_passers`;
CREATE TABLE `sys_passers` (
  `id` bigint(20) NOT NULL auto_increment,
  `passerInf1` varchar(255) default NULL,
  `lockPasserInf1` bit(1) default NULL,
  `passerInf2` varchar(255) default NULL,
  `lockPasserInf2` bit(1) default NULL,
  `state` bit(1) default NULL,
  `question1` varchar(255) default NULL,
  `question2` varchar(255) default NULL,
  `answer1` varchar(255) default NULL,
  `answer2` varchar(255) default NULL,
  `priTag1` varchar(255) default NULL,
  `questionForPriTag1` varchar(255) default NULL,
  `lockPriTag1` bit(1) default NULL,
  `priTag2` varchar(255) default NULL,
  `questionForPriTag2` varchar(255) default NULL,
  `lockPriTag2` bit(1) default NULL,
  `userGroupID` int(11) default NULL,
  `userID` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK518AA84360BD5DE5` (`userID`),
  KEY `FK518AA8437F6303AF` (`userGroupID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "sys_user_arts_count"
#

DROP TABLE IF EXISTS `sys_user_arts_count`;
CREATE TABLE `sys_user_arts_count` (
  `id` bigint(20) NOT NULL auto_increment,
  `timeKey` int(11) default NULL,
  `artGids` varchar(255) default NULL,
  `articleThreadsPassed` bigint(20) default NULL,
  `articleThreadsCount` bigint(20) default NULL,
  `uid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKA1087A029293F00F` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "sys_user_integral"
#

DROP TABLE IF EXISTS `sys_user_integral`;
CREATE TABLE `sys_user_integral` (
  `id` bigint(20) NOT NULL auto_increment,
  `type` int(11) default NULL,
  `tagId` bigint(20) default NULL,
  `recTime` datetime default NULL,
  `value` int(11) default NULL,
  `uid` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2E1D55AE9293F00F` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "sys_usergroups"
#

DROP TABLE IF EXISTS `sys_usergroups`;
CREATE TABLE `sys_usergroups` (
  `gid` int(11) NOT NULL auto_increment,
  `groupName` varchar(20) NOT NULL,
  `powerMask` longtext,
  `siteArticleGroupsSelectCustomStr` longtext,
  `state` bit(1) default NULL,
  `userCount` int(11) default NULL,
  `numberAppearRestrict` bit(1) default NULL,
  `privateHtml` longtext,
  PRIMARY KEY  (`gid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "sys_userinf"
#

DROP TABLE IF EXISTS `sys_userinf`;
CREATE TABLE `sys_userinf` (
  `userID` bigint(20) NOT NULL,
  `regTimstamp` datetime default NULL,
  `regIp` varchar(255) default NULL,
  `trueName` varchar(255) default NULL,
  `petName` varchar(255) default NULL,
  `sex` bit(1) default NULL,
  `birthday` date default NULL,
  `dept` varchar(255) default NULL,
  `country` varchar(255) default NULL,
  `province` varchar(255) default NULL,
  `city` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `mobile` varchar(255) default NULL,
  `qq` varchar(255) default NULL,
  `msn` varchar(255) default NULL,
  `personalSignature` varchar(255) default NULL,
  `question1` varchar(255) default NULL,
  `answer1` varchar(255) default NULL,
  `question2` varchar(255) default NULL,
  `answer2` varchar(255) default NULL,
  `allScore` int(11) default NULL,
  `bbsScore` int(11) default NULL,
  `priTag1` varchar(255) default NULL,
  `priTag2` varchar(255) default NULL,
  PRIMARY KEY  (`userID`),
  KEY `FK77FB5CC460BD5DE5` (`userID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "sys_users"
#

DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` bigint(20) NOT NULL auto_increment,
  `userName` varchar(20) NOT NULL,
  `remName` varchar(20) default NULL,
  `password` varchar(32) NOT NULL,
  `salt` varchar(6) NOT NULL,
  `state` bit(1) default NULL,
  `passingTime` datetime default NULL,
  `album` bit(1) default NULL,
  `email` varchar(255) NOT NULL,
  `avatarFile` varchar(255) default NULL,
  `avatarFileLock` bit(1) default NULL,
  `lastLoginTimstamp` datetime default NULL,
  `lastLoginIp` varchar(255) default NULL,
  `regTimstamp` datetime default NULL,
  `regIp` varchar(255) default NULL,
  `uuid` varchar(32) default NULL,
  `articleThreadsPassed` bigint(20) default NULL,
  `articleThreadsCount` bigint(20) default NULL,
  `spaceState` int(11) default NULL,
  `groupID` int(11) default NULL,
  `passerID` bigint(20) default NULL,
  `postToPasserID` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK205BA21698F20DA6` (`postToPasserID`),
  KEY `FK205BA216DB13C1D8` (`passerID`),
  KEY `FK205BA216F499B9DA` (`groupID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "vote_items"
#

DROP TABLE IF EXISTS `vote_items`;
CREATE TABLE `vote_items` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(50) NOT NULL,
  `unicodeOrder` varchar(255) default NULL,
  `body` varchar(255) default NULL,
  `recNum` bigint(20) default NULL,
  `state` bit(1) default NULL,
  `addTime` datetime default NULL,
  `addIp` varchar(64) default NULL,
  `item1` varchar(255) default NULL,
  `item2` varchar(255) default NULL,
  `item3` varchar(255) default NULL,
  `item4` varchar(255) default NULL,
  `item5` varchar(255) default NULL,
  `item6` varchar(255) default NULL,
  `item7` varchar(255) default NULL,
  `item8` varchar(255) default NULL,
  `item9` varchar(255) default NULL,
  `item10` varchar(255) default NULL,
  `item11` varchar(255) default NULL,
  `item12` varchar(255) default NULL,
  `item13` varchar(255) default NULL,
  `item14` varchar(255) default NULL,
  `item15` varchar(255) default NULL,
  `item16` varchar(255) default NULL,
  `item17` varchar(255) default NULL,
  `item18` varchar(255) default NULL,
  `item19` varchar(255) default NULL,
  `item20` varchar(255) default NULL,
  `subID` int(11) default NULL,
  `userID` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK7C7E462B4166D6CA` (`subID`),
  KEY `FK7C7E462B60BD5DE5` (`userID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "vote_recs"
#

DROP TABLE IF EXISTS `vote_recs`;
CREATE TABLE `vote_recs` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `addIp` varchar(64) default NULL,
  `addTime` datetime default NULL,
  `identity` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `occ` varchar(255) default NULL,
  `itemsRec` longtext,
  `mes` longtext,
  `mesState` bit(1) default NULL,
  `email` varchar(255) default NULL,
  `state` bit(1) default NULL,
  `salt` varchar(32) default NULL,
  `recCount` int(11) default NULL,
  `subID` int(11) default NULL,
  `userID` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC4A02184166D6CA` (`subID`),
  KEY `FKC4A021860BD5DE5` (`userID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "vote_style"
#

DROP TABLE IF EXISTS `vote_style`;
CREATE TABLE `vote_style` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(50) default NULL,
  `state` bit(1) default NULL,
  `author` varchar(50) default NULL,
  `description` varchar(255) default NULL,
  `barMaxValue` varchar(4) default NULL,
  `resultPageCode` longtext,
  `publicCode1` longtext,
  `publicCode2` longtext,
  `publicCode3` longtext,
  `publicCode4` longtext,
  `pid` int(11) default NULL,
  `vid` int(11) default NULL,
  `iid` int(11) default NULL,
  `rid` int(11) default NULL,
  `jid` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK7D0B7C1CD5CDEF07` (`iid`),
  KEY `FK7D0B7C1CD5CE10D0` (`rid`),
  KEY `FK7D0B7C1CD5CE094E` (`pid`),
  KEY `FK7D0B7C1CD5CDF2C8` (`jid`),
  KEY `FK7D0B7C1CD5CE1FD4` (`vid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "vote_style_element"
#

DROP TABLE IF EXISTS `vote_style_element`;
CREATE TABLE `vote_style_element` (
  `id` int(11) NOT NULL auto_increment,
  `htmlTemplate` longtext,
  `cssCode` longtext,
  `htmlCode` longtext,
  `topCode` longtext,
  `footerCode` longtext,
  `memberPanelCodeForLogIn` longtext,
  `memberPanelCodeForLogOut` longtext,
  `itemsLoopFormat` longtext,
  `lineCodeFormat` longtext,
  `titleFormat` varchar(255) default NULL,
  `hrefLineFormat` longtext,
  `specialCode1` longtext,
  `specialCode2` longtext,
  `specialCode3` longtext,
  `specialCode4` longtext,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "vote_subs"
#

DROP TABLE IF EXISTS `vote_subs`;
CREATE TABLE `vote_subs` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(50) NOT NULL,
  `secStr` varchar(32) default NULL,
  `createTime` datetime default NULL,
  `signStartTime` datetime default NULL,
  `signEndTime` datetime default NULL,
  `voteStartTime` datetime default NULL,
  `voteEndTime` datetime default NULL,
  `upperLimit` int(11) default NULL,
  `fullUpperConstraint` bit(1) default NULL,
  `ipArea` varchar(255) default NULL,
  `hoursAtIpRule` int(11) default NULL,
  `hoursAtMachineRule` int(11) default NULL,
  `useVerifyCode` bit(1) default NULL,
  `identityRule` bit(1) default NULL,
  `phoneCodeRule` bit(1) default NULL,
  `posterNameCCUChk` bit(1) default NULL,
  `state` bit(1) default NULL,
  `openResult` bit(1) default NULL,
  `netJoin` bit(1) default NULL,
  `netJoinMustBeMember` bit(1) default NULL,
  `netJoinAutoPassed` bit(1) default NULL,
  `mesAutoPassed` bit(1) default NULL,
  `salt` varchar(6) default NULL,
  `votePassword` varchar(255) default NULL,
  `orderType` int(11) default NULL,
  `percent` int(11) default NULL,
  `styleID` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC4AB268E3C771C3` (`styleID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
