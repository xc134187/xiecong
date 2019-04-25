/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/12
 */

package se.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import se.model.Mapper.NotificationMapper;
import se.model.Notification;
import se.model.User;
import se.utils.DbUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * this Controller contains the methods about notifications
 */
@Controller
public class NotifyController {
    /**
     * a page to show all notification sort by time
     *
     * @return page
     */
    @RequestMapping("/Notify")
    public String AllNotify() {
        DbUtils<NotificationMapper> dbUtils = new DbUtils<>(NotificationMapper.class);
        List<Notification> notifications = (dbUtils.mapper).selectAll();
        return null;
    }

    /**
     * return the top {top} notification in system
     *
     * @param top number to return
     * @return JSON string
     */
    @RequestMapping("/Notify/Top")
    public @ResponseBody
    List<Notification> TopNotify(@RequestParam int top, HttpSession session) {
        DbUtils<NotificationMapper> dbUtils = new DbUtils<>(NotificationMapper.class);
        return (dbUtils.mapper).selectTop(top);
    }

    /**
     * to create a new notification, only for admin
     *
     * @param title title
     * @param text  context
     * @return if success redirect to /Notify, if false return null
     */
    @RequestMapping(value = "/Notify/New", method = RequestMethod.POST)
    public void New(@RequestParam String title,
                    @RequestParam String text,
                    @RequestParam MultipartFile file,
                    HttpServletRequest request,
                    HttpServletResponse response,
                    HttpSession session) throws IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        //todo: check the current user is admin
        User user = (User) session.getAttribute("user");
        if (user.getRole() == 3) {
            String url = null;
            // 文件不为空，保存文件并获取下载的url
            if (!file.isEmpty()) {
                String storePath = request.getSession().getServletContext().getRealPath("/files");//存放我们上传的文件路径
                String fileName = title + "-" + file.getOriginalFilename();

                File filepath = new File(storePath, fileName);
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();//如果目录不存在，创建目录
                }
                try {
                    file.transferTo(new File(storePath + File.separator + fileName));//把文件写入目标文件地址
                    url = "/Download?filename=" + fileName;

                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().write("<script>alert('文件保存失败');window.location.href='/FormMain';</script>");
                }
            }
            // 写入通知
            Date time = new Date();
            Notification notification = new Notification();
            notification.setTime(time);
            notification.setTitle(title);
            String context = (url == null) ? text : text + "<br />附件<a href='" + url + "'></a>";
            notification.setContext(context);

            DbUtils<NotificationMapper> dbUtils = new DbUtils<>(NotificationMapper.class);
            (dbUtils.mapper).newNotification(notification);

            dbUtils.session.commit();
            dbUtils.session.close();

        }
        response.getWriter().write("<script>alert('通知发布成功！！！');window.location.href='/FormMain';</script>");
    }
}
