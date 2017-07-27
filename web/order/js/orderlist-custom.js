$(document).ready(function () {

    // 初始化按钮点击事件
    var buttonInit = new ButtonInit();
    buttonInit.Init();

    LoadCustomer();

    LoadEmployee();
});

var ButtonInit = function () {
    var buttonInit = {};

    buttonInit.Init = function () {

    };

    return buttonInit;
}

// 用于生成员工列表

function LoadEmployee() {
    $.ajax({
        url: "/orderservlet.do?operate=getEmployeeList",
        type: "post",
        dataType: "json",
        success: function (arrayType) {
            var optionStr = "<option value='0'>请选择店员</option>";
            for (var i = 0; i < arrayType.length; i++) {
                var jsonData = arrayType[i];
                optionStr += "<option value='" + jsonData.empId + "'>" + jsonData.empName + "</option>"
            }
            $("#empName").html(optionStr);
        },
        error: function () {
            alert("通信失败");
        }
    });
}


function LoadCustomer() {
    $.ajax({
        url: "/orderservlet.do?operate=getCustomerList",
        type: "post",
        dataType: "json",
        success: function (arrayType) {
            var optionStr = "<option value='0'>请选择客户</option>";
            for (var i = 0; i < arrayType.length; i++) {
                var jsonData = arrayType[i];
                optionStr += "<option value='" + jsonData.customerId + "'>" + jsonData.customerName + "</option>"
            }
            $("#customerName").html(optionStr);
        },
        error: function () {
            alert("通信失败");
        }
    });
}

// 详情界面
function btnToDetail(orderId) {
    // alert(orderId);
    window.location.href = "/orderservlet.do?operate=toOrderDetail&orderId="+orderId;
}

// 修改界面
function btnToEdit(orderId) {
    window.location.href = "/orderservlet.do?operate=toOrderEdit&orderId="+orderId;
}

// 删除订单
function btnToDel(orderId) {
    window.location.href = "/orderservlet.do?operate=delOrderByOrderId&orderId="+orderId;
}