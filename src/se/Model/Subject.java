/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/14
 */

package se.Model;

public class Subject {
    private String courseID;
    private String courseName;
    private String courseKind;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseKind() {
        return courseKind;
    }

    public void setCourseKind(String courseKind) {
        this.courseKind = courseKind;
    }
}
