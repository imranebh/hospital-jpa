package ma.lalo.hospital.service;

import jakarta.transaction.Transactional;
import ma.lalo.hospital.entities.Consultation;
import ma.lalo.hospital.entities.Medecin;
import ma.lalo.hospital.entities.Patient;
import ma.lalo.hospital.entities.RendezVous;
import ma.lalo.hospital.repositories.ConsultationRepository;
import ma.lalo.hospital.repositories.MedecinRepository;
import ma.lalo.hospital.repositories.PatientRepository;
import ma.lalo.hospital.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Transactional
public class IHospitalServiceImpl implements IHospitalService {


    private ConsultationRepository consultationRepository;
    private MedecinRepository medecinRepository;
    private PatientRepository patientRepository;
    private RendezVousRepository rendezVousRepository;

    public IHospitalServiceImpl(ConsultationRepository consultationRepository,
                                MedecinRepository medecinRepository,
                                PatientRepository patientRepository,
                                RendezVousRepository rendezVousRepository
                                ) {
        this.consultationRepository = consultationRepository;
        this.medecinRepository =  medecinRepository;
        this.patientRepository = patientRepository;
        this.rendezVousRepository = rendezVousRepository;
    }


    @Override
    public Patient savePatient(Patient patient) {

        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {

        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        rendezVous.setId(UUID.randomUUID().toString());

        if (rendezVousRepository.existsByDate(rendezVous.getDate())) {
            throw new IllegalStateException("This rendezVous date/time is already taken");
        }

        if (rendezVousRepository.existsByMedecinAndDate(rendezVous.getMedecin(), rendezVous.getDate())) {
            throw new IllegalStateException("Medecin is not available at this date/time");
        }

        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {

        return consultationRepository.save(consultation);
    }
}
