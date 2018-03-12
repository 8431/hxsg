//刷新挖宝得住信息

    function sxmsg(){
		
        $.ajax({
            type: "GET",
            url:""+httpurl+"zhanchang/appwdbw",
           dataType : 'jsonp',
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            success: function(msg){

                   
                     var p=msg.rmsg;
					

                     var s="";

                    for(var i=0;i<p.length;i++){

                        var d= new Date(p[i].date);
                        var hh=d.getHours();
                        var mm= d.getMinutes();

                        if(mm<10){
                            mm="0"+mm;
                        }
                        if(hh<10){
                            hh="0"+hh;
                        }

                        var times=hh+":"+mm;


                        var	a="<p style='text-indent:10px;font-weight:bold;color:#ffffff;fontsize:10px;'><span >"+times+"</span>&nbsp;<font style='color:#FFEE1D'>"+p[i].rolename+"</font>:<span>挖到【<font color='#ff9e00'>"+p[i].baoname+"</font>】</span></p>";

                        s=s+a;

                    }
                

                $(".wbmsg").html(s);
                //alert(s);



            }

        });
        window.setTimeout(sxmsg,2000);
    }
    window.setTimeout(sxmsg,50);

	

    $(function(){
		
			
     
		/*购买挖宝工具*/
       $("#gmtg").click(function(){

           $.ajax({
               type: "GET",
               url: ""+httpurl+"zhanchang/appyzwb",
				dataType : 'jsonp',
			
				jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数					
				success:function(msg){
                   var mg=msg.msg;
                   $(".inputks").css({"display":"block"});
                   $("#tst").text(mg);
               }
           });

       });
       $(".quxiao").click(function(){
           $(".inputk").css({"display":"none"});
           $(".queding5").css({"display":"none"});
           $(".quxiao").css({"display":"none"});
           $("#tst").text("");
           window.location.reload();

       });
       $("#q1").click(function(){
           $.ajax({
               type: "GET",
               url: ""+httpurl+"zhanchang/appwabao",
				dataType : 'jsonp',
			
				jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数					
				success:function(msg){
                 
                   var mg=msg.msg;


                   $("#tst").text(mg);



               }
           });
       });


   });