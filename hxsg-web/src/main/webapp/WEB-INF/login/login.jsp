<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>咕叽三国</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.js"></script>
    <script type="text/javascript">
        $(function(){

            $("#login").click(function(){

                $.ajax({

                    type: "POST",
                    url: "role/login",
                    data:"name="+$("#name").val()+"&password="+$("#password").val(),
                    success: function(msg){
                        window.location.href="http://www.baidu.com";
                        alert("ss");
                    }

                });
                alert("ss");
            });
        });

    </script>
    <style type="text/css">
        .index{
            border:1px solid #ff0000;
            width:100%;
            height:100%;
            margin:0px auto
        position:fixed;
            background:#000031;

        }
        .top{
            position:fixed;
            top:0px;
            float:left;

            width:360px;
            line-height:35px;
            height:35px;
            text-align:center;
            color:#ffffff

        }
        .roleid{
            position:fixed;
            top:34px;
            left:41px;
            float:left;
            background:#000031;
            color:#FF9998;
            text-align:center;

            width:350px;
            height:30px;

        }
        .rolename{
            color:#ffffff;
            text-indent:30px;


        }

        .left{
            float:left;


            position:absolute;
            left:0px;
            top:44px;
            height:850px;
            width:32px;




        }
        .photo{

            position:absolute;

            top:60px;
            left:270px;

            border:0px solid #ff0000;
        }

    </style>

</head>

<body>
<form method="post" action="<%=request.getContextPath() %>/zhuce/login">
    <div class="index">
        <div style="border:1px solid #ff0000; height:300px;width:300px;margin:0px auto;margin-top:100px;">

            <p style="color:#ffffff;">账       号：<input id="name" type="text" name="name"/></p>

            <p style="color:#ffffff;">密       码：<input id="password" type="password" name="password"/></p>
            <p style="color:#ff2235">${xinxi }</p>
            <a href="<%=basePath%>zhuce/gamezc"> <input type="button" value="注册" style="width:150px;font-size:20px;background:#ff2821;color:#7fffc5;position:relative;top:50px;left:0px;"/></a>
            <input  type="submit" value="进入游戏" style="width:100px;font-size:20px;background:#7b66ff;color:#7fffc5;position:relative;top:50px;left:100px;"/>
        </div></div>
</form>

</div>

</div>


</body>
</html>
