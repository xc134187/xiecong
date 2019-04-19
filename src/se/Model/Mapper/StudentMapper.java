/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/19
 */

package se.Model.Mapper;

import se.Model.Grade;

import java.util.List;

public interface StudentMapper {
    int CheckIn(String userid);

    boolean IsTodayCheckedIn(String userid);

    int CheckOut(String userid);

    int SelfJudge(float grade, String userId, int subjectId);

    List<Grade> QueryGrade(String userId);
}
