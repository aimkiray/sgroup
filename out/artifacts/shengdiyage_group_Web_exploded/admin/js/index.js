/**
 * Created by Akari on 2017/7/11.
 */
var a = document.title;

window.onblur = function() {
    document.title = "( / ω · * ) 盯…";
}, window.onfocus = function() {
    document.title = a;
};