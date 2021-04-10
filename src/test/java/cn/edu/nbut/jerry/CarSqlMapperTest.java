package cn.edu.nbut.jerry;

import cn.edu.nbut.jerry.POJO.Car;
import cn.edu.nbut.jerry.Utils.MyBatisUtils;
import cn.edu.nbut.jerry.mapper.CarSqlMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CarSqlMapperTest {
    @Test
    public void testInsertCar() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        Date dateWas;
        Calendar calendar = Calendar.getInstance();
        Calendar calendarWas = Calendar.getInstance();
        calendarWas.set(2011, 1, 2);
        Integer age = calendar.get(Calendar.YEAR) - calendarWas.get(Calendar.YEAR);
//        System.out.println("age = " + age);
        dateWas = calendarWas.getTime();
        Car car = new Car();
        car.setLicensePlateNumber("浙B 188AB");
        car.setBrand("BMW");
        car.setBrandNumber("750Li");
        car.setRentalPrice("400");
        car.setDisplacement("3.5T");
        car.setCars(3);
        car.setSeats(5);
        car.setEngineNumber("456789123");
        car.setFrameNumber("12378456");
        car.setCarStatus("已启用");
        car.setPurchaseDate(dateWas);
        car.setCarType("小轿车");
        car.setStoreId(1);
        try {
            int result = mapper.insertCarById(car);
            sqlSession.commit();
            if (result > 0){
                System.out.println("添加成功！");
            } else {
                System.out.println("添加失败。");
            }
        } catch (Exception e){
            sqlSession.rollback();
            System.out.println("添加失败。");
            System.out.println(e);
            e.getCause();
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testSelectById() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        Car car = mapper.selectCarById(1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(car.getPurchaseDate());
        System.out.println(date);
        sqlSession.close();
    }
    @Test
    public void testUpdateCar() {
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        Date dateWas;
        Calendar calendar = Calendar.getInstance();
        Calendar calendarWas = Calendar.getInstance();
        calendarWas.set(2011, 1, 2);
        Integer age = calendar.get(Calendar.YEAR) - calendarWas.get(Calendar.YEAR);
//        System.out.println("age = " + age);
        dateWas = calendarWas.getTime();
        Car car = new Car();
        car.setCarId(2);
        car.setBrand("Red Bull");
        car.setPurchaseDate(dateWas);
        car.setBrand("Formula 1");
        car.setCarPicture("../resources");
        car.setCarType("SuperSportCar");
        car.setRentalPrice("100000000");
        car.setCarType("F1");
        try {
            int result = mapper.updateCarById(car);
            sqlSession.commit();
            if (result > 0){
                System.out.println("修改成功！");
            } else {
                System.out.println("修改失败。");
            }
        } catch (Exception e){
            sqlSession.rollback();
            System.out.println("修改失败。");
            System.out.println(e);
            e.getCause();
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testDeleteCar(){
        SqlSession sqlSession = MyBatisUtils.createSqlSession();
        CarSqlMapper mapper = sqlSession.getMapper(CarSqlMapper.class);
        Car car = new Car();
        car.setCarId(2);
        try {
            int result = mapper.deleteCarById(car.getCarId());
            sqlSession.commit();
            if (result > 0){
                System.out.println("删除成功");
            }
        } catch (Exception e){
            sqlSession.rollback();
            System.out.println(e);
        } finally {
            sqlSession.close();
        }
    }
}
