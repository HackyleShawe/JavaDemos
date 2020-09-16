# 统计登录成功数

**项目功能：** 模拟用户登录一个Web页面的成功数。

**项目意义：**

1. 理解`ServletContext`作为一个域对象，到底是怎么个原理；
2. 实践：多个Servlet对象对`ServletContext域`中存放的数据进行公共访问；
3. 实践：从`ServletContext域`中存放和拿取数据（可以是基础数据类型和引用数据类型）；



# 项目构建思想

**前端界面：**

1. login.html		登录界面
2. login_success.html        登录成功界面，显示登录成功的数量

**后端：**

1. LoginServlet.java
   - 获取HTTP中的请求参数，也即用户名和密码，并进行验证；
   - 设置/调整 `域对象`中count的值
2. CountServlet.java        从域对象中取出值；显示在页面；



# 运行项目

**环境(IDE)：**
	Eclipse IDE for Enterprise Java Developers.
	Version: 2019-12 (4.14.0)
	Build id: 20191212-1212

<u>**导入Eclipse可直接运行！**</u>



**1. 部署到Tomcat：**

选中项目，选择Run As，Run on Server，选择你的Tomcat服务器，即可运行。



**2. 测试：**

1, 在浏览器中输入：

> http://localhost:8080/02-TotalNumberOfSuccessfulLoginWithServletContext/login.html



2, 进入登录界面，输入测试的用户名和密码：

```
admin
123
```



3, 点击登录后，成功后会跳转到以下页面：

> http://localhost:8080/02-TotalNumberOfSuccessfulLoginWithServletContext/login_success.html



4, 点击“获取网站登录成功总数”，进入：

> http://localhost:8080/02-TotalNumberOfSuccessfulLoginWithServletContext/CountServlet



5, 即显示成功登录成功的数量。

