package PROJET.AL.BIBLIO.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@DiscriminatorValue("1") // Correspond au rôle "Membre"
public class Membre extends Utilisateur {
    public Membre() {
        super();
    }

    public Membre(String nom, String prenom, String email, String motDePasse) {
        super(null, nom, prenom, email, motDePasse);
    }

    @Override
    public void afficherDetails() {
        System.out.println("Membre : " + getNom() + " " + getPrenom());
    }
}

