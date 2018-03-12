
    var numid=null;
    function mouse(nm){
        numid=nm;
    }
var wbmsgs=null;
    var num1=0;
    var num2=0;
    var num3=0;
    var num4=0;
    var num5=0;
    var num6=0;
    var num7=0;
    var num8=0;
    var num9=0;
function wabao1(num){
        if(num==1&&num1==0){
            num1=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "GET",
                url: ""+httpurl+"zhanchang/appxhcs",
				 dataType : 'jsonp',
				 data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                 jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                 success: function(msg){
					
                    
                   
				
                 
                   var msg=msg.msg;
                  
                  
                    if(msg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
////////////////////////////////////////////////////
    function wabao2(num){
        if(num==1&&num2==0){
            num2=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
           $.ajax({
                type: "GET",
                url: ""+httpurl+"zhanchang/appxhcs",
				 dataType : 'jsonp',
				 data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                 jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                 success: function(msg){ 
                    var msg=msg.msg;
                  
                  
                    if(msg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
////////////////////////////////
    function wabao3(num){
        if(num==1&&num3==0){
            num3=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
           $.ajax({
                type: "GET",
                url: ""+httpurl+"zhanchang/appxhcs",
				 dataType : 'jsonp',
				 data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                 jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                 success: function(msg){ 
                    var msg=msg.msg;
                  
                  
                    if(msg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
    function wabao4(num){
        if(num==1&&num4==0){
            num4=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "GET",
                url: ""+httpurl+"zhanchang/appxhcs",
				 dataType : 'jsonp',
				 data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                 jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                 success: function(msg){ 
                    var msg=msg.msg;
                  
                  
                    if(msg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
    function wabao5(num){
        if(num==1&&num5==0){
            num5=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
             $.ajax({
                type: "GET",
                url: ""+httpurl+"zhanchang/appxhcs",
				 dataType : 'jsonp',
				 data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                 jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                 success: function(msg){ 
                    var msg=msg.msg;
                  
                  
                    if(msg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
    function wabao6(num){
        if(num==1&&num6==0){
            num6=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "GET",
                url: ""+httpurl+"zhanchang/appxhcs",
				 dataType : 'jsonp',
				 data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                 jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                 success: function(msg){ 
                   var msg=msg.msg;
                  
                  
                    if(msg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
    function wabao7(num){
        if(num==1&&num7==0){
            num7=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "GET",
                url: ""+httpurl+"zhanchang/appxhcs",
				 dataType : 'jsonp',
				 data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                 jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                 success: function(msg){ 
                    var msg=msg.msg;
                  
                  
                    if(msg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
    function wabao8(num){
        if(num==1&&num8==0){
            num8=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "GET",
                url: ""+httpurl+"zhanchang/appxhcs",
				 dataType : 'jsonp',
				 data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                 jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                 success: function(msg){ 
                    var msg=msg.msg;
                  
                  
                    if(msg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
    function wabao9(num){
        if(num==1&&num9==0){
            num9=1;
            $("#w"+numid).css({"background":"#000031"});
            var id=$("#wbid").val();
            $.ajax({
                type: "GET",
                url: ""+httpurl+"zhanchang/appxhcs",
				 dataType : 'jsonp',
				 data:"w"+numid+"=0&id="+id+"&type=w"+numid,
                 jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                 success: function(msg){ 
                    var msg=msg.msg;
                  
                  
                    if(msg=="sx"){
                        window.location.reload();
                    }
                    $("#tishi").text(msg);

                }

            });

        }
    }
	

    $(function(){
		/*加载挖宝页面*/
		$().ready(function(){

				 $.ajax({
                 type: "GET",
                 url: ""+httpurl+"zhanchang/appfee",
                 dataType : 'jsonp',
                 jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
                 success: function(msg){
					 var wb=msg.wb;
					 var s="";
					if(wb.w1==0){
						s+=" <p  ><span style='background:#000031'  id='w1' class='sy' >挖宝</span>";
						
					} 
					if(wb.w1==1){
						s+="<p  ><span style='background:#e016ff' onclick='wabao1(1)' onmousemove='mouse(1)' id='w1' class='sy'>挖宝</span>";
						
					} 
					if(wb.w2==0){
						s+="<span style='background:#000031'  id='w2' class='sy' >挖宝</span>";
						
					} 
					if(wb.w2==1){
						s+="<span style='background:#e016ff' onclick='wabao2(1)' onmousemove='mouse(2)' id='w2' class='sy'>挖宝</span>";
						
					} 
					if(wb.w3==0){
						s+="<span style='background:#000031'  id='w3' class='sy' >挖宝</span></p>";
						
					} 
					if(wb.w3==1){
						s+="<span style='background:#e016ff' onclick='wabao3(1)' onmousemove='mouse(3)' id='w3' class='sy'>挖宝</span></p>";
						
					} 
					if(wb.w4==0){
						s+="<p  ><span style='background:#000031'  id='w4' class='sy' >挖宝</span>";
						
					} 
					if(wb.w4==1){
						s+="<p ><span style='background:#e016ff' onclick='wabao4(1)' onmousemove='mouse(4)' id='w4' class='sy'>挖宝</span>";
						
					} 
					if(wb.w5==0){
						s+="<span style='background:#000031'  id='w5' class='sy' >挖宝</span>";
						
					} 
					if(wb.w5==1){
						s+="<span style='background:#e016ff' onclick='wabao5(1)' onmousemove='mouse(5)' id='w5' class='sy'>挖宝</span>";
						
					} 
					if(wb.w6==0){
						s+="<span style='background:#000031'  id='w6' class='sy' >挖宝</span></p>";
						
					} 
					if(wb.w6==1){
						s+="<span style='background:#e016ff' onclick='wabao6(1)' onmousemove='mouse(6)' id='w6' class='sy'>挖宝</span></p>";
						
					} 
					if(wb.w7==0){
						s+="<p ><span style='background:#000031'  id='w7' class='sy' >挖宝</span>";
						
					} 
					if(wb.w7==1){
						s+="<p  ><span style='background:#e016ff' onclick='wabao7(1)' onmousemove='mouse(7)' id='w7' class='sy'>挖宝</span>";
						
					} 
					if(wb.w8==0){
						s+="<span style='background:#000031'  id='w8' class='sy' >挖宝</span>";
						
					} 
					if(wb.w8==1){
						s+="<span style='background:#e016ff' onclick='wabao8(1)' onmousemove='mouse(8)' id='w8' class='sy'>挖宝</span>";
						
					} 
					if(wb.w9==0){
						s+="<span style='background:#000031'  id='w9' class='sy' >挖宝</span></p>";
						
					} 
					if(wb.w9==1){
						s+="<span style='background:#e016ff' onclick='wabao9(1)' onmousemove='mouse(9)' id='w9' class='sy'>挖宝</span></p>";
						
					} 
					$("#xswb").html("");
					$("#xswb").html(s);
					$("#wbid").val(wb.id);
					$("#wbnum").text(wb.num);
					 if(wb.w1==0&&wb.w2==0&&wb.w3==0&&wb.w4==0&&wb.w5==0&&wb.w6==0&&wb.w7==0&&wb.w8==0&&wb.w9==0){
							 $("#jxwb").html("");   
						 	 $("#jxwb").html(" <p ><span  id='sxwb' class='sy'>继续挖宝</span></p>");
					 }
					
					 if( wb.status=='1'){
						     $("#status2").css({"display":"none"});   
						 	
						 $(".frame").css({"display":"block"}); 
					}
					 if( wb.status=='0'||wb==null){
						     $("#status2").css({"display":"block"});   
						 	 $(".frame").css({"display":"none"});  
						
					}
					$("#sxwb").click(function(){
						 if( wb.status=='0'){
						window.top.location.href="../battle/wabao.html";
						
					}else{
						var id=$("#wbid").val();
						
						 $.ajax({
							  type:"GET",
							  url:""+httpurl+"zhanchang/appsxwb",
							dataType : 'jsonp',
							data:{id:id},
							jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
					
							  success:function(msg){
								
								window.location.reload();
							  
						  
							} 
					});
						
						
					}
						
						
						
					});
					
				 }
				 });
					 
				
				
				
				
				
				
			});
			
     
		
       
     


   });