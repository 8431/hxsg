var httpurl="http://127.0.0.1:6161/hxsg-web/";
function onPageLoad(){
    dwr.engine.setActiveReverseAjax(true);
}
onPageLoad();
Date.prototype.format=function(fmt) {           
    var o = {           
    "M+" : this.getMonth()+1, //月份           
    "d+" : this.getDate(), //日           
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
    "H+" : this.getHours(), //小时           
    "m+" : this.getMinutes(), //分           
    "s+" : this.getSeconds(), //秒           
    "q+" : Math.floor((this.getMonth()+3)/3), //季度           
    "S" : this.getMilliseconds() //毫秒           
    };           
    var week = {           
    "0" : "/u65e5",           
    "1" : "/u4e00",           
    "2" : "/u4e8c",           
    "3" : "/u4e09",           
    "4" : "/u56db",           
    "5" : "/u4e94",           
    "6" : "/u516d"          
    };           
    if(/(y+)/.test(fmt)){           
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
    }           
    if(/(E+)/.test(fmt)){           
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);           
    }           
    for(var k in o){           
        if(new RegExp("("+ k +")").test(fmt)){           
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
        }           
    }           
    return fmt;           
}        
/*消息通知栏*/
function checkmsg(){

          $.ajax({
		  
                    type:'GET',
					url: ""+httpurl+"friends/appyzhy",
					dataType : 'jsonp',				
					jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
					success: function(msg){          

                  var jg=msg;  //使用这个方法解析json


                  if(jg.code==0){
                      $(".news").css({"display":"block"});

                  }else{
                      $(".news").css({"display":"none"});

                  }



              }

          });

          window.setTimeout(checkmsg,3000);
      }
      window.setTimeout(checkmsg,1);
      $(function(){
          $(".news").click(function(){
              $.ajax({
                 type:'GET',
					url: ""+httpurl+"friends/apptjhy",
					dataType : 'jsonp',				
					jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
					success: function(msg){ 

                      var jg=msg;  //使用这个方法解析json
                      var p=jg.tjnews;

                      if(jg.code==0){
                          if(p.type=="加好友"){

                             $(".fy").text("");
                              $(".fy").text(p.message);
                              $("#fid").val(p.friendid);

                              $("#rfmsgid").val(p.id);
                              $(".fy").css({"display":"block"});
                              $(".newsure").css({"display":"block"});

                              $(".newjujue").css({"display":"block"});

                          }
                          if(p.type=="邮件"){
                              $(".fy").text("");
                              $(".fy").text(p.message);
                              $("#rfmsgid").val(p.id);
                              $(".sure").css({"display":"block"});
                              $(".fy").css({"display":"block"});
                          }
                          if(p.type=="通知"){
						
                              $(".fy").text("");
                              $(".fy").text(p.message);
                              $("#rfmsgid").val(p.id);
                              $(".sure").css({"display":"block"});
                              $(".fy").css({"display":"block"});
                          }
                          if(p.type=="私聊"){
                              var ids=p.id;
                              $.ajax({
							    type:'GET',
								url: ""+httpurl+"friends/readmsg",
								dataType : 'jsonp',	
								data:{id:ids},									
								jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
								success: function(msg){ 

                                 
                                 
                                


                                      window.top.location.href="../friends/talkmsg.html?roleid=encodeURI("+p.roleid+")?friendid=encodeURI("+p.friendid+")?friendname=encodeURI("+p.friendname+")?rolename=encodeURI("+p.rolename+")";
                                  }



                              });
                          }









                      }

                  }
              });
          });
          $(".newsure").click(function(){
          
              var id=$("#rfmsgid").val();

              $.ajax({
			  				   type:'GET',
								url: ""+httpurl+"friends/appsuretjhy",
								dataType : 'jsonp',	
								data:{friendid:friendid,id:id},									
								jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
								success: function(msg){ 
                

                       $(".newsure").css({"display":"none"});
                       $(".newjujue").css({"display":"none"});
                          $(".fy").css({"display":"none"});
                      $(".news").css({"display":"none"});




                  }
              });
          });

          $(".newjujue").click(function(){

              var fid=$("#fid").val();
              var id=$("#rfmsgid").val();
              $.ajax({
			  
			  				   type:'GET',
								url: ""+httpurl+"friends/appnewjujue",
								dataType : 'jsonp',	
								data:{friendid:friendid,id:id},									
								jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
								success: function(msg){ 
                 
                      $(".newsure").css({"display":"none"});
                      $(".newjujue").css({"display":"none"});
                      $(".fy").css({"display":"none"});
                      $(".news").css({"display":"none"});
                  }
              });
          });
          $(".sure").click(function(){
			 
		
              var ids=$("#rfmsgid").val();
			

              $.ajax({
			   type:'GET',
								url: ""+httpurl+"friends/appsurebottun",
								dataType : 'jsonp',	
								 data:{id:ids},									
								jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
								success: function(msg){ 
               
						
                      $(".sure").css({"display":"none"});
                      $(".fy").css({"display":"none"});
                      $(".news").css({"display":"none"});
                      if(msg.indexOf("邮件")>=0){
                          window.top.location.href="youjian/werduemail.html";


                      }

                  }
              });
          });


      });