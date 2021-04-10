package cn.edu.nbut.jerry.service.impl;

import cn.edu.nbut.jerry.POJO.User;
import cn.edu.nbut.jerry.Utils.MyBatisUtils;
import cn.edu.nbut.jerry.mapper.UserSqlMapper;
import cn.edu.nbut.jerry.service.UserService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserSqlMapper userSqlMapper = null;
    private SqlSession sqlSession = null;
    @Override
    public int checkUserLegitimate(User user) {
        int result = -1;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            userSqlMapper = sqlSession.getMapper(UserSqlMapper.class);
            // 在service中异常自己处理而不往外抛了
            result = userSqlMapper.selectUserByUserNameForCheck(user);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }

    @Override
    public int insertUser(User user) {
        int result = -1;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            userSqlMapper = sqlSession.getMapper(UserSqlMapper.class);
            result = userSqlMapper.insertUserById(user);
            sqlSession.commit();
            if (result > 0){
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败，数据库位置");
            }
        } catch (Exception ex){
            sqlSession.rollback();
            System.out.println("添加失败：" + ex);
            ex.getCause();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public int updateUserById(User user) {
        int result = -1;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            userSqlMapper = sqlSession.getMapper(UserSqlMapper.class);
            result = userSqlMapper.updateUserById(user);
            sqlSession.commit();
            if (result > 0){
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败，数据库位置");
            }
        } catch (Exception ex){
            sqlSession.rollback();
            System.out.println("修改失败：" + ex);
            ex.getCause();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public int updateUserByUsername(User user) {
        int result = -1;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            userSqlMapper = sqlSession.getMapper(UserSqlMapper.class);
            result = userSqlMapper.updateUserByUsername(user);
            sqlSession.commit();
            if (result > 0){
                System.out.println("修改成功 FROM UserServiceImpl.java");
            } else {
                System.out.println("修改失败，数据库位置。FROM UserServiceImpl.java");
            }
        } catch (Exception ex){
            sqlSession.rollback();
            System.out.println("修改失败： FROM UserServiceImpl.java" + ex);
            ex.getCause();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public int updateUserByUsernameWithoutPassword(User user) {
        int result = -1;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            userSqlMapper = sqlSession.getMapper(UserSqlMapper.class);
            result = userSqlMapper.updateUserByUsernameWithoutPassword(user);
            sqlSession.commit();
            if (result > 0){
                System.out.println("修改成功 FROM UserServiceImpl.java");
            } else {
                System.out.println("修改失败，数据库位置。FROM UserServiceImpl.java");
            }
        } catch (Exception ex){
            sqlSession.rollback();
            System.out.println("修改失败： FROM UserServiceImpl.java" + ex);
            ex.getCause();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public int deleteUserById(Integer userId) {
        int result = -1;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            userSqlMapper = sqlSession.getMapper(UserSqlMapper.class);
            result = userSqlMapper.deleteUserById(userId);
            sqlSession.commit();
            if (result > 0){
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } catch (Exception e){
            sqlSession.rollback();
            System.out.println("删除失败，回滚" + e);
            e.getCause();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public User getUserByName(String username) {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        UserSqlMapper mapper = sqlSession.getMapper(UserSqlMapper.class);
        User user;
        user = mapper.selectUserByName(username);
        sqlSession.close();
        return user;
    }

    @Override
    public User getUserById(Integer userId) {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        UserSqlMapper mapper = sqlSession.getMapper(UserSqlMapper.class);
        User user;
        user = mapper.selectUserById(userId);
        sqlSession.close();
        return user;
    }

    @Override
    public List<User> getAllUser() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        UserSqlMapper mapper = sqlSession.getMapper(UserSqlMapper.class);
        List<User> users;
        users = mapper.selectAll();
        sqlSession.close();
        return users;
    }

    @Override
    public List<User> searchUserLikeNicknameForSysAdmin(String nickname) {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        UserSqlMapper mapper = sqlSession.getMapper(UserSqlMapper.class);
        List<User> users;
        users = mapper.searchUserLikeNicknameForSysAdmin("%"+nickname+"%");
        sqlSession.close();
        return users;
    }
}
