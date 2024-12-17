package PROJET.AL.BIBLIO.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurFacade {

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private BibliothecaireRepository bibliothecaireRepository;

    @Autowired
    private AdministrateurRepository administrateurRepository;

    public Utilisateur createUtilisateur(String type, Utilisateur utilisateur) {
        if (type.equals("Membre")) {
            return membreRepository.save((Membre) utilisateur);
        } else if (type.equals("Bibliothecaire")) {
            return bibliothecaireRepository.save((Bibliothecaire) utilisateur);
        } else if (type.equals("Administrateur")) {
            return administrateurRepository.save((Administrateur) utilisateur);
        } else {
            throw new IllegalArgumentException("Type d'utilisateur non pris en charge");
        }
    }

    public Utilisateur readUtilisateur(Long id, String type) {
        if (type.equals("Membre")) {
            return membreRepository.findById(id).orElseThrow(() -> new RuntimeException("Membre non trouvé"));
        } else if (type.equals("Bibliothecaire")) {
            return bibliothecaireRepository.findById(id).orElseThrow(() -> new RuntimeException("Bibliothécaire non trouvé"));
        } else if (type.equals("Administrateur")) {
            return administrateurRepository.findById(id).orElseThrow(() -> new RuntimeException("Administrateur non trouvé"));
        } else {
            throw new IllegalArgumentException("Type d'utilisateur non pris en charge");
        }
    }

    public void deleteUtilisateur(Long id, String type) {
        if (type.equals("Membre")) {
            membreRepository.deleteById(id);
        } else if (type.equals("Bibliothecaire")) {
            bibliothecaireRepository.deleteById(id);
        } else if (type.equals("Administrateur")) {
            administrateurRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Type d'utilisateur non pris en charge");
        }
    }
}
