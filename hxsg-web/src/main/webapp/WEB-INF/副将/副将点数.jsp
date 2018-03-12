<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>


    <title>My JSP '????.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="<%=basePath%>jQuery/jquery-1.4.2.js"></script>
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style type="text/css">
        .kj{
            background:#000031;
            width:299px;
            height:505px;
            position:fixed;
            left:32px;
            top:35px;
            color:#ffffff;

        }

        .yaoping{
            color:#ff0000;
            line-height:30px;
            height:30px;
            width:299px;
            background:#634163;
            text-indent:0px;

        }
        .roleid{
            position:fixed;
            top:35px;
            left:33px;
            border:0px solid #ff0000;
            background:#000031;
            color:#FF9998;
            text-align:center;

            width:300px;
            height:30px;
            line-height:30px;

        }
        a{
            text-decoration:none;
            color:#FFFF00;
        }

        li{
            float:left;
            width:50px;
            background:#E1B205;
            margin-left:5px;
            text-align:center;
            color:#B520BD;
            font-size:20px;
        }

        .sy{

              font-size:20px;
              margin-left:20px;
              border:2px solid #ff0000;
              background: #fff823;
              color: #ff171a;
              font-weight: bold;
          }
        .sy2{

            font-size:25px;

            border:0px solid #ff0000;
            background:#B520BD;
            color:#ffffff;
        }
        .photo{

            position:fixed;

            top:70px;
            left:250px;

            border:0px solid #ff0000;
        }
        .roleid{
            font-size: 25px;
            position:fixed;
            top:34px;
            left:0px;
            float:left;
            background:#000031;
            color:#FF9998;
            text-indent:50px;

            width:320px;
            height:30px;

        }
        .rolename{
            color:#ffffff;
            text-indent:40px;

            font-size: 20px;


        }
        .inputks{
            background: #8c7b25;
            width:340px;
            height: 100px;
            line-height: 60px;

            position: fixed;
            top:150px;
            left:10px;
            display:none;
            text-align: center;
            size:4;
            font-weight: bold;
            color: #fff9f8;
            display: none;

        }

        .quxiaos{
            text-align: center;
            position: absolute;
            top:70px;
            left:140px;
            border:2px solid #ff0000;
            background:#ff9e00;
            color:#ff0000;
            width:50px;
            height: 20px;
            line-height:20px;
            display: block;
        }
    </style>
</head>

<body>





<div class="kj"style="overflow-Y: auto;overflow-X:hidden;width:320px;">

<div class="roleid"><b>${rf.fujiangname}属性点</b></div>
<div style="border:0px solid #ff0000; width:270px;">

<br/> <br/>

    <p class="rolename"> 目前剩余点数:<span  id="kyds" >${rf.keyongds}</span></p>
    <c:if test="${rf.keyongds>0}">
        <input type="hidden" id="cz" value="${rf.chengzhang}"/>
        <input type="hidden" id="chuxue" value="${rf.chuxue}"/>
        <input type="hidden" id="chujing" value="${rf.chujing}"/>
        <input type="hidden" id="chusu" value="${rf.chusu}"/>
        <input type="hidden" id="chugong" value="${rf.chugong}"/>
        <input type="hidden" id="leve" value="${rf.leve}"/>

        <p class="rolename">气血: <span id="qxz">${rf.totalxue2}</span></p>
        <p class="rolename">体质点: <span id="tzd">${rf.qixueds}</span>
            <input id="tz" type="number" name="quantity" min="1" max="${rf.keyongds}"style="background: #9cf8ff;"> <input id="fptz" type="button" style="background: #ff1097;color:#ffffff"value="分配"/> </p>

        <p class="rolename">精力: <span id="jlz">${rf.totaljing2}</span></p>
        <p class="rolename">智力点: <span id="zld">${rf.jinglids}</span>  <input id="zl" type="number" name="quantity" min="1" max="${rf.keyongds}"style="background: #9cf8ff;"> <input id="fpzl"type="button" style="background: #ff1097;color:#ffffff"value="分配"/> </p>

       <p class="rolename">攻击: <span id="gjz">${rf.totalgong}</span></p>
        <p class="rolename">力量点: <span id="lld">${rf.gongjids}</span>  <input id="ll" type="number" name="quantity" min="1" max="${rf.keyongds}"style="background: #9cf8ff;"> <input id="fpll" type="button" style="background: #ff1097;color:#ffffff"value="分配"/></p>

        <p class="rolename">速度: <span id="sdz">${rf.totalsudu}</span></p>
        <p class="rolename">敏捷点: <span id="mjd">${rf.sududs}</span>  <input id="mj" type="number" name="quantity" min="1" max="${rf.keyongds}" style="background: #9cf8ff;"> <input id="fpmj" type="button" style="background: #ff1097;color:#ffffff"value="分配"/> </p>
        <p class="rolename"><span class="sy" id="qdfp" >确定分配</span> <span id="cxfp" class="sy" >重新分配</span></p>

    <input type="hidden" id="fid" value="${rf.id}"/>
    <input type="hidden" id="fuid" value="${rf.fuid}"/>
    </c:if>
<c:if test="${rf.keyongds==0}">
    <p class="rolename">气血: <span id="qx">${rf.totalxue2}</span></p>
    <p class="rolename">体质点:${rf.qixueds}  </p>

    <p class="rolename">精力: <span id="jl">${rf.totaljing2}</span></p>
    <p class="rolename">智力点:${rf.jinglids}  </p>

    <p class="rolename">攻击: <span id="gj">${rf.totalgong}</span></p>
    <p class="rolename">力量点:${rf.gongjids}   </p>

    <p class="rolename">速度: <span id="sd">${rf.totalsudu}</span></p>
    <p class="rolename">敏捷点:${rf.sududs}  </p>
</c:if>

</div>


</div>
<%--通用消息框--%>
<div class="inputks">
    <p id="tst" style="color:#ff2821 "></p>
    <div class="quxiaos">确定</div>
</div>
<jsp:include  page="../common/kj.jsp"/>
</body>
<script type="text/javascript" >
$(function() {
    <%--通用消息框--%>
    $(".quxiaos").click(function(){
        $(".inputks").css({"display":"none"});
        $(".quxiaos").css({"display":"none"});
        $("#tst").text("");

    });
    <%--通用消息框--%>

    var kyds=parseInt($("#kyds").text());
    $("#cxfp").click(function(){

        window.location.reload();//刷新当前页面.
    });
    var cz=parseFloat($("#cz").val());
    var level=parseInt($("#leve").val());

    var chuxue=parseInt($("#chuxue").val());
    var chujing=parseInt($("#chujing").val());
    var chugong=parseInt($("#chugong").val());
    var chusu=parseInt($("#chusu").val());

    $("#fptz").click(function(){

        var a=$("#tz").val();
        if(a>0&&parseInt($("#kyds").text())>0){
            var b= $("#kyds").text()-a;
            kyds=$("#kyds").text(b);
            var tzd= parseInt($("#tzd").text())+parseInt(a);
            $("#tzd").text(tzd );
            
            $("#qxz").text( Math.round(cz*level*(tzd+chuxue*0.8)));
            //alert(Math.round(cz*level(tzd+chuxue*0.8)));
            $("#tz").val("");
        }else{
            $(".inputks").css({"display":"block"});
            $(".quxiaos").css({"display":"block"});
            $("#tst").text("你的属性点已分配完！");
        }

    });
    $("#fpzl").click(function(){
        var a=$("#zl").val();
        if(a>0&&parseInt($("#kyds").text())>0){
           var b= $("#kyds").text()-a;
            kyds=$("#kyds").text(b);
            kyds=$("#kyds").text(b);
            var zld= parseInt($("#zld").text())+parseInt(a);
            $("#zld").text(zld );
            $("#jlz").text( Math.round(cz*level*(zld+chujing*0.8)));
            $("#zl").val("");
        }else{
            $(".inputks").css({"display":"block"});
            $(".quxiaos").css({"display":"block"});
            $("#tst").text("你的属性点已分配完！");
        }
    });
    $("#fpll").click(function(){
        var a=$("#ll").val();
        if(a>0&&parseInt($("#kyds").text())>0){
            var b= $("#kyds").text()-a;
            kyds=$("#kyds").text(b);
            var lld= parseInt($("#lld").text())+parseInt(a);
            $("#lld").text(lld );
            $("#gjz").text( Math.round((level*cz*chugong)/7+cz*chugong*0.5+level*cz*lld*0.2));
            $("#ll").val("");
        }else{
            $(".inputks").css({"display":"block"});
            $(".quxiaos").css({"display":"block"});
            $("#tst").text("你的属性点已分配完！");
        }
    });
    $("#fpmj").click(function(){
        var a=$("#mj").val();
        if(a>0&&parseInt($("#kyds").text())>0){
            var b= $("#kyds").text()-a;
            kyds=$("#kyds").text(b);
            var mjd= parseInt($("#mjd").text())+parseInt(a);
            $("#mjd").text(mjd );

            $("#sdz").text(parseInt(cz*(mjd+chusu)));
            $("#mj").val("");
        }else{
            $(".inputks").css({"display":"block"});
            $(".quxiaos").css({"display":"block"});
            $("#tst").text("你的属性点已分配完！");
        }
    });
//确定分配
    $("#qdfp").click(function(){
        var  sz=$("#sdz").text()
        var  qz=$("#qxz").text();
        var  jz=$("#jlz").text();
        var  gz=$("#gjz").text();
        var  ks=$("#kyds").text();
        var  fid=$("#fid").val();
        var qxds=$("#tzd").text();
        var zlds=$("#zld").text();
        var gjds=$("#lld").text();
        var sdds=$("#mjd").text();
        //alert(sz);alert(qz);alert(jz);alert(gz);alert(ks);
        $.ajax({
            type: "POST",
            url: "<%=basePath%>fujiang/fpsx",
            data:{id:fid,keyongds:ks,totalxue2:qz,qixueds:qxds,jinglids:zlds,gongjids:gjds,sududs:sdds,totaljing2:jz,totalgong:gz,totalsudu:sz},
            success: function(msg) {
                $(".inputks").css({"display":"block"});
                $(".quxiaos").css({"display":"block"});
                $("#tst").text("恭喜你分配成功！");
            }
        });

    });

});

</script>
</html>
