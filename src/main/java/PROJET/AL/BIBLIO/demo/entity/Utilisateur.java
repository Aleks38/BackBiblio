package PROJET.AL.BIBLIO.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}