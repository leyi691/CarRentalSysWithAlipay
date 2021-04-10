package cn.edu.nbut.jerry.mapper;

import cn.edu.nbut.jerry.POJO.User;
import java.util.List;

public interface UserSqlMapper {
    public List<User>       selectAll                               ();
    public User             selectUserById                          (Integer userId);
    public User             selectUserByName                        (String username);
    public Integer          selectUserByUserNameForCheck            (User user);
    public List<User>       searchUserLikeNicknameForSysAdmin       (String nickname);
    public Integer          insertUserById                          (User user);
    public Integer          updateUserById                          (User user);
    public Integer          updateUserByUsername                    (User user);
    public Integer          updateUserByUsernameWithoutPassword     (User user);
    public Integer          deleteUserById                          (Integer userId);
}
