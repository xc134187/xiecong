/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/19
 */

package se.model.Mapper;

import se.model.Student;

public interface StudentMapper {
    // 签到
    int CheckIn(String userid);

    // 查看今日是否签到
    boolean IsTodayCheckedIn(String userid);

    // 签退
    int CheckOut(String userid);

    // 重置签到时间
    int CheckInReset();

    // 获取个人信息
    Student QuerySelfInfo(String userid);
}
