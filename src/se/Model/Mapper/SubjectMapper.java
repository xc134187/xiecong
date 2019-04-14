/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/14
 */

package se.Model.Mapper;

import se.Model.Subject;

import java.util.List;

public interface SubjectMapper {
    List<Subject> SelectByTeacher(String teacherName);
    Subject SelectBySubjectId(String SubjectId);
    int PubNewSubject(Subject subject);
}
