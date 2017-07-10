function getTime() {
    var times = new Date();
    var second = times.getSeconds();
    if(second < 10){
        second = "0"+second;
    }
    var minute = times.getMinutes();
    if(minute < 10){
        minute = "0"+minute;
    }
    var hour = times.getHours();
    if(hour < 10){
        hour = "0"+hour;
    }
    var day = times.getDate();
    var month = times.getMonth();
    month = month + 1;
    var year = times.getFullYear();
    var nowTime = hour+":"+minute+":"+second;
    var nowDate = year+"年"+month+"月"+day+"日";
    document.getElementById("nowDate").innerHTML = nowDate;
    document.getElementById("nowTime").innerHTML = nowTime;
}
var re;
function showTime() {
    re = setInterval(getTime,1000);
}

function stopTime() {
    clearInterval(re);
}

function sure() {
    var product = document.getElementsByName("product");
    // alert(product.length);
    var select = "";
    for(var i = 0;i < product.length;i++){
        if(product[i].checked){
            select = select + product[i].value+"\n";
        }
    }
    alert(select);
}