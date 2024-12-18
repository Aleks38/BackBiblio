package PROJET.AL.BIBLIO.demo;

import PROJET.AL.BIBLIO.demo.entity.Emprunt;
import PROJET.AL.BIBLIO.demo.repository.EmpruntService;
import PROJET.AL.BIBLIO.demo.repository.LivreService;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BibliothequeControllerFacade {

    @Autowired
    private LivreService livreService;

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/createUser")
    public ResponseEntity<Map<String, Object>> createUtilisateur(
            @RequestParam String role,
            @RequestParam String nom,
            @RequestParam String prenom,
            @RequestParam String email,
            @RequestParam String motDePasse) {
        try {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("role", role);
            requestBody.put("nom", nom);
            requestBody.put("prenom", prenom);
            requestBody.put("email", email);
            requestBody.put("motDePasse", motDePasse);

            Map<String, Object> response = utilisateurService.createUtilisateur(requestBody);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }



    @PostMapping("/emprunts/{id}/prolonger")
    public ResponseEntity<String> prolongerEmprunt(@PathVariable int id, @RequestParam int jours) {
        String result = empruntService.prolongerEmprunt(id, jours);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/livres/{id}/disponible")
    public ResponseEntity<Boolean> verifierDisponibiliteLivre(@PathVariable int id) {
        boolean disponible = livreService.verifierDisponibiliteLivre(id);
        return ResponseEntity.ok(disponible);
    }

    @GetMapping("/emprunts/retards")
    public ResponseEntity<List<Emprunt>> getEmpruntsEnRetard() {
        List<Emprunt> empruntsEnRetard = empruntService.getEmpruntsEnRetard();
        return ResponseEntity.ok(empruntsEnRetard);
    }


    @PostMapping("/emprunts/return/{id}")
    public ResponseEntity<String> returnLivre(@PathVariable int id) {
        String message = livreService.returnLivre(id);
        return ResponseEntity.ok(message);
    }

}

