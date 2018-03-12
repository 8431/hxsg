/* 监控平台后台系统，页面数据动态生成 */

$(function(){
	
	// var localhost= "192.168.121.146";
	//var documentBox = $(document);
	var popBg = $('.iframe_box');
	var popBgW = $('.iframe_boxW');
	var popLoading = $('.popLoading');

	var pushAllos = $("#pushAllos");					// 字典管理

	var toTransmit = $('#toTransmit');					// 保存
	var toCancel = $('#toCancel');						// 取消
	var toModify = $('#toModify'); 						// 修改

	var hide = $('.hide');								// 隐藏状态
	var show = $('.show');								// 显示状态
	var hideOperation = $('.hideOperation');   			// 隐藏操作

	var pushTable = $("#pushTable");					// 推送配置
	var js_pushTable = $("#js_pushTable");				// 推送配置添加
	var pushTitle = $("#pushTitle");					// 推送配置标题
	var js_pushTb_static = $("#js_pushTb_static");		// 推送规则表格	

	var dictionnarycode=null;
	var dictionnaryPk=null;

	/*20160907 新增*/
	var js_sourceVal;
	var js_systemNameVal;
	var js_systemVal;

	// 退出系统
	$(".btnBox").delegate(".exitBtn", "click", function(event){
		event.stopPropagation;
		var exitSystem = "true";
		closePop(".infoUp", ".iframe_box");
	});

	// 取消刷新页面
	$(document).delegate("#toCancel", "click", function(event){
		event.stopPropagation;
		window.location.replace(window.location.href);
	});
	// 取消刷新页面
	$(document).delegate(".cancel", "click", function(event){
		event.stopPropagation;
		window.location.replace(window.location.href);
	});

	dictionaryPush()
	// 数据库管理页面 数据交互
	pushAllos.on("click", function(){
		dictionaryPush();
	});
	function dictionaryPush(){
		var searchDictionary = $("#searchDictionary")				// 搜索按钮
		var js_dictionary_option = $("#js_dictionary_option");		// 字典管理 左边列表 选项
		var js_dictionary_List = $("#js_dictionary_List");			// 字典管理 左边列表

		// 推送规则配置单行模板
		var addHtmlPushTr = '';
		var addHtmlPushLast = '<tr >' 
								+ '<td colspan="2">' + '<span class="addIcon">'+'</span>' + '</td>'  
								+ '</tr>'

		// 推送规则配置单行模板
		var addHtmlRuleTr = '';
		addHtmlRuleTr  += '<tr>' 
						+ '<td>' + '<input type="text" class="longInput" value="">' +'</td>'
						+ '<td>' 
						// + '<span class="rules">'
								 + '<select>'
								 + '<option>' +'大于'+ '</option>'
								 + '<option>' +'大于等于'+ '</option>'
								 + '<option>' +'等于'+ '</option>'
								 + '<option>' +'小于'+ '</option>'
								 + '<option>' +'小于等于'+ '</option>'
								 + '<option>' +'包含'+ '</option>'
								 + '<option>' +'不等于'+ '</option>'
								 + '</select>'
								 // + '<p class="rulesBg">'+'</p>'
								 +'</span>' 
						+'</td>'
						+ '<td>' + '<input type="text" class="shortInput" value="">' +'</td>'
						+ '<td class="operaCenter">' + '<a href="javascript:;" class="addTR" value="insert">'+ '</a>' 
						    	 + '<a href="javascript:;" class="del" value="upanddel">'+ '</a>'
						+'</td>'
						+ '</tr>'

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
				
				dictionaryLeft({"type":"1"});
			}else{
				dictionaryLeft({"type":"1"});
			}
		});
		dictionaryLeft({"type":"1"}); 
		// 左侧数据注入功能
		function dictionaryLeft(datetype){
			var addHtmlList = '';

			$.ajax({
	        type:"POST",
	        url:portconfig+"dictionary/dytype",
			data:datetype,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		        success: function(json){

					var dictionary = json.dictionary;

					// 字典管理 左边列表
					for(var i=0;i<dictionary.length;i++){
						addHtmlList += '<li value="'+dictionary[i].type+'" type="'+ dictionary[i].costom2 +'" alt="'+dictionary[i].code+'">' + dictionary[i].name + '</li>';
					}
					js_dictionary_List.html("");
					js_dictionary_List.append(addHtmlList);

					// 字典切换的判断
					var optionPk = js_dictionary_option.find("option:selected").val();
					
					if(optionPk == 1){
						js_dictionary_List.find("li[value='1']").show();
						js_dictionary_List.find("li[value='0']").hide();
						$("#js_dictionary_List").find("li[value='1']").eq(0).addClass("bgCurNum");
					}else{
						js_dictionary_List.find("li[value='1']").hide();
						js_dictionary_List.find("li[value='0']").show();
						$("#js_dictionary_List").find("li[value='0']").eq(0).addClass("bgCurNum");
					}

					var firstName = $(".bgCurNum").attr("alt");

					var firstNameC = $(".bgCurNum").text();
					
					var firstNameID = $(".bgCurNum").attr("type");

					var sendFirstData = {
						"name": firstName,
						"query":"code",
						"type":"select"					
					}
					dictionnarycode=null;
					dictionnarycode=firstName;
					pushConfig(sendFirstData) 
					// 左边列表中的点击效果
					js_dictionary_List.find("li").each(function(i){
						
						var _this = $(this);
						$(this).off("click").on("click",function(event){

							event.stopPropagation;

							$(this).addClass("bgCurNum");
							$(this).siblings().removeClass("bgCurNum");

							toTransmit.hide();
							toCancel.hide();
							toModify.show();
							hideOperation.hide();

							hide.hide();
							show.show();

							// 左侧列表点击时将赋值清空 20160921
							js_sourceVal = '';
							js_systemNameVal = '';
							js_systemVal = '';

							// 字典的名字
							var namePK = $(".bgCurNum").attr("alt");

							dictionnarycode=null;
							dictionnarycode=namePK;
							var sendDataProcess = {
								"name": dictionnarycode,
								"query":"code",
								"type":"select"	
							}
							pushConfig(sendDataProcess)
						});
					});
				}
			});
		}
		
		function pushConfig(sendDate){
			var addHtmlPush = '';
			var addHtmlPushBtn = '';

			$.ajax({
	        type:"POST",
	        url:portconfig+"push/PushConfiguration",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		        success: function(json){

				var codenum = json.codenum;
				// var domainVal = json.rulemap[0].EVENT_REC_DOMAIN_UID;
				// 推送配置选项表格
				if(codenum.length == 0){
					pushTable.html("");
					js_pushTable.html("");
					js_pushTable.append(addHtmlPushLast);
					$(".alloRight").hide();
					$(".btnBox").hide();
				}else{
					$(".alloRight").show();
					$(".btnBox").show();
					for(var i=0;i<codenum.length;i++){
						addHtmlPush += '<tr type="'+codenum[i]+'">' 
									+ '<td>' + '推送配置' + codenum[i] + '</td>'
									+ '<td>' + '<span class="delIcon delMean">'+'</span>' + '</td>'  
									+ '</tr>'
					}
					pushTable.html("");
					pushTable.append(addHtmlPush);
					js_pushTable.html("");
					js_pushTable.append(addHtmlPushLast);
				}

				// 推送配置标题
				pushTable.find("tr").eq(0).addClass("bgCur");
				var titleFirstCur = $(".bgCur").text();
				pushTitle.html("");
				pushTitle.append(titleFirstCur);

				toTransmit.hide();
				toCancel.hide();
				toModify.show();
				hideOperation.hide();
				$("#toValuation").hide();

				hide.hide();
				show.show();

				var firstCode = $(".bgCur").attr("type");

				dictionnaryPk = null;
				dictionnaryPk = firstCode;

				sendFirstPush = {
					"name": dictionnaryPk,
					"type":"select",
					"query":"pk"
				}
				pushCon(sendFirstPush);

				// 推送配置选项表格点击效果 
				pushTable.find("tr").each(function(i){
					
					var _this = $(this);
					$(this).off("click").on("click",function(event){

						event.stopPropagation;

						if($(this).find('td').length == 2){
							
							toTransmit.hide();
							toCancel.hide();
							toModify.show();
							hideOperation.hide();
							$("#toValuation").hide();

							hide.hide();
							show.show();

							$("#delBtnTr").remove();
							js_pushTable.html("");
							js_pushTable.append(addHtmlPushLast);

							// 推送列表的删除按钮 显示
							$(".delMean").show();

							$(this).addClass("bgCur");
							$(this).siblings().removeClass("bgCur");

							// 改变右侧标题
							var titleCur = $(this).text();
							pushTitle.html("");
							pushTitle.append(titleCur);

							// 每行推送配置的PK
							var nameTrCode = $(this).attr("type");
							
							dictionnaryPk = null;
							dictionnaryPk = nameTrCode;
							var sendDataPush = {
								"name": dictionnaryPk,
								"type":"select",
								"query":"pk"
							}
							pushCon(sendDataPush);
						}
					});
				});
			}
			});
		}

		function pushCon(sendDate){

			$.ajax({
	        type:"POST",
	        url:portconfig+"push/PushConfiguration",
			data:sendDate,
	        dataType : 'jsonp',
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
		        success: function(json){
		        	if(json.rulemap != ""){
				        var domainVal = json.rulemap[0].EVENT_REC_DOMAIN_UID;
						var nameVal = json.rulemap[0].EVENT_REC_TYPE;
						var addressVal = json.rulemap[0].EVENT_REC_SOAP_NOTICE_URL;
						var sourceVal = json.rulemap[0].EVENT_SEND_DOMAIN_UID;
						var systemNameVal = json.rulemap[0].EVENT_SEND_NAME;
						var systemVal = json.rulemap[0].EVENT_SEND_TYPE;

						var js_domain_static = $("#js_domain_static");
						var js_name_static = $("#js_name_static");
						var js_address_static = $("#js_address_static");
						var js_source_static = $("#js_source_static");
						var js_systemName_static = $("#js_systemName_static");
						var js_system_static = $("#js_system_static");

						// 赋值 20160907
						js_sourceVal = sourceVal;
						js_systemNameVal = systemNameVal;
						js_systemVal = systemVal;

						// 推送配置内容 静态
						js_domain_static.text(domainVal);
						js_name_static.text(nameVal);
						js_address_static.text(addressVal);
						js_source_static.text(sourceVal);
						js_systemName_static.text(systemNameVal);
						js_system_static.text(systemVal);

						var js_domain = $("#js_domain");
						var js_name = $("#js_name");
						var js_address = $("#js_address");
						var js_source = $("#js_source");
						var js_systemName = $("#js_systemName");
						var js_system = $("#js_system");

						// 推送配置内容 控件
						js_domain.val(domainVal);
						js_name.val(nameVal);
						js_address.val(addressVal);
						js_source.val(sourceVal);
						js_systemName.val(systemNameVal);
						js_system.val(systemVal);

						var rulesVal = json.routerule;
						if( rulesVal== null){
							js_pushTb_static.html("");
						}else{
							var addHtmlPushCon = '';
							for(var j=0;j<rulesVal.length;j++){

								if(rulesVal[j].OPERATOR == ">"){
									rulesVal[j].OPERATOR = "大于";
								}else if(rulesVal[j].OPERATOR == "="){
									rulesVal[j].OPERATOR = "等于";
								}else if(rulesVal[j].OPERATOR == "<"){
									rulesVal[j].OPERATOR = "小于";
								}else if(rulesVal[j].OPERATOR == "contain"){
									rulesVal[j].OPERATOR = "包含";
								}else if(rulesVal[j].OPERATOR == ">="){
									rulesVal[j].OPERATOR = "大于等于";
								}else if(rulesVal[j].OPERATOR == "<="){
									rulesVal[j].OPERATOR = "小于等于";
								}else if(rulesVal[j].OPERATOR == "!="){
									rulesVal[j].OPERATOR = "不等于";
								};

								addHtmlPushCon  += '<tr value="'+rulesVal[j].ROUTE_RULE_PK+'">' 
													+ '<td>' + '<span class="show">' + rulesVal[j].PARA_NAME + '</span>' + '<input type="text" class="longInput hide" value="'+rulesVal[j].PARA_NAME+'">' +'</td>'
													+ '<td class="operaPk">' + rulesVal[j].OPERATOR +'</td>'
													+ '<td class="operator hide">' + rulesVal[j].OPERATOR +'</td>'
													+ '<td>' + '<span class="show">' + rulesVal[j].VALUE + '</span>' + '<input type="text" class="shortInput hide" value="'+rulesVal[j].VALUE+'">' +'</td>'
													+ '<td class="operaCenter hideOperation">' + '<a href="javascript:;" class="addTR" value="insert">'+ '</a>' 
													    	 + '<a href="javascript:;" class="del" value="upanddel">'+ '</a>'
													+'</td>'
													+ '</tr>'
							}
															
							js_pushTb_static.html("");
							js_pushTb_static.append(addHtmlPushCon);
							

							js_pushTb_static.find(".operator").each(function(i) {
								
								var operatorArr = [];
								var text = $.trim($(this).text());
								operatorArr.push(text);
								$(this).text("");
								$(this).append('<select>'
													 + '<option value="greater" type="大于">' +'大于'+ '</option>'
													 + '<option value="Greater and equal" type="大于等于">' +'大于等于'+ '</option>'
													 + '<option value="equal" type="等于">' +'等于'+ '</option>'
													 + '<option value="less" type="小于">' +'小于'+ '</option>'
													 + '<option value="less and equal" type="小于等于">' +'小于等于'+ '</option>'
													 + '<option value="contain" type="包含">' +'包含'+ '</option>'
													 + '<option value="!= not equals">' +'不等于'+ '</option>'
													 + '</select>');
								$(this).find('select option').each(function(){
									if($(this).text() == text){
										$(this).prop('selected',true);
									};
								});
							});
						};
						tableColor();	
					};
				}
			});
		}
		// 修改
		toModify.off("click").on("click",function(){
			
			toTransmit.show();
			toCancel.show();
			toModify.hide();
			$(".hideOperation").show();

			$(".hide").show();
			$(".operaPk").hide();
			$(".show").hide();

			if(js_pushTb_static.find("tr").length == 0){
				js_pushTb_static.append(addHtmlRuleTr);
			}
		});


		// 推送配置中的添加
		js_pushTable.undelegate(".addIcon","click").delegate(".addIcon","click",function(){

			toTransmit.show();
			toCancel.show();
			toModify.hide();
			hideOperation.show();
			$("#toValuation").show();

			hide.show();
			show.hide();

			$(".alloRight").show();
			$(".btnBox").show();

			$(this).closest('tr').remove();

			// 推送列表的删除按钮 隐藏
			$(".delMean").hide();
			
			var pushNum = pushTable.find("tr").length;
			addHtmlPushTr = '<tr id="delBtnTr">' 
					+ '<td>' + '推送配置新增' + '</td>'
					+ '<td>' + '<span class="delIcon" id="delBtn">'+'</span>' + '</td>'
					+ '</tr>'
			pushTable.append(addHtmlPushTr);
			pushTable.find('tr').eq(pushNum).addClass('bgCur');
			pushTable.find('tr').eq(pushNum).siblings().removeClass('bgCur');

			var titleFirstCur = $(".bgCur").text();
			pushTitle.html("");
			pushTitle.append(titleFirstCur);

			// 创建控件 
			$(".alloEditor").find("input").val("");
			js_pushTb_static.html("");
			js_pushTb_static.append(addHtmlRuleTr);
		});

		// 赋值 20160907
		$("#toValuation").on("click",function(){
			$("#js_source").val(js_sourceVal);
			$("#js_systemName").val(js_systemNameVal);
			$("#js_system").val(js_systemVal);
		});
		
		// 推送配置中新增的删除
		pushTable.undelegate("#delBtnTr","click").delegate("#delBtnTr","click",function(){

			delAll("删除","您确定要删除吗？");
			$(".delInfoBtn").off("click").on("click", function(event){

				closePop(".infoUp", ".iframe_box");

				var delNum = pushTable.find("tr").length;
				js_pushTable.html("");
				js_pushTable.append(addHtmlPushLast);
				if(delNum == 1){
					$("#delBtnTr").remove();
					$(".alloRight").hide();
					$(".btnBox").hide();
				}else{
					pushTable.find("tr").eq(0).addClass('bgCur');
					pushTable.find("tr").eq(0).siblings().removeClass('bgCur');

					var dictionnaryPk = pushTable.find("tr").eq(0).attr("type");

					toTransmit.hide();
					toCancel.hide();
					toModify.show();
					hideOperation.hide();

					hide.hide();
					show.show();

					$("#toValuation").hide();

					$("#delBtnTr").remove();
					$(".delMean").show();
					js_pushTable.html("");
					js_pushTable.append(addHtmlPushLast);

					var sendDataPush = {
						"name": dictionnaryPk,
						"type":"select",
						"query":"pk"
					}
					pushCon(sendDataPush);
					setTimeout(function(){
						passAll("删除","删除成功！");
					},500);
				};
			});
		});

		// 推送配置中的删除
		pushTable.undelegate(".delIcon","click").delegate(".delIcon","click",function(){

			var strTr = $(this).closest('tr');
			delAll("删除","您确定要删除吗？");
			$(".delInfoBtn").off("click").on("click", function(event){

				closePop(".infoUp", ".iframe_box");
				loadingShow('white', 'loadingLong', '数据删除中... 请您稍等，谢谢！');

				var delNum = pushTable.find("tr").length;
				js_pushTable.html("");
				js_pushTable.append(addHtmlPushLast);
				if(delNum == 1){
					pushTable.find("tr").eq(0).addClass('bgCur');
					pushTable.find("tr").eq(0).siblings().removeClass('bgCur');
					var code = strTr.attr("type");

					var sendDateDel = {
						"pk": code,
						"type":"delroute"
					}

					//console.log(sendDateDel);
					$.ajax({
				        type:"POST",
				        url:portconfig+"push/PushConfiguration",
						data:sendDateDel,
				        dataType : 'jsonp',
				        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
						success: function(json){
							var result = json.result;
							//console.log(result);
							if(result == "succes"){
								var axa={
									"type":"select",
									"name":dictionnarycode,
									"query":"code"
								}
								pushConfig(axa);
								setTimeout(function(){
									closeLoading('white', 'loadingLong');
									passAll("删除","删除成功！");
								},500);
							}else{
								setTimeout(function(){
									closeLoading('white', 'loadingLong');
									errorsAll(result);
								},500);
							}
						}
					});
				}else{
					pushTable.find("tr").eq(0).addClass('bgCur');
					pushTable.find("tr").eq(0).siblings().removeClass('bgCur');
					var code = strTr.attr("type");

					var sendDateDel = {
						"pk": code,
						"type":"delroute"
					}
					$.ajax({
				        type:"POST",
				        url:portconfig+"push/PushConfiguration",
						data:sendDateDel,
				        dataType : 'jsonp',
				        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
						success: function(json){
							var result = json.result;
							if(result == "succes"){
								var axa={
									"type":"select",
									"name":dictionnarycode,
									"query":"code"
								}
								pushConfig(axa);
								setTimeout(function(){
									closeLoading('white', 'loadingLong');
									passAll("删除","删除成功！");
								},500);
							}else{
								setTimeout(function(){
									closeLoading('white', 'loadingLong');
									errorsAll(result);
								},500);
							}
						}
					});
					
				}
				
			});
			
		});

		// 推送规则配置中的添加
		js_pushTb_static.undelegate("a.addTR", "click").delegate("a.addTR", "click", function(){
			js_pushTb_static.append(addHtmlRuleTr);
			tableColor();
		});

		// 推送规则配置中的删除
		js_pushTb_static.undelegate("a.del", "click").delegate("a.del", "click", function(){
	
			var strTr = $(this).closest('tr');
			var rule_pk = strTr.attr("value");
			var delNum = js_pushTb_static.find("tr").length;
			delAll("删除","您确定要删除吗？");
			$(".delInfoBtn").off("click").on("click", function(event){

				$('.btnBox input[name="Confirm"]').removeClass("saveInfoBtn");
				if(rule_pk == undefined){
			
					if(delNum-1 == 0){
						strTr.find("input").val("");
					}else{
						strTr.remove();
						//tableColor();
					};
					closePop(".infoUp", ".iframe_box");
				}else{
					closePop(".infoUp", ".iframe_box");
					loadingShow('white', 'loadingLong', '数据删除中... 请您稍等，谢谢！');

					toTransmit.hide();
					toCancel.hide();
					toModify.show();
					hideOperation.hide();

					hide.hide();
					show.show();

					var sendDateDel = {
						"ROUTE_RULE_PK":rule_pk,
						"type":"delrule"
					}
					//console.log(sendDateDel);
					$.ajax({
				        type:"POST",
				        url:portconfig+"push/PushConfiguration",
						data:sendDateDel,
				        dataType : 'jsonp',
				        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
						success: function(json){
							var result = json.result;	
							if(json.result=="succes"){
								var dictionnaryPk = pushTable.find(".bgCur").attr("type");
								var sendDataPush = {
									"name": dictionnaryPk,
									"type":"select",
									"query":"pk"
								}
								pushCon(sendDataPush);
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
				}
			});
		});
		// // 推送规则配置中select 的效果
		// js_pushTb_static.undelegate(".rules","click").delegate(".rules","click",function(){
		// 	$(".rulesBg").hide();
		// });

		// 保存
		toTransmit.off("click").on("click",function(){
	
			var js_domain = $("#js_domain").val();
			var js_name = $("#js_name").val();
			var js_address = $("#js_address").val();
			var js_source = $("#js_source").val();
			var js_systemName = $("#js_systemName").val();
			var js_system = $("#js_system").val();

			if(js_domain == ""){
				errorsAll('接收者域不能为空');
			}else if(js_name == ""){
				errorsAll('接收者名称不能为空');
			}else if(js_address == ""){
				errorsAll('接收者通知地址不能为空');
			}else if(js_source == ""){
				errorsAll('字典来源域不能为空');
			}else if(js_systemName == ""){
				errorsAll('字典域系统名称不能为空');
			}else if(js_system == ""){
				errorsAll('字典域系统ID不能为空');
			}else {
				saveFun();
			};
		});
		function saveFun(){
			successAll("保存","您确定要完成保存吗？");

			$(".saveInfoBtn").off("click").on("click", function(event){

				closePop(".infoUp", ".iframe_box");
				loadingShow('white', 'loadingLong', '数据保存中... 请您稍等，谢谢！');

				$('.btnBox input[name="Confirm"]').removeClass("delInfoBtn");

				var codePk = $(".bgCurNum").attr("alt");                     		// 字典条目中的code
				var ruleValTrPK = $(".bgCur").attr("type");							// 推荐配置中的pk 

				if(ruleValTrPK == undefined){

					// 推送配置的key
					var pushKeyArr = ["PAY_LOAD_TYPE","EVENT_REC_DOMAIN_UID","EVENT_REC_TYPE","EVENT_REC_SOAP_NOTICE_URL","EVENT_SEND_DOMAIN_UID","EVENT_SEND_NAME","EVENT_SEND_TYPE"];
					
					// 推送配置的value
					var js_domain = $("#js_domain").val();
					var js_name = $("#js_name").val();
					var js_address = $("#js_address").val();
					var js_source = $("#js_source").val();
					var js_systemName = $("#js_systemName").val();
					var js_system = $("#js_system").val();
					var pushValueArr = [codePk,js_domain,js_name,js_address,js_source,js_systemName,js_system];
					//console.log(pushValueArr);
					
					// 推送规则配置的key
					var ruleKeyArr = ["PARA_NAME","OPERATOR","VALUE"];

					// 推送规则配置的value
					var ruleValueArr = [];
					
					var ruleValTrOne = '';
					var ruleValTrTwo = '';
					var ruleValTrThree = '';

					var ruleTrNum = $("#js_pushTb_static").find("tr").length;

					var ruleValueArr = [];
					for(var m=0;m<ruleTrNum;m++){

						var ruleValueTrArr = [];
						var strTr = $("#js_pushTb_static").find("tr").eq(m);

						ruleValTrOne = strTr.find("input").eq(0).val();
						ruleValTrTwo = strTr.find("option:selected").text();
						if(ruleValTrTwo == "大于"){
							ruleValTrTwo = ">";
						}else if(ruleValTrTwo == "等于"){
							ruleValTrTwo = "=";
						}else if(ruleValTrTwo == "小于"){
							ruleValTrTwo = "<";
						}else if(ruleValTrTwo == "包含"){
							ruleValTrTwo = "contain";
						}else if(ruleValTrTwo == "大于等于"){
							ruleValTrTwo = ">=";
						}else if(ruleValTrTwo == "小于等于"){
							ruleValTrTwo = "<=";
						}else if(ruleValTrTwo == "不等于"){
							ruleValTrTwo = "!=";
						};
						ruleValTrThree = strTr.find("input").eq(1).val();

						// 一维数组 元素逐个添加
						ruleValueTrArr = [ruleValTrOne,ruleValTrTwo,ruleValTrThree];
						
						// 二维数组 元素逐个添加
						ruleValueArr.push(ruleValueTrArr);	
					}
					var sendDateSave = {
						"routemap":pushKeyArr,
						"routerule":ruleKeyArr,
						"routemapvalue":pushValueArr,
						"routerulevalue":ruleValueArr,
						"type":"insert"
					}
					// console.log(sendDateSave);
					$.ajax({
			        type:"POST",
			        url:portconfig+"push/PushConfiguration",
					data:sendDateSave,
			        dataType : 'jsonp',
			        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
						success: function(json){
							var result = json.result;
							//console.log(result);
							if(result == "succes"){
								setTimeout(function(){
									closeLoading('white', 'loadingLong');
									passAll("保存","保存成功！");
								},500);
								var axa={
									"type":"select",
									"name":dictionnarycode,
									"query":"code"
								}
								pushConfig(axa);
							}else{
								closeLoading('white', 'loadingLong');
								errorsAll(result);
							}
						}
					});
				}else{
					// 推送配置的key
					var pushKeyArr = ["PK","EVENT_REC_DOMAIN_UID","EVENT_REC_TYPE","EVENT_REC_SOAP_NOTICE_URL","EVENT_SEND_DOMAIN_UID","EVENT_SEND_NAME","EVENT_SEND_TYPE"];
					
					// 推送配置的value
					var js_domain = $("#js_domain").val();
					var js_name = $("#js_name").val();
					var js_address = $("#js_address").val();
					var js_source = $("#js_source").val();
					var js_systemName = $("#js_systemName").val();
					var js_system = $("#js_system").val();
					var pushValueArr = [ruleValTrPK,js_domain,js_name,js_address,js_source,js_systemName,js_system];

					toTransmit.hide();
					toCancel.hide();
					toModify.show();
					$(".hideOperation").hide();

					$(".hide").hide();
					$(".operaPk").show();
					$(".show").show();

					// 推送配置规则key和value
					var ruleTrNumber = $("#js_pushTb_static").find("tr").length;		// 推送规则 tr数目
					var ruleTrNum = $("#js_pushTb_static").find("tr[value]").length;	// 推送规则 带value tr数目
					var ruleValueArr = [];
					if(ruleTrNum == ruleTrNumber){
						for(var m=0;m<ruleTrNum;m++){
							var ruleValueTrArr = [];										// 推送规则当行value组成的数组

							// 推送规则配置的value
							var strTr = $("#js_pushTb_static").find("tr").eq(m);

							// 推送规则配置的key
							var ruleKeyArr = ["ROUTE_RULE_PK","PARA_NAME","OPERATOR","VALUE"];

							// 推送规则配置的value
							var ruleTrPK =  strTr.attr("value");
							var ruleValTrOne = strTr.find("input").eq(0).val();
					        var ruleValTrTwo = strTr.find("option:selected").text();
					        if(ruleValTrTwo == "大于"){
					            ruleValTrTwo = ">";
					        }else if(ruleValTrTwo == "等于"){
					            ruleValTrTwo = "=";
					        }else if(ruleValTrTwo == "小于"){
					            ruleValTrTwo = "<";
					        }else if(ruleValTrTwo == "包含"){
					            ruleValTrTwo = "contain";
					        }else if(ruleValTrTwo  == "大于等于"){
					            ruleValTrTwo = ">=";
					        }else if(ruleValTrTwo == "小于等于"){
					            ruleValTrTwo = "<=";
					        }else if(ruleValTrTwo == "不等于"){
					            ruleValTrTwo = "!=";
					        };
					        var ruleValTrThree = strTr.find("input").eq(1).val();

					        // 一维数组 元素逐个添加
					        ruleValueTrArr = [ruleTrPK,ruleValTrOne,ruleValTrTwo,ruleValTrThree];

					        // 二维数组 元素逐个添加
	    					ruleValueArr.push(ruleValueTrArr);
					    };
					    var sendDateSave = {
				            "routemap":pushKeyArr,
				            "routerule":ruleKeyArr,
				            "routemapvalue":pushValueArr,
				            "routerulevalue":ruleValueArr,
				            "type":"update"
				        }
				        // pushCon(sendDateSave);
				      //console.log(sendDateSave);
				        $.ajax({
				        type:"POST",
				        url:portconfig+"push/PushConfiguration",
				        data:sendDateSave,
				        dataType : 'jsonp',
				        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
				            success: function(json){
				                var result = json.result;
				                //console.log(result);
								if(result == "succes"){
									setTimeout(function(){
										closeLoading('white', 'loadingLong');
										passAll("保存","保存成功！");
									},500);
									var sendDataPush = {
										"name": dictionnaryPk,
										"type":"select",
										"query":"pk"
									}
									pushCon(sendDataPush);
								}else{
									setTimeout(function(){
										closeLoading('white', 'loadingLong');
										errorsAll(result);
									},500);
								}
				            }
				        });
					}else{
						for(var m=0;m<ruleTrNum;m++){
							var ruleValueTrArr = [];										// 推送规则当行value组成的数组

							// 推送规则配置的value
							var strTr = $("#js_pushTb_static").find("tr").eq(m);

							// 推送规则配置的key
							var ruleKeyArr = ["ROUTE_RULE_PK","PARA_NAME","OPERATOR","VALUE"];

							// 推送规则配置的value
							var ruleTrPK =  strTr.attr("value");
							var ruleValTrOne = strTr.find("input").eq(0).val();
					        var ruleValTrTwo = strTr.find("option:selected").text();
					        if(ruleValTrTwo == "大于"){
					            ruleValTrTwo = ">";
					        }else if(ruleValTrTwo == "等于"){
					            ruleValTrTwo = "=";
					        }else if(ruleValTrTwo == "小于"){
					            ruleValTrTwo = "<";
					        }else if(ruleValTrTwo == "包含"){
					            ruleValTrTwo = "contain";
					        }else if(ruleValTrTwo  == "大于等于"){
					            ruleValTrTwo = ">=";
					        }else if(ruleValTrTwo == "小于等于"){
					            ruleValTrTwo = "<=";
					        }else if(ruleValTrTwo == "不等于"){
					            ruleValTrTwo = "!=";
					        };
					        var ruleValTrThree = strTr.find("input").eq(1).val();

					        // 一维数组 元素逐个添加
					        ruleValueTrArr = [ruleTrPK,ruleValTrOne,ruleValTrTwo,ruleValTrThree];

					        // 二维数组 元素逐个添加
	    					ruleValueArr.push(ruleValueTrArr);

	    					
					    };
					    var sendDateSave = {
				            "routemap":pushKeyArr,
				            "routerule":ruleKeyArr,
				            "routemapvalue":pushValueArr,
				            "routerulevalue":ruleValueArr,
				            "type":"update"
				        }
				        // pushCon(sendDateSave);
						$.ajax({
				        type:"POST",
				        url:portconfig+"push/PushConfiguration",
				        data:sendDateSave,
				        dataType : 'jsonp',
				        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
				            success: function(json){
				                var result = json.result;
				                //console.log(result);
								if(result == "succes"){
									setTimeout(function(){
										closeLoading('white', 'loadingLong');
										passAll("保存","保存成功！");
									},500);
									var sendDataPush = {
										"name": dictionnaryPk,
										"type":"select",
										"query":"pk"
									}
									pushCon(sendDataPush);
								}else{
									setTimeout(function(){
										closeLoading('white', 'loadingLong');
										errorsAll(result);
									},500);
								}
				            }
				        });


						var ruleValueArr = [];
					    for(var a=ruleTrNum;a<ruleTrNumber;a++){

							var ruleValueTrArr = [];
							var strTr = $("#js_pushTb_static").find("tr").eq(a);

							// 推送规则配置的key
							var ruleKeyArr = ["EVENT_ROUTE_MAP_PK","PARA_NAME","OPERATOR","VALUE"];

							var ruleValTrOne = strTr.find("input").eq(0).val();
					        var ruleValTrTwo = strTr.find("option:selected").text();
					        if(ruleValTrTwo == "大于"){
					            ruleValTrTwo = ">";
					        }else if(ruleValTrTwo == "等于"){
					            ruleValTrTwo = "=";
					        }else if(ruleValTrTwo == "小于"){
					            ruleValTrTwo = "<";
					        }else if(ruleValTrTwo == "包含"){
					            ruleValTrTwo = "contain";
					        }else if(ruleValTrTwo  == "大于等于"){
					            ruleValTrTwo = ">=";
					        }else if(ruleValTrTwo == "小于等于"){
					            ruleValTrTwo = "<=";
					        }else if(ruleValTrTwo == "不等于"){
					            ruleValTrTwo = "!=";
					        };
					        var ruleValTrThree = strTr.find("input").eq(1).val();

					        // 一维数组 元素逐个添加
					        ruleValueTrArr = [ruleValTrPK,ruleValTrOne,ruleValTrTwo,ruleValTrThree];

					        // 二维数组 元素逐个添加
	    					ruleValueArr.push(ruleValueTrArr);
						}
						var sendDateAdd = {
				            "routerule":ruleKeyArr,
				            "routerulevalue":ruleValueArr,
				            "type":"insertson"
				        }
				        $.ajax({
				        type:"POST",
				        url:portconfig+"push/PushConfiguration",
				        data:sendDateAdd,
				        dataType : 'jsonp',
				        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
				            success: function(json){
				                var result = json.result;
								if(result == "succes"){
									setTimeout(function(){
										closeLoading('white', 'loadingLong');
										passAll("保存","保存成功！");
									},500);
									var sendDataPush = {
										"name": dictionnaryPk,
										"type":"select",
										"query":"pk"
									}
									pushCon(sendDataPush);
								}else{
									setTimeout(function(){
										closeLoading('white', 'loadingLong');
										errorsAll(result);
									},500);
								}
				            }
				        });
					}
				}
			});
		}
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
		Confirm.removeClass("delInfoBtn");
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
		Confirm.removeClass("saveInfoBtn");
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