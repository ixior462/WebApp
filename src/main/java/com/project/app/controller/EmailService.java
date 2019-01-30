package com.project.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Class used to send emails from contact form
 * @author Dominika Kunc
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    /**
     * Method used to send emails from contact form
     * @author Dominika Kunc
     * @param from Sender's email
     * @param to Receiver's email
     * @param subject message
     * @param topic Subject of message
     */
    public void sendSimpleMessage(String to, String from, String topic, String subject){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(topic);
        message.setText(subject);
        message.setTo(to);
        message.setFrom(from);

        emailSender.send(message);
    }

}