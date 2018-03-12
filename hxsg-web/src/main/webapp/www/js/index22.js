 $(function(){
      
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



    

         $(".quxiao").click(function(){

             $("#fy2").css({"display":"none"});
         });
		 /*点击聊天按钮判断是否会员并显示会员表情*/
         $("#fayan").click(function(){
			  /*验证是否VIP*/
		    $.ajax({
              type:"GET",
              url:""+httpurl+"zhuce/appvip",
              dataType : 'jsonp',
              jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
              success: function(msg){
				  
				 
				
				  var vip=msg.vip;
				  if(vip.level>0){
					 $(".face").css({"display":"block"});
					 
					  
				  }else{
					  $(".face").css({"display":"block"});
					    $(".face").html("");
						 $(".face").css({"color":"#FFE51A"});
						  $(".face").css({"text-indent":"20px"});
					   $(".face").html("咕叽会员聊天可以发表情哦！");
					  
				  }
				  
				 


              }


          });
		

             $("#fy2").css({"display":"block"});

         });
         $(".send").click(function(){
			  var rmsg=$("#msg").html();
			  alert("sss");
			 alert(parent.window.a(rmsg));
			


         });
		 
		/*$().ready(function(){
			 alert(parent.window.a());
			
			
		});*/
     
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
          $("#mright").html("<a href='../battle/wabao.html'><span  class='lis'></a>")

          $("#mright").css({background:"url(../image/bdss.png)"});
          $("#mright").css({"background-size":"100% 100%"});
		   $("#rw").css({background:"url(../image/rwh.png)"});
		    $("#rw").css({"background-size":"100% 100%"});

          $("#ss").click(function(){
              $("#mright").html("");
              $("#mright").html("<a href='../battle/wabao.html'><span  class='lis'></a>")

              $("#mright").css({background:"url(../image/bdss.png)"});
              $("#mright").css({"background-size":"100% 100%"});
              $("#ss").css({background:"url(../image/ss1.png)"});
              $("#gn").css({background:"url(../image/gn2.png)"});
              $("#rw").css({background:"url(../image/rwh.png)"});
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
 });
 /*控制表情函数*/
  function face(num){
	  var mg=$("#msg").html();
	
	 $("#msg").html(mg+"<img class='gif' src='../face/vip1/"+(num-1)+".gif'>");
	
	 
	 
	 
	 
	 
	 
	 
 }
