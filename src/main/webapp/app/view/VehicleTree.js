/**
 * Created by YY_410 on 2015/4/13.
 */
Ext.define('BS.view.VehicleTree',{
   extend: 'Ext.tree.Panel',
    alias: 'widget.vehicletree',
    requires: [
        'BS.store.VehicleTreeStore'
    ],
    height:400,
    width:'90%',
    padding:10,
    useArrows:true,
    initComponent:function(){
        var store = Ext.create('BS.store.VehicleTreeStore',{});
        Ext.apply(this,{
            store: store,
            viewConfig: {
                plugins: {
                    ptype: 'treeviewdragdrop',
                    containerScroll: true
                }
            }
        });
        this.callParent();
    }
});