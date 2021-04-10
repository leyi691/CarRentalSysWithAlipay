package cn.edu.nbut.jerry.POJO;

import java.util.Date;

public class Order {
    private Integer         orderId;            // 订单号
    private String          outTradeNo;         // 商户唯一订单编号
    private Integer         carId;              // 车辆编号
    private Integer         userId;             // 用户编号
    private Integer         storeId;            // 取车门店Id
    private Integer         returnCarStoreId;   // 还车门店Id
    private String          orderSubject;       // 订单标题
    private String          totalAmount;        // 订单总金额
    private String          orderInfo;          // 订单描述
    private String          orderDate;          // 下单日期
    private Date            rentalDate;         // 取车日期
    private Date            returnDate;         // 还车日期
    private String          orderStatus;        // 订单状态： 未支付、待取车、租赁中、已取消、已完成
    private String          remark;             // 备注
    // 新增的
    private String          brand;              // 车厂
    private String          brandNumber;        // 车型
    private String          rentalPrice;        // 租赁价格
    private String          carType;            // 汽车类型
    private String          displacement;       // 排量
    private String          storeName;          // 商店名
    private String          returnStoreName;    // 还车商店名
    // 新增(待出车)
    private String          carPicture;         // 汽车图片
    private String          licensePlateNumber; // 车牌号
    private String          storeContactNumber; // 联系电话
    // 新增（用户名，用户昵称）
    private String          username;           // 用户名
    private String          nickname;           // 用户昵称

    public Order() {
    }

    public Order(String outTradeNo, Integer carId, Integer userId, Integer storeId, Integer returnCarStoreId, String orderSubject, String totalAmount, String orderInfo, String orderDate, Date rentalDate, Date returnDate, String orderStatus, String remark, String brand, String brandNumber, String rentalPrice, String carType, String displacement, String storeName, String returnStoreName, String carPicture, String licensePlateNumber, String storeContactNumber, String username, String nickname) {
        this.outTradeNo = outTradeNo;
        this.carId = carId;
        this.userId = userId;
        this.storeId = storeId;
        this.returnCarStoreId = returnCarStoreId;
        this.orderSubject = orderSubject;
        this.totalAmount = totalAmount;
        this.orderInfo = orderInfo;
        this.orderDate = orderDate;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.orderStatus = orderStatus;
        this.remark = remark;
        this.brand = brand;
        this.brandNumber = brandNumber;
        this.rentalPrice = rentalPrice;
        this.carType = carType;
        this.displacement = displacement;
        this.storeName = storeName;
        this.returnStoreName = returnStoreName;
        this.carPicture = carPicture;
        this.licensePlateNumber = licensePlateNumber;
        this.storeContactNumber = storeContactNumber;
        this.username = username;
        this.nickname = nickname;
    }

    public Order(Integer orderId, String outTradeNo, Integer carId, Integer userId, Integer storeId, Integer returnCarStoreId, String orderSubject, String totalAmount, String orderInfo, String orderDate, Date rentalDate, Date returnDate, String orderStatus, String remark, String brand, String brandNumber, String rentalPrice, String carType, String displacement, String storeName, String returnStoreName, String carPicture, String licensePlateNumber, String storeContactNumber, String username, String nickname) {
        this.orderId = orderId;
        this.outTradeNo = outTradeNo;
        this.carId = carId;
        this.userId = userId;
        this.storeId = storeId;
        this.returnCarStoreId = returnCarStoreId;
        this.orderSubject = orderSubject;
        this.totalAmount = totalAmount;
        this.orderInfo = orderInfo;
        this.orderDate = orderDate;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.orderStatus = orderStatus;
        this.remark = remark;
        this.brand = brand;
        this.brandNumber = brandNumber;
        this.rentalPrice = rentalPrice;
        this.carType = carType;
        this.displacement = displacement;
        this.storeName = storeName;
        this.returnStoreName = returnStoreName;
        this.carPicture = carPicture;
        this.licensePlateNumber = licensePlateNumber;
        this.storeContactNumber = storeContactNumber;
        this.username = username;
        this.nickname = nickname;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getReturnCarStoreId() {
        return returnCarStoreId;
    }

    public void setReturnCarStoreId(Integer returnCarStoreId) {
        this.returnCarStoreId = returnCarStoreId;
    }

    public String getOrderSubject() {
        return orderSubject;
    }

    public void setOrderSubject(String orderSubject) {
        this.orderSubject = orderSubject;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandNumber() {
        return brandNumber;
    }

    public void setBrandNumber(String brandNumber) {
        this.brandNumber = brandNumber;
    }

    public String getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(String rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getReturnStoreName() {
        return returnStoreName;
    }

    public void setReturnStoreName(String returnStoreName) {
        this.returnStoreName = returnStoreName;
    }

    public String getCarPicture() {
        return carPicture;
    }

    public void setCarPicture(String carPicture) {
        this.carPicture = carPicture;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getStoreContactNumber() {
        return storeContactNumber;
    }

    public void setStoreContactNumber(String storeContactNumber) {
        this.storeContactNumber = storeContactNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", carId=" + carId +
                ", userId=" + userId +
                ", storeId=" + storeId +
                ", returnCarStoreId=" + returnCarStoreId +
                ", orderSubject='" + orderSubject + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", orderInfo='" + orderInfo + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", remark='" + remark + '\'' +
                ", brand='" + brand + '\'' +
                ", brandNumber='" + brandNumber + '\'' +
                ", rentalPrice='" + rentalPrice + '\'' +
                ", carType='" + carType + '\'' +
                ", displacement='" + displacement + '\'' +
                ", storeName='" + storeName + '\'' +
                ", returnStoreName='" + returnStoreName + '\'' +
                ", carPicture='" + carPicture + '\'' +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                ", storeContactNumber='" + storeContactNumber + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
