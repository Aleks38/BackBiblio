package PROJET.AL.BIBLIO.demo.login;

import PROJET.AL.BIBLIO.demo.login.LoginResponse;
import PROJET.AL.BIBLIO.demo.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import PROJET.AL.BIBLIO.demo.repository.UtilisateurRepository;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UtilisateurRepository UtilisateurRepository;

    public LoginResponse verifyUser(String email, String password) {
        Optional<Utilisateur> optionalUser = UtilisateurRepository.findByEmailAndMotDePasse(email, password);

        if (optionalUser.isPresent()) {
            Utilisateur user = optionalUser.get();
            return new LoginResponse(user, "Connexion réussie");
        } else {
            return new LoginResponse(null,  "Identifiants incorrects");
        }
    }
}
