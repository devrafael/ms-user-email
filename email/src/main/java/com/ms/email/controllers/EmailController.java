package com.ms.email.controllers;

import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {


    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping()
    public ResponseEntity<List<EmailModel>> getAllEmails() {
        List<EmailModel> emails = emailService.getAllEmails();
        return ResponseEntity.ok().body(emails);
    }
}
