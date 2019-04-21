/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/13
 */

package se.Model.Mapper;

import se.Model.User;

import java.util.List;

public interface UserMapper {
    User Login(String userID);
    List<User> QueryAllTeacher();
}
