package PROJET.AL.BIBLIO.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Administrateur")
public class Administrateur extends Utilisateur {
    public Administrateur() {
        super();
    }

    public Administrateur(String nom, String prenom, String email, String motDePasse, Role role) {
        super(null, nom, prenom, email, motDePasse, role);
    }

    @Override
    public void afficherDetails() {
        System.out.println("Admin : " + getNom() + " " + getPrenom() );
    }
}


