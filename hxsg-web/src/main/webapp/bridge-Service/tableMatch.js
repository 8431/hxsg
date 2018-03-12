/* 监控平台后台系统，页面数据动态生成 */

$(function(){
	//var portconfig="http://192.168.121.36:6164/hxsg-web/";
	var documentBox = $(document);
	var popBg = $('.iframe_box')
	var popBgW = $('.iframe_boxW')
	var popLoading = $('.popLoading')

	var pushAllos = $(".pushAllos")						// 字典管理
	var js_query = $("#js_query");

	var js_pagelist = $(".js_pageList");				// 分页内容
	var js_table_TB = $("#js_table_TB");				// 左侧内容注入接口
	var js_page_show = $("#js_page_show");				// 分页

	var toTransmit = $('#toTransmit');					// 保存
	var toDel = $('#toDel');							// 删除
	var toAdd = $('#toAdd'); 							// 新增

    var toTransmitPop = $('#toTransmitPop');            // 弹框中保存
    var toCancelPop = $('#toCancelPop');                // 弹框中初始化
    var toModifyPop = $('#toModifyPop');                // 弹框中修改
    var toViewPop = $('#toViewPop');                    // 弹框中查看详情

	var NowPage = 1;
	var page_cur = 1;
	var page_total = "";

	/*+++++++++++++++++++++++++ 判断页面高度 +++++++++++++++++++++++++++++*/
	var reportContentLeftH = $(".pageMatch_left");
	var reportContentRightH = $(".pageMatch_right");
	var groupLeftHeight , groupRightHeight;

	function groupHeight(){
		groupLeftHeight = reportContentLeftH.height();
		groupRightHeight = reportContentRightH.height();
	
		if(groupLeftHeight > groupRightHeight){
			reportContentLeftH.addClass('addRightBorder');
			reportContentRightH.removeClass('addLeftBorder');	
		}else{
			reportContentRightH.addClass('addLeftBorder');	
			reportContentLeftH.removeClass('addRightBorder');
		}
	}
	groupHeight();
	/*+++++++++++++++++++++++++ 判断页面高度 +++++++++++++++++++++++++++++*/

    $(".popClose").on("click",function(){
        $("#js_popAllo_shop").prop("disabled",false);
    });
	// 退出系统
	$(".btnBox").delegate(".exitBtn", "click", function(event){
		event.stopPropagation;
		closePop(".infoUp", ".iframe_box");
	});

	// 取消刷新页面
	$("body").delegate(".cancel", "click", function(event){
		event.stopPropagation;
		closePop(".infoUp", ".iframe_box");
	});

	var sendInit = {
		"type":"selectPage",
        "tablename":"DICT_SOURCE_MAPPING",
        "arrays":[[]],
		"currentpage":1
	}
	var sendAll;                                                                           // 左侧页面初始值
	tableMatchLeft(sendInit);
	// 左侧注入函数
	function tableMatchLeft(sendDate){
        
		var js_table_TB = $("#js_table_TB");
		var addHtmlPushTB = '';

		$.ajax({
	        type:"POST",
	        url:portconfig+"match/mybatis",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		    success: function(json){

		    	var arrays = json.arrays;
		    	page_cur = json.page.currentpage;
				page_total = json.page.totalsize;

		    	if(arrays != ''){
					if(parseInt(page_cur) <= page_total){
				    	if(page_total > 1){
							js_pagelist.show();
						}else{
							js_pagelist.hide();
						}
						js_page_show.html("");
						js_page_show.html(page_cur + " / " + page_total + "页");

				    	for(var d=0;d<arrays.length;d++){
				    		addHtmlPushTB += '<tr type="'+ arrays[d].SOURCE_PK +'" alt="'+ arrays[d].SOURCE_DATABASE_PK +'" value="'+ arrays[d].HIUP_DATABASE_PK +'">' 
				    						+ '<td>' + arrays[d].SOURCE_NAME + '</td>'
				    						+ '<td>' + arrays[d].SOURCE_DATABASE + '</td>'
				    						+ '<td>' + arrays[d].SOURCE_TABEL + '</td>'
				    						+ '<td>' + arrays[d].HIUP_DATABASE + '</td>'
				    						+ '<td>' + arrays[d].HIUP_TABEL + '</td>'
				    						+'</tr>'
				    	}
				    	js_table_TB.html('');
				    	js_table_TB.append(addHtmlPushTB);

				    	var firstTrPk = $("#js_table_TB tr").eq(0).attr("type");
				    	var trPK ;

						$("#js_table_TB tr").each(function(){
		
							trPK = $(this).attr("type");
							if(trPK == firstTrPk){
								$("#js_table_TB tr").removeClass('click');
								$(this).addClass("click");

                                // 右侧流程图
                                $("#js_hiup_process").html("");
                                $("#js_hiup_process").append($(".click").find("td").eq(3).text() + "－" + $(".click").find("td").eq(4).text());

								var sendDateInit = {
                                    "status": "1",
									"type":"select",
									"sourcePk":firstTrPk
								}
                                sendAll = sendDateInit;
								columnShop(sendDateInit);
							}
							$(this).off("click").on("click",function(){

								$(this).addClass('click').siblings().removeClass('click');

                                // 右侧流程图
                                $("#js_hiup_process").html("");
                                $("#js_hiup_process").append($(".click").find("td").eq(3).text() + "－" + $(".click").find("td").eq(4).text());

								trPK = $(this).attr("type");
								
								var sendDate = {
                                    "status": "1",
									"type":"select",
									"sourcePk":trPK
								}
                                sendAll = sendDate;
								columnShop(sendDate);
							})
						})
				    }else{
						js_table_TB.html("");
						js_table_TB.append('<td colspan="5" class="overPage_L">' + '<p>' + "您输入的页码已超出当前内容总页码！" + '</p>' + '</td>');
					}
				}else{
					js_table_TB.html("");
					js_table_TB.append('<tr id="js_init_tr">' + '<td colspan="5" class="overPage_L">' + '<p>' + "未能查询到相关内容！" + '</p>' + '</td>' + '</tr>');
				};
	        }
	    });
	}
	// 右侧注入函数
	function columnShop(sendDate){
		var js_shopTb = $("#js_shopTb");
		var addHtmlShopTr = '';

		$.ajax({
	        type:"POST",
	        url:portconfig+"match/dmcbj",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		    success: function(json){
				
				var arrays = json.arrays;
				if(arrays != ''){
			    	for(var e=0;e<arrays.length;e++){
			    		addHtmlShopTr += '<tr id="'+ arrays[e].consumePk +'" label="'+ arrays[e].sourcePk +'" type="'+ arrays[e].sonsize +'" alt="'+ arrays[e].hiupDatabase +'" name="'+ arrays[e].hiupDatabasePk +'" value="'+ arrays[e].hiupTabel +'">' 
			    						+ '<td>' + arrays[e].sourceName + '</td>'
			    						+ '<td alt="'+ arrays[e].consumeDatabasePk +'">' + arrays[e].consumeDatabase + '</td>'
			    						+ '<td>' + arrays[e].consumeTabel + '</td>'
			    						+ '<td class="operaCenter">'  
			    								+ '<a href="javascript:;" class="save">' + '</a>'
					                            + '<a href="javascript:;" class="addTR">' + '添加' + '</a>'
					                            + '<a href="javascript:;" class="del delfn">' + '删除' + '</a>'
					                            + '<a href="javascript:;" class="linkBg">' + '配置' + '</a>'
			    						+ '</td>'
			    						+'</tr>'
			    	}
			    	js_shopTb.html('');
			    	js_shopTb.append(addHtmlShopTr);

                    // 右侧流程图
                    var firstTR = js_shopTb.find("tr").eq(0);
                    $("#js_shop_process").html("");
                    $("#js_shop_process").append(firstTR.find("td").eq(1).text() + "→" + firstTR.find("td").eq(2).text());

                    js_shopTb.find("tr").each(function(i){
                        $(this).on("click",function(){
                            var numberTR = js_shopTb.find("tr").eq(i);
                            $("#js_shop_process").html("");
                            $("#js_shop_process").append(numberTR.find("td").eq(1).text() + "→" + numberTR.find("td").eq(2).text());
                        })
                    });

                    // 区分已配置和未配置
                    js_shopTb.find("tr").each(function(){
                        if($(this).attr("type") > 0){
                            $(this).addClass("txtBg");
                        };
                    });

                    operationFun();
				
				}else if(arrays == ''){
					js_shopTb.html("");
					js_shopTb.append('<td colspan="4" class="overPage_L">' + '<p>' + "相关内容没有数据！" + '<a href="#" id="js_zengjia" class="addTR addMarTop">' + '</a>'+  '</p>' + '</td>');
				};
		    }
		});
	}

	// 右侧操作函数
    var js_shopTb = $("#js_shopTb"); 
	function operationFun(){

        // 右侧删除
        js_shopTb.undelegate(".del","click").delegate(".del","click",function(){

            var strTr = $(this).closest("tr");
            var js_consumePk = strTr.attr("id");

            delAll("删除","您确定要删除吗？");
            $(".delInfoBtn").off("click").on("click", function(event){

                if(js_consumePk == 'js_shopTr_model'){
                    strTr.remove();
                    $("#js_shopTb .linkBg").show();
                    $("#js_shopTb .addTR").show();
                    $("#js_shopTb .del").show();
                    closePop(".infoUp", ".iframe_box");
                }else{

                    closePop(".infoUp", ".iframe_box");
                    loadingShow('white', 'loadingLong', '数据删除中... 请您稍等，谢谢！');

                    var sendDatedel = {
                        "type":"del",
                        "consumePk": js_consumePk
                    }
               
                    $.ajax({
                        type:"POST",
                        url:portconfig+"match/dmcbj",
                        data:sendDatedel,
                        dataType : 'jsonp',
                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                        success: function(json){
                            var result = json.result;
                            if(result == 'succes'){
                                columnShop(sendAll);
                                closeLoading('white', 'loadingLong');
                                passAll("删除","删除成功！");
                            }else{
                                closeLoading('white', 'loadingLong');
                                errorsAll(result);
                            }
                        }
                    });
                };
            });
        });

        // 右侧配置
        js_shopTb.undelegate(".linkBg","click").delegate(".linkBg","click",function(){

            var strTr = $(this).closest("tr");
            var js_shop_list = $("#js_shop_list");
            var js_shop_back = $("#js_shop_back");
            showPop(".infoUpPop", ".iframe_box");

            var hiupOldPK = "";
            var sourceOldPK = "";
            var shopOldPK = "";

            // 弹框流程图
            $("#js_sourcePop_process").html("");
            $("#js_sourcePop_process").append($(".click").find("td").eq(1).text() + "－" + $(".click").find("td").eq(2).text());
            $("#js_hiupPop_process").html("");
            $("#js_hiupPop_process").append($(".click").find("td").eq(3).text() + "－" + $(".click").find("td").eq(4).text());

            // 消费列PK 及表名
            var js_consumePk = strTr.find("td").eq(1).attr("alt");
            var js_consumeTable = strTr.find("td").eq(2).text();
            var js_consume_optionInit = strTr.attr("id");
            var js_consumeBankTable = strTr.find("td").eq(1).text();

            // 源PK 及表名
            var js_source_Pk = $(".click").attr("alt");
            var js_source_table = $(".click").find("td").eq(2).text();

            // 平台PK 及表名
            var js_hiup_Pk = $(".click").attr("value");
            var js_hiup_table = $(".click").find("td").eq(4).text();

            if(strTr.hasClass("txtBg")){
                //console.log("OK")
                columnPopStatic();
                js_shop_list.hide();
                js_shop_back.show();
                toTransmitPop.hide();
                toCancelPop.hide();
                toModifyPop.show();
                toViewPop.show();
            }else{
                columnPop(); 
                js_shop_list.show();
                js_shop_back.hide();
                toTransmitPop.show();
                toCancelPop.show();
                toModifyPop.hide();
                toViewPop.hide();
            };
            
            function columnPop(){

                var addHtmlSource = '';
                var addHtmlShop = '';

                var addHtmlSourceArr = [];
                var addHtmlShopArr = [];

                $("#js_shopAllo").html("");
                $("#js_shopAllo").append("待配置消费列");

                // 主键 控件跟静态的切换
                $(".is_key").find("span").hide();
                $(".is_key").find("input").show();

                // 消费列数获取
                $("#js_popAllo_shop").html("");
                js_shopTb.find("tr").each(function(){
                    if(!$(this).hasClass("txtBg")){
                        var js_consume_option = $(this).attr("id");                                 // 配置列的pk
                        var js_consume_table = $(this).find("td").eq(2).text();                     // 配置列的表名
                        var js_consumePk = $(this).find("td").eq(1).attr("alt");                    // 来自字典数据管理的pk
                        var js_consumeBankTable = strTr.find("td").eq(1).text();                    // 配置列的库名

                        $("#js_popAllo_shop").append('<option class="'+js_consumeBankTable+'" name="'+js_consume_table+'" value="'+ js_consume_option +'" alt="'+ js_consumePk +'">' + '消费列' + js_consume_option + '</option>');
                        $("#js_popAllo_shop").find("option[value='"+ js_consume_optionInit +"']").prop("selected",true);
                    }
                });

                // 弹框流程图
                $("#js_shopPop_process").html("");
                $("#js_shopPop_process").append(js_consumeBankTable + "－" + js_consumeTable);

                // 消费列 注入到弹窗的表头
                var js_consume_option = $("#js_popAllo_shop").find("option:selected").val();
                var shopLine = '消费列' + js_consume_option
                $("#js_pop_table").html("");
                $("#js_pop_table").append(shopLine);                                           

                // 源配置数据获取
                var sendSource = {
                    "tablename":js_source_table,
                    "type":"tablecloumn",
                    "pk":js_source_Pk
                }
                // 初始弹窗  页面判断
                var sendHiup = {
                    "tablename":js_hiup_table,
                    "type":"tablecloumn",
                    "pk":js_hiup_Pk
                }
                // 消费列配置数据获取
                var sendShop = {
                    "tablename":js_consumeTable,
                    "type":"tablecloumn",
                    "pk":js_consumePk
                }

                $("#js_popAllo_shop").change(function(){

                    // 弹框流程图
                    $("#js_shopPop_process").html("");
                    $("#js_shopPop_process").append($(this).find("option:selected").attr("class") + "－" + $(this).find("option:selected").attr("name"));

                    // 消费列 注入到弹窗的表头
                    var shopLine = $(this).find("option:selected").text();
                    $("#js_pop_table").html("");
                    $("#js_pop_table").append(shopLine);                                       

                    // select 切换改变消费列  注入
                    var js_consumePk = $(this).find("option:selected").attr("alt");
                    var js_consumeTable = $(this).find("option:selected").attr("name");
                    var sendShop = {
                        "tablename":js_consumeTable,
                        "type":"tablecloumn",
                        "pk":js_consumePk
                    }
                    shopCloumn(sendShop);
                });
       
                $.ajax({
                    type:"POST",
                    url:portconfig+"match/gettable",
                    data:sendSource,
                    dataType : 'jsonp',
                    jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                    success: function(json){

                        var tablenameSource = json.tablename[0];
                        addHtmlSourceArr = json.tablePk.columnNames;

                        sourceOldPK = "";
                        if(json.tablePk.constraintName == null){
                            sourceOldPK = "";
                        }else{
                            sourceOldPK = json.tablePk.constraintName; 
                        };
                        console.log(json.tablePk)

                        // 添加主键名称
                        $(".js_constraintNameSource").find("input").val(json.tablePk.constraintName);
                        $(".js_constraintNameSource").attr("name",json.tablePk.tableName);

                        for(var x=0;x<tablenameSource.length;x++){
                            addHtmlSource += '<option value="'+x+'">' + tablenameSource[x].cloumn + '（' + tablenameSource[x].comments + '）' + '</option>';
                        }
                        shopCloumn(sendShop);
                    }
                });
                
                function shopCloumn(sendDate){
                    $.ajax({
                        type:"POST",
                        url:portconfig+"match/gettable",
                        data:sendDate,
                        dataType : 'jsonp',
                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                        success: function(json){
                            var tablenameShop = json.tablename[0];
                            //console.log(json);
                            addHtmlShopArr = json.tablePk.columnNames;

                            shopOldPK = "";
                            if(json.tablePk.constraintName == null){
                                shopOldPK = "";
                            }else{
                                shopOldPK = json.tablePk.constraintName; 
                            };
                            console.log(json.tablePk)
                            // 添加主键名称
                            $(".js_constraintNameShop").find("input").val(json.tablePk.constraintName);
                            $(".js_constraintNameShop").attr("name",json.tablePk.tableName);

                            addHtmlShop = '';
                            for(var y=0;y<tablenameShop.length;y++){
                                addHtmlShop += '<option alt="'+y+'">' + tablenameShop[y].cloumn + '（' + tablenameShop[y].comments + '）' + '</option>';
                            }
                            hiupCloumn(sendHiup);
                        }
                    });
                }
                
                function hiupCloumn(sendDate){

                    var addHtmlHiup = '';
                    $.ajax({
                        type:"POST",
                        url:portconfig+"match/gettable",
                        data:sendDate,
                        dataType : 'jsonp',
                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                        success: function(json){
                            var tablenameHiup = json.tablename[0];
                            var columnNames = json.tablePk.columnNames;
                            
                            hiupOldPK = "";
                            hiupOldPK = json.tablePk.constraintName;
                            if(json.tablePk.constraintName == null){
                                hiupOldPK = "";
                            }else{
                                hiupOldPK = json.tablePk.constraintName; 
                            };
                            console.log(json.tablePk)

                            // 添加主键名称
                            $(".js_constraintNameHiup").find("input").val(json.tablePk.constraintName);
                            $(".js_constraintNameHiup").attr("name",json.tablePk.tableName);

                            for(var z=0;z<tablenameHiup.length;z++){
                                addHtmlHiup += '<tr>' 
                                                    + '<td class="w248">' 
                                                        + '<input type="checkbox" />'
                                                        + '<select id="is_addHtmlSource" class="delSource">' 
                                                            + '<option>' + '</option>'
                                                            + addHtmlSource 
                                                        + '</select>'
                                                    + '</td>'
                                                    + '<td class="w248">'
                                                        + '<input type="checkbox" style=" vertical-align: top; " />' 
                                                        + '<label>' + tablenameHiup[z].cloumn + '（' + tablenameHiup[z].comments + '）' + '</label>'
                                                        + '<a href="#" class="del delPop fr">' + '</a>'
                                                    + '</td>'
                                                    + '<td>' 
                                                        + '<input type="checkbox" />'
                                                        + '<select class="delShop">'
                                                            + '<option>' + '</option>' 
                                                            + addHtmlShop 
                                                        + '</select>'
                                                    + '</td>'
                                                + '</tr>';
                            };
                            $("#js_macth_allo").html("");
                            $("#js_macth_allo").append(addHtmlHiup);

                            // 判断多选框是否选中
                            for(var x=0;x<columnNames.length;x++){
                                for(var y=0;y<tablenameHiup.length;y++){
                                    if(columnNames[x] == tablenameHiup[y].cloumn){
                                        $("#js_macth_allo").find("tr").eq(y).find("td").children("input[type='checkbox']").prop("checked",true);
                                    };
                                };
                            }; 
                            $("#js_macth_allo tr").each(function(i){
                                $(this) .find("td").eq(1).children("input[type='checkbox']").change("click", function(){
                                    if($(this).prop("checked")){
                                        $(this).closest("tr").find("input").prop("checked",true);
                                    };
                                });
                            });

                            // select动态删除元素效果
                            var arr = [];
                            $("#js_macth_allo tr .delSource").each(function(i){
                                $(this).unbind().change("click", function(){
                                   
                                    $("#js_macth_allo tr .delSource").find("option:selected").each(function(){
                                        arr.push($(this).val());
                                    }); 
                                    $(this).closest("tr").siblings().find(".delSource").html('<option value="pk">' + '</option>' + addHtmlSource);
                                    for(var a=0;a<arr.length;a++){
                                        if(arr[a] != ""){
                                            $(".delSource").eq(a).find('option[value='+arr[a]+']').attr('selected', 'selected');
                                            $(this).closest("tr").siblings().find("option[value='"+arr[a]+"']").not(":selected").remove();  
                                        }
                                    }

                                    arr = [];
                                });
                            });
                            $("#js_macth_allo tr .delShop").each(function(){
                                $(this).unbind().change(function(){
                                    $("#js_macth_allo tr .delShop").find("option:selected").each(function(){
                                        arr.push($(this).attr("alt"));
                                    });
                                    $(this).closest("tr").siblings().find(".delShop").html('<option alt="pk">' + '</option>' + addHtmlShop);
                                    for(var b=0;b<arr.length;b++){
                                        if(arr[b] != ""){
                                            $(".delShop").eq(b).find('option[alt='+arr[b]+']').attr('selected', 'selected');
                                            $(this).closest("tr").siblings().find("option[alt='"+arr[b]+"']").not(":selected").remove();
                                        }
                                    }

                                    arr = [];
                                });
                            });

                            // 弹窗初始化
                            toCancelPop.off("click").on("click",function(){
                                //columnPop();
                                $("#js_macth_allo").find("select").val("");
                                $("#js_macth_allo").find(".delSource").html("").append('<option value="pk">' + '</option>' + addHtmlSource);
                                $("#js_macth_allo").find(".delShop").html("").append('<option alt="pk">' + '</option>' + addHtmlShop);
                            });
                            
                        }
                    });
                };
            }
            
            function columnPopStatic(){

                var addHtmlSource = '';
                var addHtmlShop = '';
                var addArrSource = [];
                var addArrShop = [];

                var oddArrSourcePk = [];                                // 静态注入 源 多选框主键
                var oddArrShopPk = [];                                  // 静态注入 消费 多选框主键

                $("#js_shopAllo").html("");
                $("#js_shopAllo").append("已配置消费列");

                // 主键 控件跟静态的切换
                $(".is_key").find("span").show();
                $(".is_key").find("input").hide();

                // 消费列数获取
                $("#js_popAllo_shop").html("");
                js_shopTb.find("tr").each(function(){
                    if($(this).hasClass("txtBg")){
                        var js_consume_option = $(this).attr("id");                                 // 配置列的pk
                        var js_consume_table = $(this).find("td").eq(2).text();                     // 配置列的表名
                        var js_consumePk = $(this).find("td").eq(1).attr("alt");                    // 来自字典数据管理的pk
                        var js_consumeBankTable = strTr.find("td").eq(1).text();                    // 配置列的库名

                        $("#js_popAllo_shop").append('<option class="'+js_consumeBankTable+'" name="'+js_consume_table+'" value="'+ js_consume_option +'" alt="'+ js_consumePk +'">' + '消费列' + js_consume_option + '</option>');
                        $("#js_popAllo_shop").find("option[value='"+ js_consume_optionInit +"']").prop("selected",true);
                    };
                });

                // 弹框流程图
                $("#js_shopPop_process").html("");
                $("#js_shopPop_process").append(js_consumeBankTable + "－" + js_consumeTable);

                // 消费列 注入到弹窗的表头
                var js_consume_option = ($("#js_popAllo_shop").find("option:selected").val());
                var shopLine = '消费列' + js_consume_option
                $("#js_pop_table").html("");
                $("#js_pop_table").append(shopLine);                                          

                // 源配置数据获取
                var sendSource = {
                    "tablename":"SOURCE_PLATFORM_MAPPING",
                    "type":"select",
                    "arrays":[["SOURCE_PK=",js_consume_option]]
                }
                
                // 消费列配置数据获取
                var sendShop = {
                    "tablename":"CONSUME_PLATFORM_MAPPING",
                    "type":"select",
                    "arrays":[["CONSUME_PK=",js_consume_option]]
                }

                $("#js_popAllo_shop").unbind().change(function(){

                    // 弹框流程图
                    $("#js_shopPop_process").html("");
                    $("#js_shopPop_process").append($(this).find("option:selected").attr("class") + "－" + $(this).find("option:selected").attr("name"));

                    var shopLine = $(this).find("option:selected").text();
                    $("#js_pop_table").html("");
                    $("#js_pop_table").append(shopLine);                                        // 改变select

                    // select 切换改变消费列  注入
                    var js_consume_option = $(this).find("option:selected").val();              // 右方消费列每一行的主键
                    sendSource = {
                        "tablename":"SOURCE_PLATFORM_MAPPING",
                        "type":"select",
                        "arrays":[["SOURCE_PK=",js_consume_option]]
                    }
                    sendShop = {
                        "tablename":"CONSUME_PLATFORM_MAPPING",
                        "type":"select",
                        "arrays":[["CONSUME_PK=",js_consume_option]]
                    }

                    sourceCloumnStatic(sendSource);
                    shopCloumnStatic(sendShop);
                    hiupCloumnStatic(sendSource);
                });
                
                // 清空主键
                $(".tableTitle").find("span").html("");

                sourceCloumnStatic(sendSource)
                function sourceCloumnStatic(sendDate){

                    $.ajax({
                        type:"POST",
                        url:portconfig+"match/mybatis",
                        data:sendDate,
                        dataType : 'jsonp',
                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                        success: function(json){
                            var tablenameSource = json.result[0];
                            // console.log(tablenameSource);
                            
                            // 添加主键名称
                            $(".js_constraintNameSourceStatic").text(json.result[0][0].SOURCE_PRIMARYKEY_NAME);
                            //$(".js_constraintNameSource").find("input").val(json.result[0][0].SOURCE_PRIMARYKEY_NAME);

                            for(var x=0;x<tablenameSource.length;x++){
                                addArrSource.push(tablenameSource[x].SOURCE_COLUMN + '（' + tablenameSource[x].SOURCE_COLUMN_DESC + '）');
                                oddArrSourcePk.push(tablenameSource[x].SOURCE_PRIMARYKEY);
                            }
                            shopCloumnStatic(sendShop)
                        }
                    });
                }
                function shopCloumnStatic(sendDate){
                    $.ajax({
                        type:"POST",
                        url:portconfig+"match/mybatis",
                        data:sendDate,
                        dataType : 'jsonp',
                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                        success: function(json){
                            var tablenameShop = json.result[0];
                            // console.log(tablenameShop);
                            
                            // 添加主键名称
                            $(".js_constraintNameShopStatic").text(json.result[0][0].CONSUME_PRIMARYKEY_NAME);

                            for(var y=0;y<tablenameShop.length;y++){
                                addArrShop.push(tablenameShop[y].CONSUME_COLUMN + '（' + tablenameShop[y].CONSUME_COLUMN_DESC + '）');
                                oddArrShopPk.push(tablenameShop[y].CONSUME_PRIMARYKEY);
                            }

                            hiupCloumnStatic(sendSource);
                        }
                    });
                }
                // 平台配置数据获取 静态
                function hiupCloumnStatic(sendDate){
                    var addHtmlHiupStatic = '';
                    $.ajax({
                        type:"POST",
                        url:portconfig+"match/mybatis",
                        data:sendDate,
                        dataType : 'jsonp',
                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                        success: function(json){
                            //$("#js_tr").remove();
                            var tablenameHiup = json.result[0];
                            // console.log(tablenameHiup)
                           
                            // // 添加主键名称
                            $(".js_constraintNameHiupStatic").text(json.result[0][0].HIUP_PRIMARYKEY_NAME);

                            for(var z=0;z<tablenameHiup.length;z++){
                                addHtmlHiupStatic += '<tr alt="pk">' 
                                                    + '<td class="w248">'
                                                        + '<input type="checkbox" duoPk="'+ oddArrSourcePk[z] +'" style=" vertical-align: top; " />' 
                                                        + '<span class="show">' + addArrSource[z] + '</span>'
                                                    + '</td>'
                                                    + '<td class="w248">' 
                                                        + '<input type="checkbox" duoPk="'+ tablenameHiup[z]. HIUP_PRIMARYKEY +'" style=" vertical-align: top; " />' 
                                                        + '<label>' + tablenameHiup[z].HIUP_COLUMN + '（' + tablenameHiup[z].HIUP_COLUMN_DESC + '）' + '</label>'
                                                    + '</td>'
                                                    + '<td>' 
                                                        + '<input type="checkbox" duoPk="'+ oddArrShopPk[z] +'" style=" vertical-align: top; " />' 
                                                        + '<span class="show">' + addArrShop[z] + '</span>'
                                                    + '</td>'
                                                + '</tr>'
                            };

                            $("#js_macth_allo").html("");
                            $("#js_macth_allo").append(addHtmlHiupStatic);

                            // 静态页面时判断多选是否选中
                            $("#js_macth_allo").find("input[type='checkbox']").each(function(){
                                if($(this).attr("duoPk") == "0"){
                                    $(this).prop("checked",true);
                                }else if($(this).attr("duoPk") == "1"){
                                    $(this).prop("checked",false);
                                };
                            });

                            $("#js_macth_allo").find("td").each(function(){
                                $(this).mouseenter(function(){
                                    $(this).append('<div style="position:absolute;left:15px;top:40px;width:20px;height:7px;background:url(images/u4065.png) no-repeat 0 0;z-index:1000;">' + '</div>' 
                                        + '<div style="position:absolute;left:5px;top:46px;width:200px;height:60px;border:1px solid #CFCFCF;z-index:100;background-color:#fff;text-indent:14px;padding:4px;">' + '<p>' + $(this).text() + '</p>' + '</div>');
                                    $(this).siblings().find("div").remove();
                                    $(this).closest("tr").siblings().find("div").remove()
                                }).mouseleave(function(){
                                    $(this).find("div").remove();
                                });
                            });
                        }
                    }); 
                };

            }

            // 查看详情
            toViewPop.click(function(){

                // 右方列表主键
                var js_consumePk_pop = $("#js_popAllo_shop").find('option:selected').val();

                var js_hiupNameArr = $(".click").find("td").eq(4).text(); 
                var js_sourceNameArr = $(".click").find("td").eq(2).text();
                var js_shopNameArr = $("#js_shopTb").find('tr[id="'+ js_consumePk_pop + '"]').children("td").eq(2).text();

                var js_shopBasePk =  $("#js_shopTb").find('tr[id="'+ js_consumePk_pop + '"]').children("td").eq(1).attr("alt");
                var js_hiupBasePk = $(".click").attr("value");
                var js_sourceBasePk = $(".click").attr("alt");

                var sourceArrPk = [];
                var hiupArrPk = [];
                var shopArrPk = [];

                var sourceArrCon = [];
                var hiupArrCon = [];
                var shopArrCon = [];

                $("#js_macth_allo").find("tr").each(function(){
                    var tdHtml = $(this).find("td");
                    sourceArrCon.push(tdHtml.eq(0).text().split("（")[0]);
                    hiupArrCon.push(tdHtml.eq(1).text().split("（")[0]);
                    shopArrCon.push(tdHtml.eq(2).text().split("（")[0]);
                    console.log(tdHtml.eq(0).find("input").is(":checked"));
                    if(tdHtml.eq(0).find("input").is(":checked")){
                        sourceArrPk.push(tdHtml.eq(0).text().split("（")[0]);
                    }
                    if(tdHtml.eq(1).find("input").is(":checked")){
                        hiupArrPk.push(tdHtml.eq(1).text().split("（")[0]);
                    }
                    if(tdHtml.eq(2).find("input").is(":checked")){
                        shopArrPk.push(tdHtml.eq(2).text().split("（")[0]);
                    }
                });

                var sendTableName = [js_sourceNameArr,js_hiupNameArr,js_shopNameArr];           // 源表名-平台表名-消费列表
                var sendPkSource = [js_sourceBasePk,js_hiupBasePk,js_shopBasePk];               // 对应字典表数据管理Pk  源-平台-消费列

                var link_L = "dictionaryThan.html?" + sourceArrCon + "]" + hiupArrCon + "]" + shopArrCon + ";"
                            + sourceArrPk + "]" + hiupArrPk + "]" + shopArrPk + ";"
                            + sendTableName + ";"
                            + sendPkSource;
                toViewPop.attr("href",link_L);
            });

            // 弹框修改
            toModifyPop.off("click").on("click",function(){

                $("#js_popAllo_shop").prop("disabled",true);

                // 主键 控件跟静态的切换
                $(".is_key").find("span").hide();
                $(".is_key").find("input").show();

                var addArrSource = [];                                                               // 所有源选中的选项
                var addSourceTxt = [""];                                                             // 所有源选项
                var addArrShop = [];                                                                 // 所有消费列选中选项
                var addShopTxt = [""];                                                               // 所有消费列选项

                var addHtmlSourceArr = [];
                var addHtmlShopArr = [];

                var addHtmlSource = '';
                var addHtmlShop = '';
                var addHtmlHiup = '';

                 $("#js_macth_allo").find("tr").each(function(){
                    addArrSource.push($(this).find("td").eq(0).text());
                    addArrShop.push($(this).find("td").eq(2).text());
                });

                var js_consumePk = $("#js_popAllo_shop").find("option:selected").attr("alt");        // 右方消费列每一行的继承字典数据库管理的主键
                var js_consumeTable = $("#js_popAllo_shop").find("option:selected").attr("name");    // 右方消费列的表名
                var js_consume_option = $("#js_popAllo_shop").find("option:selected").val();         // 右方消费列每一行的主键

                // 源配置数据获取
                var sendSourceOption = {
                    "tablename":js_source_table,
                    "type":"tablecloumn",
                    "pk":js_source_Pk
                }
                // 消费列配置数据获取
                var sendShopOption = {
                    "tablename":js_consumeTable,
                    "type":"tablecloumn",
                    "pk":js_consumePk
                }
                var sendHiup = {
                    "tablename":js_hiup_table,
                    "type":"tablecloumn",
                    "pk":js_hiup_Pk
                }
                sourceCloumn(sendSourceOption);
                function sourceCloumn(sendDate){
                    $.ajax({
                        type:"POST",
                        url:portconfig+"match/gettable",
                        data:sendDate,
                        dataType : 'jsonp',
                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                        success: function(json){

                            var tablenameSource = json.tablename[0];
                            addHtmlSourceArr = json.tablePk.columnNames;
                            
                            sourceOldPK = [];
                            sourceOldPK = json.tablePk.constraintName;

                            console.log(json.tablePk);

                            // 添加主键名称
                            $(".js_constraintNameSource").find("input").val(json.tablePk.constraintName);
                            // $(".js_constraintNameSource").attr("name",json.tablePk.tableName);

                            for(var x=0;x<tablenameSource.length;x++){
                                addHtmlSource += '<option value="'+x+'">' + tablenameSource[x].cloumn + '（' + tablenameSource[x].comments + '）' + '</option>';
                                addSourceTxt.push(tablenameSource[x].cloumn + '（' + tablenameSource[x].comments + '）');
                            }
                            shopCloumn(sendShopOption);
                        }
                    });
                }
                
                function shopCloumn(sendDate){
                    $.ajax({
                        type:"POST",
                        url:portconfig+"match/gettable",
                        data:sendDate,
                        dataType : 'jsonp',
                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                        success: function(json){
                            var tablenameShop = json.tablename[0];
                            addHtmlShopArr = json.tablePk.columnNames;

                            shopOldPK = [];
                            shopOldPK = json.tablePk.constraintName;
                            
                            console.log(json.tablePk);
                            
                            // 添加主键名称
                            $(".js_constraintNameShop").find("input").val(json.tablePk.constraintName);
                            // $(".js_constraintNameShop").attr("name",json.tablePk.tableName);

                            for(var y=0;y<tablenameShop.length;y++){
                                addHtmlShop += '<option alt="'+y+'">' + tablenameShop[y].cloumn + '（' + tablenameShop[y].comments + '）' + '</option>';
                                addShopTxt.push(tablenameShop[y].cloumn + '（' + tablenameShop[y].comments + '）');
                            }
                            hiupCloumn(sendHiup);
                        }
                    });
                };

                function hiupCloumn(sendDate){
                    $.ajax({
                        type:"POST",
                        url:portconfig+"match/gettable",
                        data:sendDate,
                        dataType : 'jsonp',
                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                        success: function(json){
                            
                            var tablenameHiup = json.tablename[0];
                            var columnNames = json.tablePk.columnNames;

                            hiupOldPK = [];
                            hiupOldPK = json.tablePk.constraintName;
                            
                            console.log(json.tablePk);

                            // 添加主键名称
                            $(".js_constraintNameHiup").find("input").val(json.tablePk.constraintName);
                            // $(".js_constraintNameHiup").attr("name",json.tablePk.tableName);

                            for(var z=0;z<tablenameHiup.length;z++){
                                addHtmlHiup += '<tr>' 
                                            + '<td class="w248">' 
                                                + '<input type="checkbox" />' 
                                                + '<select class="delSource">' 
                                                    + '<option value="pk">' + '</option>'
                                                    + addHtmlSource 
                                                + '</select>'
                                            + '</td>'
                                            + '<td class="w248">' 
                                                + '<input type="checkbox" duoPk="'+ tablenameHiup[z]. HIUP_PRIMARYKEY +'" style=" vertical-align: top; " />' 
                                                + '<label>' + tablenameHiup[z].cloumn + '（' + tablenameHiup[z].comments + '）' + '</label>'
                                                + '<a href="#" class="del delPop fr">' + '</a>'
                                            + '</td>'
                                            + '<td>' 
                                                + '<input type="checkbox" />' 
                                                + '<select class="delShop">'
                                                    + '<option value="pk">' + '</option>' 
                                                    + addHtmlShop 
                                                + '</select>'
                                            + '</td>'
                                        + '</tr>'
                            }
                            $("#js_macth_allo").html("");
                            $("#js_macth_allo").append(addHtmlHiup);
                            
                            // 选中时两边也选中
                            $("#js_macth_allo tr").each(function(i){
                                $(this).find("td").eq(1).children("input[type='checkbox']").change("click", function(){
                                    if($(this).prop("checked")){
                                        $(this).closest("tr").find("input").prop("checked",true);
                                    };
                                });
                            });

                            // 判断多选框是否选中
                            for(var x=0;x<columnNames.length;x++){
                                for(var y=0;y<tablenameHiup.length;y++){
                                    if(columnNames[x] == tablenameHiup[y].cloumn){
                                        $("#js_macth_allo").find("tr").eq(y).find("td").eq(1).children("input[type='checkbox']").prop("checked",true);
                                    };
                                };
                            }; 

                            // 修改时，将数据注入控件
                            for(var a=0;a<addArrSource.length;a++){
                                for(var b=0;b<addSourceTxt.length;b++){
                                    if(addSourceTxt[b] == addArrSource[a]){
                                        $("#js_macth_allo tr .delSource").eq(a).find("option").eq(b).prop("selected",true);
                                    }

                                    //判断选中的是否是主键 
                                    for(var m=0;m<addHtmlSourceArr.length;m++){
                                        if($("#js_macth_allo tr .delSource").eq(a).find("option:selected").text().split("（")[0] == addHtmlSourceArr[m]){
                                            $("#js_macth_allo tr .delSource").eq(a).siblings("input[type='checkbox']").prop("checked",true);
                                        }
                                    }
                                }
                            };

                            for(var c=0;c<addArrShop.length;c++){
                                for(var d=0;d<addShopTxt.length;d++){
                                    if(addShopTxt[d] == addArrShop[c]){
                                        $("#js_macth_allo tr .delShop").eq(c).find("option").eq(d).prop("selected",true);
                                    }

                                    //判断选中的是否是主键 
                                    for(var n=0;n<addHtmlShopArr.length;n++){
                                        if($("#js_macth_allo tr .delShop").eq(c).find("option:selected").text().split("（")[0] == addHtmlShopArr[n]){
                                            $("#js_macth_allo tr .delShop").eq(c).siblings("input[type='checkbox']").prop("checked",true);
                                        }
                                    }
                                }
                            };


                            // 判断多选框是否选中
                            // for(var x=0;x<columnNames.length;x++){
                            //     for(var y=0;y<tablenameHiup.length;y++){
                            //         if(columnNames[x] == tablenameHiup[y].cloumn){
                            //             $("#js_macth_allo").find("tr").eq(y).find("td").eq(1).children("input[type='checkbox']").prop("checked",true);
                            //         };
                            //     };
                            // };

                            // 修改删除选中元素
                            //setTimeout(function(){},1000)
                            $("#js_macth_allo tr").each(function(){
                                var _this = $(this).find("option:selected").eq(0).val();
                                var _thisT = $(this).find("option:selected").eq(1).attr("alt");
                                if(_this != "pk"){
                                    $(this).siblings("tr").find("option[value = '"+_this+"']").remove();
                                }
                                if(_thisT != "pk"){
                                    $(this).siblings("tr").find("option[alt = '"+_thisT+"']").remove();
                                }
                            });

                            // select动态删除元素效果
                            var arr = [];
                            $("#js_macth_allo tr .delSource").each(function(i){
                                $(this).unbind().change("click", function(){
                                    $("#js_macth_allo tr .delSource").find("option:selected").each(function(){
                                        arr.push($(this).val());
                                    }); 
                                    $(this).closest("tr").siblings().find(".delSource").html('<option value="pk">' + '</option>' + addHtmlSource);
                                    for(var a=0;a<arr.length;a++){
                                        if(arr[a] != ""){
                                            $(".delSource").eq(a).find('option[value='+arr[a]+']').attr('selected', 'selected');
                                            $(this).closest("tr").siblings().find("option[value='"+arr[a]+"']").not(":selected").remove();  
                                        }
                                    }

                                    arr = [];
                                });
                            });
                            $("#js_macth_allo tr .delShop").each(function(){
                                $(this).unbind().change(function(){
                                    $("#js_macth_allo tr .delShop").find("option:selected").each(function(){
                                        arr.push($(this).attr("alt"));
                                    });
                                    $(this).closest("tr").siblings().find(".delShop").html('<option alt="pk">' + '</option>' + addHtmlShop);
                                    for(var b=0;b<arr.length;b++){
                                        if(arr[b] != ""){
                                            $(".delShop").eq(b).find('option[alt='+arr[b]+']').attr('selected', 'selected');
                                            $(this).closest("tr").siblings().find("option[alt='"+arr[b]+"']").not(":selected").remove();
                                        }
                                    }

                                    arr = [];
                                });
                            });

                            // 弹窗初始化
                            toCancelPop.off("click").on("click",function(){
                                //columnPop();
                                $("#js_macth_allo").find("select").val("");
                                $("#js_macth_allo").find(".delSource").html("").append('<option value="pk">' + '</option>' + addHtmlSource);
                                $("#js_macth_allo").find(".delShop").html("").append('<option alt="pk">' + '</option>' + addHtmlShop);
                            });
                        }
                    });
                }

                $(".hide").show();
                $(".show").hide();
                js_shop_list.show();
                js_shop_back.hide();
                toTransmitPop.show();
                toCancelPop.show();
                toModifyPop.hide();
                toViewPop.hide();
            });
            // 弹窗保存
            toTransmitPop.off("click").on("click",function(){

                $("#js_popAllo_shop").prop("disabled",false);
                closePop(".infoUpPop", ".iframe_box");
                loadingShow('white', 'loadingLong', '数据保存中... 请您稍等，谢谢！');

                // 右方列表主键
                var js_consumePk_pop = $("#js_popAllo_shop").find('option:selected').val();

                // 表里值得获取
                var hiupArrColumn = ["HIUP_COLUMN"];                                    // 平台列名 
                var hiupArrDesc = ["HIUP_COLUMN_DESC"];                                 // 平台列名描述 
                var sourceArrColumn = ["SOURCE_COLUMN"];                                // 源列名 
                var sourceArrDesc = ["SOURCE_COLUMN_DESC"];                             // 源列名描述
                var shopArrColumn = ["CONSUME_COLUMN"];                                 // 消费列名 
                var shopArrDesc = ["CONSUME_COLUMN_DESC"];                              // 消费列名描述 
                var columnArr = ["SOURCE_COLUMN_PK"];                                   // 弹框列表行主键
                var sourceArr = ["SOURCE_PK"];                                          // 平台-源 消费列主键 
                var consumeArr = ["CONSUME_PK"];                                        // 平台-消费 消费列主键 

                var shopNameArr = ["CONSUME_TABLE"];                                    // 当前选中的消费列的表名（统一） 
                var hiupNameArr = ["HIUP_TABLE"];                                       // 当前选中的平台的表名（统一） 
                var shopPKArr = ["CONSUME_PRIMARYKEY"];                                 // 多选控键 消费列主键 
                var hiupPKArr = ["HIUP_PRIMARYKEY"];                                    // 多选控键 平台主键  
                var sourceNameArr = ["SOURCE_TABLE"];                                   // 当前选中的源的表名（统一） 
                var sourcePKArr = ["SOURCE_PRIMARYKEY"];                                // 多选控键 源主键

                var js_hiupNameArr = $(".click").find("td").eq(4).text();               // 平台 表名
                var js_hiupKu = $(".click").find("td").eq(3).text();                    // 平台 库名
                var js_sourceNameArr = $(".click").find("td").eq(2).text();             // 源 表名
                var js_sourceKu = $(".click").find("td").eq(1).text();                  // 源 库名
                var js_shopNameArr = $("#js_shopTb").find('tr[id="'+ js_consumePk_pop + '"]').children("td").eq(2).text();  // 消费 表名
                var js_shopKu = $("#js_shopTb").find('tr[id="'+ js_consumePk_pop + '"]').children("td").eq(1).text();     // 消费 库名

                var js_hiupNameVal = $(".js_constraintNameHiup").find("input").val(); 
                var js_sourceNameVal = $(".js_constraintNameSource").find("input").val();
                var js_shopNameVal = $(".js_constraintNameShop").find("input").val();

                var hiupArrPk = [];                                                     // 多选框的 平台主键
                var sourceArrPk = [];                                                   // 多选框的 源主键
                var shopArrPk = [];                                                     // 多选框的 消费主键 

                var hiupMybatisPk = ["HIUP_PRIMARYKEY_NAME"];                           // 多选框的 平台主键 mybatis
                var sourceMybatisPk = ["SOURCE_PRIMARYKEY_NAME"];                       // 多选框的 源主键 mybatis
                var shopMybatisPk = ["CONSUME_PRIMARYKEY_NAME"];                        // 多选框的 消费主键 mybatis

                var js_shopBasePk =  $("#js_shopTb").find('tr[id="'+ js_consumePk_pop + '"]').children("td").eq(1).attr("alt"); // 消费 表 主键
                var js_hiupBasePk = $(".click").attr("value");                          // 平台 表 主键
                var js_sourceBasePk = $(".click").attr("alt");                          // 源 表 主键

                var sourceDbmanagePkArr = ["SOURCE_DBMANAGE_PK"];                       // 源 表 主键 数组
                var hiupDbmanagePkArr = ["HIUP_DBMANAGE_PK"];                           // 平台 表 主键 数组
                var shopDbmanagePkArr = ["CONSUME_DBMANAGE_PK"];                        // 消费 表 主键 数组 
                var hiupDataBaseKu = ["HIUP_DATABASE"];                                 // 平台 库名 数组 
                var sourceDataBaseKu = ["SOURCE_DATABASE"];                             // 源 库名 数组
                var shopDataBaseKu = ["HIUP_DATABASE"];                                 // 消费 库名 数组      

                $("#js_macth_allo").find("tr").each(function(){
                    var trueT0 = $(this).children("td").eq(0).find("option:selected").text();
                    var trueT2 = $(this).children("td").eq(2).find("option:selected").text();
                    
                    if(trueT0 != '' && trueT2 != ''){
                        var columnId = -0;                                              // 新增时弹窗每一行的主键
                        columnArr.push(columnId);

                        // 右方消费列表主键
                        sourceArr.push(js_consumePk_pop);                                   
                        consumeArr.push(js_consumePk_pop);

                        // 平台列名及描述
                        var js_hiup_name = $(this).find("td").eq(1).text().split("（");
                        var js_hiup_arr = js_hiup_name[1].split("）");
                        var js_hiup_desc = js_hiup_arr[0];
                        var js_hiup_column = js_hiup_name[0];

                        hiupArrColumn.push(js_hiup_column);
                        
                        hiupArrDesc.push(js_hiup_desc);

                        hiupNameArr.push(js_hiupNameArr);
                        hiupMybatisPk.push(js_hiupNameVal);
                        hiupDbmanagePkArr.push(js_hiupBasePk);

                        hiupDataBaseKu.push(js_hiupKu);

                        if($(this).find("td").eq(1).find("input").prop("checked")){
                            hiupArrPk.push(js_hiup_column);
                            hiupPKArr.push(0);
                        }else{
                            hiupPKArr.push(1);
                        }

                        // 源列名及描述
                        var js_source_name = $(this).find("option:selected").eq(0).text().split("（");
                        var js_source_arr = js_source_name[1].split("）");
                        var js_source_desc = js_source_arr[0];
                        var js_source_column = js_source_name[0];

                        sourceArrColumn.push(js_source_column);
                        
                        sourceArrDesc.push(js_source_desc);

                        sourceNameArr.push(js_sourceNameArr);
                        sourceMybatisPk.push(js_sourceNameVal);
                        sourceDbmanagePkArr.push(js_sourceBasePk);

                        if($(this).find("td").eq(0).find("input").prop("checked")){
                            sourceArrPk.push(js_source_column);
                            sourcePKArr.push(0);
                        }else{
                            sourcePKArr.push(1);
                        }

                        // 消费列名及描述
                        var js_shop_name = $(this).find("option:selected").eq(1).text().split("（");
                        var js_shop_arr = js_shop_name[1].split("）");
                        var js_shop_desc = js_shop_arr[0];
                        var js_shop_column = js_shop_name[0];

                        shopArrColumn.push(js_shop_column);
                        
                        shopArrDesc.push(js_shop_desc);

                        shopNameArr.push(js_shopNameArr);
                        shopMybatisPk.push(js_shopNameVal);
                        shopDbmanagePkArr.push(js_shopBasePk);

                        if($(this).find("td").eq(0).find("input").prop("checked")){
                            shopArrPk.push(js_shop_column);
                            shopPKArr.push(0);
                        }else{
                            shopPKArr.push(1);
                        }
                    }
                });

                // 先删除原有表里的数据  在保存
                var js_consume_option = $("#js_popAllo_shop").find("option:selected").val();         // 右方消费列每一行的主键
                var sendDelConsume = {
                    "type":"del",
                    "tablename":"CONSUME_PLATFORM_MAPPING",
                    "arrays":[["CONSUME_PK=",js_consume_option]]
                }
                $.ajax({
                    type:"POST",
                    url:portconfig+"match/mybatis",
                    data:sendDelConsume,
                    dataType : 'jsonp',
                    jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                    success: function(json){
                        
                    }
                });
                var sendDelSource = {
                    "type":"del",
                    "tablename":"SOURCE_PLATFORM_MAPPING",
                    "arrays":[["SOURCE_PK=",js_consume_option]]
                }
                $.ajax({
                    type:"POST",
                    url:portconfig+"match/mybatis",
                    data:sendDelSource,
                    dataType : 'jsonp',
                    jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                    success: function(json){
                     
                        var resultSource = json.result;
                        if(resultSource == true){

                            // 传递主键
                            var sendKeyDate = {
                                "tableName":[js_sourceNameArr,js_hiupNameArr,js_shopNameArr],
                                "constraintName":[js_sourceNameVal,js_hiupNameVal,js_shopNameVal],
                                "oldConstraintName":[sourceOldPK,hiupOldPK,shopOldPK],
                                "pkCloumn":[sourceArrPk, hiupArrPk, shopArrPk],
                                "pkSource":[js_sourceBasePk,js_hiupBasePk,js_shopBasePk]
                            }
                            console.log(sendKeyDate);
                            $.ajax({
                                type:"POST",
                                url:portconfig+"match/primaryKey",
                                data:sendKeyDate,
                                dataType:'jsonp',
                                jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                                success: function(json){
                                        
                                    var sendHiupSource = {                                                              // 平台-源
                                        "type":"insert",
                                        "tablename":"source_platform_mapping",
                                        "arrays":[columnArr,sourceArr,hiupArrColumn,hiupArrDesc,sourceArrColumn,sourceArrDesc,sourceNameArr,hiupNameArr,sourcePKArr,hiupPKArr,sourceMybatisPk,hiupMybatisPk,sourceDbmanagePkArr,hiupDbmanagePkArr,hiupDataBaseKu]
                                    }
                                    console.log(sendHiupSource.arrays);
                                    $.ajax({
                                        type:"POST",
                                        url:portconfig+"match/mybatis",
                                        data:sendHiupSource,
                                        dataType : 'jsonp',
                                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                                        success: function(json){
                                            var result = json.result;
                                            console.log(result)
                                            if(result == true){
                                                js_shopTb.find("tr[id='"+js_consumePk_pop+"']").addClass("txtBg");
                                                // columnPop();
                                                closePop(".infoUpPop", ".iframe_box");
                                                closeLoading('white', 'loadingLong');
                                                passAll("保存","保存成功！");
                                            }else{
                                                closeLoading('white', 'loadingLong');
                                                errorsAll(result);
                                            }
                                        }
                                    }); 
                                    var sendHiupShop = {                                                    // 平台-消费
                                        "type":"insert",
                                        "tablename":"consume_platform_mapping",
                                        "arrays":[columnArr,consumeArr,hiupArrColumn,hiupArrDesc,shopArrColumn,shopArrDesc,shopNameArr,hiupNameArr,shopPKArr,hiupPKArr,shopMybatisPk,hiupMybatisPk,shopDbmanagePkArr,hiupDbmanagePkArr,hiupDataBaseKu]
                                    }
                                    console.log(sendHiupShop.arrays);
                                    $.ajax({
                                        type:"POST",
                                        url:portconfig+"match/mybatis",
                                        data:sendHiupShop,
                                        dataType : 'jsonp',
                                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                                        success: function(json){
                                        }
                                    });

                                }
                            });
                        }
                    }
                });
            });
            // 弹窗删除
            $("#js_macth_allo").undelegate(".delPop","click").delegate(".delPop","click",function(){
                var strTR = $(this).closest("tr");
                strTR.remove();
            });

            // 弹框查看
            js_shop_list.off("click").on("click",function(){
                columnPopStatic();
                js_shop_list.hide();
                js_shop_back.show();
                toTransmitPop.hide();
                toCancelPop.hide();
                toModifyPop.show();
                $("#js_popAllo_shop").prop("disabled",false);
            });
            // 弹框返回
            js_shop_back.off("click").on("click",function(){
                columnPop(); 
                js_shop_list.show();
                js_shop_back.hide();
                toTransmitPop.show();
                toCancelPop.show();
                toModifyPop.hide();
            });
        });
    };			

	// 右侧新增
	js_shopTb.undelegate(".addTR","click").delegate(".addTR","click",function(){

		var strTr = $(this).closest("tr");

		//strTr.removeClass('bgCur');

		var matchRightModel = '<tr class="inSetTB" id="js_shopTr_model">' 
								+ '<td>' 
									+ '<select id="js_right_vendor" class="selectModel">'
									+ '</select>' 
								+ '</td>' 
								+ '<td>' 
									+ '<select id="js_right_bank" class="selectModel">'
									+ '</select>' 
								+ '</td>'
								+ '<td>'
									+ '<div class="pRelative">' 
										+ '<input type="text" id="js_right_table" class="inputModel"/>'
										+ '<ul class="shopBlock" id="js_shopBlock">' + '</ul>'
									+ '</div>' 
								+ '</td>'
								+ '<td class="operaCenter">'  
	    								+ '<a href="javascript:;" class="save" id="savefn">' + '</a>'
			                            //+ '<a href="javascript:;" class="close">' + '</a>' 
			                            //+ '<a href="javascript:;" class="write">' + '编辑' + '</a>'
			                            + '<a href="javascript:;" class="addTR">' + '添加' + '</a>'
			                            + '<a href="javascript:;" class="del" id="delfn">' + '删除' + '</a>'
			                            + '<a href="javascript:;" class="linkBg">' + '配置' + '</a>'
	    						+ '</td>'
							+ '</tr>'
		js_shopTb.prepend(matchRightModel);
        // $("#js_shopTr_model").siblings("tr").removeClass("click");
        // $("#js_shopTr_model").siblings("tr").off("click");
        js_shopTb.find("p").hide();

		$("#js_shopTb .linkBg").hide();
        $("#js_shopTb .addTR").hide();
        $("#js_shopTb .del").hide();
        $(".inSetTB .addTR").hide();
        $(".inSetTB .save").addClass("showInline");
        $(".inSetTB .del").show();

		var sendLeftInit = {
			"type":"cs",
			"source":"厂商"
		}
		rightVendor(sendLeftInit);

	});	
			
	// 获得消费列中的厂商名
	function rightVendor(sendDate){
		//console.log("leftVendor");
		var js_right_vendor = $("#js_right_vendor")

		$.ajax({
	        type:"POST",
	        url:portconfig+"match/dbname",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		    success: function(json){

		    	var result = json.result[0];
		    	//console.log(result);
		    	
		    	var addHtmlRightVendor = '';
		    	for(var i=0;i<result.length;i++){
		    		addHtmlRightVendor += '<option name="'+ result[i] +'" value="">' + result[i] + '</option>'
		    	};

		    	js_right_vendor.html("");
		    	js_right_vendor.append(addHtmlRightVendor);

                js_right_vendor.find("option").each(function(){
                    if($(this).text() == $(".click").find("td").eq(0).text()){
                        $(this).remove();
                    }
                });

		    	// 获得源中的初始库
		    	var vendorVal = js_right_vendor.find("option:selected").attr("name");
		    	//console.log(vendorVal);
		    	var sendRightInit = {
					"type":"km",
					"name":vendorVal
				}
				rightShopBank(sendRightInit);
				// 获得源中的初始库
		    	js_right_vendor.unbind().change(function(){
		    		var vendorVal = js_right_vendor.find("option:selected").attr("name");
			    	var sendDateVendor = {
			    		"type":"km",
						"name":vendorVal
			    	}
			    	rightShopBank(sendDateVendor)
		    	});
	        }
	    });
	};
	// 消费列下库函数获取
	function rightShopBank(sendDate){

		var js_right_bank = $("#js_right_bank");					// 消费列下库注入接口
		var js_right_table = $("#js_right_table");					// 消费列下表 控件
		var addHtmlRightBank = '';

		$.ajax({
	        type:"POST",
	        url:portconfig+"match/dbname",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		    success: function(json){

		    	var result = json.result[0];
		    	//console.log(result);

		    	for(var m=0;m<result.length;m++){
		    		if(result[m].databaseType == 'oracle'){
		    			addHtmlRightBank += '<option style="'+ result[m].pk +'" id="'+ result[m].port +'" alt="'+ result[m].databaseType +'" class="'+ result[m].instanceName +'" type="'+ result[m].ip +'" value="'+ result[m].userPass +'" name="'+ result[m].userName +'">' + result[m].userName + '</option>'
		    		}else{
		    			addHtmlRightBank += '<option style="'+ result[m].pk +'" id="'+ result[m].port +'" alt="'+ result[m].databaseType +'" class="'+ result[m].instanceName +'" type="'+ result[m].ip +'" value="'+ result[m].userPass +'" name="'+ result[m].userName +'">' + result[m].instanceName + '</option>'
		    		}
		    	};
		    	//console.log(addHtmlRightBank);
		    	js_right_bank.html("");
		    	js_right_bank.append(addHtmlRightBank);

		    	// 获得源中的初始表
		    	var js_tablename = js_right_table.val();										// 源下输入框内容
		    	var js_databaseType = js_right_bank.find("option:selected").attr("alt");
		    	var js_port = js_right_bank.find("option:selected").attr("id");			
		    	var js_instanceName = js_right_bank.find("option:selected").attr("class");
		    	var js_ip = js_right_bank.find("option:selected").attr("type");
		    	var js_userPass = js_right_bank.find("option:selected").attr("value");
		    	var js_userName = js_right_bank.find("option:selected").attr("name");		

		    	var sendDateInit = {
	                "databaseType": js_databaseType,
	                "instanceName": js_instanceName,
	                "port":js_port,
	                "ip": js_ip,
	                "userName": js_userName,
	                "userPass": js_userPass,
					"tablename":js_tablename,
					"type": "tablename"
				}
				rightShopTable(sendDateInit);
				// 获得源中的表
		    	js_right_bank.unbind().change(function(){
		    		var js_tablename = js_right_table.val();
			    	var js_databaseType = js_right_bank.find("option:selected").attr("alt");
			    	var js_port = js_right_bank.find("option:selected").attr("id");	
			    	var js_instanceName = js_right_bank.find("option:selected").attr("class");
			    	var js_ip = js_right_bank.find("option:selected").attr("type");
			    	var js_userPass = js_right_bank.find("option:selected").attr("value");
			    	var js_userName = js_right_bank.find("option:selected").attr("name");
			    	var sendDate = {
		                "databaseType": js_databaseType,
		                "instanceName": js_instanceName,
		                "port":js_port,
		                "ip": js_ip,
		                "userName": js_userName,
		                "userPass": js_userPass,
						"tablename": js_tablename,
						"type": "tablename"
					}
					rightShopTable(sendDate);
		    	});
	        }
	    });
	};

	// 消费列下表函数获取
	function rightShopTable(sendDate){
		//console.log("leftTable");
		var js_right_table = $("#js_right_table");					// 源下表 控件
		var js_shopBlock = $("#js_shopBlock");						// 源下表注入接口
		var addHtmlcolumn = '';

		$.ajax({
	        type:"POST",
	        url:portconfig+"match/gettable",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		    success: function(json){
                
		    	var tablename = json.tablename[0];
                if(tablename == null){
                    addHtmlcolumn = '';
                }else{
    		    	for(var a=0;a<tablename.length;a++){
    		    		addHtmlcolumn += '<li>' + tablename[a] + '</li>'
    		    	}
                }
		    	js_shopBlock.html("");
		    	js_shopBlock.append(addHtmlcolumn);

		    	//点击任意位置 隐藏搜索内容盒子
				documentBox.off("click").on("click", function(){
					$("#js_shopBlock").hide();
				});

				// 点击输入框显示内容
				$("#js_right_table").on("click",function(event){
					event.stopPropagation();
					$("#js_shopBlock").show();
				});

		    	// 模糊查询
				var columnLi = js_shopBlock.find("li");
				var tableLeftInputVal = $("#js_right_table").val();
		    	columnLi.each(function(i){
					$(this).on("click", function(){
						var infoLi = $(this).text();
						$("#js_right_table").val("");
						$("#js_right_table").val(infoLi);
					});
				});

		    	$(".pRelative").delegate("#js_right_table", "keyup", function(){
					
					var tableLeftInputVal = $("#js_right_table").val();
					tableLeftInputVal = $.trim(tableLeftInputVal); // 用jQuery的trim方法删除前后空格
					
					if(tableLeftInputVal == ""){
						js_shopBlock.hide();
						return false;
					}else{
						js_shopBlock.show();
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
							js_shopBlock.hide();
							True = true;
						}
					}
				});
		    }
		});
        
        // 右侧新增保存
		$("#savefn").off("click").on("click",function(){

            successAll("保存","您确定要完成保存吗？");

            $(".saveInfoBtn").off("click").on("click", function(event){

                closePop(".infoUp", ".iframe_box");
                loadingShow('white', 'loadingLong', '数据保存中... 请您稍等，谢谢！');

    			// 消费列下表名
    			var js_consumeTabel = js_right_table.val();
    			//console.log(js_consumeTabel);

    			// 消费列下库名 及pk
    			var js_consumeDatabasePk = $("#js_right_bank").find("option:selected").attr("style");
    			var alt_right_table = $("#js_right_bank").find("option:selected").attr("alt");
    			
    			var js_consumeDatabase = '';
    			if(alt_right_table == 'oracle'){
    				js_consumeDatabase = $("#js_right_bank").find("option:selected").attr("name");
    			}else{
    				js_consumeDatabase = $("#js_right_bank").find("option:selected").attr("class");
    			}
    			// console.log(js_consumeDatabasePk);
    			// console.log(js_consumeDatabase);

    			// 消费列下厂商名
    			var js_sourceName = $("#js_right_vendor").find("option:selected").attr("name");
    			//console.log(js_sourceName);

    			// 来自左方选中的主键
    			var js_sourcePk = $(".click").attr("type");
    			//console.log(js_sourcePk);

    			// 来自左方选中的 平台的主键
    			var js_hiupDatabasePk = $(".click").attr("value");
    			// console.log(js_hiupDatabasePk);

    			// 来自左方选中的 平台的库名
    			var js_hiupDatabase = $(".click").find("td").eq(3).text();
    			var js_hiupTabel = $(".click").find("td").eq(4).text();
    			// console.log(js_hiupDatabase);
    			// console.log(js_hiupTabel);

    			var sendDateUp = {
    				"type":"insert",
    	            "consumeDatabase": js_consumeDatabase,
    	            "consumeDatabasePk": js_consumeDatabasePk,
    	      
    	            "consumeTabel": js_consumeTabel,
    	            "hiupDatabase": js_hiupDatabase,
    	            "hiupDatabasePk": js_hiupDatabasePk,
    	            "hiupTabel": js_hiupTabel,
    	         
    	            "sourceName": js_sourceName,
    	            "sourcePk": js_sourcePk,
    	            "status": "1"
    			}
                //console.log(sendDateUp);
                $("#js_shopTr_model").remove();
                //columnShop(sendAll);
                $.ajax({
                    type:"POST",
                    url:portconfig+"match/dmcbj",
                    data:sendDateUp,
                    dataType : 'jsonp',
                    jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                    success: function(json){
                        var result = json.result;
                        //console.log(result);
                        if(result == 'succes'){
                            columnShop(sendAll);
                            closeLoading('white', 'loadingLong');
                            passAll("保存","保存成功！");
                        }else{
                            closeLoading('white', 'loadingLong');
                            errorsAll(result);
                        }
                            
                    }
                });
            });
		});
		
	}

	// 左侧新增
	toAdd.off("click").on("click",function(){

		toTransmit.show();
		toAdd.hide();
        $("#js_zengjia").hide();

		var matchTableModel = '<tr class="click inSetTB" id="js_tr">' 
								+ '<td>' 
									+ '<select id="js_left_vendorLeft" class="selectModel">'
									+ '</select>' 
								+ '</td>' 
								+ '<td>' 
									+ '<select id="js_left_bankLeft" class="selectModel">'
									+ '</select>' 
								+ '</td>'
								+ '<td>'
									+ '<div class="pRelative">' 
										+ '<input type="text" id="js_left_tableLeft" class="inputModel"/>'
										+ '<ul class="platformBlock" id="js_platformBlock">' + '</ul>'
									+ '</div>' 
								+ '</td>'
								+ '<td>' 
									+ '<select id="js_left_bankRight" class="selectModel">'
									+ '</select>' 
								+ '</td>'
								+ '<td>' 
									+ '<div class="pRelative">' 
										+ '<input type="text" id="js_left_tableRight" class="inputModel"/>'
										+ '<ul class="sourceBlock" id="js_sourceBlock">' + '</ul>'
									+ '</div>'  
								+ '</td>'
							+ '</tr>'

		js_table_TB.find("tr").eq(0).before(matchTableModel);
        $("#js_init_tr").remove();
		$("#js_tr").siblings("tr").removeClass("click");
        $("#js_tr").siblings("tr").off("click");
        js_shopTb.html("");
        js_shopTb.append('<td colspan="4" class="overPage_L">' + '<p>' + "相关内容没有数据！" + '</p>' + '</td>');

		var sendLeftInit = {
			"type":"cs",
			"source":"厂商"
		}
		leftVendor(sendLeftInit);
		var sendRightInit = {
			"type":"cs",
			"source":"平台"
		}
		rightVendorHiup(sendRightInit);
	});

	// 获得源中的厂商名
	function leftVendor(sendDate){

		var js_left_vendorLeft = $("#js_left_vendorLeft")

		$.ajax({
	        type:"POST",
	        url:portconfig+"match/dbname",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		    success: function(json){

		    	var result = json.result[0];
		    	
		    	var addHtmlLeftVendor = '';
		    	for(var i=0;i<result.length;i++){
		    		addHtmlLeftVendor += '<option name="'+ result[i] +'" value="">' + result[i] + '</option>'
		    	};
                //console.log(addHtmlLeftVendor)
		    	js_left_vendorLeft.html("");
		    	js_left_vendorLeft.append(addHtmlLeftVendor);

		    	// 获得源中的初始库
		    	var vendorVal = js_left_vendorLeft.find("option:selected").attr("name");
		    	var sendDateInit = {
					"type":"km",
					"name":vendorVal
				}
				leftBank(sendDateInit);
				// 获得源中的初始库
		    	js_left_vendorLeft.unbind().change(function(){

                    $("#js_platformBlock").html("");
		    		var vendorVal = js_left_vendorLeft.find("option:selected").attr("name");
			    	var sendDateVendor = {
			    		"type":"km",
						"name":vendorVal
			    	}
			    	leftBank(sendDateVendor)
		    	});
	        }
	    });
	};
	// 源下库函数获取
	function leftBank(sendDate){

		var js_left_bankLeft = $("#js_left_bankLeft");						// 源下库注入接口
		var js_left_tableLeft = $("#js_left_tableLeft");					// 源下表 控件
		var addHtmlLeftBank = '';

		$.ajax({
	        type:"POST",
	        url:portconfig+"match/dbname",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		    success: function(json){

		    	var result = json.result[0];

		    	for(var m=0;m<result.length;m++){
		    		if(result[m].databaseType == 'oracle'){
		    			addHtmlLeftBank += '<option style="'+ result[m].pk +'" id="'+ result[m].port +'" alt="'+ result[m].databaseType +'" class="'+ result[m].instanceName +'" type="'+ result[m].ip +'" value="'+ result[m].userPass +'" name="'+ result[m].userName +'">' + result[m].userName + '</option>'
		    		}else{
		    			addHtmlLeftBank += '<option style="'+ result[m].pk +'" id="'+ result[m].port +'" alt="'+ result[m].databaseType +'" class="'+ result[m].instanceName +'" type="'+ result[m].ip +'" value="'+ result[m].userPass +'" name="'+ result[m].userName +'">' + result[m].instanceName + '</option>'
		    		}
		    	};
		    	//console.log(addHtmlLeftBank);
		    	js_left_bankLeft.html("");
		    	js_left_bankLeft.append(addHtmlLeftBank);

		    	// 获得源中的初始表
		    	var js_tablename = js_left_tableLeft.val();										// 源下输入框内容
		    	var js_databaseType = js_left_bankLeft.find("option:selected").attr("alt");
		    	var js_port = js_left_bankLeft.find("option:selected").attr("id");			
		    	var js_instanceName = js_left_bankLeft.find("option:selected").attr("class");
		    	var js_ip = js_left_bankLeft.find("option:selected").attr("type");
		    	var js_userPass = js_left_bankLeft.find("option:selected").attr("value");
		    	var js_userName = js_left_bankLeft.find("option:selected").attr("name");		

		    	var sendDateInit = {
	                "databaseType": js_databaseType,
	                "instanceName": js_instanceName,
	                "port":js_port,
	                "ip": js_ip,
	                "userName": js_userName,
	                "userPass": js_userPass,
					"tablename":js_tablename,
					"type": "tablename"
				}
                //console.log(sendDateInit);
				leftTable(sendDateInit);
				// 获得源中的表
		    	js_left_bankLeft.unbind().change(function(){

		    		var js_tablename = js_left_tableLeft.val();
			    	var js_databaseType = js_left_bankLeft.find("option:selected").attr("alt");
			    	var js_port = js_left_bankLeft.find("option:selected").attr("id");	
			    	var js_instanceName = js_left_bankLeft.find("option:selected").attr("class");
			    	var js_ip = js_left_bankLeft.find("option:selected").attr("type");
			    	var js_userPass = js_left_bankLeft.find("option:selected").attr("value");
			    	var js_userName = js_left_bankLeft.find("option:selected").attr("name");
			    	var sendDate = {
		                "databaseType": js_databaseType,
		                "instanceName": js_instanceName,
		                "port":js_port,
		                "ip": js_ip,
		                "userName": js_userName,
		                "userPass": js_userPass,
						"tablename": js_tablename,
						"type": "tablename"
					}
 
					leftTable(sendDate);
		    	});
	        }
	    });
	};
	// 源下表函数获取
	function leftTable(sendDate){

		var js_left_tableLeft = $("#js_left_tableLeft");					// 源下表 控件
		var js_platformBlock = $("#js_platformBlock");						// 源下表注入接口
		var addHtmlPlatform = '';

		$.ajax({
	        type:"POST",
	        url:portconfig+"match/gettable",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		    success: function(json){

		    	var tablename = json.tablename[0];

                if(tablename == null){
                    addHtmlPlatform = '';
                }else{
    		    	for(var a=0;a<tablename.length;a++){
    		    		addHtmlPlatform += '<li>' + tablename[a] + '</li>'
    		    	}
                }
                js_platformBlock.html("");
                js_platformBlock.append(addHtmlPlatform);

		    	//点击任意位置 隐藏搜索内容盒子
				documentBox.off("click").on("click", function(){
					$("#js_platformBlock").hide();
				});

				// 点击输入框显示内容
				$("#js_left_tableLeft").on("click",function(event){

					event.stopPropagation();
					$("#js_platformBlock").show();
                    $("#js_left_tableLeft").val("");
				});

		    	// 模糊查询
				var platformLi = js_platformBlock.find("li");
				var tableLeftInputVal = $("#js_left_tableLeft").val();
		    	platformLi.each(function(i){
					$(this).on("click", function(){
						var infoLi = $(this).text();

						$("#js_left_tableLeft").val("");
						$("#js_left_tableLeft").val(infoLi);
					});
				});

		    	$(".pRelative").delegate("#js_left_tableLeft", "keyup", function(){
					
					var tableLeftInputVal = $("#js_left_tableLeft").val();
					tableLeftInputVal = $.trim(tableLeftInputVal); // 用jQuery的trim方法删除前后空格
					
					if(tableLeftInputVal == ""){
						js_platformBlock.hide();
						return false;
					}else{
						js_platformBlock.show();
						platformLi.hide();

						var nPos;
						var True = false;
						
						for(var i = 0; i<platformLi.length; i++){
							var sTxt = $(platformLi[i]).text();
							nPos = findCompare(tableLeftInputVal, sTxt); 
							if(nPos>=0){  
								platformLi.eq(i).show();
								True = true;
							}
						}
						if(!True){
							js_platformBlock.hide();
							True = true;
						}
					}
				});
		    }
		});
	}

    // 获得平台名
    function rightVendorHiup(sendDate){
        //console.log("leftVendor");
        var js_left_vendorLeft = $("#js_left_vendorLeft")

        $.ajax({
            type:"POST",
            url:portconfig+"match/dbname",
            data:sendDate,
            dataType : 'jsonp',
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            success: function(json){

                var result = json.result[0][0];
                //console.log(result);
                // 获得源中的初始库
                var sendDateInit = {
                    "type":"km",
                    "name":result
                }
                rightBank(sendDateInit);
            }
        });
    };
	// 平台下库函数获取
	function rightBank(sendDate){

		var js_left_bankRight = $("#js_left_bankRight");					// 平台下库注入接口
		var js_left_tableRight = $("#js_left_tableRight");					// 平台下表 控件
		var addHtmlRightBank = '';

		$.ajax({
	        type:"POST",
	        url:portconfig+"match/dbname",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		    success: function(json){

		    	var result = json.result[0];
		    	
		    	for(var m=0;m<result.length;m++){
		    		if(result[m].databaseType == 'oracle'){
		    			addHtmlRightBank += '<option style="'+ result[m].pk +'" id="'+ result[m].port +'" alt="'+ result[m].databaseType +'" class="'+ result[m].instanceName +'" type="'+ result[m].ip +'" value="'+ result[m].userPass +'" name="'+ result[m].userName +'">' + result[m].userName + '</option>'
		    		}else{
		    			addHtmlRightBank += '<option style="'+ result[m].pk +'" id="'+ result[m].port +'" alt="'+ result[m].databaseType +'" class="'+ result[m].instanceName +'" type="'+ result[m].ip +'" value="'+ result[m].userPass +'" name="'+ result[m].userName +'">' + result[m].instanceName + '</option>'
		    		}
		    	};
		    	js_left_bankRight.html("");
		    	js_left_bankRight.append(addHtmlRightBank);

		    	// 获得源中的初始表
		    	var js_tablename = js_left_tableRight.val();										// 源下输入框内容
		    	var js_databaseType = js_left_bankRight.find("option:selected").attr("alt");
		    	var js_port = js_left_bankRight.find("option:selected").attr("id");			
		    	var js_instanceName = js_left_bankRight.find("option:selected").attr("class");
		    	var js_ip = js_left_bankRight.find("option:selected").attr("type");
		    	var js_userPass = js_left_bankRight.find("option:selected").attr("value");
		    	var js_userName = js_left_bankRight.find("option:selected").attr("name");		

		    	var sendDateInit = {
	                "databaseType": js_databaseType,
	                "instanceName": js_instanceName,
	                "port":js_port,
	                "ip": js_ip,
	                "userName": js_userName,
	                "userPass": js_userPass,
					"tablename":js_tablename,
					"type": "tablename"
				}
                //console.log(sendDateInit)
				rightTable(sendDateInit);
				// 获得源中的表
		    	js_left_bankRight.unbind().change(function(){

                    $("#js_platformBlock").html("");

		    		var js_tablename = js_left_tableRight.val();
			    	var js_databaseType = js_left_bankRight.find("option:selected").attr("alt");
			    	var js_port = js_left_bankRight.find("option:selected").attr("id");	
			    	var js_instanceName = js_left_bankRight.find("option:selected").attr("class");
			    	var js_ip = js_left_bankRight.find("option:selected").attr("type");
			    	var js_userPass = js_left_bankRight.find("option:selected").attr("value");
			    	var js_userName = js_left_bankRight.find("option:selected").attr("name");
			    	var sendDate = {
		                "databaseType": js_databaseType,
		                "instanceName": js_instanceName,
		                "port":js_port,
		                "ip": js_ip,
		                "userName": js_userName,
		                "userPass": js_userPass,
						"tablename": js_tablename,
						"type": "tablename"
					}
					rightTable(sendDate);
		    	});
	        }
	    });
	};
	// 平台下表函数获取
	function rightTable(sendDate){
		//console.log("rightTable");
		var js_sourceBlock = $("#js_sourceBlock");							// 平台下表注入接口
		var js_left_tableRight = $("#js_left_tableRight");					// 平台下表 控件
		var addHtmlPlatform = '';

		$.ajax({
	        type:"POST",
	        url:portconfig+"match/gettable",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		    success: function(json){

		    	var tablename = json.tablename[0];
                
                if(tablename == null){
                    addHtmlPlatform = '';
                }else{
                    for(var a=0;a<tablename.length;a++){
                        addHtmlPlatform += '<li>' + tablename[a] + '</li>';
                    }
                }
		    	
		    	js_sourceBlock.html("");
		    	js_sourceBlock.append(addHtmlPlatform);
		    	//console.log(addHtmlPlatform);
		    	
				//点击任意位置 隐藏搜索内容盒子
				documentBox.on("click", function(){
					$("#js_sourceBlock").hide();
					$("#js_platformBlock").hide();
				});

				// 点击输入框显示内容
				$("#js_left_tableRight").on("click",function(event){
					event.stopPropagation();
					$("#js_sourceBlock").show();
				});

		    	// 模糊查询
				var sourceLi = js_sourceBlock.find("li");
				var tableRightInputVal = $("#js_left_tableRight").val();
		    	sourceLi.each(function(i){
					$(this).on("click", function(){
						var infoLi = $(this).text();
						$("#js_left_tableRight").val("");
						$("#js_left_tableRight").val(infoLi);
						js_sourceBlock.hide();
					});
				});
		    	
		    	//js_sourceBlock.show();
		    	$(".pRelative").delegate("#js_left_tableRight", "keyup", function(){
				
					var tableRightInputVal = $("#js_left_tableRight").val();
					
					if(tableRightInputVal == ""){

						js_sourceBlock.hide();
						return false;
					}else{
						js_sourceBlock.show();
						sourceLi.hide();
						tableRightInputVal = $.trim(tableRightInputVal); // 用jQuery的trim方法删除前后空格
						var nPos;
						var True = false;
						
						for(var i = 0; i<sourceLi.length; i++){
							var sTxt = $(sourceLi[i]).text();
							nPos = findCompare(tableRightInputVal, sTxt); 
							if(nPos>=0){  
								sourceLi.eq(i).show();
								True = true;
							}
						}
						if(!True){
							js_sourceBlock.hide();
							True = true;
						}
					}
					
				});
		    }
		});
	}
	
	// 左侧新增后保存
	toTransmit.off("click").on("click",function(){

		toTransmit.hide();
		toAdd.show();

		// 源下厂商名
		var js_left_vendorLeft = $("#js_left_vendorLeft").find("option:selected").attr("name");
		// console.log(js_left_vendorLeft);

		// 源下库名 及pk
		var alt_bankLeft = $("#js_left_bankLeft").find("option:selected").attr("alt");
		var js_pk_bankLeft = $("#js_left_bankLeft").find("option:selected").attr("style");

		var js_left_bankLeft = '';
		if(alt_bankLeft == 'oracle'){
			js_left_bankLeft = $("#js_left_bankLeft").find("option:selected").attr("name");
		}else{
			js_left_bankLeft = $("#js_left_bankLeft").find("option:selected").attr("class");
		}
		// console.log(js_left_bankLeft);
		// console.log(js_pk_bankLeft);

		// 源下表名
		var js_left_tableLeft = $("#js_left_tableLeft").val();
		// console.log(js_left_tableLeft);

		// 平台下库名 及pk
		var alt_bankLeft = $("#js_left_bankRight").find("option:selected").attr("alt");
		var js_pk_bankRight = $("#js_left_bankRight").find("option:selected").attr("style");

		var js_left_bankRight = '';
		if(alt_bankLeft == 'oracle'){
			js_left_bankRight = $("#js_left_bankRight").find("option:selected").attr("name");
		}else{
			js_left_bankRight = $("#js_left_bankRight").find("option:selected").attr("class");
		}
		// console.log(js_left_bankRight);
		// console.log(js_pk_bankRight);

		// 平台下表名
		var js_left_tableRight = $("#js_left_tableRight").val();
		// console.log(js_left_tableRight);

        successAll("保存","您确定要完成保存吗？");

        $(".saveInfoBtn").off("click").on("click", function(event){

            closePop(".infoUp", ".iframe_box");
            loadingShow('white', 'loadingLong', '数据保存中... 请您稍等，谢谢！');

    		var sendDateSave = {
    			"type":"insert",
    		    "hiupDatabase": js_left_bankRight,
    		    "hiupTabel": js_left_tableRight,
    		    "sourceDatabase": js_left_bankLeft,
    		    "sourceTabel": js_left_tableLeft,
    		    "status": "0",
    		    "hiupDatabasePk": js_pk_bankRight,
    		    "sourceDatabasePk": js_pk_bankLeft,
    		    "sourceName": js_left_vendorLeft
    		}

    		$.ajax({
    	        type:"POST",
    	        url:portconfig+"match/dbbj",
    			data:sendDateSave,
    	        dataType : 'jsonp',
    	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
    		    success: function(json){
                    var result = json.result;
                    //console.log(result);
                    if(result == 'succes'){
                        $("#js_tr").remove();
                        tableMatchLeft(sendInit);
                        closeLoading('white', 'loadingLong');
                        passAll("保存","保存成功！");
                    }else{
                        closeLoading('white', 'loadingLong');
                        errorsAll(result);
                    }
    		    }
    		});
        });
	});
	
    // 左侧删除
	toDel.off("click").on("click",function(){

		// 本行的PK
		var js_sourcePk = $(".click").attr("type");

        delAll("删除","您确定要删除吗？");
        $(".delInfoBtn").off("click").on("click", function(event){
            if(js_sourcePk == undefined){
                $(".click").remove();
                tableMatchLeft(sendInit);
                closePop(".infoUp", ".iframe_box");
            }else{
                closePop(".infoUp", ".iframe_box");
                loadingShow('white', 'loadingLong', '数据删除中... 请您稍等，谢谢！');

                var sendDateDel = {
                    "type":"del",
                    "sourcePk":js_sourcePk
                }

                $.ajax({
                    type:"POST",
                    url:portconfig+"match/dbbj",
                    data:sendDateDel,
                    dataType : 'jsonp',
                    jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                    success: function(json){
                        var result = json.result;
                        if(result == 'succes'){
                            tableMatchLeft(sendInit);
                            closeLoading('white', 'loadingLong');
                            passAll("删除","删除成功！");
                        }else{
                            closeLoading('white', 'loadingLong');
                            errorsAll(result);
                        }
                    }
                });
            }
        });
	});

    // 查询注入
    var sendQueryInit = {
        "type":"cs",
        "source":"平台"
    }
    queryObtain(sendQueryInit);
    function queryObtain(sendDate){
        //console.log("leftVendor");

        $.ajax({
            type:"POST",
            url:portconfig+"match/dbname",
            data:sendDate,
            dataType : 'jsonp',
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            success: function(json){

                var result = json.result[0][0];
                //console.log(result);
                // 获得源中的初始库
                var sendDateInit = {
                    "type":"km",
                    "name":result
                }
                queryIn(sendDateInit);
            }
        });
    };
    
    function queryIn(sendDate){
        //console.log("queryIn");
        var js_query_select = $("#js_query_select");                        // 查询 库注入接口
        var js_keywordCon = $("#js_keywordCon");                            // 查询 表
        var js_queryBlock = $("#js_queryBlock");                            // 查询 隐藏内容
        var addHtmlQuery = '';
        var addHtmlQueryTable = '';

        $.ajax({
            type:"POST",
            url:portconfig+"match/dbname",
            data:sendDate,
            dataType : 'jsonp',
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            success: function(json){

                var result = json.result[0];
                //console.log(result);
                
                for(var m=0;m<result.length;m++){
                    if(result[m].databaseType == 'oracle'){
                        addHtmlQuery += '<option style="'+ result[m].pk +'" id="'+ result[m].port +'" alt="'+ result[m].databaseType +'" class="'+ result[m].instanceName +'" type="'+ result[m].ip +'" value="'+ result[m].userPass +'" name="'+ result[m].userName +'">' + result[m].userName + '</option>'
                    }else{
                        addHtmlQuery += '<option style="'+ result[m].pk +'" id="'+ result[m].port +'" alt="'+ result[m].databaseType +'" class="'+ result[m].instanceName +'" type="'+ result[m].ip +'" value="'+ result[m].userPass +'" name="'+ result[m].userName +'">' + result[m].instanceName + '</option>'
                    }
                };
                js_query_select.html("");
                js_query_select.append(addHtmlQuery);

            }
        });
    }
	// 查询功能
	js_query.off("click").on("click",function(){

		var typePk = $("#js_query_select").find("option:selected").text();
		var keywordCon = $("#js_keywordCon").val();

		var sendSearch = {
			"type":"selectPage",
            "currentpage":1,
            "tablename":"DICT_SOURCE_MAPPING",
            "arrays":[["HIUP_DATABASE=",typePk],["HIUP_TABEL like","%"+keywordCon+"%"]]
		}
 
		tableMatchLeft(sendSearch);

        $(".page_bodyMatch").undelegate("#js_page_home","click").delegate("#js_page_home", "click", function(){
            
            NowPage = 1;

            var sendPageNum = {
                "currentpage":NowPage,
                "type":"selectPage",
                "tablename":"DICT_SOURCE_MAPPING",
                "arrays":[["HIUP_DATABASE=",typePk],["HIUP_TABEL like","%"+keywordCon+"%"]]
            }

            tableMatchLeft(sendPageNum);
            
        });
        
        $(".page_bodyMatch").undelegate("#js_page_pre","click").delegate("#js_page_pre", "click", function(){
            
            page_cur -= 1;
            
            if(page_cur <= 0){
                page_cur = 1;
            }else{
                NowPage = page_cur;
            };
            
            var sendPageNum = {
                "currentpage":NowPage,
                "type":"selectPage",
                "tablename":"DICT_SOURCE_MAPPING",
                "arrays":[["HIUP_DATABASE=",typePk],["HIUP_TABEL like","%"+keywordCon+"%"]]
            }

            tableMatchLeft(sendPageNum);
             
        });
        
        $(".page_bodyMatch").undelegate("#js_page_next","click").delegate("#js_page_next", "click", function(){

            page_cur = parseInt(page_cur) + 1;
            
            if(page_cur > page_total){
                page_cur = page_total
            }else{
                NowPage = page_cur;
        
            };
            
            var sendPageNum = {
                "currentpage":NowPage,
                "type":"selectPage",
                "tablename":"DICT_SOURCE_MAPPING",
                "arrays":[["HIUP_DATABASE=",typePk],["HIUP_TABEL like","%"+keywordCon+"%"]]
            }

            tableMatchLeft(sendPageNum);
            
        });
        
        $(".page_bodyMatch").undelegate("#js_page_end","click").delegate("#js_page_end", "click", function(){
            
            NowPage = page_total;
            
            var sendPageNum = {
                "currentpage":NowPage,
                "type":"selectPage",
                "tablename":"DICT_SOURCE_MAPPING",
                "arrays":[["HIUP_DATABASE=",typePk],["HIUP_TABEL like","%"+keywordCon+"%"]]
            }

            tableMatchLeft(sendPageNum);
            
        });

        $(".page_bodyMatch").undelegate("#js_page_jump","click").delegate("#js_page_jump", "click", function(){

            var jumpPage = $(".jump").val();
            var sendPageNum = {
                "currentpage":jumpPage,
                "type":"selectPage",
                "tablename":"DICT_SOURCE_MAPPING",
                "arrays":[["HIUP_DATABASE=",typePk],["HIUP_TABEL like","%"+keywordCon+"%"]]
            }
  
            tableMatchLeft(sendPageNum);
        });
	});

	// 分页功能 
	$(".page_bodyMatch").undelegate("#js_page_home","click").delegate("#js_page_home", "click", function(){
		
		NowPage = 1;

		var sendPageNum = {
			"currentpage":NowPage,
			"type":"selectPage",
            "tablename":"DICT_SOURCE_MAPPING"
		}

		tableMatchLeft(sendPageNum);
		
	});
	
	$(".page_bodyMatch").undelegate("#js_page_pre","click").delegate("#js_page_pre", "click", function(){
		
		page_cur -= 1;
		
		if(page_cur <= 0){
			page_cur = 1;
		}else{
			NowPage = page_cur;
		};
		
		var sendPageNum = {
			"currentpage":NowPage,
			"type":"selectPage",
            "tablename":"DICT_SOURCE_MAPPING",
            "arrays":[[]]
		}

		tableMatchLeft(sendPageNum);
		 
	});
	
	$(".page_bodyMatch").undelegate("#js_page_next","click").delegate("#js_page_next", "click", function(){
		
		page_cur = parseInt(page_cur) + 1;
		
		if(page_cur > page_total){
			page_cur = page_total
		}else{
			NowPage = page_cur;
	
		};
		
		var sendPageNum = {
			"currentpage":NowPage,
			"type":"selectPage",
            "tablename":"DICT_SOURCE_MAPPING",
            "arrays":[[]]
		}

		tableMatchLeft(sendPageNum);
		
	});
	
	$(".page_bodyMatch").undelegate("#js_page_end","click").delegate("#js_page_end", "click", function(){
		
		NowPage = page_total;
		
		var sendPageNum = {
			"currentpage":NowPage,
			"type":"selectPage",
            "tablename":"DICT_SOURCE_MAPPING",
            "arrays":[[]]
		}

		tableMatchLeft(sendPageNum);
		
	});

	$(".page_bodyMatch").undelegate("#js_page_jump","click").delegate("#js_page_jump", "click", function(){

        var jumpPage = $(".jump").val();
		var sendPageNum = {
			"currentpage":jumpPage,
			"type":"selectPage",
            "tablename":"DICT_SOURCE_MAPPING",
            "arrays":[[]]
		}
		tableMatchLeft(sendPageNum);
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