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

font-size:20px;
margin-left:40px;
border:0px solid #ff0000;
background:#B520BD;
color:#ffffff;
}
.inputk{
    background:#1E0514;
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
.inputks{
    background: #a9a28f;
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
.quxiao{
    text-align: center;
    position: absolute;
    top:60px;
    left:250px;
    border:2px solid #ff0000;
    background:#ff9e00;
    color:#ff0000;
    width:50px;
    height: 20px;
    line-height:20px;
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
.queding{
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
    display: none;
}
.queding2{
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
    display: none;
}
.queding3{
    text-align: center;
    position: absolute;
    top:60px;
    left:50px;
    width:50px;
    height: 20px;
    line-height: 20px;
    border:2px solid #ff0000;
    background:#ff9e00;
    color:#ff0000; display: none;
}
.queding4{
    text-align: center;
    position: absolute;
    top:60px;
    left:50px;
    width:50px;
    height: 20px;
    line-height: 20px;
    border:2px solid #ff0000;
    background:#ff9e00;
    color:#ff0000; display: none;
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
    color:#ff0000; display: none;
}
</style>
  </head>
  
  <body>





<div class="kj" style="overflow-Y: auto;overflow-X:hidden;width:320px;">
 <div class="roleid"><b>广场</b></div>
   <div style="border:0px solid #fffb29; width:100%;">
   
  <br/> <br/>
 		<hr/>

 	<p class="yaoping"><a href="#"><span class="sy">赌骰子</span></a><a href="#"><span class="sy">猜拳</span></a><a href="#"><span class="sy">彩票</span></a></p>
      <div id="suoyou">
       <p style="color:#FFFB29;text-indent:10px; ">上期（<span id="qishu"></span>）开<span id="ne"></span>,<span id="ntwo"></span>,<span id="nth"></span> <span id="dxresult"></span></p>
       <p style="color:#FFFB29;text-indent:10px; " id="ttime"><span id="fen"></span>分<span id="miao"></span>秒后开盅，买定离手</p>
       <p style="color:#FFFB29;text-indent:10px; ">目前买小<span id="xiaosum">0</span>金<span id="xiaosumyin">0</span>银</p>
       <p style="color:#FFFB29;text-indent:10px; ">目前买大<span id="dasum">0</span>金<span id="dasumyin">0</span>银</p>
       <p style="color:#FFFB29;text-indent:10px; ">目前买单<span id="dansum">0</span>金<span id="dansumyin">0</span>银</p>
       <p style="color:#FFFB29;text-indent:10px; ">目前买双<span id="shuangsum">0</span>金<span id="shuangsumyin">0</span>银</p>
       <p style="color:#f0ee20;text-indent:10px; ">目前买豹子<span id="baozisum" style="color:#FFF032">0</span>金<span id="baozisumyin" style="color:#e0e0e0">0</span>银</p>
     <p class="yaoping"><a href="#">小（1赔2）<span id="xiaoj" class="sy">压金</span></a><a href="#"><span class="sy" id="xiaoy">压银</span></a></p>
    <p class="yaoping"><a href="#">大（1赔2）<span  id="daj" class="sy">压金</span></a><a href="#"><span class="sy" id="day">压银</span></a></p>
    <p class="yaoping"><a href="#">单（1赔2）<span  id="danj" class="sy">压金</span></a><a href="#"><span class="sy" id="dany">压银</span></a></p>
    <p class="yaoping"><a href="#">双（1赔2）<span  id="shaungj" class="sy">压金</span></a><a href="#"><span class="sy" id="shuangy">压银</span></a></p>
    <p class="yaoping"><a href="#">豹子（1赔10）<span id="baozij" class="sy">压金</span></a><a href="#"><span class="sy" id="baoziy">压银</span></a></p>

       <p style="color:#FFFB29;text-indent:10px; "><a href="<%=basePath%>gchang/yingjia" style="color:#FF7018; ">赢家</a> 获<span id="sumwjj">0</span>金<span id="sumwjy">0</span>银</p>
       <p class="yaoping"><a href="<%=basePath%>gchang/lishi"><span class="sy">历史查询</span></a><a href="<%=basePath%>gchang/touzhu"><span class="sy">投注记录</span></a></p>
       <p class="yaoping"><a href="<%=basePath%>gchang/moneypm"><span class="sy">赚钱排行</span></a><a href="#"><span class="sy">称号解析</span></a></p>
   </div>

</div>
</div>

<div class="inputk">

    <input type="text" id="shuru" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " style="width:250px;height:25px;margin-top: 20px;"/>
    <p id="tishiyu"></p>
    <div id= "q1" class="queding">确定</div>
    <div id= "q2" class="queding2">确定</div>
    <div id= "q3" class="queding3">确定</div>
    <div id= "q4" class="queding4">确定</div>
    <div id= "q5" class="queding5">确定</div>
    <div  class="quxiao">取消</div>


</div>
<div class="inputks">
    <p id="tst" style="color:#ff2821 "></p>
    <div class="quxiaos">确定</div>
</div>
<jsp:include  page="../common/kj.jsp"/>
  </body>
<script type="text/javascript">

   $(function() {
       window.setTimeout(sxmsg,1);
       window.setTimeout(sxtime,1);
       function sxtime(){
           $.ajax({
               type: "POST",
               url: "<%=basePath%>gchang/sxtime",
               data: "",
               success: function (msg) {
                   var sum=JSON.parse(msg);  //使用这个方法解析json
                   var times=sum.times;
                   //alert(times);
                   if(times==0){
                    $("#suoyou").html("");

                       $("#suoyou").html("结算中，请稍后。。。");

                   }else{
                       var minutes = Math.floor(times/1000/60)-Math.floor(times/1000/3600)*60;
                       var second = Math.floor(times/1000)-Math.floor(times/1000/60)*60;
                       $("#fen").text(minutes);
                       $("#miao").text(second);
                   }



               }
           });
           window.setTimeout(sxtime,500);
       }
       function sxmsg(){
           $.ajax({
               type: "POST",
               url: "<%=basePath%>gchang/sxsum",
               data:"",
               success: function(msg){
                   var sum=JSON.parse(msg);  //使用这个方法解析json
                   var dasum=sum.dasum;
                   var dasumyin=sum.dasumyin;
                   var xiaosum=sum.xiaosum;
                   var xiaosumyin=sum.xiaosumyin;
                   var dansum=sum.dansum;
                   var dansumyin=sum.dansumyin;
                   var shuangsum=sum.shuangsum;
                   var shuangsumyin=sum.shuangsumyin;
                   var baozisum=sum.baozisum;
                   var baozisumyin=sum.baozisumyin;
                   var totalsumyin=sum.totalsumyin;
                   var totalsumjin=sum.totalsumjin;



                  $("#qishu").text(sum.ydxid);
                   $("#ne").text(sum.ydxnum1);
                   $("#ntwo").text(sum.ydxnum2);
                   $("#nth").text(sum.ydxnum3);
                   $("#dxresult").text(sum.ydxresult);


                   $("#dasumyin").text(dasumyin);
                   $("#dasum").text(dasum);
                   $("#xiaosum").text(xiaosum);
                   $("#xiaosumyin").text(xiaosumyin);
                   $("#dansum").text(dansum);
                   $("#dansumyin").text(dansumyin);
                   $("#shuangsum").text(shuangsum);
                   $("#shuangsumyin").text(shuangsumyin);
                   $("#baozisum").text(baozisum);
                   $("#baozisumyin").text(baozisumyin);
                   $("#sumwjj").text(totalsumjin);
                   $("#sumwjy").text(totalsumyin);





               }






           });
           window.setTimeout(sxmsg,1000*3);
       }
       $(".quxiaos").click(function(){
           $(".inputks").css({"display": "none"});
           $(".quxiaos").css({"display":"none"});

       });
       $("#xiaoy").click(function () {
           $("#tishiyu").html("");
           $(".inputk").css({"display": "block"});
           $(".queding").css({"display": "block"});
           $(".quxiao").css({"display": "block"});
           $(".quxiao").click(function(){
               $(".inputk").css({"display": "none"});

               $(".queding").css({"display": "none"});
               $(".quxiao").css({"display": "none"});
           });
           $("#q1").click(function(){



              var yin=$("#shuru").val();
               if(yin==0||yin==null){
                   $("#tishiyu").text("输入金额不能为空");

               }

               if(yin>200000){
                   $("#tishiyu").text("输入金额不能大于20万");

               }
               if(yin>0&&yin<=200000){

                   $.ajax({
                       type: "POST",
                       url: "<%=basePath%>gchang/ddx",
                       data:{result:"小",yin:yin},
                       success: function(msg) {
                           $(".queding").css({"display": "none"});
                           $(".quxiao").css({"display": "none"});
                           $(".inputk").css({"display": "none"});
                           $("#shuru").val("");

                           $(".inputks").css({"display":"block"});
                           $(".quxiaos").css({"display":"block"});
                           var mg=JSON.parse(msg);  //使用这个方法解析json
                           var xinxi=mg.msg;

                           $("#tst").text(xinxi);


                       }
                   });
               }





           });



       });
       $("#day").click(function () {
           $("#tishiyu").html("");
           $(".inputk").css({"display": "block"});
           $(".queding2").css({"display": "block"});
           $(".quxiao").css({"display": "block"});
           $(".quxiao").click(function(){
               $(".inputk").css({"display": "none"});
               $(".queding2").css({"display": "none"});
               $(".quxiao").css({"display": "none"});
           });
           $("#q2").click(function(){
              // alert("q2");

               var yin=$("#shuru").val();
               if(yin==0||yin==null){
                   $("#tishiyu").text("输入金额不能为空");

               }
               if(yin>200000){
                   $("#tishiyu").text("输入金额不能大于20万");

               }
               if(yin>0&&yin<=200000){
                   $.ajax({
                       type: "POST",
                       url: "<%=basePath%>gchang/ddx",
                       data:{result:"大",yin:yin},
                       success: function(msg) {

                           $(".inputk").css({"display": "none"});
                           $(".queding2").css({"display": "none"});
                           $(".quxiao").css({"display": "none"});
                           $("#shuru").val("");
                           $(".inputks").css({"display":"block"});
                           $(".quxiaos").css({"display":"block"});
                           var mg=JSON.parse(msg);  //使用这个方法解析json
                           var xinxi=mg.msg;

                           $("#tst").text(xinxi);


                       }
                   });
               }

           });



       });
       $("#dany").click(function () {
           $("#tishiyu").html("");
           $(".inputk").css({"display": "block"});
           $(".queding3").css({"display": "block"});
           $(".quxiao").css({"display": "block"});
           $(".quxiao").click(function(){
               $(".inputk").css({"display": "none"});
               $(".queding3").css({"display": "none"});
               $(".quxiao").css({"display": "none"});
           });
           $("#q3").click(function(){

               //alert("1");
               var yin=$("#shuru").val();
               if(yin==0||yin==null){
                   $("#tishiyu").text("输入金额不能为空");

               }
               if(yin>200000){
                   $("#tishiyu").text("输入金额不能大于20万");

               }
               if(yin>0&&yin<=200000){
                   $.ajax({
                       type: "POST",
                       url: "<%=basePath%>gchang/ddx",
                       data:{result:"单",yin:yin},
                       success: function(msg) {

                           $(".inputk").css({"display": "none"});
                           $(".queding3").css({"display": "none"});
                           $(".quxiao").css({"display": "none"});
                           $("#shuru").val("");
                           $(".inputks").css({"display":"block"});
                           $(".quxiaos").css({"display":"block"});
                           var mg=JSON.parse(msg);  //使用这个方法解析json
                           var xinxi=mg.msg;

                           $("#tst").text(xinxi);


                       }
                   });
               }

           });



       });
       $("#shuangy").click(function () {
           $("#tishiyu").html("");
           $(".inputk").css({"display": "block"});
           $(".queding4").css({"display": "block"});
           $(".quxiao").css({"display": "block"});
           $(".quxiao").click(function(){
               $(".inputk").css({"display": "none"});
               $(".queding4").css({"display": "none"});
               $(".quxiao").css({"display": "none"});
           });
           $("#q4").click(function(){


               var yin=$("#shuru").val();
               if(yin==0||yin==null){
                   $("#tishiyu").text("输入金额不能为空");

               }
               if(yin>200000){
                   $("#tishiyu").text("输入金额不能大于20万");

               }
               if(yin>0&&yin<=200000){
                   $.ajax({
                       type: "POST",
                       url: "<%=basePath%>gchang/ddx",
                       data:{result:"双",yin:yin},
                       success: function(msg) {

                           $(".inputk").css({"display": "none"});
                           $(".queding4").css({"display": "none"});
                           $(".quxiao").css({"display": "none"});
                           $("#shuru").val("");
                           $(".inputks").css({"display":"block"});
                           $(".quxiaos").css({"display":"block"});
                           var mg=JSON.parse(msg);  //使用这个方法解析json
                           var xinxi=mg.msg;

                           $("#tst").text(xinxi);


                       }
                   });
               }

           });



       });
       $("#baoziy").click(function () {
           $("#tishiyu").html("");
           $(".inputk").css({"display": "block"});
           $(".queding5").css({"display": "block"});
           $(".quxiao").css({"display": "block"});
           $(".quxiao").click(function(){
               $(".inputk").css({"display": "none"});
               $(".queding5").css({"display": "none"});
               $(".quxiao").css({"display": "none"});
           });
           $("#q5").click(function(){


               var yin=$("#shuru").val();
               if(yin==0||yin==null) {
                   $("#tishiyu").text("输入金额不能为空");

               }
               if(yin>200000){
                   $("#tishiyu").text("输入金额不能大于20万");

               }
               if(yin>0&&yin<=200000){
                   $.ajax({
                       type: "POST",
                       url: "<%=basePath%>gchang/ddx",
                       data:{result:"豹子",yin:yin},
                       success: function(msg) {



                           $(".inputk").css({"display": "none"});
                           $(".queding5").css({"display": "none"});
                           $(".quxiao").css({"display": "none"});
                           $("#shuru").val("");
                           $(".inputks").css({"display":"block"});
                           $(".quxiaos").css({"display":"block"});
                           var mg=JSON.parse(msg);  //使用这个方法解析json
                           var xinxi=mg.msg;

                           $("#tst").text(xinxi);

                       }
                   });
               }

           });



       });
   });

</script>
</html>
