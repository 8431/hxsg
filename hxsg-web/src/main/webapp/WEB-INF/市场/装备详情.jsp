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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="../jQuery/jquery-1.4.2.js"></script>
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
margin-left:200px;
margin-top:80px;
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
	border:1px solid #ff9e00;

}
.tongyong{
text-indent:30px;
color:#ffffff;
line-height:30px;
height:30px;
width:100%;


}
</style>

<script type="text/javascript">
$(function(){
var id=$("#wqid").val();
$("#sub").click(function(){



var result="";
	$.ajax({
         type: "POST",
         url: "<%=basePath%>sc/shopzb",
         data:"id="+id,
         success: function(msg){
          result=msg;
        
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
 <div class="roleid"><b>铁匠铺>装备详情</b></div>
   <div style="border:0px solid #ff0000; width:100%;">
   
  <br/> <br/>
 		<hr/>
 		
 
 	
 	
	<div id="kj" >	
	
 
 	
 	<p class="tongyong">名称:${wq.name}</p>
 	<p class="tongyong">等级:${wq.dengji}</p>
 	<p class="tongyong">价格:${wq.price}两</p> 	
    <p class="tongyong">效果:${wq.gongji}</p> 	
 	<p class="tongyong">介绍:${wq.jiehsao}</p> 
 	<input id="sub" type="button" value="购买" class="sy"/>
 	<input id="wqid" type="hidden" value="${wq.id }"/>
 	
 	
 		
 	

 	
  </div>
  	
  		
 

</div>

  </body>
</html>
