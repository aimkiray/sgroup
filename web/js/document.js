function email(){
    var email = document.getElementById("email").value;
    var regular = /^\w+@\w+\.[a-z]+$/;
    var checke = document.getElementById("checke");
    if(regular.test(email)){
        checke.innerHTML = "<font color=green>Excited!</font>";
    }
    if(!regular.test(email)){
        checke.innerHTML = "<font color=red>还有这种操作？</font>"
    }
}
function username(){
    var username = document.getElementById("username").value;
    var regular = /^\w{1,6}$/;
    if(regular.test(username)==true){
        checku.innerHTML = "<font color=green>Excited!</font>";
    }else{
        checku.innerHTML = "<font color=red>还有这种操作？</font>"
    }
}
function password(){
    var password = document.getElementById("password").value;
    var regular = /^[A-Z]{1}[A-Z|a-z|0-9]{5,11}$/;
    if(regular.test(password)==true){
        checkp.innerHTML = "<font color=green>Excited!</font>";
    }else{
        checkp.innerHTML = "<font color=red>还有这种操作？</font>";
    }
}
function password2(){
    if(document.getElementById("password").value == document.getElementById("password2").value){
        checkp2.innerHTML = "<font color=green>pass</font>";
    }else{
        checkp2.innerHTML = "<font color=red>interesting</font>";
    }
}
function recover(){
    document.getElementById("email").value = "";
    document.getElementById("username").value = "";
    document.getElementById("password").value = "";
    document.getElementById("password2").value = "";
    checke.innerHTML = "";
    checku.innerHTML = "";
    checkp.innerHTML = "";
    checkp2.innerHTML = "";
}
// function male(){
//     alert(document.getElementById("senddate").value);
// }

//省市县数据格式
var province_city_county_data=[
  {
    province:"江西省",
    city:[
      {
        cityName:"南昌",
        county:["东湖区","西湖区","青山湖区"]
      },
      {
        cityName:"九江",
        county:["浔阳区","濂溪区","九江县"]
      }
    ]
  },
  {
    province:"安徽省",
    city:[
      {  cityName:"安庆",
        county:["安庆市","怀宁县","潜山县"]
      },
      {  cityName:"蚌埠",
        county:["蚌埠市","固镇县","怀远县"]
      }
    ]
  }
]
var opt0 = ["请选择省份","请选择城市","请选择县区"];
var selectID_arr2=["provinceid","cityid","countyid"];
var province_index;
window.onload = function(){
  //初始化下拉框
  function init_select(){
    init_title();
    init_province();
    bind_province();
  }
  //初始化提示标题
  function init_title(){
    for(var k = 0;k<selectID_arr2.length;k++){
      var select_obj= document.getElementById(selectID_arr2[k]);
      select_obj.options[0]=new Option(opt0[k]);
    }
  }
  //初始化省份
  function init_province(){
    var province_select_obj = document.getElementById(selectID_arr2[0]);
    var province_len = province_city_county_data.length;
    for(var i = 0;i<province_len;i++){
      province_select_obj.options[i+1] = new Option(province_city_county_data[i].province,province_city_county_data[i].province);
    }
  }
  //给省份绑定onchange事件
  function bind_province(){
    var province_select_obj = document.getElementById(selectID_arr2[0]);
    province_select_obj.onchange=function(){
      var opt_index =province_select_obj.selectedIndex;
      if(opt_index!=0){
        province_index = opt_index-1;  //每个省份的序号比 option 的下标要小1
        init_city(province_index);
        bind_city();
        init_county(province_index,0);
      }else{
        clear_city();
        clear_county();
      }
    }
  }
  //选择一个省份才能初始化地级市
  function init_city(index){
    clear_city();
    var city_len = province_city_county_data[index].city.length;
    for(var i = 0;i<city_len;i++){
      document.getElementById(selectID_arr2[1]).options[i+1] = new Option(province_city_county_data[index].city[i].cityName,province_city_county_data[index].city[i].cityName);
    }
    document.getElementById(selectID_arr2[1]).options[1].selected = true;
  }
  //清除地级市信息
  function clear_city(){
    var city_select_obj = document.getElementById(selectID_arr2[1]);
    city_select_obj.options.length=0; //每次选中一个新的省份 都重新删除地级市的信息
    init_title();   //重新初始化标题
  }
  //给地级市绑定onchange事件
  function bind_city(){
    var city_select_obj = document.getElementById(selectID_arr2[1]);
    city_select_obj.onchange=function(){
      var opt_index =city_select_obj.selectedIndex;
      if(opt_index!=0){
        init_county(province_index,opt_index-1);
      }else{
        clear_county();
      }
    }
  }
  //选择一个地级市后才能初始化县
  function init_county(index,city_index){
    clear_county();
    var county_len = province_city_county_data[index].city[city_index].county.length;
    for(var i = 0;i<county_len;i++){
      document.getElementById(selectID_arr2[2]).options[i+1] = new Option(province_city_county_data[index].city[city_index].county[i],province_city_county_data[index].city[city_index].county[i]);
    }
    document.getElementById(selectID_arr2[2]).options[1].selected = true;
  }
  //清除县城信息
  function clear_county(){
    var county_select_obj = document.getElementById(selectID_arr2[2]);
    county_select_obj.options.length=0; //每次选中一个新的地级市 都重新删除县的信息
    init_title();   //重新初始化标题
  }
  init_select()
}