<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/tag.jsp"%>
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
            color:#ff0000;
            line-height:30px;
            height:30px;
            width:299px;
            background:#634163;
            text-indent:0px;

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
            margin-left:20px;
            border:0px solid #ff0000;
            background:#B520BD;
            color:#ffffff;
        }
        .photo{

            position:fixed;

            top:70px;
            left:230px;
             background:url("../image/txkuang1.jpg");
            background-size:100% 100%;
            border:0px solid #ff0000;
        }
        .roleid{
            position:fixed;
            top:34px;
            left:41px;
            float:left;
            background:#000031;
            color:#FF9998;
            text-align:center;

            width:320px;
            height:30px;

        }
        .rolename{
            color:#ffffff;
            text-indent:10px;


        }
    </style>
</head>

<body>





<div class="kj"style="overflow-Y: auto;overflow-X:hidden;width:320px;">

<div class="roleid"><b>副将:${rfj.fujiangname }</b></div>
<div style="border:0px solid #ff0000; width:270px;">

<br/> <br/>
<div class="photo">

    <div style="margin-left:12px;float:left;background:url(../image/${rfj.touxiang });background-size:100% 100%;width:60px;height:80px;"/></div>
      </div>
<p class="rolename">副将ID:${rfj.id }</p>
<p class="rolename">战斗设置:${rfj.status }</p>
<p class="rolename">头衔:${rfj.touxian}</p>

<p class="rolename">职业:${rfj.leve}级武士</p>
<p class="rolename">升级:需${rfj.sjjinyan}经验</p>
<p class="rolename">默契度:${rfj.moqiduid}</p>
<p class="rolename">忠诚度:0/100<span class="sy" style="color:#ffeffc;background:#ff311d ">奖赏</span></p>
<p class="rolename">属性点:${rfj.keyongds }
<c:if test="${rfj.keyongds>0 }">
    <a href="<%=basePath%>/fujiang/ckds?id=${rfj.id}"><span class="sy" style="color:#ffeffc;background:#ff1097 ">分配</span></a>
</c:if>
    <c:if test="${rfj.keyongds<=0 }">
    <a href="<%=basePath%>/fujiang/ckds?id=${rfj.id}"><span class="sy" style="color:#ffeffc;background:#ff311d ">查看</span></a>
    </c:if>



</p>
<p class="rolename" style="color:#ff0000;">气血：${rfj.totalxue1 }/${rfj.totalxue2 }</p>
<p class="rolename"  style="color:#4447B2">精力：${rfj.totaljing1 }/${rfj.totaljing2 }</p>
<p class="rolename">攻击:${rfj.totalgong}</p>
<p class="rolename">速度:${rfj.totalsudu }</p>
<p class="rolename">防御:${rfj.chufang }</p>
<p class="rolename">技能:(1级)心系百姓</p>
<p class="rolename">熟练度:6000  提高熟练度</p>
<p class="rolename">宝物:   装备    装备    装备   </p>
<p class="rolename"> <span class="sy">战斗能力</span>   <a href="<%=basePath%>fujiang/czpy?id=${rfj.id }"><span class="sy">初值培养 </span></a></p>
<p class="rolename"> <span class="sy">解雇副将</span>  <span class="sy">副将生平 </span>   </p>


</div>



</div>
<jsp:include  page="../common/kj.jsp"/>
</body>

</html>
