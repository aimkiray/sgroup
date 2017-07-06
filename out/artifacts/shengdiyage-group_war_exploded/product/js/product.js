/**
 * Created by Akari on 2017/7/5.
 */
function addTableTail() {
    var tr = document.getElementById("all-product-list");
    var newtr = tr.insertRow(tr.rows.length-1);
    newtr.innerHTML = "" +
        "<td colspan='7'>" +
        "<form action='/product/product_add.jsp' method='post'>" +
        "<span class='add-product-text'>请按类别输入：</span>" +
        "<input class='add-product' id='add-product' type='text' name='pname'>" +
        "<input class='add-product' id='add-product' type='text' name='pprice'>" +
        "<input class='add-product' id='add-product' type='text' name='pnumber'>" +
        "<input class='add-product' id='add-product' type='text' name='ptype'>" +
        "<input type='submit' value='确认'>" +
        "</form>" +
        "</td>";
    // newtr.insertCell(0).innerHTML = "<form action='/product/product_add.jsp' method='post'>";
    // for (var i = 1; i < tr.rows[0].cells.length-1; i++) {
    //     newtr.insertCell(i).innerHTML = "<input class='add-product' id='add-product' type='text' name='"+i+"'>";
    // }
    // newtr.insertCell(tr.rows[0].cells.length-1).innerHTML = "<input type='submit' value='确认'></form>";
}