package ma.mihrdi.patientsmvc.repositories;

import ma.mihrdi.patientsmvc.entities.Patient;
import ma.mihrdi.patientsmvc.entities.Medecin;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findByNomContains(String kw, Pageable pageable);
}
