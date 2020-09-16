# 用Servlet模拟用户登录

**项目功能：** 模拟用户登录一个Web页面时的执行流程。

**项目意义：**

1. 虽然不具备实际开发的意义，但是对于学习Servlet来说，却是极佳的入门级案例；
2. 有助于快速上手Java动态Web开发的流程；
3. 了解HTTPServlet类中的doPost方法和doGet方法在实际开发时的作用；
4. 第一个Servlet项目；



# 项目构建思想

1. 用户登录的表单页面：login.html ()

2. 数据库存放用于验证的用户名和密码

2. Tomcat服务器：LoginServlet类
   - doPost方法
   - 通过request获得请求参数
   - 使用DBUtils去数据库中校验
   - 登录成功，显示该用户的所有信息
   - 登录失败，显示出错误提示
   
   

# 运行项目

**环境(IDE)：**
	Eclipse IDE for Enterprise Java Developers.
	Version: 2019-12 (4.14.0)
	Build id: 20191212-1212

<u>**导入Eclipse可直接运行！**</u>



## 1，创建数据库

```sql
CREATE DATABASE UserData;

USE userdata;

CREATE TABLE myuser(
	id INT PRIMARY KEY,
	username VARCHAR(50),
	PASSWORD VARCHAR(50),
	email VARCHAR(50)
);


INSERT INTO myuser(id,username,PASSWORD,email)
	VALUES (1,"Tom","123","tom@gmail.com"),
	(2,"Max","321","max@gmail.com");
```



## 2，创建登录表单

创建登录用的静态HTML文件：webContent/login.html



## 3，导入相关的驱动包

1. mysql-connector-java-5.0.4-bin.jar    Java连接MySQL的驱动包
2. commons-dbutils-1.4.jar    使用DBUtils包下的方法快速连接数据库
3. c3p0-0.9.1.2.jar    使得程序具备读取数据库的配置文件：c3p0-config.xml

以上数据包已导入：WebContent/WEB-INF/lib中



## 4，编辑Java逻辑代码

1. domain包下的User类：实体JavaBean类，与数据库建立联系；
2. util包下的DataSourceUtils类：数据库的辅助；
3. service包下的LoginServlet类：业务逻辑代码，实现具体的需求；



## 5，配置web.xml

配置好映射关系，我使用"/login"去映射"service.LoginServlet"类，执行逻辑代码，实现需求。



## 6，部署到Tomcat

选中项目，选择Run As，Run on Server，选择你的Tomcat服务器，即可运行。



## 7，测试

在浏览器中输入：

> http://localhost:8080/01-SimulateUserLoginWithServlet/login.html

点击登录后，会跳转到以下页面：

> http://localhost:8080/01-SimulateUserLoginWithServlet/login

如果成功登录，会显示成功登录信息（我选择登录的是tom）：

> User [id=1, username=Tom, password=123, email=tom@gmail.com], you are login successfully.

如果登录失败，也会显示：

> sorry your username or password is wrong

