$(function(){

    $("#zhuangbei").click(function(){

        $("#zhuangbei").css({ "background": "#000031" });
        $("#zhawu").css({ "background": "#E1B205" });
        $("#kuangshi").css({ "background": "#E1B205" });
        $("#yaopin").css({ "background": "#E1B205" });
        //////////////////////////
        $.ajax({
            type: "POST",
            url: "zhaungbei",
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
            url: "yaopin",
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
                                p[i].yaoname+"("+p[i].yaonum+")<a href='jjunaction?id="+p[i].id+"'><span class='sy'>添加</span></a></p>";

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
            url: "zhawu",
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
                                p[i].jjname+"("+p[i].num+")<a href='../jjunl/syjjl?id="+p[i].id+"'><span class='sy'>添加</span></a></p>";

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
            url: "zhawu",
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


});

