// JavaScript Document

/* 公用函数 开始 */

function getInner(){
	if(typeof window.innerWidth != 'undefined'){ // 火狐
		return{
			width: window.innerWidth,
			height: window.innerHeight
		}
	}else{
		return{
			width: document.documentElement.clientWidth,
			height: document.documentElement.clientHeight
		}
	}
};

/* table div 各行换色 修改，添加等操作 */
function tableColor(){
	var tr = $(".sysTable tbody tr:odd");
	var trPush = $(".sysTablePush tbody tr:even");
	tr.addClass("tr_bg");
	trPush.addClass("tr_bg");
}

function divColor(){
	var div = $(".colorChange div.businessMain:odd, .sendingMainIN div.sendingMain:odd");
	div.addClass("div_bg");
}

function liColor(liTarget){
	var li = $(liTarget);
	li.addClass("div_bg");
}

// /* table点击高亮 */
$(document).on("click",".admin_table tr, .sysTable tr",function(){	
	if($(this).closest("table").find(".inSetTB").length == 0){
		$(".admin_table tr").removeClass("click");
		$(".sysTable tr").removeClass("click");
		$(this).addClass("click");
	}		
});

/* 公用函数 结束 */


/* page_body control_main 页面高度,宽度; 表格换色 */
$(function(){
	pageInit_businessM();
	tableColor();
	divColor();
});

function pageInit_businessM(){
	var pageBody = $(".page_body");
	var page_bodyMatch = $(".page_bodyMatch");
	var page_bodyData = $(".page_bodyData");
	var pageAllo = $(".pageAllo");
	var page_bodyMain = $(".page_bodyMain");
	var allMain = $(".allMain");
	var beside_Con = $(".beside_Con");
	var besidePush_Con = $(".besidePush_Con");
	var leftMain = $(".left_main_business");
	var wordbook = $(".wordbook");
	var lisTable = $(".lisTable");
	var update = $(".update");
	var rightSecondary = $(".right_secondary_business");
	var right_secondary_push = $(".right_secondary_push");
	var right_secondary_editor = $(".right_secondary_editor");
	var right_secondary_view = $(".right_secondary_view");
	var scrollAllo = $(".scrollAllo");
	var thanConBox_L = $(".thanConBox_L");
	var bH, bW;
	bH = parseInt(getInner().height);
	bW = parseInt(getInner().width);
	pageBody.css("height", bH-61);
	page_bodyMatch.css("height", bH-103);
	page_bodyMatch.css("width", bW);
	pageAllo.css("width", bW);
	pageAllo.css("height", bH-103);
	allMain.css("height", bH-103);
	leftMain.css("height", bH-103);
	wordbook.css("width", bW-355);
	lisTable.css("height", bH-274);
	update.css("height", bH-187);
	page_bodyMain.css("width", bW-40);
	page_bodyMain.css("height", bH-165);
	beside_Con.css("height", bH-141);
	besidePush_Con.css("height", bH-186);
	rightSecondary.css("width", bW-320);
	rightSecondary.css("height", bH-103);
	right_secondary_push.css("width", bW-320);
	right_secondary_push.css("height", bH-148);
	right_secondary_editor.css("width", bW-320);
	scrollAllo.css("width", bW-320);
	scrollAllo.css("height", bH-146);
	right_secondary_view.css("width", bW-606);
	right_secondary_view.css("height", bH-144);
	thanConBox_L.css("height", bH-236);
	$(window).resize(function(){
		bH = parseInt(getInner().height);
		bW = parseInt(getInner().width);
		pageBody.css("height", bH-61);
		page_bodyMatch.css("height", bH-103);
		page_bodyMatch.css("width", bW);
		pageAllo.css("width", bW);
		pageAllo.css("height", bH-103);
		allMain.css("height", bH-103);
		leftMain.css("height", bH-103);
		wordbook.css("width", bW-355);
		lisTable.css("height", bH-274);
		update.css("height", bH-187);
		page_bodyMain.css("width", bW-40);
		page_bodyMain.css("height", bH-165);
		beside_Con.css("height", bH-141);
		besidePush_Con.css("height", bH-186);
		rightSecondary.css("width", bW-320);
		rightSecondary.css("height", bH-103);
		right_secondary_push.css("width", bW-320);
		right_secondary_push.css("height", bH-148);
		right_secondary_editor.css("width", bW-320);
		scrollAllo.css("width", bW-320);
		scrollAllo.css("height", bH-146);
		right_secondary_view.css("width", bW-606);
		right_secondary_view.css("height", bH-144);
		thanConBox_L.css("height", bH-236);
	});
};


/*--------------------------------------------------------------------*/

/* 弹窗 公共信息 */
$(function(){
	
	$(document).on("click", ".head_tuichu", function(event){
		event.stopPropagation;
		var nameTip = $(this).attr("name");
		var popUp_p = $(".popUp_p");
		var popUp_head = $(".popUp_head");
		var Confirm = $('.btnBox input[name="Confirm"]')
		showPop(".infoUp", ".iframe_box");
		if(nameTip == "exit"){
			popUp_p.text("");
			popUp_head.find("span").text("");
			popUp_p.text("您确定要退出吗？");
			popUp_head.find("span").text("退出");
			$(".btnBox").show();
			Confirm.removeClass();
			Confirm.addClass("button_red exitBtn");
		}
	});
	
	$("body").on("click", ".popClose", function(event){
		event.stopPropagation;
		closePop(".infoUp", ".iframe_box");
        closePop(".infoUpPop", ".iframe_box");
		$(".btnBox").show();
	})

});

/* 匹配查询方法 */
function findCompare(sFind, sObj){  
	var nSize = sFind.length;  
	var nLen = sObj.length;   
	var sCompare;  
   
	if(nSize <= nLen ){  
		for(var i = 0; i <= nLen - nSize + 1; i++){  
			sCompare = sObj.substring(i, i + nSize);  
			if(sCompare == sFind){  
				return i;  
			}  
		}  
	}  
	return -1;  
}


