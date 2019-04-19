/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/12
 */
package se.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.Model.Mapper.StudentMapper;
import se.Model.Student;
import se.Model.User;
import se.utils.DbUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class UserController {

    /**
     * post back page for login form
     *
     * @param username username
     * @param password password
     * @return FormMain if success, Login if false
     */
    @RequestMapping(value = "/User/Login", method = RequestMethod.POST)
    public String Login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User u = new User();
        u.setUserId(username);
        u.setPwd(password);
        boolean success = u.Login();

        if (success) {
            session.setAttribute("user", u);
            return "redirect:/FormMain";
        } else {
            return "redirect:/Login";
        }
    }

    /**
     * check if userid and email addr matched
     *
     * @param userId   userid
     * @param mailAddr email address
     * @return JSON string
     */
    @RequestMapping(value = "/User/MailCheck", method = RequestMethod.GET)
    public String MailCheck(@RequestParam String userId, @RequestParam String mailAddr) {
        //todo check userId is match mailAddr
        return null;
    }

    /**
     * a page that allow user to reset pwd
     *
     * @param UserId   userid
     * @param response reponse obj
     * @return page"resetPassword"
     */
    @RequestMapping(value = "/User/ResetPassword", method = RequestMethod.GET)
    public String ResetPassword(@RequestParam String UserId, HttpServletResponse response) {
        try {
            response.getWriter().write("<script>alert('" + UserId + "')</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/User/Checkin")
    public Object Checkin(HttpSession session) {
        // get current user
        User user = (User) session.getAttribute("user");
        CheckinStatus status = new CheckinStatus();
        if(user == null){
            status.setResult(false);
            status.setMessage("用户未登录");

        }
        else if(user.getRole() != 2){
            status.setUserid(user.getUserId());
            status.setMessage("签到只允许学生进行");
            status.setResult(false);
            status.setCheckin("Checkin");
        }
        else if(user.getRole() == 2){
            DbUtils<StudentMapper> dbUtils = new DbUtils<>(StudentMapper.class);
            boolean isTodeyCheckedIn = dbUtils.mapper.IsTodayCheckedIn(user.getUserId());
            if(! isTodeyCheckedIn) {
                dbUtils.mapper.CheckIn(user.getUserId());
                dbUtils.session.commit();
                status.setUserid(user.getUserId());
                status.setResult(true);
                status.setMessage("签到成功");
                status.setCheckin("checkin");
            }else {
                status.setUserid(user.getUserId());
                status.setResult(false);
                status.setMessage("签到失败,今日已经签到！");
                status.setCheckin("checkin");
            }
        }
        return status;
    }

    @ResponseBody
    @RequestMapping("/User/Checkout")
    public Object CheckOut(HttpSession session){
        User user = (User) session.getAttribute("user");
        CheckinStatus status = new CheckinStatus();
        if(user == null){
            status.setResult(false);
            status.setMessage("用户未登录");

        }
        else if(user.getRole() != 2){
            status.setUserid(user.getUserId());
            status.setMessage("签到只允许学生进行");
            status.setResult(false);
            status.setCheckin("Checkout");
        }
        else if(user.getRole() == 2){
            DbUtils<StudentMapper> dbUtils = new DbUtils<>(StudentMapper.class);
            boolean isTodeyCheckedIn = dbUtils.mapper.IsTodayCheckedIn(user.getUserId());
            if(isTodeyCheckedIn) {
                dbUtils.mapper.CheckOut(user.getUserId());
                dbUtils.session.commit();
                status.setUserid(user.getUserId());
                status.setResult(true);
                status.setMessage("签退成功");
                status.setCheckin("Checkout");
            }else {
                status.setUserid(user.getUserId());
                status.setResult(false);
                status.setMessage("签退失败,今日未签到！");
                status.setCheckin("CheckOut");
            }
        }
        return status;
    }

    class CheckinStatus{
        String userid;
        String Checkin;
        Boolean result;
        String message;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getCheckin() {
            return Checkin;
        }

        public void setCheckin(String checkin) {
            Checkin = checkin;
        }

        public Boolean getResult() {
            return result;
        }

        public void setResult(Boolean result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
