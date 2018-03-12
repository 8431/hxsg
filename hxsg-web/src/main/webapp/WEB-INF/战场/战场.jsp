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
    font-size: 25px;
    height:30px;
    line-height: 30px;
border:2px solid #ff0000;
background:#ff9e00;
color:#ff0000;
}
</style>
  </head>
  
  <body>


  <div class="kj" style="overflow-Y: auto;overflow-X:hidden;width:320px;">
 <div class="roleid"><b>战场</b></div>
   <div style="border:0px solid #ff0000; width:100%;">
   
  <br/> <br/>
 		<hr/>
 	

   <p class="yaoping"><span class="sy">宝藏洞口</span></p>
       <p class="yaoping">这是一块杀人越货，无恶不作的罪恶之地也是一块，一夜暴富的财富之地。挖宝时不要相信任何人！</p>

       <p class="yaoping"><span class="sy">战场(未开放)</span></p>
       <p class="yaoping">尸横遍野，刀光剑影。一块嗜血的杀戮之地，没实力的请远离此地！</p>
  		
  	
  	
  		
  		
  		
  </div>

</div>
  <jsp:include page="../common/kj.jsp"/>
  </body>
</html>
