package PROJET.AL.BIBLIO.demo.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements CommandLineRunner {

    private final Sujet sujet;
    private final ObserverMember observerMember;
    private final ObserverLibrarian observerLibrarian;

    @Autowired
    public AppInitializer(Sujet sujet, ObserverMember observerMember, ObserverLibrarian observerLibrarian) {
        this.sujet = sujet;
        this.observerMember = observerMember;
        this.observerLibrarian = observerLibrarian;
    }

    @Override
    public void run(String... args) throws Exception {
        // Attach observers
        sujet.attach(observerMember);
        sujet.attach(observerLibrarian);

        // Trigger some events for demonstration
        Notifications notifications = new Notifications(sujet);
        notifications.newDemandeEmprunt(); // Notifies librarians
        notifications.sendReminder();     // Notifies member
    }}
