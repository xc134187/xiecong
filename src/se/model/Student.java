/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/12
 */

package se.model;

import se.model.Mapper.StudentMapper;
import se.utils.DbUtils;

public class Student implements UserCtl {
    String tclass;
    String sex;
    String userId;

    // constructor
    public Student() {
    }

    public Student(String userId) {
        this.userId = userId;
        GetUserInfo();
    }

    // getter and setter


    public String getTclass() {
        return tclass;
    }

    public void setTclass(String tclass) {
        this.tclass = tclass;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // public method
    public boolean CheckIn() {
        return false;
    }

    public boolean CheckOut() {
        return false;
    }

    public void GetGrade() {

    }

    @Override
    public void GetUserInfo() {
        DbUtils<StudentMapper> dbUtils = new DbUtils<>(StudentMapper.class);
        Student student = dbUtils.mapper.QuerySelfInfo(userId);
        this.tclass = student.tclass;
        this.sex = student.sex;
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
