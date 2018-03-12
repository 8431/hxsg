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
			
            var date=new Date();
            var hh= date.getHours();
             var mm=date.getMinutes();

            
             $("#rtime").text(hh+":"+mm);
             var rmsg=$("#msg").html();


             $("#fy2").css({"display":"none"});
             for (i = 1; i <= 5; i++) {
                 window.setTimeout("update( " + i + ") ", i * 1000);
             }
             $("#msg").css({"background-color":"#3E3E3E"});

             $.ajax({
                type: 'GET',
              url:""+httpurl+"mg/appmsg",
              dataType : 'jsonp',
              jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
               data:{rolemsg:rmsg,type:"3"},
                 success: function(msg){
                     var p=msg.rmsg;
					 // alert("ss");
					
                     var s="";
                     
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
                     

                     $("#chat").html(s);
                        $("#msg").html("");


                 }

             });


         });
		 
		 /*刷新index聊天界面信息*/
         function sxmsg(){
             $.ajax({
                 type: "GET",
                 url: ""+httpurl+"mg/appsxmsg",
                 dataType : 'jsonp',
                 jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                 success: function(msg){
					
                    
                     var p=msg.rmsg;
					

                     var s="";
                     

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
                     

                     $("#chat").html(s);



                 }

             });
             window.setTimeout(sxmsg,1000);
         }
         window.setTimeout(sxmsg,1);
     
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
