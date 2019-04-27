package se.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import se.model.Mapper.WebConfigMapper;
import se.model.WebsiteConfig;
import se.utils.CalcGradeNormalThread;
import se.utils.CheckInResetThread;
import se.utils.DbUtils;

import java.util.Date;

@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    public static WebsiteConfig config;
    private static CheckInResetThread cthread;
    private static CalcGradeNormalThread gthread;

    public static void OnResetTimeChanged(Date ResetTime) {
        if (cthread != null && cthread.isAlive()) {
            cthread.interrupt();
        }
        cthread = new CheckInResetThread(ResetTime, "CheckInResetThread");
        cthread.start();
    }

    public static void OnUploadResultEndTimeChanged(Date time) {
        if (gthread != null && gthread.isAlive()) {
            gthread.interrupt();
        }
        gthread = new CalcGradeNormalThread(time, "CheckInResetThread");
        gthread.start();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        DbUtils<WebConfigMapper> dbUtils = new DbUtils<>(WebConfigMapper.class);
        config = dbUtils.mapper.QueryConfig();

        OnResetTimeChanged(config.getCheckInResetTime());
        OnUploadResultEndTimeChanged(config.getUploadResultEndTime());
    }
}
