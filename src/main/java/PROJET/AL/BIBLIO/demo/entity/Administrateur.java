package PROJET.AL.BIBLIO.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("3")
@Data
public class Administrateur extends Utilisateur {
    public Administrateur() {
        super();
    }

    public Administrateur(String nom, String prenom, String email, String motDePasse) {
        super(null, nom, prenom, email, motDePasse);
    }

    @Override
    public void afficherDetails() {
        System.out.println("Admin : " + getNom() + " " + getPrenom() );
    }
}


