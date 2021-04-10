package cn.edu.nbut.jerry;

import cn.edu.nbut.jerry.POJO.User;
import cn.edu.nbut.jerry.Utils.MyBatisUtils;
import cn.edu.nbut.jerry.mapper.UserSqlMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserSqlMapperTest {
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        UserSqlMapper mapper = sqlSession.getMapper(UserSqlMapper.class);
        List<User> users = mapper.selectAll();
        for (User user : users){
            System.out.println(user);
        }
        sqlSession.close();
    }
    @Test
    public void testSelectUserByName(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        UserSqlMapper mapper = sqlSession.getMapper(UserSqlMapper.class);
        User user = new User();
        user.setUsername("_user1");
        User user2;
        user2 = mapper.selectUserByName(user.getUsername());
        System.out.println(user2);
        sqlSession.close();
    }
    @Test
    public void testInsertUser(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        UserSqlMapper mapper = sqlSession.getMapper(UserSqlMapper.class);
        User user = new User();
        Date date = new Date();
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        user.setUsername("_user3");
        user.setPassword("123456");
        user.setNickname("_nickname");
        user.setSex("男");
        user.setPortrait("../sd");
        user.setRegisterDate(date);
        user.setPhoneNumber("12345678901");
        user.setIdType("身份证");
        user.setIdNumber("123448990033342233321");
        user.setProvince("浙江省");
        user.setCity("宁波");
        user.setAddress("江北区风华路201号宁波工程学院");
        try {
            int result = mapper.insertUserById(user);
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
    public void testUpdateUser() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        UserSqlMapper mapper = sqlSession.getMapper(UserSqlMapper.class);
        User user = new User();
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        user.setUserId(2);
        user.setUsername("_user_modify");
        user.setPassword("1234567");
        user.setNickname("_nickname");
        user.setSex("男");
        user.setPortrait("../sd");
        user.setRegisterDate(date);
        user.setPhoneNumber("12345678901");
        user.setIdType("身份证");
        user.setIdNumber("123448990033342233321");
        user.setProvince("浙江省");
        user.setCity("宁波");
        user.setAddress("江北区风华路201号宁波工程学院");
        try {
            int result = mapper.updateUserById(user);
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
    public void testUpdateUserByUsername() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        UserSqlMapper mapper = sqlSession.getMapper(UserSqlMapper.class);
        User user = new User();
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        user.setUsername("_user1");
        user.setPassword("1234567");
        user.setNickname("nickname");
        user.setSex("男");
        user.setPortrait("../sd");
        user.setRegisterDate(date);
        user.setPhoneNumber("12345678901");
        user.setIdType("身份证");
        user.setIdNumber("123448990033342233321");
        user.setProvince("浙江省");
        user.setCity("宁波");
        user.setAddress("江北区风华路201号宁波工程学院");
        try {
            int result = mapper.updateUserByUsername(user);
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
    public void testDeleteUser() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        UserSqlMapper mapper = sqlSession.getMapper(UserSqlMapper.class);
        Integer id = 2;
        try {
            int result = mapper.deleteUserById(id);
            sqlSession.commit();
            if (result > 0){
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败1");
            }
        } catch (Exception e){
            sqlSession.rollback();
            System.out.println("删除失败，回滚");
            System.out.println(e);
            e.getCause();
        } finally {
            sqlSession.close();
        }
    }
}
