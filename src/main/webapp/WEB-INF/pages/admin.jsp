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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/example.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/icons/favicon.ico">
    <script type="text/javascript" src="<%=request.getContextPath()%>/app/amap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/extjs/ext-all-debug.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/extjs/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/extjs/example.js"></script>
</head>
<body onload="getBasicParam()" >
<script>
    var deviceWidth;
    var deviceHeight;
    function getBasicParam(){
        deviceHeight = document.body.clientHeight;
        deviceWidth = document.body.clientWidth;
    };
    Ext.onReady(function () {
        Ext.tip.QuickTipManager.init();
        var naviPanel = Ext.create('Ext.panel.Panel', {
            height: '8%',
            region: 'north',
            defaultType: 'button',
            defaults: {
                width: 180,
                style: 'margin-left:10px',
                anchor: '95%'
            },
            layout: {
                type: 'hbox',
                align: 'middle',
                pack: 'start'
            },
            items: [{
                text: '账号密码管理',
                handler: function () {
                    var tab = tabPanel.down('usergrid');
                    if (!tab) {
                        tab = Ext.create('user_grid_admin', {
                            closeAction: 'destroy'
                        });
                        tabPanel.add(tab);
                        tabPanel.setActiveTab(tab);
                    } else {
                        tabPanel.setActiveTab(tab);
                    }
                }
            }, {
                text: '企业信息管理',
                handler: function () {
                    var tab = tabPanel.down('departmentgrid');
                    if (!tab) {
                        tab = Ext.create('department_grid_admin', {
                            closeAction: 'destroy'
                        });
                        tabPanel.add(tab);
                        tabPanel.setActiveTab(tab);
                    } else {
                        tabPanel.setActiveTab(tab);
                    }
                }
            }]

        })
        var tabPanel = Ext.create('Ext.tab.Panel', {
            region: 'center',
            border: true
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
        alias: 'widget.usergrid',
        requires: [
            'Ext.grid.column.Action'
        ],
        height: 400,
        title: '用户信息管理',
        border: false,
        closable: true,
        //下面这段话最好别加
//        viewConfig: {
//            stripeRow: true,
//            enableTextSelection: true
//        },
        initComponent: function () {
            this.cellEditing = new Ext.grid.plugin.CellEditing({
                clicksToEdit: 2
            });
            this.cellEditing.on('validateedit', function (editor, e) {
                if (e.originalValue == e.value)
                    return false;
                else
                    e.record.commit();  //important,不加这句话会出现点着点着就点不动的bug
            });
            Ext.apply(this, {
                plugins: [this.cellEditing],
                selModel: {
                    selType: 'cellmodel'
                },
                tbar: [
                    {xtype: 'button', scope: this, text: '新增', width: 100, handler: this.onAddUser},
                    {xtype: 'button', scope: this, text: '刷新', width: 100, handler: this.loadStore},
                    {xtype: 'button', scope: this, text: '显示部门', width: 100, handler: this.showDepartment}
                ],
                store: Ext.create('user_store_admin', {}),
                columns: [
                    {text: '编号', dataIndex: 'id'},
                    {
                        text: '用户名',
                        dataIndex: 'username',  //不许修改
                        sortable: false,
                        editor: {xtype: 'textfield', allowBlank: false, blankText: '用户名不能为空'}
                    },
                    {
                        text: '密码',
                        dataIndex: 'password',
                        sortable: false,
                        editor: {xtype: 'textfield', minLength: 6, minLengthText:'密码至少6位'}
                    },
                    {
                        text: '级别',
                        dataIndex: 'userLevel',
                        sortable: false,
                        editor: {
                            xtype: 'combo',
                            store: Ext.create('Ext.data.Store', {
                                fields: ['name', 'value'],
                                data: [
                                    {"name": "admin", "value": "admin"},
                                    {"name": "personal", "value": "personal"},
                                    {"name": "department", "value": "department"}
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
                        sortable: false,
                        width: 60,
                        editor: {xtype: 'textfield'}
                    },
                    {text: '注册时间', dataIndex: 'registerTime', flex: 1},
                    {text: '有效账号', dataIndex: 'isValid', editor: {xtype: 'textfield'}},
                    {
                        //menuDisabled: true,
                        text: '更新或保存',
                        xtype: 'actioncolumn',
                        align: 'center',
                        items: [{
                            iconCls: 'iconfont-update',
                            tooltip: '提交修改',
                            handler: this.onUpdate
                        }]
                    }, {
                        //menuDisabled: true,
                        text: '失效',
                        xtype: 'actioncolumn',
                        align: 'center',
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
        loadStore: function () {
            this.getStore().load({});
        },
        //这边有个坑，就是方法名不能简单地写成OnAdd，否则会直接执行这个函数(估计是覆盖了原有方法)
        onAddUser: function () {
            var store = this.getStore();
            if (store.getAt(0).data['id'] == 0)
            //防止超过1次的新增导致grid选择的错误
                alert('先把之前的save完成');
            else {
                var rec = Ext.create('user_model_admin', {
                    id: 0,
                    username: 'user' + parseInt(1000 * Math.random() + 1),
                    password: 'pass',
                    userLevel: 'personal',
                    departmentId: 0,
                    registerTime: new Date().getTime() / 1000,
                    isValid: true
                });
                store.insert(0, rec);
                this.cellEditing.startEditByPosition({
                    row: 0,
                    column: 1
                })
            }
        },
        onUpdate: function (grid, rowIndex) {
            var rec = grid.getStore().getAt(rowIndex);
            console.log(rec.data);
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
                    } else if (obj.result == 'INSERT_SUCCESS') {
                        Ext.Msg.show({
                            title: 'save',
                            msg: '保存完成',
                            icon: Ext.Msg.INFO,
                            buttons: Ext.Msg.OK
                        });
                        grid.getStore().reload();
                    } else {
                        Ext.Msg.show({msg: '失败', buttons: Ext.Msg.OK});
                    }
                },
                failure: function (response) {
                    alert('服务器错误:' + response.status);
                }
            });
        },
        onDelete: function (grid, rowIndex) {
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
                            msg: 'DELETE_SUCCESS' + '@id=' + rec.data.id,
                            icon: Ext.Msg.INFO,
                            buttons: Ext.Msg.OK
                        });
                        grid.getStore().reload();
                    } else {
                        Ext.Msg.show({msg: '失败', buttons: Ext.Msg.OK});
                    }
                },
                failure: function (response) {
                    alert('服务器错误:' + response.status);
                }
            });
        },
        showDepartment: function () {
            var store = this.getStore();
            var grid = this;
            var records = grid.getSelectionModel().getSelection();
            if (records.length == 0)
                Ext.Msg.show({title: '显示部门detail', msg: '请选中需要显示的那一行', icon: Ext.Msg.INFO, buttons: Ext.Msg.OK});
            else {
                var id = records[0].data.departmentId;
                if (id == 0)
                    alert('这是一个特别的身份，无所属部门');
                else {
                    Ext.Ajax.request({
                        url: '<%=request.getContextPath()%>/admin/others/single/1/' + id,
                        success: function (response) {
                            var json = Ext.JSON.decode(response.responseText);
                            console.log(json);
                            Ext.Msg.show({
                                title: '部门信息',
                                msg: '<h4>用户:' + records[0].data.username + '</h4>' + '部门名字:' + json.name + '<br/>部门地址:' + json.location,
                                icon: Ext.Msg.INFO,
                                buttons: Ext.Msg.OK
                            });
                        },
                        failure: function () {
                            alert('服务器响应失败');
                        }
                    });
                }
            }
        }
    });
    Ext.define('department_model_admin', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'name', type: 'string'},
            {name: 'location', type: 'string'},
            {name: 'function', type: 'string'},
            {name: 'latitude', type: 'string'},
            {name: 'longitude', type: 'string'}]
    });
    Ext.define('department_store_admin', {
        extend: 'Ext.data.Store',
        model: 'department_model_admin',
        proxy: {
            type: 'ajax',
            url: '<%=request.getContextPath()%>/admin/query/1',
            reader: {
                type: 'json'
            }
        }
    });
    Ext.define('department_grid_admin', {
        extend: 'Ext.grid.Panel',
        alias: 'widget.departmentgrid',
        requires: [
            'Ext.grid.column.Action'
        ],
        height: 400,
        title: '企业信息管理',
        border: false,
        closable: true,
        initComponent: function () {
            this.cellEditing = new Ext.grid.plugin.CellEditing({
                clicksToEdit: 1
            });
            this.cellEditing.on('validateedit', function (editor, e) {
                if (e.value == e.originalValue)
                    return false;
                else
                    e.record.commit();
            });
            Ext.apply(this, {
                plugins: [this.cellEditing],
                selModel: {
                    selType: 'cellmodel'
                },
                tbar: [
                    {xtype: 'button', scope: this, text: '新增', width: 100, handler: this.onAddDepartment},
                    {xtype: 'button', scope: this, text: '刷新', width: 100, handler: this.loadStore}
                ],
                store: Ext.create('department_store_admin', {}),
                columns: [{
                    text: '序号',
                    dataIndex: 'id'
                }, {
                    text: '企业名',
                    dataIndex: 'name',
                    sotable: false,
                    editor: {xtype: 'textfield'}
                }, {
                    text: '详细地址',
                    dataIndex: 'location',
                    sotable: false,
                    flex: 1,
                    editor: {xtype: 'textfield'},
                    renderer: function (value, meta, record) {
                        meta.tdAttr = 'data-qtip="' + value + '"';
                        return value;
                    }
                }, {
                    text: '企业类型',
                    dataIndex: 'function',
                    editor: {
                        xtype: 'combo',
                        store: Ext.create('Ext.data.Store', {
                            fields: ['name', 'value'],
                            data: [
                                {"name": "ev_seller", "value": "ev_seller"},
                                {"name": "ev_buyer", "value": "ev_buyer"},
                                {"name": "sb_seller", "value": "sb_seller"},
                                {"name": "sb_buyer", "value": "sb_buyer"}
                            ]
                        }),
                        querymode: 'local',
                        displayField: 'name',
                        valueField: 'value'
                    }
                }, {
                    text: 'latitude',
                    dataIndex: 'latitude'
                }, {
                    text: 'longitude',
                    dataIndex: 'longitude'
                }, {
                    //menuDisabled: true,
                    text: '更新或保存',
                    xtype: 'actioncolumn',
                    align: 'center',
                    items: [{
                        iconCls: 'iconfont-update',
                        tooltip: '提交修改',
                        handler: this.onUpdate
                    }]
                }, {
                    //menuDisabled: true,
                    text: '失效',
                    xtype: 'actioncolumn',
                    align: 'center',
                    items: [{
                        iconCls: 'iconfont-delete',
                        tooltip: '删除记录',
                        handler: this.onDelete
                    }]
                }]
            });
            this.callParent();
            this.on('afterlayout', this.loadStore, this, {
                delay: 1,
                single: true
            });
        },
        loadStore: function () {
            this.getStore().load();
        },
        onAddDepartment: function () {
            var grid = this;
            var win = Ext.getCmp('window_for_departadd');
            if (win == undefined || win == null) {
                win = Ext.create('Ext.window.Window', {
                    title: 'Hello',
                    width: 400,
                    layout: 'fit',
                    frame: false,
                    border: false,
                    id: 'window_for_departadd',
                    items: [{
                        xtype: 'adddepartform'
                    }],
                    buttons: [{
                        text: '提交',
                        handler: function () {
                            var form = win.down('adddepartform');
                            if (form.isValid()) {
                                var data = form.getValues();
                                console.log(data);
                                var store = grid.getStore();
                                var rec = Ext.create('department_model_admin', {
                                    id: 0,
                                    name: data.name,
                                    location: data.location,
                                    function: data.function,
                                    latitude: data.latitude,
                                    longitude: data.longitude
                                });
                                store.insert(0, rec);
                                grid.cellEditing.startEditByPosition({
                                    row: 0,
                                    column: 1
                                });
                                win.hide();
                            } else {
                                alert('请完善表单');
                            }
                        }
                    }]
                });
            }
            win.show();

        },
        onUpdate: function (grid, rowIndex) {
            var rec = grid.getStore().getAt(rowIndex);
            console.log(rec);
            Ext.Ajax.request({
                url: '<%=request.getContextPath()%>/admin/update/1',
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
                    } else if (obj.result == 'INSERT_SUCCESS') {
                        Ext.Msg.show({
                            title: 'save',
                            msg: '保存完成',
                            icon: Ext.Msg.INFO,
                            buttons: Ext.Msg.OK
                        });
                        grid.getStore().reload();
                    } else {
                        Ext.Msg.show({msg: '失败', buttons: Ext.Msg.OK});
                    }
                },
                failure: function (response) {
                    alert('服务器错误:' + response.status);
                }
            });
        },
        onDelete: function (grid, rowIndex) {

        }

    });
    Ext.define('add_depart_form', {
        extend: 'Ext.form.Panel',
        alias: 'widget.adddepartform',
        layout: {
            type: 'vbox',
            align: 'center'
        },
        padding: 10,
        border: false,
        defaultType: 'textfield',
        initComponent: function () {
            var me = this;
            Ext.apply(this, {
                items: [{
                    fieldLabel: '名称',
                    name: 'name',
                    allowBlank: false
                }, {
                    xtype: 'textarea',
                    fieldLabel: '地址',
                    name: 'location',
                    grow: true,
                    allowBlank: false
                }, {
                    xtype: 'combo',
                    fieldLabel: '职能',
                    name: 'function',
                    store: Ext.create('Ext.data.Store', {
                        fields: ['name', 'value'],
                        data: [
                            {"name": "ev_seller", "value": "ev_seller"},
                            {"name": "ev_buyer", "value": "ev_buyer"},
                            {"name": "sb_seller", "value": "sb_seller"},
                            {"name": "sb_buyer", "value": "sb_buyer"}
                        ]
                    }),
                    querymode: 'local',
                    displayField: 'name',
                    valueField: 'value',
                    emptyText: 'ev_seller'
                }, {
                    fieldLabel: '纬度',
                    name: 'latitude',
                    allowBlank: false
                }, {
                    fieldLabel: '经度',
                    name: 'longitude',
                    allowBlank: false
                },
//                    {
//                    xtype: 'button',
//                    text: '根据名称在线获得经纬度',
//                    handler: function () {
//                        var url = 'http://api.map.baidu.com/geocoder/v2/?output=json&ak=8GER35MXbCi4MsTV40DGGbGa&address=';
//                        url = url + me.getValues().name;
//                        Ext.data.JsonP.request({
//                            url: url,
//                            timeout: 3000,
//                            success: function (response) {
//                                console.log(response);
//                                var location = response.result.location;
//                                if (response.status == 0) {
//                                    var record = Ext.create('department_model_admin');
//                                    record.data.name = me.getValues().name;
//                                    record.data.location = me.getValues().location;
//                                    record.data.function = me.getValues().function;
//                                    record.data.latitude = location.lat;
//                                    record.data.longitude = location.lng;
//                                    me.loadRecord(record);
//                                } else {
//                                    alert(response.msg);
//                                }
//                            },
//                            failure: function(res){
//                                console.log(res);
//                            }
//                        });
//                    }
//                },
                    {
                    xtype:'button',
                    text:'加载地图', width:'50%',
                    handler:function(){
                        var win = Ext.ComponentQuery.query('amapforsearch')[0];
                        if(win == undefined){
                            win = Ext.create('AmapForSearch',{title:'地图搜索',width:800,height:450});
                            win.on('afterrender',function(){
                                console.log('win afterrender');
                                AMap.asynLoadMap(AMap.key);
                            });
                        }
                        win.showAt((deviceWidth-800)/2,0);
                    }
                }]
            });
            this.callParent();
        }

    });
    Ext.define('AmapForSearch',{
        extend: 'Ext.window.Window',
        alias: 'widget.amapforsearch',
        layout:{
            type:'vbox',
            align:'stretch'
        },
        border:false,
        closeAction:'hide',
        //modal:true,
        initComponent:function(){
            Ext.apply(this,{
                items:[{
                    id:'amap_search', width:400, height:390
                },{
                    html: '<form id="search_form">搜索:<input type="text" id="suggestId" size="20" style="width:40%" title="搜索框"/></form>',
                    border:false
                }]
            });
            this.callParent();
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
