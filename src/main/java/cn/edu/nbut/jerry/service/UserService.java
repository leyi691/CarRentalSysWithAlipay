package cn.edu.nbut.jerry.service;

import cn.edu.nbut.jerry.POJO.User;

import java.util.List;

public interface UserService {
    public int      checkUserLegitimate                     (User user);
    public int      insertUser                              (User user);
    public int      updateUserById                          (User user);
    public int      updateUserByUsername                    (User user);
    public int      updateUserByUsernameWithoutPassword     (User user);
    public int      deleteUserById                          (Integer userid);
    public User     getUserByName                           (String username);
    public User     getUserById                             (Integer userId);
    public List<User> getAllUser                            ();
    public List<User> searchUserLikeNicknameForSysAdmin     (String nickname);
}
