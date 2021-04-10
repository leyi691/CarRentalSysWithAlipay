function doEdit(){
    document.getElementById         ("inputPassword")                .disabled="";       // 将input标签的disabled属性去除
    document.getElementById         ("inputStoreName")               .disabled="";       // 将input标签的disabled属性去除
    document.getElementById         ("inputPhoneNumber")             .disabled="";       // 将input标签的disabled属性去除
    document.getElementById         ("inputStatusOpen")              .disabled="";       // 将input标签的disabled属性去除
    document.getElementById         ("inputStatusSuspended")         .disabled="";       // 将input标签的disabled属性去除
    document.getElementById         ("inputStatusTerminated")        .disabled="";       // 将input标签的disabled属性去除

    // document.getElementById("inputIdType")        .disabled="";       // 将input标签的disabled属性去除
    // document.getElementsByName("Province")        .disabled="";       // 将input标签的disabled属性去除
    // document.getElementById("inputIdNumber")      .disabled="";       // 将input标签的disabled属性去除
    document.getElementById         ("inputAddress")                 .disabled="";       // 将input标签的disabled属性去除
    // document.getElementById         ("uploadFirst")                  .disabled="";       // 将input标签的disabled属性去除
    // document.getElementById         ("uploadSecond")                 .disabled="";       // 将input标签的disabled属性去除
    // document.getElementById         ("uploadThird")                  .disabled="";
    document.getElementById         ("addImgInputBtn")               .disabled="";       // 将input标签的disabled属性去除
    document.getElementById         ("deleteImgInputBtn")            .disabled="";       // 将input标签的disabled属性去除
    document.getElementById         ("btn-submitEdit")               .hidden="";         // 将修改按钮的hidden属性去除
}
function sysDoEdit(){
    document.getElementById         ("inputPassword")                .disabled="";       // 将input标签的disabled属性去除
    document.getElementById         ("inputAdminName")               .disabled="";       // 将input标签的disabled属性去除
    document.getElementById         ("inputPhoneNumber")             .disabled="";       // 将input标签的disabled属性去除
    document.getElementById         ("btn-submitEdit")               .hidden="";         // 将修改按钮的hidden属性去除
}
function storeStatusSelectInit(str) {
    if          (str === "开业中") {
        document.getElementById("inputStatusOpen")     .checked=true;
        // document.getElementById("inputSexMan")      .checked=false;
        // document.getElementById("inputSexWoman")    .checked=false;
    } else if   (str === "暂停营业") {
        // document.getElementById("inputSexNone")     .checked=false;
        document.getElementById("inputStatusSuspended")      .checked=true;
        // document.getElementById("inputSexWoman")    .checked=false;
    } else if   (str === "永久停业") {
        // document.getElementById("inputSexNone")     .checked=false;
        // document.getElementById("inputSexMan")      .checked=false;
        document.getElementById("inputStatusTerminated")    .checked=true;
    }
}
function carTypeSelectInit(str){
    if          (str === "越野车") {
        document.getElementById("offRoadEdit")     .checked=true;
        // document.getElementById("inputSexMan")      .checked=false;
        // document.getElementById("inputSexWoman")    .checked=false;
    } else if   (str === "小轿车") {
        // document.getElementById("inputSexNone")     .checked=false;
        document.getElementById("normalCarEdit")      .checked=true;
        // document.getElementById("inputSexWoman")    .checked=false;
    } else if   (str === "跑车") {
        // document.getElementById("inputSexNone")     .checked=false;
        // document.getElementById("inputSexMan")      .checked=false;
        document.getElementById("sportCarEdit")    .checked=true;
    } else if   (str === "超级跑车") {
        // document.getElementById("inputSexNone")     .checked=false;
        // document.getElementById("inputSexMan")      .checked=false;
        document.getElementById("superCarEdit")    .checked=true;
    }
}
function carStatusSelectInit(str){
    if          (str === "在店内") {
        document.getElementById("availableEdit")     .checked=true;
        // document.getElementById("inputSexMan")      .checked=false;
        // document.getElementById("inputSexWoman")    .checked=false;
    } else if   (str === "已租出") {
        // document.getElementById("inputSexNone")     .checked=false;
        document.getElementById("rentedEdit")      .checked=true;
        // document.getElementById("inputSexWoman")    .checked=false;
    } else if   (str === "维修中") {
        // document.getElementById("inputSexNone")     .checked=false;
        // document.getElementById("inputSexMan")      .checked=false;
        document.getElementById("maintainEdit")    .checked=true;
    }
}
/**
 * 添加文件的时候增加<input>标签
 */
function addComponent() {
    let i = 1;
    this.value="";
    this.level=arguments.length;
    this.html =         '<span>'+"添加图片1"+'<input type="file" class="form-control" id="image'+i+'" name="image'+i+'"></span>';
    if (this.level > 1) {
        i++;
        this.html +=    '<span>'+"添加图片1"+'<input type="file" class="form-control" id="image'+i+'" name="image'+i+'"></span>';
    }
    if (this.level > 2) {
        i++;
        this.html +=    '<span>'+"添加图片1"+'<input type="file" class="form-control" id="image'+i+'" name="image'+i+'"></span>';
    }
    this.write = function (){
        document.write(this.html);
    }
    // document.getElementById("files").appendChild(uploadHTML);
    // uploadHTML = document.createElement("<input class='form-control' type='file' name='upload-" + i + "'>");
    // i++;
    // document.getElementById("files").appendChild(uploadHTML);
}

// 多文件上传处理input 开始
/**
 * 添加input标签 name属性值是attachName
 * @type {number}
 */
var i = 1;
function addInput(attachName){

    if(i>0&&i<=3){
        var attach = attachName + i ;
        if(createInput(attach))
            i++;
    } else if(i>=3) {
        // 提示用户只能有 3 张照片
        $("#warning").addClass("show");
        window.setTimeout(function(){
            $("#warning").removeClass("show");
        },3000);//显示的时间 3s
    }
}
function deleteInput(){
    if(i>1){
        i=i-1;
        if(!removeInput())
            i=i+1;
    }
}
function createInput(name){
    var aElement=document.createElement("input");
    aElement.name=name;
    aElement.className="form-control mt-1"
    aElement.id=name;
    aElement.type="file";
    aElement.size="50";
    aElement.required=true;
    // aElement.value="thanks";
    // aElement.onclick=Function("asdf()");
    return document.getElementById("upload").appendChild(aElement) != null;
}
function removeInput(name){
    var aElement = document.getElementById("upload");
    return aElement.removeChild(aElement.lastChild) != null;

}
// 多文件上传处理input 结束


// jsp的form表单自我提交
function sel(formId){
    document.getElementById("thisForm"+formId).submit();
}
function showModel(modalId){
    var myModal = new bootstrap.Modal(document.getElementById('staticBackdropEdit'), {
        keyboard: true // 设置键盘关闭模态框 (当设置为true时,按下 esc 键时关闭模态框,设置为 false 时则按键无效。)

    })
    myModal.show()
}
initOrderListBtn = function (status, id){
    if (status === '已完成') {
        document.getElementById("dispatchBtn"+id).className='btn btn-outline-success disabled';
        document.getElementById("returnBtn"+id).className='btn btn-outline-warning disabled';
        document.getElementById("cancelBtn"+id).className='btn btn-outline-danger disabled';
    } else if (status === '待支付') {
        document.getElementById("dispatchBtn"+id).className='btn btn-outline-success disabled';
        document.getElementById("returnBtn"+id).className='btn btn-outline-warning disabled';
        document.getElementById("cancelBtn"+id).className='btn btn-outline-danger';
    } else if (status === '待取车') {
        document.getElementById("dispatchBtn"+id).className='btn btn-outline-success disabled';
        document.getElementById("returnBtn"+id).className='btn btn-outline-warning';
        document.getElementById("cancelBtn"+id).className='btn btn-outline-danger disabled';
    } else if (status === '待还车') {
        document.getElementById("dispatchBtn"+id).className='btn btn-outline-success disabled';
        document.getElementById("returnBtn"+id).className='btn btn-outline-warning';
        document.getElementById("cancelBtn"+id).className='btn btn-outline-danger disabled';
    } else if (status === '超时关闭') {
        document.getElementById("dispatchBtn"+id).className='btn btn-outline-success disabled';
        document.getElementById("returnBtn"+id).className='btn btn-outline-warning disabled';
        document.getElementById("cancelBtn"+id).className='btn btn-outline-danger';
    } else if (status === '已支付') {
        document.getElementById("dispatchBtn"+id).className='btn btn-outline-success';
        document.getElementById("returnBtn"+id).className='btn btn-outline-warning disabled';
        document.getElementById("cancelBtn"+id).className='btn btn-outline-danger disabled';
    }
    console.log('id = ' + id)
    document.getElementById("dispatchBtn"+id).className='btn btn-outline-success disabled';
    document.getElementById("returnBtn"+id).className='btn btn-outline-warning disabled';
    document.getElementById("cancelBtn"+id).className='btn btn-outline-danger disabled';

}
function test_BTN_TEST(id){
    console.log('id = ' + id)
    document.getElementById("dispatchBtn"+id).className='btn btn-outline-success disabled';
    document.getElementById("returnBtn"+id).className='btn btn-outline-warning disabled';
    document.getElementById("cancelBtn"+id).className='btn btn-outline-danger disabled';
}

// 暂时用不上
// function idTypeSelectInit(str){
//     if          (str === "居民身份证")   {
//         document.getElementById("residentIdentityCard")                     .selected=true;
//     } else if   (str === "军官证")      {
//         document.getElementById("certificateOfOfficers")                    .selected=true;
//     } else if   (str === "武警警官证")   {
//         document.getElementById("certificateOfArmedPoliceOfficer")          .selected=true;
//     } else if   (str === "士兵证")      {
//         document.getElementById("soldierCertificate")                       .selected=true;
//     } else if   (str === "护照")      {
//         document.getElementById("passport")                                 .selected=true;
//     }
// }