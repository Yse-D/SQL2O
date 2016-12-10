###SQL2O:Convert DB schema into java bean class


---

####Usage

```
new SQL2O.Builder()
                .dbType(DB.MYSQL)//数据库(MySQL/DB2)
                .sourceType(SOURCE.CONNECTION)//数据源(连接数据库/SQL文件)
                .info(new DBStarter.Info("127.0.0.1","3306","test","root",""))//连接数据库信息(host,port,dbname,username,password)
                .naming(NAMING.HUMP)//命名规则(与表字段相同/驼峰式)
                .typeMapper(new TypeMapper().defaultMapper())//数据库字段类型与Java数据类型映射器
                .outputPath("/bean")//Java文件输出路径
                .build()
                .start();
```

####sample
db schema:
```
CREATE TABLE `family` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `size` smallint(6) DEFAULT NULL COMMENT '家庭人数',
  `kid_size` smallint(6) DEFAULT NULL COMMENT '小孩数',
  `income` tinyint(4) DEFAULT NULL COMMENT '月收入－区间枚举值',
  `address` varchar(256) NOT NULL COMMENT '家庭地址',
  `telephone` varchar(16) DEFAULT NULL COMMENT '家庭电话',
  `postcode` varchar(6) DEFAULT NULL COMMENT '居住地邮编',
  `province_id` bigint(20) DEFAULT NULL COMMENT '家庭地址－省',
  `province_name` varchar(32) DEFAULT NULL COMMENT '省名称',
  `city_id` bigint(20) DEFAULT NULL COMMENT '家庭地址－市',
  `city_name` varchar(32) DEFAULT NULL COMMENT '市名称',
  `district_id` bigint(20) DEFAULT NULL COMMENT '家庭地址－区',
  `district_name` varchar(32) DEFAULT NULL COMMENT '区名称',
  `subdistrict_id` bigint(20) DEFAULT NULL COMMENT '家庭地址－街道',
  `subdistrict_name` varchar(64) DEFAULT NULL COMMENT '街道名称',
  `addr_detail` varchar(128) DEFAULT NULL COMMENT '家庭地址－详细地址部分',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '删除标志 -1:删除 0:正常',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='家庭表';
```

Java bean class:
```
public class Family                           //家庭表
{
	private  Long      id                     //主键ID
	private  Short     size                   //家庭人数
	private  Short     kidSize                //小孩数
	private  Byte      income                 //月收入－区间枚举值
	private  String    address                //家庭地址
	private  String    telephone              //家庭电话
	private  String    postcode               //居住地邮编
	private  Long      provinceId             //家庭地址－省
	private  String    provinceName           //省名称
	private  Long      cityId                 //家庭地址－市
	private  String    cityName               //市名称
	private  Long      districtId             //家庭地址－区
	private  String    districtName           //区名称
	private  Long      subdistrictId          //家庭地址－街道
	private  String    subdistrictName        //街道名称
	private  String    addrDetail             //家庭地址－详细地址部分
	private  Byte      isDeleted              //删除标志 -1:删除 0:正常
	private  Date      createdAt              //创建时间
	private  Date      updatedAt              //更新时间
}
```

