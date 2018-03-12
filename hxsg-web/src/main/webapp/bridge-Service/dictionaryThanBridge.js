$(function(){
	
	// var localhost = "http://localhost/controlSystemDictionary/";
	var documentBox = $(document);
	var popBg = $('.iframe_box')
	var popBgW = $('.iframe_boxW')
	var popLoading = $('.popLoading')

	var js_query = $("#js_query");

	var js_pageList = $(".js_pageList");
	var js_page_show = $("#js_page_show");

	var js_hiup_bank = $("#js_hiup_bank");								// 平台 库
	var js_keywordCon = $("#js_keywordCon");							// 平台 查询的表
	var js_queryBlock = $("#js_queryBlock");							// 平台 表

	var NowPage = 1;
	var page_cur = 1;
	var page_total = "";
	var searchVal = "";

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

	// 查询 库注入
	queryKu();
	function queryKu(){

		var addHtmlKu = '';
		$.ajax({
	        type:"POST",
	        url:portconfig + "match/queryDatabaseAndTableName",
	        dataType:'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
	        success: function(json){
				console.log(json);
				var arrays = json.arrays;

				for(var n=0;n<arrays.length;n++){
					addHtmlKu += '<option>' + arrays[n] + '</option>';
				};
				js_hiup_bank.html(addHtmlKu);
				
				// 获取库下的表
				var sendDateBank = {
					"database":js_hiup_bank.find("option:selected").text()
				}
				queryTable(sendDateBank);
			}
		});
	};

	// 查询 表注入
	function queryTable(sendDate){

		var addHtmlTable = '';
		$.ajax({
	        type:"POST",
	        url:portconfig + "match/queryDatabaseAndTableName",
	        data:sendDate,
	        dataType:'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
	        success: function(json){
				console.log(json);
				var arrays = json.arrays;

				for(var m=0;m<arrays.length;m++){
					addHtmlTable += '<li value="' + arrays[m].SOURCE_TABLE + '" alt="' + arrays[m].CONSUME_TABLE + '" sourcePk="' + arrays[m].SOURCE_DBMANAGE_PK + '" hiupPk="' + arrays[m].HIUP_DBMANAGE_PK + '" consumePk="' + arrays[m].CONSUME_DBMANAGE_PK + '">' + arrays[m].HIUP_TABLE + '</li>';
				};
				js_queryBlock.html(addHtmlTable);

				//点击任意位置 隐藏搜索内容盒子
				documentBox.off("click").on("click", function(){
					js_queryBlock.hide();
				});

				// 点击输入框显示内容
				js_keywordCon.on("click",function(event){
					event.stopPropagation();
					js_queryBlock.show();
				});

		    	// 模糊查询
				var columnLi = js_queryBlock.find("li");
				var tableLeftInputVal = js_keywordCon.val();
		    	columnLi.each(function(i){
					$(this).on("click", function(){
						var infoLi = $(this).text();
						js_keywordCon.val("");
						js_keywordCon.val(infoLi);
					});
				});

		    	$(".pRelative").delegate("#js_keywordCon", "keyup", function(){
					
					var tableLeftInputVal = js_keywordCon.val();
					tableLeftInputVal = $.trim(tableLeftInputVal); // 用jQuery的trim方法删除前后空格
					
					if(tableLeftInputVal == ""){
						js_queryBlock.hide();
						return false;
					}else{
						js_queryBlock.show();
						columnLi.hide();

						var nPos;
						var True = false;
						
						for(var i = 0; i<columnLi.length; i++){
							var sTxt = $(columnLi[i]).text();
							nPos = findCompare(tableLeftInputVal, sTxt); 
							if(nPos>=0){  
								columnLi.eq(i).show();
								True = true;
							}
						}
						if(!True){
							js_queryBlock.hide();
							True = true;
						}
					}
				});
			}
		});
	};
	$("#js_query").click(function(){
		// loadingShow("save","保存提示功能");
		var is_hiup_table = js_keywordCon.val();
		var sendDateQuery = {};
		
		js_queryBlock.find("li").each(function(){
			if($(this).text() == is_hiup_table){
				sendDateQuery = {
					"database":js_hiup_bank.find("option:selected").text(),
					"tableName":[$(this).attr("value"),is_hiup_table,$(this).attr("alt")],
					"pkSource":[$(this).attr("sourcePk"),$(this).attr("hiupPk"),$(this).attr("consumePk")],
					"currentPage":1,
					"url":"match/moHuComparisonOfData"
				}
			}
		});
		console.log(sendDateQuery);
		dictionaryThan(sendDateQuery);
	});
	
	// 页面间传值
	// var thisURL = document.URL; 
 //    var getval = thisURL.split('?')[1];
 //    var arraysVal = getval.split(';');
 //    var sendSourceCloumnArr = arraysVal[0].split(']')[0].split(',');
 //    var sendHiupCloumnArr = arraysVal[0].split(']')[1].split(',');
 //    var sendShopCloumnArr = arraysVal[0].split(']')[2].split(',');

 //    var sendSourcePkCloumnArr = arraysVal[1].split(']')[0].split(',');
 //    var sendHiupPkCloumnArr = arraysVal[1].split(']')[1].split(',');
 //    var sendShopPkCloumnArr = arraysVal[1].split(']')[2].split(',');

 //    var sendTableNameArr = arraysVal[2].split(',');										//表名----顺序是   源表名-平台表名-消费列表名
 //    var sendPkSourceArr = arraysVal[3].split(',');										//对应字典表数据管理Pk 顺序是   源-平台-消费列
										
 //    var pkCloumn= [sendSourcePkCloumnArr, sendHiupPkCloumnArr, sendShopPkCloumnArr];	//主键 顺序是   源主键字段-平台主键字段-消费列主键字段								
 //    var Cloumn= [sendSourceCloumnArr, sendHiupCloumnArr, sendShopCloumnArr];			//页面配置字段   顺序是   源-平台-消费列
  
	// var sendData11={
	//     "Cloumn":Cloumn,
	//     "pkCloumn":pkCloumn,
	// 	"tableName":sendTableNameArr,
	// 	"pkSource":sendPkSourceArr,
	// 	"currentPage":NowPage
	// }
	// console.log(sendData11);
	var tableName=["A","A","A"];//表名----顺序是   源表名-平台表名-消费列表名
  var pkCloumn= [["A", "B"], ["A","B"], ["A", "B"]];//主键 顺序是   源主键字段-平台主键字段-消费列主键字段
  var pkSource=[10,10,10];//对应字典表数据管理Pk 顺序是   源-平台-消费列
  var Cloumn= [["A", "B"], ["A","B"], ["A", "B"]];//页面配置字段   顺序是   源-平台-消费列
  var currentPage=1;
   var sendData11={
   "Cloumn":Cloumn,
   "pkCloumn":pkCloumn,
	"tableName":tableName,
"pkSource":pkSource,
"currentPage":currentPage,
"url":"match/comparisonOfData"
  }
	dictionaryThan(sendData11);
	function dictionaryThan(sendDate){
		console.log("OK");
		var addHtmlThanTable = '';
		var is_table_than = $("#is_table_than");

		$.ajax({
	        type:"POST",
	        url:portconfig + sendDate.url,
			data:sendDate,
	        dataType:'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
	        success: function(json){
				
				console.log(json)
				var arrays = json.arrays;
				var column = json.column;

				page_cur = json.page.currentpage;
				page_total = json.page.totalsize;

				is_table_than.css("width",330*column.length);										     	// 计算多个table的宽度								

				page(sendDate,dictionaryThan)
				for(var j=0;j<column.length;j++){

					var addHtmlThanTr = '';
					for(var i=0;i<arrays.length;i++){
						addHtmlThanTr += '<tr>' + 
					                        '<td>' + arrays[i].consum[1] + '</td>' +
					                        '<td>' + arrays[i].platform[1] + '</td>' +
					                        '<td>' + arrays[i].source[1] + '</td>' +
					                    '</tr>' ;
					};

					addHtmlThanTable +=  '<table>' +	
											'<thead>' +
							                    '<tr>' +
							                        '<th colspan="3">' + column[j] + '</th>'+
							                    '</tr>' +
							                '</thead>' +
							                '<tbody>' + addHtmlThanTr + '</tbody>' + 
							            '</table>';
				};
				is_table_than.html(addHtmlThanTable);
			}
		});
	}

	// 分页功能
	function page(pageData,func){
	    var addHtmlPage  =  '<a id="js_page_home" class="fenye_a" href="#">' + '首页' + '</a>' + 
	                    '<a id="js_page_pre" class="fenye_a1" href="#">' + '&lt;' + '</a>' +
	                    '<span id="js_page_show" class="fenye_span">' + page_cur + " / " + page_total + "页" + '</span>' +
	                    '<a id="js_page_next" class="fenye_a2" href="#">' + '&gt;' + '</a>' +
	                    '<a id="js_page_end" class="fenye_a" href="#">' + '尾页' + '</a>' +
	                    '<input type="text" class="fenye_jump">' +
	                    '<a id="js_page_jump" class="fenye_a" href="#">' + '跳转' + '</a>';
	    js_pageList.html(addHtmlPage); 

	    if(page_total > 1){
	        js_pageList.show();
	    }else{
	        js_pageList.hide();
	    }

	    // 首页
	    documentBox.undelegate("#js_page_home","click").delegate("#js_page_home", "click", function(){     
	        NowPage = 1;
	        pageData['pageNum'] = NowPage;
	        func(pageData);        
	    });

	    // 上一页
	    documentBox.undelegate("#js_page_pre","click").delegate("#js_page_pre", "click", function(){        
	        page_cur -= 1;        
	        if(page_cur <= 0){
	            page_cur = 1;
	        }else{
	            NowPage = page_cur;
	        };        
	        pageData['pageNum'] = NowPage;
	        func(pageData); 
	    });

	    // 下一页
	    documentBox.undelegate("#js_page_next","click").delegate("#js_page_next", "click", function(){       
	        page_cur = parseInt(page_cur) + 1;
	        if(page_cur > page_total){
	            page_cur = page_total
	        }else{
	            NowPage = page_cur;
	        };
	        pageData['pageNum'] = NowPage;
	        func(pageData);       
	    });

	    // 尾页
	    documentBox.undelegate("#js_page_end","click").delegate("#js_page_end", "click", function(){       
	        NowPage = page_total;
	        pageData['pageNum'] = NowPage;
	        func(pageData);        
	    });

	    // 跳转
	    documentBox.undelegate("#js_page_jump","click").delegate("#js_page_jump", "click", function(){
	        
	        var jump_page = parseInt($(this).siblings("input").val());
	        if(isNaN(jump_page) || jump_page == 0){
	            errorAll(".js_jump_L","输入的页码是非法字符！" ,"red");
	            NowPage = page_cur;
	        }else if(jump_page < 0){
	            NowPage = -jump_page;
	        }else if(jump_page > page_total){
	            NowPage = page_total;
	        }else {
	            NowPage = jump_page;
	        }
	        console.log(NowPage)
	        pageData['pageNum'] = NowPage;
	        func(pageData);
	    });
	};

	function errorAll(className,messge,color){
	    if(color == "red"){
	        is_color = "#ff0000";
	    }else if(color == "green"){
	        is_color = "#00ff00";
	    }else if(color == "black"){
	        is_color = "#000000";
	    }
	    var popUp_p = $(".js_jump_L").find(".popUp_p")
	    popUp_p.text(messge);
	    popUp_p.css("color", is_color);
	    showPop(className,".iframe_box");
	} 
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
		
	function errors(messge){
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