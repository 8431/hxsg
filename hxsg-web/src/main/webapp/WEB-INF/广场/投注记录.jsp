<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
   
    
    <title>My JSP 'rolesum.jsp' starting page</title>
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
color: #fffbf9;
line-height:30px;
height:30px;
width:299px;
background:#634163;
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
border:2px solid #ff0000;
background:#ff9e00;
color:#ff0000;
}
</style>
  </head>
  
  <body>


  <div class="kj" style="overflow-Y: auto;overflow-X:hidden;width:320px;">
 <div class="roleid"><b>【投注记录】</b></div>
   <div style="border:0px solid #ff0000; width:100%;">
   
  <br/> <br/>
 		<hr/>
 	
   <c:forEach items="${hy}" var="t">
       <c:if test="${t.jieguo=='赢'}">
       <p class="yaoping" style="color: #e8ff06;font-weight: bold;">第${t.num}期，你押${t.result}
           <c:if test="${t.jin==0}">
               ${t.yin}两 ${t.jieguo}!</p>
           </c:if>
       <c:if test="${t.yin==0}">
           ${t.jin}金 ${t.jieguo}!</p>

       </c:if>
       </c:if>
       <c:if test="${t.jieguo=='输'}">
           <p class="yaoping">第${t.num}期，你押${t.result}
           <c:if test="${t.jin==0}">
               ${t.yin}两 ${t.jieguo}!</p>
           </c:if>
           <c:if test="${t.yin==0}">
               ${t.jin}金 ${t.jieguo}!</p>

           </c:if>
       </c:if>

   </c:forEach>

 	
 	
  		
  	
  	
  		
  		
  		
  </div>

</div>
  <jsp:include page="../common/kj.jsp"/>
  </body>
</html>
