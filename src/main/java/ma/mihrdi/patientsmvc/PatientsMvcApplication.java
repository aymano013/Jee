package ma.mihrdi.patientsmvc;

import ma.mihrdi.patientsmvc.entities.Patient;
import ma.mihrdi.patientsmvc.repositories.PatientRepository;
import ma.mihrdi.patientsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }
    // @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {

            patientRepository.save(new Patient(null,"Hassan",new Date(),false,12));
            patientRepository.save(new Patient(null,"Mohammed",new Date(),true,333));
            patientRepository.save(new Patient(null,"Yasmine",new Date(),true,65));
            patientRepository.save(new Patient(null,"Hanae",new Date(),false,12));


        };
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService ){
        return args -> {
                securityService.saveNewUser("admin","admin","admin");
                securityService.saveNewUser("user","user","user");

                securityService.saveNewRole("USER","");
                securityService.saveNewRole("ADMIN","hada ana xD");

                securityService.addRoleToUSer("admin","ADMIN");
                securityService.addRoleToUSer("admin","USER");

                securityService.addRoleToUSer("user","USER");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
