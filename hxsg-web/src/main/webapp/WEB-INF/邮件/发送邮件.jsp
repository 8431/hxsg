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
      <script type="text/javascript" src="<%=basePath%>jQuery/jquery-1.4.2.js"></script>

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.kj{
background:#000031;
width:299px;
height:505px;
position:fixed;
left:32px;
top:35px;
color: #2629ff;

}
.kj3{
    background:#000031;
    width:299px;
    height:505px;
    position:fixed;
    left:32px;
    top:35px;
    color:#ffffff;
    display:none;

}
.kjt{
    background:#8c8c17;
    width:320px;
    height:500px;
    position:fixed;
    left:32px;
    top:50px;
    color:#ffffff;
    display:none;

}

.yaoping{
color: #ffffa4;
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
margin-left:60px;
border:0px solid #ff0000;
background:#B520BD;
color:#ffffff;
}
.inputks{
    background: #8c7b25;
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
.chexiao{
    text-align: center;
    position: fixed;
    top:503px;
    left:10px;
    border:2px solid #ff0000;
    background:#ff9e00;
    color:#ff0000;
    width:320px;
    height: 30px;
    line-height:30px;
    display: block;
}
.fujian{
    text-align: center;
    position: fixed;
    top:490px;
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
  </head>
  
  <body>





<div class="kj" style="overflow-Y: auto;overflow-X:hidden;width:320px;">
 <div class="roleid"><b>发送邮件</b></div>
   <div style="border:0px solid #ff0000; width:100%;">
   
  <br/> <br/>
 		<hr/>
 		
 	<p class="yaoping">欢迎客官，本店提供邮寄服务！</p>
 	<p class="yaoping">收件人ID</p>
       <input type="text" id="roleid" value="${receiveid}" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " style="width:100px;height:25px;margin-top:0px;"/>
       <p class="yaoping"><a href="#"><span class="sy" id="yjwp">邮寄物品</span></a></p>
       <p class="yaoping" >赠送银两</p>

       <input type="text" id="roleyin" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " style="width:100px;height:25px;margin-top:0px;"/>
       <p class="yaoping">附加信息</p>

       <textarea maxlength="30" onchange="this.value=this.value.substring(0,30)" onkeydown="this.value=this.value.substring(0,30)" onkeyup="this.value=this.value.substring(0,30)" id="msg" rows="2" cols="30"  ></textarea>
       <p class="yaoping" >邮包附件</p>
       <div id="fujian">

       </div>

       <p class="yaoping"><a href="#"><span class="sy" id="send">确定发送</span></a></p>
 	
  </div>


</div>



<div class="kjt" style="overflow-Y: auto;overflow-X:hidden;">
    <div class="roleid"><b>物品</b></div>
    <div style="border:0px solid #ff0000; width:100%;">

        <br/> <br/>

        <ul >
            <li id="yaopin" >药品</li>
            <li id="kuangshi">矿石</li>
            <li id="zhuangbei">装备</li>
            <li id="zhawu">杂物</li>

        </ul>
        <br/>
        <hr/>
        <div id="xswp" >


        </div>



    </div>
    <div class="chexiao" id="cx">撤销</div>
</div>
<div class="kj3">
    <div class="roleid"><b>添加物品</b></div>
    <div style="border:0px solid #ff0000; width:100%;">

        <br/> <br/>
        <hr/>

        <p class="yaoping">欢迎客官，本店提供邮寄服务！</p>
        <p class="yaoping">物品:<span id="jyname"></span></p>
        <p class="yaoping">总量:<span id="jynum"></span></p>
        <p class="yaoping">交易价格：
            <input type="text" id="jyprice" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " style="width:100px;height:25px;margin-top:0px;"/>
        </p>
        <p class="yaoping">交易数量:
            <input type="text" id="jyzl" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " style="width:100px;height:25px;margin-top:0px;"/>
        </p>

        <p >交易总价为空为赠送！</p>
        <p class="sy" id="yjsure">确定</p>


    </div>


</div>

</div>
<%--通用消息框--%>
<div class="inputks">
    <p id="tst" style="color:#ff2821 "></p>
    <div class="quxiaos">确定</div>
</div>
<jsp:include  page="../common/kj.jsp"/>
  </body>
<script type="text/javascript">

var numid=null;
function getadd(num){

    numid=num;


}

function zwtj(){

    $.ajax({
        type: "POST",
        url: "<%=basePath%>youjian/tjwp",
        data:{id:numid},
        success: function(msg){
            var message=JSON.parse(msg);  //使用这个方法解析json

            var p=message.msg;
            $("#jyname").text(p.jjname);
            $("#jynum").html(p.num);
            $(".kjt").css({"display":"none"});
            $(".kjt").css({"display":"none"});
            $(".kj3").css({"display":"block"});


        }
    });


}

$(function(){



    ///////////////////////////////////
    $("#zhuangbei").click(function(){
        $("#zhuangbei").css({ "background": "#000031" });
        $("#zhawu").css({ "background": "#E1B205" });
        $("#kuangshi").css({ "background": "#E1B205" });
        $("#yaopin").css({ "background": "#E1B205" });
        //////////////////////////
        $.ajax({
            type: "POST",
            url: "<%=basePath%>wupin/zhaungbei",
            data:"",
            success: function(msg){
                $("#xswp").html("");
                var message=JSON.parse(msg);  //使用这个方法解析json

                var p=message.rzb;

                var s="";
                if(message.code==0){
                    for(var i=0;i<p.length;i++){

                        var	a="<p style='text-indent:10px;color:#ff0000;line-height:30px;height:30px;width:100%;background:#634163'>"+
                                p[i].name+"<a href=''><span class='sy'>添加</span></a></p>";

                        s=s+a;

                    }
                }
                $("#xswp").html(s);
            }

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
            url: "<%=basePath%>wupin/yaopin",
            data:"",
            success: function(msg){

                $("#xswp").html("");
                var message=JSON.parse(msg);  //使用这个方法解析json
                var p=message.yao;

                var s="";
                if(message.code==0){

                    for(var i=0;i<p.length;i++){
                        if(p[i].yaonum>0){

                            var	a="<p style='text-indent:10px;color:#ff0000;line-height:30px;height:30px;width:100%;background:#634163'>"+
                                    p[i].yaoname+"("+p[i].yaonum+")<span class='sy' id='yaotj'>添加</span></p>";

                            s=s+a;
                        }
                    }
                }

                $("#xswp").html(s);

            }
        });

    });
    $("#zhawu").click(function(){

        $("#zhawu").css({ "background": "#000031" });
        $("#zhuangbei").css({ "background": "#E1B205" });
        $("#kuangshi").css({ "background": "#E1B205" });
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
                            var	a="<p style='text-indent:10px;color:#fffdb5;line-height:30px;height:30px;width:100%;background:#634163'>"+
                                    p[i].jjname+"("+p[i].num+")"+"<span class='sy' onclick='zwtj()' onmouseover='getadd("+p[i].id+")' >添加</span></p>";

                            s=s+a;
                        }
                    }
                }


                $("#xswp").html(s);
            }

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
                                    p[i].jjname+"("+p[i].num+")<a href='jjunaction?id="+p[i].id+"'><span class='sy'>添加</span></a></p>";

                            s=s+a;
                        }
                    }
                }
                $("#xswp").html(s);
            }

        });

    });
    /////////////////////////////////////

    $("#yjwp").click(function(){
        if($("#roleid").val()==""||$("#roleid").val()==null){
            $(".inputks").css({"display":"block"});
            $(".quxiaos").css({"display":"block"});
            $("#tst").text("邮寄物品前请填写收件人ID");
        }else{
            $(".kj").css({"display":"none"});
            $(".kjt").css({"display":"block"});
        }


    });
    $("#cx").click(function(){

        $(".kjt").css({"display":"none"});
        $(".kj").css({"display":"block"});

    });

    $(".quxiaos").click(function(){
        $(".inputks").css({"display":"none"});
        $(".quxiaos").css({"display":"none"});
        $("#tst").text("");

    });
    $("#send").click(function(){

        var receiveid=$("#roleid").val();
        var yin=$("#roleyin").val();
        var message=$("#msg").val();
        if(yin==""||yin==null||receiveid==""||receiveid==null){
            $(".inputks").css({"display":"block"});
            $(".quxiaos").css({"display":"block"});
            $("#tst").text("输入银两或ID不能为空");
        }else{


        $.ajax({
            type: "POST",
            url: "<%=basePath%>youjian/sendyj",
            data:{receiveid:receiveid,yin:yin,message:message},
            success: function(msg) {
                var p=JSON.parse(msg);  //使用这个方法解析json
                var msg=p.msg;
                $(".inputks").css({"display":"block"});
                $(".quxiaos").css({"display":"block"});
                $("#tst").text(msg);
                $("#roleid").val("");
                $("#roleyin").val("");
                $("#msg").val("");
                $("#fujian").html("");
//                $("#send").unbind("click");

            }

        });
        }
    });

//跳转到添加物品
    var s="";
    $("#yjsure").click(function(){

        if(($("#jyzl").val()==""&&$("#jyprice").val()=="") ){

            $(".inputks").css({"display":"block"});
            $(".quxiaos").css({"display":"block"});
            $("#tst").text("邮寄的数量或者价格不正确");

        }
        var a=parseInt($("#jyzl").val());
        var b=parseInt($("#jynum").html());
       // alert($("#jynum").html());
        if( a>b){
//            alert($("#jyzl").val());
//            alert($("#jynum").html());

            $(".inputks").css({"display":"block"});
            $(".quxiaos").css({"display":"block"});
            $("#tst").text("交易数量不能大于拥有数量或者为空");


        }else {
            if ($("#jyzl").val() != "" && $("#jyzl").val() != null && $("#jyzl").val()>= 0) {
                if($("#jyprice").val()==""||$("#jyprice").val()==null){
                    $("#jyprice").val("0");
                }

                $(".kj3").css({"display": "none"});
                $(".kjt").css({"display": "none"});
                $(".kj").css({"display": "block"});
                var a = $("#jyname").text()
//            if(s.indexOf(a)!=-1){
//                s=
//            }
                s += "<p id='fj" + numid + "'>" + $("#jyname").text() + "(" + $("#jyzl").val() + ")</p><p>(提取需" + $("#jyprice").val() + "银两)</p><hr/>";
                $("#fujian").html(s);
                var num=$("#jyzl").val();
                var receiveid=$("#roleid").val();
                var yin=$("#jyprice").val();
                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>youjian/sxnum",
                    data:{num:num,id:numid,jjname:a,receiveid:receiveid,yin:yin},
                    success: function(msg) {

                    }
                });

            }
        }
    });
});

</script>
</html>
