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
$(document).ready(function () {
    // alert(0);
    // 初始化产品类别列表
    LoadType();
    // alert($("#typeId").val());
    // 初始化Table
    var productTable = new TableInit();
    productTable.Init();

    // 初始化Button的点击事件
    var productButtonInit = new ButtonInit();
    productButtonInit.Init();

});

// 用于生成产品数组

function LoadType() {
    $.ajax({
        url: "/productservlet.do?operate=queryProductType",
        type: "post",
        dataType: "json",
        success: function (arrayType) {
            // alert(1);
            var optionStr = "<option value='0'>请选择类别</option>";
            for (var i = 0; i < arrayType.length; i++) {
                var jsonData = arrayType[i];
                optionStr += "<option value='" + jsonData.typeId + "'>" + jsonData.typeName + "</option>"
            }
            // alert(optionStr);
            $("#typeIdSearch").html(optionStr);
        },
        error: function () {
            alert("通信失败");
        }
    });
}


var TableInit = function () {
    var productTableInit = {};
    //初始化Table
    productTableInit.Init = function () {
        $('#tb_product').empty();
        $('#tb_product').bootstrapTable('destroy').bootstrapTable({
            url: '/productservlet.do?operate=queryProductToBootstrap',  //servlet的url地址,调用ajax获取json数据。
            method: 'get',                      //请求方式
            dataType: 'json',                   //返回json
            // contentType:
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            // sortOrder: "asc",                   //排序方式
            queryParams: productTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],      //可供选择的每页的行数（*）
            // search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
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
                editable: {
                    type: 'text',
                    title: '修改名称',
                    placement: 'right',
                    validate: function(value) {
                        if (!value) return '不能为空';
                    }
                }
            }, {
                title: '价格',
                field: 'productPrice',
                align: 'center',
                valign: 'middle',
                editable: {
                    type: 'text',
                    title: '修改价格',
                    placement: 'right',
                    validate: function(value) {
                        if (!value) return '密码不能为空';
                    }
                }
            }, {
                title: '日期',
                field: 'productTime',
                align: 'center',
                valign: 'middle',
                editable: {
                    type: 'text',
                    title: '修改时间',
                    placement: 'right',
                }
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
                formatter: operateFormatter //自定义方法，添加操作按钮
            }],
        });
    };

    //需要给服务器端传的参数
    productTableInit.queryParams = function (params) {
        var param = {
            //键名与servlet要相同
            pageSize: params.limit,   //页面大小
            pageNumber: params.offset,  //页码
            productName: $("#productNameSearch").val(),
            typeId: $("#typeIdSearch").val()
        };
        return param;
    };

    var operateFormatter = function (value, row, index) {//赋予的参数
        return [
            '<button class="btn btn-info btn-sm rightSize detailBtn" type="button" value="'+value+'" onclick="updateProduct(this.value)" ><i class="fa fa-paste"></i>修改</button>',
            '<button class="btn btn-danger btn-sm rightSize packageBtn" type="button" value="'+value+'" onclick="delProduct(this.value)"><i class="fa fa-envelope"></i>删除</button>'
        ].join(' ');
    }

    return productTableInit;
};

function updateProduct(value) {
    $.ajax({
        url: "/productservlet.do?operate=getUpdateProductJsp&productId="+arguments[0],
        type: "post",
        dataType: "text",
        success: function (data) {
            bootbox.dialog({
                message: data,
                title: "修改产品"
            })
        },
        error: function () {
            alert("通信失败");
        }
    });
}

function delProduct(productId) {
    bootbox.confirm({
        message: "确认删除？",
        buttons: {
            confirm: {
                label: '确认',
                className: 'btn-success'
            },
            cancel: {
                label: '取消',
                className: 'btn-danger'
            }
        },
        callback: function (result) {
            if (result) {
                $.ajax({
                    url: "/productservlet.do?operate=delproduct&productId="+productId,
                    type: "post",
                    dataType: "text",
                    success: function () {
                        $(".bootbox-close-button").click();
                    },
                    error: function () {
                        alert("删除失败");
                        // $(".bootbox-close-button").click();
                    }
                });
            } else {
                $(".bootbox-close-button").click();
            }
        }
    });
}


var ButtonInit = function () {
    var productInit = {};

    productInit.Init = function () {
        // 初始化页面上面的按钮事件

        $(document).click(function(e){
            var _con = $(".modal-content");   // 设置目标区域
            if(!_con.is(e.target) && _con.has(e.target).length === 0){ // Mark 1
                $(".bootbox-close-button").click();
            }
        });

        $("#btn_add").click(function () {
            $.ajax({
                url: "/productservlet.do?operate=getaddproductjsp",
                type: "post",
                dataType: "text",
                success: function (data) {
                    bootbox.dialog({
                        message: data,
                        title: "添加产品"
                    })
                },
                error: function () {
                    alert("通信失败");
                }
            });
        });

        $("#btn_query").click(function () {
            $("#tb_product").bootstrapTable('refresh');
        });

        /*弹出修改产品栏
        $("#btn_update_product").click(function () {
            alert(1);
            $.ajax({
                url: "/productservlet.do?operate=getupdateproductjsp",
                type: "post",
                dataType: "text",
                success: function (data) {
                    bootbox.dialog({
                        message: data,
                        title: "修改产品"
                    })
                },
                error: function () {
                    alert("通信失败");
                }
            });
        });*/

        /*$("#btn_edit").click(function () {
           var arrselections = $("#tb_product").bootstrapTable('getSelections');
           if (arrselections.length > 1) {
               toastr.warning('只能选择一行进行编辑');

               return;
           }
           if (arrselections.length <= 0) {
               toastr.warning('请选择有效数据');

               return;
           }
           $("#myModalLabel").text("编辑");
           $("#txt_departmentname").val(arrselections[0].DEPARTMENT_NAME);
           $("#txt_parentdepartment").val(arrselections[0].PARENT_ID);
           $("#txt_departmentlevel").val(arrselections[0].DEPARTMENT_LEVEL);
           $("#txt_statu").val(arrselections[0].STATUS);

           postdata.DEPARTMENT_ID = arrselections[0].DEPARTMENT_ID;
           $('#myModal').modal();
        });*/

        /*$("#btn_delete").click(function () {
           var arrselections = $("#tb_product").bootstrapTable('getSelections');
           if (arrselections.length <= 0) {
               toastr.warning('请选择有效数据');
               return;
           }

           Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
               if (!e) {
                   return;
               }
               $.ajax({
                   type: "post",
                   url: "/Home/Delete",
                   data: { "": JSON.stringify(arrselections) },
                   success: function (data, status) {
                       if (status == "success") {
                           toastr.success('提交数据成功');
                           $("#tb_product").bootstrapTable('refresh');
                       }
                   },
                   error: function () {
                       toastr.error('Error');
                   },
                   complete: function () {

                   }

               });
           });
        });*/

        /*$("#btn_submit").click(function () {
           postdata.DEPARTMENT_NAME = $("#txt_departmentname").val();
           postdata.PARENT_ID = $("#txt_parentdepartment").val();
           postdata.DEPARTMENT_LEVEL = $("#txt_departmentlevel").val();
           postdata.STATUS = $("#txt_statu").val();
           $.ajax({
               type: "post",
               url: "/Home/GetEdit",
               data: { "": JSON.stringify(postdata) },
               success: function (data, status) {
                   if (status == "success") {
                       toastr.success('提交数据成功');
                       $("#tb_product").bootstrapTable('refresh');
                   }
               },
               error: function () {
                   toastr.error('Error');
               },
               complete: function () {

               }

           });
        });*/
    };

    return productInit;
};