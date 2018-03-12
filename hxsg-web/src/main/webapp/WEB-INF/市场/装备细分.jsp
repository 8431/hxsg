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




  <jsp:include  page="../common/kj.jsp"/>
<div class="kj">
 <div class="roleid"><b>铁匠铺>装备</b></div>
   <div style="border:0px solid #ff0000; width:100%;">
   
  <br/> <br/>
 		<hr/>
 		<c:if test="${sx=='kj'}">
 		<c:forEach items="${lis}" var="p">
 		<c:if test="${p.shuxing=='铠甲'}">
 		<p class="yaoping"><a href="<%=basePath%>sc/zbxq?id= ${p.id}"> ${p.name }</a><a href="<%=basePath%>sc/zbxq?id= ${p.id}"><span class="sy">购买</span></a></p>
 		</c:if>	
 		</c:forEach>
 		</c:if>
 		
 		<c:if test="${sx=='xl'}">
 		<c:forEach items="${lis}" var="p">
 		<c:if test="${p.shuxing=='项链'}">
 		<p class="yaoping"><a href="<%=basePath%>sc/zbxq?id= ${p.id}"> ${p.name }</a><a href="<%=basePath%>sc/zbxq?id= ${p.id}"><span class="sy">购买</span></a></p>
 		</c:if>
 		</c:forEach>
 		</c:if>
 		<c:if test="${sx=='zx'}">
 		<c:forEach items="${lis}" var="p">
 		<c:if test="${p.shuxing=='战靴'}">
 		<p class="yaoping"><a href="<%=basePath%>sc/zbxq?id= ${p.id}"> ${p.name }</a><a href="<%=basePath%>sc/zbxq?id= ${p.id}"><span class="sy">购买</span></a></p>
 		</c:if>
 		</c:forEach>
 		</c:if>
 		<c:if test="${sx=='hw'}">
 		<c:forEach items="${lis}" var="p">
 		<c:if test="${p.shuxing=='护腕'}">
 		<p class="yaoping"><a href="<%=basePath%>sc/zbxq?id= ${p.id}"> ${p.name }</a><a href="<%=basePath%>sc/zbxq?id= ${p.id}"><span class="sy">购买</span></a></p>
 		</c:if>	
 		</c:forEach>
 		</c:if>
 		<c:if test="${sx=='qk'}">
 		<c:forEach items="${lis}" var="p">
 		<c:if test="${p.shuxing=='血盔'}">
 		<p class="yaoping"><a href="<%=basePath%>sc/zbxq?id= ${p.id}"> ${p.name }</a><a href="<%=basePath%>sc/zbxq?id= ${p.id}"><span class="sy">购买</span></a></p>
 		</c:if>	
 		</c:forEach>
 		</c:if>
 		<c:if test="${sx=='lk'}">
 		<c:forEach items="${lis}" var="p">
 		<c:if test="${p.shuxing=='力盔'}">
 		<p class="yaoping"><a href="<%=basePath%>sc/zbxq?id= ${p.id}"> ${p.name }</a><a href="<%=basePath%>sc/zbxq?id= ${p.id}"><span class="sy">购买</span></a></p>
 		</c:if>	
 		</c:forEach>
 		</c:if>
 		<c:if test="${sx=='sk'}">
 		<c:forEach items="${lis}" var="p">
 		<c:if test="${p.shuxing=='速盔'}">
 		<p class="yaoping"><a href="<%=basePath%>sc/zbxq?id= ${p.id}"> ${p.name }</a><a href="<%=basePath%>sc/zbxq?id= ${p.id}"><span class="sy">购买</span></a></p>
 		</c:if>	
 		</c:forEach>
 		</c:if>
 		<c:if test="${sx=='pw'}">
 		<c:forEach items="${lis}" var="p">
 		<c:if test="${p.shuxing=='普武'}">
 		<p class="yaoping"><a href="<%=basePath%>sc/zbxq?id= ${p.id}"> ${p.name }</a><a href="<%=basePath%>sc/zbxq?id= ${p.id}"><span class="sy">购买</span></a></p>
 		</c:if>	
 		</c:forEach>
 		</c:if>
 		<c:if test="${sx=='lw'}">
 		<c:forEach items="${lis}" var="p">
 		<c:if test="${p.shuxing=='力武'}">
 		<p class="yaoping"><a href="<%=basePath%>sc/zbxq?id=${p.id}"> ${p.name }</a><a href="<%=basePath%>sc/zbxq?id= ${p.id}"><span class="sy">购买</span></a></p>
 		</c:if>	
 		</c:forEach>
 		</c:if>
 
 	
  		
  	
  	
  		
  		
  		
  </div>

</div>
  </body>
</html>
