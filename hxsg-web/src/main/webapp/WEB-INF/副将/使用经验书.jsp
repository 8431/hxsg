<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@include file="../common/tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
   
    
    <title>My JSP 'role_fujiang.jsp' starting page</title>
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
.rolename{
 	color:#ff0000;
 	text-indent:10px;
 	font-size:20px;
 
}
.rolename2{
 	color:#ff9e00;
 	text-indent:10px;
 	font-size:20px;
 
}
a{
text-decoration:none;
color: #c4fe96;
font-size:20px;
font-weight:bold;

}
.roleid{
	position:fixed;
	top:34px;
	left:0px;
    float:left;
   background:#000031;
	color:#FF9998;
	text-align:center;
	
    width:350px;
	height:30px;	
	
}
.sy{
width:50px;
font-size:20px;
margin-left:30px;
border:1px solid #ff0000;
background:#ff9e00;
color:#ff0000;}
</style>
<script type="text/javascript">


$(function(){
 $("tr:even").css({"background":"#634163"});
  $("tr:odd").css({"background":"#CF3DF6"});



})
 
</script>
  </head>
  
  <body>
  
  <div class="kj"style="overflow-Y: auto;overflow-X:hidden;width:320px;">
  
  
 
  <div class="roleid"><b>${name}</b></div>
   <div style="border:0px solid #ff0000; width:300px;">
   
  <br/> <br/>
       <c:if test="${size==0}">

           <p style="text-indent:10px;line-height:30px;height:30px;width:100%;background:#634163;">
               <a href="#">你没有可以使用该心法的副将</a></p>
       </c:if>
  <c:forEach items="${lis}" var="p">
  <p style="text-indent:10px;line-height:30px;height:30px;width:100%;background:#634163;">
  <a href="#" >(${p.leve}级)${p.fujiangname }(${p.touxian})</a>
  <a href="<%=basePath%>fujiang/totalnum?id=${p.id}&jjid=${jjid}"><span class="sy" id="syxf">选择</span></a></p>


 	</c:forEach>	
 		
		
 		
 		
  		
  	
  	
  		
  		
  		
  </div>
   </div>
  <jsp:include page="../common/kj.jsp"/>
  </body>
</html>
