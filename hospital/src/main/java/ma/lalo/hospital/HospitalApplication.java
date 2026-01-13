package ma.lalo.hospital;

import ma.lalo.hospital.entities.*;
import ma.lalo.hospital.repositories.ConsultationRepository;
import ma.lalo.hospital.repositories.MedecinRepository;
import ma.lalo.hospital.repositories.PatientRepository;
import ma.lalo.hospital.repositories.RendezVousRepository;
import ma.lalo.hospital.service.IHospitalService;
import org.springframework.beans.factory.BeanRegistrarDslMarker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}



	@Bean
	CommandLineRunner start(IHospitalService hospitalService,PatientRepository patientRepository,MedecinRepository medecinRepository,RendezVousRepository rendezVousRepository,ConsultationRepository consultationRepository){
		return args -> {
			Stream.of("Oussama","Imrane","AbuBakr")
					.forEach(name -> {
						Patient p = new Patient();
						p.setNom(name);
						p.setDateNaissance(new Date());
						p.setMalade(false);
						hospitalService.savePatient(p);
					});

			Stream.of("Abderazzak Bouraqadi","Yassine Alami","Imrane Bahou")
					.forEach(name -> {
						Medecin m = new Medecin();
						m.setNom(name);
						m.setEmail(name+"@gmail.com");
						m.setSpecialite(Math.random()>0.5 ? "ORL" : "LES ANALYSES");
						hospitalService.saveMedecin(m);
					});

			Patient p1 = patientRepository.findById(1L).orElse(null);
			Patient p2 = patientRepository.findByNom("AbuBakr");


			Medecin m1 = medecinRepository.findById(1L).orElse(null);
			Medecin m2 = medecinRepository.findByNom("Abderazzak Bouraqadi");


			RendezVous rv = new RendezVous();
			rv.setDate(new Date());
			rv.setStatus(StatusRDV.DONE);
			rv.setPatient(p1);
			rv.setMedecin(m2);
			RendezVous savedRDV = hospitalService.saveRendezVous(rv);
			System.out.println(savedRDV.getId());



			RendezVous rv1 = rendezVousRepository.findAll().get(0);
			Consultation con = new Consultation();
			con.setRapport("BlaBla");
			con.setDateConsultation(new Date());
			con.setRendezVous(rv1);
			hospitalService.saveConsultation(con);






		};
	}
}
