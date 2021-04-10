package cn.edu.nbut.jerry.mapper;

import cn.edu.nbut.jerry.POJO.CarStore;

import java.util.List;

public interface CarStoreSqlMapper {
    public List<CarStore>   selectAll                                   ();
    public CarStore         selectCarStoreById                          (Integer id);
    public CarStore         selectCarStoreByUserName                    (String carStoreName);
    public CarStore         selectCarStoreByIdForUser                   (Integer carStoreId);
    public Integer          selectCarIsInRental                         (Integer carStoreId);
    public List<CarStore>   searchCarStoreLikeCarStoreNameForSysAdmin   (String storeName);
    public int              selectCarStoreByUsernameForCheck            (CarStore carStore);
    public int              insertCarStoreById                          (CarStore carStore);
    public int              updateCarStoreById                          (CarStore carStore);
    public int              updateCarStoreByIdWithoutPassword           (CarStore carStore);
    public int              updateCarStoreByUserName                    (CarStore carStore);
    public int              updateCarStoreByUserNameWithoutPassword     (CarStore carStore);
    public int              deleteCarStoreById                          (Integer id);
}