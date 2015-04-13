Ext.define('BS.view.Main',{
	extend: 'Ext.panel.Panel',
	alias: 'widget.main',
	requires:[
	     'BS.view.Logo',
	     'BS.view.NaviPanel'
	],
	layout:'border',
    border:false,
    width:1200,
    height:'100%',
    items:[{
    	region: 'north',
    	xtype:'logo'
    },{
    	region:'west',   	
    	xtype:'navipanel',border:false,
    	style:'border-style:solid;border-width: 0px 1px 0px 1px;',
//    	iconCls:'treeleaf',
    	title:'功能导航'
    },{
    	region:'center',
    	xtype:'tabpanel',
    	itemId:'main_tabpanel',
        border:false,
        style:'border-style:solid;border-width: 0px 1px 0px 0px;',
        layout:'fit',
    	items:[{
    		xtype:'panel',
    		title:'车辆信息'
    	}]
    }]
});