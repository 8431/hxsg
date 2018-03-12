<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
.index{
	border:0px solid #ff0000;
	width:360px;
	height:520px;
	
	position:fixed;
	top:0px;
	left:0px;
	background:#000031;
	
}
.top{
    position:fixed;
    top:0px;
    left:0px;
    float:left;
	background:url(image/top2.png) #4C4539;
    width:363px;
    line-height:35px;
	height:35px;
	text-align:center;
	color:#A7D02D;
	text-shadow:1px 1px 5px #4E524B;
	font-weight:bold;
	background-size:100% 100%;
	
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
 	text-indent:20px;
 	
 
}

.left{
    float:left;
	
   background-size:100% 100%;
	position:fixed;
	left:0px;
	top:35px;
	height:495px;
	 width:32px;
	 background:url(image/rolescoot.png);

	
	
}
.right{
    float:left;
	
   background-size:100% 100%;
	position:fixed;
	left:331px;
	top:35px;
	height:495px;
	 width:32px;
	 background:url(../image/rolescoot.png);

	
	
}
.photo{
	
position:fixed;

top:70px;
left:250px;
	
	border:0px solid #ff0000;
}
.fh{
position:fixed;
top:492px;
left:30px;
width:302px;
height:38px;
border:0px solid #ff0000;
background:#000031;
}
a{
color:white;
text-shadow:2px 2px 2px #ff0000;
text-decoration: none;
font-size:15px;
font-weight:bold;
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

  </head>
  
  <body>
  
    <div class="kj"  style="overflow-Y: auto;overflow-X:hidden;width:320px;">
   
   <div style="border:0px solid #ff0000; width:270px;height:950px;">
   <div class="photo"><img  style="width:65px;height: 79px;background-size: 100% 100%;"src="${role.touxiang }"/></div>
   <div class="roleid"><b>洞庭湖区-${role.juesename }(${role.id })</b></div>
   <br/>
   <br/>
   <br/>
   <br/>
  		<p class="rolename">职业：${role.dengji }级${role.zhiye }</p>
  		<p class="rolename">称号：<a href="#">${role.chenghao }</a></p>
  		<p class="rolename">升级：需${role.jingyan }经验</p>
  		<p class="rolename">升级效率：${role.sjxiaolv }</p>
  		<p class="rolename"><a href="#">体力值：${role.tilizhi }</a></p>
  		<p class="rolename"><a href="#">活力：${role.huilizhi }</a></p>
  		<p class="rolename">摊位：<a href="#">${role.tanwei }</a></p>
  		<p class="rolename">居住地：${role.juzhudi }</p>
  		<p class="rolename">房产：<a href="#">${role.house }</a></p>
  		<p class="rolename">教派：${role.jiaopai }</p>
  		<p class="rolename">已杀：${role.killsum }人</p>
  		<p class="rolename">配偶：${role.peiou }</p>
  		<p class="rolename">查看技能</p>
  		<p class="rolename">属性点分配：${role.shuxing }点未分配    <a href="#">查看</a></p>
  		<p class="rolename" style="color:#ffffff;">副将：<a href="#">无</a><a href="#">无</a><a href="#">无</a></p>
  		<p class="rolename" style="color:#ff0000;">气血：${role.qixue1 }/${role.qixue2 }</p>
  		<p class="rolename"  style="color:#4447B2">精力：${role.jingli1 }/${role.jingli2 }</p>
  		<p class="rolename">攻击：${role.gongji }</p>
  		<p class="rolename">速度：${role.sudu }</p>
  		<p class="rolename">防御：${role.fangyu }</p>
  		<p class="rolename">负重：${role.fuzhong1 }</p>
  		<p class="rolename">战斗能力   生平     性格</p>
  		
  		
  </div>
  </div>
    <jsp:include  page="../common/kj.jsp"/>
  </body>
</html>


