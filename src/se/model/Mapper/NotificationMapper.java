/**
 * @author: created by wwbweibo
 * @version: 1.0
 * @date: 2019/4/14
 */

package se.model.Mapper;

import se.model.Notification;

import java.util.List;

public interface NotificationMapper {
    List<Notification> selectAll();

    List<Notification> selectTop(int num);

    Notification select(int id);

    void newNotification(Notification notification);

}
