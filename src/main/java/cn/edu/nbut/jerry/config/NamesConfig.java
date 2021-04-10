package cn.edu.nbut.jerry.config;

public class NamesConfig {

    public static String        CAR_STORE_DISPATCH_DO =            "carDispatch";
    /**
     * 订单状态 (4种)
     */
    public static final String        TRADE_SUCCESS               =           "已支付";
    public static final String        WAIT_BUYER_PAY              =           "待支付";
    public static final String        WAIT_BUYER_GET_CAR          =           "租赁中";
    public static final String        TRADE_CLOSED                =           "超时关闭";
    public static final String        TRADE_FINISHED              =           "已完成";
    public static final String        TRADE_REFUND                =           "已退款";
    public static final String        ORDER_NOT_EXISTS            =           "订单不存在";
    public static final String        REFUND_REASON               =           "商铺取消订单，商铺无法提供车辆";
    public static final String        RETURN_URL_USER             =           "http://localhost:8080/doGoods?op=returnUrl";
    public static final String        NOTIFY_URL_USER             =           "http://localhost:8080/doGoods?op=notifyUrl";
    public static final String        RETURN_URL_STORE            =           "http://localhost:8080/doCarStore?op="+CAR_STORE_DISPATCH_DO;
    public static final String        NOTIFY_URL_STORE            =           "http://localhost:8080/doCarStore?op=notifyUrl";

//    public static Integer       ORDER_TIME_LIMIT            =            300;   // 300s（5 分钟） 询问一次
//    public static Integer       ORDER_TOTAL_TIME            =            -1800*1000;  // 总限制时间为30分钟
//    public static Integer       ORDER_POLLING_COUNT         =            6;     // 订单状态重复轮询次数，执行次数是该次数+1

    public static final Integer       ORDER_TIME_LIMIT_TEST       =            1;             // 1s 询问一次
    public static final Integer       ORDER_TOTAL_TIME_TEST       =            -1800*1000;    // 总限制时间为 XXs 如果 XXs 内不支付 则 订单状态就会修改为超时
    public static final Integer       ORDER_POLLING_COUNT_TEST    =            30;            // 订单状态轮询重复次数,执行次数是该次数+1

    public static final String        CAR_AT_SERVICE              =            "已租出";
    public static final String        CAR_IN_SHOP                 =            "在店内";
    public static final String        CAR_IN_REPAIR               =            "维修中";

    public static final String        STORE_STATUE_OPEN           =            "开业中";
    public static final String        STORE_STATUE_SUSPEND        =            "暂停营业";
    public static final String        STORE_STATUE_CLOSED         =            "永久停业";

    public static final String        ALIPAY_RETURN_URL           =            "http://localhost:8080/doGoods?op=returnUrl";

    public static final String        HEADER_FIRST                =            "首页";
    public static final String        HEADER_SECOND               =            "租车";
    public static final String        HEADER_THIRD                =            "支持";
    public static final String        HEADER_FORTH_STORE          =            "店铺管理主页";
    public static final String        HEADER_FORTH_USER           =            "个人信息主页";
    public static final String        HEADER_FORTH_SYSADMIN       =            "系统管理员主页";
    public static final String        HEADER_FIFTH_SIGNUP_IN      =            "登录 / 注册";
    public static final String        HEADER_FIFTH_EXIT           =            "退出系统";

    public static final String        HEADER_SUPPORT_LINK         =            "<script type=\"text/javascript\">alert(\"需要支持？请拨打支持电话：400-888-888\");</script>";    // 弃用
    public static final String        HEADER_SUPPORT_LINK_MODAL_BTN=            "<a type=\"button\" class=\"py-2 d-none d-md-inline-block\" data-bs-toggle=\"modal\" data-bs-target=\"#supportModal\">"+HEADER_THIRD+"</a>";


    public static final String        SEARCH_TYPE_CAR_BRAND       =            "按汽车品牌搜索";
    public static final String        SEARCH_TYPE_ORDER_STATUE    =            "按订单状态搜索";
    public static final String        SEARCH_TYPE_CAR_LICENSE_NO  =            "按汽车牌照搜索";

    public static final int           ONE_PAGE_SIZE_SHORT         =            6;         // 一个页面显示最多6项内容
    public static final int           ONE_PAGE_SIZE_MID           =            10;         // 一个页面显示最多10项内容
    public static final int           ONE_PAGE_SIZE_LONG          =            14;        // 一个页面显示最多14项内容
}
