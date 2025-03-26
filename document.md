# 甄选优选项目

## 项目介绍

是一个商业模式为B2C类型的电子商务平台。

## 业务功能介绍

**后台系统功能**:

- 用户登录
- 菜单管理
- 角色管理
- 权限规则管理
- 商品管理
- 商品分类
- 商品规格

线上地址：http://xxx.purepick-backend.com

账号/密码：admin/admin

**前台系统功能**:

- 首页商品分类查询
- 分类模块：分类查询、根据分类查询商品数据
- 登录功能
- 用户收货地址管理
- 购物车模块
- 订单模块

线上地址：http://xxx.purepick-front.com

## 技术栈版本

| SpringBoot | SpringCloud | SpringCloudAlibaba | Nacos  | Sentinel | MySQL  | MyBatis | PageHelper | Knife4j | EasyExcel | Minio  | Hutool |
|:-----------|:-----------:|-------------------:|--------|----------|--------|---------|------------|---------|-----------|--------|--------|
| 3.2.4      |  2023.0.1   |         2023.0.1.0 | v2.3.2 | 1.8.6    | 8.0.29 | 3.0.3   | 1.4.6      | 4.4.0   | 4.0.2     | 8.5.10 | 5.8.25 |

## 技术栈

- Spring Boot
- Spring Cloud
    - Gateway
    - Openfeign
- Spring Cloud Alibaba
    - Nacos
    - Sentinel
- flyway
- 持久层
    - MySQL
    - MyBatis
    - PageHelper
- 缓存
    - Redis
    - Spring Cache
- Nginx
- Knife4j
- EasyExcel
- Minio
- Docker
- 阿里云
    - 阿里云短信服务
- 支付宝
    - 支付宝sdk
- 工具类
    - Hutool
    - fastjson
    - gson

## 服务端口占用

| manager | gateway | product | user | cart |
|:--------|:-------:|--------:|------|------|
| 8000    |  8500   |    8100 | 8200 | 8300 |
