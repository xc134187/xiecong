/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/12
 */

package se.Model;

public enum Role {
    Teacher(1),
    Student(2),
    Admin(3);

    private int value;

    Role(int value) {
        this.value = value;
    }

    public static Role valueOf(int value) {
        switch (value) {
            case 1:
                return Teacher;
            case 2:
                return Student;
            case 3:
                return Admin;
            default:
                return null;
        }
    }

    public int getValue() {
        return value;
    }
}
