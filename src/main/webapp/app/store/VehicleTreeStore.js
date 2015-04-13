/**
 * Created by YY_410 on 2015/4/13.
 */
Ext.define('BS.store.VehicleTreeStore',{
   extend: 'Ext.data.TreeStore',
    proxy:{
        type:'ajax',
        url: 'vehicle/tree/category',
        reader:{
            type: 'json',
            root: 'children'
        },
        extraParams:{
            category:"type",
            username: user_global  //全局用户名
        }
    },
    root:{
        text:'车辆图',
        id:'root',
        expanded: true
    },
    folderSort: true,
    sorters:[{
        property: 'text',
        direction: 'ASC'
    }]
});