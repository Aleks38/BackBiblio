package PROJET.AL.BIBLIO.demo.entity;

public class UtilisateurFactory {
        public static Utilisateur CreateUtilisateur(String role, String nom, String prenom, String email, String motDePasse, Role roleEntity) {
            switch (role.toLowerCase()) {
                case "administrateur":
                    return new Administrateur(nom, prenom, email, motDePasse, roleEntity);
                case "bibliothecaire":
                    return new Bibliothecaire(nom, prenom, email, motDePasse, roleEntity);
                case "membre":
                    return new Membre(nom, prenom, email, motDePasse, roleEntity);
                default:
                    throw new IllegalArgumentException("Type de role inconnu : " + role);
            }
    }

}
