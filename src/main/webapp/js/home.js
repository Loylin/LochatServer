/**
 * Created by Loylin on 2017/12/4.
 */
$(function () {
    init();
    change();
});

function init() {
    $(".home-main .content").hide();
    $(".home-main .control").show();
    $("#userInfo").on('click', function () {

    });
}

function change() {
    $(".home-left li").on('click', function () {
        if($(this).hasClass("active")){

        } else {
            $(".home-left li").removeClass("active");
            $(this).addClass("active");
            $(".home-main .content").hide();
            $("." + this.id).show();
        }
    });
}