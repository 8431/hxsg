<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include  page="../common/tag.jsp"/>
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

              font-size:25px;
              margin-left:40px;
              border:0px solid #ff0000;
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
        .inputk{
            background: #dfe5dd;
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

        .quxiao{
            text-align: center;
            position: absolute;
            top:60px;
            left:240px;
            border:2px solid #ff0000;
            background:#ff9e00;
            color:#ff0000;
            width:50px;
            height: 20px;
            line-height:20px;
            display: none;
        }
        .queding5{
            text-align: center;
            position: absolute;
            top:60px;
            left:50px;
            width:50px;
            height: 20px;
            line-height: 20px;
            border:2px solid #ff0000;
            background:#ff9e00;
            color:#ff0000;
            display: none;
        }
    </style>
</head>

<body>





<div class="kj"style="overflow-Y: auto;overflow-X:hidden;width:320px;">

<div class="roleid"><b>初值培养</b></div>
<div style="border:0px solid #ff0000; width:270px;">

<br/> <br/>
 <p class="rolename"> 副将:${rf.fujiangname}</p>
    <p class="rolename"> 该副将初始数据</p>
<p class="rolename">成长率: <span id="czlt">${rf.chengzhang}</span> <span class="sy" id="czl">培养</span> </p>
    <p class="rolename">气血: <span id="qxt">${rf.chuxue}</span>  <span class="sy" id="qx">培养</span> </p>
    <p class="rolename">精力: <span id="jlt">${rf.chujing}</span> <span class="sy" id="jl">培养</span> </p>
    <p class="rolename">攻击: <span id="gjt">${rf.chugong}</span> <span class="sy" id="gj">培养</span> </p>
    <p class="rolename">敏捷: <span id="mjt">${rf.chusu}</span> <span class="sy" id="mj">培养</span> </p>
<input type="hidden" id="fid" value="${rf.id}"/>
    <input type="hidden" id="fuid" value="${rf.fuid}"/>


</div>


    <p > <a href="<%=basePath%>fujiang/fjqk?id=${rf.fuid}"><span class="sy2" >该副将的最优数据</span> </a>  </p>
    <p > <span class="sy2">副将初值培养说明</span></p>
</div>
<div class="inputk">
    <p id="tst" style="color:#ff2821 "></p>
    <div id= "q5" class="queding5">确定</div>
    <div id= "q4" class="queding5">确定</div>
    <div id= "q3" class="queding5">确定</div>
    <div id= "q2" class="queding5">确定</div>
    <div id= "q1" class="queding5">确定</div>

    <div  class="quxiao">取消</div>
</div>
<jsp:include  page="../common/kj.jsp"/>


</body>
<script type="text/javascript" >
$(function(){
    $(".quxiao").click(function(){
        $(".inputk").css({"display":"none"});
        $(".queding5").css({"display":"none"});
        $(".quxiao").css({"display":"none"});

    });


    $("#czl").click(function(){

        $(".inputk").css({"display":"block"});
        $("#q5").css({"display":"block"});
        $(".quxiao").css({"display":"block"});
        $("#tst").text("你将花费50金培养副将？");

    });
    $("#q5").click(function(){

        var fid=$("#fid").val();
        var fuid=$("#fuid").val();
        $.ajax({
            type: "POST",
            url: "<%=basePath%>fujiang/pyfj",
            data:{id:fid,type:"成长",fuid:fuid},
            success: function (msg) {
                var mg=JSON.parse(msg);  //使用这个方法解析json
                var msg=mg.msg;
                var num=mg.num;


                if(msg!=null){

                    $("#tst").text("");
                    $("#tst").text(msg);
                }else{
                    $(".inputk").css({"display":"none"});
                    $("#q5").css({"display":"none"});
                    $(".quxiao").css({"display":"none"});
                }


                if(num!=null&&num!=0){
                    $("#czlt").text("");
                    $("#czlt").text(num);
                }


            }
        });

    });
    $("#q4").click(function(){

        var fid=$("#fid").val();
        var fuid=$("#fuid").val();
        $.ajax({
            type: "POST",
            url: "<%=basePath%>fujiang/pyfj",
            data:{id:fid,type:"气血",fuid:fuid},
            success: function (msg) {
                var mg=JSON.parse(msg);  //使用这个方法解析json
                var msg=mg.msg;
                var num=mg.num;

                if(msg!=null){

                    $("#tst").text("");
                    $("#tst").text(msg);
                }else{
                    $(".inputk").css({"display":"none"});
                    $("#q4").css({"display":"none"});
                    $(".quxiao").css({"display":"none"});
                }


                if(num!=null&&num!=0){
                    $("#qxt").text("");
                    $("#qxt").text(num);
                }



            }
        });

    });
    $("#q3").click(function(){

        var fid=$("#fid").val();
        var fuid=$("#fuid").val();
        $.ajax({
            type: "POST",
            url: "<%=basePath%>fujiang/pyfj",
            data:{id:fid,type:"精力",fuid:fuid},
            success: function (msg) {

                var mg=JSON.parse(msg);  //使用这个方法解析json
                var msg=mg.msg;
                var num=mg.num;

                if(msg!=null){
                   // alert(msg);
                    $("#tst").text("zzz");
                    $("#tst").text(msg);
                }else{
                    $(".inputk").css({"display":"none"});
                    $("#q3").css({"display":"none"});
                    $(".quxiao").css({"display":"none"});
                }


                if(num!=null&&num!=0){
                    $("#jlt").text("");
                    $("#jlt").text(num);
                }



            }
        });

    });
    $("#q2").click(function(){

        var fid=$("#fid").val();
        var fuid=$("#fuid").val();
        $.ajax({
            type: "POST",
            url: "<%=basePath%>fujiang/pyfj",
            data:{id:fid,type:"攻击",fuid:fuid},
            success: function (msg) {
                var mg=JSON.parse(msg);  //使用这个方法解析json
                var msg=mg.msg;
                var num=mg.num;

                if(msg!=null){

                    $("#tst").text("");
                    $("#tst").text(msg);
                }else{
                    $(".inputk").css({"display":"none"});
                    $("#q2").css({"display":"none"});
                    $(".quxiao").css({"display":"none"});
                }


                if(num!=null&&num!=0){
                    $("#gjt").text("");
                    $("#gjt").text(num);
                }



            }
        });

    });
    $("#q1").click(function(){

        var fid=$("#fid").val();
        var fuid=$("#fuid").val();
        $.ajax({
            type: "POST",
            url: "<%=basePath%>fujiang/pyfj",
            data:{id:fid,type:"敏捷",fuid:fuid},
            success: function (msg) {
                var mg=JSON.parse(msg);  //使用这个方法解析json
                var msg=mg.msg;
                var num=mg.num;

                if(msg!=null){

                    $("#tst").text("");
                    $("#tst").text(msg);
                }else{
                    $(".inputk").css({"display":"none"});
                    $("#q1").css({"display":"none"});
                    $(".quxiao").css({"display":"none"});
                }


                if(num!=null&&num!=0){
                    $("#mjt").text("");
                    $("#mjt").text(num);
                }



            }
        });

    });
    $("#qx").click(function(){
        $(".inputk").css({"display":"block"});
        $("#q4").css({"display":"block"});
        $(".quxiao").css({"display":"block"});
        $("#tst").text("你将花费50金培养副将？");
    });
    $("#jl").click(function(){
        $(".inputk").css({"display":"block"});
        $("#q3").css({"display":"block"});
        $(".quxiao").css({"display":"block"});
        $("#tst").text("你将花费50金培养副将？");
    });
    $("#gj").click(function(){
        $(".inputk").css({"display":"block"});
        $("#q2").css({"display":"block"});
        $(".quxiao").css({"display":"block"});
        $("#tst").text("你将花费50金培养副将？");
    });
    $("#mj").click(function(){
        $(".inputk").css({"display":"block"});
        $("#q1").css({"display":"block"});
        $(".quxiao").css({"display":"block"});
        $("#tst").text("你将花费50金培养副将？");
    });
});

</script>
</html>
