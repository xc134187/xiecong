/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/14
 */

package se.Model.Mapper;

import org.apache.ibatis.annotations.Param;
import se.Model.Subject;

import java.util.List;

public interface SubjectMapper {
    // 通过教师姓名查看课题
    List<Subject> SelectByTeacherName(String teacherName);
    // 通过教师ID查看课题
    List<Subject> SelectByTeacherId(String teacherId);
    // 获取所有课题
    List<Subject> SelectAllSub();
    // 通过课题名称查看课程
    List<Subject> SelectBySubjectName(String subjectName);
    // 通过课程类型查看课程
    List<Subject> SelectBySubjectType(String subjectType);
    // 通过课程ID查看课程
    Subject SelectBySubjectId(int subjectId);
    // 查询已选教师课题的学生列表
    List<Subject> SelectStudentsForTeacher(String userId);
    // 获取最大选课人数
    int SelectMaxSelectNum(int subjectId);
    // 获取当前选课人数
    int SelectCurrSelectNum(int subjectId);
    // 发布新课题
    int PubNewSubject(Subject subject);
    // 更新课题信息
    int UpdateSubjectInfo(Subject subject);
    // 学生选课
    int StudentSelectSubject(@Param(value="subject_id") int subjectId,
                             @Param(value="user_id") String userId);


}