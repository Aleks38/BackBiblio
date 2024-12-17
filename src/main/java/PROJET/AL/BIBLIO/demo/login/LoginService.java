package PROJET.AL.BIBLIO.demo.login;

import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public LoginResponse verifyUser(String email, String password) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);

        if (utilisateur != null) {
            if (utilisateur.getMotDePasse().equals(password)) {
                return new LoginResponse(utilisateur, "Connexion réussie");
            } else {
                return new LoginResponse(null, "Mot de passe incorrect");
            }
        } else {
            return new LoginResponse(null, "Identifiants incorrects");
        }
    }

}