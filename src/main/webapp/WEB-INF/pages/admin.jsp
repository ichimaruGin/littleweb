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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/icon.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/icons/favicon.ico">
    <script type="text/javascript" src="<%=request.getContextPath()%>/extjs/ext-all-debug.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/extjs/ext-lang-zh_CN.js"></script>
</head>
<body>
<script>
    Ext.onReady(function () {
        var naviPanel = Ext.create('Ext.panel.Panel', {
            width: '25%',
            region: 'west',
            defaultType: 'button',
            defaults: {
                width: 200,
                style: 'margin-top:10px',
                anchor: '95%'
            },
            layout: {
                type: 'vbox',
                align: 'center'
            },
            items: [{
                text: '账号密码管理',
                handler: function () {
                    var tab = tabPanel.down('usergrid');
                    if(!tab){
                        tab = Ext.create('user_grid_admin',{
                            closeAction: 'destroy'
                        });
                        tabPanel.add(tab);
                        tabPanel.setActiveTab(tab);
                    }else{
                        tabPanel.setActiveTab(tab);
                    }
                }
            }, {
                text: '企业信息管理'
            }]

        })

        var tabPanel = Ext.create('Ext.tab.Panel', {
            region: 'center',
            plain: true,
            border: true,
            defaults: {
                bodyPadding: 10
            },

            listeners: {
                add: {
                    scope:this,
                    fn:function(panel,tabPanel,pos){
                        //console.log('add a panel on '+ pos);
                    }
                },
                remove: {
                    scope:this,
                    fn:function(ct,panel){
                        //console.log('remove a panel',ct,panel);
                    }
                }
            }
        });
        var mainPanel = Ext.create('Ext.panel.Panel', {
            width: 1200,
            height: '100%',
            border: false,
            layout: 'border',
            items: [naviPanel, tabPanel]
        });

        Ext.create('Ext.container.Viewport', {
            layout: {
                type: 'hbox',
                pack: 'center'
            },
            items: [mainPanel]
        });




    });
    Ext.define('user_model_admin', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'username', type: 'string'},
            {name: 'password', type: 'string'},
            {name: 'userLevel', type: 'string'},
            {name: 'departmentId', tyep: 'int'},
            {
                name: 'registerTime', convert: function (value, record) {
                var d = Ext.Date.parse(value, 'timestamp');
                return Ext.Date.format(d, 'Y-m-d H:i:s');
            }
            },
            {name: 'isValid', type: 'boolean'}
        ]
    });
    Ext.define('user_store_admin', {
        model: 'user_model_admin',
        proxy: {
            type: 'ajax',
            url: '<%=request.getContextPath()%>/admin/query/0',
            reader: {
                type: 'json'
            }
        }
    });
    Ext.define('user_grid_admin', {
        extend: 'Ext.grid.Panel',
        alias:'widget.usergrid',
        requires: [
            'Ext.grid.column.Action'
        ],
        height: 400,
        title: '用户信息管理',
        border: false,
        closable: true,
        viewConfig: {
            stripeRow: true,
            enableTextSelection: true
        },
        initComponent: function () {
            this.cellEditing = new Ext.grid.plugin.CellEditing({
                clicksToEdit:1,
                listeners:{
                    canceledit:{
                        scope:this,
                        fn: this.cancelSave
                    },
                    validateedit:{
                        scope:this,
                        fn:this.autoCommit
                    }
                }
            });
            Ext.apply(this, {
                plugins: [this.cellEditing],
                selModel: {
                    selType: 'cellmodel'
                },
                tbar: [
                    {xtype: 'button', scope:this, text: '新增', width: 100, handler: this.onAddUser},
                ],
                store: Ext.create('user_store_admin', {}),
                columns: [
                    {text: '编号', dataIndex: 'id'},
                    {
                        text: '用户名',
                        dataIndex: 'username',  //不许修改
                        sotable: false,
                        editor:{xtype: 'textfield'}
                    },
                    {
                        text: '密码',
                        dataIndex: 'password',
                        sotable: false,
                        editor: {xtype: 'textfield'}
                    },
                    {
                        text: '级别',
                        dataIndex: 'userLevel',
                        sotable: false,
                        editor: {
                            xtype:'combo',
                            store: Ext.create('Ext.data.Store', {
                                fields: ['name', 'value'],
                                data : [
                                    {"name":"admin", "value":"admin"},
                                    {"name":"personal", "value":"personal"},
                                    {"name":"department", "value":"department"}
                                ]
                            }),
                            querymode: 'local',
                            displayField: 'name',
                            valueField: 'value'
                        }
                    },
                    {
                        text: '部门',
                        dataIndex: 'departmentId',
                        sotable: false,
                        width: 60,
                        editor: {xtype: 'textfield'}
                    },
                    {text: '注册时间', dataIndex: 'registerTime', flex: 1},
                    {text: '有效账号', dataIndex: 'isValid', editor: {xtype: 'textfield'}},
                    {
                        //menuDisabled: true,
                        text: '更新或保存',
                        xtype: 'actioncolumn',
                        align:'center',
                        items: [{
                            iconCls: 'iconfont-update',
                            tooltip: '提交修改',
                            handler: this.onUpdate
                        }]
                    },
                    {
                        //menuDisabled: true,
                        text: '失效',
                        xtype: 'actioncolumn',
                        align:'center',
                        items: [{
                            iconCls: 'iconfont-delete',
                            tooltip: '删除记录',
                            handler: this.onDelete
                        }]
                    }
                ]
            });
            this.callParent();
            this.on('afterlayout', this.loadStore, this, {
                delay: 1,
                single: true
            });
        },
        loadStore:function(){
            this.getStore().load({

            });
        },
        onAddUser: function () {
            var store = this.getStore();
            if(store.getAt(0).data['id'] == 0)
                //防止超过1次的新增导致grid选择的错误
                alert('先把之前的save完成');
            else{
                var rec = Ext.create('user_model_admin', {
                    id: 0,
                    username: 'user'+parseInt(1000*Math.random()+1),
                    password: 'pass',
                    userLevel: 'personal',
                    departmentId: 0,
                    registerTime: new Date().getTime() / 1000,
                    isValid: true
                });
                store.insert(0, rec);
            }
        },
        autoCommit: function(editor,e){
            console.log(e);
            e.record.commit(); //imortant,不加这句话会出现点着点着就点不动的bug
        },
        cancelSave: function(editor,context){
            console.log('cancel');
        },
        onUpdate: function (grid, rowIndex) {
            var rec = grid.getStore().getAt(rowIndex);
            Ext.Ajax.request({
                url: '<%=request.getContextPath()%>/admin/update/0',
                params: rec.data,
                method: 'POST',
                success: function (response) {
                    var obj = Ext.decode(response.responseText);
                    if (obj.result == 'UPDATE_SUCCESS') {
                        Ext.Msg.show({
                            title: 'update',
                            msg: '更新完成',
                            icon: Ext.Msg.INFO,
                            buttons: Ext.Msg.OK
                        });
                        grid.getStore().reload();
                    }else if(obj.result == 'INSERT_SUCCESS'){
                        Ext.Msg.show({
                            title: 'save',
                            msg: '保存完成',
                            icon: Ext.Msg.INFO,
                            buttons: Ext.Msg.OK
                        });
                        grid.getStore().reload();
                    } else{
                        Ext.Msg.show({msg: '失败', buttons: Ext.Msg.OK});
                    }
                },
                failure: function (response) {
                    alert('服务器错误:' + response.status);
                }
            });
        },
        onDelete: function (grid, rowIndex, colIndex) {
            var rec = grid.getStore().getAt(rowIndex);
            Ext.Ajax.request({
                url: '<%=request.getContextPath()%>/admin/delete/0',
                params: rec.data,
                method: 'POST',
                success: function (response) {
                    var obj = Ext.decode(response.responseText);
                    if (obj.result == 'UPDATE_SUCCESS') {
                        Ext.Msg.show({
                            title: 'update',
                            msg: 'DELETE_SUCCESS'+'@id='+rec.data.id,
                            icon: Ext.Msg.INFO,
                            buttons: Ext.Msg.OK
                        });
                        grid.getStore().reload();
                    }else{
                        Ext.Msg.show({msg: '失败', buttons: Ext.Msg.OK});
                    }
                },
                failure: function (response) {
                    alert('服务器错误:' + response.status);
                }
            });
        }
    });
</script>
</body>
<style type="text/css">
    html {
        height: 100%;
        background-color: lightgray;
    }
</style>
</html>
