$(document).ready(function () {

    // 初始化订单信息


    // 初始化按钮点击事件
    var buttonInit = new ButtonInit();
    buttonInit.Init();
});

var ButtonInit = function () {
    var buttonInit = {};

    buttonInit.Init = function () {
        // 详情界面
        $("#btn_to_detail").click(function () {
            window.location.href = "/order/orderdetail.jsp";
        });
    };

    return buttonInit;
}