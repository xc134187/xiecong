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

import java.util.HashMap;
import java.util.Map;

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
    Map<String, String> TopNotify(@RequestParam int top) {
        //todo: select top {top} notify from database and return to client
        Map<String, String> result = new HashMap<String, String>();
        result.put("title", "test");
        result.put("url", "rest");
        return result;
    }

    /**
     * to create a new notification, only for admin
     *
     * @param Title title
     * @param text  context
     * @return if success redirect to /Notify, if false return null
     */
    @RequestMapping(value = "/Notify/New", method = RequestMethod.POST)
    public String New(@RequestParam String Title, @RequestParam String text) {
        //todo: check the current user is admin
        //todo: write to database
        return null;
    }
}
