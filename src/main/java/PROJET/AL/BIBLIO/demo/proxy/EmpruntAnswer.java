package PROJET.AL.BIBLIO.demo.proxy;

import PROJET.AL.BIBLIO.demo.entity.Emprunt;

public class EmpruntAnswer {
    private Emprunt emprunt;
    private String message;

     EmpruntAnswer(Emprunt emprunt, String message) {
        this.emprunt = emprunt;
        this.message=message;
    }

    // Getters et setters
    public Emprunt getemprunt() { return emprunt; }
                public void setUmprunt(Emprunt emprunt ) { this.emprunt = emprunt; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
