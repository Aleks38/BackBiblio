package PROJET.AL.BIBLIO.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@DiscriminatorValue("Membre")
public class Membre extends Utilisateur {
    public Membre() {
        super();
    }

    public Membre(String nom, String prenom, String email, String motDePasse, Role role) {
        super(null, nom, prenom, email, motDePasse, role);
    }

    @Override
    public void afficherDetails() {
        System.out.println("Membre : " + getNom() + " " + getPrenom());
    }
}

