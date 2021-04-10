package cn.edu.nbut.jerry.service.impl;

import cn.edu.nbut.jerry.POJO.Admin;
import cn.edu.nbut.jerry.Utils.MyBatisUtils;
import cn.edu.nbut.jerry.mapper.AdminSqlMapper;
import cn.edu.nbut.jerry.service.AdminService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminSqlMapper adminSqlMapper = null;

    /**
     * 新增管理员账号
     * @param admin
     * @return
     */
    @Override
    public int addAdminAccount(Admin admin) {
        int result = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            adminSqlMapper = sqlSession.getMapper(AdminSqlMapper.class);
            // 在service中异常自己处理而不往外抛了
            // 管理员用户名不能重复
            Admin realAdmin = adminSqlMapper.selectAdminByUserName(admin.getAdminName());
            if (realAdmin == null) {
                result = adminSqlMapper.insertAdminById(admin);
            } else {
                result = -1; // -1 代表管理员名已经存在
            }
            sqlSession.commit();    // 提交 SQL 语句
        } catch (SqlSessionException exception) {
            sqlSession.rollback();
            exception.printStackTrace();
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }

    /**
     * 修改管理员账户信息
     * @param admin
     * @return
     */
    @Override
    public int updateAdminAccountById(Admin admin) {
        int result = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            adminSqlMapper = sqlSession.getMapper(AdminSqlMapper.class);
            // 在service中异常自己处理而不往外抛了
            Admin realAdmin = adminSqlMapper.selectAdminByUserName(admin.getAdminUsername());
            if (realAdmin == null) {
                result = adminSqlMapper.updateAdminById(admin);
            }
            // 两个情况：
            // 1）名字没改，导致与自己的名字重复了
            // 2）名字改成重复的名字了
                // 1.名字没改的情况，这里应该允许更改。
            else if(realAdmin.getAdminId().equals(admin.getAdminId())){
                result = adminSqlMapper.updateAdminById(admin);
            }
                // 2.名字改了但是与其他名字重复，不予更改。
            else { result = -1; }
            sqlSession.commit();
        } catch (SqlSessionException exception) {
            sqlSession.rollback();
            System.out.println(exception);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }

    @Override
    public int updateAdminByIdWithoutPassword(Admin admin) {
        int result = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            adminSqlMapper = sqlSession.getMapper(AdminSqlMapper.class);
            // 在service中异常自己处理而不往外抛了
            Admin realAdmin = adminSqlMapper.selectAdminByUserName(admin.getAdminUsername());
            if (realAdmin == null) {
                result = adminSqlMapper.updateAdminByIdWithoutPassword(admin);
            }
            // 两个情况：
            // 1）名字没改，导致与自己的名字重复了
            // 2）名字改成重复的名字了
            // 1.名字没改的情况，这里应该允许更改。
            else if(realAdmin.getAdminId().equals(admin.getAdminId())){
                result = adminSqlMapper.updateAdminByIdWithoutPassword(admin);
            }
            // 2.名字改了但是与其他名字重复，不予更改。
            else { result = -1; }
            sqlSession.commit();
        } catch (SqlSessionException exception) {
            sqlSession.rollback();
            System.out.println(exception);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }

    /**
     * 通过id来删除管理员账户
     * @param adminId
     * @return
     */
    @Override
    public int deleteAdminAccountById(Integer adminId) {
        int result = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            adminSqlMapper = sqlSession.getMapper(AdminSqlMapper.class);
            // 在service中异常自己处理而不往外抛了
            result = adminSqlMapper.deleteAdminById(adminId);
            sqlSession.commit();    // 提交 SQL 语句
        } catch (SqlSessionException exception) {
            sqlSession.rollback();
            System.out.println(exception);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }

    /**
     * 查询所有管理员信息
     * @return
     */
    @Override
    public List<Admin> getAll_AdminAccount() {
        SqlSession sqlSession = null;
        List<Admin> admins = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            adminSqlMapper = sqlSession.getMapper(AdminSqlMapper.class);
            // 在service中异常自己处理而不往外抛了
            admins = adminSqlMapper.selectAll();
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return admins;
    }

    /**
     * 通过id查询管理员信息
     * @param adminId
     * @return
     */
    @Override
    public Admin getAdminAccountById(Integer adminId) {
        SqlSession sqlSession = null;
        Admin admin;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            adminSqlMapper = sqlSession.getMapper(AdminSqlMapper.class);
            // 在service中异常自己处理而不往外抛了
            admin = adminSqlMapper.selectAdminById(adminId);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return admin;
    }

    /**
     * 验证系统管理员的用户名和密码
     * @param admin
     * @return
     */
    @Override
    public boolean checkAdminLegitimate(Admin admin) {
        SqlSession sqlSession = null;
        boolean flag = false;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            adminSqlMapper = sqlSession.getMapper(AdminSqlMapper.class);
            // 在service中异常自己处理而不往外抛了
            int result = adminSqlMapper.selectAdminByUserNameForCheck(admin);
            if (result == 1){
                flag = true;
            }
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return flag;
    }

    /**
     * 通过管理员用户名查询管理员信息
     * @param adminUserName
     * @return
     */
    @Override
    public Admin getAdminAccountByUserName(String adminUserName) {
        SqlSession sqlSession = null;
        Admin admin;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            adminSqlMapper = sqlSession.getMapper(AdminSqlMapper.class);
            // 在service中异常自己处理而不往外抛了
            admin = adminSqlMapper.selectAdminByUserName(adminUserName);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return admin;
    }
}
