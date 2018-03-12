<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@include file="../common/tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
   
    
    <title>My JSP 'role_fujiang.jsp' starting page</title>
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
.kj{
background:#000031;
width:299px;
height:505px;
position:fixed;
left:32px;
top:35px;
color:#ffffff;

}
.rolename{
 	color:#ff0000;
 	text-indent:10px;
 	font-size:20px;
 
}
.rolename2{
 	color:#ff9e00;
 	text-indent:10px;
 	font-size:20px;
 
}
a{
text-decoration:none;
color: #c4fe96;
font-size:20px;
font-weight:bold;

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
.sy{
width:100px;
    height:30px;
    line-height: 30px;
font-size:25px;
    text-align: center;
margin-left:10px;
border:1px solid #ff0000;
background: #fff56f;
color: #ff3e12;}
.inputks{
    background: #e8f09e;
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
</style>
<script type="text/javascript">


$(function(){
 $("tr:even").css({"background":"#634163"});
  $("tr:odd").css({"background":"#CF3DF6"});
    $(".quxiaos").click(function(){
        $(".inputks").css({"display":"none"});

        $(".quxiaos").css({"display":"none"});
        window.location.reload();

    });
$("#syxf").click(function(){
    var id=$("#pid").val();
    var jid=$("#jid").val();
    var num=$("#zl").val();
    //alert(num);
    $.ajax({
        type: "POST",
        url: "<%=basePath%>fujiang/xinfashu",
        data: {id:id,num:num,jid:jid},
        success: function (msg) {
            $(".inputks").css({"display": "block"});
            $(".quxiaos").css({"display": "block"});
            var mg=JSON.parse(msg);  //使用这个方法解析json
            var msg=mg.msg;
            var sjmsg=mg.sjmsg;
            if(sjmsg==null){
                $("#tst").text(msg);
            }else{
                $("#tst").text(sjmsg);
            }

            $("#syxf").unbind("click");

        }


    });


});


})
 
</script>
  </head>
  
  <body>
  
  <div class="kj"style="overflow-Y: auto;overflow-X:hidden;width:320px;">
  
  
 

   <div style="border:0px solid #ff0000; width:300px;">
   
<input id="pid" type="hidden" value="${id}"/>
       <input id="jid" type="hidden" value="${jid}"/>
  <p style="text-indent:10px;line-height:30px;height:30px;width:100%;background:#634163;">
  请输入使用数量(1-10)本</p>
       <p style="text-indent:10px;line-height:30px;height:30px;width:100%;background:#634163;">
           <input id="zl" type="number"  min="1" max="10"style="background: #9cf8ff;height:25px;"></p>

  <span class="sy" id="syxf">确定</span></p>

       <%--通用消息框--%>
       <div class="inputks">
           <p id="tst" style="color:#ff2821;text-indent: 5px;font-size: 15px; "></p>
           <div class="quxiaos">确定</div>
       </div>

 		
		
 		
 		
  		
  	
  	
  		
  		
  		
  </div>
   </div>
  <jsp:include page="../common/kj.jsp"/>
  </body>
</html>
