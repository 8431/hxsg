
// 跨浏览器获取视口大小
function showPop(content, mask){
	var bH = getInner().height + $(window).scrollTop();
	var bW = getInner().width;
	var _this = $(content);
	var _thisWidth = _this.width();
	var _thisHeight = _this.height();
	var top = parseInt((getInner().height - _thisHeight)/2 + $(window).scrollTop());
	var left = parseInt((getInner().width - _thisWidth)/2);
	
	//$("body").css("overflow","hidden");
	$(mask).css({width:bW,height:bH,display:"block"});
	
	_this.css("display","block");
	_this.css({top:top,left:left});


	$(window).resize(function(){
		var bH = getInner().height + $(window).scrollTop();
		var bW = getInner().width;
		var _thisWidth = _this.width();
		var _thisHeight = _this.height();
		var top = parseInt((getInner().height - _thisHeight)/2 + $(window).scrollTop());
		var left = parseInt((getInner().width - _thisWidth)/2);
		
		_this.css({top:top,left:left});
		
	});
	
};
		
//关闭灰色JS遮罩层和操作窗口
function closePop(content, mask){
	$(mask).css("display","none");
	$(content).css("display","none");
}
/* end 弹出框功能区*/