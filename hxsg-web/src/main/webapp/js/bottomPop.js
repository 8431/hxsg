// JavaScript Document

function alertBottomPop(alertInfo){
	console.log("OK!");
	var bodyBox = $("body");
	var addStyle = "";
	var addABPop ="";
	var addAlertContent = alertInfo;
	
	
	addStyle +=  '<style type="text/css">' +
				 '<!-- /* 样式列表 */' +
					   '#alertBottomBox { position: fixed; right: 1px; bottom: 0; z-index: 100; _position: absolute; _top: expression(eval(document.documentElement.scrollTop+100)); }' +
					   '#alertBottomBox .alertBottomInfo { position: absolute; top: 0; right: 0; font-family:"Microsoft YaHei","宋体","Arial Narrow";  background-color: #FFF; font-weight: bold; width: 432px; height: 378px; padding:0; overflow: hidden; border: 2px solid #cac7cd; }' +
					   '#alertBottomBox .alertBottomInfo .alertBottomTitle { width: 100%; height: 32px; background-color: #d9e0e8; border-bottom: 1px solid #cac7cd;}' +
					   '#alertBottomBox .alertBottomInfo .alertBottomContent { width: 412px; height: 325px; padding: 10px; overflow-y: auto; font-weight: normal; text-align: left;}' +
					   '#alertBottomBox .alertBottomInfo .alertBottomContent a { color: #7ba0cf;}' +
					   '#alertBottomBox .alertBottomInfo .alertBottomTitle h1 { float: left; line-height: 32px; text-align: left; text-indent: 10px;}' +
					   '#alertBottomBox .alertBottomInfo .alertBottomTitle .closeAlertBottom { display: block; float: right; width: 16px; height: 16px; margin: 8px 10px 0 0; cursor: pointer;} ' +
					   '' +
					   '' +
			     '--></style>';
	
	bodyBox.eq(0).append(addStyle);
	
	addABPop += '<div id="alertBottomBox">' +
					'<div class="alertBottomInfo">' +
						'<div class="alertBottomTitle">' +
							'<h1>监控系统提醒</h1>' +
							'<span class="closeAlertBottom"><img src="images/close.png" /></span>' +
						'</div>' +
						'<div class="alertBottomContent">' +
						'</div>' +
					'</div>' +
				'</div>';
				
	bodyBox.eq(0).append(addABPop);
	
	var alertBottomContent = $(".alertBottomContent");
	
	alertBottomContent.html("");
	alertBottomContent.html(addAlertContent);
	$("#alertBottomBox").animate({bottom:'+382px'}, "slow");
	$(".closeAlertBottom").on("click",function(){
			$("#alertBottomBox").animate({bottom:'0'}, "slow");
	});
}

$(function(){
	/*alertBottomPop('<p>测试<a href="">链接内容</a>，效果页面</p><p>测试<a href="">链接内容</a>，效果页面</p><p>测试<a href="">链接内容</a>，效果页面</p><p>测试<a href="">链接内容</a>，效果页面</p>');*/
});