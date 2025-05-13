package kz.edu.astanait.codeadviser.service;

public interface EmailSender {

    void send(String to, String subject, String text);
}
