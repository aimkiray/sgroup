/**
 * Created by nekuata on 2017/7/20.
 */
/*$(document).ready(function () {
    $("#tb_product").bootstrapTable({
        columns: [{
            field: 'productId',
            checkbox: true,
            rowspan: 1,
            align: 'center',
            valign: 'middle'
        }, {
            title: '名称',
            field: 'productName',
            // width: '10%',
            rowspan: 1,
            align: 'center',
            valign: 'middle',
            editable: {
                type: 'text',
                title: '修改名称',
                placement: 'right',
                emptytext: "空文本",
                validate: function(value) {
                    if (!value) return '不能为空';
                }
            },
        }, {
            title: '价格',
            field: 'productPrice',
            // width: '10%',
            rowspan: 1,
            align: 'center',
            valign: 'middle',
            editable: {
                type: 'text',
                title: '修改价格',
                placement: 'right',
                validate: function(value) {
                    if (!value) return '密码不能为空';
                }
            },
        }, {
            title: '类别',
            field: 'productType.typeName',
            rowspan: 1,
            align: 'center',
            valign: 'middle',
            editable: {
                type: 'select_type',
                title: '修改类别',
                placement: 'left',
                mode: "inline",
                inputclass: 'input-large',
                select_type: {
                    multiple: true,
                    tags: true,
                    data: [{
                        id: 'bsb',
                        text: '篮球'
                    }, {
                        id: 'ftb',
                        text: '足球'
                    }, {
                        id: 'wsm',
                        text: '游泳'
                    }],
                },
            }
        }],
        filter: true,
        striped: true, //隔行变色
        url: 'json/data1.json',
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortable: false, //是否启用排序
        // sortOrder: "asc", //排序方式
        sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: false, //是否显示所有的列
        showRefresh: false, //是否显示刷新按钮
        minimumCountColumns: 2, //最少允许的列数
        clickToSelect: false, //是否启用点击选中行
        // height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id", //每一行的唯一标识，一般为主键列
        showToggle: false, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        onLoadSuccess: function() {}
        });
})*/

// 这个改进可以谈谈

$(document).ready(function () {
    // 初始化产品类别列表
    // LoadType();
    // alert($("#typeId").val());
    // 初始化Table
    var orderTable = new TableInit();
    orderTable.Init();

    // 初始化Button的点击事件
    var orderButton = new ButtonInit();
    orderButton.Init();

});

// 用于保存产品数组

/*function LoadType() {
    $.ajax({
        url: "/productservlet.do?operate=queryProductType",
        type: "post",
        dataType: "json",
        success: function (arrayType) {
            var optionStr = "<option value='0'>请选择类别</option>";
            for (var i = 0; i < arrayType.length; i++) {
                var jsonData = arrayType[i];
                optionStr += "<option value='" + jsonData.typeId + "'>" + jsonData.typeName + "</option>"
            }
            $("#typeId").html(optionStr);
        },
        error: function () {
            alert("通信失败");
        }
    });
}*/


var TableInit = function () {
    var orderTableInit = {};
    //初始化Table
    orderTableInit.Init = function () {
        $('#tb_product').empty();
        $('#tb_product').bootstrapTable('destroy').bootstrapTable({
            url: '/productservlet.do?operate=queryProductToBootstrap',  //servlet的url地址,调用ajax获取json数据。
            method: 'get',                      //请求方式
            dataType: 'json',                   //返回json
            // contentType:
            // toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            // sortOrder: "asc",                   //排序方式
            queryParams: orderTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],         //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            // strictSearch: true,
            // showColumns: true,                  //是否显示列选择按钮
            // showRefresh: true,                  //是否显示刷新按钮
            // minimumCountColumns: 2,             //最少允许的列数
            // clickToSelect: true,                //是否启用点击选中行
            height: 900,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "productId",              //每一行的唯一标识，一般为主键列
            // showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表

            columns: [{
                field: 'productId',
                checkbox: false,
                align: 'center',
                valign: 'middle',
            }, {
                title: '名称',
                field: 'productName',
                align: 'center',
                valign: 'middle',
            }, {
                title: '价格',
                field: 'productPrice',
                align: 'center',
                valign: 'middle',
            }, {
                title: '日期',
                field: 'productTime',
                align: 'center',
                valign: 'middle',
            }, {
                title: '类别',
                field: 'productType.typeName',
                align: 'center',
                valign: 'middle'
            }, {
                title: '图片',
                field: 'operate',
                align: 'center',
                valign: 'middle'
            }, {
                title: '操作',
                field: 'productId',
                align: 'center',
                valign: 'middle',
                formatter: orderTableInit.operateFormatter //自定义方法，添加操作按钮
            }],
        });
    };

    //需要给服务器端传的参数
    orderTableInit.queryParams = function (params) {
        var param = {
            //键名与servlet要相同
            pageSize: params.limit,   //页面大小
            pageNumber: params.offset,  //页码
            productName: $("#productNameSearch").val(),
            typeId: $("#typeIdSearch").val()
        };
        return param;
    };

    orderTableInit.operateFormatter = function (value, row, index) {//赋予的参数
        if(productMap[value] === undefined) {
            productMap[value] = 0;
        }
        // console.log(typeof value);
        // console.log("-----");
        return [
            '<form class="form-inline">',
                '<button class="btn btn-danger btn-sm rightSize packageBtn" type="button" value="'+value+'" onclick="delProductNum(this.value)"><i class="fa fa-envelope"></i>-</button>',
                '<input type="text" id="show_product_num'+value+'" class="form-control" style="width: 50px" maxlength="3" value="'+productMap[value]+'">',
                '<button class="btn btn-info btn-sm rightSize detailBtn" type="button" value="'+value+'" onclick="addProductNum(this.value)" ><i class="fa fa-paste"></i>+</button>',
            '</form>'
        ].join(' ')
    };

    return orderTableInit;

};

/**
 * 增加产品数量
 * @param value
 */
function addProductNum(productId) {
    // console.log(typeof $("#show_product_num"+value).val());
    $("#show_product_num"+productId).val(function () {
        // console.log(typeof productId);
        // alert(productId);
        // 最多选择999个
        if(parseInt(this.value) < 999) {
            productMap[parseInt(productId)] = parseInt(this.value)+1;
        } else {
            productMap[parseInt(productId)] = parseInt(this.value);
        }
        // alert(productMap[arguments[0]]);

        return productMap[parseInt(productId)];
    });
    /*$.ajax({
        url: "/productservlet.do?operate=getUpdateProductJsp&productId="+arguments[0],
        type: "post",
        dataType: "text",
        success: function (data) {

        },
        error: function () {
            alert("通信失败");
        }
    });*/
}

function delProductNum(productId) {
    // console.log(typeof $("#show_product_num"+value).val());
    $("#show_product_num"+productId).val(function () {
        // 最少选择0个
        if(parseInt(this.value) > 0) {
            productMap[parseInt(productId)] = parseInt(this.value)-1;
        } else {
            productMap[parseInt(productId)] = parseInt(this.value);
        }
        // alert(productMap[arguments[0]]);

        return productMap[parseInt(productId)];
    });
    /*$.ajax({
        url: "/productservlet.do?operate=getUpdateProductJsp&productId="+arguments[0],
        type: "post",
        dataType: "text",
        success: function (data) {

        },
        error: function () {
            alert("通信失败");
        }
    });*/
}

var ButtonInit = function () {
    var orderInit = {};

    orderInit.Init = function () {
        // 初始化页面上面的按钮事件

        // 提交订单
        $("#btn_generate_order").click(function () {
            // console.log(productMap);
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
            // $("#tb_order_detail").bootstrapTable('refresh');
            // $(".bootbox-close-button").click();
        });

    };
    return orderInit;
};