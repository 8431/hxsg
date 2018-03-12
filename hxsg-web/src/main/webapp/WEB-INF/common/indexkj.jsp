<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <script type="text/javascript" src="<%=basePath%>jQuery/jquery-1.4.2.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.left{
    float:left;
    background-size:100% 100%;

	position:fixed;
	left:0px;
	top:35px;
	height:540px;
    width:32px;
	 background:url(../image/rolescoot.png);


}
.right{
    float:left;
	
   background-size:100% 100%;
	position:fixed;
	left:331px;
	top:35px;
	height:540px;
	 width:32px;
	 background:url(../image/rolescoot.png);

}
.fh{
position:fixed;
top:537px;
left:30px;
width:302px;
height:38px;
border:0px solid #ff0000;
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
 .news{
     background:url("../image/news.gif");
     position:fixed;
     top:5px;
     left:300px;
     background-size:100% 100%;
     width:70px;
     height:25px;
     display:none;

 }
.fy{
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
.newsure{
    background: url(../image/hy_sure.png);
    width:90px;
    height: 35px;
    background-size: 100% 100%;
    position: fixed;
    top:250px;
    left:60px;
    display:none;

}
.newjujue{
    background: url(../image/hy_jujue.png);
    width:90px;
    height: 35px;
    background-size: 100% 100%;
    position: absolute;
    top:250px;
    left:200px;
    display:none;

}
.sure{
    background: url(../image/sure.jpg);
    width:90px;
    height: 35px;
    background-size: 100% 100%;
    position: absolute;
    top:250px;
    left:130px;
    display:none;

}
</style>
  </head>
  
  <body onload="checkmsg()">

  <div class="news" ></div>
  <div class="newsure"></div>
  <div class="newjujue"></div>
  <div class="sure"></div>
  <input type="hidden" id="fid"/>
  <input type="hidden" id="rfmsgid"/>
  <div class="fy"></div>


  </body>
  <script type="text/javascript">
      function checkmsg(){

          $.ajax({
              type: "POST",
              url: "<%=basePath%>friends/yzhy",
              data:"",
              success: function(msg){

                  var jg=JSON.parse(msg);  //使用这个方法解析json


                  if(jg.code==0){
                      $(".news").css({"display":"block"});

                  }else{
                      $(".news").css({"display":"none"});

                  }



              }

          });

          window.setTimeout(checkmsg,3000);
      }
//      window.setTimeout(checkmsg,1);
      $(function(){
          $(".news").click(function(){
              $.ajax({
                  type: "POST",
                  url: "<%=basePath%>friends/tjhy",
                  data:"",
                  success: function(msg){

                      var jg=JSON.parse(msg);  //使用这个方法解析json
                      var p=jg.tjnews;

                      if(jg.code==0){
                          if(p.type=="加好友"){

                             $(".fy").text("");
                              $(".fy").text(p.message);
                              $("#fid").val(p.friendid);

                              $("#rfmsgid").val(p.id);
                              $(".fy").css({"display":"block"});
                              $(".newsure").css({"display":"block"});

                              $(".newjujue").css({"display":"block"});

                          }
                          if(p.type=="邮件"){
                              $(".fy").text("");
                              $(".fy").text(p.message);
                              $("#rfmsgid").val(p.id);
                              $(".sure").css({"display":"block"});
                              $(".fy").css({"display":"block"});
                          }
                          if(p.type=="通知"){
                              $(".fy").text("");
                              $(".fy").text(p.message);
                              $("#rfmsgid").val(p.id);
                              $(".sure").css({"display":"block"});
                              $(".fy").css({"display":"block"});
                          }
                          if(p.type=="私聊"){
                              var ids=p.id;
                              $.ajax({
                                  type: "POST",
                                  url: "<%=basePath%>friends/readmsg",
                                  data:{id:ids},
                                  success: function(msg){


                                      window.top.location.href="<%=basePath%>/friends/talkmsgs?roleid="+p.roleid+"&friendid="+p.friendid+"&friendname="+p.friendname+"&rolename="+p.rolename;
                                  }



                              });
                          }









                      }

                  }
              });
          });
          $(".newsure").click(function(){
              var fid=$("#fid").val();
              var id=$("#rfmsgid").val();

              $.ajax({
                  type: "POST",
                  url: "<%=basePath%>friends/suretjhy",
                  data:{friendid:fid,id:id},
                  success: function(msg){

                       $(".newsure").css({"display":"none"});
                       $(".newjujue").css({"display":"none"});
                          $(".fy").css({"display":"none"});
                      $(".news").css({"display":"none"});




                  }
              });
          });

          $(".newjujue").click(function(){

              var fid=$("#fid").val();
              var id=$("#rfmsgid").val();
              $.ajax({
                  type: "POST",
                  url: "<%=basePath%>friends/newjujue",
                  data:{friendid:fid,id:id},
                  success: function(msg){
                      $(".newsure").css({"display":"none"});
                      $(".newjujue").css({"display":"none"});
                      $(".fy").css({"display":"none"});
                      $(".news").css({"display":"none"});
                  }
              });
          });
          $(".sure").click(function(){
              var ids=$("#rfmsgid").val();

              $.ajax({
                  type: "POST",
                  url: "<%=basePath%>friends/surebottun",
                  data:{id:ids},
                  success: function(msg){

                      $(".sure").css({"display":"none"});
                      $(".fy").css({"display":"none"});
                      $(".news").css({"display":"none"});
                      if(msg.indexOf("邮件")){
                          window.top.location.href="<%=basePath%>/youjian/wdyj";


                      }

                  }
              });
          });


      });


  </script>
</html>
