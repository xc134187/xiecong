/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/26
 */

package se.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.model.GradeList;
import se.model.Mapper.GradeMapper;
import se.model.User;
import se.utils.DbUtils;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("Grade")
public class GradeController {
    @RequestMapping(value = "pubGrade", method = RequestMethod.POST)
    public void PubGrade(@ModelAttribute("gradeList") GradeList gradeList){
        DbUtils<GradeMapper> dbUtils = new DbUtils<>(GradeMapper.class);
        dbUtils.mapper.UpdateStudentGrade(gradeList.getGrades());
        dbUtils.session.commit();
        dbUtils.session.close();
    }

    // 通过教师ID查看成绩
    @RequestMapping("teacherQueryGrade")
    @ResponseBody
    public GradeList TeacherQueryGrade(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user.getRole() == 1){
            DbUtils<GradeMapper> dbUtils = new DbUtils<>(GradeMapper.class);
            GradeList gradeList = new GradeList();
            gradeList.setGrades(dbUtils.mapper.TeacherQueryGrade(user.getUserId()));
            return gradeList;
        }
        return null;
    }
}
