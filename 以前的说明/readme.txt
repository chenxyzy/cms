Lerx 2 beta 1 ʹ��˵��

���˵����һ�׻���struts2��hibernate��spring����վ���ݹ���ϵͳ���Ƕ�Lerx 1.0�汾�Ĺ�����д��

beta�汾�ݲ��ṩ��װ���ܣ���ǰҲ����lerx 1.x�汾���ݡ�

1.��װ
	a.����
		����ϵͳ��Windows��Linux ����:linux
		Web����:tomcat(����jdk)	�������� tomcat+nginx �� tomcat+apache 2 ��������
		�汾���飺��ð���������ʹ�õİ汾 tomcat 6.0.18 �� jdk 1.6
		ע�⣺��Ϊû����java7�в��ԡ��벻Ҫ��װ��java 7�Ļ����С�Windows�������뽫java���Զ�����ȡ����
		��ϵͳ֧�ָ��ֹ�ϵ�����ݿ⣬��������ΪMysql���о���Ŀ��Խ��е�����
		�������������lerx�������������Ƶ���н������ء�
	b.����
		[1]��upload�е��ļ��ϴ�����վĿ¼��mysql-connector-java-5.0.8-bin.jar����ϴ���tomcat��libĿ¼�£�WEB-INF/lib�в�Ҫ�У�����libĿ¼����ò�Ҫ�ظ�����Щjar�ļ���
		[2]�������ݿ�
		[3]��ʼ�����ݿ�
			����ͨ�������Զ���ʼ����Ҳ����ִ��sql���г�ʼ���������Զ���ʼ���뽫WEB-INF/classes�µ�hibernate.cfg.xml�е���ע�͵������������лָ�
			<property name="hbm2ddl.auto">create</property>
		[4]��ʵ������޸����ݿ������ļ�jdbc.properties
			˵����  jdbc.url��ʽ(��mysqlΪ��)��jdbc:mysql://���ݿ�IP:�˿ں�/���ݿ���?...�����治Ҫ�޸ģ�
				jdbc.username=���ݿ��û���
				jdbc.password=���ݿ��û�����
	c.����
		����Tomcat
		���������ṩ��ϵͳ��ʼ�����ݽ��г�ʼ�����ݣ�Ȼ���ٽ����̨�����޸ġ��ر��Ƿ��ģ�塣
		���޸ķ��ģ��ʱ�������ȸ���һ�ݣ��Է��������
2.����
	�޸�WEB-INF/classes�µ�ApplicationResources_zh_CN.properties�ļ�
	���ļ��е�lerx.com��Ϊ��ķ���������
	ApplicationResources_zh_CN.properties����˵�������֣�
	
	lerx.default.admin.username 
	lerx.default.admin.password
		���û�н�����̨����Ա���룬�����ڴ˴����ã���ϵͳ���ԴӴ˴���ȡ�����ʺź����룬���Ӱ�ȫ�Ƕ�ǿ�Ҳ��Ƽ����������������ڵ�һ�ε�¼�������û������룬������ǣ����������������ý��к�̨��¼
	lerx.userNameLength
		�û�����С���ȡ�
	lerx.userDefaultPassword
	lerx.userDefaultState
	lerx.userDefaultEmail
		��̨���������û��ĳ�ʼֵ���û�״̬Ϊenabledʱ�����ӵ��û�Ϊ����״̬������Ϊ��ֹ״̬
	lerx.defaultReturnUrl
		��ϵͳ����һЩ��������ҳ��ʧ��ʱ��Ĭ�ϵķ���ҳ�棬���磬��¼ʱ���ص�¼ǰ����ҳ�档
	lerx.defaultHtml=index.html
		Ĭ���ĵ����������ý�Ӱ�쾲̬�ļ������ɡ�����Ҫ��web�������������У�����Ĭ���ĵ�������Ĭ���б�
	lerx.serverPort
		��ʹ��80����Ķ˿���Ϊ����˿�ʱ�����ڴ˴������޸ġ�0��80����80
	lerx.ipFilter
		��ʾipʱ��ʾλ�����������
	lerx.htmlPath
		��̬�ļ������ļ��С����ļ���ֻ������ҳ��Ч����������������Ҫ���Զ����������Ŀ¼��
	lerx.articleModifySaftMode
		�޸����º�İ�ȫģʽ����Ϊtrueʱ����ĳһ���±��޸ĺ󣬽��Զ��������±��δ���״̬��false�򲻽��д˲���
	lerx.articleRealTimeByAjax
		��̬��ʾ��������ʱʹ�á����ʹ��ajax��js����ʾ���µĲ�����Ϣ����鿴���ȵȣ�Ӧ��Ϊfalse������Ϊtrue
	--------------------------------
	applicationContext.xml�е���Ҫѡ���Ҫ���ɾ�̬���������ע��ȥ����
	<!--  
	<import resource="applicationTimerTask.xml" />
	-->
	��applicationTimerTask.xml�н�����Ӧ���޸ġ�
	[applicationTimerTask.xml]
		<!--ע�ᶨʱִ������ʵ�� -->
		<bean id="myTimerTask" name="myTimerTask" class="com.lerx.sys.util.MyTimerTask">
			<property name="taskUrl">
				<value>http://localhost:8080/lerx/createStatic.action</value>
			</property>
		</bean>
		�����뽫<value>http://localhost:8080/lerx/createStatic.action</value>�е�ֵ��Ӧ���ʵ����������޸ģ��˴��������޷��Զ�������ҳ��Ƶ����̬�ļ���
3.����˵��
	a.����ĳЩ��ĿƵ���ķ��ʽ�������(������Ϊ��Ա����ip����)ʱ���������Ŀ��ǿ�Ʋ����ɾ�̬���Ϲ���������Ŀ�����µ��ļ����������ɾ�̬��
	b.��ip���Բ������¸�ʽ
		xxx.xxx.xxx.xxx
		�� xxx.xxx.xxx.xxx-xxx.xxx.xxx.xxx
		�� xxx.xxx.xxx.xxx,xxx.xxx.xxx.xxx,xxx.xxx.xxx.xxx-xxx.xxx.xxx.xxx(�������Ի��ʹ��)
	c.��ϵͳ�Ӱ�ȫ���Ͽ��ǣ���һЩ���ݷ���������html���ˡ������±��⡢���ߵȵȣ��Ժ������е��������ݡ�
	d.һЩ��Ҫ���ܣ���ʼģ���в�����ajax��������ȻҲ���Ըĳ�javascript�ű���
4.���ݿ⽨������utf8
create database lerx default character set utf8 collate utf8_general_ci;

�����������ٷ���վ(www.lerx.com)�������Ƶ���е���Ƶ����ѧϰ��

	






	


	