package lk.shiran.VehicleCrimeDetection;

import lk.shiran.VehicleCrimeDetection.dto.AppRoleDTO;
import lk.shiran.VehicleCrimeDetection.dto.AppUserDTO;
import lk.shiran.VehicleCrimeDetection.entity.AppRole;
import lk.shiran.VehicleCrimeDetection.entity.Permission;
import lk.shiran.VehicleCrimeDetection.entity.RolePermission;
import lk.shiran.VehicleCrimeDetection.repo.ModuleRepo;
import lk.shiran.VehicleCrimeDetection.repo.PermissionRepo;
import lk.shiran.VehicleCrimeDetection.repo.RolePermissionRepo;
import lk.shiran.VehicleCrimeDetection.repo.RoleRepo;
import lk.shiran.VehicleCrimeDetection.service.UserService;
import lk.shiran.VehicleCrimeDetection.entity.Module;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.ArrayList;

@SpringBootApplication
public class VehicleCrimeDetection {

	public String PORT=System.getenv("PORT");


	public static void main(String[] args) {

		SpringApplication.run(VehicleCrimeDetection.class);
		System.out.println("spring start ...!");
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//
//			userService.saveRole(new AppRoleDTO(null,"ROLE_USER", true));
//			userService.saveRole(new AppRoleDTO(null,"ROLE_MANAGER", true));
//			userService.saveRole(new AppRoleDTO(null,"ROLE_ADMIN", true));
//			userService.saveRole(new AppRoleDTO(null,"ROLE_SUPER_ADMIN", true));
//
//			userService.saveUser(new AppUserDTO(null,"shiran sandaruwan","shiran","shiran@gmail.com","972752916v","1234",new ArrayList<>()));
//			userService.saveUser(new AppUserDTO(null,"kamal perera","kamal","kamal@gmail.com","972752911v","1234",new ArrayList<>()));
//			userService.saveUser(new AppUserDTO(null,"gayan perera","gayan","gayan@gmail.com","972752913v","1234",new ArrayList<>()));
//			userService.saveUser(new AppUserDTO(null,"tashi perera","tashi","tashi@gmail.com","972752915v","1234",new ArrayList<>()));
//
//
//			userService.addRoleToUser("shiran","ROLE_ADMIN");
//			userService.addRoleToUser("shiran","ROLE_USER");
//			userService.addRoleToUser("shiran","ROLE_MANAGER");
//			userService.addRoleToUser("shiran","ROLE_SUPER_ADMIN");
//			userService.addRoleToUser("kamal","ROLE_USER");
//			userService.addRoleToUser("gayan","ROLE_MANAGER");
//			userService.addRoleToUser("tashi","ROLE_ADMIN");
//
//		};
//	}
//
//	@Bean
//	CommandLineRunner run(
//			UserService userService,
//			ModuleRepo moduleRepo,
//			PermissionRepo permissionRepo,
//			RoleRepo roleRepo,
//			RolePermissionRepo rolePermissionRepo
//	) {
//		return args -> {
//
//			// ===============================
//			// 4️⃣ MODULES (Parent + Child)
//			// ===============================
//
//			Module userManagement = moduleRepo.save(new Module(null,"User Management",null,true));
//			Module vehicleManagement = moduleRepo.save(new Module(null,"Vehicle Management",null,true));
//			Module dashboardManagement = moduleRepo.save(new Module(null,"Dashboard Management",null,true));
//			Module reportManagement = moduleRepo.save(new Module(null,"Report Management",null,true));
//
//			Module users = moduleRepo.save(new Module(null,"Users",userManagement.getId(),true));
//			Module roles = moduleRepo.save(new Module(null,"Roles",userManagement.getId(),true));
//			Module dashboard = moduleRepo.save(new Module(null,"Dashboard",dashboardManagement.getId(),true));
//			Module report = moduleRepo.save(new Module(null,"report",reportManagement.getId(),true));
//
//			Module vehicles = moduleRepo.save(new Module(null,"Vehicles",vehicleManagement.getId(),true));
//			Module crimes = moduleRepo.save(new Module(null,"Crimes",vehicleManagement.getId(),true));
//
//			// ===============================
//			// 5️⃣ PERMISSIONS
//			// ===============================
//
//			Permission createUser = permissionRepo.save(new Permission(null,"CREATE",users));
//			Permission readUser   = permissionRepo.save(new Permission(null,"READ",users));
//			Permission updateUser = permissionRepo.save(new Permission(null,"UPDATE",users));
//			Permission deleteUser = permissionRepo.save(new Permission(null,"DELETE",users));
//
//			Permission createVehicle = permissionRepo.save(new Permission(null,"CREATE",vehicles));
//			Permission readVehicle   = permissionRepo.save(new Permission(null,"READ",vehicles));
//			Permission updateVehicle = permissionRepo.save(new Permission(null,"UPDATE",vehicles));
//			Permission deleteVehicle = permissionRepo.save(new Permission(null,"DELETE",vehicles));
//
//			Permission createRoles = permissionRepo.save(new Permission(null,"CREATE",roles));
//			Permission readRoles   = permissionRepo.save(new Permission(null,"READ",roles));
//			Permission updateRoles = permissionRepo.save(new Permission(null,"UPDATE",roles));
//			Permission deleteRoles = permissionRepo.save(new Permission(null,"DELETE",roles));
//
//			Permission createDashboard = permissionRepo.save(new Permission(null,"CREATE",dashboard));
//			Permission readDashboard   = permissionRepo.save(new Permission(null,"READ",dashboard));
//			Permission updateDashboard = permissionRepo.save(new Permission(null,"UPDATE",dashboard));
//			Permission deleteDashboard = permissionRepo.save(new Permission(null,"DELETE",dashboard));
//
//			Permission createReport = permissionRepo.save(new Permission(null,"CREATE",report));
//			Permission readReport   = permissionRepo.save(new Permission(null,"READ",report));
//			Permission updateReport = permissionRepo.save(new Permission(null,"UPDATE",report));
//			Permission deleteReport = permissionRepo.save(new Permission(null,"DELETE",report));
//
//			Permission createCrimes = permissionRepo.save(new Permission(null,"CREATE",crimes));
//			Permission readCrimes   = permissionRepo.save(new Permission(null,"READ",crimes));
//			Permission updateCrimes = permissionRepo.save(new Permission(null,"UPDATE",crimes));
//			Permission deleteCrimes = permissionRepo.save(new Permission(null,"DELETE",crimes));
//
//			// ===============================
//			// 6️⃣ ASSIGN PERMISSIONS TO ROLE (ADMIN)
//			// ===============================
//
//			AppRole adminRole = roleRepo.findByName("ROLE_ADMIN");
//
//			rolePermissionRepo.save(new RolePermission(null, adminRole, createUser));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, readUser));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, updateUser));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, deleteUser));
//
//			rolePermissionRepo.save(new RolePermission(null, adminRole, createVehicle));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, readVehicle));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, updateVehicle));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, deleteVehicle));
//
//			rolePermissionRepo.save(new RolePermission(null, adminRole, createRoles));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, readRoles));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, updateRoles));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, deleteRoles));
//
//			rolePermissionRepo.save(new RolePermission(null, adminRole, createDashboard));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, readDashboard));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, updateDashboard));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, deleteDashboard));
//
//			rolePermissionRepo.save(new RolePermission(null, adminRole, createReport));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, readReport));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, updateReport));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, deleteReport));
//
//			rolePermissionRepo.save(new RolePermission(null, adminRole, createCrimes));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, readCrimes));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, updateCrimes));
//			rolePermissionRepo.save(new RolePermission(null, adminRole, deleteCrimes));
//
//		};
//	}

}
