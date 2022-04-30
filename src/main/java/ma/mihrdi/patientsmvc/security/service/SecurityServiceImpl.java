package ma.mihrdi.patientsmvc.security.service;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import ma.mihrdi.patientsmvc.security.entities.AppRole;
import ma.mihrdi.patientsmvc.security.entities.AppUser;
import ma.mihrdi.patientsmvc.security.repositories.AppRoleRepository;
import ma.mihrdi.patientsmvc.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

@Service
@Slf4j // attribut pour loger des informations
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String password2) {
        if(!password.equals(password2)) throw new RuntimeException("Passwords not match");
        String hashedPWD = passwordEncoder.encode(password);
        AppUser appUser= new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {


        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole != null) throw new RuntimeException("Role "+roleName+" Already exist");
        appRole = new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);


        AppRole savedAppRole = appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUSer(String usernmae, String roleName) {

        AppUser appUser = appUserRepository.findByUsername(usernmae);
        if (appUser == null) throw new RuntimeException("User "+roleName+" not found !! ");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole == null) throw new RuntimeException("Role "+roleName+" not found !! ");

        appUser.getAppRoles().add(appRole);
        appUserRepository.save(appUser);


    }

    @Override
    public void removeRoleFromUSer(String usernmae, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(usernmae);
        if (appUser == null) throw new RuntimeException("User "+roleName+" not found !! ");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole == null) throw new RuntimeException("Role "+roleName+" not found !! ");

        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}
