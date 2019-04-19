/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/19
 */

package se.Model.Mapper;

public interface StudentMapper {
    int CheckIn(String userid);
    boolean IsTodayCheckedIn(String userid);
    int CheckOut(String userid);
}
