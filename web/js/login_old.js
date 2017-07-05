/**
 * Created by neko on 2017/6/6.
 */
var password = "";
var show = true;
function checkRegular(node) {
    var regulars = "";
    if(arguments[1] == 1){
        regulars = /^[\u4e00-\u9fa5]{1,6}$/;
    }
    if(arguments[1] == 2){
        regulars = /^\w{8,12}@[a-z|A-Z|0-9]{2,3}\.[a-z|A-Z|0-9]{2,3}(\.cn)?$/;
    }
    if(arguments[1] == 3){
        password = arguments[0].value;
        regulars = /^\w{6,12}$/;
    }
    if(arguments[1] == 4){
        checkPasswordAgain(arguments[0].value,arguments[0].parentNode.parentNode.lastElementChild)
    }
    if(regulars.test(arguments[0].value)){
        arguments[0].parentNode.parentNode.lastElementChild.innerHTML = "<span style='color: green'>&nbsp;&nbsp;OK</span>";
    }else{
        arguments[0].parentNode.parentNode.lastElementChild.innerHTML = "<span style='color: red'>&nbsp;&nbsp;有什么不对?</span>";
    }
}

function checkPasswordAgain() {
    var regulars = /^\w{6,12}$/;
    if(regulars.test(arguments[0]) && (arguments[0] == password) && (password != "")){
        arguments[1].innerHTML = "<font color='green'>&nbsp;&nbsp;Pass<font>";
    }else{
        arguments[1].innerHTML = "<font color='red'>&nbsp;&nbsp;有什么不对?<font>";
    }
}

function showSuggest(node) {
    var suggest = [
        '@outlook.com',
        '@foxmail.com',
        '@gmail.com'
    ];
    if (show && (node.value.indexOf("@") != -1)) {
        for (var i = 0; i <= suggest.length - 1; i++) {
            var element = document.createElement("span");
            // element.setAttribute("floor", "left");
            element.setAttribute("class","suggest");
            element.setAttribute("onclick","suggest("+i+")");
            arguments[0].value = arguments[0].value + suggest[i];
            // node.parentNode.setAttribute("display", "block");
            element.innerHTML = "<br/>" + suggest[i];
            node.parentNode.appendChild(element);
            show = false;
        }
        show = false;
    }
}

// function suggest(num) {
//     var output = document.getElementById("inputMail");
//     var suggest = [
//         'outlook.com',
//         'foxmail.com',
//         'gmail.com'
//     ];
//     output.value = output.value + suggest[num];
// }