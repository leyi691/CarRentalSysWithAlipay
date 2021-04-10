package cn.edu.nbut.jerry;

import cn.edu.nbut.jerry.POJO.CarStore;
import cn.edu.nbut.jerry.Utils.MyBatisUtils;
import cn.edu.nbut.jerry.mapper.CarStoreSqlMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarStoreSqlMapperTest {
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        CarStoreSqlMapper mapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        List<CarStore> carStores = mapper.selectAll();
        for (CarStore carStore : carStores){
            System.out.println(carStore);
        }
        sqlSession.close();
    }
    @Test
    public void testSelectByUsername(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        CarStoreSqlMapper mapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        CarStore carStore = mapper.selectCarStoreByUserName("redbull");

        System.out.println(carStore);

        sqlSession.close();
    }
    @Test
    public void testInsertCarStore(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        CarStoreSqlMapper mapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        CarStore carStore = new CarStore();
        carStore.setStoreAdminUsername("audi");
        carStore.setStorePassword("123456");
        carStore.setStoreName("Audi 奥迪");
        carStore.setStoreImagePath1("./ssd");
        carStore.setStoreContactNumber("888999");
        carStore.setStoreArea("NingBo");
        carStore.setStoreAddress("余姚市xx路xx号");
        carStore.setStoreStatus("Open");
        try {
            int result = mapper.insertCarStoreById(carStore);
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
    public void testUpdateByStoreId(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        CarStoreSqlMapper mapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        CarStore carStore = new CarStore();
        carStore.setStoreId(2);
        carStore.setStoreAdminUsername("audi");
        carStore.setStorePassword("123456");
        carStore.setStoreName("Audi 奥迪");
        carStore.setStoreImagePath1("./");
        carStore.setStoreContactNumber("888123999");
        carStore.setStoreArea("NingBo");
        carStore.setStoreAddress("余姚市xx路xx号");
        carStore.setStoreStatus("Open");
        try {
            int result = mapper.updateCarStoreById(carStore);
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
    public void testUpdateByUsername(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        CarStoreSqlMapper mapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        CarStore carStore = new CarStore();
        carStore.setStoreId(2);
        carStore.setStoreAdminUsername("audi");
        carStore.setStorePassword("123456");
        carStore.setStoreName("Audi 奥迪");
        carStore.setStoreImagePath1("./");
        carStore.setStoreContactNumber("888123999");
        carStore.setStoreArea("NingBo");
        carStore.setStoreAddress("余姚市xx路xx号");
        carStore.setStoreStatus("Open");
        try {
            int result = mapper.updateCarStoreByUserName(carStore);
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
    public void testUpdateByUsernameWithoutPassword(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        CarStoreSqlMapper mapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        CarStore carStore = new CarStore();
        carStore.setStoreId(2);
        carStore.setStoreAdminUsername("audi");
        carStore.setStoreName("Audi 奥迪");
        carStore.setStoreImagePath1("./");
        carStore.setStoreContactNumber("888999");
        carStore.setStoreArea("NingBo");
        carStore.setStoreAddress("余姚市xx路xx号");
        carStore.setStoreStatus("Open");
        try {
            int result = mapper.updateCarStoreByUserNameWithoutPassword(carStore);
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
    public void testDeleteStoreById(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        CarStoreSqlMapper mapper = sqlSession.getMapper(CarStoreSqlMapper.class);
        Integer id = 2;
        try {
            int result = mapper.deleteCarStoreById(id);
            sqlSession.commit();
            if (result > 0){
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败,无记录");
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
    /**
     * 验证门店管理员的用户名和密码
     * @param
     * @return
     */
    @Test
    public void testCheckCarStoreLegitimate() {
        SqlSession sqlSession = null;
        boolean flag = false;
        CarStore carStore = new CarStore();
        carStore.setStoreAdminUsername("audi");
        carStore.setStorePassword("123456");
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            CarStoreSqlMapper carStoreSqlMapper = sqlSession.getMapper(CarStoreSqlMapper.class);
            // 在service中异常自己处理而不往外抛了
            int result = carStoreSqlMapper.selectCarStoreByUsernameForCheck(carStore);
            if (result == 1){
                flag = true;
            }
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        System.out.println(flag);
    }
}
