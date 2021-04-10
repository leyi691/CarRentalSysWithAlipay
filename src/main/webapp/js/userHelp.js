function doEdit(){
    document.getElementById("inputPassword")      .disabled="";       // 将input标签的disabled属性去除
    document.getElementById("inputNickname")      .disabled="";       // 将input标签的disabled属性去除
    document.getElementById("inputSexNone")       .disabled="";       // 将input标签的disabled属性去除
    document.getElementById("inputSexMan")        .disabled="";       // 将input标签的disabled属性去除
    document.getElementById("inputSexWoman")      .disabled="";       // 将input标签的disabled属性去除
    document.getElementById("inputPhoneNumber")   .disabled="";       // 将input标签的disabled属性去除
    document.getElementById("inputIdType")        .disabled="";       // 将input标签的disabled属性去除
    // document.getElementsByName("Province")        .disabled="";       // 将input标签的disabled属性去除
    document.getElementById("inputIdNumber")      .disabled="";       // 将input标签的disabled属性去除
    document.getElementById("inputAddress")       .disabled="";       // 将input标签的disabled属性去除
    document.getElementById("formFile")           .disabled="";       // 将input标签的disabled属性去除
    document.getElementById("btn-submitEdit")     .hidden="";         // 将修改按钮的hidden属性去除
}
function sexSelectInit(str){
    if          (str === "无") {
        document.getElementById("inputSexNone")     .checked=true;
        // document.getElementById("inputSexMan")      .checked=false;
        // document.getElementById("inputSexWoman")    .checked=false;
    } else if   (str === "男") {
        // document.getElementById("inputSexNone")     .checked=false;
        document.getElementById("inputSexMan")      .checked=true;
        // document.getElementById("inputSexWoman")    .checked=false;
    } else if   (str === "女") {
        // document.getElementById("inputSexNone")     .checked=false;
        // document.getElementById("inputSexMan")      .checked=false;
        document.getElementById("inputSexWoman")    .checked=true;
    }
}
function idTypeSelectInit(str){
    if          (str === "居民身份证")   {
        document.getElementById("residentIdentityCard")                     .selected=true;
    } else if   (str === "军官证")      {
        document.getElementById("certificateOfOfficers")                    .selected=true;
    } else if   (str === "武警警官证")   {
        document.getElementById("certificateOfArmedPoliceOfficer")          .selected=true;
    } else if   (str === "士兵证")      {
        document.getElementById("soldierCertificate")                       .selected=true;
    } else if   (str === "护照")      {
        document.getElementById("passport")                                 .selected=true;
    }
}
// 各种提醒（alert）的地方
// 模板

// if(条件){
//         $("#success").addClass("show");
//         window.setTimeout(function(){
//          		$("#success").removeClass("show");
//          },1000);//显示的时间
// }
// if(条件){
//          $("#fail").addClass("show");
//          window.setTimeout(function(){
//           		$("#fail").removeClass("show");
//          },1000);//显示的时间
// }
function alertSuccess(){

}
function alertWarning(){
        $("#warning").addClass("show");
        window.setTimeout(function(){
            $("#warning").removeClass("show");
        },2000);//显示的时间
}
function alertDanger(){
    $("#danger").addClass("show");
    window.setTimeout(function(){
        $("#danger").removeClass("show");
    },2000);//显示的时间
}