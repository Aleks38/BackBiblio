package PROJET.AL.BIBLIO.demo.controller;

import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import PROJET.AL.BIBLIO.demo.entity.UtilisateurFactory;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("/utilisateurs")
    public ResponseEntity<?> createUtilisateur(
            @RequestParam String role,
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String email,
            @RequestParam String motDePasse
    ) {
        try {
            // Création de l'utilisateur via la factory
            Utilisateur utilisateur = UtilisateurFactory.CreateUtilisateur(role, nom, prenom, email, motDePasse);

            // Sauvegarde dans la base de données
            utilisateurRepository.save(utilisateur);

            return ResponseEntity.ok("Utilisateur créé avec succès : " + utilisateur.getNom() + " " + utilisateur.getPrenom());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue.");
        }
    }
}