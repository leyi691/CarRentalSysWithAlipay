/**
 * elementId 修改的input标签ID
 * days 指 offset 的天数
 */
function setDate(elementId, days){
    document.getElementById(elementId).value=getDay(days);
    countDays();
}

/**
 * 日期相减得到天数 endDate - startDate
 */
function getNumberOfDays(startDate, endDate){
    // startDate:   开始日期
    // endDate:     结束日期
    var startDay =  Date.parse(new Date(startDate));
    var endDay   =  Date.parse(new Date(endDate));
    return parseInt((endDay - startDay) / (1000 * 60 * 60 * 24))
}

/**
 * 根据当天获取某一天
 * getDay(0) 当天
 * getDay(7) 7天后
 * getDay(-7) 7天前
 * 输出格式： 2020-01-01
 */
function getDay(day){
    function doHandleMonth(month){
        var m = month;
        if(month.toString().length === 1){
            m = "0" + month;
        }
        return m;
    }
    var today = new Date();
    var targetday_milliseconds=today.getTime() + 1000*60*60*24*day;
    today.setTime(targetday_milliseconds); //注意，这行是关键代码
    var tYear = today.getFullYear();
    var tMonth = today.getMonth();
    var tDate = today.getDate();
    tMonth = doHandleMonth(tMonth + 1);
    tDate = doHandleMonth(tDate);
    return tYear+"-"+tMonth+"-"+tDate;
}

/**
 * 精度浮点加法
 * @param arg1
 * @param arg2
 * @returns {number}
 */
function accAdd(arg1, arg2) {
    var r1, r2, m, c;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    c = Math.abs(r1 - r2);
    m = Math.pow(10, Math.max(r1, r2));
    if (c > 0) {
        var cm = Math.pow(10, c);
        if (r1 > r2) {
            arg1 = Number(arg1.toString().replace(".", ""));
            arg2 = Number(arg2.toString().replace(".", "")) * cm;
        } else {
            arg1 = Number(arg1.toString().replace(".", "")) * cm;
            arg2 = Number(arg2.toString().replace(".", ""));
        }
    } else {
        arg1 = Number(arg1.toString().replace(".", ""));
        arg2 = Number(arg2.toString().replace(".", ""));
    }
    return (arg1 + arg2) / m;
}

/**
 * 精度浮点减法
 * @param arg1
 * @param arg2
 * @returns {string}
 */
function accSubtract(arg1, arg2) {
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2)); //last modify by deeka //动态控制精度长度
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m).toFixed(n);
}

/**
 * 精度浮点乘法
 * @param arg1
 * @param arg2
 * @returns {number} 精确到2位小数
 */
function accMultiply(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length;
    }
    catch (e) {
    }
    try {
        m += s2.split(".")[1].length;
    }
    catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
}

/**
 * 精度浮点除法
 * @param arg1
 * @param arg2
 * @returns {number} 精确到2位小数
 */
function accDivision(arg1, arg2) {
    var t1 = 0, t2 = 0, r1, r2;
    try {
        t1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
    }
    try {
        t2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
    }
    with (Math) {
        r1 = Number(arg1.toString().replace(".", ""));
        r2 = Number(arg2.toString().replace(".", ""));
        return (r1 / r2) * pow(10, t2 - t1);
    }
}

/**
 * jsp Form 表单自我提交
 * @param formId
 */
function sel(formId){
    document.getElementById("thisForm"+formId).submit();
}

/**
 * 页面打开自动显示 modal 模态框
 * @param modalId
 */
function showModel(modalId){
    var myModal = new bootstrap.Modal(document.getElementById(modalId), {
        keyboard: true // 设置键盘关闭模态框 (当设置为true时,按下 esc 键时关闭模态框,设置为 false 时则按键无效。)

    })
    myModal.show()
}