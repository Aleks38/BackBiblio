package PROJET.AL.BIBLIO.demo.login;

import PROJET.AL.BIBLIO.demo.entity.Utilisateur;

public class LoginResponse {
    private Utilisateur user;
    private String message;

    public LoginResponse(Utilisateur user, String message) {
        this.user = user;
        this.message = message;
    }

    // Getters et setters
    public Utilisateur getUser() { return user; }
    public void setUser(Utilisateur user ) { this.user = user; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
