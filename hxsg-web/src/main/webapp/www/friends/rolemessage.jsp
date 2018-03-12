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
<script type="text/javascript" src="<%=basePath%>jQuery/jquery-1.4.2.js"></script>
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
	background:url(../image/top2.png) #4C4539;
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
 	font-size:20px;
 	
 	
 
}

.left{
    float:left;
	
   background-size:100% 100%;
	position:fixed;
	left:0px;
	top:35px;
	height:495px;
	 width:32px;
	 background:url(../image/rolescoot.png);

	
	
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
background:#502700;
width:299px;
height:505px;
position:fixed;
left:32px;
top:35px;
color:#ffffff;

}
.kuang{

height:30px;
line-height:30px;
font-size:20px;
text-align:center;
background:#D14F00;
color:#ffffff;
margin-left:20px;

}
.fy2{
    background: url(../image/alert.jpg);
    width:340px;
    height: 100px;
    line-height: 60px;
    background-size: 100% 100%;
    position: fixed;
    top:150px;
    left:10px;
    display:none;
    text-align: center;
    size:4;
    font-weight: bold;
    color:#D0C80B;

}
.suresr{
    background: url(../image/sure2.jpg);
    width:90px;
    height: 35px;
    background-size: 100% 100%;
    position:fixed;
    top:250px;
    left:130px;
    display:none;

}
.newsures{
    background: url(../image/hy_sure.png);
    width:90px;
    height: 35px;
    background-size: 100% 100%;
    position: fixed;
    top:250px;
    left:60px;
    display:none;

}
.newjujues{
    background: url(../image/hy_jujue.png);
    width:90px;
    height: 35px;
    background-size: 100% 100%;
    position: fixed;
    top:250px;
    left:200px;
    display:none;

}
</style>

  </head>

  <body>


    <div class="kj"  style="overflow-Y: auto;overflow-X:hidden;width:320px;">
   
   <div style="border:0px solid #ff0000; width:270px;height:950px;">
   <div class="photo"><img  style="width:65px;height: 79px;background-size: 100% 100%;"src="${role.touxiang }"/></div>
   <div class="roleid"><b>${role.juesename }(${role.id })</b></div>
       <div id="fids" style="display:none">${role.id }</div>
  <br/>  <br/>
  <a href="<%=basePath%>friends/talkmsgs?friendid=${role.id }&roleid=${rolemsg.id}&friendname=${role.juesename }&rolename=${rolemsg.juesename}"><span class="kuang" id="fxx">发信息</span></a>           <span class="kuang" id="jhy">${hy}</span><br/>
 <a href="<%=basePath%>/youjian/fsyj?id=${role.id }"> <span class="kuang" id="wkf">寄邮包</span></a>   <span class="kuang" id="jh">${msg}</span><br/>
  <span class="kuang" id="wkf1">切磋</span>     <span class="kuang" id="wkf2">换物品 </span><br/>
  		<p class="rolename">职业：${role.dengji }级${role.zhiye }</p>
  		<p class="rolename">称号：<a href="#">${role.chenghao }</a></p>
  		<p class="rolename">教派：${role.jiaopai }</p>
  		<p class="rolename">PK标志:0<a href="#"><span class="kuang">查看</span></a> </p>
  		 <a href="#"><span class="kuang">个人榜</span></a>    <a href="#"><span class="kuang">排行榜</span><br/></a>
  		<p class="rolename"><a href="#">体力值:${role.tilizhi }</a></p>
  		<p class="rolename"><a href="#">魅力值:${role.huilizhi }</a></p>
  		<p class="rolename">摊位:<a href="#">${role.tanwei }</a></p>
  		
  		<p class="rolename">房产:<a href="#">${role.house }</a></p>
  		
  		<p class="rolename">已杀:${role.killsum }人</p>
  		<p class="rolename">性格:${role.juzhudi }</p>
  		<p class="rolename">配偶：${role.peiou }</p>
  		<p class="rolename">师傅：${role.peiou }</p>
  	
  		<p class="rolename" style="color:#ffffff;">副将：<a href="#">无</a><a href="#">无</a><a href="#">无</a></p>
  	   <p class="rolename">坐骑：的卢马</p>
  		<p class="rolename">抗性：抗物理100</p>
  		<p class="rolename">抗性：抗雷100</p>
  		<p class="rolename">风格：速度型</p>
  		 <a href="#"><span class="kuang">生平资料</span></a>    <a href="#"><span class="kuang">查看装备 </span><br/></a>
       <div class="fy2"></div>
       <div class="suresr"></div>
       <div class="newsures"></div>
       <div class="newjujues"></div>
  </div>
  </div>



    <script type="text/javascript">

        $(function(){

            $(".fy2").click(function(){
                $(".fy2").css({"display":none});
            });
            $("#wkf").click(function(){
                $(".fy2").text("功能暂未开放");
            });
            $(".suresr").click(function(){
                $(".suresr").css({"display":"none"});
                $(".fy2").css({"display":"none"});
            });
            $("#jhy").click(function(){
                var fid=$("#fids").text();
                if($("#jhy").text()=="割袍断交"){
                    $(".fy2").css({"display": "block"});
                    $(".fy2").text("你确定要跟昔日的好友断交吗？");
                    $(".newsures").css({"display":"block"});

                    $(".newjujues").css({"display":"block"});
                }else {
                    $.ajax({
                        type: "POST",
                        url: "<%=basePath%>friends/addfriend",
                        data: {friendid: fid},
                        success: function (msg) {

                            $(".suresr").css({"display": "block"});
                            $(".fy2").css({"display": "block"});
                            $(".fy2").text(msg);
                        }

                    });
                }
            });
            $(".newsures").click(function(){
                var fid=$("#fids").text();


                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>friends/djhy",
                    data:{friendid:fid},
                    success: function(msg){

                        $(".newsures").css({"display":"none"});
                        $(".newjujues").css({"display":"none"});
                        $(".fy2").css({"display":"none"});





                    }
                });
            });

            $(".newjujues").click(function(){
                $(".newsures").css({"display":"none"});
                $(".newjujues").css({"display":"none"});
                $(".fy2").css({"display":"none"});

            });
            $(".sure").click(function(){
                var ids=$("#rfmsgid").val();
                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>friends/newjujue",
                    data:{id:ids},
                    success: function(msg){
                        $(".sure").css({"display":"none"});
                        $(".fy2").css({"display":"none"});
                        $(".news").css({"display":"none"});

                    }
                });
            });
            $("#jh").click(function(){
                var fid=$("#fids").text();
                if( $("#jh").text().equal("加黑")){
                    $.ajax({
                        type: "POST",
                        url: "<%=basePath%>friends/jh",
                        data:{friendid:fid,status:"1"},
                        success: function(msg){
                            $(".fy2").text("该玩家已被你加入黑名单");
                            $("#jh").text("");
                            $("#jh").text("解黑");
                        }
                    });
                }
                if( $("#jh").text().equal("解黑")){
                    $.ajax({
                        type: "POST",
                        url: "<%=basePath%>friends/jh",
                        data:{friendid:fid,status:"0"},
                        success: function(msg){
                            $(".fy2").text("该玩家已被你加入黑名单");
                            $("#jh").text("");
                            $("#jh").text("加黑");
                        }
                    });
                }

            });

        });

    </script>
<jsp:include page="../common/kj.jsp"/>
  </body>
</html>


