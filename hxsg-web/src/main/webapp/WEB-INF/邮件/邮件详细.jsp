<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/tag.jsp" %>
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
color: #f6ff9e;
line-height:30px;
height:30px;
width:299px;
background:#634163;
text-indent:10px;

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
margin-left:60px;
border:0px solid #ff0000;
background:#B520BD;
color:#ffffff;
}
.sy2{

    font-size:20px;
    margin-left:60px;
    border:0px solid #ff0000;
    background: #fff823;
    color: #ff454a;
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





<div class="kj" style="overflow-Y: auto;overflow-X:hidden;width:320px;">
 <div class="roleid"><b>邮件详细</b></div>
   <div style="border:0px solid #ff0000; width:100%;">
   
  <br/> <br/>
 		<hr/>
 		
        <input type="hidden" value="${yj.id}" id="yjid"/>
       <input type="hidden" value="${yj.roleid}" id="roleids"/>
 	   <p class="yaoping">发信人:${yj.rolename}(id:${yj.roleid})</p>
       <p class="yaoping">消息内容:${yj.message}</p>
       <p class="yaoping">时间:${yj.data}</p>
       <p class="yaoping">银两:${yj.yin}<span class="sy" id="lq">
           <c:if test="${yj.status=='已领取'}">已领取</c:if>
            <c:if test="${yj.status=='已读'}">领取</c:if>
           <c:if test="${yj.status=='0'}">领取</c:if>
       </span> </p>

    <c:if test="${liyjwp!=null}">
       <p class="yaoping">邮包附件</p>
           <c:forEach items="${liyjwp}" var="p">

               <c:if test="${p.yin==0}">
               <p class="yaoping" style="color:#ff7e1c;font-weight: bold" id="ts">${p.wupinnname}(${p.num}) <span class="sy2" id="tq" onclick="gmwp()" onmouseover="getnum(${p.id})">
                   <c:if test="${p.status=='已领取'}">已提取</c:if>
            <c:if test="${p.status=='已读'}">领取</c:if>
               </span></p><hr/>
               </c:if>
               <c:if test="${p.yin>0}">
                   <p class="yaoping" style="color:#ff7e1c;font-weight: bold">${p.wupinnname}(${p.num}) </p>

                   <p class="yaoping">(购买需要${p.yin})</p><span class="sy2" id="gm" onclick="gmwp()" onmouseover="getnum(${p.id})">
                   <c:if test="${p.status=='已领取'}">已购买</c:if>
            <c:if test="${p.status=='已读'}">购买</c:if>
                   </span><hr/>
               </c:if>
           </c:forEach>


       </c:if>

       <p class="yaoping"><a href="#"><span class="sy" id="hfyj">回复邮件</span></a></p>
       <hr>
       <p class="yaoping">提示：请认真查看邮件信息，避免受骗</p>
       <p class="yaoping"><a href="#"><span class="sy" id="scyj">删除邮件</span></a></p>

  </div>


</div>
<div class="inputks">
    <p id="tst" style="color:#ff2821 "></p>
    <div class="quxiaos">确定</div>
</div>
<jsp:include  page="../common/kj.jsp"/>
  </body>
<script type="text/javascript">
    var numid=null;
    function getnum(num){
        numid=num;
    }
    function gmwp(){

        var yjid=numid;

        $.ajax({
            type: "POST",
            url: "<%=basePath%>youjian/lqwpyl",
            data:{id:yjid},
            success: function(msg) {
                var p=JSON.parse(msg);  //使用这个方法解析json
                var msg=p.msg;
                $(".inputks").css({"display":"block"});
                $(".quxiaos").css({"display":"block"});

                $("#tst").text(msg);



            }

        });

    }
$(function(){

$("#hfyj").click(function(){
    var roleid=$("#roleids").val();
    window.top.location.href="<%=basePath%>/youjian/fsyj?id="+roleid

});

    $("#lq").click(function(){
        var yjid=$("#yjid").val();
        $.ajax({
            type: "POST",
            url: "<%=basePath%>youjian/lqyl",
            data:{id:yjid},
            success: function(msg) {
                var p=JSON.parse(msg);  //使用这个方法解析json
                var msg=p.msg;
                $(".inputks").css({"display":"block"});
                $(".quxiaos").css({"display":"block"});
                $("#tst").text(msg);


            }

        });

    });

    });
    $(".quxiaos").click(function(){
        $(".inputks").css({"display":"none"});
        $(".quxiaos").css({"display":"none"});
        $("#tst").text("");

    });
    $("#send").click(function(){
        var receiveid=$("#roleid").val();
        var yin=$("#roleyin").val();
        var message=$("#msg").text();
        $.ajax({
            type: "POST",
            url: "<%=basePath%>youjian/sendyj",
            data:{receiveid:receiveid,yin:yin,message:message},
            success: function(msg) {
                var p=JSON.parse(msg);  //使用这个方法解析json
                var msg=p.msg;
                $(".inputks").css({"display":"block"});
                $(".quxiaos").css({"display":"block"});
                $("#tst").text(msg);


            }

        });

    });




</script>
</html>
