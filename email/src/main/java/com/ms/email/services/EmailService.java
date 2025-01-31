package com.ms.email.services;

import com.ms.email.dtos.EmailRecord;
import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.EmailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public void sendEmail(EmailRecord emailRecord) {

        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailRecord, emailModel);
        try{
            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getTitle());
            message.setText(emailModel.getContent());
            mailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
            emailRepository.save(emailModel);

        }catch (MailException mailException){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        }
    }

    public List<EmailModel> getAllEmails(){
        return emailRepository.findAll();
    }


}
