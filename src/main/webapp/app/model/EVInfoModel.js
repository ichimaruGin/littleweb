/*工程车车辆信息的EvTreeGrid*/
Ext.define('BS.model.EVInfoModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'tid',     type: 'string'},
        {name: 'licenseNumber',     type: 'string'},
        {name: 'department', type: 'string'},
        {name: 'rentStatus',     type: 'string'}
    ]
});