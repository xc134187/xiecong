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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import se.Model.Mapper.SubjectMapper;
import se.Model.Subject;
import se.Model.User;
import se.utils.DbUtils;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RequestMapping("/Subject")
@Controller
public class SubjectController {
    @RequestMapping("search_teacher_name")
    @ResponseBody
    public List<Subject> SelectSubjectByTeacherName(@RequestParam String teacher_name) {
        DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
        return (dbUtils.mapper).SelectByTeacherName(teacher_name);
    }

    @RequestMapping("search_teacher_id")
    @ResponseBody
    public List<Subject> SelectSubjectByTeacherId(@RequestParam String teacher_id) {
        DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
        return (dbUtils.mapper).SelectByTeacherId(teacher_id);
    }

    @ResponseBody
    @RequestMapping("search_subject_kind")
    public List<Subject> SelectSubjectKind(@RequestParam String kind) {
        DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
        return dbUtils.mapper.SelectBySubjectType(kind);
    }

    @RequestMapping
    @ResponseBody
    public List<Subject> SelectAllSubject() {
        DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
        return (dbUtils.mapper).SelectAllSub();
    }

    @RequestMapping(value = "pub_subject", method = RequestMethod.POST)
    public String NewSubject(@RequestParam String subject_name,
                             @RequestParam String subject_kind,
                             @RequestParam int max_select_num,
                             ServletResponse response,
                             HttpSession session) {

        String retLocation = "redirect:/Login";
        try {
            // 检查当前用户是否为教师
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.getWriter().write("<script>alert('你还未登录，请登录后重试')</script>");
                retLocation = "redirect:/Login";
            }
            if (user.getRole() == 1) {
                Subject subject = new Subject();
                subject.setSubjectName(subject_name);
                subject.setSubjectKind(subject_kind);
                subject.setMaxSelectNum(max_select_num);
                subject.setUserId(user.getUserId());

                DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
                (dbUtils.mapper).PubNewSubject(subject);

                dbUtils.session.commit();
                dbUtils.session.close();

                response.getWriter().write("<script>alert('提交成功！')</script>");
                retLocation = "redirect:/FormMain";
            } else {
                response.getWriter().write("<script>alert('只有教师才可以提交课题！')</script>");
                retLocation = "redirect:/FormMain";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retLocation;
    }

    @RequestMapping("SelectStudentsForTeacher")
    @ResponseBody
    public List<Subject> SelectStudentsForTeacher(@RequestParam String userId) {
        DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
        return (dbUtils.mapper).SelectStudentsForTeacher(userId);
    }

    @RequestMapping("selectStudentSubject")
    @ResponseBody
    public Subject SelectStudentSubject(@RequestParam String userId) {
        DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
        return (dbUtils.mapper).SelectStudentSubject(userId);
    }

    /**
     * 更新课程信息
     *
     * @param subject `Subject` 项目类实体
     * @param session `HttpSession`
     */
    @RequestMapping("update_subject")
    public void UpdateSubject(@RequestParam Subject subject, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null && user.getRole() == 1) {
            DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
            (dbUtils.mapper).UpdateSubjectInfo(subject);
        }
    }

    /**
     * 学生选课接口
     *
     * @param subjectId `String` 项目id
     * @param session   `HttpSession`
     */
    @RequestMapping("SelectSubject")
    @ResponseBody
    public void SelectSubject(@RequestParam int subjectId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != 2) {
            return;
        } else {
            DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);
            int max_select = dbUtils.mapper.SelectMaxSelectNum(subjectId);
            int curr_select = dbUtils.mapper.SelectCurrSelectNum(subjectId);
            if (curr_select <= max_select) {
                dbUtils.mapper.StudentSelectSubject(subjectId, user.getUserId());
                dbUtils.session.commit();
                dbUtils.session.close();
            }
        }
    }

    // 成果上传
    @RequestMapping(value = "Upload", method = RequestMethod.POST)
    public void Upload(@RequestParam MultipartFile file,
                       HttpServletRequest request,
                       HttpSession session) {
        if (!file.isEmpty()) {
            String contextPath = request.getContextPath();//"/SpringMvcFileUpload"
            String servletPath = request.getServletPath();//"/gotoAction"
            String scheme = request.getScheme();//"http"

            String storePath = request.getSession().getServletContext().getRealPath("/files");//存放我们上传的文件路径
            String fileName = ((User) session.getAttribute("user")).getUserId() + ".zip";

            File filepath = new File(storePath, fileName);
            if (!filepath.getParentFile().exists()) {

                filepath.getParentFile().mkdirs();//如果目录不存在，创建目录

            }
            try {
                file.transferTo(new File(storePath + File.separator + fileName));//把文件写入目标文件地址
                String url = "/Download?filename=" + fileName;
                DbUtils<SubjectMapper> dbUtils = new DbUtils<>(SubjectMapper.class);

                dbUtils.mapper.UploadResult(url, ((User) session.getAttribute("user")).getUserId());
                dbUtils.session.commit();
                dbUtils.session.close();

            } catch (Exception e) {

                e.printStackTrace();
                return;
            }

        }
    }
}
