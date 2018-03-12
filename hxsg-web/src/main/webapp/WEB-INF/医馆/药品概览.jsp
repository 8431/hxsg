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
margin-left:100px;
border:1px solid #ff0000;
background:#ff9e00;
color:#ff0000;
}
</style>
  </head>
  
  <body>





<div class="kj">

 <div class="roleid">

     <c:if test="${sx==0}">
         <b>医馆>气血药品</b>
     </c:if>
     <c:if test="${sx==1}">
         <b>医馆>精力药品</b>
     </c:if>
     </div>
   <div style="border:0px solid #ff0000; width:100%;">
   
  <br/> <br/>
 		<hr/>
 		<c:forEach items="${yao}" var="y">
 	<p class="yaoping"><a href="<%=basePath%>yao/ypxq?yaoid=${y.yaoid}&sx=${y.sx }&yaoname=${y.yaoname }&yaoprice=${y.yaoprice }&dengji=${y.dengji}&qixuezhi=${y.qixuezhi}">
 		${y.yaoname }(${y.yaoprice })</a>
 		<a href="<%=basePath%>yao/ypxq?yaoid=${y.yaoid}&sx=${y.sx }&yaoname=${y.yaoname }&yaoprice=${y.yaoprice }&dengji=${y.dengji}&qixuezhi=${y.qixuezhi}"><span class="sy">购买</span></a></p>
 	</c:forEach>
 	
 
 	
  		
  	
  	
  		
  		
  		
  </div>
    <jsp:include  page="../common/kj.jsp"/>
</div>
  </body>
</html>
