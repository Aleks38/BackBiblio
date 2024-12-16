package PROJET.AL.BIBLIO.demo.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Notifications {

    private final Sujet sujet;

    @Autowired
    public Notifications(Sujet sujet) {
        this.sujet = sujet;
    }

    public void newDemandeEmprunt() {
        System.out.println("A new demande d'emprunt has been created.");
        sujet.notifyObservers("New Demande");
    }

    public void sendReminder() {
        System.out.println("Sending a reminder to members.");
        sujet.notifyObservers("Reminder");
    }
}
