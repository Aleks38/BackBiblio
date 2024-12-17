package PROJET.AL.BIBLIO.demo.controller;

import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import PROJET.AL.BIBLIO.demo.entity.UtilisateurFactory;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/*
@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")

public class userController {
    @Autowired
     UtilisateurRepository utilisateurRepository;

    @PostMapping("/utilisateurs")
    public String createUtilisateur(
            @RequestParam String role,
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String email,
            @RequestParam String motDePasse
    ) {

        // Créer un utilisateur via la factory
        Utilisateur utilisateur;
        try {
            utilisateur = UtilisateurFactory.CreateUtilisateur(role, nom, prenom, email, motDePasse);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        // Enregistrer l'utilisateur dans la base de données
        utilisateurRepository.save(utilisateur);

        return "Utilisateur créé avec succès : " + utilisateur.getNom() + " " + utilisateur.getPrenom();
    }
}*/
