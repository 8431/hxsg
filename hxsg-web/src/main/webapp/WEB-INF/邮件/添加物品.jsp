<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include  page="../common/tag.jsp"/>
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
      <script type="text/javascript" src="<%=basePath%>js/wupin.js"></script>
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
.kjt{
    background:#8c8c17;
    width:320px;
    height:380px;
    position:fixed;
    left:32px;
    top:105px;
    color:#ffffff;
    display:none;

}

.yaoping{
color:#ff0000;
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
width:50px;
font-size:20px;
margin-left:10px;
border:0px solid #ff0000;
background:#B520BD;
color:#ffffff;
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
.chexiao{
    text-align: center;
    position: fixed;
    top:490px;
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





<div class="kj">
 <div class="roleid"><b>添加物品</b></div>
   <div style="border:0px solid #ff0000; width:100%;">
   
  <br/> <br/>
 		<hr/>
 		
 	<p class="yaoping">欢迎客官，本店提供邮寄服务！</p>
 	<p class="yaoping">物品:${jj.jjname}</p>
       <p class="yaoping">总量:${jj.num}</p>
       <p class="yaoping">交易价格：
       <input type="text" id="roleid" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " style="width:100px;height:25px;margin-top:0px;"/>
       </p>
       <p class="yaoping">交易数量:
           <input type="text" id="roleid" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " style="width:100px;height:25px;margin-top:0px;"/>
       </p>

       <p >交易总价为空为赠送！</p>
       <p class="sy" >确定</p>


  </div>


</div>

</div>

<jsp:include  page="../common/kj.jsp"/>
  </body>
<script type="text/javascript"></script>




</html>
