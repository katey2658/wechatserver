

-- 用户登录表
create table customer_login(
  customer_id bigint unsigned AUTO_INCREAMENT  NOT NULL comment '用户ID',
  login_name varchar(20) not null comment '用户登录名',
  password char(32) not null comment 'MD5加密的密码',
  user_stats tinyint not null default 1 comment '用户状态',
  modified_time timestamp not null default CURRENT_TIMESTAMP
  on update  CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_customerid(customer_id)
)engine=innodb comment='用户登录表';

-- 用户信息表
create table customer_info(
  customer_inf_id int unsigned AUTO_INCREAMENT not null comment '自增主键ID',
  customer_id int unsigned not null comment 'customer_login 表的自增ID',
  customer_name varchar(20) not null comment '用户真实姓名',
  identify_card_type tinyint  not null default 1 comment '证件类型：1 身份证  2军官证 3护照',
  identify_card_no varchar(20) comment '证件号码',
  mobile_phone int unsigned comment '手机号',
  customer_email varchar(50) comment '邮箱',
  gender char(1) comment '性别',
  user_point int not null default 0 comment '用户积分',
  register_time timestamp not null comment '注册时间',
  birthday datetime comment '会员生日',
  customer_level tinyint not null default 1 comment '会员级别：1普通会员 2青铜会员 3白银会员 4黄金会员 5钻石会员',
  user_money decimal(8,2) not null default 0.00 comment '用户余额',
  modified_time timestamp not null default CURRENT_TIMESTAMP
  on update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_customerinfid(customer_inf_id)
)engine=innodb comment='用户信息表';

-- 用户级别表
create table customer_level_info(
  customer_level tinyint not null AUTO_INCREAMENT comment '会员级别ID',
  level_name varchar(10) not null comment '会员级别名称',
  min_point int unsigned not null default 0 comment '该级别最低分',
  max_point int unsigned not null default 0 comment '该级别最高分',
  modified_time timestamp not null default CURRENT_TIMESTAMP
  on update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_levelid(customer_level)
)engine= innodb comment='用户级别信息表';



-- 用户积分日志表
create table cutomer_point_log(
  point_id int unsigned not null AUTO_INCREAMENT comment '积分日志ID',
  customer_id int unsigned not null comment '用户Id',
  source tinyint unsigned not null comment '积分来源:0订单 1登录 2活动',
  refer_number int unsigned not null default 0 comment '积分来源相关编号',
  change_point smallint not null default 0 comment '变更积分数',
  create_time timestamp not null  comment '积分日志生成时间',
  primary key pk_pointid(point_id)
)engine=innodb comment='用户积分日志表';



-- 用户余额变动表
create table customer_balance_log(
  balance_id int unsigned not null  AUTO_INCREAMENT comment '余额日志id',
  customer_id int unsigned not null comment '用户ID',
  source tinyint unsigned not  null default 1 comment '记录来源：1订单，2退订货',
  source_sn int unsigned not null comment '相关单据ID',
  create_time timestamp not null default CURRENT_TIMESTAMP comment '记录生成时间',
  amount decimal(8,2)not null default 0.00 comment '变动金额',
  primary key pk_balanceid(balance_id)
)engine=innodb comment ='用户余额变动表';

-- 用户登录日志表
create table customer_login_log(
  login_id int unsigned not null AUTO_INCREAMENT comment '登录日志Id',
  customer_id int unsigned not null comment '登录用户Id',
  login_time timestamp not null comment '用户登录时间',
  login_ip int unsigned not null comment '登录Ip',
  login_type tinyint not null comment '登录类型：0未成功 1成功',
  primary key pk_loginid(login_id)
)engine=innodb comment='用户登录日志表'
partition by range(year(login_time))(
  partition p0 values less than (2015),
  partition p1 values less than (2016),
  partition p2 values less than (2017)
);

-- 增加分区
alter table customer_login_log add partition(partition p4 less than(2018));
-- 删除过时数据
alter table customer_login_log drop partition p0;
-- 过期数据归档
-- 建以一个一样的表 arch_customer_login_log，但不分区
-- 一般迁移后就删除
alter  table customer_login_log exchange partition p1 with table arch_customer_login_log;
alter table arch_customer_login_log engine=archive;





-- 品牌信息表
creat table brand_info(
  brand_id smallint unsigned AUTO_INCREAMENT not null  comment '品牌',
  brand_name varchar(50) not null comment '品牌名称',
  telephone varchar(50) not null comment '联系电话',
  brand_web varchar(100) comment '品牌网站',
  brand_logo varchar (100) comment '品牌logo url',
  brand_desc varchar(150) comment '品牌描述',
  brand_status tinyint not null default 0 comment '品牌状态：0禁止 1启用',
  brand_order tinyint not null default 0 comment '排序',
  modified_time timestamp not null default CURRENT_TIMESTAMP
   on update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_brandid (brand_id)
)engine=innodb comment ='品牌信息表';

-- 分类信息表
create table product_category(
  category_id smallint unsigned AUTO_INCREAMENT not null  comment '分类Id',
  category_name  varchar(10) not null comment '分类名称',
  category_code varchar(10) not null comment '分类编码',
  parent_id smallint unsigned not null default 0 comment '父分类Id',
  category_level tinyint not null default 1 comment '分类层级',
  category_status tinyint not null default 1 comment '分类状态',
  modified_time timestamp not null default CURRENT_TIMESTAMP
   on update CURRENT_TIMESTAMP comment '最后修改时间',
   primary key pk_categoryid(category_id)
)engine=innodb comment='商品分类表';


-- 供应商信息表
creat table supplier_info(
  supplier_id unsigned AUTO_INCREAMENT not null comment '供应商 ID',
  supplier_code char(8) not null comment '供应商编码',
  supplier_name char(50) not null comment '供应商名称',
  supplier_type tinyint not null  comment '供应商类型：1.自营 2.平台',
  link_man varchar(10) not null comment '供应商联系人',
  phone_number varchar(50) not null comment '联系电话',
  bank_name varchar(50) not null comment '供应商开户银行名称',
  bank_acount varchar(50)  not null comment '银行账号',
  address varchar(50) not null comment '供应商地址',
  supplier_status tinyint not null default 0 comment '状态：0禁用 1启用',
  modified_time timestamp not null default CURRENT_TIMESTAMP
  on update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_supplierid(supplier_id)
)engine=innodb comment='供应商信息表';

-- 商品信息表
create table product_info(
  product_id int unsigned AUTO_INCREAMENT not null comment '商品ID',
  product_code char(16) not null comment '商品编码',
  product_name varchar(20) not null  comment '商品名称',
  bar_code varchar(50) not null comment '国条码',
  brand_id int unsigned not null comment '品牌表的Id',
  one_category_id smallint unsigned not null comment '一级分类Id',
  two_category_id smallint unsigned not null comment '二级分类Id',
  three_category_id smallint unsigned not null comment '三级分类Id',
  supplier_id int unsigned not null comment '商品的供应商Id',
  price decimal(8,2) not null comment '商品销售价格',
  average_cost decimal(18,2) not null comment '商品加权平均成本',
  publish_status tinyint not null default 0 comment '上下架状态：0下架 1上架',
  audit_status tinyint not null default 0 comment  '审核状态：0未审核 1已审核',
  weight float comment '商品重量',
  length float comment '商品长度',
  height float comment '商品高度',
  width float comment '商品宽度',
  color_type enum('红','黄','蓝','黑'),
  production_data datetime  not null comment '生产日期',
  shelf_life int not null  comment '商品有效期',
  descript text not null  comment '商品描述',
  in_date  timestamp not null default CURRENT_TIMESTAMP comment '商品录入时间',
  modified_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_productid(product_id)
)engine=innodb comment='商品信息表';

-- 商品图片表
create table product_pic_info(
  product_pic_id int unsigned AUTO_INCREAMENT not null comment '商品图片Id',
  product_id int unsigned not null comment '商品Id',
  pic_desc varchar(50)  comment '图片描述',
  pic_url varchar(200) not null comment '图片Url',
  master_default tinyint not null default 0 comment '是否主图：0.非主图 1.主图',
  pic_order tinyint not null default 0 comment '图片排序',
  pic_status tinyint not null default 1 comment '图片是否有效：0无效 1有效',
  modified_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_picid(product_pic_id)
)engine =innodb comment='商品图片信息表';

-- 商品评论表
create table product_comment(
  comment_id bigint unsigned AUTO_INCREAMENT not null comment '评论Id',
  product_id int unsigned not null comment '商品Id',
  order_id bigint unsigned not null comment '订单Id',
  customer_id int unsigned not null comment '用户Id',
  title varchar(50) not null  comment '评论标题',
  content varchar(300) not null comment '评论内容',
  audit_status tinyint not null comment '审核状态：0未审核 1已审核',
  audit_time timestamp not null comment '评论时间',
  modified_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_commentid(comment_id)
)engine=innodb comment='商品评论表';

-- 订单主表
creat table order_master(
  order_id bigint unsigned not null  AUTO_INCREAMENT comment '订单ID',
  order_sn bigint unsigned not null comment '订单编号 yyyymmddnnnnnnnn',
  customer_id int unsigned not null comment '下单人ID',
  shipping_user varchar(10) not null comment '收货人姓名',
  province smallint not null comment '省',
  city smallint not null comment '市',
  district smallint not null comment '区',
  address varchar(100) not null comment '地址',
  payment_method tinyint not null comment '支付方式：1现金 2余额 3网银 4支付宝 5微信',
  order_money decimal (8,2) not null comment '订单金额',
  district_money decimal(8,2) not null default 0.00 comment '优惠金额',
  shipping_money decimal(8,2) not null default 0.00 comment '运费金额',
  payment_money decimal(8,2) not null default 0.00 comment '支付金额',
  shipping_comp_name varchar(10) comment '快递公司名称',
  shipping_sn varchar(50) comment '快递单号',
  creat_time datetime not null default CURRENT_TIMESTAMP comment '下单时间',
  shipping_time datetime comment '发货时间',
  pay_time datetime comment '支付时间',
  receive_time datetime comment '收货时间',
  order_status tinyint not null default 0 comment '订单状态',
  order_point int unsigned not null default 0 comment '订单积分',
  invoice_title varchar(100) comment '发票抬头',
  modified_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_orderid(order_id)
)engine=innodb comment='订单主表';

-- 定单详情表
create table  order_detail(
  order_detail_id bigint unsigned not null AUTO_INCREAMENT  comment '自增主键Id,订单详情表id',
  order_id bigint unsigned not null  comment '订单表Id',
  product_id int unsigned not null comment '订单商品Id',
  product_name varchar(50) not null comment '商品名称',
  product_num int not null default 1 comment '购买商品数量',
  product_price decimal(8,2) not null comment '购买商品单价',
  averave_cost decimal(8,2) not null default 0.00 comment '平均成本价格',
  weight float comment '商品重量',
  fee_money  decimal(8,2)not null default 0.00 comment '优惠分摊金额',
  warehouse_id int unsigned not null comment '仓库Id',
  modified_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_orderdetailid(order_detail)
)engine=innodb comment='定单详情表';

-- 购物车表
create table order_cart(
  cart_id int unsigned not null AUTO_INCREAMENT comment '购物车ID',
  customer_id int unsigned not null comment '用户Id',
  product_id int unsigned not null comment '商品Id',
  product_amount int not null comment '加入购物车商品数量',
  price decimal(8,2)not null comment '商品价格',
  add_time timestamp not null default CURRENT_TIMESTAMP comment '加入购车时间',
  modified_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_cartid(cart_id)
)engine=innodb comment='购物车表';

-- 仓库信息表
creat table warehouse_info(
  warehouse_id smallint unsigned not null AUTO_INCREAMENT comment '仓库Id',
  warehouse_sn char(5) not null comment '仓库编码',
  warehouse_name varchar(10) not null comment '仓库名称',
  warehouse_phone varchar(20) not null comment '仓库联系人',
  province smallint not null comment '省',
  city smallint not null comment '市',
  district smallint not null comment '区',
  address varchar(100) not null comment '仓库地址',
  warehouse_status tinyint not null default 1 comment '仓库状态：0禁用 1 启用',
  modified_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_wid(w_id)
)engine=innodb comment='仓库信息表';


-- 商品库存表
create table warehouse_product(
  warehouse_product_id int unsigned not null AUTO_INCREAMENT comment '商品库存Id',
  product_id int unsigned not null comment '商品Id',
  warehouse_id smallint unsigned not null comment '仓库Id',
  current_num int unsigned not null default 0 comment '当前商品库存',
  lock_num int unsigned not null default 0 comment '当前占用数量',
  in_transit_num int unsigned not null default 0 comment '在途数据',
  average_cost decimal(8,2) not null default 0.00 comment '移动加权成本',
  modified_time timestamp not null default CURRENT_TIMESTAMP ont update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_wpid(wp_id)
)engine=innodb comment='商品库存表';

-- 物流公司信息表
create table shipping_info(
  ship_id tinyint unsigned not null AUTO_INCREAMENT comment '主键id',
  ship_name varchar(20) not null comment '物流公司名称',
  ship_contact varchar(20) not null comment '物流公司联系人',
  telephone varchar (20) not null comment '物流公司联系电话',
  price decimal(8,2) not null default 0.00 comment  '配送价格',
  modified_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_shipid(ship_id)
)engine =innodb comment='物流公司信息表';

-- 快递信息表
create table customer_addr(
  customer_addr_id int unsigned AUTO_INCREAMENT not null comment '自增主键Id',
  customer_id int unsigned not null comment 'customer_login 的自增Id',
  zip_code smallint not null comment '邮编',
  province smallint not null comment '地区表中省份的Id',
  city smallint not null comment '地区表中城市的id',
  district smallint not null comment '地区表中的区id',
  address varchar(200) not null comment '具体的地址门牌号',
  address_default  not null comment '是否默认',
  modified_time timestamp not null default CURRENT_TIMESTAMP
  on update CURRENT_TIMESTAMP comment '最后修改时间',
  primary key pk_customeraddid(customer_addr_id)
)engine=innodb comment='用户地址表';
