package ma.lalo.hospital.service;

import ma.lalo.hospital.entities.Consultation;
import ma.lalo.hospital.entities.Medecin;
import ma.lalo.hospital.entities.Patient;
import ma.lalo.hospital.entities.RendezVous;

public interface IHospitalService

{
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);

}
