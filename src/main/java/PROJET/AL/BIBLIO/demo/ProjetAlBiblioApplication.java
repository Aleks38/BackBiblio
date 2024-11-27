package PROJET.AL.BIBLIO.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import PROJET.AL.BIBLIO.demo.Observer.AppInitializer;
import PROJET.AL.BIBLIO.demo.Observer.ObserverLibrarian;
import PROJET.AL.BIBLIO.demo.Observer.ObserverMember;
import PROJET.AL.BIBLIO.demo.Observer.Sujet;

@SpringBootApplication
public class ProjetAlBiblioApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProjetAlBiblioApplication.class, args);

        ObserverLibrarian observerLibrarian = context.getBean(ObserverLibrarian.class);
        ObserverMember observerMember = context.getBean(ObserverMember.class);
        Sujet sujet = context.getBean(Sujet.class);

        AppInitializer appInitializer = new AppInitializer(sujet, observerMember, observerLibrarian);

        try {
            appInitializer.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
