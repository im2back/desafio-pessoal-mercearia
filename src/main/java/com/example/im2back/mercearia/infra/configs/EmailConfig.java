package com.example.im2back.mercearia.infra.configs;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        //config properties do email sender
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587); // A porta pode variar dependendo do servidor de e-mail
        mailSender.setUsername("mercearia.api@gmail.com");
        //futuramente extrair para uma variavel de ambiente
        mailSender.setPassword("vocfjirdnteauqjr");
        
        //config gmail segurança
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
       
        return mailSender;
    }
}
