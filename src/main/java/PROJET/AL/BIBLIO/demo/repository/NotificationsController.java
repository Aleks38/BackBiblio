package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.Observer.AppInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationsController {

    private final AppInitializer appInitializer;

    @Autowired
    public NotificationsController(AppInitializer appInitializer) {
        this.appInitializer = appInitializer;
    }

    @RequestMapping(value = "/trigger", method = RequestMethod.GET)
    public void triggerNotifications() throws Exception {
        appInitializer.run(); // Trigger the event handling function
    }
}
