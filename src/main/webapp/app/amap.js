/**
 * Created by YY_410 on 2015/4/8.
 */

var AMap = {
    key: '8GER35MXbCi4MsTV40DGGbGa',
    smap: undefined,
    ac: undefined,  //Autocomplete对象
    mapUtils: undefined,
    asynLoadMap: function (key) {
        if (this.smap == undefined) {
            var script = document.createElement('script');
            script.src = "http://api.map.baidu.com/api?v=2.0&callback=smap_initialize&ak=" + key;
            document.body.appendChild(script);
        }
    }
};

var smap_initialize = function () {
    Ext.example.msg('map init complete!', '', 1);
    AMap.smap = new BMap.Map(amap_search);
    AMap.smap.centerAndZoom(new BMap.Point(120.486301, 30.086351), 16);
    AMap.mapUtils = new MapUtils();
    AMap.ac = new BMap.Autocomplete({
        'input': 'suggestId',
        'location': AMap.smap
    });
    //智能搜索(通过模糊查询获得名称对应的地址以及经纬度，将地址经纬度填入ADD表单)
    AMap.ac.addEventListener('onconfirm', function (e) {
        console.log(e);
        var _value = e.item.value;
        var sValue = _value.province + _value.city + _value.district + _value.street + _value.business;
        AMap.smap.clearOverlays();
        var local = new BMap.LocalSearch(AMap.smap, {
            onSearchComplete: function () {
                var pp = local.getResults().getPoi(0).point;
                AMap.mapUtils.addMarker(pp, AMap.smap);
                var msg = '地址:<br/><' + sValue + '><br/>';
                Ext.Msg.confirm('搜索结果', msg, function (btn) {
                    if ('yes' == btn) {
                        var addDepartWindow = Ext.getCmp('window_for_departadd');
                        var addDepartForm = addDepartWindow.down('adddepartform');
                        var record = Ext.create('department_model_admin');
                        record.data.location = sValue;
                        record.data.latitude = pp.lat;
                        record.data.longitude = pp.lng;
                        addDepartForm.loadRecord(record);
                        addDepartWindow.close();
                    }
                })
            }
        });
        local.search(sValue);
    });
}

//
var MapUtils = function MapUtils() {
};
MapUtils.prototype.addMarker = function (pp, map) {
    map.centerAndZoom(pp, 16);
    var marker = new BMap.Marker(pp);
    map.addOverlay(marker);
};





