/* 监控平台后台系统，页面数据动态生成 */

$(function(){
	
	// var localhost= "192.168.121.146";
	var documentBox = $(document);
	var popBg = $('.iframe_box')
	var popBgW = $('.iframe_boxW')
	var popLoading = $('.popLoading')

	var pushAllos = $(".pushAllos")						// 字典管理
	var js_query = $("#js_query");

	var js_pagelist = $(".js_pageList");
	var js_page_show = $("#js_page_show");

	var NowPage = 1;
	var page_cur = 1;
	var page_total = "";

	// 退出系统
	$(".btnBox").delegate(".exitBtn", "click", function(event){
		event.stopPropagation;
		var exitSystem = "true";
		closePop(".infoUp", ".iframe_box");
	});

	// 取消刷新页面
	$(document).delegate(".cancel", "click", function(event){
		event.stopPropagation;
		window.location.replace(window.location.href);
	});

	var sendInit = {
		"typename":"",	
		"currentpage":1,
		"type":"selectpz",
		"dictionarytype":1
	}
	dictionaryPushList(sendInit);
	function dictionaryPushList(sendDate){

		var js_PushTb = $("#js_PushTb");
		// var pageNumber = page;
		var addHtmlPushTB = '';

		$.ajax({
        type:"POST",
        url:portconfig+"push/PushConfiguration",
		data:sendDate,
        dataType : 'jsonp',
        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
	        success: function(json){
			var arrays = json.arrays;
			page_cur = json.page.currentpage;
			page_total = json.page.totalsize;
			//console.log(arrays);
			if(arrays != ''){
				if(page_cur <= page_total){
					if(page_total > 1){
						js_pagelist.show();
					}else{
						js_pagelist.hide();
					}
					js_page_show.html("");
					js_page_show.html(page_cur + " / " + page_total + "页");
				
					var addHtmlPushTr = '';
					for(var i=0;i<arrays.length;i++){

						var costom2 = arrays[i].Dictionary.code;
						var name = arrays[i].Dictionary.name;
						var type = arrays[i].Dictionary.type;
						var rulemap = arrays[i].rulemap;
						if(type == "1"){
							type = '否';
							$("#js_typePk").find("input[value='1']").prop("checked",true);
							$("#js_typePk").find("input[value='0']").prop("checked",false);
						}else{
							type = '是';
							$("#js_typePk").find("input[value='1']").prop("checked",false);
							$("#js_typePk").find("input[value='0']").prop("checked",true);
						}

						$("#js_typePk").find('input[type="radio"]').each(function(){
							$(this).on("click", function(){
								$(this).prop("checked",true);
								$(this).siblings().prop("checked",false);
							});	
						});

						var rulemapNum = rulemap.length;
						
						var addHtmlPushTb = '';
						var addHtmlPushTbInit = '';
						if(rulemapNum == 0){
							addHtmlPushTbInit += '<tr>'+
								    					'<td>' + costom2 + '</td>'+ 
													    '<td>' + name + '</td>'+ 
													    '<td>' + type + '</td>'+
									    				'<td>' + '</td>' +
														'<td>' + '</td>' +
														'<td>' + '</td>' +
														'<td>' + '</td>' +
														'<td>' + '</td>' +
														'<td>' + '</td>' +
													'</tr>';
							addHtmlPushTr = addHtmlPushTbInit;
						}else{
							for(var j=0;j<rulemapNum;j++){
								 addHtmlPushTbInit = '<tr>'+
								    					'<td rowspan="'+(rulemapNum+1)+'">' + costom2 + '</td>'+ 
													    '<td rowspan="'+(rulemapNum+1)+'">' + name + '</td>'+ 
													    '<td rowspan="'+(rulemapNum+1)+'">' + type + '</td>'+
													'</tr>';
							    	addHtmlPushTb += '<tr>'+
									    				'<td>' + rulemap[j][0][0].EVENT_REC_DOMAIN_UID + '</td>' +
														'<td>' + rulemap[j][0][0].EVENT_REC_TYPE + '</td>' +
														'<td>' + rulemap[j][0][0].EVENT_REC_SOAP_NOTICE_URL + '</td>' +
														'<td>' + rulemap[j][0][0].EVENT_SEND_DOMAIN_UID + '</td>' +
														'<td>' + rulemap[j][0][0].EVENT_SEND_NAME + '</td>' +
														'<td>' + rulemap[j][0][0].EVENT_SEND_TYPE + '</td>' +
													'</tr>';
								addHtmlPushTr=addHtmlPushTbInit + addHtmlPushTb;
							};
						};
						addHtmlPushTB += addHtmlPushTr;
					}
					//console.log(addHtmlPushTb);
					js_PushTb.html("");
					js_PushTb.append(addHtmlPushTB);
				}else{
					js_PushTb.html("");
					js_PushTb.append('<td colspan="9" class="overPage_L">' + '<p>' + "您输入的页码已超出当前内容总页码！" + '</p>' + '</td>');
					// js_PushTb.append('<tr>' + '<td colspan="9">' + "没有数据！" + '</td>' + '</tr>');
				}
			}else{
				js_PushTb.html("");
				js_PushTb.append('<td colspan="9" class="overPage_L">' + '<p>' + "未能查询到相关内容！" + '</p>' + '</td>');
				js_pagelist.hide();
			};	
		}
	});
		// },"json");
	}

	// 查询功能
	js_query.off("click").on("click",function(){

		var typePk = $("#js_typePk").find("input[name='typePk']:checked").attr("value");
		var keywordCon = $("#js_keywordCon").val();

		var sendSearch = {
			"typename":keywordCon,	
			"currentpage":1,
			"type":"selectpz",
			"dictionarytype":typePk
		}
		dictionaryPushList(sendSearch);
	});

	// 分页功能
	documentBox.undelegate("#js_page_home","click").delegate("#js_page_home", "click", function(){
		
		NowPage = 1;

		var typePk = $("#js_typePk").find("input[type='radio']:checked").attr("value");
		var sendPageNum = {
			"typename":"",	
			"currentpage":NowPage,
			"type":"selectpz",
			"dictionarytype":typePk
		}
		dictionaryPushList(sendPageNum);
		
	});
	
	documentBox.undelegate("#js_page_pre","click").delegate("#js_page_pre", "click", function(){
		
		page_cur -= 1;
		
		if(page_cur <= 0){
			page_cur = 1;
		}else{
			NowPage = page_cur;
		};
		
		var typePk = $("#js_typePk").find("input[type='radio']:checked").attr("value");
		var sendPageNum = {
			"typename":"",	
			"currentpage":NowPage,
			"type":"selectpz",
			"dictionarytype":typePk
		}

		dictionaryPushList(sendPageNum);
		 
	});
	
	documentBox.undelegate("#js_page_next","click").delegate("#js_page_next", "click", function(){
		
		page_cur = parseInt(page_cur) + 1;
		
		if(page_cur > page_total){
			page_cur = page_total
		}else{
			NowPage = page_cur;
	
		};
		
		var typePk = $("#js_typePk").find("input[type='radio']:checked").attr("value");
		var sendPageNum = {
			"typename":"",	
			"currentpage":NowPage,
			"type":"selectpz",
			"dictionarytype":typePk
		}
		dictionaryPushList(sendPageNum);
		
	});
	
	documentBox.undelegate("#js_page_end","click").delegate("#js_page_end", "click", function(){
		
		NowPage = page_total;
		
		var typePk = $("#js_typePk").find("input[type='radio']:checked").attr("value");
		var sendPageNum = {
			"typename":"",	
			"currentpage":NowPage,
			"type":"selectpz",
			"dictionarytype":typePk
		}
		dictionaryPushList(sendPageNum);
		
	});

	documentBox.undelegate("#js_page_jump","click").delegate("#js_page_jump", "click", function(){

		NowPage = parseInt($(this).siblings("input").val());
		
		var typePk = $("#js_typePk").find("input[type='radio']:checked").attr("value");
		var sendPageNum = {
			"typename":"",	
			"currentpage":NowPage,
			"type":"selectpz",
			"dictionarytype":typePk
		}
		dictionaryPushList(sendPageNum);
	});
	/* loading 效果功能 */
	function loadingShow (bgColor, AjaxStyle, message){
		var ajaxShow;
		AjaxStyle == "" ? ajaxShow = "" : ajaxShow = AjaxStyle; 
		popBgW.addClass(bgColor);
		popLoading.addClass(AjaxStyle);
		popLoading.html("");
		popLoading.append('<p class="loading_info">' + message + '</p>');
		showPop("."+AjaxStyle,".iframe_boxW");
	}
	
	function closeLoading (bgColor, AjaxStyle){
		popBgW.css("display","none");
		popLoading.css("display","none");
		popBgW.removeClass(bgColor);
		popLoading.removeClass(AjaxStyle);
		popLoading.html("");
		closePop("."+AjaxStyle,".iframe_boxW");
	}

	// 保存提示功能
	function successAll(title, messge){
		var nameTip = $(this).attr("type");
		var popUp_p = $(".popUp_p");
		var popUp_head = $(".popUp_head");
		var Confirm = $('.btnBox input[name="Confirm"]')
		showPop(".infoUp", ".iframe_box");
		popUp_p.text("");
		popUp_p.html('<strong class="successAll">' + messge + '</strong>');
		popUp_head.find("span").text(title);
		$(".btnBox").show();
		Confirm.removeClass();
		Confirm.addClass("button_red saveInfoBtn mr15");
	}
	// 删除提示功能
	function delAll(title, messge){
		var nameTip = $(this).attr("type");
		var popUp_p = $(".popUp_p");
		var popUp_head = $(".popUp_head");
		var Confirm = $('.btnBox input[name="Confirm"]')
		showPop(".infoUp", ".iframe_box");
		popUp_p.text("");
		popUp_p.html('<strong class="delAll">' + messge + '</strong>');
		popUp_head.find("span").text(title);
		$(".btnBox").show();
		Confirm.removeClass();
		Confirm.addClass("button_red delInfoBtn mr15");
	}	

	// 测试成功提示功能
	function passAll(title, messge){
		var popUp_p = $(".popUp_p");
		var popUp_head = $(".popUp_head");
		showPop(".infoUp", ".iframe_box");
		popUp_p.text("");
		popUp_head.find("span").text("");
		popUp_p.html('<strong class="passAll">' + messge + '</strong>');
		popUp_head.find("span").text(title);
		$(".btnBox").hide();
	}
	// 报错提示功能
		
	function errorsAll(messge){
		var popUp_p = $(".popUp_p");
		var popUp_head = $(".popUp_head");
		showPop(".infoUp", ".iframe_box");
		popUp_p.text("");
		popUp_head.find("span").text("");
		popUp_p.html("<b>" + messge + "！</b>");
		popUp_head.find("span").text("错误");
		$(".btnBox").hide();
	}	
});