package PROJET.AL.BIBLIO.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("2")
@Data
public class Bibliothecaire extends Utilisateur {
    public Bibliothecaire() {
        super();
    }

    public Bibliothecaire(String nom, String prenom, String email, String motDePasse) {
        super(null, nom, prenom, email, motDePasse);
    }

    @Override
    public void afficherDetails() {
        System.out.println("Bibliothecaire : " + getNom() + " " + getPrenom() );
    }
}


