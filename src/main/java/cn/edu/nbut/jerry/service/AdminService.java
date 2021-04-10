package cn.edu.nbut.jerry.service;

import cn.edu.nbut.jerry.POJO.Admin;

import java.util.List;

public interface AdminService {
    public List<Admin>      getAll_AdminAccount                 ();
    public Admin            getAdminAccountById                 (Integer adminId);
    public boolean          checkAdminLegitimate                (Admin admin);
    public Admin            getAdminAccountByUserName           (String adminUserName);
    public int              addAdminAccount                     (Admin admin);
    public int              updateAdminAccountById              (Admin admin);
    public int              updateAdminByIdWithoutPassword      (Admin admin);
    public int              deleteAdminAccountById              (Integer adminId);
}
