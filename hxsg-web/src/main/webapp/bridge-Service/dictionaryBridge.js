/* 监控平台后台系统，页面数据动态生成 */

$(function(){

    //var documentBox = $(document);
    var popBg = $('.iframe_box')
    var popBgW = $('.iframe_boxW')
    var popLoading = $('.popLoading')
    var nameGlobal =  null;
    var nameGlobalID =  null;

    var sendingRules = $(".sendingRules")						// 字典明细

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

    dictionaryData()
    // 数据库管理页面 数据交互
    sendingRules.on("click", function(){
        dictionaryData();
    });

    //字典明细数据交互
    function dictionaryData(){

        //alert("dictionaryData");

        var searchDictionary = $("#searchDictionary")				// 搜索按钮

        var js_dictionary_option = $("#js_dictionary_option");		// 字典明细 左边列表 选项
        var js_dictionary_List = $("#js_dictionary_List");			// 字典明细 左边列表

        var js_dictionary = $("#js_dictionary");
        var js_dictionary_TB = $("#js_dictionary_TB");
        var js_dictionarydetail_th = $("#js_dictionarydetail_th");	// 字典明细 右边表头
        var js_dictionarydetail_td = $("#js_dictionarydetail_td");	// 字典明细 右边表格内容
        var js_pageList = $(".js_pageList");                        // 分页 注入接口

        var addOnlyTR = '';											// 增加的单行

        var Maintain = '';

        //初始值
        var js_pageList = $(".js_pageList");                        // 分页 入口
        var nowPageNumber = 1; 										// 当前页
        var overPageNumber = '';									// 总页码

        var firstName = '';

        dictionaryLeft({"type":"1"});

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
            console.log(datetype)
            $.ajax({
                type:"get",
                // url:portconfig+"dictionary/dytype",
                url:"http://119.29.234.184:8080/hxsg2.0/hxsgAdmin/getColumn",
                data:datetype,
                dataType : 'jsonp',
                jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                success: function(json){
                    var dictionary = json.dictionary;

                    // 字典明细 左边列表

                    for(var i=0;i<dictionary.length;i++){
                        addHtmlList += '<li name="'+dictionary[i].costom3+'" value="'+dictionary[i].type+'" type="'+ dictionary[i].costom2 +'" id="'+dictionary[i].pk+'" alt="'+dictionary[i].costom1+'">' + dictionary[i].name + '</li>';
                    }
                    js_dictionary_List.html("");
                    js_dictionary_List.append(addHtmlList);

                    js_pageList.undelegate(".homePage", "click").delegate(".homePage", "click", function(){

                        nowPageNumber = 1;
                        loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');
                        dictionaryRight(nameGlobal,nameGlobalID,3);
                        closeLoading('white', 'loadingBigl');
                    });

                    js_pageList.undelegate(".prePage", "click").delegate(".prePage", "click", function(){

                        if(nowPageNumber > 1){
                            nowPageNumber -= 1;
                        }else{
                            nowPageNumber = 1;
                        }
                        loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');
                        dictionaryRight(nameGlobal,nameGlobalID,3);
                        closeLoading('white', 'loadingBigl');

                    });
                    js_pageList.undelegate(".nextPage", "click").delegate(".nextPage", "click", function(){

                        if(nowPageNumber < overPageNumber){
                            nowPageNumber = parseInt(nowPageNumber) + 1;
                        }else{
                            nowPageNumber = overPageNumber
                        }
                        loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');
                        dictionaryRight(nameGlobal,nameGlobalID,3);
                        closeLoading('white', 'loadingBigl');

                    });
                    js_pageList.undelegate(".overPage", "click").delegate(".overPage", "click", function(){

                        nowPageNumber = overPageNumber;
                        loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');
                        dictionaryRight(nameGlobal,nameGlobalID,3);
                        closeLoading('white', 'loadingBigl');
                    });

                    // 字典切换的判断
                    var optionPk = js_dictionary_option.find("option:selected").val();
                    if(optionPk == 1 && judgeval == 0){
                        optionval = 1;
                        js_dictionary_List.find("li[value='1']").show();
                        js_dictionary_List.find("li[value='0']").hide();
                        $("#js_dictionary_List").find("li[value='1']").eq(0).addClass("bgCurNum").siblings().removeClass("bgCurNum");
                        //alert($("#js_dictionary_List").find("li[value='1']").length);
                    }else if(optionPk == 0 && judgeval == 0){
                        optionval = 0;
                        js_dictionary_List.find("li[value='1']").hide();
                        js_dictionary_List.find("li[value='0']").show();
                        var nonNum = $("#js_dictionary_List").find("li[value='1']").length;
                        $("#js_dictionary_List").find("li").eq(nonNum).addClass("bgCurNum").siblings().removeClass("bgCurNum");
                        // console.log( $("#js_dictionary_List").find("li[value='0']").eq(0).text());

                    }else if(judgeval == 1 && optionval == 1){
                        js_dictionary_option.find("option[value='1']").prop("selected",true);
                        js_dictionary_List.find("li[value='1']").show();
                        js_dictionary_List.find("li[value='0']").hide();
                        $("#js_dictionary_List").find("li[id='"+showval+"']").addClass("bgCurNum").siblings().removeClass("bgCurNum");

                    }else if(judgeval == 1 && optionval == 0){
                        js_dictionary_option.find("option[value='0']").prop("selected",true);
                        js_dictionary_List.find("li[value='1']").hide();
                        js_dictionary_List.find("li[value='0']").show();
                        $("#js_dictionary_List").find("li[id='"+showval+"']").addClass("bgCurNum").siblings().removeClass("bgCurNum");
                    }

                    // 切换传值 （de=0 初始，de=1 切换，nc=1 非常数，nc=0 常数）
                    Maintain = "dictionaryEditor.html?nc="+optionval+"&de=1&ee=" + $(".bgCurNum").attr("id") ;
                    $("#ee").attr("href",Maintain);

                    var namePK = $(".bgCurNum").attr("alt");                // costom1
                    var orderByName = $(".bgCurNum").attr("name");          // costom3
                    var firstNameTxt = $(".bgCurNum").text();               // 字典中文名称

                    var sendDataProcess = {
                        "tablename": namePK,
                        "name": firstNameTxt,
                        "currentpage":1,
                        "type":"select",
                        "orderByName":orderByName
                    }
                    nameGlobal = null;
                    nameGlobalID = null;

                    nameGlobal = sendDataProcess;
                    nameGlobalID = showval;
                    // nameGlobal['currentpage'] = 1;
                    dictionaryRight(nameGlobal,nameGlobalID,1);



                    // 左边列表中的点击效果
                    js_dictionary_List.find("li").each(function(i){

                        $(this).off("click").on("click",function(event){

                            event.stopPropagation;

                            $(this).addClass("bgCurNum").siblings().removeClass("bgCurNum");

                            var namePK = $(".bgCurNum").attr("alt");                // costom1
                            var nameID = $(".bgCurNum").attr("type");               // costom2
                            var orderByName = $(".bgCurNum").attr("name");          // costom3
                            var firstNameTxt = $(".bgCurNum").text();               // 字典中文名称

                            var sendDataProcess = {
                                "tablename": namePK,
                                "name": firstNameTxt,
                                "currentpage":"",
                                "type":"select",
                                "orderByName":orderByName
                            }

                            nameGlobal = sendDataProcess;
                            nameGlobalID = nameID;
                            nameGlobal['currentpage'] = 1;

                            dictionaryRight(nameGlobal,nameGlobalID,1);

                            js_pageList.undelegate(".homePage", "click").delegate(".homePage", "click", function(){

                                nowPageNumber = 1;

                                loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');
                                dictionaryRight(nameGlobal,nameGlobalID,3);
                                closeLoading('white', 'loadingBigl');
                            });

                            js_pageList.undelegate(".prePage", "click").delegate(".prePage", "click", function(){

                                if(nowPageNumber > 1){
                                    nowPageNumber -= 1;
                                }else{
                                    nowPageNumber = 1;
                                }

                                loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');
                                dictionaryRight(nameGlobal,nameGlobalID);
                                closeLoading('white', 'loadingBigl');

                            });
                            js_pageList.undelegate(".nextPage", "click").delegate(".nextPage", "click", function(){

                                if(nowPageNumber < overPageNumber){
                                    nowPageNumber = parseInt(nowPageNumber) + 1;
                                }else{
                                    nowPageNumber = overPageNumber
                                }

                                loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');
                                dictionaryRight(nameGlobal,nameGlobalID,3);
                                closeLoading('white', 'loadingBigl');

                            });
                            js_pageList.undelegate(".overPage", "click").delegate(".overPage", "click", function(){

                                nowPageNumber = overPageNumber;

                                loadingShow('white', 'loadingBigl', '数据载入中，请您稍等，谢谢！');
                                dictionaryRight(nameGlobal,nameGlobalID,3);
                                closeLoading('white', 'loadingBigl');
                            });

                            var optionPk = js_dictionary_option.find("option:selected").val();
                            if(optionPk == 1){
                                // 切换传值 （de=0 初始，de=1 切换，nc=1 非常数，nc=0 常数）
                                Maintain = "dictionaryEditor.html?nc=1&de=1&ee=" + $(".bgCurNum").attr("id") ;
                                $("#ee").attr("href",Maintain);
                            }else{
                                // 切换传值 （de=0 初始，de=1 切换，nc=1 非常数，nc=0 常数）
                                Maintain = "dictionaryEditor.html?nc=0&de=1&ee=" + $(".bgCurNum").attr("id") ;
                                $("#ee").attr("href",Maintain);
                            }
                        })

                    })


                }
            });
        }

        // 右侧数据交互功能
        function dictionaryRight(sendDate,nameID,dd){

            var addHtmlTh = '';
            var addHtmlTrA = '';
            var addHtmlTrB = '';
            var addHtmlPage = '';

            if(dd==1){
                sendDate['currentpage']
            }else{
                sendDate['currentpage'] = nowPageNumber;
            }
            // alert(JSON.stringify(sendDate));
            //console.log(nowPageNumber)
            $.ajax({
                type:"POST",
                // url:portconfig+"dictionary/test",
                url:"http://119.29.234.184:8080/hxsg2.0/hxsgAdmin/getDetail",
                data:sendDate,
                dataType : 'jsonp',
                jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                success: function(json){
                    console.log(json)
                    var dictionarydetail=json.chinacloum;
                    var englishcloum = json.englishcloum;
                    var dictionarydetailVal = json.values;
                    var costomid = json.costomid;
                    if(englishcloum == null){
                        js_dictionary.find("p").remove();
                        js_dictionarydetail_td.html("");
                        js_dictionarydetail_th.html("");
                        js_dictionary.find("h5").remove();
                        js_dictionary.append('<h5 class="no_data">该条信息字典编辑填写有误，未能查询到相关内容！</h5>');
                    }else{
                        // 增加模板
                        var addOnlyTD = '';
                        for(var d=0;d<dictionarydetail.length;d++){
                            addOnlyTD += '<td>' + '<span class="w320T addPad inputText">' +'<input type="text" class="inputVal" />' + '</span>' + '</td>';
                        }
                        addOnlyTR = '<tr class="inSetTB click addSingel">' + addOnlyTD
                            + '<td class="operaCenter">'+ '<span class="w160T">'
                            + '<a href="javascript:;" class="save" id="save" value="insert">' + '</a>'
                            + '<a href="javascript:;" class="close">' + '</a>'
                            + '<a href="javascript:;" class="write">' + '编辑' + '</a>'
                            + '<a href="javascript:;" class="addTR" value="insert">' + '添加' + '</a>'
                            + '<a href="javascript:;" class="del" id="del" value="upanddel">' + '删除' + '</a>'
                            +'</span>'
                            + '</td>'
                            + '</tr>'

                        // 字典明细 右边表头
                        for(var j=0;j<dictionarydetail.length;j++){
                            addHtmlTh += '<th value="'+ englishcloum[j] +'">' + '<span class="w320T addPad">' + dictionarydetail[j] + '</span>' + '</th>'
                        }
                        addHtmlTrA = '<tr alt="'+ costomid +'" type="'+englishcloum[0]+'" value="'+englishcloum[1]+'">' + addHtmlTh
                            + '<th>' + '<span class="w160T">' +'操作'+ '</span>' + '</th>'
                            + '</tr>'

                        // 字典明细 右边表格内容
                        for(var n=0;n<dictionarydetailVal.length;n++){
                            //console.log(dictionarydetailVal[n].center_city);
                            var addHtmlTd = '';
                            for(var key in englishcloum){
                                if(dictionarydetailVal[n][englishcloum[key]]==null){
                                    dictionarydetailVal[n][englishcloum[key]]="";
                                }
                                //var key=englishcloum[m];
                                console.log( dictionarydetailVal[n][englishcloum[key]])
                                addHtmlTd += '<td>' +'<span class="w320T addPad inputText">' +dictionarydetailVal[n][englishcloum[key]] + '</span>' + '</td>'
                            }
                            addHtmlTrB +='<tr alt="'+ dictionarydetailVal[n][0] +'" value="'+ dictionarydetailVal[n][1] +'">' + addHtmlTd
                                + '<td class="operaCenter">' + '<span class="w160T">'
                                + '<a href="javascript:;" class="save savefn" value="upanddel">' + '</a>'
                                + '<a href="javascript:;" class="close">' + '</a>'
                                + '<a href="javascript:;" class="write">' + '编辑' + '</a>'
                                + '<a href="javascript:;" class="addTR" value="insert">' + '添加' + '</a>'
                                / + '<a href="javascript:;" class="del delfn" value="upanddel">' + '删除' + '</a>'
                                +'</span>'
                                + '</td>'
                                + '</tr>'
                        }
                        //console.log(addHtmlTd)
                        js_dictionarydetail_th.html("");
                        js_dictionarydetail_th.append(addHtmlTrA);
                        js_dictionarydetail_td.html("");
                        js_dictionarydetail_td.append(addHtmlTrB);

                        if(addHtmlTrB != ''){
                            /* 26160711 修改 start */
                            js_dictionary.find("h5").remove();
                            js_dictionary.find("p").remove();
                            js_pageList.show();
                        }else{
                            js_dictionary.find("p").remove();
                            js_dictionary.find("h5").remove();
                            js_dictionarydetail_td.html("");
                            js_dictionarydetail_th.html("");
                            js_pageList.hide();
                            js_dictionary.append('<p class="no_data">对不起！字典明细暂无数据！<a href="#" id="js_addData" style="color:#7ba0cf;" class="ml15 gestures">增加数据</a></p>');
                            /* 26160711 修改 end */

                            /* 26160905 增加 start */
                            js_dictionary.delegate("#js_addData","click",function(){
                                js_dictionary.find("p").remove();
                                js_dictionarydetail_th.html("");
                                js_dictionarydetail_th.append(addHtmlTrA);
                                js_dictionarydetail_td.html("");
                                js_dictionarydetail_td.append(addOnlyTR);

                                $(".write").hide();
                                $(".addTR").hide();
                                $(".del").hide();
                                $(".addSingel").find(".save").addClass("showInline");
                                $(".addSingel").find(".del").show();
                            });
                            /* 26160905 增加 end */
                        }

                        tableColor();

                        //分页注入
                        var totalPage = json.page.totalpage;             //总页数
                        var currentPage = json.page.currentpage;         //当前页码

                        nowPageNumber = currentPage;
                        overPageNumber = totalPage;

                        js_pageList.html("")

                        if(totalPage >= 2){

                            addHtmlPage += '<a class="fenye_a homePage" href="javascript:void(0);">首页</a>' +
                                '<a class="fenye_a1 prePage" href="javascript:void(0);"> &lt; </a>' +
                                '<span class="fenye_span">' + currentPage + '/' + totalPage + '页</span>' +
                                '<a class="fenye_a2 nextPage" href="javascript:void(0);"> &gt; </a>' +
                                '<a class="fenye_a overPage" href="javascript:void(0);">尾页</a>';
                            js_pageList.html(addHtmlPage);
                        }

                        domainTable("js_dictionary_TB");
                    };
                }
            });
        };
        // 增删改查
        function domainTable(id){
            var pid = "#" + id + " ";
            //var costom2												// 单行ID
            var arr=[];													// 单行的所有值
            var arr1=[];												// 单行的所有中文key
            var arr2=[];												// 单行的所有英文文key
            var trueT = false;

            // 修改
            $(pid + "a.write").each(function(i){
                var _this = $(this);

                $(this).off("click").on("click",function(){

                    if(!trueT){
                        trueT = true;
                        var strTR = ""; 			// 被点击修改按钮的祖级（当前行）
                        var strTD = ""; 			// 被点击按钮的父级（包含其TD）

                        strTB = _this.closest("tbody");
                        strTR = _this.closest("tr");
                        strTD = _this.closest("td");

                        $(pid + ".write").hide();
                        $(pid + ".addTR").hide();
                        $(pid + ".del").hide();
                        strTD.find(".save").addClass("showInline");
                        strTD.find(".close").addClass("showInline");

                        strTR.siblings().removeClass("click");
                        strTR.addClass("click");

                        // 修改时判断key是一列还是两列
                        var len = strTR.find(".inputText").length;

                        for(var e=0;e<len;e++){
                            var tdSpan = strTR.find(".inputText").eq(e);
                            var text = strTR.find(".inputText").eq(e).text();
                            tdSpan.text("");
                            tdSpan.append('<input type="text" value="" class="inputVal"/>');
                            tdSpan.find("input[type='text']").val(text);
                        }
                    }

                })
            });

            // 增加
            $(pid + "a.addTR").each(function(i){
                //$(pid).delegate("a.addTR", "click", function(){
                var _this = $(this);

                $(this).off("click").on("click",function(){

                    var strTr = $(this).closest('tr');

                    strTr.removeClass("click");
                    strTr.siblings().removeClass("click");

                    $(pid).append(addOnlyTR);
                    $(pid + ".write").hide();
                    $(pid + ".addTR").hide();
                    $(pid + ".del").hide();
                    $(".addSingel").find(".save").addClass("showInline");
                    $(".addSingel").find(".del").show();
                    tableColor();
                });
            });

            // 关闭
            $(pid).undelegate("a.close", "click").delegate("a.close", "click", function(){
                //alert("OK");
                var i=0;
                var strTr = $(this).closest('tr');

                // 选中行中的各个td值得组合
                var lenSave = strTr.find(".inputText").length;

                // 将值填入对应的td中
                var a= 0;
                for(var k=0;k<lenSave;k++){
                    var newVals = strTr.find(".inputVal").eq(k).val();
                    arr.push(newVals);
                }

                strTr.find(".inputText").each(function(){
                    var newtexts = arr[a];
                    a++;
                    $(this).text(newtexts);
                });

                $(pid + ".save").removeClass("showInline");
                $(pid + ".close").removeClass("showInline");
                strTr.removeClass("click");
                $(pid + ".write").show();
                $(pid + ".addTR").show();
                $(pid + ".del").show();
                trueT = false;
                arr=[];
            });

            // 新增中的保存
            $(pid).undelegate("a#save", "click").delegate("a#save", "click", function(){
                // alert("OK");
                var strTr = $(this).closest('tr');
                var arr=[];

                // 按钮类型
                var typeBtn = $(this).attr("value");

                // 左侧字典的名字
                var namePK = $(".bgCurNum").attr("alt");

                // 选中行中的各个td值
                var lenSave = strTr.find(".inputText").length;
                for(var k=0;k<lenSave;k++){
                    var newVals = strTr.find(".inputVal").eq(k).val();
                    var newKeys = strTr.parent().siblings("thead").find("span").eq(k).text();
                    var newKeysE = strTr.parent().siblings("thead").find("th").eq(k).attr("value");
                    arr.push(newVals);
                    arr1.push(newKeys);
                    arr2.push(newKeysE);
                }

                // 每一行的PK值
                var costom2 = '';
                var costomid = strTr.parent().siblings("thead").find("tr").attr("alt");
                var arrPK = costomid.split(',');
                if(arrPK[1] == undefined){
                    var eqZero = arr2.indexOf(arrPK[0]);
                    costom2 = arr[eqZero];
                }else{
                    var eqZero = arr2.indexOf(arrPK[0]);
                    var eqOne= arr2.indexOf(arrPK[1]);
                    costom2 = arr[eqZero]+','+arr[eqOne];
                }

                successAll("保存","您确定要完成保存吗？");

                // 确认保存信息
                $(".btnBox").undelegate(".saveInfoBtn", "click").delegate(".saveInfoBtn", "click", function(event){

                    event.stopPropagation;
                    closePop(".infoUp", ".iframe_box");
                    loadingShow('white', 'loadingLong', '数据保存中... 请您稍等，谢谢！');
                    var saveData = {
                        "type":typeBtn,
                        "tablename":namePK,
                        "chinacloum":arr1,
                        "costom2":costom2,
                        "costom3":costomid,
                        "englishcloum":arr2,
                        "values":arr
                    }
                    console.log(saveData);

                    // 数据保存上传
                    $.ajax({
                        type:"POST",
                        //url:portconfig+"dictionary/test",
                        url:"http://127.0.0.1:8080/hxsgAdmin/getDetail",
                        data:saveData,
                        dataType : 'jsonp',
                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                        success: function(json){
                            var result = json.result;
                            if(json.result=="succes"){

                                setTimeout(function(){
                                    closeLoading('white', 'loadingLong');
                                    passAll("保存","保存成功！")
                                },1000);
                                //console.log("33333333333"+nameGlobal)
                                dictionaryRight(nameGlobal,nameGlobalID,3);
                            }else{

                                setTimeout(function(){
                                    closeLoading('white', 'loadingLong');
                                    errorsAll(result);
                                },1000);
                                dictionaryRight(nameGlobal,nameGlobalID,3);
                            }
                        }
                    });
                });

                $(pid + ".save").removeClass("showInline");
                $(pid + ".close").removeClass("showInline");
                $(pid + ".write").show();
                $(pid + ".addTR").show();
                $(pid + ".del").show();
                trueT = false;
            });

            // 页面上的保存
            $(pid + "a.savefn").off("click").on("click", function(){
                var strTr = $(this).closest('tr');
                var arr=[];

                // 按钮类型
                typeBtn = $(this).attr("value");

                // 左侧字典的名字
                var namePK = $(".bgCurNum").attr("alt");

                // 选中行中的各个td值得组合
                var lenSave = strTr.find(".inputText").length;
                for(var k=0;k<lenSave;k++){
                    var newVals = strTr.find(".inputVal").eq(k).val();
                    var newKeys = strTr.parent().siblings("thead").find("span").eq(k).text();
                    var newKeysE = strTr.parent().siblings("thead").find("th").eq(k).attr("value");
                    arr.push(newVals);
                    arr1.push(newKeys);
                    arr2.push(newKeysE);
                }

                // 每一行的PK值
                var costom2 = '';
                var costomid = strTr.parent().siblings("thead").find("tr").attr("alt");
                var arrPK = costomid.split(',');
                if(arrPK[1] == undefined){
                    var eqZero = arr2.indexOf(arrPK[0]);
                    costom2 = arr[eqZero];
                }else{
                    var eqZero = arr2.indexOf(arrPK[0]);
                    var eqOne= arr2.indexOf(arrPK[1]);
                    costom2 = arr[eqZero]+','+arr[eqOne];
                }

                successAll("保存","您确定要完成保存吗？");

                // 确认保存信息
                $(".btnBox").undelegate(".saveInfoBtn", "click").delegate(".saveInfoBtn", "click", function(event){

                    event.stopPropagation;

                    closePop(".infoUp", ".iframe_box");
                    loadingShow('white', 'loadingLong', '数据保存中... 请您稍等，谢谢！');
                    saveData = {
                        "type":typeBtn,
                        "tablename":namePK,
                        "chinacloum":arr1,
                        "costom2":costom2,
                        "costom3":costomid,
                        "englishcloum":arr2,
                        "values":arr
                    }
                    console.log(saveData);
                    // 数据保存上传
                    $.ajax({
                        type:"POST",
                        //url:portconfig+"dictionary/test",
                        url:"http://127.0.0.1:8080/hxsgAdmin/getDetail",
                        data:saveData,
                        dataType : 'jsonp',
                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                        success: function(json){
                            if(json.result=="succes"){

                                setTimeout(function(){
                                    closeLoading('white', 'loadingLong');
                                    passAll("保存","保存成功！");
                                },1000);
                                console.log(nameGlobal)
                                dictionaryRight(nameGlobal,nameGlobalID,3);
                            }else{
                                dictionaryRight(nameGlobal,nameGlobalID,3);
                                setTimeout(function(){
                                    var result = json.result;
                                    closeLoading('white', 'loadingLong');
                                    errorsAll(result);
                                },1000);
                            }
                        }
                    });
                });

                $(pid + ".save").removeClass("showInline");
                $(pid + ".close").removeClass("showInline");
                $(this).removeClass("click");
                $(pid + ".write").show();
                $(pid + ".addTR").show();
                $(pid + ".del").show();
                trueT = false;
            });

            // 新增中的删除
            $(pid).undelegate('a#del','click').delegate('a#del','click', function() {

                var strTr = $(this).closest('tr');
                delAll("删除","您确定要删除吗？");

                /* 20160905 修改 start*/
                $(".delInfoBtn").off("click").on("click", function(event){
                    if(strTr.siblings("tr").length == 0){
                        js_dictionary.find("p").remove();
                        js_dictionarydetail_td.html("");
                        js_dictionarydetail_th.html("");
                        js_pageList.hide();
                        js_dictionary.append('<p class="no_data">对不起！字典明细暂无数据！<a href="#" id="js_addData" style="color:#7ba0cf;" class="ml15 gestures">增加数据</a></p>');
                        closePop(".infoUp", ".iframe_box");
                    }else{
                        strTr.remove();
                        $(pid + "a.write").show();
                        $(pid + "a.addTR").show();
                        $(pid + "a.del").show();
                        closePop(".infoUp", ".iframe_box");
                    }
                });
                /* 20160905 修改 end*/
                trueT = false;
            });

            // 页面上的删除（有数据传输）
            $(pid + "a.delfn").off("click").on('click', function() {

                event.stopPropagation;

                var strTr = $(this).closest('tr');
                var arr=[];

                // 按钮类型
                typeBtn = $(this).attr("value");

                // 左侧字典的名字
                var namePK = $(".bgCurNum").attr("alt");

                // 选中行中的各个td值得组合
                var lenSave = strTr.find(".inputText").length;
                for(var k=0;k<lenSave;k++){
                    var newVals = strTr.find(".inputText").eq(k).text();
                    var newKeys = strTr.parent().siblings("thead").find("span").eq(k).text();
                    var newKeysE = strTr.parent().siblings("thead").find("th").eq(k).attr("value");
                    arr.push(newVals);
                    arr1.push(newKeys);
                    arr2.push(newKeysE);
                }

                // 每一行的PK值
                var costom2 = '';
                var costomid = strTr.parent().siblings("thead").find("tr").attr("alt");
                var arrPK = costomid.split(',');
                if(arrPK[1] == undefined){
                    var eqZero = arr2.indexOf(arrPK[0]);
                    costom2 = arr[eqZero];
                }else{
                    var eqZero = arr2.indexOf(arrPK[0]);
                    var eqOne= arr2.indexOf(arrPK[1]);
                    costom2 = arr[eqZero]+','+arr[eqOne];
                }

                delAll("删除","您确定要删除吗？");
                // 删除信息
                $(".delInfoBtn").off("click").on("click", function(event){

                    event.stopPropagation;
                    closePop(".infoUp", ".iframe_box");
                    loadingShow('white', 'loadingLong', '数据删除中... 请您稍等，谢谢！');

                    delData = {
                        "type":typeBtn,
                        "tablename":namePK,
                        "chinacloum":arr1,
                        "costom2":costom2,
                        "costom3":costomid,
                        "englishcloum":arr2,
                        "values":arr,
                        "isdeleted":"1"
                    }
                    // console.log(delData);
                    // alert(namePK+"/"+costom2+"/"+arrOneTitle+"/"+arrTwoTitle+"/"+arr);

                    // 数据删除上传
                    $.ajax({
                        type:"POST",
                        //url:portconfig+"dictionary/test",
                        url:"http://127.0.0.1:8080/hxsgAdmin/getDetail",
                        data:delData,
                        dataType : 'jsonp',
                        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                        success: function(json){
                            var result = json.result;
                            if(json.result=="succes"){
                                dictionaryRight(nameGlobal,nameGlobalID,3);
                                setTimeout(function(){
                                    closeLoading('white', 'loadingLong');
                                    passAll("删除","删除成功！")
                                },1000);
                            }else{
                                setTimeout(function(){
                                    closeLoading('white', 'loadingLong');
                                    errorsAll(result)
                                },1000);
                            }

                        }
                    });
                });
                trueT = false;
            });
        };

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