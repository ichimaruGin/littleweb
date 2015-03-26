<%--
  Created by IntelliJ IDEA.
  User: YY_410
  Date: 2015/3/26
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理中心</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/ext-theme-neptune-all.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/extjs/ext-all-debug.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/extjs/ext-lang-zh_CN.js"></script>
</head>
<body>
<script>
    Ext.onReady(function () {
        var tbar = Ext.create('Ext.toolbar.Toolbar', {
            width: 1200,
            region: 'north',
            style: 'width: 1200px;background-color:#157FCC ',
            items: [{
                text: '待定'
            }, '-', {
                text: '待定1234'
            }]

        });

        var naviPanel = Ext.create('Ext.panel.Panel', {
            width: '30%',
            region: 'west',
            title: '^-^',
            defaultType: 'button',
            defaults: {
                width: 200,
                style:'margin-top:10px',
                anchor: '95%'
            },
            layout: {
                type: 'vbox',
                align:'center'
            },
            items: [{
                text: '账号密码管理',
                handler:function(){
                    console.log(userStore);
                }
            }, {
                text: '企业信息管理'
            }]

        })

        var tabPanel = Ext.create('Ext.tab.Panel', {
            title: '^-^',
            region: 'center',
            defaults: {
                bodyPadding: 10
            },
            items: [{
                title: 'one'
            }, {
                title: 'two'
            }]
        });

        var mainPanel = Ext.create('Ext.panel.Panel', {
            width: 1200,
            height: '100%',
            border: false,
            layout: 'border',
            items: [tbar, naviPanel, tabPanel]
        });

        Ext.create('Ext.container.Viewport', {
            layout: {
                type: 'hbox',
                pack: 'center'
            },
            items: [mainPanel]
        });

        var userStore = Ext.create('Ext.data.Store', {
            model: 'user',
            proxy: {
                type: 'ajax',
                url: '<%=request.getContextPath()%>/admin/user',
                reader:{
                    type:'json'
                }
            }
        });
    });
    Ext.define('user', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'username', type: 'string'},
            {name: 'password', type: 'string'},
            {name: 'userLevel', type: 'string'},
            {name: 'departmentId', tyep: 'int'},
            {name: 'registerTime', type: 'date'}
        ]
    })
</script>
</body>
<style type="text/css">
    html {
        height: 100%;
        background-color: lightgray;
    }
</style>
</html>
