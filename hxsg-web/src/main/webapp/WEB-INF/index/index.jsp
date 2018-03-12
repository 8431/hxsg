<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
  <connector implementation="org.mortbay.jetty.nio.SelectChannelConnector">
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
	border:1px solid #ff0000;
	width:360px;
	height:490px;
	
	position:fixed;
	left:0px;
	top:30px;
	background:#000000;
}
.top{
   position:fixed;
    top:0px;
    left:0px;
    float:left;
	background:url(../image/top2.png) #4C4539;
    width:360px;
    line-height:35px;
	height:35px;
	text-align:center;
	color:#A7D02D;
	text-shadow:1px 1px 5px #4E524B;
	font-weight:bold;
	background-size:100% 100%;

}
.m_left{
    float:left;
	
    width:160px;
	height:250px;

	
	
}
.m_left_t{
 	width:160px;
	height:123px;
	background:url(../image/juese.png);
	background-size:100% 100%;
}
.m_left_b{
 	width:160px;
	height:123px;
	background:#211400;
	border:1px solid #FF9900;
	font-size:5px;
}
.m_right{
    float:left;
	border:0px solid #ff0000;
    width:200px;
	height:250px;
	background-size:100% 100%;

	
	
}
.md{
    float:left;
	border:1px solid #ff0000;
    width:360px;
	height:38px;
position:absolute;
	top:250px;
	left:0px;
	
	
}
.fujinwanjia{
    float:left;
	border:0px solid #ff0000; 
    width:360px;
	height:30px;
	line-height:30px;
    position:absolute;
	top:282px;
	left:0px;
	font-size:20px;
	background:url(../image/fujinwanjia2.png);
	background-size:100% 100%;
	
}
.md_f{
    float:left;
	
    width:90px;
	height:35px;
	
	border:0px solid #ff9e00;
	
}

li{
float:left;
width:50px;
border:1px solid #ff0000;
width:50px;
background:#E1B205;
margin-left:40px;
margin-top:10px;
text-align:center;
color:#B520BD;
font-size:20px;
list-style:none;
}
.lis{
float:left;
height:32px;
border:1px solid #ff0000;
width:85px;

margin-left:10px;
margin-top:5px;
text-align:center;
color:#B520BD;
font-size:20px;

}
.juesename{
margin-top:12px;
width:110px;
line-height:20px;
height:20px;
border:0px solid #ff0000;
margin-left:55px;
text-align:center;
color:#ffffff;
font-size:16px;
font-weight:bold;
float:left;
background:#290900;
}
.juesefj{
margin-top:40px;
width:150px;
margin-left:5px;
height:40px;
border:1px solid #ff0000;

text-align:center;
color:#ffffff;
font-size:16px;
font-weight:bold;
float:left;
background:#290900 url(../image/juesefj.png) no-repeat;
background-size:100% 100%;
}
a{
color:white;
text-shadow:2px 2px 2px #ff0000;
text-decoration: none;
font-size:15px;
font-weight:bold;


}
.buttom{
position:absolute;
left:0px;
border:0px solid #ff9e00;
top:314px;
float:left;
width:360px;
height:225px;
background:url(../image/chatmsg.png);

}
.gonghui{
width:50px;
height:25px;
background:url(../image/gonghui.png);
background-size:100% 100%;
position:fixed;
left:202px;
top:345px;
}
.shijie{
width:50px;
height:25px;
background:url(../image/shijie.png);
background-size:100% 100%;
position:fixed;
left:250px;
top:345px;
}
.xitong{
width:50px;
height:25px;
background:url(../image/xitong.png);
background-size:100% 100%;
position:fixed;
left:300px;
top:345px;
}
.chat{
position:fixed;
left:0px;
border:3px solid #ff9e00;
top:375px;
float:left;
width:357px;
height:190px;



}
.lisgn{
float:left;
height:30px;
border:2px solid #ff0000;
width:50px;

margin-left:11px;
margin-top:6px;
text-align:center;
color:#B520BD;
font-size:20px;

}
.fy2{
background: url(../image/fy.png);
    width:340px;
    height: 290px;
    background-size: 100% 100%;
    position: fixed;
    top:150px;
    left:10px;
    display: none;

}
.send{
    background: url(../image/send.png);
    width:75px;
    height:25px;
    background-size: 100% 100%;
    position: absolute;
    top:220px;
    left:50px;


}
.quxiao{
    background: url(../image/quxiao.png);
    width:75px;
    height:25px;
    background-size: 100% 100%;
    position: absolute;
    top:220px;
    left:210px;


}
.quxiaos{
    text-align: center;
    position: absolute;
    top:60px;
    left:240px;
    border:2px solid #ff0000;
    background:#ff9e00;
    color:#ff0000;
    width:50px;
    height: 20px;
    line-height:20px;
    display: block;
}
.queding5{
    text-align: center;
    position: absolute;
    top:60px;
    left:50px;
    width:50px;
    height: 20px;
    line-height: 20px;
    border:2px solid #ff0000;
    background:#ff9e00;
    color:#ff0000;
    display: block;
}
.inputks{
    background: #8c7b25;
    width:340px;
    height: 100px;


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
.tishi{
    width:340px;
    height: 40px;

    position: absolute;
    top:180px;
    left:55px;
   font-weight: bold;


}
.sy{
    font-size: 25px;
    height:30px;
    line-height: 30px;
    border:2px solid #ff0000;
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
</style>
<script type="text/javascript">

        $(function(){

$("#mright").css({background:"url(../image/rw.png)"});
$("#mright").css({"background-size":"100% 100%"});
$("#rw").css({background:"url(../image/rw2.png)"});
$("#ss").css({background:"url(../image/ss2.png)"});
$("#yd").css({background:"url(../image/yd2.png)"});
$("#gn").css({background:"url(../image/gn2.png)"});
$("#rw").css({"background-size":"100% 100%"});
$("#ss").css({"background-size":"100% 100%"});
$("#yd").css({"background-size":"100% 100%"});
$("#gn").css({"background-size":"100% 100%"});
	$("#ss").click(function(){	
	$("#mright").html("");
	
	
	
	$("#mright").html("<a href='<%=basePath%>yao/login'><span id='yiguan' class='lis'></a><a href='钱庄.jsp'><span id='yiguan' class='lis'></a><a href='<%=basePath%>yg/login'><span id='yiguan' class='lis'></a><a href='<%=basePath%>sc/shichang'><span id='yiguan' class='lis'></a><a href='<%=basePath%>gchang/gc'><span id='yiguan' class='lis'></a><a href='医馆.html'><span id='yiguan' class='lis'></a><a href='#'><span id='zhanchang' class='lis'></a><a href='医馆.html'><span id='yiguan' class='lis'></a>");
        $("#zhanchang").click(function(){
            $("#zhu").css({"display":"none"});
            $("#fu").css({"display":"block"});

        });
            $("#mright").css({background:"url(../image/ss.png)"});
	$("#mright").css({"background-size":"100% 100%"});
	$("#ss").css({background:"url(../image/ss1.png)"});
	$("#gn").css({background:"url(../image/gn2.png)"});
	$("#rw").css({background:"url(../image/rw2.png)"});
	$("#yd").css({background:"url(../image/yd2.png)"});
	$("#rw").css({"background-size":"100% 100%"});
	$("#ss").css({"background-size":"100% 100%"});
	$("#yd").css({"background-size":"100% 100%"});
	$("#gn").css({"background-size":"100% 100%"});
	});
	$("#gn").click(function(){
	$("#mright").html("");
	$("#mright").html("<a href='医馆.jsp'><span id='yiguan' class='lisgn'></a><a href='<%=basePath%>wupin/wupin'><span id='yiguan' class='lisgn'></a><a href='<%=basePath%>yg/fujiang'><span id='yiguan' class='lisgn'></a><a href='4医馆.jsp'><span id='yiguan' class='lisgn'></a><a href='5医馆.jsp'><span id='yiguan' class='lisgn'></a><a href='<%=basePath%>friends/hy'><span id='yiguan' class='lisgn'></a><a href='<%=basePath%>youjian/yj'><span id='yiguan' class='lisgn'></a><a href='医馆.jsp'><span id='yiguan' class='lisgn'></a><a href='医馆.jsp'><span id='yiguan' class='lisgn'></a><a href='医馆.jsp'><span id='yiguan' class='lisgn'></a><a href='医馆.jsp'><span id='yiguan' class='lisgn'></a><a href='医馆.jsp'><span id='yiguan' class='lisgn'></a><a href='医馆.jsp'><span id='yiguan' class='lisgn'></a><a href='医馆.jsp'><span id='yiguan' class='lisgn'></a><a href='医馆.jsp'><span id='yiguan' class='lisgn'></a>");
	
	$("#mright").css({background:"url(" +
            "../image/gn.png)"});
	$("#mright").css({"background-size":"100% 100%"});
	$("#ss").css({background:"url(../image/ss2.png)"});
	$("#gn").css({background:"url(../image/gn1.png)"});
	$("#rw").css({background:"url(../image/rw2.png)"});
	$("#yd").css({background:"url(../image/yd2.png)"});
	$("#rw").css({"background-size":"100% 100%"});
	$("#ss").css({"background-size":"100% 100%"});
	$("#yd").css({"background-size":"100% 100%"});
	$("#gn").css({"background-size":"100% 100%"});
	});
	$("#rw").click(function(){
	$("#mright").html("");
	$("#mright").css({background:"url(../image/rw.png)"});
	$("#mright").css({"background-size":"100% 100%"});
	$("#ss").css({background:"url(../image/ss2.png)"});
	$("#gn").css({background:"url(../image/gn2.png)"});
       
	$("#rw").css({background:"url(../image/rw1.png)"});
	$("#yd").css({background:"url(../image/yd2.png)"});
	$("#rw").css({"background-size":"100% 100%"});
	$("#ss").css({"background-size":"100% 100%"});
	$("#yd").css({"background-size":"100% 100%"});
	$("#gn").css({"background-size":"100% 100%"});
	});
	$("#yd").click(function(){
	$("#mright").html("");
	$("#mright").css({background:"url(../image/rw.png)"});
	$("#mright").css({"background-size":"100% 100%"});
	$("#ss").css({background:"url(../image/ss2.png)"});
	$("#gn").css({background:"url(../image/gn2.png)"});
	$("#rw").css({background:"url(../image/rw2.png)"});
	$("#yd").css({background:"url(../image/yd1.png)"});
	$("#rw").css({"background-size":"100% 100%"});
	$("#ss").css({"background-size":"100% 100%"});
	$("#yd").css({"background-size":"100% 100%"});
	$("#gn").css({"background-size":"100% 100%"});
	});
	

});
</script>

  </head>
  
   
  <body >


<div id="zhu" style="display: block">
  <div class="index" >
      <div class="top"></div>
   <div class="m_left">
    <a href="<%=basePath%>zhuce/roledetail"><div class="juesename">${role.juesename }</div></a>
      <a href="<%=basePath%>yg/fujiang"><div class="juesefj"></div></a>
   <div class="m_left_t">



   </div>
   <div class="m_left_b">
     <font color="#ffffff" size="3">&nbsp;&nbsp;职业：${role.dengji }级${role.zhiye }</font><br/>
     <font color="#ffffff" size="3">&nbsp;&nbsp;银：${role.yin }</font><br/>
     <font color="#D0CF00" size="3">&nbsp;&nbsp;金：${role.jin }</font><br/>
     <font color="#0DABE5" size="3">&nbsp;&nbsp;升级还需经验：</font><br/>
      <font color="#0DABE5" size="3">&nbsp;&nbsp;${role.jingyan }</font><br/>


   </div>

   </div>
    <div class="m_right" id="mright">





    </div>
     <div class="md">
     <div class="md_f" id="rw"></div> <div id="ss" class="md_f"></div>
     <div class="md_f" id="yd"></div> <div  class="md_f" id="gn"></div>
     </div>
   <div class="fujinwanjia"><a href="<%=basePath%>friends/onlinesum?juzhudi=${role.juzhudi }&status=${role.status }">附近玩家(100人)</a></div>
   <div class="buttom">
   <div id="chat" class="chat">

   </div>
<div id="fayan" style="background:url(../image/fytx.gif) ;background-position:220px -100px;position:absolute;top:100px;left:300px;line-height:50px;height: 50px;font-size:20;width: 60px;font-weight:bold;color:#190fff;text-align: center;">聊</div>


   </div>
   </div>
    <div class="gonghui"></div>
      <div class="shijie"></div>
      <div class="xitong"></div>
   <div id="fy2" class="fy2">


       <textarea maxlength="48" onchange="this.value=this.value.substring(0,48)" onkeydown="this.value=this.value.substring(0, 48)" onkeyup="this.value=this.value.substring(0, 48)" id="msg" name="message" rows="5" cols="30" style="margin-top:80px;margin-left:60px;"></textarea>
<div class="send"></div>
<div  class="quxiao"></div>
       <div  class="tishi"></div>

   </div>
    <%--&lt;%&ndash;通用消息框&ndash;%&gt;--%>
    <%--<div class="inputks">--%>
        <%--<p id="tst" style="color:#ffeb8a "></p>--%>
        <%--<div id= "q5" class="queding5">确定</div>--%>
        <%--<div  class="quxiaos">取消</div>--%>
    <%--</div>--%>
   <jsp:include page="../common/indexkj.jsp"/>
    </div>
  <div id="fu"  style="display: none">
      <div class="kj" style="overflow-Y: auto;overflow-X:hidden;width:320px;">
          <div class="roleid"><b>战场</b></div>
          <div style="border:0px solid #ff0000; width:100%;">

              <br/> <br/>
              <hr/>


              <p class="yaoping"><span class="sy" id="bzdk">宝藏洞口</span></p>
              <p class="yaoping">这是一块杀人越货，无恶不作的罪恶之地也是一块，一夜暴富的财富之地。挖宝时不要相信任何人！</p>

              <p class="yaoping"><span class="sy">战场(未开放)</span></p>
              <p class="yaoping">尸横遍野，刀光剑影。一块嗜血的杀戮之地，没实力的请远离此地！</p>






          </div>

      </div>


      <jsp:include page="../common/kj.jsp"/>
  </div>
  </body>
  <script type="text/javascript">
      <%--通用消息框--%>
      $(".quxiaos").click(function(){
          $(".inputks").css({"display":"none"});
          $(".queding5").css({"display":"none"});
          $(".quxiaos").css({"display":"none"});

      });
      $("#q5").click(function(){


          $.ajax({
              type: "POST",
              url: "<%=basePath%>zhanchang/fee",
              data:"",
              success: function (msg) {
                  var mg=JSON.parse(msg);  //使用这个方法解析json
                  var msg=mg.msg;
                  $("#tst").text(msg);



              }
          });
              });
      <%--通用消息框--%>

      function update(num) {
          var secs = 5;
          if (num == secs) {

              $("#msg").attr("disabled", false);
              $("#msg").css({"background-color":"#ffffff"});
          }
          else {
               printnr = secs - num;
           $(".tishi").text("发送中，距离下次操作还剩" + printnr + "秒");
              if(printnr==1){
                  $(".tishi").text("可以发言了！");
              }
              $("#msg").attr("disabled", true);


          }
      }



     $(function(){

         $(".quxiao").click(function(){

             $("#fy2").css({"display":"none"});
         });
         $("#fayan").click(function(){

             $("#fy2").css({"display":"block"});

         });
         $(".send").click(function(){
            var date=new Date();
            var hh= date.getHours();
             var mm=date.getMinutes();

            
             $("#rtime").text(hh+":"+mm);
             var rmsg=$("#msg").val();


             $("#fy2").css({"display":"none"});
             for (i = 1; i <= 5; i++) {
                 window.setTimeout("update( " + i + ") ", i * 1000);
             }
             $("#msg").css({"background-color":"#3E3E3E"});

             $.ajax({
                 type: "POST",
                 url: "<%=basePath%>mg/msg",
                 data:{rolemsg:rmsg,type:"3"},
                 success: function(msg){
                     var jg=JSON.parse(msg);  //使用这个方法解析json
                     var p=jg.rmsg;

                     var s="";
                     if(jg.code==0){

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


                             var	a="<p style='text-indent:10px;font-weight:bold;color:#ffffff;fontsize:10px;'><span >"+times+"</span>&nbsp;<font style='color:FFEE1D'>"+p[i].rolename+"</font>:<span>"+p[i].message+"</span></p>";

                             s=s+a;

                         }
                     }

                     $("#chat").html(s);



                 }

             });


         });
         function sxmsg(){
             $.ajax({
                 type: "POST",
                 url: "<%=basePath%>mg/sxmsg",
                 data:"",
                 success: function(msg){

                     var jg=JSON.parse(msg);  //使用这个方法解析json
                     var p=jg.rmsg;

                     var s="";
                     if(jg.code==0){

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


                             var	a="<p style='text-indent:10px;font-weight:bold;color:#ffffff;fontsize:10px;'><span >"+times+"</span>&nbsp;<font style='color:#FFEE1D'>"+p[i].rolename+"</font>:<span>"+p[i].message+"</span></p>";

                             s=s+a;

                         }
                     }

                     $("#chat").html(s);



                 }

             });
             window.setTimeout(sxmsg,500);
         }
         window.setTimeout(sxmsg,1);
     });
//宝藏洞口

    function yzwb(){

        $(".inputks").css({"display":"block"});
        $(".queding5").css({"display":"block"});
        $(".quxiaos").css({"display":"block"});

        $.ajax({
            type: "POST",
            url: "<%=basePath%>zhanchang/yzwb",
            data:"",
            success: function (msg) {
                var mg=JSON.parse(msg);  //使用这个方法解析json
                var msg=mg.msg;
                $("#tst").text(msg);



            }
        });
    }


      $("#bzdk").click(function(){

        $("#ss").unbind("click");
          $("#mright").html("");
          $("#mright").html("<a href='<%=basePath%>zhanchang/fee'><span  class='lis'></a>")

          $("#mright").css({background:"url(../image/bdss.png)"});
          $("#mright").css({"background-size":"100% 100%"});

          $("#ss").click(function(){
              $("#mright").html("");
              $("#mright").html("<a href='<%=basePath%>zhanchang/fee'><span  class='lis'></a>")

              $("#mright").css({background:"url(../image/bdss.png)"});
              $("#mright").css({"background-size":"100% 100%"});
              $("#ss").css({background:"url(../image/ss1.png)"});
              $("#gn").css({background:"url(../image/gn2.png)"});
              $("#rw").css({background:"url(../image/rw2.png)"});
              $("#yd").css({background:"url(../image/yd2.png)"});
              $("#rw").css({"background-size":"100% 100%"});
              $("#ss").css({"background-size":"100% 100%"});
              $("#yd").css({"background-size":"100% 100%"});
              $("#gn").css({"background-size":"100% 100%"});
              $("#rw").unbind("click");
          });
          $("#rw").unbind("click");
          $("#zhu").css({"display":"block"});
          $("#fu").css({"display":"none"});


      });

  </script>
</html>
