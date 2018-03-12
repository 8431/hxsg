$(function(){
    
    var documentBox = $(document);
    var popBg = $('.iframe_box');                                       // 弹窗黑背景
    var popBgW = $('.iframe_boxW');                                     // 弹窗黑背景
    var popLoading = $('.popLoading');                                  // 弹窗白背景

    var js_query = $("#js_query");                                      // 查询按钮

    var js_pagelist = $(".js_pageList");
    var js_page_show = $("#js_page_show");

    var js_table_static = $("#js_table_static");                        // 左侧表注入
    var js_dictname = $("#js_dictname");                                // 字典名
    var js_flowChart = $("#js_flowChart");                              // 流程图注入
    var js_radioBtn = $(".radioBtn");                                   // 成功、失败和全部  获取

    var js_init_time = $("#js_init_time");                              // 开始时间
    var js_end_time = $("#js_end_time");                                // 结束时间

    // 获取当前时间 初始值
    function compare(n){
        if(n<10){
            return '0'+n;
        }else{
            return n;
        }
    }
    var js_mouth = $("#js_mouth");
    var todayTimeInit ='';
    var todayTimeEnd ='';
    tadaytime_array1 = compare(new Date().getFullYear());
    tadaytime_array2 = compare(new Date().getMonth()+1);
    tadaytime_array3 = compare(new Date().getDate());
    todayTimeInit = tadaytime_array1 + '-' + tadaytime_array2 + '-' + tadaytime_array3 + ' ' + '00:00:00';
    todayTimeEnd = tadaytime_array1 + '-' + tadaytime_array2 + '-' + tadaytime_array3 + ' ' + '23:59:59';

    // 将当天的时间注入
    js_init_time.val(todayTimeInit);
    js_end_time.val(todayTimeEnd);
    
    var NowPage = 1;
    var page_cur = 1;
    var page_total = "";
    var searchVal = [["REC_TIME>","2016-09-01 00:00:00"],["REC_TIME<","2016-09-09 23:59:59"]];
    var sendQuery = "";                                                 // 获取右侧传参

    // 初始全部为选中状态
    js_radioBtn.find('input[alt="01"]').prop('checked',true);

    // 初始传值
    var sendDateInit = {
        "type":"selectPage",
        "currentpage":"1",
        "tablename":"V_DICT_PUSH_SCATTER_STATUS",
        "arrays":searchVal
    }

    flowleft(sendDateInit);
    function flowleft(sendDate){
        var addHtmlTbody = '';
        $.ajax({
            type:"POST",
            url:portconfig+"flow/mybatis",
            data:sendDate,
            dataType : 'jsonp',
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            success: function(json){
                // console.log(json)
                var arrays = json.arrays;
                page_cur = json.page.currentpage;
                page_total = json.page.totalsize;
                //console.log(arrays);
                if(arrays != ''){
                    if(page_cur <= page_total){
                        // 分页注入
                        if(page_total > 1){
                            js_pagelist.show();
                        }else{
                            js_pagelist.hide();
                        }
                        js_page_show.html("");
                        js_page_show.html(page_cur + " / " + page_total + "页");

                        // 左侧内容表格注入
                        for(var i=0;i<arrays.length;i++){

                            addHtmlTbody += '<tr alt="'+ arrays[i].UUID +'">' + 
                                                '<td class="w112">' + arrays[i].MSG_ID + '</td>' +
                                                '<td class="w112">' + arrays[i].PAY_LOAD_TYPE + '</td>' +
                                                '<td class="w112">' + arrays[i].SUB_TYPE + '</td>' +
                                                '<td class="w112">' + arrays[i].REC_TIME + '</td>' +
                                                '<td class="w112">' + arrays[i].STATUS + '</td>' + 
                                            '</tr>';
                        }
                        js_table_static.html('');
                        js_table_static.append(addHtmlTbody);

                        tableColor();

                        // 表格内容转换
                        js_table_static.find("tr").each(function(){

                            var strTr = $(this).closest("tr");

                            // 将操作中的英文转换成中文
                            var operation = strTr.find("td").eq(2);
                            //console.log(strTr.find("td").eq(2).text());
                            if(operation.text() == "ADD"){
                                operation.text("上传");
                            }else if(operation.text() == "UPDATE"){
                                operation.text("更新");
                            }else if(operation.text() == "ABANBON"){
                                operation.text("作废");
                            };

                            // 将更新中的数字转换成图片
                            var state = strTr.find("td").eq(4);
                            //console.log(strTr.find("td").eq(2).text());
                            if(state.text() == "0"){
                                state.html("");
                                state.append("<span class= 'success'>"+"</span>");
                            }else if(state.text() == "1"){
                                state.html("");
                                state.append("<span class= 'success'>"+"</span>");
                            }else if(state.text() == "2"){
                                state.html("");
                                state.append("<span class= 'fail'>"+"</span>");
                            };
                        });
                        
                        js_table_static.find("tr").eq(0).addClass("click");
                        var initUUID = $(".click").attr("alt");
                        sendQuery = {
                            "uuid":initUUID
                        };
                        flowright(sendQuery);

                        js_table_static.undelegate("click").delegate("tr", "click" ,function(){
                            
                            $(this).addClass("click").siblings().removeClass("click");
                            var UUID = $(".click").attr("alt");
                            sendQuery = {
                                "uuid":UUID
                            };
                            flowright(sendQuery);
                        });
                    }else{
                        js_table_static.html("");
                        js_table_static.append('<td colspan="9" class="overPage_L">' + '<p>' + "您输入的页码已超出当前内容总页码！" + '</p>' + '</td>');
                        js_flowChart.html("");
                    }
                }else{
                    js_table_static.html("");
                    js_table_static.append('<td colspan="9" class="overPage_L">' + '<p>' + "未能查询到相关内容！" + '</p>' + '</td>');
                    js_flowChart.html("");
                    js_pagelist.hide();
                };
            }
        });    
    }

    function flowright(sendDate){

        var addHtmlStart = '';                                                      // 页面初始固定html
        var addHtmlPush = '';                                                       // 页面推送子数列html
        
        $.ajax({
            type:"POST",
            url:portconfig+"flow/img",
            data:sendDate,
            dataType : 'jsonp',
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            success: function(json){
                var push = json.push;
                var scatter = json.scatter;
                //console.log(typeof(push));
                if(push != ''){

                    var pushSon = push.length;

                    js_flowChart.css("width",605);
                    js_flowChart.css("height",(pushSon + 1)*100);                
                    // '+ scatter.QUEUESTATUS +'
                    // 静态的部分
                    addHtmlStart =  '<div class="title" style="top:'+ (100 + 50*(pushSon-1))/2 +"px" +';left:-15px;">' + '<p>' + "HIS" + '</p>' + '</div>' +
                                    '<div class="successImg" style="width:210px;position:absolute;top:'+ ((100 + 50*(pushSon-1))/2+25) +"px" +';left:-15px;">' + 
                                        '<span class="dlw210">' + '</span>' +
                                        '<span class="timeRetry">' + 
                                            '<em>' + scatter.HISTIME + '</em>' +
                                        '</span>' +
                                    '</div>' +

                                    '<div class="lineSite85" style="left:98px;top:'+ (((100 + 50*(pushSon-1))/2 + 33)-1)+"px" +';">' + '</div>' +
                                    '<div class="verticalLineQueue" style="height:'+ (50*(pushSon-1)+100)+"px" +';">' + '</div>' +

                                    '<div class="lineSite85" style="left:185px;top:33px;">' + '</div>' +
                                    '<div class="arrowImg" style="left:270px;top:28px;">' + '</div>' +
                                    '<div class="title" style="top:0;left:190px;">' + '<p>' + "打散队列" + '</p>' + '</div>' + 
                                    '<div class="judgeQueue" alt="'+ scatter.QUEUESTATUS +'" name="'+ scatter.DEC +'" style="width:210px;position:absolute;top:25px;left:190px;">' + 
                                        '<span class="onfail dlw210">' + '</span>' +
                                        '<span class="timeRetry">' + 
                                            '<em>' + scatter.QUEUETIME + '</em>' +
                                            '<i name="SCATTER_STATUS=">' + '重试' + '</i>' +
                                        '</span>' +
                                    '</div>' +

                                    '<div class="crossBreak">' + '</div>' +
                                    '<div class="arrowImg" style="left:490px;top:28px;">' + '</div>' +
                                    '<div class="title" style="top:0;left:410px;">' + '<p>' + "打散字典" + '</p>' + '</div>' + 
                                    '<div class="judgeWord" alt="'+ scatter.DICTSTATUS +'" name="'+ scatter.DEC +'" style="width:210px;position:absolute;top:25px;left:410px;">' + 
                                        '<span class="onfail dlw210">' + '</span>' +
                                        '<span class="timeRetry">' + 
                                            '<em>' + scatter.DICTTIME + '</em>' +
                                            '<i name="SCATTER_STATUS=">' + '重试' + '</i>' +
                                        '</span>' +
                                    '</div>' +

                                    '<div class="lineSite85" style="left:185px;top:'+ (((100 + 50*(pushSon-1)) + 33)-2)+"px" +';">' + '</div>' +
                                    '<div class="arrowImg" style="left:270px;top:'+ (((100 + 50*(pushSon-1)) + 28)-2)+"px" +';">' + '</div>' +
                                    '<div class="title" style="top:'+ ((100 + 50*(pushSon-1))-2) +"px" +';left:190px;">' + '<p>' + "推送队列" + '</p>' + '</div>' +
                                    '<div class="judgePush" style="width:210px;position:absolute;top:'+ ((100 + 50*(pushSon-1))-2+25)+"px" +';left:190px;">' + 
                                        '<span class="onfail dlw210">' + '</span>' +
                                        '<span class="timeRetry">' + 
                                            '<em>' + push[0].PUSHTIME + '</em>' +
                                            '<i name="PUSH_STATUS=">' + '重试' + '</i>' +
                                        '</span>' +
                                    '</div>' +

                                    '<div class="lineSite100" style="left:303px;top:'+ (((100 + 50*(pushSon-1)) + 33)-2)+"px" +';">' + '</div>' +
                                    '<div class="verticalLinePush" style="height:'+ 100*(pushSon-1) +"px" +';">' + '</div>';
                    // 可变的推送子队列
                    for(var j=0;j<pushSon;j++){
                        if(j == 0){                                                                     // 解决推送队列中子队列第一个错了 2像素的问题
                            addHtmlPush =  '<div class="lineSite85" style="left:405px;top:'+ ((100 + 100*j) + 33)+"px" +';">' + '</div>' +
                                        '<div class="arrowImg" style="left:490px;top:'+ ((100 + 100*j) + 28)+"px" +';">' + '</div>' +
                                        '<div class="title onTxt" style="top:'+ 100*(j+1) +"px" +';left:410px;">' + '<p>' + push[j].ID_NAME + '</p>' + '</div>' +
                                        '<div class="judgeSon" alt="'+ push[j].PUSH_STATUS +'" name="'+ push[j].PUSH_DEC +'" style="width:210px;position:absolute;top:'+ (100*(j+1)+25)+"px" +';left:410px;">' + 
                                            '<span class="onfail dlw210">' + '</span>' +
                                            '<span class="timeRetry" >' + 
                                                '<em>' + push[j].PUSHTIME + '</em>' +
                                                '<i name="PUSH_STATUS=">' + '重试' + '</i>' +
                                            '</span>' +
                                        '</div>';  
                        }else{
                            // '+ push[j].PUSH_STATUS +'
                            addHtmlPush +=  '<div class="lineSite85" style="left:405px;top:'+ ((100 + 100*j) + 31)+"px" +';">' + '</div>' +
                                            '<div class="arrowImg" style="left:490px;top:'+ ((100 + 100*j) + 26)+"px" +';">' + '</div>' +
                                            '<div class="title onTxt" style="top:'+ 100*(j+1) +"px" +';left:410px;">' + '<p>' + push[j].ID_NAME + '</p>' + '</div>' +
                                            '<div class="judgeSon" alt="'+ push[j].PUSH_STATUS +'" name="'+ push[j].PUSH_DEC +'" style="width:210px;position:absolute;top:'+ (100*(j+1)+25)+"px" +';left:410px;">' + 
                                                '<span class="onfail dlw210">' + '</span>' +
                                                '<span class="timeRetry">' + 
                                                    '<em>' + push[j].PUSHTIME + '</em>' +
                                                    '<i name="PUSH_STATUS=">' + '重试' + '</i>' +
                                                '</span>' +
                                            '</div>'; 
                        }; 
                    };           

                    addHtmlStart += addHtmlStart + addHtmlPush;

                    js_flowChart.html("");
                    js_flowChart.append(addHtmlStart);
                    
                    var judgeQueue = $(".judgeQueue");                                          // 打散队列
                    var judgeWord = $(".judgeWord");                                            // 打散字典
                    var judgePush = $(".judgePush");                                            // 推送队列

                    // 打散队列和打散字典 是否成功的判断
                    var altQueue =  js_flowChart.find(".judgeQueue").attr("alt");
                    var altWord =  js_flowChart.find(".judgeWord").attr("alt");
                    if(altQueue == 2){
                        judgeQueue.addClass("failImg");
                        judgeQueue.find("i").show();
                        judgeWord.addClass("noshowImg");
                        judgeWord.find(".timeRetry").hide();
                    }else{
                        judgeQueue.addClass("successImg");
                        judgeQueue.find("i").hide();
                        if(altWord == 2){
                            judgeWord.addClass("failImg");
                            judgeWord.find("i").show();
                        }else{
                            judgeWord.addClass("successImg");
                            judgeWord.find("i").hide();
                        };
                    }

                    // 推送队列子列 是否成功的判断
                    if(pushSon != 0){
                        judgePush.addClass("failImg");
                        judgePush.find("i").show();
                        js_flowChart.find(".judgeSon").each(function(){
                            if($(this).attr("alt") == 2){
                                $(this).addClass("failImg");
                                $(this).find("i").show();
                            }else{
                                $(this).addClass("successImg");
                                $(this).find("i").hide();
                            }
                        });
                    };

                    // 推送队列 是否成功的判断
                    js_flowChart.find(".judgeSon").each(function(){
                        if($(this).hasClass("successImg")){
                            judgePush.removeClass("failImg");
                            judgePush.addClass("successImg");
                            judgePush.find("i").hide();
                        }
                    });

                    // 错误提示
                    js_flowChart.find(".onfail").each(function(){
                        if($(this).closest("div").attr("alt") == 2){
                            $(this).mouseenter(function(){
                                $(this).append('<div style="position:absolute;left:0px;top:40px;width:200px;height:40px;border:1px solid #B20639;z-index:1001;background-color:#FBF3F5;text-indent: 1em;">' + '<p>' + $(this).closest("div").attr("name") + '</p>' + '</div>');
                                $(this).siblings().find("div").remove();
                            }).mouseleave(function(){
                                $(this).find("div").remove();
                            });
                        }
                    });

                    // 文本提示
                    js_flowChart.find(".onTxt").each(function(){
                        $(this).mouseenter(function(){
                            $(this).append('<div style="position:absolute;left:15px;top:21px;width:20px;height:7px;background:url(images/u4065.png) no-repeat 0 0;z-index:1000;">' + '</div>'  
                                + '<div style="position:absolute;left:0;top:27px;width:200px;height:40px;border:1px solid #CFCFCF;background-color:#fff;font-size:14px;font-weight:normal;text-indent: 5px;">' + $(this).text() + '</div>');
                        }).mouseleave(function(){
                            $(this).find("div").remove();
                        });
                    });

                }else{
                    js_flowChart.html("");
                }
            }
        }); 
    };

    // 重试
    js_flowChart.undelegate("click").delegate("i", "click" ,function(){
        
        // 重试状态位
        var js_retry = $(this).attr("name");

        // uuid
        var js_uuid = $(".click").attr("alt");

        var sendRetry = {
			"type":"update",
            "set":[[js_retry,"0"]],
            "where":[["UUID=",js_uuid]],
            "tablename":"DICT_REC_INFO"
        };
        //console.log(sendRetry);

        $.ajax({
        type:"POST",
        url:portconfig+"match/mybatis",
        data:sendRetry,
        dataType : 'jsonp',
        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            success: function(json){
                //console.log(json.result);
            }
        });
    });
    // 查询功能
    js_query.off("click").on("click",function(){

        var js_init_time = $("#js_init_time").val();
        var js_end_time = $("#js_end_time").val();
        js_dictname = $("#js_dictname").val();
        // console.log(js_dictname)
        // console.log(searchVal)

        var judgementPk = js_radioBtn.find("input[name='choose']:checked").attr("alt");
        //console.log(judgementPk);
		//////////////0920修改---------------------------------------------------------------
        if(judgementPk == '01'){
            searchVal = [["REC_TIME>",js_init_time],["REC_TIME<",js_end_time],["PAY_LOAD_TYPE like","%"+js_dictname+"%"]];
        }else if(judgementPk == '1'){
            searchVal = [["REC_TIME>",js_init_time],["REC_TIME<",js_end_time],["STATUS=","0"],["PAY_LOAD_TYPE like","%"+js_dictname+"%"]];
        }else if(judgementPk == '2'){
            searchVal = [["REC_TIME>",js_init_time],["REC_TIME<",js_end_time],["STATUS>","0"],["PAY_LOAD_TYPE like","%"+js_dictname+"%"]];
        };
		//////////////0920修改---------------------------------------------------------------
        //console.log(searchVal);

        var sendSearch = {
            "currentpage":NowPage,
            "type":"selectPage",
            "tablename":"V_DICT_PUSH_SCATTER_STATUS",
            "arrays":searchVal
        }
        console.log(sendSearch)
        $.ajax({
        type:"POST",
        url:portconfig+"flow/mybatis",
        data:sendSearch,
        dataType : 'jsonp',
        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            success: function(json){
                flowleft(sendSearch);
            }
        });
    });

    // 分页功能
    documentBox.undelegate("#js_page_home","click").delegate("#js_page_home", "click", function(){
        
        NowPage = 1;

        var sendPageNum = {
            "currentpage":NowPage,
            "type":"selectPage",
            "tablename":"V_DICT_PUSH_SCATTER_STATUS",
            "arrays":searchVal
        }
        flowleft(sendPageNum);
        
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
            "type":"selectPage",
            "tablename":"V_DICT_PUSH_SCATTER_STATUS",
            "arrays":searchVal
        }

        flowleft(sendPageNum);
         
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
            "type":"selectPage",
            "tablename":"V_DICT_PUSH_SCATTER_STATUS",
            "arrays":searchVal
        }

        flowleft(sendPageNum);
        
    });
    
    documentBox.undelegate("#js_page_end","click").delegate("#js_page_end", "click", function(){
        
        NowPage = page_total;
        
        var sendPageNum = {
            "currentpage":NowPage,
            "type":"selectPage",
            "tablename":"V_DICT_PUSH_SCATTER_STATUS",
            "arrays":searchVal
        }
        flowleft(sendPageNum);
        
    });

    documentBox.undelegate("#js_page_jump","click").delegate("#js_page_jump", "click", function(){

        NowPage = parseInt($(this).siblings("input").val());
        
        var sendPageNum = {
            "currentpage":NowPage,
            "type":"selectPage",
            "tablename":"V_DICT_PUSH_SCATTER_STATUS",
            "arrays":searchVal
        }
        flowleft(sendPageNum);
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