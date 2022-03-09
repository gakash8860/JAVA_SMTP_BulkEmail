package com.bulkemail.BulkEmail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;


    public void sendEmail(ArrayList<String> toEmail, String subject, String body){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("gakash8860@gmail.com");
        for (int i=0;i< toEmail.size();i++){
            simpleMailMessage.setTo(toEmail.get(i));

            simpleMailMessage.setText(body);
            simpleMailMessage.setSubject(subject);

            javaMailSender.send(simpleMailMessage);
            System.out.println("Mail Sent Successfully");
        }



    }
}
