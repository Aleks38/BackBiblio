package PROJET.AL.BIBLIO.demo.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class ObserverLibrarian implements Observer {

    private final String librarianEmail = "hammoudimohamedani@gmail.com"; // Replace with the librarian's email address
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void update(String message) {
        if (message.contains("New Demande")) {
            sendEmailNotification();
        }
    }

    private void sendEmailNotification() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(librarianEmail);
            message.setSubject("New Borrow Request Notification");
            message.setText("Dear Librarian, you have a new borrow request.\n\nThank you.");
            mailSender.send(message);
            System.out.println("Email sent to librarian: " + librarianEmail);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}