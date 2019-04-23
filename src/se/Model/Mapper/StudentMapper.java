/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/19
 */

package se.Model.Mapper;

import se.Model.Grade;
import se.Model.Student;

import java.util.List;

public interface StudentMapper {
    // 签到
    int CheckIn(String userid);
    // 查看今日是否签到
    boolean IsTodayCheckedIn(String userid);
    // 签退
    int CheckOut(String userid);
    // 成绩自评
    int SelfJudge(float grade, String userId, int subjectId);
    // 获取个人信息
    Student QuerySelfInfo(String userid);
    // 查询成绩
    List<Grade> QueryGrade(String userId);
}
