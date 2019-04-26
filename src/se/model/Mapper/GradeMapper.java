/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/26
 */

package se.model.Mapper;

import se.model.Grade;
import se.model.GradeList;

import java.util.List;

public interface GradeMapper {
    // 查询成绩
    List<Grade> TeacherQueryGrade(String userId);
    int UpdateStudentGrade(List<Grade> gradeList);
}
