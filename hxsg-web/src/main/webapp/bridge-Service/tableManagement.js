/* 监控平台后台系统，页面数据动态生成 */

$(function(){
	
	// var localhost = "http://localhost/controlSystemDictionary/";
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

	var sendInit = {	
		"currentpage":1,
		"databaseName":"",
		"type":"select"
	}
	tableManagement(sendInit);
	function tableManagement(sendDate){

		var js_tableTd = $("#js_tableTd");
		// var pageNumber = page;
		var addHtmlPushTB = '';

		$.ajax({
	        type:"POST",
	        url:portconfig+"db/dbbj",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		        success: function(json){

			// $.post(localhost + "data-service/tableManagement.json",sendDate,function(json){

				var arrays = json.arrays;
				page_cur = json.page.currentpage;
				page_total = json.page.totalsize;

				if(arrays != null){
					if(page_cur <= page_total){
						if(page_total > 1){
							js_pagelist.show();
						}else{
							js_pagelist.hide();
						}
						js_page_show.html("");
						js_page_show.html(page_cur + " / " + page_total + "页");

						var addHtmlManageTr = '';
						for(var i=0;i<arrays.length;i++){

							addHtmlManageTr +=  '<tr alt="'+ arrays[i].PK +'">'
													+'<td>' + arrays[i].DATABASE_NAME + '</td>'
													+'<td>' + arrays[i].SOURCE + '</td>'
													+'<td>' + arrays[i].IP + '</td>'
													+'<td>' + arrays[i].PORT + '</td>'
													+'<td>' + arrays[i].DATABASE_TYPE + '</td>'
													+'<td>' + arrays[i].INSTANCE_NAME + '</td>'
													+'<td>' + arrays[i].USER_NAME + '</td>'
													+'<td>' + arrays[i].USER_PASS + '</td>'
													+'<td class="operaCenter">' 
														+ '<a href="javascript:;" class="connection" value="insert">' + '测试连接' + '</a>'
														+ '<a href="javascript:;" class="save savefn" value="insert">' + '</a>'
							                            + '<a href="javascript:;" class="close">' + '</a>' 
							                            + '<a href="javascript:;" class="write">' + '编辑' + '</a>'
							                            + '<a href="javascript:;" class="addTR addTRfn" value="insert">' + '添加' + '</a>'
							                            + '<a href="javascript:;" class="del delfn" value="upanddel">' + '删除' + '</a>'
													+ '</td>'
												'</tr>'
						}
						
						js_tableTd.html("");
						js_tableTd.append(addHtmlManageTr);
					}else{
						js_tableTd.html("");
						js_tableTd.append('<td colspan="9" class="overPage_L">' + '<p>' + "您输入的页码已超出当前内容总页码！" + '</p>' + '</td>');
						// js_tableTd.append('<tr>' + '<td colspan="9">' + "没有数据！" + '</td>' + '</tr>');
					}
				}else{
					js_tableTd.html("");
					js_tableTd.append('<tr class="js_addquery">' + '<td colspan="9" class="overPage_L">' + '<p>' + "未能查询到相关内容！" + '<a href="#" style="color:#7ba0cf;" class="addTRfn ml15 gestures">'+'增加数据'+'</a>' + '</p>' + '</td>' + '</tr>');
					js_pagelist.hide();
				};	

				// 表格背景和增删改
				tableColor();
				domainTable("js_tableTd");
			// },"json");
			}
		});
	}

	 // 增删改查
    function domainTable(id){

        var pid = "#" + id + " ";
        var textArr = [];

        // 修改
        $(pid + "a.write").each(function(i){

        	var _this = $(this);
            $(this).off("click").on("click",function(){

                var strTR = _this.closest("tr"); 					// 被点击修改按钮的祖级（当前行）
                var strTD = _this.closest("td");

                $(pid + ".write").hide();
                $(pid + ".addTR").hide();
                $(pid + ".del").hide();
                $(pid + ".connection").hide();						// 20160905 增加
                strTD.find(".connection").show();					// 20160905 增加
                strTD.find(".connection").addClass("js_update");	// 20160905 增加
                strTD.find(".save").css('display','inline-block');
                strTD.find(".close").css('display','inline-block');

                strTR.siblings().removeClass("click");
                strTR.addClass("click");

                // 修改时出现输入控件
        		var len = strTR.find("td").length-1;				// 有内容的列数
                for(var e=0;e<len;e++){
                    var tdSpan = strTR.find("td").eq(e);
                    var text = strTR.find("td").eq(e).text();
					textArr.push(text);
					
                    if(e == 1){
                    	tdSpan.text("");
		                tdSpan.append('<select class="selectModel">'
		                				+ '<option value="厂商">' + '厂商' + '</option>'
		                				+ '<option value="平台">' + '平台' + '</option>'
		                				+ '</select>'
		                			);
		                tdSpan.find("input[type='text']").val(text);
                    }else if(e == 4){
                    	tdSpan.text("");
		                tdSpan.append('<select class="selectModel">'
		                				+ '<option value="ORACLE">' + 'ORACLE' + '</option>'
		                				+ '<option value="MYSQL">' + 'MYSQL' + '</option>'
		                				+ '<option value="SQLSERVER2000">' + 'SQLSERVER2000' + '</option>'
		                				+ '<option value="SQLSERVER2005">' + 'SQLSERVER2005' + '</option>'
		                				+ '<option value="SQLSERVER2008">' + 'SQLSERVER2008' + '</option>'
	
		                				+ '</select>'
		                			);
		                tdSpan.find('select option').each(function(){
							if($(this).text() == text){
								$(this).prop('selected',true);
							};
						});
                    }else{
                    	tdSpan.text("");
		                tdSpan.append('<input type="text" value="" class="dataInputShort"/>');
		                tdSpan.find("input[type='text']").val(text);
                    }
                }
            })
        });

		// 关闭
        $(pid).undelegate("a.close", "click").delegate("a.close", "click", function(){

            var strTR = $(this).closest("tr"); 					// 被点击修改按钮的祖级（当前行）
            var strTD = $(this).closest("td");

            $(pid + ".write").show();
            $(pid + ".addTR").show();
            $(pid + ".del").show();
            $(pid + ".connection").show();						// 20160905 增加
            strTD.find(".connection").removeClass("js_update");	// 20160905 增加
            strTD.find(".save").css('display','none');
            strTD.find(".close").css('display','none');

            // 将获取到的控件值依次注入到每个td中
            var len = textArr.length;
            for(var e=0;e<len;e++){
            	var tdStatic = strTR.find("td").eq(e);
            	tdStatic.html("");
            	tdStatic.append(textArr[e]);
            }
            textArr = [];
        });
		
		// 保存
		$(pid).undelegate("a.savefn", "click").delegate("a.savefn", "click", function(){

			var strTR = $(this).closest("tr"); 					// 被点击修改按钮的祖级（当前行）

			successAll("保存","您确定要完成保存吗？");
			$(".btnBox").undelegate(".saveInfoBtn", "click").delegate(".saveInfoBtn", "click", function(event){

				closePop(".infoUp", ".iframe_box");
				loadingShow('white', 'loadingLong', '数据保存中... 请您稍等，谢谢！');

				var trPk = strTR.attr("alt");
				
				// 获取当前行的所有控件值
	            var selectVal = strTR.find("option:selected");
	            var arraySelect = [];
	            for(var y=0;y<selectVal.length;y++){
	            	var select = selectVal.eq(y).val();
					arraySelect.push(select);
	            }

	            var lenInput = strTR.find("input").length;
	            var arrayClose = [];
	            for(var z=0;z<lenInput;z++){
	            	var input = strTR.find("input").eq(z).val();
	            	if(z == 1){
	            		arrayClose.push(arraySelect[0]);
	            		arrayClose.push(input);
	            	}else if(z == 3){
	            		arrayClose.push(arraySelect[1]);
	            		arrayClose.push(input);
	            	}else{
	            		arrayClose.push(input);
	            	}
	            }

	            var sendSave = {
					"type":"update",
		            "databaseName": arrayClose[0],
		            "databaseType": arrayClose[4],
		            "instanceName": arrayClose[5],
		            "ip": arrayClose[2],
		            "port": arrayClose[3],
					"userName": arrayClose[6],
		            "userPass": arrayClose[7],
		            "source": arrayClose[1],
					"pk": trPk
		        }
		        $.ajax({
		        type:"POST",
		        url:portconfig+"db/dbbj",
				data:sendSave,
		        dataType : 'jsonp',
		        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			        success: function(json){
		        		closeLoading('white', 'loadingLong');
						passAll("保存","保存成功！");
	        			tableManagement(sendInit);
			        }
			    });
			});
		});

		// 删除
		$(pid + "a.delfn").off("click").on("click", function(){

			var strTR = $(this).closest("tr"); 					// 被点击修改按钮的祖级（当前行）

			delAll("删除","您确定要删除吗？");
			$(".btnBox").undelegate(".delInfoBtn", "click").delegate(".delInfoBtn", "click", function(event){
				event.stopPropagation();
				
				closePop(".infoUp", ".iframe_box");
				loadingShow('white', 'loadingLong', '数据删除中... 请您稍等，谢谢！');
				var trPk = strTR.attr("alt");

				var sendDel = {
					"type":"del",
					"pk": trPk
				}

				$.ajax({
		        type:"POST",
		        url:portconfig+"db/dbbj",
				data:sendDel,
		        dataType : 'jsonp',
		        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			        success: function(json){
			        	var result = json.result;
		        		if(result == "succes"){
			        		closeLoading('white', 'loadingLong');
							passAll("删除","删除成功！");
		        			tableManagement(sendInit);
			        	}
			        }
			    });
			});
		});

		// 新增
	    $(pid + "a.addTRfn").each(function(i){
	        var _this = $(this);
	        //console.log("OK");
	        $(this).off("click").on("click",function(event){

	        	event.stopPropagation;
	            var strTR = _this.closest('tr');
	            $(".js_addquery").hide();

	            strTR.after(
	            	'<tr class="addSingel click inSetTB">' 
	            	    + '<td>' + '<input type="text" class="dataInputShort" />' + '</td>'
	            	    + '<td>'  
	            	    	+ '<select id="" class="selectModel">'
                                + '<option value="厂商">' + '厂商' + '</option>'
                				+ '<option value="平台">' + '平台' + '</option>'
                            + '</select>'
	            	    + '</td>'
	            	    + '<td>' + '<input type="text" class="dataInputShort" />' + '</td>'
	            	    + '<td>' + '<input type="text" class="dataInputShort" />' + '</td>'
	            	    + '<td>'  
	            	    	+ '<select id="" class="selectModel">'
                                + '<option value="ORACLE">' + 'ORACLE' + '</option>'
                				+ '<option value="MYSQL">' + 'MYSQL' + '</option>'
                				+ '<option value="SQLSERVER2000">' + 'SQLSERVER2000' + '</option>'
                				+ '<option value="SQLSERVER2005">' + 'SQLSERVER2005' + '</option>'
                				+ '<option value="SQLSERVER2008">' + 'SQLSERVER2008' + '</option>'
                            + '</select>'
	            	    + '</td>'
	            	    + '<td>' + '<input type="text" class="dataInputShort" />' + '</td>'
	            	    + '<td>' + '<input type="text" class="dataInputShort" />' + '</td>'
	            	    + '<td>' + '<input type="text" class="dataInputShort" />' + '</td>'
	            	    + '<td class="operaCenter">'  
	            	    		+ '<a href="javascript:;" class="connection js_update" value="">' + '测试连接' + '</a>'
	            	    		+ '<a href="javascript:;" class="save" id="save" value="insert">' + '</a>'
	                            + '<a href="javascript:;" class="close">' + '</a>'
	                            + '<a href="javascript:;" class="write">' + '</a>'
	                           	+ '<a href="javascript:;" class="addTR" value="insert">' + '</a>'
	                            + '<a href="javascript:;" class="del" id="del" value="upanddel">' + '</a>'
	            	    + '</td>'
	            	+ '</tr>'
	            );
	            $(pid + ".write").hide();
	            $(pid + ".addTR").hide();
	            $(pid + ".del").hide();
	            $(pid + ".connection").hide();								// 20160905 增加
	            $(".addSingel").find(".connection").show();					// 20160905 增加
	            $(".addSingel").find(".save").css('display','inline-block');
	            $(".addSingel").find(".del").show();
	            $(".addSingel").siblings().removeClass("click");
	        });
	    });

		// 新增中保存
		$(pid).undelegate("a#save", "click").delegate("a#save", "click", function(){

			var strTR = $(this).closest("tr"); 					// 被点击修改按钮的祖级（当前行）

			successAll("保存","您确定要完成保存吗？");
			$(".btnBox").undelegate(".saveInfoBtn", "click").delegate(".saveInfoBtn", "click", function(event){

				closePop(".infoUp", ".iframe_box");
				loadingShow('white', 'loadingLong', '数据保存中... 请您稍等，谢谢！');

				// 获取当前行的所有控件值
	            var selectVal = strTR.find("option:selected");
	            var arraySelect = [];
	            for(var y=0;y<selectVal.length;y++){
	            	var select = selectVal.eq(y).val();
					arraySelect.push(select);
	            }

	            var lenInput = strTR.find("input").length;
	            var arrayClose = [];
	            for(var z=0;z<lenInput;z++){
	            	var input = strTR.find("input").eq(z).val();
	            	if(z == 1){
	            		arrayClose.push(arraySelect[0]);
	            		arrayClose.push(input);
	            	}else if(z == 3){
	            		arrayClose.push(arraySelect[1]);
	            		arrayClose.push(input);
	            	}else{
	            		arrayClose.push(input);
	            	}
	            }
	            // arrayClose[名称，来源，IP,端口，类型，实例名，用户，密码]
	            var sendSave = {
					"type":"insert",
		            "databaseName": arrayClose[0],
		            "databaseType": arrayClose[4],
		            "instanceName": arrayClose[5],
		            "ip": arrayClose[2],
		            "port": arrayClose[3],
					"userName": arrayClose[6],
		            "userPass": arrayClose[7],
		            "source": arrayClose[1]
		        }
		        $.ajax({
		        type:"POST",
		        url:portconfig+"db/dbbj",
				data:sendSave,
		        dataType : 'jsonp',
		        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			        success: function(json){
			        	var result = json.result;
			        	if(result == "succes"){
			        		closeLoading('white', 'loadingLong');
							passAll("保存","保存成功！");
		        			tableManagement(sendInit);
			        	}
			        }
			    });
			});
		});

		// 新增中删除
		$(pid).undelegate("a#del", "click").delegate("a#del", "click", function(){

			var strTR = $(this).closest("tr"); 						// 被点击修改按钮的祖级（当前行）
			delAll("删除","您确定要删除吗？");
			$(".btnBox").undelegate(".delInfoBtn", "click").delegate(".delInfoBtn", "click", function(event){
			// $(".delInfoBtn").off("click").on("click", function(event){
				event.stopPropagation();
				
				closePop(".infoUp", ".iframe_box");
				// loadingShow('white', 'loadingLong', '数据删除中... 请您稍等，谢谢！');

				$(pid + ".write").show();
	            $(pid + ".addTR").show();
	            $(pid + ".del").show();
	            $(pid + ".connection").show();						// 20160905 增加
				strTR.remove();
			});
		});

		// 尝试连接
		$(pid).undelegate("a.connection", "click").delegate("a.connection", "click", function(){

			var arrayConnection = [];
			var strTR = $(this).closest("tr"); 						// 被点击修改按钮的祖级（当前行）
			var connectionVal = strTR.find("td");
			var js_keyType = '';

			loadingShow('white', 'loadingLong', '数据保存中... 请您稍等，谢谢！');

			if($(this).hasClass("js_update")){
				js_keyType = "0";
				for(var i=0;i<connectionVal.length-1;i++){
					if(i==1){
						arrayConnection.push(connectionVal.eq(i).find("option:selected").text());
					}else if(i==4){
						arrayConnection.push(connectionVal.eq(i).find("option:selected").text());
					}else{
						arrayConnection.push(connectionVal.eq(i).find("input").val());
					}
				}
			}else{
				js_keyType = "1";
				for(var i=0;i<connectionVal.length-1;i++){
					arrayConnection.push(connectionVal.eq(i).text());
				}
			}
			var sendDataConnection = {
				"keyType":js_keyType,
				"databaseName": arrayConnection[0],
	            "databaseType": arrayConnection[4],
	            "instanceName": arrayConnection[5],
	            "ip": arrayConnection[2],
	            "port": arrayConnection[3],
				"userName": arrayConnection[6],
	            "userPass": arrayConnection[7],
	            "source": arrayConnection[1]
	        }

	        $.ajax({
	        type:"POST",
	        url:portconfig+"match/testConnection",
			data:sendDataConnection,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		        success: function(json){
	        		var result = json.result;
	        		if(result == true){
	        			closeLoading('white', 'loadingLong');
	        			passAll("连接","连接成功！");
	        		}else{
	        			closeLoading('white', 'loadingLong');
	        			errorsAll("连接失败");
	        		}
		        }
		    });
		});
    }

	// 查询功能
	js_query.off("click").on("click",function(){

		var keywordCon = $("#js_keywordCon").val();

		var sendSearch = {
			"databaseName":keywordCon,
			"currentpage":1,
			"type":"select"
		}
		searchVal = keywordCon;
		$.ajax({
        type:"POST",
        url:portconfig+"db/dbbj",
		data:sendSearch,
        dataType : 'jsonp',
        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
	        success: function(json){
        		tableManagement(sendSearch);
	        }
	    });
	});
	// 分页功能
	documentBox.undelegate("#js_page_home","click").delegate("#js_page_home", "click", function(){
		
		NowPage = 1;

		var sendPageNum = {
			"currentpage":NowPage,
			"databaseName":searchVal,
			"type":"select"
		}
		tableManagement(sendPageNum);
		
	});
	
	documentBox.undelegate("#js_page_pre","click").delegate("#js_page_pre", "click", function(){
		
		page_cur -= 1;
		
		if(page_cur <= 0){
			page_cur = 1;
		}else{
			NowPage = page_cur;
		};
		
		var sendPageNum = {
			"currentpage":NowPage,
			"databaseName":searchVal,
			"type":"select"
		}

		tableManagement(sendPageNum);
		 
	});
	
	documentBox.undelegate("#js_page_next","click").delegate("#js_page_next", "click", function(){
		
		page_cur = parseInt(page_cur) + 1;
		
		if(page_cur > page_total){
			page_cur = page_total
		}else{
			NowPage = page_cur;
	
		};
		
		var sendPageNum = {
			"currentpage":NowPage,
			"databaseName":searchVal,
			"type":"select"
		}

		tableManagement(sendPageNum);
		
	});
	
	documentBox.undelegate("#js_page_end","click").delegate("#js_page_end", "click", function(){
		
		NowPage = page_total;
		
		var sendPageNum = {
			"currentpage":NowPage,
			"databaseName":searchVal,
			"type":"select"
		}
		tableManagement(sendPageNum);
		
	});

	documentBox.undelegate("#js_page_jump","click").delegate("#js_page_jump", "click", function(){

		NowPage = parseInt($(this).siblings("input").val());
		
		var sendPageNum = {
			"currentpage":NowPage,
			"databaseName":searchVal,
			"type":"select"
		}
		tableManagement(sendPageNum);
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