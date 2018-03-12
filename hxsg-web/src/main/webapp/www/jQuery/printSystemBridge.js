// 报告打印系统打印页面数据生成
// $(function(){
	var localhost   = "http://168.160.76.180:7897/";
	var windowBox   = $(window);
	var dataPath    = 'data-service/patientInfoListJson.json';
	var searchBtn   = $('.search');
	var sendData    = {};
	var messageData = '';

	//点击查询按钮时获取用户输入的病人信息
	searchBtn.on("click",function(){
		var nameVal                 = $('#nameVal').val();
		var clinicNumberVal         = $('#clinicNumberVal').val();
		var selectSex               = $('#selectSex option:selected').text();
		var personalIdpersonalIdVal = $('#personalIdpersonalIdVal').val();
		var medicalInsuranceVal     = $('#medicalInsuranceVal').val();
		sendData = {
			"nameVal":nameVal,
			"clinicNumberVal":clinicNumberVal,
			"selectSex":selectSex,
			"personalIdpersonalIdVal":personalIdpersonalIdVal,
			"medicalInsuranceVal":medicalInsuranceVal
		};

		// 判断用户输入的信息再分情况载入数据
		if(nameVal == "" && clinicNumberVal == "" && selectSex == "" && personalIdpersonalIdVal == "" && medicalInsuranceVal == ""){
			// alert('请输入病人信息！！！');
			//$('.mainInfo').html('');
			//$('.mainInfo').html('<p class="searchPatient">没有查到此患者信息~</p>');
		}else{
			loadingData(dataPath,sendData);
		}
	});


	//数据注入函数
	function loadingData(dataPath,sendData){
		var mainInfo = $(".mainInfo");
		var addHtml='';
		var messageSendData = sendData;
		// alert(1);
 $.ajax({
              type:"GET",
              url:""+localhost+"printpersoninfos/personinfo.do",
              dataType : 'jsonp',
			  data:sendData,
              jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
              success: function(json){
				  messageData = json.patientInfoList;
				 
			var messageDataLength = messageData.length;
		
			if(messageDataLength != 0){
				for(var i=0; i<messageDataLength; i++){
					addHtml += 	'<div class="wraper mainListIn" uniqueId="' + messageData[i].uniqueId+ '">' +
									'<div class="cubeList clearfix">'+
										'<div class="infoList">'+
											'<label for="">住院号：</label>' +
											'<span  class="infoList_longNumber">'+ messageData[i].beInNumber +'</span>' +
											'<label for="">姓名：</label>'+
											'<span class="infoList_name">'+ messageData[i].personName +'</span>' +
											'<label for="">性别：</label>' +
											'<span class="infoList_sex">' + messageData[i].personSex +'</span>' +
											'<label for="">年龄：</label>' +
											'<span class="infoList_age">' + messageData[i].personAge +'</span>' +
											'<label for="">身份证号：</label>' +
											'<span class="idNumber">' + messageData[i].cardIdNumber +'</span>' +
											'<label for="">医保号：</label>' +
											'<span class="cardNumber">' + messageData[i].medicalNumber +'</span>'+
											
										'</div>'+
										'<a class="toggleBtn" href="javascript:void(0);"></a>' +
									'</div>' +
									'<div class="showInfoList"></div>' +
								'</div>';
					}
			}else{
				addHtml = '<div class="mainInfo"><p class="searchPatient">没有查到此患者的信息~</p></div>';
			}

			mainInfo.html("");
			mainInfo.html(addHtml);

			//查找已打印或者未打印
			$(".toggleBtn").on("click",function(){
				var _this = $(this);

				_this.toggleClass('active');

				var thisWrap = _this.closest('.mainListIn');
				var showWrap = thisWrap.find(".showInfoList");
				var uniqueId = thisWrap.attr('uniqueId');

				sendData = {
					"empi" : uniqueId
				};

				loadingSubData(_this,dataPath,sendData);
			});

		// },'json');

			}
		});

	}

	// 已打印展开数据列表
	function loadingSubData(dom,dataPath,sendData){
		$.ajax({
			type:"GET",
			url:""+localhost+"printpersoninfos/prientinfo.do",
			dataType : 'jsonp',
			data:sendData,
			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			success: function(json){

		// $.post(localhost + dataPath,sendData,function(json){
			var printedInfo = json.printedInfo;
			var toPrintInfo = json.toPrintInfo;
			var showWrap    = dom.closest('.mainListIn').find(".showInfoList");

			var addHtml = '';
			// 已打印数据展示
			if(printedInfo.length){
				addHtml +=	'<div class="listContent">' +
								'<p class="redFont">【<span>已打印</span>】</p>' +
								'<table class="titleList">' +
									'<thead>' +
										'<tr>' +
											'<th class="w020">&ensp;&ensp;&ensp;打印序号</th>' +
											'<th class="w020 paddingL35">打印时间</th>' +
											'<th class="w020 paddingL20">打印医生</th>' +
											'<th class="w020 paddingL15">所在科室</th>' +
											// '<th class="w015">PDF/补打印</th>' +
											'<th class=""></th>' +
										'</tr>' +
									'</thead>' +
								'</table>';

				for(var i = 0; i < printedInfo.length; i++){
					addHtml +=	'<div class="printedList" uniqueId="' + printedInfo[i].uniqueId + '" bitchid="' + printedInfo[i].bitchid + '">' +
									'<table class="tableList fullWidth">' +
										'<thead>' +
											'<tr>' +
												'<th class="w020 paddingLeft">第' + (i + 1) + '次打印' +
													'<input class="button print-button" type="button" value="补打印" />' +
												'</th>' +
												'<th class="w020">' + printedInfo[i].printTime + '</th>' +
												'<th class="w020">' + printedInfo[i].printUser + '</th>' +
												'<th class="w020 marginLeft">' + printedInfo[i].belongsDep + '</th>' +
												'<th class="w015">' +
												// 	'<a class="pdfPrint" href="javascript:void(0);"></a>' +
												// 	'<a class="addPrint" href="javascript:void(0);"></a>' +
												'</th>' +
												'<th class="w005">' +
													'<a class="sub_toggleBtn" href="javascript:void(0);"></a>' +
												'</th>' +
											'</tr>' +
										'</thead>' +
									'</table>' +
									'<div class="printContent"></div>' +
								'</div>';
				}

				addHtml += '</div>';
			}

			if(printedInfo.length && toPrintInfo.length){
				var copyDom = showWrap.siblings(".cubeList").clone();
				copyDom.find(".infoList").addClass("noPrint").siblings(".toggleBtn").remove();
				addHtml += copyDom[0].outerHTML;
			}

			// 未打印数据展示
			if(toPrintInfo.length){
				addHtml +=	'<div class="listContent">' +
								'<p class="redFont">【<span>未打印</span>】</p>' +
								'<table class="titleList">' +
									'<thead>' +
										'<tr>' +
											'<th class="w020">&ensp;&ensp;&ensp;检查时间' +
												'<input class="button print-button" type="button" value="打印" />' +
											'</th>' +
											'<th class="w020 paddingL35">检查项目</th>' +
											'<th class="w020 paddingL20">样本</th>' +
											'<th class="w020 paddingL15">报告情况</th>' +
											'<th class="w015"></th>' +
											'<th class="textAlignL">' +
											'</th>' +
										'</tr>' +
									'</thead>' +
								'</table>';

				for(var i = 0; i < toPrintInfo.length; i++){
					addHtml +=	'<div class="printedList" bitchid="' + toPrintInfo[i].bitchid + '">' +
									'<div class="detail-wrap">' +
										'<table class="tableList">' +
											'<thead uniqueId="' + toPrintInfo[i].uniqueId + '">' +
												'<tr class="paddingLr">' +
													'<th class="w020 paddingLeft_none">' + toPrintInfo[i].checkTime + '</th>' +
													'<th class="w020 paddingLeft_none">' + toPrintInfo[i].checkName + '</th>' +
													'<th class="w020">' + toPrintInfo[i].sampleName + '</th>' +
													'<th class="w020 grayColor textAlign">' + toPrintInfo[i].reportOut + '</th>' +
													'<th class="w015 grayColor"></th>' +
													'<th class="w005 textAlign_right">' +
														'<a class="childBtn" href="javascript:void(0);"></a>' +
													'</th>' +
												'</tr>' +
											'</thead>' +
										'</table>' +
									'<div class="detailContent"><table class="tableList"><tbody></tbody></table></div></div>' +
								'</div>';
				}

				addHtml += '</div>';
			}

			showWrap.html(addHtml);

			var _wrap = dom.closest('.wraper');
			_wrap.addClass('spreadState');
			_wrap.find(".showInfoList").slideDown();

			// 已打印展开数据列表查询
			$(".sub_toggleBtn").on("click",function(){
				var _this = $(this);

				_this.toggleClass('activeBtn');

				var thisWrap = _this.closest('.printedList');
				var showWrap = thisWrap.find(".printContent");
				var uniqueId = thisWrap.attr('uniqueId');
				var _beInNumber = _this.closest(".wraper").find(".infoList_longNumber").text();

				sendData = {
					"empi" : uniqueId,
					"InHosNum" : _beInNumber
				};

				printNum(_this,dataPath,sendData);

			});
		// },'json');

			}
		});
	}

	// 报告列表
	function loadingCheck(dom,dataPath,sendData){
		$.ajax({
			type:"GET",
			url:""+localhost+"printpersoninfos/xmlinfo.do",
			dataType : 'jsonp',
			data:sendData,
			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			success: function(json){

		// $.post(localhost + dataPath,sendData,function(json){
			var check = json.subMainInfo;
			var showWrap = dom.closest('.printedList').find(".printContent");

			var addHtml = '';

			for(var i = 0; i < check.length; i++){
				addHtml +=	'<div class="detail-wrap"><table class="tableList">' +
								'<thead uniqueId="' + check[i].uniqueId + '">' +
									'<tr class="underLineThin paddingLr">' +
										'<th class="w020 paddingLeft_none">' + check[i].reportTime + '</th>' +
										'<th class="w020 paddingLeft_none">' + check[i].checkName + '</th>' +
										'<th class="w020 marginLeft">' + check[i].sampleName + '</th>' +
										'<th class="grayColor textAlign w015"><span class="reported">' + check[i].reportOut + '</span></th>' +
										'<th class="grayColor w020">' +
											'<span>住院号：' + check[i].inHosNum + '</span>&ensp;&ensp;&ensp;' +
											'<span>检验号：</span>' +
											'<span>' + check[i].checkNum + '</span>' +
										'</th>' +
										'<th class="w005 textAlign_right">' +
											'<a class="childBtn" href="javascript:void(0);"></a>' +
										'</th>' +
									'</tr>' +
								'</thead>' +
							'</table>' +
							'<div class="detailContent"><table class="tableList"><tbody></tbody></table></div></div>';
			}

			showWrap.html(addHtml);

			var _wrap = dom.closest('.printedList');
			_wrap.children('.tableList').addClass('grayBg');
			_wrap.find(".printContent").slideDown();
		// },'json');

			}
		});
	}

	// 展开报告详细数据展示
	$(document).on("click",".childBtn",function(){
		var _this = $(this);

		_this.toggleClass('active_childBtn');

		var uniqueId = _this.closest("thead").attr('uniqueId');

		sendData = {
			"uniqueId" : uniqueId
		};

		checkItem(_this,dataPath,sendData);
	});

	// 点击打印
	$(document).on("click",".print-button",function(){
		var _this = $(this);
		var uniqueId = _this.closest(".wraper").attr("uniqueId");
		var bitchid = _this.closest(".printedList").attr("bitchid") || _this.closest('.listContent').find(".printedList:first").attr("bitchid");
         var name=$("#doctorname").text();
        var dpname=$("#deptname").text();
        var dpcode=$("#deptcode").val();
        var dtid=$("#doctorid").val();

		sendData = {
			"empi" : uniqueId,
			"bitchid" : bitchid,
            "doctorname":name,
            "deptname":dpname,
            "deptcode":dpcode,
            "doctorid":dtid
		};

		// 这里你去改
		$.ajax({
			type:"GET",
			url:""+localhost+"printpersoninfos/printreport.do",
			dataType : 'jsonp',
			data:sendData,
			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			success: function(json){
                var pdfmsg=json.filemsg;
                var filepath=json.pdfpaths;
                var msg=json.msg;
                if(msg=='error'){
                    alert("在内部服务器中没有找到pdf文档，无法打印！");
                }
                alert(pdfmsg);

				
			}
			
			
			
			});
	});

	// 加载报告
	function loadingCheckDetail(dom,dataPath,sendData){
		$.ajax({
			type:"GET",
			url:""+localhost+"printpersoninfos/noprintxmlinfo.do",
			dataType : 'jsonp',
			data:sendData,
			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			success: function(json){

		// $.post(localhost + dataPath,function(json){
			var _json = json.detailCheck;
			var _type = _json.dataType;

			if(_type == 0){
				loadingCheckFlag(dom,dataPath,sendData,_json);
			}else if(_type == 1){
				loadingCheckCell(dom,dataPath,sendData,_json);
			}

			var _wrap = dom.closest('.detail-wrap');
			_wrap.find(".detailContent").slideDown();
		// },'json');

			}
		});
	}

	// 指标类报告
	function loadingCheckFlag(dom,dataPath,sendData,json){
		var detailCheck = json;
		var showWrap = dom.closest('.printedList').find(".detailContent");

		var addHtml = '';

		// for(var i = 0; i < detailCheck.length; i++){
			var _detail = detailCheck.detail;
			var _html = '';

			for(var j = 0; j < _detail.length; j++){
				var _str = flagTo(_detail[j].proResultState,_detail[j].proResult);

				_html += '<tr class="underLineThin paddingLr">' +
							'<td class="w020"></td>' +
							'<td class="w020 paddingLeft_none">' + _detail[j].proName + '</td>' +
							'<td class="w020"><span class="marginRight ' + getColorClass(_detail[j].proResultState) + '">' +
								_str + '</span><span class="grayColor">' + _detail[j].proResultUnit + '</span></td>' +
							'<td class="w015">' + _detail[j].proSampleRange + '</td>' +
							'<td class="w020"></td>' +
							'<td></td>' +
						 '</tr>';
			}


			if(detailCheck.checkResult !== ''){
				var _last = '<tr class="borderDashed">' +
								'<td class="combineList" colspan=6>' +
									'<span class="checkResult">检验结果：</span>' +
									'<p>' + detailCheck.checkResult + '</p>' +
								'</td>' +
							'</tr>';
			}else{
				var _last = '';
			}

			addHtml +=	'<tr class="paddingLr">' +
							'<td></td>' +
							'<td class="boldFont paddingLeft_none">' + detailCheck.name + '</td>' +
							'<td></td>' +
							'<td></td>' +
							'<td></td>' +
							'<td></td>' +
						'</tr>' +
						'<tr class="titleFont paddingLr">' +
							'<td></td>' +
							'<td class="paddingLeft_none">项目名称</td>' +
							'<td>结果/单位</td>' +
							'<td>参考值</td>' +
							'<td></td>' +
							'<td></td>' +
						'</tr>' +
							_html +	_last;
		// }

		showWrap.find("tbody").html(addHtml);
		showWrap.find(".borderDashed:last-child").removeClass('borderDashed');
	}

	// 细菌培养
	function loadingCheckCell(dom,dataPath,sendData,json){
		var detailCell = json.detail;
		var showWrap = dom.closest('.printedList').find(".detailContent");

		var addHtml = '';

		for(var i = 0; i < detailCell.length; i++){
			var _cell = detailCell[i].detail;
			var _href = _cell.table ? "<a class='more' href='javascript:;'>详细</a>" : "";
			var _num = _cell.table ? _cell.table.length : 0;

			if(_cell.table){
				var _more = '<div class="floatBox">' +
								'<h5><span>细菌技术（CFU/ml）:' + _cell.count + '</span><span>半定量：' + _cell.number + '</span></h5>' +
								'<b>临床评语</b>' +
								'<p>' + _cell.stence + '</p>' +
								'<table>' +
									'<thead>' +
										'<tr>' +
											'<th width="200">抗菌药物</th>' +
											'<th width="50">KB(mm)</th>' +
											'<th width="70">MIC(μg/ml)</th>' +
											'<th>敏感度</th>' +
										'</tr>' +
									'</thead>' +
								'</table>' +
								'<div>' +
									'<table>' +
										'<tbody>';

				for(var j = 0; j < _num; j++){
					var More = _cell.table[j];
					_more += '<tr class="' + getColorClass(More.state) + '">' +
								'<td width="200">' + More.yaowu + '</td>' +
								'<td width="50">' + More.kb + '</td>' +
								'<td width="70">' + More.mic + '</td>' +
								'<td>' + More.mingan + '</td>' +
							 '</tr>';
				}

				_more += '</tbody>' +
								'</table>' +
							'</div>' +
						 '</div>';
			}else{
				var _more = '';
			}

			addHtml +=	'<tr class="underLineThin">' +
							'<td class="w020"></td>' +
							'<td class="w020">' + detailCell[i].name + '</td>' +
							'<td class="w020">' + detailCell[i].describe + '</td>' +
							'<td class="w015"><div>' + _href + _more + '</div></td>' +
							'<td></td>' +
							'<td></td>' +
						'</tr>';
		}

		showWrap.find("tbody").html(addHtml);

		$(".more").on("click",function(e){
			e.stopPropagation();
			var _this = $(this);
			_this.closest('div').addClass('pr');
			_this.siblings(".floatBox").show();
		});

		$(".floatBox").on("click",function(e){
			e.stopPropagation();
		});

		$("body").on("click",function(){
			$(".more").closest('div').removeClass('pr');
			$(".floatBox").hide();
		});
	}

// });

function getColorClass(num){
	var str = '';

	if(num == 0){
		str = "colorRed";
	}else if(num == 1){
		str = "colorGreen";
	}else{
		str = "";
	}

	return str;
}

function flagTo(num,colorNum){
	var str = '';

	if(num == 0){
		str = colorNum + " <i>↑</i>";
	}else if(num == 1){
		str = colorNum + " <i>↓</i>";
	}else{
		str = colorNum;
	}

	return str;
}