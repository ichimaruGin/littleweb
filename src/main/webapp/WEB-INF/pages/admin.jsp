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
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/icons/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/ext-theme-neptune-all.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/icon.css">
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
                text: '待定'
            }]

        });

        var naviPanel = Ext.create('Ext.panel.Panel', {
            width: '30%',
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
                    tabPanel.remove(userGrid, false);
                    tabPanel.add(userGrid);
                    userGrid.getStore().load();
                }
            }, {
                text: '企业信息管理'
            }]

        })

        var tabPanel = Ext.create('Ext.tab.Panel', {
            region: 'center',
            plain: true,
            border: false,
            defaults: {
                bodyPadding: 10
            },
            items: [],
            listeners: {
                added: function (tab, comp) {
                    tab.setActiveTab(comp);
                }
            }
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

        var userGrid = Ext.create('user_grid_admin');


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
        requires: [
            'Ext.grid.column.Action'
        ],
        height: 400,
        title: '用户信息管理',
        frame: true,
        border: false,
        closable: true,
        viewConfig: {
            stripeRow: true,
            enableTextSelection: true
        },
        initComponent: function () {
            this.cellEditing = new Ext.grid.plugin.CellEditing({
                clicksToEdit: 2
            });
            Ext.apply(this, {
                selType: 'cellmodel',
                tbar: [
                    {xtype: 'button', text: '新增', width: 100, handler: this.onAddUser},
                ],
                plugins: [this.cellEditing],
                store: Ext.create('user_store_admin', {}),
                columns: [
                    {text: '编号', dataIndex: 'id'},
                    {
                        text: '用户名',
                        dataIndex: 'username',
                        sotable: false,
                        editor: {xtype: 'textfield', allowBlank: false}
                    },
                    {
                        text: '密码',
                        dataIndex: 'password',
                        sotable: false,
                        editor: {xtype: 'textfield', allowBlank: false}
                    },
                    {
                        text: '级别',
                        dataIndex: 'userLevel',
                        sotable: false,
                        editor: {xtype: 'textfield', allowBlank: false}
                    },
                    {
                        text: '部门',
                        dataIndex: 'departmentId',
                        sotable: false,
                        width:60,
                        editor: {xtype: 'textfield', allowBlank: false}
                    },
                    {text: '注册时间', dataIndex: 'registerTime', flex: 1},
                    {text: '有效账号', dataIndex: 'isValid', editor: {xtype: 'textfield', allowBlank: false}},
                    {
                        menuDisabled: true,
                        xtype: 'actioncolumn',
                        width:40,
                        items: [{
                            iconCls: 'iconfont-update',
                            tooltip: '提交修改',
                            handler: this.onUpdate
                        }]
                    },
                    {
                        menuDisabled: true,
                        xtype: 'actioncolumn',
                        width:40,
                        items: [{
                            iconCls: 'iconfont-delete',
                            tooltip: '删除记录',
                            handler: this.onDelete
                        }]
                    }
                ]
            });
            this.callParent();
        },
        onAddUser: function () {

        },
        onUpdate: function (grid, rowIndex, colIndex) {
            var rec = grid.getStore().getAt(rowIndex);
            Ext.Ajax.request({
                url: '<%=request.getContextPath()%>/admin/update/0',
                params: rec.data,
                method: 'POST',
                success: function (response) {
                    var obj = Ext.decode(response.responseText);
                    if(obj.result == 'UPDATE_SUCCESS'){
                        console.log('refresh')
                        grid.getStore().reload();
                    }
                },
                failure: function (response) {
                    alert('server-side failure with status code ' + response.status);
                }
            });
        },
        onDelete: function (grid, rowIndex, colIndex) {
            var rec = grid.getStore().getAt(rowIndex);

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
