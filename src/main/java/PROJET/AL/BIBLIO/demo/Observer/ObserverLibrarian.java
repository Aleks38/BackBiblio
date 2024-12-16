package PROJET.AL.BIBLIO.demo.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class ObserverLibrarian implements Observer {

    @Autowired
    private JavaMailSender mailSender;

    private final String librarianEmail = "hammoudimohamedani@gmail.com"; // Replace with the librarian's email address

    @Override
    public void update(String message) {
        if (message.contains("New Demande")) {
            sendEmailNotification();
        }
    }

    private void sendEmailNotification() {
        try {
            SimpleMailMessage messageee = new SimpleMailMessage();

            messageee.setTo(librarianEmail);
            messageee.setSubject("New Borrow Request Notification");
            messageee.setText("Dear Librarian, you have a new borrow request.\n\nThank you.");
            mailSender.send(messageee);
            System.out.println("Email sent to librarian: " + librarianEmail);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
