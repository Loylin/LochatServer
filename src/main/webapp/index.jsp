<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="bookmark" href="favicon.ico"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台管理系统登陆</title>
    <link rel="stylesheet" type="text/css" href="common/libs/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <script type="text/javascript" src="common/libs/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="common/libs/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="common/libs/layer/layer.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
</head>
<body>
<div class="container-fluid login">
    <!-- 头部导航栏 -->
    <nav class="navbar navbar-default mynav" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#"><p class="text-center">后台管理系统</p> </a>
            </div>
        </div>
    </nav>

    <!-- 登陆窗体 -->
    <div class="col-md-4 col-md-offset-4 login-border">
        <form class="form-horizontal login-form">
            <div class="login-title">
                <label>系统管理员登陆</label>
            </div>
            <div class="form-group">
                <label for="account" class="col-sm-2 control-label">账号</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="account" placeholder="Account">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-9">
                    <input type="password" class="form-control" id="password" placeholder="Password">
                </div>
            </div>
            <div class="form-group">
                <label for="VCode" class="col-sm-2 control-label">验证码</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="VCode" placeholder="请输入验证码">
                </div>
                <div class="col-sm-4 col-sm-offset-1">
                    <label class="form-control" id="VText"><p>&nbsp;&nbsp;c&nbsp;&nbsp;&nbsp;&nbsp;
                        2&nbsp;&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;&nbsp;9</p></label>
                </div>
            </div>
            <button class="btn btn-primary login-btn">登陆</button>
        </form>
    </div>

    <!-- 底部 -->
    <div class="navbar navbar-default navbar-fixed-bottom login-footer" role="navigation">
        <a class="navbar-brand footer-brand"> <p class="text-center">CopyRight @ Loylin/Lochat 2017 - 2018</p> </a>
    </div>
</div>
</body>
</html>