package lk.shiran.VehicleCrimeDetection.service;

import lk.shiran.VehicleCrimeDetection.dto.AppRoleDTO;
import lk.shiran.VehicleCrimeDetection.dto.AppUserDTO;

import java.util.List;

public interface UserService {
    AppUserDTO saveUser(AppUserDTO appUserDTO);
    AppRoleDTO saveRole(AppRoleDTO appRoleDTO);
    void addRoleToUser(String username,String roleName);
    AppUserDTO getUser(String username);
    List<AppUserDTO> getUsers();

}
