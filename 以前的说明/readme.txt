Lerx 2 beta 1 使用说明

软件说明：一套基于struts2、hibernate、spring的网站内容管理系统。是对Lerx 1.0版本的功能重写。

beta版本暂不提供安装功能，当前也不与lerx 1.x版本兼容。

1.安装
	a.环境
		操作系统：Windows或Linux 建议:linux
		Web容器:tomcat(包括jdk)	建议利用 tomcat+nginx 或 tomcat+apache 2 进行整合
		版本建议：最好按开发环境使用的版本 tomcat 6.0.18 、 jdk 1.6
		注意：因为没有在java7中测试。请不要安装在java 7的环境中。Windows环境中请将java的自动升级取消。
		本系统支持各种关系型数据库，开发环境为Mysql。有经验的可以进行调整。
		所需软件均可在lerx官网的软件下载频道中进行下载。
	b.步骤
		[1]将upload中的文件上传到网站目录，mysql-connector-java-5.0.8-bin.jar最好上传在tomcat的lib目录下，WEB-INF/lib中不要有，即在lib目录中最好不要重复有这些jar文件。
		[2]建立数据库
		[3]初始化数据库
			可以通过程序自动初始化，也可以执行sql进行初始化。程序自动初始化请将WEB-INF/classes下的hibernate.cfg.xml中的已注释掉的下面代码进行恢复
			<property name="hbm2ddl.auto">create</property>
		[4]按实际情况修改数据库配置文件jdbc.properties
			说明：  jdbc.url格式(以mysql为例)：jdbc:mysql://数据库IP:端口号/数据库名?...（后面不要修改）
				jdbc.username=数据库用户名
				jdbc.password=数据库用户密码
	c.运行
		重启Tomcat
		建议利用提供的系统初始化数据进行初始化数据，然后再进入后台进行修改。特别是风格模板。
		在修改风格模板时，建议先复制一份，以防误操作。
2.配置
	修改WEB-INF/classes下的ApplicationResources_zh_CN.properties文件
	将文件中的lerx.com改为你的服务器域名
	ApplicationResources_zh_CN.properties配置说明（部分）
	
	lerx.default.admin.username 
	lerx.default.admin.password
		如果没有建立后台管理员密码，可以在此处设置，即系统可以从此处读取管理帐号和密码，但从安全角度强烈不推荐这种做法。可以在第一次登录后即设置用户名密码，如果忘记，可以再在这里设置进行后台登录
	lerx.userNameLength
		用户名最小长度。
	lerx.userDefaultPassword
	lerx.userDefaultState
	lerx.userDefaultEmail
		后台快速增加用户的初始值。用户状态为enabled时，增加的用户为可用状态，否则为禁止状态
	lerx.defaultReturnUrl
		当系统记忆一些操作返回页面失败时，默认的返回页面，比如，登录时返回登录前所在页面。
	lerx.defaultHtml=index.html
		默认文档名。该设置将影响静态文件的生成。您需要在web服务器的设置中，将该默认文档名加入默认列表。
	lerx.serverPort
		当使用80以外的端口作为服务端口时，请在此处进行修改。0和80代表80
	lerx.ipFilter
		显示ip时显示位数。从左侧起。
	lerx.htmlPath
		静态文件所在文件夹。该文件夹只对内容页有效。其它按服务及配置要求自动生成在相关目录。
	lerx.articleModifySaftMode
		修改文章后的安全模式。将为true时，当某一文章被修改后，将自动将该文章变成未审核状态。false则不进行此操作
	lerx.articleRealTimeByAjax
		动态显示文章数据时使用。如果使用ajax或js来显示文章的部分信息，如查看数等等，应置为false，否则为true
	--------------------------------
	applicationContext.xml中的重要选项，如要生成静态，请下面的注释去除。
	<!--  
	<import resource="applicationTimerTask.xml" />
	-->
	在applicationTimerTask.xml中进行相应的修改。
	[applicationTimerTask.xml]
		<!--注册定时执行任务实体 -->
		<bean id="myTimerTask" name="myTimerTask" class="com.lerx.sys.util.MyTimerTask">
			<property name="taskUrl">
				<value>http://localhost:8080/lerx/createStatic.action</value>
			</property>
		</bean>
		上面请将<value>http://localhost:8080/lerx/createStatic.action</value>中的值对应你的实际情况进行修改！此处出错，将无法自动生成首页和频道静态文件。
3.特殊说明
	a.当对某些栏目频道的访问进行限制(如限制为会员或限ip访问)时，将请该栏目的强制不生成静态打上勾。即该栏目及其下的文件将不会生成静态。
	b.限ip可以采用如下格式
		xxx.xxx.xxx.xxx
		或 xxx.xxx.xxx.xxx-xxx.xxx.xxx.xxx
		或 xxx.xxx.xxx.xxx,xxx.xxx.xxx.xxx,xxx.xxx.xxx.xxx-xxx.xxx.xxx.xxx(即：可以混合使用)
	c.本系统从安全性上考虑，对一些数据发布进行了html过滤。如文章标题、作者等等，以后评论中的所有内容。
	d.一些重要功能，初始模板中采用了ajax技术。当然也可以改成javascript脚本。
4.数据库建表请用utf8
create database lerx default character set utf8 collate utf8_general_ci;

其它，请至官方网站(www.lerx.com)点击帮助频道中的视频进行学习。

	






	


	