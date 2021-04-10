/**
 * 将相应的类型 list 亮起
 * @param str
 */
function carTypeInit(str){
    if          (str === "所有车辆") {
        document.getElementById("allCar")           .className="list-group-item active";
    } else if   (str === "越野车") {
        document.getElementById("SUV")              .className="list-group-item active";
    } else if   (str === "小轿车") {
        document.getElementById("car")              .className="list-group-item active";
    } else if   (str === "跑车") {
        document.getElementById("sportCar")         .className="list-group-item active";
    } else if   (str === "超级跑车") {
        document.getElementById("superCar")         .className="list-group-item active";
    }
}
/**
 * 计算用户选择了几天，并以此计算总金额
 */
function countDays(){
    // console.log('dayOffset = ' + dayOffset);
    let p = document.getElementById("rentalDays");
    let startDay = document.getElementById("getCarDate").value;
    let endDay = document.getElementById("returnCarDate").value;
    let days = getNumberOfDays(startDay, endDay);
    p.innerText = days;
    let price = document.getElementById("price").innerText;
    let totalAmount = document.getElementById("totalAmount");
    // 还车日期不能早于取车日期
    if (days > 0 && getNumberOfDays(startDay, getDay(0)) <0){
        // 如果取车日期和还车日期都正常
        // 也就是 取车日期 < 还车日期 且 取车日期 > 今天
        document.getElementById("submitBtn").disabled = "";                 // 启用提交按钮
    } else if (days <= 0) {
        startDay=endDay=        '还车日期不能早于或等于取车日期';
        document.getElementById("submitBtn").disabled="disabled";           // 禁用提交按钮
    } else if (startDay === getDay(0)) {
        startDay = endDay =     '取车日期不能是今天';
        document.getElementById("submitBtn").disabled="disabled";           // 禁用提交按钮
    } else if (getNumberOfDays(startDay, getDay(0)) > 0){
        startDay=endDay=        '取车日期不能早于今天';
        document.getElementById("submitBtn").disabled="disabled";           // 禁用提交按钮
    }
    totalAmount.innerText=accMultiply(days, price); // 使用精确乘法
    document.getElementById("totalAmountInput")     .value          =       accMultiply(days, price);
    document.getElementById("inputGetCarDate")      .value          =       startDay;
    document.getElementById("inputReturnCarDate")   .value          =       endDay;
    document.getElementById("showGetCarDate")       .innerText      =       startDay;
    document.getElementById("showReturnCarDate")    .innerText      =       endDay;
}

// function add20Cars(elementId){
//     document.getElementById(elementId).innerHTML = "<th scope=\"row\">\n" +
//         "                                <img class=\"card-img-top img-fluid\" src=\"<%=car.getCarPicture()%>\" alt=\"汽车图片\"\n" +
//         "                                     style=\"width: 12vw; height: 7vw;\">\n" +
//         "                            </th>\n" +
//         "                            <td><h4><span class=\"text-dark\"><%=car.getBrand()%> <%=car.getBrandNumber()%></span></h4><br>\n" +
//         "                                <%=car.getCars()%>厢 ｜ <%=car.getSeats()%>座 | <%=car.getDisplacement()%>\n" +
//         "                                <br>\n" +
//         "                            </td>\n" +
//         "                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n" +
//         "                            <td></td>\n" +
//         "                            <td>¥<span class=\"text-danger display-6\"><%=car.getRentalPrice()%>></span> 元/天</td>\n" +
//         "                            <td><a type=\"submit\" class=\"btn btn-primary\"\n" +
//         "                                   href=\"<%=basePath%>/doGoods?op=rentalCar&carId=<%=car.getCarId()%>\">\n" +
//         "                                立即预订</a>\n" +
//         "                            </td>";
// }

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
// function alertSuccess(){
//
// }
// function alertWarning(){
//         $("#warning").addClass("show");
//         window.setTimeout(function(){
//             $("#warning").removeClass("show");
//         },2000);//显示的时间
// }
// function alertDanger(){
//     $("#danger").addClass("show");
//     window.setTimeout(function(){
//         $("#danger").removeClass("show");
//     },2000);//显示的时间
// }