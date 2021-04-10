package cn.edu.nbut.jerry.POJO;

public class CarStore {
    private Integer storeId;
    private String storeAdminUsername;
    private String storePassword;
    private String storeName;
    private String storePhotoDirectory;
    private String storeContactNumber;
    private String storeArea;
    private String storeAddress;
    private String storeStatus;
    private String storeImagePath1;
    private String storeImagePath2;
    private String storeImagePath3;

    public CarStore() {
    }

    public CarStore(String storeAdminUsername, String storePassword, String storeName, String storePhotoDirectory, String storeContactNumber, String storeArea, String storeAddress, String storeStatus, String storeImagePath1, String storeImagePath2, String storeImagePath3) {
        this.storeAdminUsername = storeAdminUsername;
        this.storePassword = storePassword;
        this.storeName = storeName;
        this.storePhotoDirectory = storePhotoDirectory;
        this.storeContactNumber = storeContactNumber;
        this.storeArea = storeArea;
        this.storeAddress = storeAddress;
        this.storeStatus = storeStatus;
        this.storeImagePath1 = storeImagePath1;
        this.storeImagePath2 = storeImagePath2;
        this.storeImagePath3 = storeImagePath3;
    }

    public CarStore(Integer storeId, String storeAdminUsername, String storePassword, String storeName, String storePhotoDirectory, String storeContactNumber, String storeArea, String storeAddress, String storeStatus, String storeImagePath1, String storeImagePath2, String storeImagePath3) {
        this.storeId = storeId;
        this.storeAdminUsername = storeAdminUsername;
        this.storePassword = storePassword;
        this.storeName = storeName;
        this.storePhotoDirectory = storePhotoDirectory;
        this.storeContactNumber = storeContactNumber;
        this.storeArea = storeArea;
        this.storeAddress = storeAddress;
        this.storeStatus = storeStatus;
        this.storeImagePath1 = storeImagePath1;
        this.storeImagePath2 = storeImagePath2;
        this.storeImagePath3 = storeImagePath3;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreAdminUsername() {
        return storeAdminUsername;
    }

    public void setStoreAdminUsername(String storeAdminUsername) {
        this.storeAdminUsername = storeAdminUsername;
    }

    public String getStorePassword() {
        return storePassword;
    }

    public void setStorePassword(String storePassword) {
        this.storePassword = storePassword;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePhotoDirectory() {
        return storePhotoDirectory;
    }

    public void setStorePhotoDirectory(String storePhotoDirectory) {
        this.storePhotoDirectory = storePhotoDirectory;
    }

    public String getStoreContactNumber() {
        return storeContactNumber;
    }

    public void setStoreContactNumber(String storeContactNumber) {
        this.storeContactNumber = storeContactNumber;
    }

    public String getStoreArea() {
        return storeArea;
    }

    public void setStoreArea(String storeArea) {
        this.storeArea = storeArea;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(String storeStatus) {
        this.storeStatus = storeStatus;
    }

    public String getStoreImagePath1() {
        return storeImagePath1;
    }

    public void setStoreImagePath1(String storeImagePath1) {
        this.storeImagePath1 = storeImagePath1;
    }

    public String getStoreImagePath2() {
        return storeImagePath2;
    }

    public void setStoreImagePath2(String storeImagePath2) {
        this.storeImagePath2 = storeImagePath2;
    }

    public String getStoreImagePath3() {
        return storeImagePath3;
    }

    public void setStoreImagePath3(String storeImagePath3) {
        this.storeImagePath3 = storeImagePath3;
    }

    @Override
    public String toString() {
        return "CarStore{" +
                "storeId=" + storeId +
                ", storeAdminUsername='" + storeAdminUsername + '\'' +
                ", storePassword='" + storePassword + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storePhotoDirectory='" + storePhotoDirectory + '\'' +
                ", storeContactNumber='" + storeContactNumber + '\'' +
                ", storeArea='" + storeArea + '\'' +
                ", storeLocation='" + storeAddress + '\'' +
                ", storeStatus='" + storeStatus + '\'' +
                ", storeImagePath1='" + storeImagePath1 + '\'' +
                ", storeImagePath2='" + storeImagePath2 + '\'' +
                ", storeImagePath3='" + storeImagePath3 + '\'' +
                '}';
    }
}
