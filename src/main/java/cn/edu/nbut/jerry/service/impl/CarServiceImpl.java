package cn.edu.nbut.jerry.service.impl;

import cn.edu.nbut.jerry.POJO.Car;
import cn.edu.nbut.jerry.Utils.MyBatisUtils;
import cn.edu.nbut.jerry.mapper.CarSqlMapper;
import cn.edu.nbut.jerry.service.CarService;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarServiceImpl implements CarService {
    SqlSession sqlSession = null;
    @Override
    public List<Car> getAllCar() {
        sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        List<Car> cars = mapper.selectAll();
        sqlSession.close();
        return cars;
    }

    @Override
    public List<Car> getAllCarRandom() {
        sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        List<Car> cars = mapper.selectAllCarRand();
        sqlSession.close();
        return cars;
    }

    @Override
    public List<Car> getAllCarAvailableRand() {
        sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        List<Car> cars = mapper.selectAllCarAvailableRand();
        sqlSession.close();
        return cars;
    }

    @Override
    public List<Car> getAllCarsByStoreId(Integer storeId) {
        sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        List<Car> cars = mapper.selectAllByStoreId(storeId);
        sqlSession.close();
        return cars;
    }

    @Override
    public List<Car> getCarsByCarType(String carType) {
        sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        List<Car> cars = mapper.selectAllByCarTypeAvailable(carType);
        sqlSession.close();
        return cars;
    }

    @Override
    public Car getCarById(Integer id) {
        sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        Car car = mapper.selectCarById(id);
        sqlSession.close();
        return car;
    }

    @Override
    public List<Car> searchCarLikeBrand(String brand) {
        List<Car> cars = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
            cars = mapper.searchCarLikeBrand("%"+brand+"%");
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return cars;
    }

    @Override
    public List<Car> searchCarLikeBrandByStoreId(String brand, int storeId) {
        List<Car> cars = null;
        try {
            sqlSession = MyBatisUtils.createSqlSession();
            CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("searchTxt", "%" + brand + "%");
            map.put("storeId", storeId);
            cars = mapper.searchCarLikeBrandByStoreId(map);
        } finally {
            MyBatisUtils.closeAll(sqlSession);
        }
        return cars;
    }

    @Override
    public int addCarById(Car car) {
        sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        int result = -1;
        try {
            result = mapper.insertCarById(car);
            sqlSession.commit();
            if (result > 0){
                System.out.println("添加成功！");
            } else {
                System.out.println("添加失败。ERR");
            }
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("添加失败。rollback");
            System.out.println(e);
            e.getCause();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public int updateCarById(Car car) {
        sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        int result = -1;
        try {
            result = mapper.updateCarById(car);
            sqlSession.commit();
            if (result > 0){
                System.out.println("修改成功！");
            } else {
                System.out.println("修改失败。ERR");
            }
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("修改失败。rollback");
            System.out.println(e);
            e.getCause();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 通过carId修改汽车状态
     * 汽车状态： 在店内、已租出、维修中
     * @return
     */
    @Override
    public int updateCarStatusById(Car car) {
        sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        int result = -1;
        try {
            result = mapper.updateCarStatusById(car);
            sqlSession.commit();
            if (result > 0){
                System.out.println("汽车状态修改成功！");
            } else {
                System.out.println("修改失败。ERR");
            }
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("修改失败。rollback");
            System.out.println(e);
            e.getCause();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public int deleteCarById(Integer id) {
        sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        int result = -1;
        try {
            result = mapper.deleteCarById(id);
            sqlSession.commit();
            if (result > 0){
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败，车辆处于租赁中");
            }
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("删除失败。rollback");
            System.out.println(e);
            e.getCause();
        } finally {
            sqlSession.close();
        }
        return result;
    }
}
