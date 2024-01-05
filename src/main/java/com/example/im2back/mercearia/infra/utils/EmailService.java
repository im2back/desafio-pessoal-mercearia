package com.example.im2back.mercearia.infra.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviarEmailComAnexo(PDDocument anexo, String destinatario) throws IOException {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
        	if(destinatario == null) {
        		return;
        	}
        	
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(destinatario);
            helper.setSubject("Conta Detalhada");
            helper.setText("Caro cliente, segue em anexo um PDF contendo sua conta detalhada.");

         // Converte o PDDocument para ByteArrayDataSource
            DataSource dataSource = new ByteArrayDataSource(getBytesFromPDDocument(anexo), "application/pdf");

            
            // anexando o PDF ao e-mail
            helper.addAttachment("NotaDetalhada.pdf", dataSource);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private byte[] getBytesFromPDDocument(PDDocument document) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            document.save(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
