package PROJET.AL.BIBLIO.demo.Observer;

import org.springframework.stereotype.Component;

@Component
public class ObserverMember implements Observer {
    @Override
    public void update(String message) {
        if (message.contains("Reminder")) {
            System.out.println("Member received notification: " + message);
        }
    }
}