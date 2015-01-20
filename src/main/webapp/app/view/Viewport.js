Ext.define('BS.view.Viewport', {
    extend: 'Ext.container.Viewport',
    requires:[
        'Ext.layout.container.Fit',
    ],

	layout:{
		type:'hbox',
		//align:'stretch',
		pack:'center'
	},
	width:'100%',
	minWidth:1200,
	minHeight:750,
	autoScroll:true,
    items: [{
        
    }]
});
