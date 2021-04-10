package cn.edu.nbut.jerry;

import cn.edu.nbut.jerry.POJO.Admin;
import cn.edu.nbut.jerry.Utils.JudgeHelper;
import cn.edu.nbut.jerry.Utils.MyBatisUtils;
import cn.edu.nbut.jerry.mapper.AdminSqlMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class AdminSqlMapperTest {
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        AdminSqlMapper mapper = sqlSession.getMapper(AdminSqlMapper.class);
        List<Admin> admins = mapper.selectAll();
        for (Admin admin : admins){
            System.out.println(admin);
        }
        sqlSession.close();
    }
    @Test
    public void testInsertAdmin() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        AdminSqlMapper mapper = sqlSession.getMapper(AdminSqlMapper.class);
        Admin admin = new Admin();
        admin.setAdminName("lijiayu2");
        admin.setAdminUsername("lijiayu2");
        admin.setAdminPassword("123456");
        admin.setAdminPhoneNumber("12345678901");
        try {
            int result = mapper.insertAdminById(admin);
            sqlSession.commit();
            if (result > 0){
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败1");
            }
        } catch (Exception e){
            sqlSession.rollback();
            System.out.println("添加失败2");
            System.out.println(e);
            e.getCause();
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testUpdateAdmin() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        AdminSqlMapper mapper = sqlSession.getMapper(AdminSqlMapper.class);
        Admin admin = new Admin();
        admin.setAdminId(2);
        admin.setAdminName("lijiayu2");
        admin.setAdminUsername("lijiayu2");
        admin.setAdminPassword("123456");
        admin.setAdminPhoneNumber("12345678901");
        try {
            int result = mapper.updateAdminById(admin);
            sqlSession.commit();
            if (result > 0){
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败1");
            }
        } catch (Exception e){
            sqlSession.rollback();
            System.out.println("修改失败2");
            System.out.println(e);
            e.getCause();
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testDeleteAdminById() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        AdminSqlMapper mapper = sqlSession.getMapper(AdminSqlMapper.class);
        Admin admin = new Admin();
        admin.setAdminId(2);
        int result = mapper.deleteAdminById(admin.getAdminId());
        sqlSession.commit();
        if (result > 0){
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }
    @Test
    public void testSelectAdminById(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        AdminSqlMapper mapper = sqlSession.getMapper(AdminSqlMapper.class);
        Admin admin;
        admin = mapper.selectAdminById(1);
        System.out.println(admin);
        sqlSession.close();
    }
    @Test
    public void testSelectAdminByName(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        AdminSqlMapper mapper = sqlSession.getMapper(AdminSqlMapper.class);
        Admin admin;
        admin = mapper.selectAdminByUserName("lijiayu");
        System.out.println(admin);
        sqlSession.close();
    }
    @Test
    public void CheckAdminLegitimate() {
        SqlSession sqlSession = null;
        boolean flag = false;
        Admin admin = new Admin();
        admin.setAdminUsername("lijiayu");
        admin.setAdminPassword("123456");
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            AdminSqlMapper adminSqlMapper = sqlSession.getMapper(AdminSqlMapper.class);
            // 在service中异常自己处理而不往外抛了
            int result = adminSqlMapper.selectAdminByUserNameForCheck(admin);
            if (result == 1) {
                flag = true;
            }
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        System.out.println(flag);
    }

    @Test
    public void test(){
        String string = "aaa456ac啊";
        System.out.println(JudgeHelper.isChinese(string));
    }
}
