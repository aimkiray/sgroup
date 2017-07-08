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
    // 获取当前行数
    var thisLine = arguments[0];
    // 删除当前行
    var allProduct = document.getElementById("all-product-list");
    allProduct.deleteRow(thisLine);
    // 显示隐藏的产品修改单元格
    var updateProduct = document.getElementById("update-product-table"+arguments[0]);
    updateProduct.setAttribute("class","table-body");

}