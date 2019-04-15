/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/15
 */

package se.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import se.Model.Subject;
import se.Model.User;

import javax.servlet.http.HttpSession;

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
            subject.setTeacherName(user.getUserId());
        }else {
            return;
        }
    }
}
