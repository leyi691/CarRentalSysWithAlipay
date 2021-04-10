package cn.edu.nbut.jerry.POJO;

import java.util.Date;

public class Car {
    private Integer             carId;
    private Integer             storeId;
    private String              carPicture;             // 图片地址
    private String              licensePlateNumber;     // 车牌号
    private String              brand;                  // 车厂
    private String              brandNumber;            // 型号
    private String              carType;                // 4 种车辆类型： 越野车、小轿车、跑车、超级跑车。
    private String              rentalPrice;            // 每日租金
    private String              displacement;           // 车排量
    private Integer             cars;                   // 厢数
    private Integer             seats;                  // 座数
    private String              engineNumber;           // 引擎编号
    private String              frameNumber;            // 车架号
    private Date                purchaseDate;           // 购入日期
    private String              carStatus;              // 汽车状态

    public Car() {
    }

    public Car(Integer storeId, String carPicture, String licensePlateNumber, String brand, String brandNumber, String carType, String rentalPrice, String displacement, Integer cars, Integer seats, String engine, String engineNumber, String frameNumber, Date purchaseDate, String carStatus) {
        this.storeId = storeId;
        this.carPicture = carPicture;
        this.licensePlateNumber = licensePlateNumber;
        this.brand = brand;
        this.brandNumber = brandNumber;
        this.carType = carType;
        this.rentalPrice = rentalPrice;
        this.displacement = displacement;
        this.cars = cars;
        this.seats = seats;
        this.engineNumber = engineNumber;
        this.frameNumber = frameNumber;
        this.purchaseDate = purchaseDate;
        this.carStatus = carStatus;
    }

    public Car(Integer carId, Integer storeId, String carPicture, String licensePlateNumber, String brand, String brandNumber, String carType, String rentalPrice, String displacement, Integer cars, Integer seats, String engine, String engineNumber, String frameNumber, Date purchaseDate, String carStatus) {
        this.carId = carId;
        this.storeId = storeId;
        this.carPicture = carPicture;
        this.licensePlateNumber = licensePlateNumber;
        this.brand = brand;
        this.brandNumber = brandNumber;
        this.carType = carType;
        this.rentalPrice = rentalPrice;
        this.displacement = displacement;
        this.cars = cars;
        this.seats = seats;
        this.engineNumber = engineNumber;
        this.frameNumber = frameNumber;
        this.purchaseDate = purchaseDate;
        this.carStatus = carStatus;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(String rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public Integer getCars() {
        return cars;
    }

    public void setCars(Integer cars) {
        this.cars = cars;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }


    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", storeId=" + storeId +
                ", carPicture='" + carPicture + '\'' +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", brandNumber='" + brandNumber + '\'' +
                ", carType='" + carType + '\'' +
                ", rentalPrice='" + rentalPrice + '\'' +
                ", displacement='" + displacement + '\'' +
                ", cars=" + cars +
                ", seats=" + seats +
                ", engineNumber='" + engineNumber + '\'' +
                ", frameNumber='" + frameNumber + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", carStatus='" + carStatus + '\'' +
                '}';
    }
}
