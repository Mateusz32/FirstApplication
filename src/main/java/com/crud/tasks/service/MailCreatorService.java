package com.crud.tasks.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.thymeleaf.TemplateEngine;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

}
