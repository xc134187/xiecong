package se.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import se.model.Mapper.WebConfigMapper;
import se.model.WebsiteConfig;
import se.utils.DbUtils;

@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    public static WebsiteConfig config;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("website-config.xml");
//        config = (WebsiteConfig) context.getBean("WebsiteConfig");
        DbUtils<WebConfigMapper> dbUtils = new DbUtils<>(WebConfigMapper.class);
        config = dbUtils.mapper.QueryConfig();
    }
}
