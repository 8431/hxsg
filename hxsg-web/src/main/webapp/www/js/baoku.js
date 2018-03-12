$(function(){
    $().ready(function () {

        yaopin();

    });

    $("#zhuangbei").click(function () {

        zhuangbei();

    });
    $("#kuangshi").click(function () {

        kuangshi();

    });
    $("#yaopin").click(function () {

        yaopin();

    });


var bkbwid;
    function ajaxs(data) {	
		
        $("#xswp").html("");
        $.ajax({
            type: "GET",
            url: "" + httpurl + "bkshop/xsbw",
            dataType: 'jsonp',
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            data: data,
            success: function (msg) {
				
				
                $("#xswp").html("");
                var p = msg.msg;
                var s = "";
                for (var i = 0; i < p.length; i++) {
					
			
                    var b = null;
					
				
                    b = "zmsuccess.html";
                    var a = "<a href='../wupin/wupindetail.html?name=encodeURI(" + p[i].name + ")'>" + "<p style='margin-left:5%;text-indent:10px;color:#fffdb5;line-height:30px;height:30px;width:90%;background:#634163'>" +
                        p[i].name + "(" + p[i].price + "金砖)</a><a href='#' onclick='gmbkbw("+p[i].id+")'><span class='sy' >购买</span></p></a>";
                    s = s + a;
               
				
				
				}
           
                $("#xswp").html(s);
            }

        });
    }
		
 			
    function kuangshi() {
        $("#zhawu").css({ "background": "#E1B205" });
        $("#zhuangbei").css({ "background": "#E1B205" });
        $("#kuangshi").css({ "background": "#ff3a96" });
        $("#yaopin").css({ "background": "#E1B205" });
        var data = {"type": "0"};
        ajaxs(data);
    };


    function yaopin() {
		
        $("#xswp").html("");

        $("#zhawu").css({ "background": "#E1B205" });
        $("#zhuangbei").css({ "background": "#E1B205" });
        $("#kuangshi").css({ "background": "#E1B205" });
        $("#yaopin").css({ "background": "#ff3a96" });
        var data = {"type": "1"};
        ajaxs(data);
	


    };
	
    function zhuangbei() {
	
		
        $("#xswp").html("");
        $("#zhawu").css({ "background": "#E1B205" });
        $("#zhuangbei").css({ "background": "#000031" });
        $("#kuangshi").css({ "background": "#E1B205" });
        $("#yaopin").css({ "background": "#E1B205" });
        $("#xswp").html("-----------未开放");
    }
		$(".quxiaos").click(function(){
							$(".inputks").css({"display": "none"});
							$(".quxiaos").css({"display":"none"});
				});


});
function gmbkbw(nu){
	
				bkbwid=nu;
			$(".inputk").css({"display": "block"});
			$(".queding").css({"display": "block"});
			$(".quxiao").css({"display": "block"});
			$(".quxiao").click(function(){
				$(".inputk").css({"display": "none"});
				$(".queding").css({"display": "none"});
				$(".quxiao").css({"display": "none"});
			});
			

		};

function tjmgbw(){
	var num=$("#shuru").val();
	
	var dajson={"id":bkbwid,"num":num}
	$.ajax({
            type: "GET",
            url: "" + httpurl + "bkshop/shopbw",
            dataType: 'jsonp',
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            data: dajson,
            success: function (msg) {
					$(".queding").css({"display": "none"});
                           $(".quxiao").css({"display": "none"});
                           $(".inputk").css({"display": "none"});
                           $("#shuru").val("");

                           $(".inputks").css({"display":"block"});
                           $(".quxiaos").css({"display":"block"});
						 
						
				 $("#tst").text(msg.msg);
				
				
			}
	});
					
	 
	
	
	}


		
