package PROJET.AL.BIBLIO.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int userId;
    private int livreId;
    private String dateEmprunt;
    private String dateRetour;

    @ManyToOne
    @JoinColumn(name = "statut_id")
    private Status status;
}
