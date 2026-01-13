package ma.lalo.hospital.repositories;

import ma.lalo.hospital.entities.Medecin;
import ma.lalo.hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    Medecin findByNom(String name);
}
