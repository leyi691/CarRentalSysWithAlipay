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