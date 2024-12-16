package PROJET.AL.BIBLIO.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("Bibliothecaire")
public class Bibliothecaire extends Utilisateur {
    public Bibliothecaire() {
        super();
    }

    public Bibliothecaire(String nom, String prenom, String email, String motDePasse, Role role) {
        super(null, nom, prenom, email, motDePasse, role);
    }

    @Override
    public void afficherDetails() {
        System.out.println("Bibliothecaire : " + getNom() + " " + getPrenom() );
    }
}


