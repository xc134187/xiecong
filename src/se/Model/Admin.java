/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/12
 */

package se.Model;

public class Admin implements UserCtl {

    String userId;

    public Admin() {
    }

    public Admin(String userId) {
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
