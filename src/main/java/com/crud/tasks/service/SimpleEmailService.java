package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(final Mail mail, TemplateEmail templateEmail) {
        log.info("Starting email preparation...");
        try {
            javaMailSender.send(createMimeMessage(mail, templateEmail));
            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail,TemplateEmail templateEmail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(getTemplateEmail(mail.getMessage(),templateEmail), true);
        };
    }

    private SimpleMailMessage createMailMessage(final Mail mail, TemplateEmail templateEmail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()));
        return mailMessage;
    }

    private String getTemplateEmail(String message, TemplateEmail templateEmail) {
        String text;
        if (templateEmail == TemplateEmail.TRELLO_CARD_EMAIL) {
            text = mailCreatorService.buildTrelloCardEmail(message);
        } else {
            text = mailCreatorService.buildSchedulerCardEmail(message);
        }
        return text;
    }
}
