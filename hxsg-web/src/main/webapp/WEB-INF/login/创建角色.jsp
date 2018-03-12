<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="<%=basePath%>jQuery/jquery-1.4.2.js"></script>
<style type="text/css">
a{
text-decoration:none;

}
.index{
	width:100%;
	border:0px solid #ff0000;
	width:360px;
	height:575px;
	margin:0px auto
	position:fixed;
	left:0px;
	top:0px;
	background:#000031;
	
}
.top{
    position:fixed;
    top:0px;
    
	background:url(../image/top.png);
    width:398px;
    line-height:35px;
	height:35px;
	text-align:center;
	color:#ffffff
	
}
.roleid{
	position:fixed;
	top:34px;
	left:41px;
   
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

.left{
   
	
   
	
	left:0px;
	top:44px;
	height:650px;
	 width:32px;
	 background:url(../image/rolescoot.png);
}
.right{
 
	
	float:right;
	margin-left:300px;
	height:400px;
	 width:32px;
	 background:url(../image/rolescoot.png);
}




.juesetx{
	margin-top:20px;
float:left;
margin-left:40px;
	width:65px;
	border:0px solid #ff0000;
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
margin-left:300px;
border:1px solid #ff0000;
background:#ff9e00;
color:#ff0000;
}
.fh{
position:fixed;
top:580px;
left:50px;
}

</style>
<script type="text/javascript">
    $(function(){
    
     $("#d1").click(function(){
     $("#role_tx").val($("#m1").attr("src"));
      //alert( $("#role_tx").val());
   
      
   
     $("#d1").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d2").click(function(){
      $("#role_tx").val($("#m2").attr("src"));
      //alert( $("#role_tx").val());
   
    $("#d2").css({"border":"3px solid #E2E200"});
     $("#d1").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d3").click(function(){
     $("#role_tx").val($("#m3").attr("src"));
      //alert( $("#role_tx").val());
   
    $("#d3").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d1").css({"border":""});
    });
    
    
    
    
    
    
    
    
    
    
    
 
  $("#nv").click(function(){ 
   if($("#wushi").attr("checked")){
    $("#tx").html("<div id='d1' class='juesetx'><img  id='m1' src='../image/wushinv1.jpg'/></div><div id='d2' class='juesetx'><img id='m2' src='../image/wushinv2.jpg'/></div><div id='d3' class='juesetx'><img id='m3' src='../image/wushinv3.jpg'/></div>")
   } 
     //////////////////////
    $("#d1").click(function(){
   $("#role_tx").val($("#m1").attr("src"));
    $("#d1").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d2").click(function(){
      $("#role_tx").val($("#m2").attr("src"));
    $("#d2").css({"border":"3px solid #E2E200"});
     $("#d1").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d3").click(function(){
      $("#role_tx").val($("#m3").attr("src"));
    $("#d3").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d1").css({"border":""});
    });   
    ////////////////////
  });
  $("#wushi").click(function(){ 
   if($("#nv").attr("checked")){
    $("#tx").html("<div id='d1' class='juesetx'><img id='m1' src='image/wushinv1.jpg'/></div><div id='d2' class='juesetx'><img id='m2' src='image/wushinv2.jpg'/></div><div id='d3'class='juesetx'><img id='m3' src='image/wushinv3.jpg'/></div>")
   } 
    //////////////////////
    $("#d1").click(function(){
   $("#role_tx").val($("#m1").attr("src"));
    $("#d1").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d2").click(function(){
      $("#role_tx").val($("#m2").attr("src"));
    $("#d2").css({"border":"3px solid #E2E200"});
     $("#d1").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d3").click(function(){
      $("#role_tx").val($("#m3").attr("src"));
    $("#d3").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d1").css({"border":""});
    });   
    ////////////////////
   
  });
   $("#nan").click(function(){ 
   if($("#wushi").attr("checked")){
    $("#tx").html("<div id='d1' class='juesetx'><img id='m1' src='image/wushi1.jpg'/></div><div id='d2' class='juesetx'><img id='m2' src='image/wushi2.jpg'/></div><div id='d3' class='juesetx'><img id='m3' src='image/wushi3.jpg'/></div>")
   } 
     //////////////////////
    $("#d1").click(function(){
   $("#role_tx").val($("#m1").attr("src"));
    $("#d1").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d2").click(function(){
      $("#role_tx").val($("#m2").attr("src"));
    $("#d2").css({"border":"3px solid #E2E200"});
     $("#d1").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d3").click(function(){
      $("#role_tx").val($("#m3").attr("src"));
    $("#d3").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d1").css({"border":""});
    });   
    ////////////////////
  });
  $("#wushi").click(function(){ 
   if($("#nan").attr("checked")){
    $("#tx").html("<div id='d1' class='juesetx'><img id='m1' src='../image/wushi1.jpg'/></div><div id='d2' class='juesetx'><img id='m2' src='../image/wushi2.jpg'/></div><div id='d3' class='juesetx'><img id='m3' src='../image/wushi3.jpg'/></div>")
   } 
    //////////////////////
    $("#d1").click(function(){
   $("#role_tx").val($("#m1").attr("src"));
    $("#d1").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d2").click(function(){
      $("#role_tx").val($("#m2").attr("src"));
    $("#d2").css({"border":"3px solid #E2E200"});
     $("#d1").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d3").click(function(){
      $("#role_tx").val($("#m3").attr("src"));
    $("#d3").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d1").css({"border":""});
    });   
    ////////////////////
   
  });
  ///////////////
 
  /////文人
  $("#nv").click(function(){ 
   if($("#wenren").attr("checked")){
    $("#tx").html("<div id='d1' class='juesetx'><img id='m1' src='../image/wenrennv1.jpg'/></div><div id='d2' class='juesetx'><img id='m2' src='../image/wenrennv2.jpg'/></div><div id='d3' class='juesetx'><img id='m3' src='../image/wenrennv3.jpg'/></div>")
   } 
     //////////////////////
    $("#d1").click(function(){
   $("#role_tx").val($("#m1").attr("src"));
    $("#d1").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d2").click(function(){
      $("#role_tx").val($("#m2").attr("src"));
    $("#d2").css({"border":"3px solid #E2E200"});
     $("#d1").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d3").click(function(){
      $("#role_tx").val($("#m3").attr("src"));
    $("#d3").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d1").css({"border":""});
    });   
    ////////////////////
  });
   $("#wenren").click(function(){ 
   if($("#nv").attr("checked")){
    $("#tx").html("<div id='d1' class='juesetx'><img id='m1' src='../image/wenrennv1.jpg'/></div><div id='d2' class='juesetx'><img id='m2' src='../image/wenrennv2.jpg'/></div><div id='d3' class='juesetx'><img id='m3' src='../image/wenrennv3.jpg'/></div>")
   } 
    //////////////////////
    $("#d1").click(function(){
   $("#role_tx").val($("#m1").attr("src"));
    $("#d1").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d2").click(function(){
      $("#role_tx").val($("#m2").attr("src"));
    $("#d2").css({"border":"3px solid #E2E200"});
     $("#d1").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d3").click(function(){
      $("#role_tx").val($("#m3").attr("src"));
    $("#d3").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d1").css({"border":""});
    });   
    ////////////////////
   
  });
  ///////////////
   $("#nan").click(function(){   
   if($("#wenren").attr("checked")){
    $("#tx").html("<div id='d1' class='juesetx'><img id='m1' src='../image/wenren1.jpg'/></div><div id='d2' class='juesetx'><img id='m2' src='../image/wenren2.jpg'/></div><div id='d3' class='juesetx'><img id='m3' src='../image/wenren3.jpg'/></div>")
   }
     //////////////////////
    $("#d1").click(function(){
   $("#role_tx").val($("#m1").attr("src"));
    $("#d1").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d2").click(function(){
      $("#role_tx").val($("#m2").attr("src"));
    $("#d2").css({"border":"3px solid #E2E200"});
     $("#d1").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d3").click(function(){
      $("#role_tx").val($("#m3").attr("src"));
    $("#d3").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d1").css({"border":""});
    });   
    ////////////////////   
  });
     $("#wenren").click(function(){   
   if($("#nan").attr("checked")){
    $("#tx").html("<div id='d1' class='juesetx'><img id='m1' src='../image/wenren1.jpg'/></div><div id='d2' class='juesetx'><img id='m2' src='../image/wenren2.jpg'/></div><div id='d3' class='juesetx'><img id='m3' src='../image/wenren3.jpg'/></div>")
   }  
     //////////////////////
    $("#d1").click(function(){
   $("#role_tx").val($("#m1").attr("src"));
    $("#d1").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d2").click(function(){
      $("#role_tx").val($("#m2").attr("src"));
    $("#d2").css({"border":"3px solid #E2E200"});
     $("#d1").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d3").click(function(){
      $("#role_tx").val($("#m3").attr("src"));
    $("#d3").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d1").css({"border":""});
    });   
    //////////////////// 
  });
  /////异人
    $("#nv").click(function(){ 
  
   if($("#yiren").attr("checked")){
    $("#tx").html("<div id='d1' class='juesetx'><img id='m1' src='../image/yirennv1.jpg'/></div><div id='d2' class='juesetx'><img id='m2' src='../image/yirennv2.jpg'/></div><div id='d3' class='juesetx'><img id='m3' src='../image/yirennv3.jpg'/></div>")
   } 
    //////////////////////
    $("#d1").click(function(){
   $("#role_tx").val($("#m1").attr("src"));
    $("#d1").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d2").click(function(){
      $("#role_tx").val($("#m2").attr("src"));
    $("#d2").css({"border":"3px solid #E2E200"});
     $("#d1").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d3").click(function(){
      $("#role_tx").val($("#m3").attr("src"));
    $("#d3").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d1").css({"border":""});
    });   
    ////////////////////
   
  });
     $("#yiren").click(function(){ 
   if($("#nv").attr("checked")){
    $("#tx").html("<div id='d1' class='juesetx'><img id='m1' src='../image/yirennv1.jpg'/></div><div id='d2' class='juesetx'><img id='m2' src='../image/yirennv2.jpg'/></div><div id='d3' class='juesetx'><img id='m3' src='../image/yirennv3.jpg'/></div>")
   } 
   //////////////////////
    $("#d1").click(function(){
   $("#role_tx").val($("#m1").attr("src"));
    $("#d1").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d2").click(function(){
      $("#role_tx").val($("#m2").attr("src"));
    $("#d2").css({"border":"3px solid #E2E200"});
     $("#d1").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d3").click(function(){
      $("#role_tx").val($("#m3").attr("src"));
    $("#d3").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d1").css({"border":""});
    });   
    ////////////////////
  });
  ///////////////
   $("#nan").click(function(){  
   if($("#yiren").attr("checked")){
    $("#tx").html("<div id='d1' class='juesetx'><img id='m1' src='../image/yiren1.jpg'/></div><div id='d2' class='juesetx'><img id='m2' src='../image/yiren2.jpg'/></div><div id='d3' class='juesetx'><img id='m3' src='../image/yiren3.jpg'/></div>")
   }  
    $("#d1").click(function(){
   $("#role_tx").val($("#m1").attr("src"));
    $("#d1").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d2").click(function(){
      $("#role_tx").val($("#m2").attr("src"));
    $("#d2").css({"border":"3px solid #E2E200"});
     $("#d1").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d3").click(function(){
      $("#role_tx").val($("#m3").attr("src"));
    $("#d3").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d1").css({"border":""});
    });
  });
     $("#yiren").click(function(){  
   if($("#nan").attr("checked")){
    $("#tx").html("<div id='d1' class='juesetx'><img id='m1' src='../image/yiren1.jpg'/></div><div id='d2'class='juesetx'><img id='m2' src='../image/yiren2.jpg'/></div><div id='d3' class='juesetx'><img id='m3' src='../image/yiren3.jpg'/></div>")
   } 
   
     $("#d1").click(function(){
   $("#role_tx").val($("#m1").attr("src"));
    $("#d1").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d2").click(function(){
      $("#role_tx").val($("#m2").attr("src"));
    $("#d2").css({"border":"3px solid #E2E200"});
     $("#d1").css({"border":""});
      $("#d3").css({"border":""});
    });
     $("#d3").click(function(){
      $("#role_tx").val($("#m3").attr("src"));
    $("#d3").css({"border":"3px solid #E2E200"});
     $("#d2").css({"border":""});
      $("#d1").css({"border":""});
    });
   
    
  });
   
  ///////////////////////////////////
  
  
  });
 
 

   
 
    
    
   
    

</script>
  </head>
  
  <body>
 

   <div style="background:#000031;width:360px;height:575px;border:0px solid #ff0000;position:fixed;top:0px;left:0px;">
   <form action="<%=basePath%>zhuce/creatrole?id=${id}" method="post">
   <p style="color:#FFFF00;font-size:30px;text-indent:15px;">创建角色</p>
  <p style="color:#ffffff;width:100%;text-indent:15px;">角色名：<input name="juesename" type="text" size="20" style="height:30px;"/></p>
 
  <hr/>
  <p style="color:#ffffff;width:100%;text-indent:15px;">性别：<input checked="checked" id="nan" type="radio"  name="sex" value="男"/>男&nbsp;&nbsp;&nbsp;<input id="nv" name="sex"  type="radio" value="女"/>女</p> 
	<hr/>
	<p style="color:#ffffff;width:100%;text-indent:15px;">
	职业：<input id="wushi" name="zhiye" type="radio" checked="checked"  value="武士" />武士&nbsp;&nbsp;&nbsp;
	<input id="wenren" name="zhiye" type="radio" value="文人"/>文人&nbsp;&nbsp;&nbsp;
	<input id="yiren" name="zhiye" type="radio" value="异人"/>异人</p>
	
	<hr/>
  <p style="color:#ffffff;text-indent:15px;">头像：</p>
  <input id="role_tx" type="hidden" name="touxiang"/>
 <div id="tx">
 <div id="d1" class='juesetx'><img id="m1" src='../image/wushi1.jpg'/></div>
 <div id="d2" class='juesetx'><img id="m2" src='../image/wushi2.jpg'/></div>
 <div id="d3" class='juesetx'><img id="m3" src='../image/wushi3.jpg'/></div>
 
 
 </div>
  

	
	<input type="submit" value="进入游戏" style="text-indent:0px;width:100px;font-size:20px;background:#FFFF00;color:#ff0000;position:absolute;top:500px;left:100px;"/>
	</form>
	</div>

  
  </body>
</html>
