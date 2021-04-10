package cn.edu.nbut.jerry.service.impl;

import cn.edu.nbut.jerry.POJO.CarStore;
import cn.edu.nbut.jerry.Utils.MyBatisUtils;
import cn.edu.nbut.jerry.mapper.CarStoreSqlMapper;
import cn.edu.nbut.jerry.service.CarStoreService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CarStoreServiceImpl implements CarStoreService {
    private CarStoreSqlMapper carStoreSqlMapper = null;

    @Override
    public int addCarStoreAccount(CarStore carStore) {
        int result = -1;
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        carStoreSqlMapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        try {
            result = carStoreSqlMapper.insertCarStoreById(carStore);
            sqlSession.commit();
            if (result > 0){
                System.out.println("添加成功, FORM CarStoreService");
            } else {
                System.out.println("添加失败, FORM CarStoreService");
            }
        } catch (Exception e){
            sqlSession.rollback();
            System.out.println("添加失败,回滚 FORM CarStoreService");
            System.out.println(e);
            e.getCause();
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }

    @Override
    public int updateCarStoreAccountById(CarStore carStore) {
        int result = -1;
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        carStoreSqlMapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        try {
            result = carStoreSqlMapper.updateCarStoreById(carStore);
            sqlSession.commit();
            if (result > 0){
                System.out.println("修改成功, FORM CarStoreService");
            } else {
                System.out.println("修改失败, FORM CarStoreService");
            }
        } catch (Exception e){
            sqlSession.rollback();
            System.out.println("修改失败,回滚 FORM CarStoreService");
            System.out.println(e);
            e.getCause();
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }

    @Override
    public int updateCarStoreByIdWithoutPassword(CarStore carStore) {
        int result = -1;
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        carStoreSqlMapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        try {
            result = carStoreSqlMapper.updateCarStoreByIdWithoutPassword(carStore);
            sqlSession.commit();
            if (result > 0){
                System.out.println("修改成功, FORM CarStoreService");
            } else {
                System.out.println("修改失败, FORM CarStoreService");
            }
        } catch (Exception e){
            sqlSession.rollback();
            System.out.println("修改失败,回滚 FORM CarStoreService");
            System.out.println(e);
            e.getCause();
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }

    @Override
    public int updateCarStoreByUsername(CarStore carStore) {
        int result = -1;
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        carStoreSqlMapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        try {
            result = carStoreSqlMapper.updateCarStoreByUserName(carStore);
            sqlSession.commit();
            if (result > 0){
                System.out.println("修改成功, FORM CarStoreService");
            } else {
                System.out.println("修改失败, FORM CarStoreService");
            }
        } catch (Exception e){
            sqlSession.rollback();
            System.out.println("修改失败,回滚 FORM CarStoreService");
            System.out.println(e);
            e.getCause();
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }

    @Override
    public int updateCarStoreByUsernameWithoutPassword(CarStore carStore) {
        int result = -1;
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        carStoreSqlMapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        try {
            result = carStoreSqlMapper.updateCarStoreByUserNameWithoutPassword(carStore);
            sqlSession.commit();
            if (result > 0){
                System.out.println("修改成功, FORM CarStoreService");
            } else {
                System.out.println("修改失败, FORM CarStoreService");
            }
        } catch (Exception e){
            sqlSession.rollback();
            System.out.println("修改失败,回滚 FORM CarStoreService");
            System.out.println(e);
            e.getCause();
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }

    @Override
    public int deleteCarStoreAccountById(Integer carStoreId) {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        CarStoreSqlMapper mapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        int result = -1;
        try {
            if (getCarIsInRentalNumber(carStoreId) > 0) {
                System.out.println("删除失败，还有车辆处于租赁状态");
                return -1;
            } else {
                result = mapper.deleteCarStoreById(carStoreId);
                sqlSession.commit();
                if (result > 0) {
                    System.out.println("删除成功 FORM CarStoreService");
                } else {
                    System.out.println("删除失败，不存在记录");
                }
            }
        } catch (Exception e){
            sqlSession.rollback();
            System.out.println("删除失败，回滚 FORM CarStoreService");
            System.out.println(e);
            e.getCause();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public List<CarStore> getAllCarStoreAccount() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        carStoreSqlMapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        List<CarStore> carStores = carStoreSqlMapper.selectAll();
        MyBatisUtils.closeAll(sqlSession);
        return carStores;
    }

    @Override
    public CarStore getCarStoreAccountById(Integer carStoreId) {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        carStoreSqlMapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        CarStore carStore = carStoreSqlMapper.selectCarStoreById(carStoreId);
        MyBatisUtils.closeAll(sqlSession);
        return carStore;
    }

    @Override
    public CarStore getCarStoreAccountByUserName(String carStoreUserName) {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        carStoreSqlMapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        CarStore carStore = carStoreSqlMapper.selectCarStoreByUserName(carStoreUserName);
        MyBatisUtils.closeAll(sqlSession);
        return carStore;
    }

    @Override
    public CarStore getCarStoreByIdForUser(Integer carStoreId) {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        carStoreSqlMapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        CarStore carStore = carStoreSqlMapper.selectCarStoreByIdForUser(carStoreId);
        MyBatisUtils.closeAll(sqlSession);
        return carStore;
    }

    @Override
    public int getCarIsInRentalNumber(Integer carStoreId) {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        carStoreSqlMapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        int count = carStoreSqlMapper.selectCarIsInRental(carStoreId);
        MyBatisUtils.closeAll(sqlSession);
        return count;
    }

    @Override
    public List<CarStore> searchCarStoreLikeCarStoreNameForSysAdmin(String storeName) {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        carStoreSqlMapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        List<CarStore> carStores = carStoreSqlMapper.searchCarStoreLikeCarStoreNameForSysAdmin("%"+storeName+"%");
        MyBatisUtils.closeAll(sqlSession);
        return carStores;
    }

    /**
     * 验证门店管理员的用户名和密码
     * @param carStore
     * @return
     */
    @Override
    public int checkCarStoreLegitimate(CarStore carStore) {
        SqlSession sqlSession = null;
        int result = -1;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            carStoreSqlMapper = sqlSession.getMapper(CarStoreSqlMapper.class);
            // 在service中异常自己处理而不往外抛了
            result =  carStoreSqlMapper.selectCarStoreByUsernameForCheck(carStore);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return result;
    }
}
