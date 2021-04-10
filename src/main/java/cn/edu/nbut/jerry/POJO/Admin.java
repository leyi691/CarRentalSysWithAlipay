package cn.edu.nbut.jerry.POJO;

public class Admin {
    private Integer adminId;    // 管理员ID
    private String adminUsername;    // 管理员用户名
    private String adminPassword;    // 管理员密码
    private String adminNickName;   // 管理员姓名
    private String adminPhoneNumber; // 管理员电话号码

    public Admin() {
    }

    public Admin(String adminUsername, String adminPassword, String adminNickName, String adminPhoneNumber) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.adminNickName = adminNickName;
        this.adminPhoneNumber = adminPhoneNumber;
    }

    public Admin(Integer adminId, String adminUsername, String adminPassword, String adminNickName, String adminPhoneNumber) {
        this.adminId = adminId;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.adminNickName = adminNickName;
        this.adminPhoneNumber = adminPhoneNumber;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminNickName;
    }

    public void setAdminName(String adminNickName) {
        this.adminNickName = adminNickName;
    }

    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public void setAdminPhoneNumber(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", username='" + adminUsername + '\'' +
                ", password='" + adminPassword + '\'' +
                ", adminNickName='" + adminNickName + '\'' +
                ", phoneNumber='" + adminPhoneNumber + '\'' +
                '}';
    }
}
