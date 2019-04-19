/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/19
 */

package se.Model;

public class Grade {
    float grade_normal, grade_final, grade_selfjudge;
    String user_id, user_name, subject_id, subject_name;

    public float getGrade_normal() {
        return grade_normal;
    }

    public void setGrade_normal(float grade_normal) {
        this.grade_normal = grade_normal;
    }

    public float getGrade_final() {
        return grade_final;
    }

    public void setGrade_final(float grade_final) {
        this.grade_final = grade_final;
    }

    public float getGrade_selfjudge() {
        return grade_selfjudge;
    }

    public void setGrade_selfjudge(float grade_selfjudge) {
        this.grade_selfjudge = grade_selfjudge;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
}
