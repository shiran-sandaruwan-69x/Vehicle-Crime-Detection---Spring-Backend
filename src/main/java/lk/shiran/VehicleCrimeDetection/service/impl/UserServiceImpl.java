package lk.shiran.VehicleCrimeDetection.service.impl;

import lk.shiran.VehicleCrimeDetection.dto.AppRoleDTO;
import lk.shiran.VehicleCrimeDetection.dto.AppUserDTO;
import lk.shiran.VehicleCrimeDetection.entity.AppRole;
import lk.shiran.VehicleCrimeDetection.entity.AppUser;
import lk.shiran.VehicleCrimeDetection.entity.Module;
import lk.shiran.VehicleCrimeDetection.entity.Permission;
import lk.shiran.VehicleCrimeDetection.entity.RolePermission;
import lk.shiran.VehicleCrimeDetection.repo.*;
import lk.shiran.VehicleCrimeDetection.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final ModuleRepo moduleRepo;

    private final PermissionRepo permissionRepo;

    private final RolePermissionRepo rolePermissionRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ModelMapper mapper;

    private final PasswordEncoder passwordEncoder;


    // meken kare spring security walata role tika add kara and access karanna ena user wa add kara
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = userRepo.findByUsername(username);
        if (user == null){
            log.error("user not found database");
            throw new UsernameNotFoundException("user not found database");
        }else{
            log.info("user found database {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(appRole -> {
            authorities.add(new SimpleGrantedAuthority(appRole.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public AppUserDTO saveUser(AppUserDTO appUserDTO) {
        if (appUserDTO != null){
         log.info("saving new user {} to database",appUserDTO.getName());
         // meken eken passwordEncoder karana eka karanne
         appUserDTO.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));
         userRepo.save(mapper.map(appUserDTO,AppUser.class));
         return appUserDTO;
        }
        return null;
    }

//    @Override
//    public AppRoleDTO saveRole(AppRoleDTO appRoleDTO) {
//        if (appRoleDTO != null){
//            log.info("saving new role {} to database",appRoleDTO.getName());
//            roleRepo.save(mapper.map(appRoleDTO, AppRole.class));
//            return appRoleDTO;
//        }
//        return null;
//    }

    @Override
    public AppRoleDTO saveRole(AppRoleDTO appRoleDTO) {
        if (appRoleDTO != null){
            log.info("saving new role {} to database", appRoleDTO.getName());

            AppRole savedRole = roleRepo.save(
                    mapper.map(appRoleDTO, AppRole.class)
            );

            return mapper.map(savedRole, AppRoleDTO.class);
        }
        return null;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}",roleName,username);
        AppUser user = userRepo.findByUsername(username);
        AppRole role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUserDTO getUser(String username) {
        log.info("fetching user {}",username);
        return mapper.map(userRepo.findByUsername(username),AppUserDTO.class);

    }

    @Override
    public List<AppUserDTO> getUsers() {
        log.info("fetching all users");
        return mapper.map(userRepo.findAll(), new TypeToken<ArrayList<AppUserDTO>>(){}.getType());
    }

    @Override
    public List<AppRoleDTO> getRoles() {
        log.info("Fetching all roles");
        return mapper.map(
                roleRepo.findAll(),
                new TypeToken<List<AppRoleDTO>>() {}.getType()
        );
    }

    @Override
    public AppRoleDTO updateRole(Long id, AppRoleDTO appRoleDTO) {
        log.info("Updating role with id {}", id);

        AppRole existingRole = roleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));

        // Update only required fields
        existingRole.setName(appRoleDTO.getName());
        existingRole.setStatus(appRoleDTO.getStatus());

        AppRole updatedRole = roleRepo.save(existingRole);

        return mapper.map(updatedRole, AppRoleDTO.class);
    }

    @Override
    public Map<String, Map<String, List<Map<String, Object>>>>
    getPermissionsByRoleId(Long roleId) {

        List<RolePermission> rolePermissions = rolePermissionRepo.findByRoleId(roleId);

        Map<Long, Boolean> activePermissionMap = rolePermissions.stream()
                .collect(Collectors.toMap(
                        rp -> rp.getPermission().getId(),
                        rp -> true
                ));

        List<Module> modules = moduleRepo.findAll();

        Map<String, Map<String, List<Map<String, Object>>>> response = new LinkedHashMap<>();

        List<Module> parents = modules.stream()
                .filter(m -> m.getParentId() == null)
                .collect(Collectors.toList());

        for (Module parent : parents) {

            Map<String, List<Map<String, Object>>> childMap = new LinkedHashMap<>();

            List<Module> children = modules.stream()
                    .filter(m -> parent.getId().equals(m.getParentId()))
                    .collect(Collectors.toList());

            for (Module child : children) {

                List<Permission> permissions = permissionRepo.findByModuleId(child.getId());

                List<Map<String, Object>> permissionList = permissions.stream()
                        .map(p -> {
                            Map<String, Object> map = new HashMap<>();
                            map.put("id", p.getId());
                            map.put("name", p.getName());
                            map.put("action", activePermissionMap.containsKey(p.getId()));
                            map.put("parentId", child.getId());
                            return map;
                        })
                        .collect(Collectors.toList());

                childMap.put(child.getName(), permissionList);
            }

            response.put(parent.getName(), childMap);
        }

        return response;
    }

    @Override
    @Transactional
    public void updateRolePermissions(Long roleId, List<Long> permissionIds) {

        // 1️⃣ Check role exists
        AppRole role = roleRepo.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + roleId));

        // 2️⃣ Delete existing permissions of that role
        rolePermissionRepo.deleteByRoleId(roleId);

        // 3️⃣ If no permissions selected, stop here
        if (permissionIds == null || permissionIds.isEmpty()) {
            return;
        }

        // 4️⃣ Fetch all permissions at once (better performance)
        List<Permission> permissions = permissionRepo.findAllById(permissionIds);

        // 5️⃣ Assign new permissions
        for (Permission permission : permissions) {

            RolePermission rolePermission = new RolePermission();
            rolePermission.setRole(role);
            rolePermission.setPermission(permission);

            rolePermissionRepo.save(rolePermission);
        }
    }

    @Override
    public AppUserDTO updateUser(Long id, AppUserDTO userDTO) {
        AppUser existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        existingUser.setName(userDTO.getName());
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setNicNo(userDTO.getNicNo());
        existingUser.setStatus(userDTO.getStatus());

        if(userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()){
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        if(userDTO.getRoles() != null){
            existingUser.setRoles(userDTO.getRoles());
        }

        AppUser updatedUser = userRepo.save(existingUser);

        return mapper.map(updatedUser, AppUserDTO.class);
    }

}
