<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
   
    
    <title>My JSP '??.jsp' starting page</title>
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
 <script type="text/javascript" src="<%=basePath%>jQuery/wupin.js"></script>
<style type="text/css">
a{
text-decoration:none;
    color: #ffed99;
    text-indent:30px;
    font-size:20px;
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
.rolename{
 	color:#ff0000;
 	text-indent:30px;
 	font-size:20px;
 
}
.rolename2{
 	color:#ff9e00;
 	text-indent:30px;
 	font-size:20px;
 
}


li{
float:left;
width:50px;
background:#E1B205;
margin-left:5px;
text-align:center;
color:#B520BD;
font-size:20px;
list-style:none;
}
.ypss{
float:left;
width:50px;
background:#ff9e00;
margin-left:5px;
text-align:center;
color:#B520BD;
font-size:20px;
list-style:none;


}
.sy{
width:50px;
font-size:20px;
margin-left:50px;
border:1px solid #ff0000;
background:#ff9e00;
color:#ff0000;

}
.kj{
background:#000031;
width:299px;
height:505px;
position:fixed;
left:32px;
top:35px;
color:#ffffff;

}

</style>
<%--<script type="text/javascript">--%>

<%--</script>--%>
  </head>
  
  <body>
  <div class="kj" style="overflow-Y: auto;overflow-X:hidden;width:320px;">
   <div class="roleid"><b>物品</b></div>
   <div style="border:0px solid #ff0000; width:100%;">

  <br/> <br/>
 		<div style="width:100%;margin-top:50px"><img src="../image/wdzb.png"/></div>
 		<ul>
 		<li id="yaopin" >药品</li>
 		<li id="kuangshi">矿石</li>
 		<li id="zhuangbei">装备</li>
 		<li id="zhawu">杂物</li>
 		</ul>
 		<br/>
 		<hr/>
 		<div id="xswp" >

  	
  	  </div>
  		
  		
  		
  </div>
 </div>

  <jsp:include page="../common/kj.jsp"/>

  </body>

</html>
