Ext.define('BS.view.LoginForm',{
	extend: 'Ext.form.Panel',
	alias: 'widget.loginform',
	title: '登录验证',
	width:300,//默认大小
	requires:['Ext.form.Label'],
	border:false,
	initComponent:function(){
		var me = this;
		Ext.apply(this,{
			layout:'anchor',
			defaults:{		
				width:200,
				anchor:'95%',
				allowBlank:false, //默认不许空白
				padding:10,
			},
			labelWidth: 30, // 标签的宽度
			defaultType:'textfield',
			items:[{					
				fieldLabel:'用户名',
				name:'username',
				emptyText:'请输入用户名',
				blankText:'请务必输入用户名',
			    enableKeyEvents:true,
			    listeners:{
			        	keypress:{
			        		fn:function(self,e){		
			    				if(e.getKey() == 13){
						        	if(me.isValid())  //信息格式验证正确，提交表单，验证信息内容
						        		me.fireEvent('loginaction',me.getValues()); //触发自定义的事件 
						        	else{
						        		 Ext.Msg.alert('提示','     输入信息格式错误           ');	            		
						        	}
			    				}
			        		}
			        	}
			   }
			},{
				fieldLabel:'密码',
				name:'password',
				emptyText:'请输入密码',
				blankText:'请务必输入密码',
				inputType: 'password' ,
		        enableKeyEvents:true,
		        listeners:{
		        	keypress:{
		        		fn:function(self,e){		
		    				if(e.getKey() == 13){
					        	if(me.isValid())  //信息格式验证正确，提交表单，验证信息内容
					        		 me.fireEvent('loginaction',me.getValues()); //触发自定义的事件
					        	else{
					        		 Ext.Msg.alert('提示','     输入信息格式错误           ');	            		
					        	}
		    				}
		        		}
		        	}
		        }
			}],			
			buttonAlign:'center',
		    buttons: [{
		        text: '重置',       
		        handler:function(){               	
		        	me.form.reset();
		        }
		    },{
		        text: '登录',
		        itemId:'login',
		        handler:function(){
		        	if(me.isValid()){  //信息格式验证正确，提交表单，验证信息内容
		        		me.fireEvent('loginaction',me.getValues()); //触发自定义的事件;
		        	}
		        	else{
		        		 Ext.Msg.alert('提示','     输入信息格式错误           ');	            		
		        	}
		        }
		    }]
		});		
		this.callParent();
		this.addEvents('loginaction');//自定义登录验证事件（需放在callParent后）
	}
	
});
