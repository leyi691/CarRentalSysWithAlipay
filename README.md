基于Javaweb的汽车租赁系统，集成了支付宝沙箱，可以实现支付宝收款。
本系统以B/S架构为基础，使用三层结构和MVC开发模式，基于JSP、JavaScript、XML、Java等技术。采用了Mybatis后端持久层框架，使用Maven管理项目，MySQL作为后台数据库。

database文件夹为我使用到的数据表及其链接，已经全部包含在内，只需打开您的MySql，将其导入运行即可创建相应的数据表。
使用Tomacat 7作为服务器，使用Idea 2020.2开发。

这是我2021年毕业设计内容，后面会将我的毕业论文一并上传。

# 部署操作指南

# 1. 前提环境

|  名称  |     版本      |
| :----: | :-----------: |
|  jdk   |    jdk1.8     |
| mysql  |    8.0.22     |
| tomcat | 7.0.101及以上 |

输入Java - version出现如下为jdk安装成功。

![Screenshot 2021-05-28 at 3.03.05 PM](/Users/charlieli/Library/Application Support/typora-user-images/Screenshot 2021-05-28 at 3.03.05 PM.png)

# 2. Java环境安装与配置

1、到[Oracle](http://www.linuxidc.com/topicnews.aspx?tid=12)公司的官网里下载好jdk，网址

http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

![Screenshot 2021-05-28 at 3.05.26 PM](/Users/charlieli/Library/Application Support/typora-user-images/Screenshot 2021-05-28 at 3.05.26 PM.png)

2.安装 参见

https://www.linuxidc.com/Linux/2017-01/139212.htm

# 3. Tomcat安装部署说明

以使用提供的`apache-tomcat-7.0.109.tar`为例

tar zxvf tomcat.tar解压，解压之后目录如下

![Screenshot 2021-05-28 at 3.07.58 PM](/Users/charlieli/Library/Application Support/typora-user-images/Screenshot 2021-05-28 at 3.07.58 PM.png)

- 启动，检测与停止

启动

1. cd bin/
2. ../startup.sh

检测：浏览器打开 http://localhost:8080
停止：./shutdown.sh

# 4.MySQL安装部署

- 参见：https://www.cnblogs.com/dengshihuang/p/8029092.html
- 建议使用 DataGrip 作为连接MySQL的客户端。

连接方式如下：

<img src="/Users/charlieli/Library/Application Support/typora-user-images/Screenshot 2021-05-28 at 3.15.17 PM.png" alt="Screenshot 2021-05-28 at 3.15.17 PM" style="zoom: 50%;" />

<img src="/Users/charlieli/Library/Application Support/typora-user-images/Screenshot 2021-05-28 at 3.16.45 PM.png" alt="Screenshot 2021-05-28 at 3.16.45 PM" style="zoom:50%;" />

- 导入sql文件，操作如下图 

![Screenshot 2021-05-28 at 3.21.40 PM](/Users/charlieli/Library/Application Support/typora-user-images/Screenshot 2021-05-28 at 3.21.40 PM.png)

<img src="/Users/charlieli/Library/Application Support/typora-user-images/Screenshot 2021-05-28 at 3.22.29 PM.png" alt="Screenshot 2021-05-28 at 3.22.29 PM" style="zoom:50%;" />

选择位于数据库文件夹中的carRentalSys.sql文件。

<img src="/Users/charlieli/Library/Application Support/typora-user-images/Screenshot 2021-05-28 at 3.23.28 PM.png" alt="Screenshot 2021-05-28 at 3.23.28 PM" style="zoom:50%;" />

等待运行完毕

<img src="/Users/charlieli/Library/Application Support/typora-user-images/Screenshot 2021-05-28 at 3.24.28 PM.png" alt="Screenshot 2021-05-28 at 3.24.28 PM" style="zoom:50%;" />

出现以下表则表明导入成功。

<img src="/Users/charlieli/Library/Application Support/typora-user-images/Screenshot 2021-05-28 at 3.26.49 PM.png" alt="Screenshot 2021-05-28 at 3.26.49 PM" style="zoom:50%;" />

# 5.后台应用部署

使用cd命令进入tomcat目录结构下的webapp目录，将项目部署包文件夹中所提供的war包上传解压后会看到如下图所示结构。

![Screenshot 2021-05-28 at 3.48.52 PM](/Users/charlieli/Library/Application Support/typora-user-images/Screenshot 2021-05-28 at 3.48.52 PM.png)

使用命令 `cd /home/tomcat/webapps/CarRental/WEB-INF/classes/`进入到配置文件所在目录如下图 ：

![066031A3-CAA7-49E6-9746-02DD4491019F](/Users/charlieli/Library/Containers/com.tencent.qq/Data/Library/Application Support/QQ/Users/277087614/QQ/Temp.db/066031A3-CAA7-49E6-9746-02DD4491019F.png)

打开上图所示配置文件，找到这三行

`        <property name="url" value="jdbc:mysql://localhost:3306/carRental"/>
        <property name="username" value="root"/>
        <property name="password" value="12345678"/>`

将实际mysql地址以及端口号配置、数据库访问账号密码填写到对应的value位置，以本地数据库端口号为3306为例，地址为 `jdbc:mysql://127.0.0.1:3306/CarRental`

修改完成之后再次重启tomcat即可。

# 6.访问系统

`http://IP地址:端口号/doIndex?op=list看到如下页面代表启动成功` 

![Screenshot 2021-05-28 at 4.00.49 PM](/Users/charlieli/Desktop/Screenshot 2021-05-28 at 4.00.49 PM.png)
