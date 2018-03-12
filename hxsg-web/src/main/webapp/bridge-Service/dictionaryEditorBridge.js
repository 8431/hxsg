/* 监控平台后台系统，页面数据动态生成 */

$(function(){
	
	// var localhost= "192.168.121.89";
	//var documentBox = $(document);
	var popBg = $('.iframe_box')
	var popBgW = $('.iframe_boxW')
	var popLoading = $('.popLoading')

	var toTransmit = $('#toTransmit');							// 保存
	var toCancel = $('#toCancel');								// 取消
	var toModify = $('#toModify'); 								// 修改

	var firstPk = '';

	// 参数传值
	var sendDataProcess = {
		"pk": "",
		"type":""
	}

	var editor = '';
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

	dictionaryData();

	//字典管理数据交互
	function dictionaryData(){
		//alert("dictionaryData");

		var searchDictionary = $("#searchDictionary")				// 搜索按钮

		var js_dictionary_option = $("#js_dictionary_option");		// 字典管理 左边列表 选项
		var js_dictionary_List = $("#js_dictionary_List");			// 字典管理 左边列表
		
		dictionaryLeft({"type":"1"});

		$("#js_dictionary_List li").load("dictionaryMaintain.html?class=bgCurNum");

		// 搜索功能
		searchDictionary.off("click").on("click", function(){

			// 非常数字典与常数字典
			var js_dictionary_optionId = $("#js_dictionary_option").find("option:selected").val();

			// 搜索输入框内容
			var searchVal = searchDictionary.siblings("input[type='text']").val();

			// 类型
			var typeSearch = searchDictionary.siblings("input[type='text']").attr("alt"); 

			var searchData = {
				"typename":js_dictionary_optionId,
				"type":"mohu",
				"currentpage":"",
				"plyname":searchVal
			}
			dictionaryLeft(searchData);
		});

		// 非常数字典与常数字典的切换
		$("#js_dictionary_option").change(function(){
			
			if($(this).find("option:selected").val() == 1){
				judgeval = 0
				dictionaryLeft({"type":"1"});
			}else{
				judgeval = 0
				dictionaryLeft({"type":"1"});
			}
		});
		// 左侧数据注入功能
		function dictionaryLeft(datetype){
			// alert("dictionaryLeft");
			var addHtmlList = '';
		 	$.ajax({
	        type:"POST",
	        url:portconfig+"dictionary/dytype",
			data:datetype,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		        success: function(json){

					var dictionary = json.dictionary;
					//console.log(dictionary);
					// 字典管理 左边列表
					for(var i=0;i<dictionary.length;i++){
						
						addHtmlList += '<li class="clearfix" value="'+dictionary[i].type+'" type="'+ dictionary[i].costom2 +'" alt="'+dictionary[i].pk+'">'
									+ '<span class="fl">' + dictionary[i].name + '</span>'
									+ '<a href="javascript:;" class="delLeft txtRight" value="upanddel">' + '</a>'
									+ '<a href="javascript:;" class="addLeft txtRight" value="insert">' + '</a>'
									+ '</li>';
					}
					// console.log(addHtmlList);
					js_dictionary_List.html("");
					js_dictionary_List.append(addHtmlList);

					// 字典切换的判断
					var optionPk = js_dictionary_option.find("option:selected").val();
					var firstNamePk;
					if(optionPk == 1 && judgeval == 0){
						optionval = 1
						js_dictionary_option.find("option[value='1']").prop("selected",true);
						js_dictionary_List.find("li[value='1']").show();
						js_dictionary_List.find("li[value='0']").hide();
						$("#js_dictionary_List").find("li[value=1]").eq(0).addClass("bgCurNum");
						firstNamePk = $(".bgCurNum").attr("alt");							// 获取初始页面值
					}else if(optionPk == 0 && judgeval == 0){
						optionval = 0
						js_dictionary_option.find("option[value='0']").prop("selected",true);
						js_dictionary_List.find("li[value='1']").hide();
						js_dictionary_List.find("li[value='0']").show();

						var nonNum = $("#js_dictionary_List").find("li[value='1']").length;
						$("#js_dictionary_List").find("li").eq(nonNum).addClass("bgCurNum");
						firstNamePk = $(".bgCurNum").attr("alt");
					}else if(judgeval == 1 && optionval == 1){
						
						js_dictionary_option.find("option[value='1']").prop("selected",true);
						js_dictionary_List.find("li[value='1']").show();
						js_dictionary_List.find("li[value='0']").hide();
						js_dictionary_List.find("li[alt = '"+showval+"']").addClass("bgCurNum");
						js_dictionary_List.find("li[alt = '"+showval+"']").siblings().removeClass("bgCurNum");
						firstNamePk = $(".bgCurNum").attr("alt");							// 获取初始页面值

					}else if(judgeval == 1 && optionval == 0){
						
						js_dictionary_option.find("option[value='0']").prop("selected",true);
						js_dictionary_List.find("li[value='1']").hide();
						js_dictionary_List.find("li[value='0']").show();
						js_dictionary_List.find("li[alt = '"+showval+"']").addClass("bgCurNum");
						js_dictionary_List.find("li[alt = '"+showval+"']").siblings().removeClass("bgCurNum");
						firstNamePk = $(".bgCurNum").attr("alt");							// 获取初始页面值
					}

					$(".hide").hide();
					$(".hideBtn").hide();
					$(".show").show();
					$(".showBtn").show();

					$("a.addLeft").show();
		            $("a.delLeft").show();


					// 切换传值 （de=0 初始，de=1 切换，nc=1 非常数，nc=0 常数）
                    editor = "dictionaryMaintain.html?nc="+optionval+"&de=1&ee=" + $(".bgCurNum").attr("alt") ;
                    $("#cc").attr("href",editor);
					var sendFirstData = {
						"pk":firstNamePk,
						"type":"select"					
					}
					firstPk = firstNamePk;
					dictionaryRightstatic(sendFirstData);

					// 左边列表中的点击效果
					js_dictionary_List.find("li").each(function(i){
						
						var _this = $(this);
						$(this).off("click").on("click",function(event){
                     
							event.stopPropagation;

							$(".hide").hide();
							$(".hideBtn").hide();
							$(".show").show();
							$(".showBtn").show();

							$("a.addLeft").show();
				            $("a.delLeft").show();

							$(this).addClass("bgCurNum");
							$(this).siblings().removeClass("bgCurNum");
							$(".addLi").remove();													// 0808新增

							// 获取每次点击的页面值
							var namePk = $(".bgCurNum").attr("alt");
							
							var optionPk = js_dictionary_option.find("option:selected").val();
							if(optionPk == 1){
                                // 切换传值 （de=0 初始，de=1 切换，nc=1 非常数，nc=0 常数）
                                editor = "dictionaryMaintain.html?nc=1&de=1&ee=" + namePk;
                                $("#cc").attr("href",editor);
                            }else{
                                // 切换传值 （de=0 初始，de=1 切换，nc=1 非常数，nc=0 常数）
                                editor = "dictionaryMaintain.html?nc=0&de=1&ee=" + namePk;
                                $("#cc").attr("href",editor);
                            }

							sendDataProcess = {
								"pk": namePk,
								"type":"select"	
							}
							dictionaryRightstatic(sendDataProcess);
						})
					});

					// 修改
					toModify.off("click").on("click",function(){
						$(".hide").show();
						$(".hideBtn").show();
						$(".show").hide();
						$(".showBtn").hide();

						$("a.addLeft").hide();
				        $("a.delLeft").hide();

				        var namePk = $(".bgCurNum").attr("alt");
				   
						sendDataProcess = {
							"pk": namePk,
							"type":"select"	
						}
						dictionaryRight(sendDataProcess);
					});
				}
		 	});
		}

		// 控件注入
		function dictionaryRight(sendDate){
			$.ajax({
	        type:"POST",
	        url:portconfig+"dictionary/zdbj",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		        success: function(json){

		        	var code = json.result.code;
		        	var name = json.result.name;
		        	var comments = json.result.comments;
		        	var dictTableName = json.result.dictTableName;
		        	var dictTablePkName = json.result.dictTablePkName;
		        	var dictTableTimestamp = json.result.dictTableTimestamp;
		        	var dictDeleteName = json.result.dictDeleteName;
		        	
		        	$("#js_code").val(code);
		        	// $("#js_code_static").text(code);
		        	$("#js_name").val(name);
		        	// $("#js_name_static").text(name);
		        	$("#js_comments").val(comments);
		        	// $("#js_comments_static").text(comments);
		        	$("#js_dictTableName").val(dictTableName);
		        	// $("#js_dictTableName_static").text(dictTableName);
		        	$("#js_dictTablePkName").val(dictTablePkName);
		        	// $("#js_dictTablePkName_static").text(dictTablePkName);
		        	$("#js_dictTableTimestamp").val(dictTableTimestamp);
		        	// $("#js_dictTableTimestamp_static").text(dictTableTimestamp);
		        	$("#js_dictDeleteName").val(dictDeleteName);
		        }
		    });
		}

		// 静态注入
		function dictionaryRightstatic(sendDate){
			$.ajax({
	        type:"POST",
	        url:portconfig+"dictionary/zdbj",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		        success: function(json){

		        	var code = json.result.code;
		        	var name = json.result.name;
		        	var comments = json.result.comments;
		        	var dictTableName = json.result.dictTableName;
		        	var dictTablePkName = json.result.dictTablePkName;
		        	var dictTableTimestamp = json.result.dictTableTimestamp;
		        	var dictDeleteName = json.result.dictDeleteName;
		        	var custom1 = json.result.custom1;
		        	
		        	$("#js_code_static").text(code);
		        	$("#js_name_static").text(name);
		        	$("#js_comments_static").text(comments);
		        	$("#js_dictTableName_static").text(dictTableName);
		        	$("#js_dictTablePkName_static").text(dictTablePkName);
		        	$("#js_dictTableTimestamp_static").text(dictTableTimestamp);
		        	$("#js_dictDeleteName_static").text(dictDeleteName);
		        	$("#js_orderBy_static").text(custom1);

		        }
		    });
	
		}

		// 保存
		toTransmit.off("click").on("click",function(){

			successAll("保存","您确定要完成保存吗？");

			var nameVal = $("#js_dictionary_option").find("option:selected").val();

            // 高亮Pk
            var namePk = $(".bgCurNum").attr("alt");

            // 字典荷载类型
            var js_code = $("#js_code").val();
        	
        	// 字典名称
        	var js_name = $("#js_name").val();
        	
        	// 描述
        	var js_comments = $("#js_comments").val();
        	
        	// 打散表名
        	var js_dictTableName = '';
        	if(nameVal == 0){
        		js_dictTableName = $("#js_dictTableName").val("ATS_DICT.DICT_CONSTANT_MAP");
        	}else{
        		js_dictTableName = $("#js_dictTableName").val();
        	}
        	
        	// 打散主键
        	var js_dictTablePkName = $("#js_dictTablePkName").val();
        	
        	// 打散转换TimeStamp段
        	var js_dictTableTimestamp = $("#js_dictTableTimestamp").val();

        	// 打散废弃字段
        	var js_dictDeleteName = $("#js_dictDeleteName").val();

        	//字段排序
            var js_orderBy = $("#js_orderBy").val();

        	if(js_code == ""){
				errorsAll('字典荷载类型不能为空');
			}else if(js_name == ""){
				errorsAll('字典名称不能为空');
			}else if(js_comments == ""){
				errorsAll('描述不能为空');
			}else if(js_dictTableName == ""){
				errorsAll('打散表名不能为空');
			}else if(js_dictTablePkName == ""){
				errorsAll('打散主键不能为空');
			}else if(js_dictTableTimestamp == ""){
				errorsAll('打散转换TimeStamp段不能为空');
			}else if(js_dictDeleteName == ""){
				errorsAll('打散废弃字段不能为空');
			}else if(js_orderBy == null){
				$("#js_orderBy").val("");
			}else {
				// $('.btnBox input[name="Confirm"]').removeClass("delInfoBtn");
				$(".btnBox").undelegate(".saveInfoBtn", "click").delegate(".saveInfoBtn", "click", function(event){
					event.stopPropagation();

					$(".hide").hide();
					$(".hideBtn").hide();
					$(".show").show();
					$(".showBtn").show();

					$("a.addLeft").show();
		            $("a.delLeft").show();
		            
		            $(".alloCon").find("input").val("");
		            $("#js_static").hide();

					closePop(".infoUp", ".iframe_box");
					loadingShow('white', 'loadingLong', '数据保存中... 请您稍等，谢谢！');

					// 判断是新增更新 还是修改更新
					var saveli = $(".addLi").find("input").val();
					if(saveli == undefined){
			            var sendDate = '';
				        if(nameVal == 0){
				        	sendDate = {
								"code": js_code,
						        "comments": js_comments, 
						        "dictDeleteName": js_dictDeleteName,
						        "dictTableName": "ATS_DICT.DICT_CONSTANT_MAP",
						        "dictTablePkName": js_dictTablePkName,
						        "dictTableTimestamp": js_dictTableTimestamp,
						        "custom1": js_orderBy,
						        "name": js_name,
						        "pk": namePk,
						        "type": "update"
				            }
				        }else{
				        	sendDate = {
								"code": js_code,
						        "comments": js_comments, 
						        "dictDeleteName": js_dictDeleteName,
						        "dictTableName": js_dictTableName,
						        "dictTablePkName": js_dictTablePkName,
						        "dictTableTimestamp": js_dictTableTimestamp,
						        "custom1": js_orderBy,
						        "name": js_name,
						        "pk": namePk,
						        "type": "update"
				            }
				        }
				        //console.log(sendDate)
						$.ajax({
				        type:"POST",
				        url:portconfig+"dictionary/zdbj",
						data:sendDate,
				        dataType : 'jsonp',
				        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
					        success: function(json){
					        	var result = json.result;
								if(json.result=="succes"){
						        	setTimeout(function(){
										closeLoading('white', 'loadingLong');
										passAll("保存","保存成功！");
									},500);
						        	dictionaryRightstatic(sendDataProcess);
					        	}else{
									setTimeout(function(){
										closeLoading('white', 'loadingLong');
										errorsAll("保存失败！");
									},500);
								}
					        }
					    });
					}else{
						var sendDate = '';
				        if(nameVal == 0){
				        	sendDate = {
								"isdeleted":"0",
				        		"code": js_code,
						        "comments": js_comments, 
						        "dictDeleteName": js_dictDeleteName,
						        "dictTableName": "ATS_DICT.DICT_CONSTANT_MAP",
						        "dictTablePkName": js_dictTablePkName,
						        "dictTableTimestamp": js_dictTableTimestamp,
						        "custom1": js_orderBy,
						        "name": js_name,
						        "type": "insert"
				            }
				        }else{
				        	sendDate = {
								"isdeleted":"0",
				        		"code": js_code,
						        "comments": js_comments, 
						        "dictDeleteName": js_dictDeleteName,
						        "dictTableName": js_dictTableName,
						        "dictTablePkName": js_dictTablePkName,
						        "dictTableTimestamp": js_dictTableTimestamp,
						        "custom1": js_orderBy,
						        "name": js_name,
						        "type": "insert"
				            }
				        }
						$.ajax({
				        type:"POST",
				        url:portconfig+"dictionary/zdbj",
						data:sendDate,
				        dataType : 'jsonp',
				        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
					        success: function(json){
					        	var result = json.result;
								if(json.result=="succes"){
						        	setTimeout(function(){
										closeLoading('white', 'loadingLong');
										passAll("保存","保存成功！");
									},500);

						        	dictionaryData();
						        }else{
									setTimeout(function(){
										closeLoading('white', 'loadingLong');
										errorsAll("保存失败！");
									},500);
								}
					        }
					    });
					}
			    });
			};
		});
		// 删除
		$("#js_dictionary_List").undelegate("a.delLeft", "click").delegate("a.delLeft", "click", function(){
			delAll("删除","您确定要删除吗？");
			$(".btnBox").undelegate(".delInfoBtn", "click").delegate(".delInfoBtn", "click", function(event){
			// $(".delInfoBtn").off("click").on("click", function(event){
				event.stopPropagation();
				
				closePop(".infoUp", ".iframe_box");
				loadingShow('white', 'loadingLong', '数据删除中... 请您稍等，谢谢！');

				$('.btnBox input[name="Confirm"]').removeClass("saveInfoBtn");

				var namePk = $(".bgCurNum").attr("alt");

				var sendDateDel = {
					"isdeleted":"1",
			        "pk": namePk,
			        "type": "update"
				}
				$.ajax({
			        type:"POST",
			        url:portconfig+"dictionary/zdbj",
					data:sendDateDel,
			        dataType : 'jsonp',
			        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
					success: function(json){
						var result = json.result;	
						if(json.result=="succes"){
							dictionaryLeft({"type":"1"});
							setTimeout(function(){
								closeLoading('white', 'loadingLong');
								passAll("删除","删除成功！");
							},500);
						}else{
							setTimeout(function(){
								closeLoading('white', 'loadingLong');
								errorsAll("删除失败！");
							},500);
						}
					}
				});
			});
		});	
	}
		
	// 增加
	$("#js_dictionary_List").delegate("a.addLeft", "click", function(event){ 
            
        event.stopPropagation();
   	
   		var strLi = $(this).closest("li");
        $(".hide").show();
		$(".hideBtn").show();
		$(".show").hide();
		$(".showBtn").hide();

		$("a.addLeft").hide();
        $("a.delLeft").hide();

       	// 常数字典中有固定值
        var nameVal = $("#js_dictionary_option").find("option:selected").val();
        if(nameVal == 0){
        	$("#js_static").show();
        	$("#js_dictTableName").hide();
        	$("#js_dictTableName_static").hide();
        }else{
        	$("#js_dictTableName").show();
        	$("#js_dictTableName_static").hide();
        }

        // 添加输入框
        strLi.after('<li class="addLi">' + '<input type="text" id="addInput">' + '</li>');
        $(".addLi").addClass("bgCurNum");
		$(".addLi").siblings().removeClass("bgCurNum");

		// 字典名称同步
		$(".addLi").undelegate("keyup").delegate("#addInput", "keyup", function(){
			var addInput = $("#addInput").val();
			$("#js_name").val(addInput);
		});

		$(".alloCon").undelegate("keyup").delegate("#js_name", "keyup", function(){
			var addInput = $("#js_name").val();
			$("#addInput").val(addInput);
		});
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
		$(".popClose").hide();
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
		Confirm.removeClass();
		$(".popClose").hide();
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
		$(".popClose").show();
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
		$(".popClose").show();
	}	
});