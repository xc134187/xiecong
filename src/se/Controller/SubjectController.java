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

@Controller
@RequestMapping("/Subject")
public class SubjectController {
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

            DbUtils dbUtils = new DbUtils();
            SubjectMapper mapper = dbUtils.session.getMapper(SubjectMapper.class);
            mapper.PubNewSubject(subject);

            dbUtils.session.commit();
            dbUtils.session.close();
        }else {
            return;
        }
    }

    @RequestMapping("search_teacher_name")
    @ResponseBody
    public List<Subject> SelectSubjectByTeacherName(@RequestParam String teacher_name){
        DbUtils dbUtils = new DbUtils();
        SubjectMapper subjectMapper = dbUtils.session.getMapper(SubjectMapper.class);
        return subjectMapper.SelectByTeacherName(teacher_name);
    }

    @RequestMapping("search_by_teacher_id")
    @ResponseBody
    public List<Subject> SelectSubjectByTeacherId(@RequestParam String teacher_id){
        DbUtils dbUtils = new DbUtils();
        SubjectMapper subjectMapper = dbUtils.session.getMapper(SubjectMapper.class);
        return subjectMapper.SelectByTeacherId(teacher_id);
    }


    @ResponseBody
    public List<Subject> SelectAllSubject(){
        DbUtils dbUtils = new DbUtils();
        SubjectMapper subjectMapper = dbUtils.session.getMapper(SubjectMapper.class);
        return   subjectMapper.SelectAllSub();
    }

    @RequestMapping("update_subject")
    public void UpdateSubject(@RequestParam Subject subject, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user!=null && user.getRole() == 1) {
            DbUtils dbUtils = new DbUtils(SubjectMapper.class);
            ((SubjectMapper)dbUtils.mapper).UpdateSubjectInfo(subject);
        }
    }
}
