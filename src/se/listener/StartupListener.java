package se.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import se.model.WebsiteConfig;

@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
    public static WebsiteConfig config;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext context = new ClassPathXmlApplicationContext("website-config.xml");
        config = (WebsiteConfig) context.getBean("WebsiteConfig");
    }
}
