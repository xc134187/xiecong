/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/12
 */

package se.Controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.model.User;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String Root() {
        return "redirect:/Login";
    }

    /**
     * Main Form
     *
     * @param model
     * @return
     */
    @RequestMapping("/FormMain")
    public String FormMain(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getRole() == 1) {
            model.addAttribute("Title", "教师管理页面");
            return "teacher";
        } else if (user.getRole() == 2) {
            model.addAttribute("Title", "学生管理页面");
            return "student";
        } else if (user.getRole() == 3) {
            model.addAttribute("Title", "系统管理页面");
            return "admin";
        }
        return null;
    }

    @RequestMapping("/Download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam String filename) {
        try {
            String storeDir = request.getSession().getServletContext().getRealPath("/files");
            File file = new File(storeDir, filename);
            HttpHeaders headers = new HttpHeaders();
            String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");//设置编码
            headers.setContentDispositionFormData("attachment", downloadFileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Login path
     *
     * @param model
     * @return
     */
    @RequestMapping("/Login")
    public String Login(Model model) {
        model.addAttribute("Title", "Login Page");
        return "login";
    }

    @RequestMapping("/Logout")
    public String Logout(HttpSession session) {
        session.invalidate();
        return "redirect:Login";
    }
}
