package cn.edu.nbut.jerry.service;

import cn.edu.nbut.jerry.POJO.Car;

import java.util.List;

public interface CarService {
    public List<Car>            getAllCar                       ();
    public List<Car>            getAllCarRandom                 ();
    public List<Car>            getAllCarAvailableRand          ();     // 倒序查询 '在店内' 的汽车
    public List<Car>            getAllCarsByStoreId             (Integer storeId);
    public List<Car>            getCarsByCarType                (String carType);
    public Car                  getCarById                      (Integer id);

    public List<Car>            searchCarLikeBrand              (String brand);
    public List<Car>            searchCarLikeBrandByStoreId     (String brand, int storeId);

    public int                  addCarById                      (Car car);
    public int                  updateCarById                   (Car car);
    public int                  updateCarStatusById             (Car car);
    public int                  deleteCarById                   (Integer id);
}