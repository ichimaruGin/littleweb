Ext.define('BS.view.EVGridTree',{
	extend:'Ext.tree.Panel',
	alias:'widget.evgridtree',
	requires: [
	    'BS.store.EVInfoStore'
	],  
	 height: 500,
	 width: 900,
     useArrows: true,
     rootVisible: false,  //会自动load store ；若为true，则不会自动load
     multiSelect: true,
    // singleExpand: true,
    // hideHeaders:true, 
    // expanded:false,
     
     initComponent: function() {   
    	 var me = this;
         Ext.apply(this, {
        	 layout:{
        		 type:'vbox',
        		 align:'center'
        	 },
        	 store: Ext.create('BS.store.EVInfoStore'),  	
             columns: [{
                 xtype: 'treecolumn', //this is so we know which column will show the tree
                 text: '车辆编号',
                 flex: 2,
                 menuDisabled : true,
                 sortable: true,
                 dataIndex: 'tid'
             },{
                 text: '车牌号码',
                 flex: 1,                 
                 menuDisabled : true,
                 sortable: true,
                 align: 'center',
                 dataIndex: 'licenseNumber',
             },{
                 text: '所属公司',
                 flex: 4,
                 menuDisabled : true,
                 sortable: false,
                 align: 'center',
                 dataIndex: 'department',
             },{
            	text: '租赁状态' ,
            	//tooltip: '单击显示详细租赁信息',
                menuDisabled : true,
                sortable: false,
                align: 'center',
                dataIndex: 'rentStatus',
             },{
                 text: '编辑',
                 width: 55,
                 flex: 1,
                 menuDisabled: true,
                 xtype: 'actioncolumn',
                 tooltip:'编辑',
                 align: 'center',
                 icon: 'resources/icons/edit_task.png',
                 handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
                     Ext.MessageBox.show({
                         title:'编辑'+record.get('machineSno'),
                         width:420,
                         msg: '请选择您的操作 (删除该纺机的信息|修改该纺机的信息)?',
                         buttons: Ext.MessageBox.YESNOCANCEL,
                         buttonText:{ 
                             yes: "删除", 
                             no: "修改",
                             cancel:'取消'
                         },
                         fn: function(btnText){
                        	 if(btnText=='yes'){
                        		 //对应删除操作
                        		 me.fireEvent('deletemachine',record.get('machineSno'));//触发machinetree的deletemachine事件,将机器型号传过去
                        	 }else if(btnText=='no'){
                        		 //对应修改操作
                        		 me.fireEvent('modifymachine',record.get('machineSno'));//触发machinetree的modifymachine事件,将机器型号传过去
                        	 }
                         },
                         animateTarget: this,
                         icon: Ext.MessageBox.QUESTION
                     });
                 },
                 //只有叶子节点的可以被编辑
                 isDisabled: function(view, rowIdx, colIdx, item, record) {
                     return !record.data.leaf;
                 }
             }],
             items:[{
            	 xtype:'button',
            	 text:'新增纺机'
             }]

         });
         this.callParent();
         me.addEvents('modifymachine');
         me.addEvents('deletemachine');//为machinetree增加两个事件
     }
});