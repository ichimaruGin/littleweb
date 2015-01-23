Ext.define('BS.view.Main',{
	extend: 'Ext.panel.Panel',
	alias: 'widget.main',
	requires:[
	     'BS.view.Logo'
	],
	layout:'border',
    border:false,
    width:1200,
    height:'100%',
    items:[{
    	region: 'north',
    	xtype:'logo'
    },{
    	region:'center',
    	xtype:'panel',
    	title:'panel'
    }]
});