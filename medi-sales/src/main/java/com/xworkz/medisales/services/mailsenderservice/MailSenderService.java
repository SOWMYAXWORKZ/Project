package com.xworkz.medisales.services.mailsenderservice;

public interface MailSenderService {

    boolean sendRegistrationEmail(String subject, String bodyOfMsg, String email);
}
