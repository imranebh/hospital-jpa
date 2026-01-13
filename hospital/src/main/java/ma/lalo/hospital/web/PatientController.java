package ma.lalo.hospital.web;

import ma.lalo.hospital.entities.Patient;
import ma.lalo.hospital.repositories.PatientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients")
    List<Patient> patientList() {
        return patientRepository.findAll();
    }
}
