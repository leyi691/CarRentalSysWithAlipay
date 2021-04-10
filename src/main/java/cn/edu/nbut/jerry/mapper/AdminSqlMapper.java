package cn.edu.nbut.jerry.mapper;

import cn.edu.nbut.jerry.POJO.Admin;

import java.util.List;

/**
 * 这个接口名和包名要和DeptSqlMapper.xml文件中<mapper>中的 namespace 相同
 */
public interface AdminSqlMapper {
    public List<Admin> selectAll();
    public Admin selectAdminById(Integer id);
    public Admin selectAdminByUserName(String adminName);
    public int selectAdminByUserNameForCheck(Admin admin);
    public int insertAdminById(Admin admin);
    public int updateAdminById(Admin admin);
    public int updateAdminByIdWithoutPassword(Admin admin);
    public int deleteAdminById(Integer id);
}
