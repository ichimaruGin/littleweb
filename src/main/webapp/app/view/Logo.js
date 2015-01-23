Ext.define('BS.view.Logo',{
	extend:'Ext.panel.Panel',
	alias:'widget.logo',
	
	requires:['Ext.Img',
			  'Ext.toolbar.Toolbar',
	          'Ext.toolbar.TextItem',
			  'Ext.form.Label'],
	border:false,
	initComponent:function(){
		var me = this;
		Ext.apply(this,{
			items:[{
			    xtype: 'toolbar',
				style:'width: 1200px;height: 28px;padding-left:0px;padding-right:10px;background-color:#157FCC ',
			    items: ['','-',{
					xtype:'label',
					text:'管理',
					textAlign:'center',
					listeners:{
						el:{
							click:function(){
								
							},
						},
						afterrender:{
							fn:function(me){
								me.getEl().dom.style.cursor = 'pointer'; //手型鼠标
								me.getEl().dom.title = '增删查改';
								me.getEl().dom.onmouseover = function(){
									btn.btnInnerEl.dom.style.color = 'blue';									
								};
								me.getEl().dom.onmouseout = function(){
									btn.btnInnerEl.dom.style.color = 'white';
								};
							}
						}
					}
				},
				'->',
					{xtype:'label',name:'welcome',style:'color:white'},'-',{xtype:'label',name:'currentTime',style:'color:white'},
					{xtype:'label',html:'<a href="login/logout" title="注销" style="color:white;TEXT-DECORATION: none;">注销</a>'},
					
					
			    ]
			},{
				xtype:'image',
				baseCls:'',
				border:1,
				src:'resources/images/logo1200.png',
				style:'width: 1200px;height: 115px;'
			}],
			listeners:{
				afterrender:function(){
					var timeLabel = me.down('toolbar').down('label[name=currentTime]');
					config = {
							interval: 1000,
							run: function(){
								timeLabel.update(Ext.Date.format(new Date(),'Y-m-d H:i:s'));
							}
						};
					Ext.TaskManager.start(config);
					var welcome = me.down('toolbar').down('label[name=welcome]');
					welcome.setText('欢迎:'+user_global);
				}
			}
		});
		this.callParent();
	}
});
