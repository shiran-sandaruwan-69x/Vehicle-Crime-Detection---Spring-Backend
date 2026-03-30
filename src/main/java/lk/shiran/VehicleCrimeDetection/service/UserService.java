package lk.shiran.VehicleCrimeDetection.service;

import lk.shiran.VehicleCrimeDetection.dto.AppRoleDTO;
import lk.shiran.VehicleCrimeDetection.dto.AppUserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    AppUserDTO saveUser(AppUserDTO appUserDTO);
    AppRoleDTO saveRole(AppRoleDTO appRoleDTO);
    void addRoleToUser(String username,String roleName);
    AppUserDTO getUser(String username);
    List<AppUserDTO> getUsers();

    List<AppRoleDTO> getRoles();

    AppRoleDTO updateRole(Long id, AppRoleDTO appRoleDTO);

    Map<String, Map<String, List<Map<String, Object>>>>
    getPermissionsByRoleId(Long roleId);

    void updateRolePermissions(Long roleId, List<Long> permissionIds);

    AppUserDTO updateUser(Long id, AppUserDTO userDTO);

}
