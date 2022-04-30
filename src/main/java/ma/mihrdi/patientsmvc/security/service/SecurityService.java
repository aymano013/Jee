package ma.mihrdi.patientsmvc.security.service;

import ma.mihrdi.patientsmvc.security.entities.AppRole;
import ma.mihrdi.patientsmvc.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username , String password , String password2);
    AppRole saveNewRole(String roleName , String description);
    void addRoleToUSer(String usernmae, String roleName);
    void removeRoleFromUSer(String usernmae, String roleName);
    AppUser loadUserByUserName(String username);
}
