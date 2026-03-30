package lk.shiran.VehicleCrimeDetection.dto;

import lk.shiran.VehicleCrimeDetection.entity.AppRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppUserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String nicNo;
    private String password;
    private Boolean status;
    private Collection<AppRole> roles = new ArrayList<>();
}
