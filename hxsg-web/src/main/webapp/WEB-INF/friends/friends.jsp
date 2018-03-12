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
<style type="text/css">
a{
text-decoration:none;

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
margin-left:130px;
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
.name{
    color:#FF9A0F;
    font-weight: bold;

}
</style>
<script type="text/javascript">
$(function(){
    $(document).ready(function(){
        $("#zhawu").css({ "background": "#E1B205" });
        $("#zhuangbei").css({ "background": "#E1B205" });
        $("#kuangshi").css({ "background": "#E1B205" });
        $("#yaopin").css({ "background": "#000031" });
        $.ajax({
            type: "POST",
            url: "<%=basePath%>friends/fd",
            data:{type:"好友"},
            success: function(msg){

                $("#xswp").html("");
                var fd=JSON.parse(msg);  //使用这个方法解析json
                var p=fd.friends;

                var s="";
                if(fd.code==0){

                    for(var i=0;i<p.length;i++){


                        var	a="<p style='text-indent:10px;color:#ff0000;line-height:30px;height:30px;width:100%;background:#634163'>"+
                                "<a href='<%=basePath%>friends/wjxx?id="+p[i].friendid+"' class='name'>"+p[i].friendname+"</a> <a href='<%=basePath%>friends/talkmsgs?friendid="+p[i].friendid+"&roleid="+p[i].roleid+"&friendname="+p[i].friendname+"&rolename="+p[i].rolename+"'><span class='sy'>聊天</span></a></p>";

                        s=s+a;


                    }
                    $("#xswp").html(s);
                }}
        });

    });
 $("#zhuangbei").click(function(){
 $("#zhuangbei").css({ "background": "#000031" });
 $("#zhawu").css({ "background": "#E1B205" });
 $("#kuangshi").css({ "background": "#E1B205" });
 $("#yaopin").css({ "background": "#E1B205" });
     $.ajax({
         type: "POST",
         url: "<%=basePath%>friends/fd",
         data:{type:"亲人"},
         success: function(msg){

             $("#xswp").html("");
             var fd=JSON.parse(msg);  //使用这个方法解析json
             var p=fd.friends;

             var s="";
             if(fd.code==0){

                 for(var i=0;i<p.length;i++){


                     var	a="<p style='text-indent:10px;color:#ff0000;line-height:30px;height:30px;width:100%;background:#634163'>"+
                             "<a href='#'>"+p[i].friendname+"</a> <a href='friends/msg?id="+p[i].id+"'><span class='sy'>聊天</span></a></p>";

                     s=s+a;


                 }
                 $("#xswp").html(s);
             }}
     });
 
  ////////////////////////
});





 $("tr:even").css({"background":"#634163"});

	 
	
 

 $("#yaopin").click(function(){
	 $("#zhawu").css({ "background": "#E1B205" });
	 $("#zhuangbei").css({ "background": "#E1B205" });
	 $("#kuangshi").css({ "background": "#E1B205" });
 	 $("#yaopin").css({ "background": "#000031" });
         $.ajax({
         type: "POST",
         url: "<%=basePath%>friends/fd",
         data:{type:"好友"},
         success: function(msg){

             $("#xswp").html("");
             var fd=JSON.parse(msg);  //使用这个方法解析json
             var p=fd.friends;

             var s="";
             if(fd.code==0){

                 for(var i=0;i<p.length;i++){


                         var	a="<p style='text-indent:10px;color:#ff0000;line-height:30px;height:30px;width:100%;background:#634163'>"+
                                 "<a href='<%=basePath%>friends/wjxx?id="+p[i].friendid+"' class='name'>"+p[i].friendname+"</a> <a href='<%=basePath%>friends/talkmsgs?friendid="+p[i].friendid+"&roleid="+p[i].roleid+"&friendname="+p[i].friendname+"&rolename="+p[i].rolename+"'><span class='sy'>聊天</span></a></p>";

                         s=s+a;


             }
             $("#xswp").html(s);
         }}
         });

});
 $("#zhawu").click(function(){

 $("#zhawu").css({ "background": "#000031" });
 $("#zhuangbei").css({ "background": "#E1B205" });
 $("#kuangshi").css({ "background": "#E1B205" });
 $("#yaopin").css({ "background": "#E1B205" });
     $.ajax({
         type: "POST",
         url: "<%=basePath%>friends/fd",
         data:{type:"仇人"},
         success: function(msg){

             $("#xswp").html("");
             var fd=JSON.parse(msg);  //使用这个方法解析json
             var p=fd.friends;

             var s="";
             if(fd.code==0){

                 for(var i=0;i<p.length;i++){


                     var	a="<p style='text-indent:10px;color:#ff0000;line-height:30px;height:30px;width:100%;background:#634163'>"+
                             "<a href='#'>"+p[i].friendname+"</a> <a href='friends/msg?id="+p[i].id+"'><span class='sy'>忘记</span></a></p>";

                     s=s+a;


                 }
                 $("#xswp").html(s);
             }}
     });

 });
    $("#kuangshi").click(function(){

        $("#zhawu").css({ "background": "#E1B205" });
        $("#zhuangbei").css({ "background": "#E1B205" });
        $("#kuangshi").css({ "background": "#000031" });
        $("#yaopin").css({ "background": "#E1B205" });
        $.ajax({
            type: "POST",
            url: "<%=basePath%>wupin/zhawu",
            data:"",
            success: function(msg){
                $("#xswp").html("");
                var message=JSON.parse(msg);  //使用这个方法解析json
                var p=message.jjun;

                var s="";
                if(message.code==0){
                    for(var i=0;i<p.length;i++){
                        if(p[i].num>0){
                            var	a="<p style='text-indent:10px;color:#ff0000;line-height:30px;height:30px;width:100%;background:#634163'>"+
                                    p[i].jjname+"("+p[i].num+")<a href='jjunaction?id="+p[i].id+"'><span class='sy'>使用</span></a></p>";

                            s=s;
                        }
                    }
                }
                $("#xswp").html(s);
            }

        });

    });


});

 
</script>
  </head>
  
  <body>
  <div class="kj" style="overflow-Y: auto;overflow-X:hidden;width:320px;">
   <div class="roleid"><b>好友</b></div>
   <div style="border:0px solid #ff0000; width:100%;">
   
  <br/> <br/>
       <input type="text" style="margin-left:10px;width:80px;"/><span>搜ID</span>

       <input type="text" style="margin-left:2px;width:80px;"/><span>搜姓名</span>
 		<ul>
 		<li id="yaopin" >好友</li>
 		<li id="kuangshi">最近</li>
 		<li id="zhuangbei">亲人</li>
 		<li id="zhawu">仇人</li>
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
