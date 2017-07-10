/**
 * Created by Akari on 2017/7/5.
 */
// 显示添加产品的单元格
function addTableTail() {
    var addTable = document.getElementById("add-product-table");
    addTable.setAttribute("class","table-body");
}
// 显示修改产品的单元格
function updateProduct() {
    // 获取当前的tr
    var thisLine = document.getElementById("table-body"+arguments[0]);
    // 隐藏当前行
    thisLine.setAttribute("class","hide-update-table");
    // 显示产品修改单元格
    var updateProduct = document.getElementById("update-product-table"+arguments[0]);
    updateProduct.setAttribute("class","table-body");
}
// 显示添加产品的单元格
function addProductType() {
    var addTypeTable = document.getElementById("add-type-table");
    addTypeTable.setAttribute("class","type-list-body");
}
// 显示修改产品类别的输入框
function updateType() {
    // 获得当前的span
    var thisType = document.getElementById("type-list"+arguments[0]);
    // 隐藏这个span
    thisType.setAttribute("class","hide-update-table");
    // 获得修改框
    var updateType = document.getElementById("update-type-input"+arguments[0]);
    // 显示修改框
    updateType.setAttribute("class","type-list");
}