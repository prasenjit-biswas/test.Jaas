1> Create an entry in server.xml
	<Realm appName="testJAAS" className="org.apache.catalina.realm.JAASRealm"/>
2> Create a file name jaas.config and place that in tomcat conf/ folder
   in this file make entry like 
     testJAAS{
		com.security.JAASLogingModule required debug=true;
	};
3> when you are starting tomcat append the JAVA_OPTION like 
   -Djava.security.auth.login.config="D:\apache-tomcat-7.0.37\conf\jaas.config"