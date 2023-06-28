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

## 3、部署

### 3.1、前后端集成部署

- **修改Vue.prototype.$httpUrl**

vue项目文件下的main.js中（不同项目设置的位置不一定一样）

```vue
Vue.prototype.$httpUrl = 'http://{服务器ip}:{springboot端口号}';
eg:
Vue.prototype.$httpUrl = 'http://67.99.26.82:8081';
```

- **vue项目打包**

vue项目目录下

```vue
npm run build
```

- **移动dist文件**

将打包好的dist文件移动到springboot项目`resources/static`中

![image-20230625213804938](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230625213804938.png)

- **激活生产环境和配置静态资源目录**

如图所示，激活生产环境，配置静态资源目录

![image-20230625214121144](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230625214121144.png)

接着需要配置生成环境，例如mysql，redis的密码等。

- **使用maven工具打包** 

![image-20230625214253411](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230625214253411.png)

- **服务器端运行jar包**

前台运行jar包

```bash
java -jar xxx.jar
```

后台运行jar包

```bash
nohup java -jar xxx.jar >msg.log 2>&1 &
```



### 3.2、利用Docker前后端分离部署

- **配置nginx目录，这里在/root/nginx2 下配置**

```bash
[root@iZbp144worluc8frpn60arZ nginx2]# pwd
/root/nginx2
[root@iZbp144worluc8frpn60arZ ~]# cd nginx2
[root@iZbp144worluc8frpn60arZ nginx2]# ll
total 8
drwxr-xr-x 6 root root 4096 Jun 27 19:52 html
-rw-r--r-- 1 root root  550 Jun 26 16:02 nginx.conf
```

nginx.conf

```bash
#user  root;
worker_processes  1;
events {
  worker_connections  1024;
}
http {
  include       mime.types;
  default_type  application/octet-stream;
  sendfile        on;
  keepalive_timeout  65;
  server {
      listen       80;
      server_name  localhost;
      location / {
          root   /usr/share/nginx/html;
          try_files $uri $uri/ /index.html last; # 别忘了这个哈
          index  index.html index.htm;
      }
      error_page   500 502 503 504  /50x.html;
      location = /50x.html {
          root   html;
      }
  }
}
```

html目录（将打包好的dist文件解压到这）

```bash
[root@iZbp144worluc8frpn60arZ nginx2]# cd html
[root@iZbp144worluc8frpn60arZ html]# ll
total 32
drwxr-xr-x 2 root root 4096 Jun 27 19:52 css
-rw-r--r-- 1 root root 4286 Jun 27 19:52 favicon.ico
drwxr-xr-x 2 root root 4096 Jun 27 19:52 fonts
drwxr-xr-x 2 root root 4096 Jun 27 19:52 img
-rw-r--r-- 1 root root  670 Jun 27 19:52 index.html
drwxr-xr-x 2 root root 4096 Jun 27 19:52 js
-rw-r--r-- 1 root root 1524 Jun 27 19:52 logo.svg
```

- **利用docker-compose编排服务**

Dockerfile文件

```dockerfile
FROM openjdk:8
EXPOSE 8082
ADD wms-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]
```

docker-compose.yml

```yml
version: "3"
services:
  nginx: # 服务名称，用户自定义
    image: nginx:latest  # 镜像版本
    ports:
      - 80:80  # 暴露端口
    volumes: # 挂载
      - /root/nginx2/html:/usr/share/nginx/html
      - /root/nginx2/nginx.conf:/etc/nginx/nginx.conf
    privileged: true # 这个必须要，解决nginx的文件调用的权限问题
  mysql:
    image: mysql:latest
    ports:
      - "3306:3306"
    environment: # 指定用户root的密码
      - MYSQL_ROOT_PASSWORD={password}
    privileged: true
  wms:
    image: wms:latest
    build: src # 表示以当前目录下的Dockerfile开始构建镜像
    ports:
      - 8082:8082
    depends_on: # 依赖与mysql其实可以不填，默认已经表示可以
      - mysql
```

如果使用云服务器，请注意在安全组中（或防火墙）开放相应端口

- **组织WMS文件夹目录**

```
[root@iZbp144worluc8frpn60arZ WMS]# ls
docker-compose.yml  Dockerfile  wms-0.0.1-SNAPSHOT.jar
```

- **运行docker-compose命令编排服务**

```yml
[root@iZbp144worluc8frpn60arZ WMS]# docker-compose up -d
```

如果服务正常启动，则可以通过ip访问网站。

- **若后期项目有更新，则需要重新build**

```bash
docker-compose stop
docker-compose up -d --build
```



## 4、UI界面

### 4.1、登录

![image-20230627134641784](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627134641784.png)

### 4.2、个人中心

![image-20230627134810243](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627134810243.png)

### 4.3、登出

![image-20230627134858701](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627134858701.png)

![image-20230627134925083](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627134925083.png)

### 4.4、修改密码

![image-20230627135215716](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627135215716.png)

### 4.5、管理员管理

![image-20230627135244127](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627135244127.png)

### 4.6、用户管理

#### 查询用户

![image-20230627195815309](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627195815309.png)

#### 新增用户

![image-20230627195930492](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627195930492.png)



#### 禁用/启用用户

![image-20230627200044266](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627200044266.png)

#### 删除用户

![image-20230627200105526](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627200105526.png)

### 4.7、仓库管理

#### 查询仓库

![image-20230627200154417](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627200154417.png)

#### 新增仓库

![image-20230627200321443](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627200321443.png)

#### 编辑仓库

![image-20230627200448292](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627200448292.png)

#### 删除仓库

![image-20230627200516006](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627200516006.png)

### 4.8、物品分类管理

![image-20230627200555776](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627200555776.png)

### 4.9、物品管理

#### 新增物品

![image-20230627200808807](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627200808807.png)

#### 入库

![image-20230627200846218](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627200846218.png)

![image-20230627200907494](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627200907494.png)

![image-20230627200927064](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627200927064.png)

#### 出库

![image-20230627201005221](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627201005221.png)



### 4.10、记录管理

![image-20230627201022058](https://github.com/MrNeoJeep/WMS/blob/master/README.assets/image-20230627201022058.png)

