/**
 * Created by Akari on 2017/7/5.
 */
// 显示添加产品的单元格
function addTableTail() {
    var addTable = document.getElementById("add-product-table");
    addTable.setAttribute("class", "table-body");
}
// 显示修改产品的单元格
function updateProduct() {
    // 获取当前的tr
    var thisLine = document.getElementById("table-body" + arguments[0]);
    // 隐藏当前行
    thisLine.setAttribute("class", "hide-update-table");
    // 显示产品修改单元格
    var updateProduct = document.getElementById("update-product-table" + arguments[0]);
    // alert(updateProduct.ownerDocument);
    // var list = updateProduct.getElementsByTagName("td");
    // alert(list);
    // for (var i = 0; i < list.length; i++) {
    //     updateForm.appendChild(list[i]);
    // }
    // alert(updateProduct.ownerDocument);
    // var updateForm = document.createElement("form");
    // updateForm.appendChild(updateProduct.ownerDocument);
    // alert(updateForm.innerHTML);
    // updateProduct.innerHTML = "";
    // updateProduct.appendChild(updateForm);

    // 在外面加上form表单用于提交
    // updateProduct.innerHTML = "<form id='products'>" +
    //     updateProduct.innerHTML +
    //     "</form>";
    updateProduct.setAttribute("class", "table-body");
}
// 显示添加产品的单元格
function addProductType() {
    var addTypeTable = document.getElementById("add-type-table");
    addTypeTable.setAttribute("class", "type-list-body");
}
// 显示修改产品类别的输入框
function updateType() {
    // 获得当前的span
    var thisType = document.getElementById("type-list" + arguments[0]);
    // 隐藏这个span
    thisType.setAttribute("class", "hide-update-table");
    // 获得修改框
    var updateType = document.getElementById("update-type-input" + arguments[0]);
    // 显示修改框
    updateType.setAttribute("class", "type-list");
}

// 产品全选框
function checkAll() {
    addFormToMulDel();
    // 获得所有checkbox
    var check = document.getElementsByName("check_product");
    // 如果全选框被选中
    if (check[0].checked) {
        // 选中所有checkbox
        for (var i = 1; i < check.length; i++) {
            check[i].checked = true;
        }
    }
    // 如果除全选框外的checkbox全部选中
    var temp = true;
    for (i = 1; i < check.length; i++) {
        temp = (temp && check[i].checked)
    }
    // 选中全选框
    if (temp) {
        check[0].checked = true;
    }
}

// 产品单选框
function checkOne() {
    // 切换到批量删除模式
    addFormToMulDel();
    var check = document.getElementsByName("check_product");
    if (check[0].checked) {
        check[0].checked = false;
    }
    var temp = true;
    for (var i = 1; i < check.length; i++) {
        temp = (temp && check[i].checked)
    }
    if (temp) {
        check[0].checked = true;
    }
}

// 添加用于批量删除的form表单
function addFormToMulDel() {
    var mainTable = document.getElementById("");
}

// 切换form表单的提交动作
// 修改产品
function updateProductAction() {
    // 获得修改框
    var form = document.getElementById("products");
    form.action = "/productservlet.do?operate=updateproduct&what=" + arguments[0];
    form.enctype = "multipart/form-data";
}

// 批量删除
function delMulAction() {
    if (confirm('确认删除选中项？')) {
        var form = document.getElementById("products");
        form.action = "/productservlet.do?operate=muldel";
        form.enctype = "application/x-www-form-urlencoded";
    } else {
        return false;
    }
}

// 产品搜索功能
// function searchProduct() {
//
// }

// 创建一个ajax对象
function createXMLHttpRequest() {
    try {
        return new XMLHttpRequest();
    } catch (e) {
        try {
            return new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    return null;
}

// 产品类别添加验证(ajax)
function addTypeNotice() {

    var typeName = document.getElementById("add-type").value;
    if (typeName != "") {
        var xmlHttp = createXMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                if (xmlHttp.responseText == "true") {
                    document.getElementById("add-type-notice").innerHTML = "<span style='color: green'>可以添加</span>";
                } else {
                    document.getElementById("add-type-notice").innerHTML = "<span style='color: red'>该类别已存在</span>";
                }
            }
        }
        xmlHttp.open("post", "/productservlet.do", true);
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xmlHttp.send("operate=checkTypeName&typeName=" + typeName);
    }
}

// 产品类别下的所有产品(ajax)
function accurateSearch() {
    // 初始化产品列表
    document.getElementById("show-product-list").length = 1;
    var typeId = document.getElementById("type-id").value;
    if (typeId != "0") {
        var xmlHttp = createXMLHttpRequest();
        var productList = document.getElementById("show-product-list");
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var strProducts = xmlHttp.responseText;
                var products = JSON.parse(strProducts);
                for (var i = 0; i < products.length; i++) {
                    productList.add(new Option(products[i].productName,products[i].productId));
                }
            }
        }
        xmlHttp.open("post", "/productservlet.do", true);
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xmlHttp.send("operate=productsByType&typeId=" + typeId);
    }
}