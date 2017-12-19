<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="shortcut icon" href="../../favicon.ico"/>
    <link rel="bookmark" href="../../favicon.ico"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="../../common/libs/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/home.css">
    <script type="text/javascript" src="../../common/libs/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../../common/libs/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../common/libs/layer/layer.js"></script>
    <script type="text/javascript" src="../../js/home.js"></script>
</head>
<body>
<div class="container-fluid home-page">
    <nav class="navbar navbar-inverse navbar-fixed-top home-navbar">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#showComp" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><p class="text-center">后台管理系统</p></a>
        </div>
        <div class="collapse navbar-collapse navbar-right home-navbar-right" id="showComp">
            <a class="navbar-brand dropdown-toggle" id="userInfo" data-toggle="dropdown" aria-haspopup="true"
               aria-expanded="false"><p>用户：Loylin</p></a>
            <ul class="dropdown-menu">
                <li><a href="#">用户信息</a></li>
                <li><a href="#">修改密码</a></li>
                <li><a href="#">控制状态</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">退出系统</a></li>
            </ul>
        </div>
    </nav>

    <!-- Left -->
    <div class="home-left">
        <p>服务列表</p>
        <ul class="nav nav-pills nav-stacked">
            <li class="active" id="control"><a>控制台</a></li>
            <li class="" id="userlist"><a>用户列表</a></li>
            <li class="" id="session"><a>Session</a></li>
            <li class="" id="pushNotification"><a>消息推送</a></li>
        </ul>
    </div>

    <!-- content -->
    <div class="home-main">
        <div class="content control">
            <jsp:include page="control.jsp" flush="true"/>
        </div>
        <div class="content userlist">
            <jsp:include page="userlist.jsp" flush="true"/>
        </div>
        <div class="content session">
            <jsp:include page="session.jsp" flush="true"/>
        </div>
        <div class="content pushNotification">
            <jsp:include page="pushNotification.jsp" flush="true"/>
        </div>
    </div>
</div>
</body>
</html>