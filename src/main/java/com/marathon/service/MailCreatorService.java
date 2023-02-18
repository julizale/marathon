package com.marathon.service;

import com.marathon.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    public String buildDailyRunnersCountEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your races and runners data.");
        functionality.add("Check history weather for event day.");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("marathon_url", "http://localhost:8090/");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye_message", "Your Marathon Application");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/daily-mail", context);
    }

}