package PROJET.AL.BIBLIO.demo.entity;

public class UtilisateurFactory {
        public static Utilisateur CreateUtilisateur(String role, String nom, String prenom, String email, String motDePasse) {
            switch (role.toLowerCase()) {
                case "administrateur":
                    return new Administrateur(nom, prenom, email, motDePasse);
                case "bibliothecaire":
                    return new Bibliothecaire(nom, prenom, email, motDePasse);
                case "membre":
                    return new Membre(nom, prenom, email, motDePasse);
                default:
                    throw new IllegalArgumentException("Type de role inconnu : " + role);
            }
    }

}
