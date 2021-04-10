package cn.edu.nbut.jerry.mapper;

import cn.edu.nbut.jerry.POJO.Car;

import java.util.List;
import java.util.Map;

public interface CarSqlMapper {
    public List<Car>        selectAll                           ();
    public List<Car>        selectAllCarRand                    ();
    public List<Car>        selectAllCarAvailableRand           ();
    public List<Car>        selectAllByStoreId                  (Integer id);
    public List<Car>        selectAllByCarTypeAvailable         (String carType);
    public Car              selectCarById                       (Integer id);

    public List<Car>        searchCarLikeBrand                  (String searchTxt);
    public List<Car>        searchCarLikeBrandByStoreId         (Map<String, Object> map);

    public int              insertCarById                       (Car car);
    public int              updateCarById                       (Car car);
    public int              updateCarStatusById                 (Car car);
    public int              deleteCarById                       (Integer id);
}
