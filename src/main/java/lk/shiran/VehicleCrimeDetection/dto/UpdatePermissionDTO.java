package lk.shiran.VehicleCrimeDetection.dto;
import lombok.Data;
import java.util.List;

@Data
public class UpdatePermissionDTO {
    private List<Long> permissions;
}
