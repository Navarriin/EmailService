package com.navarro.emailservice.controllers;

import com.navarro.emailservice.application.EmailSenderUseCaseImpl;
import com.navarro.emailservice.core.EmailRequest;
import com.navarro.emailservice.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/email")
public class EmailSenderController {

    private final EmailSenderUseCaseImpl emailSenderUseCase;

    @Autowired
    public EmailSenderController(EmailSenderUseCaseImpl emailSenderUseCase) {
        this.emailSenderUseCase = emailSenderUseCase;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        try {
            this.emailSenderUseCase.sendEmail(request.to(), request.subject(), request.body());
            return ResponseEntity.ok("Email sent Success!");
        } catch (EmailServiceException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending email!");
        }
    }
}
