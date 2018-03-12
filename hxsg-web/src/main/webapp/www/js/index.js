       $(function(){
	/*
	页面加载时调用java接口获取人物数据
	*/	var viplevel=null;
		var juzhudi=null;
		var status=null;
		$().ready(function(){
          $.ajax({
              type:"GET",
              url:""+httpurl+"zhuce/appindex",
              dataType : 'jsonp',
              jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
              success: function(msg){
				  
				 
				  var role=msg.msg;
				 
				  
				 
				juzhudi=role.juzhudi;
				status=role.status;
                 $(".juesename").text(role.juesename);
                 $("#yin").text(role.yin);
				 $("#jin").text(role.jin);
				  $("#level").text(role.dengji);
				 $("#zhiye").text(role.zhiye);
				 $("#jingyan").text(role.jingyan);


              }


          });
		 

      });   
		   
		   
		   
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
	
	
	
	$("#mright").html("<a href='../hospital/yiguan.html'><span id='yiguan' class='lis'></a><a href='#'><span id='yiguan' class='lis'></a><a href='../yiguan/yiguan.html'><span id='yiguan' class='lis'></a><a href='../shichang/sc.html'><span id='yiguan' class='lis'></a><a href='../gc/gc.html'><span id='yiguan' class='lis'></a><a href='#'><span id='yiguan' class='lis'></a><a href='#'><span id='zhanchang' class='lis'>ssss</a><a href='#'><span id='yiguan' class='lis'></a>");
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
	$("#mright").html("<a href='#'><span id='yiguan' class='lisgn'></a>"+
	"<a href='../wupin/wupin.html'><span id='yiguan' class='lisgn'></a>"+
	"<a href='#'><span id='yiguan' class='lisgn'></a>"+
	"<a href='#'><span id='yiguan' class='lisgn'></a>"+
	"<a href='#'><span id='yiguan' class='lisgn'></a>"+
	"<a href='#'><span id='yiguan' class='lisgn'></a>"+
	"<a href='../email/email.html'><span id='yiguan' class='lisgn'></a>"+
	"<a href='#'><span id='yiguan' class='lisgn'></a>"+
	"<a href='#'><span id='yiguan' class='lisgn'></a>"+
	"<a href='#'><span id='yiguan' class='lisgn'></a>"+
	"<a href='#'><span id='yiguan' class='lisgn'></a>"+
	"<a href='../baoku/baoku.html'><span id='yiguan' class='lisgn'></a>"+
	"<a href='#'><span id='yiguan' class='lisgn'></a>"+
	"<a href='#'><span id='yiguan' class='lisgn'></a>"+
	"<a href='#'><span id='yiguan' class='lisgn'></a>");
	
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