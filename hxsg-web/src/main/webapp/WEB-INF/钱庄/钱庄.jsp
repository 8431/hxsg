<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <title>index.html</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
     <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
<script type="text/javascript" src="jQuery/jquery-1.4.2.js"></script>
<style type="text/css">
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
margin-left:200px;
border:1px solid #ff0000;
background:#ff9e00;
color:#ff0000;
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
.rolename{
 	color:#ffffff;
 	text-indent:30px;
 	
 
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
.xuangk{
width:80px;margin:30px 0px 0px 50px;background:#ff0000;color:#ffffff;font-size:20px;

}
	
	


</style>


  </head>
  
  <body>
 
   
  <div class="kj">
  
 	  
 <div class="roleid"><b>驿馆</b></div>
   <div style="border:0px solid #ff0000; width:100%;">
   
  <br/> <br/>
  <hr/>
       您目前拥有0金0银
	<a href="#"><div class="xuangk" >银两取款</div></a>
 		<a href="#"><div class="xuangk">银两存款</div></a>
 		<a href="#"><div class="xuangk">金砖取款</div></a>
 		<a href="<%=basePath%>yg/fjls"><div class="xuangk">金砖存款</div></a>
 		<a href="<%=basePath%>yg/fjzl"><div class="xuangk">副将招领</div></a>
 		
 		<br/>
 </div>
 
   </div>
   
  <jsp:include page="../common/kj.jsp"/>
  </body>
</html>
