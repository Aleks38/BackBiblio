package PROJET.AL.BIBLIO.demo.controller;

import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import PROJET.AL.BIBLIO.demo.entity.UtilisateurFactory;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @PostMapping("/creatUtilisateurs")
    public ResponseEntity<Map<String, Object>> createUtilisateur(@RequestBody Map<String, String> requestBody) {
        Utilisateur utilisateur;
        Map<String, Object> response = new HashMap<>();

        try {
            String role = requestBody.get("role");
            String nom = requestBody.get("nom");
            String prenom = requestBody.get("prenom");
            String email = requestBody.get("email");
            String motDePasse = requestBody.get("motDePasse");

            utilisateur = UtilisateurFactory.CreateUtilisateur(role, nom, prenom, email, motDePasse);
            utilisateur = utilisateurRepository.save(utilisateur);

            response.put("status", "success");
            response.put("message", "Utilisateur créé avec succès");
            response.put("utilisateur", utilisateur);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}