<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/ext-theme-neptune-all.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/icons/favicon.ico">
    <script type="text/javascript" src="<%=request.getContextPath()%>/extjs/ext-all-debug.js"></script>
    <title>index</title>
</head>
<body>
<script>
    var authcodeBaseUrl = '<%=request.getContextPath()%>/login/authcode/';
    Ext.onReady(function () {
        var authcode = Ext.create("Ext.Img", {
            src: authcodeBaseUrl + new Date().getMilliseconds(),
            renderTo: Ext.getBody(),
            html: '无法获取验证码',
            width: 100,
            height: 25,
            style: 'margin-left:10px',
            listeners: {
                el: {
                    click: function () {
                        authcode.setSrc(authcodeBaseUrl + new Date().getMilliseconds());
                    }
                },
                afterrender: function (me) {
                    me.getEl().dom.style.cursor = 'pointer';
                    me.getEl().dom.title = '看不清?换一张呗~';
                }

            }
        });
        var loginform = Ext.create("Ext.form.Panel", {
            title: '登录验证',
            width: 300,
            border: false,
            style: 'margin-left:auto;margin-right:auto;margin-top:15%',
            layout: 'anchor',
            defaults: {
                width: 200,
                anchor: '95%',
                allowBlank: false, //默认不许空白
                padding: 10,
                labelWidth: 50 // 标签的宽度
            },
            defaultType: 'textfield',
            items: [{
                fieldLabel: '用户名',
                name: 'username',
                emptyText: '请输入用户名',
                blankText: '用户名不能为空',
                minLength: 5
            }, {
                fieldLabel: '密码',
                name: 'password',
                emptyText: '请输入密码',
                blankText: '密码不能为空',
                minLength: 5,
                inputType: 'password'
            }, {
                xtype: 'container',
                layout: {
                    type: 'hbox'
                },
                items: [{
                    xtype: 'textfield',
                    fieldLabel: '验证码:',
                    labelWidth: 50,
                    width: 150,
                    name: 'authcode',
                    emptyText: '请输入验证码',
                    blankText: '验证码不能为空'
                }, authcode]
            }],
            buttonAlign: 'center',
            buttons: [{
                text: '登录',
                itemId: 'login',
                handler: function () {
                    login_submit(loginform);
                }
            }]
        });
        var map = new Ext.util.KeyMap({
            target: Ext.getBody(),
            key: Ext.EventObject.ENTER,
            //key: "arnt",
            fn: function () {
                login_submit(loginform);
            }
        });
        var mainpanel = Ext.create("Ext.panel.Panel", {
            width: 1200,
            height: '100%',
            border: false,
            dockedItems: [{
                xtype: 'toolbar',
                dock: 'top',
                style: 'width: 1200px;height: 28px;padding-left:0px;padding-right:10px;background-color:#157FCC ',
                defaultType: 'label',
                defaults: {
                    style: 'color:white'
                },
                items: [' ', '-', {
                    text: '欢迎'
                }, '->', '-', {
                    xtype: 'label',
                    listeners: {
                        render: function (me) {
                            var config = {
                                run: function () {
                                    me.update(Ext.Date.format(new Date(), 'Y-m-d H:i:s A'));
                                },
                                interval: 1000
                            };
                            Ext.TaskManager.start(config);
                        }
                    }
                }]
            }],
            items: [loginform]
        });
        Ext.create("Ext.container.Viewport", {
            layout: {
                type: 'hbox',
                pack: 'center'
            },
            items: [mainpanel]
        });
        //console.log('loginform.getEl()',loginform.getEl());
        //loginform.getEl().dom.style.marginTop = '20px';
    });
    //form validation && submit
    function login_submit(loginform) {
        if (loginform.isValid()) {
            loginform.submit({
                clientValidation: true,
                waitMsg: '请稍候',
                waitTitle: '正在验证登录',
                url: '<%=request.getContextPath()%>/login/check',
                success: function (form, action) {
                    var result = action.result.result;
                    var level = action.result.level;
                    if (result == 'SUCCESS') {
                        location.href = '<%=request.getContextPath()%>/' + level;
                    } else {
                        if (result == 'FAIL_AUTHCODE') {
                            Ext.Msg.show({title: '登录验证', msg: '验证码错误', buttons: Ext.Msg.OK, icon: Ext.Msg.INFO});
                        }
                        if (result == 'FAIL_USER_ERROR') {
                            Ext.Msg.show({title: '登录验证', msg: '用户名错误', buttons: Ext.Msg.OK, icon: Ext.Msg.INFO});
                        }
                        if (result == 'FAIL_PASSWD_ERROR') {
                            Ext.Msg.show({title: '登录验证', msg: '密码错误', buttons: Ext.Msg.OK, icon: Ext.Msg.INFO});
                        }
                        authcode.setSrc(authcodeBaseUrl + new Date().getMilliseconds()); //重载验证码
                    }
                },
                failure: function (form, action) {
                    switch (action.failureType) {
                        case Ext.form.action.Action.CLIENT_INVALID:
                            Ext.Msg.alert('Failure', '');
                            break;
                        case Ext.form.action.Action.CONNECT_FAILURE:
                            Ext.Msg.alert('Failure', 'Ajax communication failed');
                            break;
                        case Ext.form.action.Action.SERVER_INVALID:
                            Ext.Msg.alert('出错啦~', '当前提交不可用，请重试');
                    }
                }
            });
        } else {
            Ext.Msg.show({
                width: 150,
                title: "登录验证~",
                buttons: Ext.Msg.OK,
                msg: '请完善表单后再登录',
                icon: Ext.Msg.INFO,
                closable: false
            });
        }

    }
</script>
</body>
<style type="text/css">
    html {
        height: 100%;
        background-color: lightgray;
    }
</style>
</html>