<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/tag.jsp" %>
<jsp:include  page="../common/tag.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>


    <title>My JSP '????.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="<%=basePath%>jQuery/jquery-1.4.2.js"></script>
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style type="text/css">
        .kj{
            background:#000031;
            width:299px;
            height:505px;
            position:fixed;
            left:32px;
            top:35px;
            color:#ffffff;

        }

        .yaoping{
            color: #f6ff9e;
            line-height:30px;
            height:30px;
            width:299px;
            background:#634163;
            text-indent:10px;

        }
        .roleid{
            position:fixed;
            top:35px;
            left:33px;
            border:0px solid #ff0000;
            background:#000031;
            color:#FF9998;
            text-align:center;

            width:300px;
            height:30px;
            line-height:30px;

        }
        a{
            text-decoration:none;
            color:#FFFF00;
        }

        li{
            float:left;
            width:50px;
            background:#E1B205;
            margin-left:5px;
            text-align:center;
            color:#B520BD;
            font-size:20px;
        }

        .sy{

            font-size:20px;
            margin-left:60px;
            border:0px solid #ff0000;
            background:#B520BD;
            color:#ffffff;
        }
    </style>
</head>

<body>





<div class="kj" style="overflow-Y: auto;overflow-X:hidden;width:320px;">
    <div class="roleid"><b>已读邮件</b></div>
    <div style="border:0px solid #ff0000; width:100%;">

        <br/> <br/>
        <hr/>

        <p class="yaoping">欢迎客官，本店提供邮寄服务！</p>
        <c:forEach items="${liyj}" var="p">
            <input type="hidden" value="${p.data}" id="pd"/>
            <p class="yaoping"><span id="dtime">${p.data}</span><a href="#">${p.rolename}</a></p>
            <p class="yaoping"><span class="sy"><a href="<%=basePath%>youjian/ckyj?id=${p.id}">查看</a></span></p>
        </c:forEach>


    </div>

</div>
<jsp:include  page="../common/kj.jsp"/>
</body>
<script type="text/javascript">
$(function(){
//    var t=$("#pd").val();
//    var d = new Date( t);
//    var sd = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate()+" "+ d.getHours()+":"+d.getMinutes();
//    $("#dtime").text(sd);



});

</script>
</html>
