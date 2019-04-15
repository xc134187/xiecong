/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/14
 */

package se.Model.Mapper;

import se.Model.Subject;

import java.util.List;

public interface SubjectMapper {
    List<Subject> SelectByTeacherName(String teacherName);
    List<Subject> SelectByTeacherId(String teacherId);
    List<Subject> SelectAllSub();
    List<Subject> SelectBySubjectName(String subjectName);
    List<Subject> SelectBySubjectType(String subjectType);
    Subject SelectBySubjectId(String subjectId);
    int PubNewSubject(Subject subject);
    int UpdateSubjectInfo(Subject subject);
}