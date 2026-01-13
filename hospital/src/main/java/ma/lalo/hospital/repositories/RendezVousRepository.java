package ma.lalo.hospital.repositories;

import ma.lalo.hospital.entities.Medecin;
import ma.lalo.hospital.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface RendezVousRepository extends JpaRepository<RendezVous,String> {

    boolean existsByDate(Date date);

    boolean existsByMedecinAndDate(Medecin medecin, Date date);
}
