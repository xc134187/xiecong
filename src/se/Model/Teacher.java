/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/12
 */

package se.Model;

public class Teacher implements UserCtl {
    String userId;

    public Teacher(){}

    public Teacher(String userid){
        this.userId = userid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void GetUserInfo() {

    }

    @Override
    public boolean UpdateUserInfo() {
        return false;
    }

    @Override
    public boolean ResetPwd() {
        return false;
    }
}
