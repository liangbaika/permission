项目简介：
本项目(主要提供restful api 接口,当然也提供了一个带界面的版本web-permission-ui.war)是一个以权限为核心的基于RBAC模型可自由拓展的基础框架
权限方面 未采用任何第三方权限框架，全是自己编写，更加可把控，熟悉，自由配置;
基础模块
1.用户管理
2.角色管理
3.组织部门管理
4.权限模块管理
5.权限点管理
6.角色与用户关系维护管理
7.角色与权限关系维护管理（6,7为本项目核心）
8.日志管理 以及操作恢复还原等

项目启动注意事项：

1、数据库配置：/resources/settings.properties
2、redis配置：/resources/redis.properties
3、项目入口页：/signin.jsp
4  部署到tomcat等服务器时,路径内请不要包含项目名 错例:http://localhost:8080/permission 正确: http://localhost:8080/
5、登录使用测试用户名和密码：
username: admin@qq.com
password: 12345678

演示地址：http://www.liangbaikai.xin

其他：
1、如果暂时不想使用redis，如何移除
1) applicationContext.xml里移除 <import resource="redis.xml" />
2) 修改RedisPool.java 类取消被spring管理
3）修改SysCacheService.java 类移除RedisPool.java的使用

2、如果想在正式环境使用，需要注意哪些
1）如果是集群部署，需要配置session共享，保证登录一次就可以，体验好
可以参考一下：http://blog.csdn.net/pingnanlee/article/details/68065535
2）确认一下项目里超级管理员的权限的规则
代码位置：SysCoreService.java类里的isSuperAdmin() 超级管理员规则可以自由配置 根据名字或者类型判断
3）新增管理员的密码处理
SysUserService.java里的save() 方法里需要移除 password = "12345678";
同时，MailUtil里的发信参数要补全，并在SysUserService.java里的save()里 sysUserMapper.insertSelective(user) 之前调用
这是默认给的逻辑，可以根据项目实际情况调整（邮件可自由拓展，此处可以用来 在添加用户后把密码以邮件方式发给用户 需补全方法）

3. 建议日志 邮件 以异步方式处理,从而提高系统效率,更好的用户体验
1) 在web.xml 里的DisPatcherServlet 里加入   <async-supported>true</async-supported>
2) 在spring-web.xml 添加 <task:executor id="executor" pool-size="25"/> ; <task:annotation-driven executor="executor"/>
3) 在对应的类上加上@EnableAsync  方法上加上 @Async 即可在对应的方法调用了

4. 本项目细节还有待优化; 由于jsp已在实际开发中日渐落寞，而前后端分离的方式更是主流，因此，本项目已推出 restful版本，提供几乎所有的更丰富的接口API；

5. 本项目预计会有后续版本(ps:工作有时有点忙，也还未毕业，最近估计准备毕业设计等，空闲时间有点少...)

6. 无论你是前端 后端还是运维 或者测试，有兴趣的小伙伴都可以email我：1144388620@qq.com 同QQ,欢迎来撩;
   由于本人技术能力有限，本项目还请各位大佬嘴下留情;
   真诚的感谢您的阅读;
