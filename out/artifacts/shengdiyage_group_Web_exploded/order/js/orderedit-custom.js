var productMap;
// var jsonOrder;

$(document).ready(function () {

    // 用于计算已经选中的产品，并恢复现场

    LoadJsonOrderMap();

    // alert($("#typeId").val());
    // 初始化表格
    var orderDetailTable = new TableInit()
    orderDetailTable.Init();

    // 初始化Button的点击事件
    var orderDetailButtonInit = new ButtonInit();
    orderDetailButtonInit.Init();

    // 初始化产品数组（字面量）
    productMap = {};

    // 初始化json放入表格的订单json数据
    // jsonOrder = {};
    // 初始化员工列表
    // LoadEmployee();

    // 初始化客户列表
    // LoadCustomer();

});

// 提交表单
/*function delOldOrder(orderId) {


}*/

// 用于计算已经选中的产品
function LoadJsonOrderMap() {
    var orderId = $("#thisOrderId")[0].value;
    // alert(orderId);
    $.ajax({
        url: "/orderservlet.do?operate=getOrderJson&orderId="+orderId,
        type: "post",
        dataType: "json",
        success: function (jsonMap) {
            // alert(jsonMap);
            productMap = jsonMap;
            // alert(productMap);
            // alert(productMap['59']);
            // 填装已选择的数据
            LoadTableData();
        },
        error: function () {
            alert("通信失败");
        }
    });
}

// 初始化已选择的表格
function LoadTableData() {
    $.ajax({
        url: "/orderservlet.do?operate=queryOrderDetail",
        type: "post",
        data: "productMap="+JSON.stringify(productMap),
        dataType: "json",
        // dataType: "text",
        success: function (data) {
            // 将json传给订单列表
            // jsonOrder = data;
            // 刷新一下订单列表
            // alert(data);
            // alert(JSON.stringify(data));
            $("#tb_order_detail").bootstrapTable('load',{data: data});
            // alert(2);
            // 初始化订单对象
            // productMap = {};
            $(".bootbox-close-button").click();
        },
        error: function () {
            alert("通信失败");
            // $(".bootbox-close-button").click();
        }
    });
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


var TableInit = function () {
    var orderDetailTableInit = {};
    //初始化Table
    orderDetailTableInit.Init = function () {
        $('#tb_order_detail').empty();
        $('#tb_order_detail').bootstrapTable('destroy').bootstrapTable({
            // url: '/orderservlet.do?operate=queryOrderDetail',  //servlet的url地址,调用ajax获取json数据。
            // method: 'post',                      //请求方式
            // dataType: 'json',                   //返回json
            // queryParams: orderDetailTableInit.queryParams,//传递参数（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            // cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            // search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端
            // strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            // showRefresh: true,                  //是否显示刷新按钮
            // minimumCountColumns: 2,             //最少允许的列数
            // clickToSelect: true,                //是否启用点击选中行
            // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "productId",              //每一行的唯一标识，一般为主键列
            // showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            // data: JSON.stringify(jsonOrder),

            columns: [{
                title: '编号',
                field: 'productId',
                align: 'center',
                valign: 'middle',
            }, {
                title: '产品名称',
                field: 'productName',
                align: 'center',
                valign: 'middle',
            }, {
                title: '数量',
                field: 'quantity',
                align: 'center',
                valign: 'middle',
            }, {
                title: '折扣',
                field: 'discount',
                align: 'center',
                valign: 'middle',
                formatter: orderDetailTableInit.operateFormatter
            }],
        });
    };

    orderDetailTableInit.getOrderDetail = function () {
        $.ajax({
            url: "/orderservlet.do?operate=queryProductById&productId=",
            type: "post",
            dataType: "text",
            success: function (data) {
                var detailJson = {
                    productId: data
                }
            },
            error: function () {
                alert("通信失败");
            }
        });
    }
    /*orderDetailTableInit.queryParams = function (params) {
        var param = {
            //键名与servlet要相同
            productMap: JSON.stringify(productMap)
        };
        return param;
    };*/

    orderDetailTableInit.operateFormatter = function (value, row, index) {//赋予的参数
        // alert(value);
        // alert(row);
        // alert(index);
        // alert(value);
        return [
            '<select id="setDiscount" name="discount">' ,
            '<option value="10">不打折</option>' ,
            '<option value="10">10折</option>' ,
            '<option value="20">20折</option>' ,
            '<option value="998">998折</option>' ,
            '<option value="9">真·打折</option>' ,
            '</select>'
        ];
    };

    return orderDetailTableInit;
};

/*onchange="setDisconut()" onload="setDisconut()"
function setDisconut() {

    $("#tb_order_detail").bootstrapTable('updateCell',{index : 0, field : "discount", value: $("#setDiscount").val()});
}*/


var ButtonInit = function () {
    var orderDetailInit = {};

    orderDetailInit.Init = function () {
        // 初始化页面上面的按钮事件

        // 用于选择订单时间
        $("#orderDate").datetimepicker({
            format: "yyyy-mm-dd hh:ii:ss",
            autoclose: true,
            todayBtn: true
        });

        // 显示产品列表
        $("#btn_show_product").click(function () {
            $.ajax({
                url: "/orderservlet.do?operate=getProductList",
                type: "post",
                dataType: "text",
                success: function (data) {
                    bootbox.dialog({
                        message: data,
                        title: "选择产品"
                    })
                },
                error: function () {
                    alert("通信失败");
                }
            });
        });
        $("#btn_none").click(function () {
            $.ajax({
                url: "/orderservlet.do?operate=getProductList",
                type: "post",
                dataType: "text",
                success: function (data) {
                    bootbox.dialog({
                        message: data,
                        title: "选择产品"
                    })
                },
                error: function () {
                    alert("通信失败");
                }
            });
        });

        $(document).click(function(e){
            var _con = $(".modal-content");   // 设置目标区域
            if(!_con.is(e.target) && _con.has(e.target).length === 0){ // Mark 1
                $(".bootbox-close-button").click();
            }
        });

        $("#btn_submit_orders").click(function () {

            $("#jsonData").val(JSON.stringify($("#tb_order_detail").bootstrapTable('getData')));

            var orderId = $("#thisOrderId")[0].value;

            $.ajax({
                url: "/orderservlet.do?operate=delOrderByOrderId&flag=ajax&orderId="+orderId,
                type: "post",
                dataType: "text",
                success: function (value) {
                    // alert(value);
                    if(value == "true") {
                        alert("修改成功");
                        $("#orderEditData").submit();
                        return true;
                    } else {
                        alert("修改失败");
                        return false;
                    }
                },
                error: function () {
                    alert("通信失败");
                    return false;
                }
            });

            // console.log($("#tb_order_detail").bootstrapTable('getData'));
            // alert(JSON.stringify($("#tb_order_detail").bootstrapTable('getData')));
        })
    };
    return orderDetailInit;
};