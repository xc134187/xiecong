/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/13
 */

package se.model.Mapper;

import se.model.User;

import java.util.List;

public interface UserMapper {
    User Login(String userID);

    List<User> QueryAllTeacher();
}
