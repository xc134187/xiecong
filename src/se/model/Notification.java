/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/14
 */

package se.model;

import java.util.Date;

public class Notification {
    private int NotificationId;
    private String title;
    private String context;
    private Date time;

    public int getNotificationId() {
        return NotificationId;
    }

    public void setNotificationId(int notificationId) {
        NotificationId = notificationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
