##毕设web端


##mvn创建web应用(默认是web2.3)，转换成web3.0
*参考http://www.open-open.com/lib/view/open1389227144328.html

##eclipse 配置本地tomcat
*创建一个server(Tomcat)
*右键stop,clean,open
*server location选择第二个User tomcat Installation
*修改deploy path为本地tomcat的webapps
*保存重启

##intellij idea 配置tomcat
*open  Ctrl+Alt+S 打开设置 找到build,execution,deployment 找到application servers 在右侧界面click 'green add',添加
一个tomcat server(local) 配置一下就OK
*然后就是为web app添加run的conf
*Run->Edit Configuration->click 'green add'->choose tomcat
*在右侧的配置界面,设置name&选择浏览器&设置web启动打开的url&选择deploy对象
*这样就会出现Application Servers的tab,单击后run

##tomcat7 设置gzip压缩
*<Connector port="8080" protocol="HTTP/1.1"
*               connectionTimeout="20000"
*               redirectPort="8443"
*				URIEncoding="UTF-8"
*               compression="on"
*               compressionMinSize="2048"  //2kB
*               noCompressionUserAgents="gozilla,traviata"
*               compressableMimeType="text/html,text/xml,text/css,text/plain,text/javascript,application/x-javascript,application/javascript" />

##登录
*验证码
*form.submit()-->controller-->ajax返回-->windown.location.reload
*采用session保存 用户信息及用户登陆状态(UserStatus)
*LoginResult转为json后作为登录验证ajax的返回值

