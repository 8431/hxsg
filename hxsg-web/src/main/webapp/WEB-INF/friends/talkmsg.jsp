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
.syss{
    position: fixed;
width:50px;
font-size:20px;
left:100px;
top:160px;
border:1px solid #ff0000;
background:#ff9e00;
color:#ff0000;

}
.shuaxin{
    width:50px;
    font-size:20px;
    position: fixed;
    left:190px;
    border:1px solid #ff0000;
    background:#ff9e00;
    color:#ff0000;
    top:160px;
}
.tmsg{
    float:left;
    width:300px;
    height:360px;
    font-size:20px;
    position: fixed;
    left:30px;
    border:2px solid rgba(255, 41, 247, 0.90);
    background: #1e315f;
    color: #f4ff1c;
    top:190px;

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

  <div class="kj" style="overflow-Y: auto;overflow-X:hidden;width:320px;">
   <div class="roleid"><b>与【${msg.friendname}】聊天</b></div>
   <div style="border:0px solid #ff0000; width:100%;">
       <input   id="fid" type="hidden" value="${msg.friendid}"/>
       <input id="rid"  type="hidden" value="${msg.roleid}"/>
       <input id="fname"  type="hidden" value="${msg.friendname}"/>
       <input id="rname"  type="hidden" value="${msg.rolename}"/>
  <br/> <br/>


            <textarea maxlength="48" onchange="this.value=this.value.substring(0,48)" onkeydown="this.value=this.value.substring(0, 48)" onkeyup="this.value=this.value.substring(0, 48)" id="msg2" name="message" rows="2" cols="25" style="margin-top:20px;margin-left:60px;"></textarea>
       <div class='syss'>发送</div> <div class='shuaxin'>刷新</div>
       <div id="talkmsg" class="tmsg" style="overflow-Y: auto;overflow-X:hidden;">


       </div>


  		
  </div>
 </div>

  <jsp:include page="../common/kj.jsp"/>

  </body>
  <script type="text/javascript" >
      $(function(){
          $(".shuaxin").click(function(){
              var fid=$("#fid").val();
              var rid=$("#rid").val();


              var fname=$("#fname").val();
              var rname=$("#rname").val();
              $.ajax({
                  type: "POST",
                  url: "<%=basePath%>friends/shuaxinmsg",
                  data:{friendid:rid,roleid:fid,friendname:rname,rolename:fname},
                  success: function(msg){

                      $("#talkmsg").html("");
                      var fd=JSON.parse(msg);  //使用这个方法解析json
                      var p=fd.talkmsg;
                      var s="";
                      if(fd.code==0){
                          for(var i=0;i<p.length;i++){
                              var d= new Date(p[i].data);
                              var hh=d.getHours();
                              var mm= d.getMinutes();

                              if(mm<10){
                                  mm="0"+mm;
                              }
                              if(hh<10){
                                  hh="0"+hh;
                              }

                              var times=hh+":"+mm;

                              var a=" <p style='text-indent:10px;font-weight:bold;color:#ffffff;font-size:15px;'><span >"+times+"</span>&nbsp;<font style='color:#FFEE1D'>"+p[i].friendname+"</font>:<span>"+p[i].message+"</span></p>";

                              s=s+a;


                          }
                          $("#talkmsg").html(s);
                      }}
              });


          });
          $(document).ready(function(){

                  var fid=$("#fid").val();
                  var rid=$("#rid").val();


                  var fname=$("#fname").val();
                  var rname=$("#rname").val();
                  $.ajax({
                      type: "POST",
                      url: "<%=basePath%>friends/shuaxinmsg",
                      data:{friendid:rid,roleid:fid,friendname:fname,rolename:rname},
                      success: function(msg){

                          $("#talkmsg").html("");
                          var fd=JSON.parse(msg);  //使用这个方法解析json
                          var p=fd.talkmsg;
                          var s="";
                          if(fd.code==0){
                              for(var i=0;i<p.length;i++){
                                  var d= new Date(p[i].data);
                                  var hh=d.getHours();
                                  var mm= d.getMinutes();

                                  if(mm<10){
                                      mm="0"+mm;
                                  }
                                  if(hh<10){
                                      hh="0"+hh;
                                  }

                                  var times=hh+":"+mm;

                                  var a=" <p style='text-indent:10px;font-weight:bold;color:#ffffff;font-size:15px;'><span >"+times+"</span>&nbsp;<a href='#'><font style='color:#FFEE1D'>"+p[i].friendname+"</a></font>:<span>"+p[i].message+"</span></p>";

                                  s=s+a;


                              }
                              $("#talkmsg").html(s);
                          }}
                  });


          });
          $(".syss").click(function(){
              var fid=$("#fid").val();
              var rid=$("#rid").val();
              var msg=$("#msg2").val();

              var fname=$("#fname").val();
              var rname=$("#rname").val();
              $.ajax({
                  type: "POST",
                  url: "<%=basePath%>friends/sendmsgs",
                  data:{friendid:rid,roleid:fid,message:msg,friendname:rname,rolename:fname},
                  success: function(msg){

                      var fid=$("#fid").val();
                      var rid=$("#rid").val();


                      var fname=$("#fname").val();
                      var rname=$("#rname").val();
                      $.ajax({
                          type: "POST",
                          url: "<%=basePath%>friends/shuaxinmsg",
                          data:{friendid:rid,roleid:fid,friendname:rname,rolename:fname},
                          success: function(msg){
                              $("#msg2").val("");
                              $("#talkmsg").html("");
                              var fd=JSON.parse(msg);  //使用这个方法解析json
                              var p=fd.talkmsg;
                              var s="";
                              if(fd.code==0){
                                  for(var i=0;i<p.length;i++){
                                      var d= new Date(p[i].data);
                                      var hh=d.getHours();
                                      var mm= d.getMinutes();

                                      if(mm<10){
                                          mm="0"+mm;
                                      }
                                      if(hh<10){
                                          hh="0"+hh;
                                      }

                                      var times=hh+":"+mm;

                                      var a=" <p style='text-indent:10px;font-weight:bold;color:#ffffff;font-size:15px;'><span >"+times+"</span>&nbsp;<font style='color:#FFEE1D'>"+p[i].friendname+"</font>:<span>"+p[i].message+"</span></p>";

                                      s=s+a;


                                  }
                                  $("#talkmsg").html(s);
                              }}
                      });




                  }
              });

          });


      });
  </script>
</html>
