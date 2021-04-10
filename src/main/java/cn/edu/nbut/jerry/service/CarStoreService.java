package cn.edu.nbut.jerry.service;

import cn.edu.nbut.jerry.POJO.CarStore;

import java.util.List;

public interface CarStoreService {
    public int                  addCarStoreAccount                          (CarStore carStore);
    public int                  updateCarStoreAccountById                   (CarStore carStore);
    public int                  updateCarStoreByIdWithoutPassword           (CarStore carStore);
    public int                  updateCarStoreByUsername                    (CarStore carStore);
    public int                  updateCarStoreByUsernameWithoutPassword     (CarStore carStore);
    public int                  deleteCarStoreAccountById                   (Integer carStoreId);
    public List<CarStore>       getAllCarStoreAccount                       ();
    public CarStore             getCarStoreAccountById                      (Integer carStoreId);
    public CarStore             getCarStoreAccountByUserName                (String carStoreUserName);
    public CarStore             getCarStoreByIdForUser                      (Integer carStoreId);
    public int                  getCarIsInRentalNumber                         (Integer carStoreId);
    public List<CarStore>       searchCarStoreLikeCarStoreNameForSysAdmin   (String searchStoreName);
    public int                  checkCarStoreLegitimate                     (CarStore carStore);
}
