
## An example of how to use SQL2O generate a java back-end scaffolding


### database schema

```sql
# ************************************************************
# Host: 127.0.0.1 (MySQL 5.7.16)
# Database: blog
# Generation Time: 2017-01-04 06:56:24 +0000
# ************************************************************

# Dump of table admin
# ------------------------------------------------------------
DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nick` varchar(64) DEFAULT NULL COMMENT '昵称',
  `pwd` varchar(128) DEFAULT NULL COMMENT '密码',
  `group_id` bigint(20) DEFAULT NULL COMMENT '管理员组ID',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员员表';


# Dump of table admin_group
# ------------------------------------------------------------
DROP TABLE IF EXISTS `admin_group`;

CREATE TABLE `admin_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(512) DEFAULT NULL COMMENT '管理员组名称',
  `blog_category` tinyint(1) DEFAULT NULL COMMENT '是否有管理博客分类权限',
  `gallery_category` tinyint(1) DEFAULT NULL COMMENT '是否有管理画廊分类权限',
  `member_group` tinyint(1) DEFAULT NULL COMMENT '是否有管理会员组权限',
  `modify_member` tinyint(1) DEFAULT NULL COMMENT '是否有修改用户会员权限',
  `enter_member_back` tinyint(1) DEFAULT NULL COMMENT '是否可以进入用户后台',
  `post_notification` tinyint(1) DEFAULT NULL COMMENT '是否有发布通知权限',
  `add_model` tinyint(1) DEFAULT NULL COMMENT '是否有添加用户模板权限',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员组表';


# Dump of table blog
# ------------------------------------------------------------
DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(512) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '正文',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '作者ID',
  `recommend` tinyint(1) DEFAULT NULL COMMENT '是否被推荐',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客表';


# Dump of table comment
# ------------------------------------------------------------
DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` longtext COMMENT '评论内容',
  `blog_id` bigint(20) DEFAULT NULL COMMENT '评论的博客ID',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '评论作者',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';


# Dump of table gallery
# ------------------------------------------------------------
DROP TABLE IF EXISTS `gallery`;

CREATE TABLE `gallery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) DEFAULT NULL COMMENT '画廊名称',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '画廊作者ID',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='画廊表';


# Dump of table member
# ------------------------------------------------------------
DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nick` varchar(64) DEFAULT NULL COMMENT '昵称',
  `pwd` varchar(128) DEFAULT NULL COMMENT '密码',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像路径',
  `group_id` bigint(20) DEFAULT NULL COMMENT '会员组ID',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员表';


# Dump of table member_group
# ------------------------------------------------------------
DROP TABLE IF EXISTS `member_group`;

CREATE TABLE `member_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(512) DEFAULT NULL COMMENT '会员组名称',
  `upload_amount` int(11) DEFAULT NULL COMMENT '图片上传数量',
  `blog_amount` int(11) DEFAULT NULL COMMENT '单日博客量',
  `water_mark` tinyint(1) DEFAULT NULL COMMENT '水印权限',
  `encrypt` tinyint(1) DEFAULT NULL COMMENT '加密权限',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员组表';


# Dump of table notification
# ------------------------------------------------------------
DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(512) DEFAULT NULL COMMENT '通知标题',
  `content` longtext COMMENT '通知正文',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '发布通知的管理员ID',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知表';


# Dump of table picture
# ------------------------------------------------------------
DROP TABLE IF EXISTS `picture`;

CREATE TABLE `picture` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) DEFAULT NULL COMMENT '图片名称',
  `gallery_id` bigint(20) DEFAULT NULL COMMENT '画廊ID',
  `url` varchar(512) DEFAULT NULL COMMENT '图片路径',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片表';


# Dump of table point_detail
# ------------------------------------------------------------
DROP TABLE IF EXISTS `point_detail`;

CREATE TABLE `point_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '产生积分的会员ID',
  `amount` int(11) DEFAULT NULL COMMENT '产生的积分数量',
  `point_group_id` bigint(20) DEFAULT NULL COMMENT '所属会员组ID',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分明细表';


# Dump of table point_group
# ------------------------------------------------------------
DROP TABLE IF EXISTS `point_group`;

CREATE TABLE `point_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) DEFAULT NULL COMMENT '积分组名称',
  `action_name` varchar(256) DEFAULT NULL COMMENT '积分组动作名称',
  `action_code` varchar(256) DEFAULT NULL COMMENT '积分组动作码',
  `amount` int(11) DEFAULT NULL COMMENT '积分组规定积分数',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分组表';


# Dump of table sensitive_word
# ------------------------------------------------------------
DROP TABLE IF EXISTS `sensitive_word`;

CREATE TABLE `sensitive_word` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `word` varchar(512) DEFAULT NULL COMMENT '敏感词',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='敏感词表';

```

### run SQL2O

```java
new SQL2O.Builder()
                .dbType(DB.MYSQL)//database type : default MySQL
                .info(new DBStarter.Info("127.0.0.1", "3306", "blog", "root", ""))//MySQL database information : host,port,database,username,password
                .naming(NAMING.HUMP)//naming rule , default using camel-casing preferences
                .typeMapper(new TypeMapper().defaultMapper())//MySQL to Java data type mapper
                .projectName("blog")//project name : default 'project'
                .packageName("io.laudoak")//package name : default 'io.demo'
                .build()
                .start();
```

### generate scaffolding

```
.
├── pom.xml
└── src
    └── main
        ├── java
        │   └── io
        │       └── laudoak
        │           ├── blogApplication.java
        │           ├── dao
        │           │   ├── AdminDao.java
        │           │   ├── AdminGroupDao.java
        │           │   ├── BlogDao.java
        │           │   ├── CommentDao.java
        │           │   ├── Dao.java
        │           │   ├── GalleryDao.java
        │           │   ├── MemberDao.java
        │           │   ├── MemberGroupDao.java
        │           │   ├── NotificationDao.java
        │           │   ├── PictureDao.java
        │           │   ├── PointDetailDao.java
        │           │   ├── PointGroupDao.java
        │           │   └── SensitiveWordDao.java
        │           ├── model
        │           │   ├── Admin.java
        │           │   ├── AdminGroup.java
        │           │   ├── Blog.java
        │           │   ├── Comment.java
        │           │   ├── Gallery.java
        │           │   ├── Member.java
        │           │   ├── MemberGroup.java
        │           │   ├── Notification.java
        │           │   ├── Picture.java
        │           │   ├── PointDetail.java
        │           │   ├── PointGroup.java
        │           │   └── SensitiveWord.java
        │           └── service
        │               ├── AdminGroupService.java
        │               ├── AdminService.java
        │               ├── BlogService.java
        │               ├── CommentService.java
        │               ├── GalleryService.java
        │               ├── MemberGroupService.java
        │               ├── MemberService.java
        │               ├── NotificationService.java
        │               ├── PictureService.java
        │               ├── PointDetailService.java
        │               ├── PointGroupService.java
        │               ├── SensitiveWordService.java
        │               └── impl
        │                   ├── AdminGroupServiceImpl.java
        │                   ├── AdminServiceImpl.java
        │                   ├── BlogServiceImpl.java
        │                   ├── CommentServiceImpl.java
        │                   ├── GalleryServiceImpl.java
        │                   ├── MemberGroupServiceImpl.java
        │                   ├── MemberServiceImpl.java
        │                   ├── NotificationServiceImpl.java
        │                   ├── PictureServiceImpl.java
        │                   ├── PointDetailServiceImpl.java
        │                   ├── PointGroupServiceImpl.java
        │                   └── SensitiveWordServiceImpl.java
        └── resources
            ├── application.properties
            ├── mapper
            │   ├── AdminGroupMapper.xml
            │   ├── AdminMapper.xml
            │   ├── BlogMapper.xml
            │   ├── CommentMapper.xml
            │   ├── GalleryMapper.xml
            │   ├── MemberGroupMapper.xml
            │   ├── MemberMapper.xml
            │   ├── NotificationMapper.xml
            │   ├── PictureMapper.xml
            │   ├── PointDetailMapper.xml
            │   ├── PointGroupMapper.xml
            │   └── SensitiveWordMapper.xml
            └── mybatis-config.xml
```

### mvn compile

enter the root directory of project , type mvn compile command line and run

```
mvn compile
```

### open project and run

open project with IntelliJ IDEA and run Application
