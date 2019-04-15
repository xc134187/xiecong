/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/12
 */

package se.Model;

import se.Model.Mapper.UserMapper;
import se.utils.DbUtils;

public class User {
    private String userId;
    private String userName;
    private String userPassword;
    private int role;
    private UserCtl roleInfo;


    // getter and setter
    public UserCtl getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(UserCtl roleInfo) {
        this.roleInfo = roleInfo;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return userPassword;
    }

    public void setPwd(String pwd) {
        this.userPassword = pwd;
    }

    // public function
    public Boolean Login() {
        DbUtils utils = new DbUtils(UserMapper.class);
        User user = ((UserMapper)utils.mapper).Login(userId);
        // fixme: 返回的 role 值比在数据库中大1
        boolean success = (user != null && user.userPassword.equals(userPassword));
        if (success) {
            this.userId = user.userId;
            this.userName = user.userName;
            this.role = user.role;
            this.userPassword = user.userPassword;
            // if login success, get user's information
            getUserInfo();
        }
        return success;
    }

    //private function
    private void getUserInfo() {
        if (role ==2) {
            this.roleInfo = new Student();
        } else if (role == 1) {
            this.roleInfo = new Teacher();
        } else {
            this.roleInfo = new Admin();
        }
        roleInfo.GetUserInfo();
    }
}

