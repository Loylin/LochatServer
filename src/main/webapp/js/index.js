/**
 * Created by Loylin on 2017/12/4.
 */
$(function () {
    getVcode();//获取验证码
    check();//检查输入
});

function getVcode() {

}

function check() {
    $(document).on('click', ".login-btn", function () {
        var account = $("#account").val();
        var password = $("#password").val();
        var vcode = $("#VCode").val();
        if (account.length === 0){
            layer.tips('账号不能为空', '#account', {tips:[2, '#F00']});
        } else if (password.length === 0){
            layer.tips('密码不能为空', '#password', {tips:[2, '#F00']});
        } else if (vcode.length === 0){
            layer.tips('验证码不能为空', '#VCode', {tips:[2, '#F00']});
        } else {
            // layer.alert("hello", {icon: 6, offset: 't'});
            login();
        }
        return false;
    });
}

function login() {
    window.location.href = window.location.href.substring(0, window.location.href.lastIndexOf('/')) + "/page/home.html";
}