$(function(){
$().ready(function(){
	
	zhawu();
	
});
$("#zhawu").click(function(){
	
	zhawu();
	
});
$("#zhuangbei").click(function(){
	
	zhuangbei();
	
});
$("#kuangshi").click(function(){
	
	kuangshi();
	
});
$("#yaopin").click(function(){
	
	yaopin();
	
});
   
    function zhawu(){

        $("#zhawu").css({ "background": "#000031" });
        $("#zhuangbei").css({ "background": "#E1B205" });
        $("#kuangshi").css({ "background": "#E1B205" });
        $("#yaopin").css({ "background": "#E1B205" });
       $.ajax({
		type:"GET",
              url:""+httpurl+"wupin/appzhawu",
              dataType : 'jsonp',
              jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
              success: function(msg){
                $("#xswp").html("");
              
                var p=msg.jjun;

                var s="";
                if(msg.code!=1){
				 for(var i=0;i<p.length;i++){
                        if(p[i].num>0){
							var b=null;
							if(p[i].type=="书"){
								b="jybook.html";
							}
							if(p[i].type=="令"){
								b="zmsuccess.html";
							}
							if(p[i].type=="令"||p[i].type=="书"||p[i].type.indexOf("锤")>=0){
								if(p[i].num>0){
                            var	a="<a href='wupindetail.html?name=encodeURI("+p[i].jjname+")'>"+"<p style='margin-left:5%;text-indent:10px;color:#fffdb5;line-height:30px;height:30px;width:90%;background:#634163'>"+
                                p[i].jjname+"("+p[i].num+")</a><a href='../fujiang/"+b+"?id="+p[i].id+"?name="+p[i].jjname+"'><span class='sy'>使用</span></a></p>";

                            s=s+a;
								}
							}
                        }
                    }
	  	
					
					
					
					
				}
                   
	   
                $("#xswp").html(s);
            }

        });

    };
	
	 function zhuangbei(){
		   $("#xswp").html("");

        $("#zhawu").css({ "background": "#E1B205" });
        $("#zhuangbei").css({ "background": "#000031" });
        $("#kuangshi").css({ "background": "#E1B205" });
        $("#yaopin").css({ "background": "#E1B205" });
       $.ajax({
		type:"GET",
              url:""+httpurl+"wupin/appzhawu",
              dataType : 'jsonp',
              jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
              success: function(msg){
                $("#xswp").html("");
              
                var p=msg.jjun;

                var s="";
                if(msg.code!=1){
				 for(var i=0;i<p.length;i++){
                        if(p[i].num>0){
							var b=null;
							
							if(p[i].type=="武器"&&p[i].status!='1'&&p[i].num==1){
								
								
								b="zmsuccess.html";
							
                            var	a="<a href='wupindetail.html?name=encodeURI("+p[i].jjname+")'>"+"<p style='margin-left:5%;text-indent:10px;color:#fffdb5;line-height:30px;height:30px;width:90%;background:#634163'>"+
                                p[i].jjname+"("+p[i].num+")</a><a><span onclick='zb("+p[i].id+")' class='sy'>使用</span></a></p>";
								 s=s+a;
								
								 
								}
								
							if(p[i].jjname.indexOf("碎片")>=0&&p[i].num==1){
								
								b="zmsuccess.html";
							
                            var	a="<a href='wupindetail.html?name=encodeURI("+p[i].jjname+")'>"+"<p style='margin-left:5%;text-indent:10px;color:#fffdb5;line-height:30px;height:30px;width:90%;background:#634163'>"+
                                p[i].jjname+"("+p[i].num+")</a><a><span class='sy'>兑换</span></a></p>";
								 s=s+a;
								
								 
								}
								
								
                           
                        }
                    }
					//alert(s);
					if(s.indexOf("undefined")>0){
						$("#xswp").html("");
						
					}else{
					 $("#xswp").html(s);	
					}
					
					
					
					
				}else{
					$("#xswp").html("");
				}
                   
	   
               
            }

        });

    };
	function kuangshi(){
		   $("#xswp").html("");

        $("#zhawu").css({ "background": "#E1B205" });
        $("#zhuangbei").css({ "background": "#E1B205" });
        $("#kuangshi").css({ "background": "#000031" });
        $("#yaopin").css({ "background": "#E1B205" });
       $.ajax({
		type:"GET",
              url:""+httpurl+"wupin/appzhawu",
              dataType : 'jsonp',
              jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
              success: function(msg){
                $("#xswp").html("");
              
                var p=msg.jjun;

                var s="";
                if(msg.code!=1){
					
				 for(var i=0;i<p.length;i++){
                        if(p[i].num>0){
							var b=null;
							
							if(p[i].type=="石"){
								b="zmsuccess.html";
							
                            var	a="<a href='wupindetail.html?name=encodeURI("+p[i].jjname+")'>"+"<p style='margin-left:5%;text-indent:10px;color:#fffdb5;line-height:30px;height:30px;width:90%;background:#634163'>"+
                                p[i].jjname+"("+p[i].num+")</a><a href='#'><span class='sy'>使用</span></a></p>";
								 s=s+a;
								}
								if(p[i].type.indexOf("宝")>=0){
								b="zmsuccess.html";
							
                            var	a="<a href='wupindetail.html?name=encodeURI("+p[i].jjname+")'>"+"<p style='margin-left:5%;text-indent:10px;color:#fffdb5;line-height:30px;height:30px;width:90%;background:#634163'>"+
                                p[i].jjname+"("+p[i].num+")</a><a href='#'><span class='sy'>使用</span></a></p>";
								 s=s+a;
								}
                           
                        }
                    }
					//alert(s);
					if(s.indexOf("undefined")>0){
						$("#xswp").html("");
						
					}else{
					 $("#xswp").html(s);	
					}
					
					
					
					
				}else{
					$("#xswp").html("");
				}
                   
	   
               
            }

        });

    };
		
 
	function yaopin(){
		   $("#xswp").html("");

        $("#zhawu").css({ "background": "#E1B205" });
        $("#zhuangbei").css({ "background": "#E1B205" });
        $("#kuangshi").css({ "background": "#E1B205" });
        $("#yaopin").css({ "background": "#000031" });
       $.ajax({
		type:"GET",
              url:""+httpurl+"wupin/appzhawu",
              dataType : 'jsonp',
              jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
              success: function(msg){
                $("#xswp").html("");
              
                var p=msg.jjun;

                var s="";
                if(msg.code!=1){
					
				 for(var i=0;i<p.length;i++){
                        if(p[i].num>0){
							var b=null;
							
							if(p[i].type=="药"){
								b="zmsuccess.html";
							
                            var	a="<a href='wupindetail.html?name=encodeURI("+p[i].jjname+")'>"+"<p style='margin-left:5%;text-indent:10px;color:#fffdb5;line-height:30px;height:30px;width:90%;background:#634163'>"+
                                p[i].jjname+"("+p[i].num+")</a><a href='#'><span class='sy'>使用</span></a></p>";
								 s=s+a;
								}
                           
                        }
                    }
					//alert(s);
					if(s.indexOf("undefined")>0){
						$("#xswp").html("");
						
					}else{
					 $("#xswp").html(s);	
					}
					
					
					
					
				}else{
					$("#xswp").html("");
				}
                   
	   
               
            }

        });

    };

   
 });
  function zhuangbei(){
		   $("#xswp").html("");

        $("#zhawu").css({ "background": "#E1B205" });
        $("#zhuangbei").css({ "background": "#000031" });
        $("#kuangshi").css({ "background": "#E1B205" });
        $("#yaopin").css({ "background": "#E1B205" });
       $.ajax({
		type:"GET",
              url:""+httpurl+"wupin/appzhawu",
              dataType : 'jsonp',
              jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
              success: function(msg){
                $("#xswp").html("");
              
                var p=msg.jjun;

                var s="";
                if(msg.code!=1){
				 for(var i=0;i<p.length;i++){
                        if(p[i].num>0){
							var b=null;
							
							if(p[i].type=="武器"&&p[i].status!='1'){
								b="zmsuccess.html";
							
                            var	a="<a href='wupindetail.html?name=encodeURI("+p[i].jjname+")'>"+"<p style='margin-left:5%;text-indent:10px;color:#fffdb5;line-height:30px;height:30px;width:90%;background:#634163'>"+
                                p[i].jjname+"("+p[i].num+")</a><a><span onclick='zb("+p[i].id+")' class='sy'>使用</span></a></p>";
								 s=s+a;
								
								 
								}
                           
                        }
                    }
					//alert(s);
					if(s.indexOf("undefined")>0){
						$("#xswp").html("");
						
					}else{
					 $("#xswp").html(s);	
					}
					
					
					
					
				}else{
					$("#xswp").html("");
				}
                   
	   
               
            }

        });

    };
 function zb(num){
	  $.ajax({
		type:"GET",
              url:""+httpurl+"zbxq/rwzb",
              dataType : 'jsonp',
			  data:{id:num},
                 jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
              success: function(msg){
               
                           
                        }
                    });
		
		
		zhuangbei();
		
	};
	
	

