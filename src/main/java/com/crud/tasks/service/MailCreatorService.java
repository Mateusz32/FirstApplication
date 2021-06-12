package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.thymeleaf.TemplateEngine;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());

        context.setVariable("preview_message", "New card added in Trello");
        context.setVariable("goodbay_message", "Have nice day");
        context.setVariable("company_details", companyConfig.getCompanyName()+"\n"+companyConfig.getOwnerName()+" "+companyConfig.getOwnerSurname());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

}
