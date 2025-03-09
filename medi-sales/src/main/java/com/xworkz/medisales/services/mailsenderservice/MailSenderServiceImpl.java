package com.xworkz.medisales.services.mailsenderservice;

import com.xworkz.medisales.constants.ServiceConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements MailSenderService{


    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public boolean sendRegistrationEmail(String subject, String bodyOfMsg, String email) {


        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(ServiceConstant.FROM_MAIL.getPath(),ServiceConstant.FROM_NAME.getPath());
            mimeMessageHelper.addTo(email);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(bodyOfMsg);

        };

        javaMailSender.send(mimeMessagePreparator);
        return true;
    }
}
