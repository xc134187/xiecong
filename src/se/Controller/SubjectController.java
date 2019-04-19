/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/15
 */

package se.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.Model.Mapper.SubjectMapper;
import se.Model.Subject;
import se.Model.User;
import se.utils.DbUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/Subject")
@Controller
public class SubjectController {
    @RequestMapping("search_teacher_name")
    @ResponseBody
    public List<Subject> SelectSubjectByTeacherName(@RequestParam String teacher_name){
        DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
        return (dbUtils.mapper).SelectByTeacherName(teacher_name);
    }

    @RequestMapping("search_teacher_id")
    @ResponseBody
    public List<Subject> SelectSubjectByTeacherId(@RequestParam String teacher_id){
        DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
        return (dbUtils.mapper).SelectByTeacherId(teacher_id);
    }
    @ResponseBody
    @RequestMapping("search_subject_kind")
    public List<Subject> SelectSubjectKind(@RequestParam String kind){
        DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
        return dbUtils.mapper.SelectBySubjectType(kind);
    }

    @RequestMapping
    @ResponseBody
    public List<Subject> SelectAllSubject(){
        DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
        return (dbUtils.mapper).SelectAllSub();
    }

    @RequestMapping(value = "pub_subject", method = RequestMethod.GET)
    public void NewSubject(@RequestParam String subject_name,
                           @RequestParam String subject_kind,
                           HttpSession session){
        // 检查当前用户是否为教师
        User user = (User)session.getAttribute("user");
        if(user == null)
            return;
        if(user.getRole() == 1){
            Subject subject = new Subject();
            subject.setSubjectName(subject_name);
            subject.setSubjectKind(subject_kind);
            subject.setTeacherId(user.getUserId());

            DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
            (dbUtils.mapper).PubNewSubject(subject);

            dbUtils.session.commit();
            dbUtils.session.close();
        }else {
            return;
        }
    }

    @RequestMapping("update_subject")
    public void UpdateSubject(@RequestParam Subject subject, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user!=null && user.getRole() == 1) {
            DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
            (dbUtils.mapper).UpdateSubjectInfo(subject);
        }
    }
}
