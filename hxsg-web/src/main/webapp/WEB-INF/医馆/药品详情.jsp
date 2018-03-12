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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>jQuery/jquery-1.4.2.js"></script>
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
text-indent:50px;

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
width:50px;
font-size:20px;
margin-left:240px;
border:1px solid #ff0000;
background:#ff9e00;
color:#ff0000;
}
.kjdiv{

	position:fixed;
	top:300px;
	left:50px;
	width:200px;
	height:100px;
    margin: 0 auto;
    border:0px solid #ff9e00;

}
</style>
<script type="text/javascript">



</script>
<script type="text/javascript">
$(function(){
$("#sub").click(function(){
var yaoname=$("#yaoname").val();
var yaoprice=$("#yaoprice").val();
var yaoid=$("#yaoid").val();
var yaonum=$("#yaonum").val();
var result="";
	$.ajax({
         type: "POST",
         url: "<%=basePath%>yao/gmyp",
         data:"yaoname="+yaoname+"&yaoprice="+yaoprice+"&yaoid="+yaoid+"&yaonum="+yaonum,
         success: function(msg){
          result=msg['msg'];
         //alert(result);
			$("#kj").html("<div id='kjdiv' class='kjdiv'></div>");
			$("#kjdiv").html(result);
         }
         });



});

});
  



</script>
  </head>
  
  <body>




  <jsp:include  page="../common/kj.jsp"/>
<div  class="kj">
 <div class="roleid"><b>医馆>气血药品</b></div>
   <div style="border:0px solid #ff0000; width:100%;">
   
  <br/> <br/>
 		<hr/>
 		
 
 	
 	
 		<div id="kj" >
 		<c:if test="${yaopin.sx==0 }">
 		
 		<p style="text-indent:30px;color:#FFFF00;line-height:30px;height:30px;">名称:${yaopin.yaoname }</p>
 	<p style="text-indent:30px;color:#ffffff;line-height:30px;height:30px;width:100%;background:#634163">价格:${yaopin.yaoprice }</p> 	
 	<p style="text-indent:30px;color:#ffffff;line-height:30px;height:30px;width:100%;background:#634163">等级:${yaopin.dengji}</p>
 	
 	<c:if test="${yaopin.qixuezhi>1 }"> 	
 	<p style="text-indent:30px;color:#ffffff;line-height:30px;height:30px;width:100%;background:#634163">补充气血值:<fmt:formatNumber type="number" value="${yaopin.qixuezhi }" maxFractionDigits="0"></fmt:formatNumber></p> 
 	
 	</c:if>
 	<c:if test="${yaopin.qixuezhi<=1 }"> 
 	<p style="text-indent:30px;color:#ffffff;line-height:30px;height:30px;width:100%;background:#634163">补充气血值:<fmt:formatNumber type="number" value="${yaopin.qixuezhi*100 }" maxFractionDigits="0"></fmt:formatNumber>% </p> 
 	</c:if>
 	<p style="text-indent:30px;color:#ffffff;line-height:30px;height:30px;width:100%;background:#634163">购买数量：<input id="yaonum" name="yaonum"  type="text" size="6" style="width:50px;"/></p> 
 	
 	
 	<input id="sub" type="submit" value="购买" class="sy"/>
 	<input id="yaoname" name="yaoname" type="hidden" value="${yaopin.yaoname }"/>
 	<input id="yaoid" name="yaoid" type="hidden" value="${yaopin.yaoid}"/>
 	<input id="yaoprice" name="yaoprice" type="hidden" value="${yaopin.yaoprice }"/>
 	
 		
 		</c:if>
 	
 	
 	
 	
 	
 	
 	
 	
 		<c:if test="${yaopin.sx==1 }">
 		
 		<p style="text-indent:30px;color:#FFFF00;line-height:30px;height:30px;">名称:${yaopin.yaoname }</p>
 	<p style="text-indent:30px;color:#ffffff;line-height:30px;height:30px;width:100%;background:#634163">价格:${yaopin.yaoprice }</p> 	
 	<p style="text-indent:30px;color:#ffffff;line-height:30px;height:30px;width:100%;background:#634163">等级:${yaopin.dengji}</p>
 	
 	<c:if test="${yaopin.qixuezhi>1 }"> 	
 	<p style="text-indent:30px;color:#ffffff;line-height:30px;height:30px;width:100%;background:#634163">补充精力值:<fmt:formatNumber type="number" value="${yaopin.qixuezhi }" maxFractionDigits="0"></fmt:formatNumber></p> 
 	
 	</c:if>
 	<c:if test="${yaopin.qixuezhi<=1 }"> 
 	<p style="text-indent:30px;color:#ffffff;line-height:30px;height:30px;width:100%;background:#634163">补充精力值:<fmt:formatNumber type="number" value="${yaopin.qixuezhi*100 }" maxFractionDigits="0"></fmt:formatNumber>% </p> 
 	</c:if>
 	<p style="text-indent:30px;color:#ffffff;line-height:30px;height:30px;width:100%;background:#634163">购买数量：<input id="yaonum" name="yaonum"  type="text" size="6" style="width:50px;"/></p> 
 	
 	
 	<input id="sub" type="submit" value="购买" class="sy"/>
 	<input id="yaoname" name="yaoname" type="hidden" value="${yaopin.yaoname }"/>
 	<input id="yaoid" name="yaoid" type="hidden" value="${yaopin.yaoid}"/>
 	<input id="yaoprice" name="yaoprice" type="hidden" value="${yaopin.yaoprice }"/>
 	
 		
 		</c:if>
 	
  </div>
  	
  		
 

</div>
</div>
  </body>
</html>
