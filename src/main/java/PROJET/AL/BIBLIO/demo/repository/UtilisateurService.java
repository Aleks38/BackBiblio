package PROJET.AL.BIBLIO.demo.repository;

import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import PROJET.AL.BIBLIO.demo.entity.UtilisateurFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Map<String, Object> createUtilisateur(Map<String, String> requestBody) {
        Map<String, Object> response = new HashMap<>();

        String role = requestBody.get("role");
        String nom = requestBody.get("nom");
        String prenom = requestBody.get("prenom");
        String email = requestBody.get("email");
        String motDePasse = requestBody.get("motDePasse");

        Utilisateur utilisateur = UtilisateurFactory.CreateUtilisateur(role, nom, prenom, email, motDePasse);

        utilisateur = utilisateurRepository.save(utilisateur);

        response.put("status", "success");
        response.put("message", "Utilisateur créé avec succès");
        response.put("utilisateur", utilisateur);

        return response;
    }
}
