Ext.define('BS.view.NaviPanel',{
	extend:'Ext.panel.Panel',
	alias:'widget.navipanel',
	width:300,
	initComponent:function(){
		Ext.apply(this,{			
			layout:{
				type:'accordion',
			    titleCollapse: true,//不一定要点+或-就选中
			    animate: true,
			    align:'stretch',// 各子组件的宽度拉伸至与容器的宽度相等
			    fill:true,
			    multi:false
			},
			items: [{
			    title:'车辆查询',
			    xtype: 'panel',
			    html:'一些话'
			},{
			    title: '管理区',
			    xtype: 'panel',
			    html:'一些话'
			}]	
		});
		this.callParent();
	}
});