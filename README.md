# WMS(Warehouse Management System)

![Static Badge](https://img.shields.io/badge/SpringBoot%20v2.7.5-%E5%90%8E%E7%AB%AF%E6%A1%86%E6%9E%B6-blue)
![Static Badge](https://img.shields.io/badge/MyBatisPlus%20v3.4.1-%E6%8C%81%E4%B9%85%E5%B1%82%E6%A1%86%E6%9E%B6-green)
![Static Badge](https://img.shields.io/badge/Vue2-%E5%89%8D%E7%AB%AF%E6%A1%86%E6%9E%B6-red)
![Static Badge](https://img.shields.io/badge/Docker-%E9%A1%B9%E7%9B%AE%E9%83%A8%E7%BD%B2-blue)
![](https://img.shields.io/badge/Git-%E7%89%88%E6%9C%AC%E6%8E%A7%E5%88%B6-orange)
![](https://img.shields.io/badge/maven-%E9%A1%B9%E7%9B%AE%E7%AE%A1%E7%90%86-yellowgreen)
![](https://img.shields.io/badge/MD5-%E7%94%A8%E6%88%B7%E9%9A%90%E7%A7%81%E4%BF%9D%E6%8A%A4-lightgrey)
![](https://img.shields.io/badge/JSON-%E6%95%B0%E6%8D%AE%E4%BA%A4%E4%BA%92-yellowgreen)
<br>

## 1、功能概述

仓库管理系统的主要功能如下：

> 系统功能包括 ：产品入出库登记、确认入出库信息、删除库内信息。
>
> 系统管理员功能：添加人员、删除人员、查询库内信息、用户管理。
>
> 用户功能包括：查询库内信息、查询出库信息、查询入库信息、修改本用户密码。
>
> 按管理人员分类，不同权限人员具有不同管理功能。

三级权限

| 权限/功能  | 管理员管理 | 用户管理 | 仓库管理 | 物品分类管理 |  物品管理   |  记录管理   |
| :--------: | :--------: | :------: | :------: | :----------: | :---------: | :---------: |
| 超级管理员 |     √      |    √     |    √     |      √       |      √      |      √      |
| 普通管理员 |     ×      |    √     |    √     |      √       |      √      |      √      |
|    员工    |     ×      |    ×     |    ×     |      ×       | √（有限制） | √（有限制） |

> 在物品管理功能中，员工仅能查询；在记录管理中员工仅能查询自己的入库出库记录。

## 2、技术栈

- `SpringBoot`：后端框架
- `MyBatisPlus`：持久层框架
- `Vue2`：采用Vue作为前端框架，本项目前后端分离
- `MD5`：用户密码使用MD5加密
- `Docker`：使用Docker容器部署项目
- `Git`：使用Github进行版本控制

## 3、UI界面

### 3.1、登录

![image-20230627134641784](D:\Project\Java_Project\FutureScience\WMS\README.assets\image-20230627134641784.png)

### 3.2、个人中心

![image-20230627134810243](D:\Project\Java_Project\FutureScience\WMS\README.assets\image-20230627134810243.png)

### 3.3、登出

![image-20230627134858701](D:\Project\Java_Project\FutureScience\WMS\README.assets\image-20230627134858701.png)

![image-20230627134925083](D:\Project\Java_Project\FutureScience\WMS\README.assets\image-20230627134925083.png)

### 3.4、修改密码

![image-20230627135215716](D:\Project\Java_Project\FutureScience\WMS\README.assets\image-20230627135215716.png)

### 3.5、管理员管理

![image-20230627135244127](D:\Project\Java_Project\FutureScience\WMS\README.assets\image-20230627135244127.png)

### 3.6、用户管理

#### 查询用户



#### 新增用户



#### 删除用户



#### 禁用/启用用户



### 3.7、仓库管理

#### 查询仓库



#### 新增仓库



#### 编辑仓库



#### 删除仓库



### 3.8、物品分类管理



### 3.9、物品管理

#### 查询物品



#### 新增物品



#### 编辑物品



#### 入库



#### 出库





### 3.10、记录管理



