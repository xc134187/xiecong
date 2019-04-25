/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/12
 */

package se.model;

/**
 * UserCtl interface: method for user role
 */
public interface UserCtl {
    void GetUserInfo();

    boolean UpdateUserInfo();

    boolean ResetPwd();
}
