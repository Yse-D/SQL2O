## SQL2O

这是一个根据 MySQL 数据库模式生成 Java 后端工程脚手架的小工具

### 脚手架的特性

 - 框架: Spring Boot,MyBatis的 Spring Boot 集成, Lombok 库精简代码

 - 项目构建和管理工具: Maven

 - 模型层,数据访问层和服务层三个分层

 - 对数据库增删改查的基础操作

### 生成的脚手架结构

```
.
├── pom.xml
└── src
    └── main
        ├── java
        │   └── io
        │       └── laudoak
        │           ├── mapper
        │           ├── model
        │           └── service
        │               └── impl
        └── resources
            ├── application.yml
            └── io
                └── laudoak
                    └── mapper
```

### 一个简单示例

 - 数据库模式:

```
# Dump of table blog
# ------------------------------------------------------------

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

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` longtext COMMENT '评论内容',
  `blog_id` bigint(20) DEFAULT NULL COMMENT '评论的博客ID',
  `owner_id` bigint(20) DEFAULT NULL COMMENT '评论作者',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';



# Dump of table member
# ------------------------------------------------------------

CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nick` varchar(64) DEFAULT NULL COMMENT '昵称',
  `pwd` varchar(128) DEFAULT NULL COMMENT '密码',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像路径',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员表';

```

 - 通过 Yaml 文件配置

 ```
 database:
   driverClassName: com.mysql.jdbc.Driver
   url: jdbc:mysql://localhost:3306/sample?useUnicode=true&characterEncoding=utf-8&useSSL=false
   username: root
   password:

 project:
   packageName: io.laudoak
   projectName: sample

 typeMapper:
   #Numeric Data Types:
   INT: Integer
   TINYINT: Byte
   SMALLINT: Short
   MEDIUMINT: Integer
   BIGINT: Long
   FLOAT: Float
   DOUBLE: Double
   DECIMAL: Double
   BIT: Byte
   #Date and Time Types:
   DATE: Date
   DATETIME: Date
   TIMESTAMP: Date
   TIME: Date
   YEAR: Date
   #String Types:
   CHAR: String
   VARCHAR: String
   BLOB: String
   TEXT: String
   TINYBLOB: String
   TINYTEXT: String
   MEDIUMBLOB: String
   MEDIUMTEXT: String
   LONGBLOB: String
   LONGTEXT: String
   ENUM: String
 ```

 - 运行 SQL2O :

 ```
 SQL2O.run();
 ```

 - 输出的脚手架

 ```
 .
 ├── pom.xml
 └── src
     └── main
         ├── java
         │   └── io
         │       └── laudoak
         │           ├── Application.java
         │           ├── mapper
         │           │   ├── BaseMapper.java
         │           │   ├── BlogMapper.java
         │           │   ├── CommentMapper.java
         │           │   └── MemberMapper.java
         │           ├── model
         │           │   ├── Blog.java
         │           │   ├── Comment.java
         │           │   └── Member.java
         │           └── service
         │               ├── BaseService.java
         │               ├── BlogService.java
         │               ├── CommentService.java
         │               ├── MemberService.java
         │               └── impl
         │                   ├── BlogServiceImpl.java
         │                   ├── CommentServiceImpl.java
         │                   └── MemberServiceImpl.java
         └── resources
             ├── application.yml
             └── io
                 └── laudoak
                     └── mapper
                         ├── BlogMapper.xml
                         ├── CommentMapper.xml
                         └── MemberMapper.xml
 ```

 - 编译和运行脚手架

 进入架手架项目根目录输入`mvn compile`编译脚手架项目,完成后在 IDEA 打开并运行 Application.java


