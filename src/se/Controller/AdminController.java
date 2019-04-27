/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/26
 */

package se.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import se.listener.StartupListener;
import se.model.User;
import se.model.WebsiteConfig;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/Admin")
public class AdminController {
    @RequestMapping(value = "UpdateControl", method = RequestMethod.POST)
    public void UpdateDateControl(@RequestParam String pubSubjectStartTime,
                                  @RequestParam String pubSubjectEndTime,
                                  @RequestParam String selectSubjectStartTime,
                                  @RequestParam String selectSubjectEndTime,
                                  @RequestParam String uploadResultStartTime,
                                  @RequestParam String uploadResultEndTime,
                                  @RequestParam String pubGradeStartTime,
                                  @RequestParam String pubGradeEndTime,
                                  @RequestParam String checkInTime,
                                  @RequestParam String checkOutTime,
                                  @RequestParam String checkInResetTime,
                                  HttpServletResponse response,
                                  HttpSession session) throws ParseException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        User user = (User) session.getAttribute("user");
        if (user != null && user.getRole() == 3) {
            StartupListener.config.setPubSubjectStartTime(pubSubjectStartTime);
            StartupListener.config.setPubSubjectEndTime(pubSubjectEndTime);
            StartupListener.config.setSelectSubjectStartTime(selectSubjectStartTime);
            StartupListener.config.setSelectSubjectEndTime(selectSubjectEndTime);
            StartupListener.config.setUploadResultStartTime(uploadResultStartTime);
            StartupListener.config.setUploadResultEndTime(uploadResultEndTime);
            StartupListener.config.setPubGradeStartTime(pubGradeStartTime);
            StartupListener.config.setPubGradeEndTime(pubGradeEndTime);
            StartupListener.config.setCheckInTime(checkInTime);
            StartupListener.config.setCheckOutTime(checkOutTime);
            StartupListener.config.setCheckInResetTime(checkInResetTime);
            StartupListener.config.UpdateConfig();

            StartupListener.OnResetTimeChanged(StartupListener.config.getCheckInResetTime());
            StartupListener.OnUploadResultEndTimeChanged(StartupListener.config.getUploadResultEndTime());

            response.getWriter().write("<script>alert('保存成功');window.location.href='/FormMain'</script>");
        } else {
            response.getWriter().write("<script>alert('保存失败，仅限管理员使用！');window.location.href='/FormMain'</script>");
        }
    }

    @ResponseBody
    @RequestMapping("controlTime")
    public WebsiteConfig ControlTime() {
        return StartupListener.config;
    }
}
