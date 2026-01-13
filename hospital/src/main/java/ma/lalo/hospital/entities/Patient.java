package ma.lalo.hospital.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;


@Entity @NoArgsConstructor @AllArgsConstructor @ToString @Data @Getter @Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Date dateNaissance;
    private boolean malade;
    @OneToMany(mappedBy = "patient" , fetch = FetchType.LAZY)
    private Collection<RendezVous> rendezVous;
}
