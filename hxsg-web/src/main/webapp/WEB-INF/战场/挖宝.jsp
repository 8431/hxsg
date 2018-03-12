<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
   
    
    <title>战场</title>
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
    border:1px #ff1097;

}

.yaoping{
color: #eeff84;


width:299px;

text-indent:20px;

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


.sy{
    margin-left: 20px;
    font-size: 25px;
    height:30px;
    line-height: 30px;
border:2px solid #ff0000;
background:  #e016ff;
color: #dddaff;
}
.wbmsg{
    border:1px #ff1097;
    height: 220px;
    width: 300px;

}
.inputks{
    background: #ece39d;
    width:300px;
    height: 100px;

    margin-left: 23px;
    position: fixed;
    top:150px;
    left:10px;
    display:none;
    text-indent: 10px;
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
.queding5{

    position: absolute;
    top:60px;
    left:30px;
    width:50px;
    height: 20px;
    line-height: 20px;
    border:2px solid #ff0000;
    background:#ff9e00;
    color:#ff0000;
    display: block;
}
.quxiao{

    position: absolute;
    top:60px;
    left:220px;
    border:2px solid #ff0000;
    background:#ff9e00;
    color:#ff0000;
    width:50px;
    height: 20px;

    display: block;
}
</style>
  </head>
  
  <body>


  <div class="kj" >
 <div class="roleid"><b>矿洞</b></div>
   <div style="border:0px solid #ff0000; width:100%;">

  <br/>
       <c:if test="${wb.status=='1'}">
       <p class="yaoping" >剩余挖宝次数${wb.num}</p>
       <input type="hidden" id="wbid" value="${wb.id}"/>
       <p class="yaoping" style="color: #d8ff21" id="tishi">点击挖宝按钮，挖开属于你的宝藏</p>
 		<hr/>
      <p class="yaoping">

       <c:if test="${wb.w1==0}"><span style="background:#000031"  id="w1" class="sy" >挖宝</span></c:if>
       <c:if test="${wb.w1==1}"> <span style="background:#e016ff " onclick="wabao1(${wb.w1})" onmousemove="mouse(1)" id="w1" class="sy">挖宝</span></c:if>
       <c:if test="${wb.w2==0}"><span style="background:#000031"  id="w2" class="sy" >挖宝</span></c:if>
       <c:if test="${wb.w2==1}"> <span style="background:#e016ff " onclick="wabao2(${wb.w2})" onmousemove="mouse(2)" id="w2" class="sy">挖宝</span></c:if>
          <c:if test="${wb.w3==0}"><span style="background:#000031"  id="w3" class="sy" >挖宝</span></c:if>
          <c:if test="${wb.w3==1}"> <span style="background:#e016ff " onclick="wabao3(${wb.w3})" onmousemove="mouse(3)" id="w3" class="sy">挖宝</span></c:if>
      </p>
       <p class="yaoping">
          <c:if test="${wb.w4==0}"><span style="background:#000031"  id="w4" class="sy" >挖宝</span></c:if>
          <c:if test="${wb.w4==1}"> <span style="background:#e016ff " onclick="wabao4(${wb.w4})" onmousemove="mouse(4)" id="w4" class="sy">挖宝</span></c:if>
          <c:if test="${wb.w5==0}"><span style="background:#000031"  id="w5" class="sy" >挖宝</span></c:if>
          <c:if test="${wb.w5==1}"> <span style="background:#e016ff " onclick="wabao5(${wb.w5})" onmousemove="mouse(5)" id="w5" class="sy">挖宝</span></c:if>
          <c:if test="${wb.w6==0}"><span style="background:#000031"  id="w6" class="sy" >挖宝</span></c:if>
          <c:if test="${wb.w6==1}"> <span style="background:#e016ff " onclick="wabao6(${wb.w6})" onmousemove="mouse(6)" id="w6" class="sy">挖宝</span></c:if>
          </p>
       <p class="yaoping">
           <c:if test="${wb.w7==0}"><span style="background:#000031"  id="w7" class="sy" >挖宝</span></c:if>
          <c:if test="${wb.w7==1}"> <span style="background:#e016ff " onclick="wabao7(${wb.w7})" onmousemove="mouse(7)" id="w7" class="sy">挖宝</span></c:if>
          <c:if test="${wb.w8==0}"><span style="background:#000031"  id="w8" class="sy" >挖宝</span></c:if>
          <c:if test="${wb.w8==1}"> <span style="background:#e016ff " onclick="wabao8(${wb.w8})" onmousemove="mouse(8)" id="w8" class="sy">挖宝</span></c:if>
          <c:if test="${wb.w9==0}"><span style="background:#000031"  id="w9" class="sy" >挖宝</span></c:if>
          <c:if test="${wb.w9==1}"> <span style="background:#e016ff " onclick="wabao9(${wb.w9})" onmousemove="mouse(9)" id="w9" class="sy">挖宝</span></c:if>

      </p>

       <c:if test="${wb.w1==0&&wb.w2==0&&wb.w3==0&&wb.w4==0&&wb.w5==0&&wb.w6==0&&wb.w7==0&&wb.w8==0&&wb.w9==0}">
       <p class="yaoping"><span id="sxwb" class="sy">继续挖宝</span></p>
       </c:if>
      <hr/>
  </div>
      </c:if>
<c:if test="${wb.status=='0'||wb==null}">

    <p class="yaoping" style="color: #d8ff21" id="tishi">宝藏守卫:想挖宝吗？嘿嘿。来我这里买要铁镐（100000两）。我是良心商人。</p>
    <p class="yaoping" style="color: #d8ff21" id="tishi"><span id="gmtg" class="sy">购买</span></p>
</c:if>
      <div class="wbmsg" style="overflow-Y: auto;overflow-X:hidden;width:320px;">

      </div>
</div>
  <%--通用消息框--%>
  <div class="inputks">
      <p id="tst" style="color:#ff2821 "></p>
      <div id= "q1" class="queding5">确定</div>

      <div  class="quxiao">取消</div>
  </div>
  <jsp:include page="../common/kj.jsp"/>
  </body>
<script type="text/javascript">
    //刷新挖宝得住信息

    function sxmsg(){
        $.ajax({
            type: "POST",
            url: "<%=basePath%>zhanchang/wdbw",
            data:"",
            success: function(msg){

                var jg=JSON.parse(msg);  //使用这个方法解析json
                var p=jg.rmsg;

                var s="";
                if(jg.code==0){

                    for(var i=0;i<p.length;i++){

                        var d= new Date(p[i].date);
                        var hh=d.getHours();
                        var mm= d.getMinutes();

                        if(mm<10){
                            mm="0"+mm;
                        }
                        if(hh<10){
                            hh="0"+hh;
                        }

                        var times=hh+":"+mm;


                        var	a="<p style='text-indent:10px;font-weight:bold;color:#ffffff;fontsize:10px;'><span >"+times+"</span>&nbsp;<font style='color:#FFEE1D'>"+p[i].rolename+"</font>:<span>挖到【<font color='#ff9e00'>"+p[i].baoname+"</font>】</span></p>";

                        s=s+a;

                    }
                }

                $(".wbmsg").html(s);
                //alert(s);



            }

        });
        window.setTimeout(sxmsg,2000);
    }
    window.setTimeout(sxmsg,50);
    var numid=null;
    function mouse(nm){
        numid=nm;
    }
var wbmsgs=null;
    var num1=0;
    var num2=0;
    var num3=0;
    var num4=0;
    var num5=0;
    var num6=0;
    var num7=0;
    var num8=0;
    var num9=0;
function wabao1(num){
        if(num==1&&num1==0){
            num1=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "POST",
                url: "<%=basePath%>zhanchang/xhcs",
                data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                success: function (msg) {
                    var mg=JSON.parse(msg);  //使用这个方法解析json
                    var msg=mg.msg;
                    var sxmsg=mg.sxmsg;
                    //alert(sxmsg);
                    if(sxmsg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
////////////////////////////////////////////////////
    function wabao2(num){
        if(num==1&&num2==0){
            num2=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "POST",
                url: "<%=basePath%>zhanchang/xhcs",
                data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                success: function (msg) {
                    var mg=JSON.parse(msg);  //使用这个方法解析json
                    var msg=mg.msg;
                    var sxmsg=mg.sxmsg;
                    //alert(sxmsg);
                    if(sxmsg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
////////////////////////////////
    function wabao3(num){
        if(num==1&&num3==0){
            num3=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "POST",
                url: "<%=basePath%>zhanchang/xhcs",
                data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                success: function (msg) {
                    var mg=JSON.parse(msg);  //使用这个方法解析json
                    var msg=mg.msg;
                    var sxmsg=mg.sxmsg;
                    //alert(sxmsg);
                    if(sxmsg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
    function wabao4(num){
        if(num==1&&num4==0){
            num4=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "POST",
                url: "<%=basePath%>zhanchang/xhcs",
                data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                success: function (msg) {
                    var mg=JSON.parse(msg);  //使用这个方法解析json
                    var msg=mg.msg;
                    var sxmsg=mg.sxmsg;
                    //alert(sxmsg);
                    if(sxmsg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
    function wabao5(num){
        if(num==1&&num5==0){
            num5=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "POST",
                url: "<%=basePath%>zhanchang/xhcs",
                data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                success: function (msg) {
                    var mg=JSON.parse(msg);  //使用这个方法解析json
                    var msg=mg.msg;
                    var sxmsg=mg.sxmsg;
                    //alert(sxmsg);
                    if(sxmsg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
    function wabao6(num){
        if(num==1&&num6==0){
            num6=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "POST",
                url: "<%=basePath%>zhanchang/xhcs",
                data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                success: function (msg) {
                    var mg=JSON.parse(msg);  //使用这个方法解析json
                    var msg=mg.msg;
                    var sxmsg=mg.sxmsg;
                    //alert(sxmsg);
                    if(sxmsg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
    function wabao7(num){
        if(num==1&&num7==0){
            num7=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "POST",
                url: "<%=basePath%>zhanchang/xhcs",
                data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                success: function (msg) {
                    var mg=JSON.parse(msg);  //使用这个方法解析json
                    var msg=mg.msg;
                    var sxmsg=mg.sxmsg;
                    //alert(sxmsg);
                    if(sxmsg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
    function wabao8(num){
        if(num==1&&num8==0){
            num8=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "POST",
                url: "<%=basePath%>zhanchang/xhcs",
                data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                success: function (msg) {
                    var mg=JSON.parse(msg);  //使用这个方法解析json
                    var msg=mg.msg;
                    var sxmsg=mg.sxmsg;
                    //alert(sxmsg);
                    if(sxmsg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
    function wabao9(num){
        if(num==1&&num9==0){
            num9=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "POST",
                url: "<%=basePath%>zhanchang/xhcs",
                data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                success: function (msg) {
                    var mg=JSON.parse(msg);  //使用这个方法解析json
                    var msg=mg.msg;
                    var sxmsg=mg.sxmsg;
                    //alert(sxmsg);
                    if(sxmsg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }

    $(function(){
       $("#sxwb").click(function(){
          // $("#sxwb").unbind("click");
           var id=$("#wbid").val();
           $.ajax({
               type: "POST",
               url: "<%=basePath%>zhanchang/sxwb",
               data:{id:id},
               success: function (msg) {
                   window.location.reload();
         }
           });

       });

       $("#gmtg").click(function(){

           $.ajax({
               type: "POST",
               url: "<%=basePath%>zhanchang/yzwb",
               data:"",
               success: function (msg) {
                   var mg=JSON.parse(msg);  //使用这个方法解析json
                   var msg=mg.msg;

                   $(".inputks").css({"display":"block"});
                   $("#tst").text(msg);



               }
           });

       });
       $(".quxiao").click(function(){
           $(".inputk").css({"display":"none"});
           $(".queding5").css({"display":"none"});
           $(".quxiao").css({"display":"none"});
           $("#tst").text("");
           window.location.reload();

       });
       $("#q1").click(function(){
           $.ajax({
               type: "POST",
               url: "<%=basePath%>zhanchang/wabao",
               data:"",
               success: function (msg) {
                   var mg=JSON.parse(msg);  //使用这个方法解析json
                   var msg=mg.msg;


                   $("#tst").text(msg);



               }
           });
       });


   });

</script>
</html>
