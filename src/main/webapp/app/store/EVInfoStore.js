/*工程车车辆信息的EvTreeGrid*/
Ext.define('BS.store.EVInfoStore',{
	extend: 'Ext.data.TreeStore',
	model: 'BS.model.EVInfoModel',
	proxy:{
		url:'ev/info/treegrid',
		type:'ajax',
		extraParams:{
            category:"normal",
			username: user_global
		}
	}
});