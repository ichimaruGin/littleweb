var clientWidth;
var clientHeight;
Ext.define('SXFJ.controller.Main', {
    extend: 'Ext.app.Controller',

	//refs中定义的内容，对于Ext.window.Window这种没有直接渲染的组件，需要自己先new一个
	init:function(){
		this.control({
			
		});
	},
	onLaunch:function(){
		document.getElementById('loading_id').parentNode.removeChild(document.getElementById('loading_id'));//删除载入界面的div
		Ext.tip.QuickTipManager.init(); //初始化quicktip
		Ext.example.msg('欢迎','',2);
//		var regex = new RegExp("^(AI|RA)\\d{2}$");
//		console.log(regex.test('AI000'));
//		console.log(regex.test('RA00'));
	},
	getXY:function(){
		document.write(
				"屏幕分辨率为："+screen.width+"*"+screen.height
				+"<br />"+
				"屏幕可用大小："+screen.availWidth+"*"+screen.availHeight
				+"<br />"+
				"网页可见区域宽："+document.body.clientWidth
				+"<br />"+
				"网页可见区域高："+document.body.clientHeight
				+"<br />"+
				"网页可见区域宽(包括边线的宽)："+document.body.offsetWidth 
				+"<br />"+
				"网页可见区域高(包括边线的宽)："+document.body.offsetHeight 
				+"<br />"+
				"网页正文全文宽："+document.body.scrollWidth
				+"<br />"+
				"网页正文全文高："+document.body.scrollHeight
				+"<br />"+
				"网页被卷去的高："+document.body.scrollTop
				+"<br />"+
				"网页被卷去的左："+document.body.scrollLeft
				+"<br />"+
				"网页正文部分上："+window.screenTop
				+"<br />"+
				"网页正文部分左："+window.screenLeft
				+"<br />"+
				"屏幕分辨率的高："+window.screen.height
				+"<br />"+
				"屏幕分辨率的宽："+window.screen.width
				+"<br />"+
				"屏幕可用工作区高度："+window.screen.availHeight
				+"<br />"+
				"屏幕可用工作区宽度："+window.screen.availWidth
				);
	}
});
