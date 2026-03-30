package lk.shiran.VehicleCrimeDetection.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleNo;
    private String vehicleType;
    private String vehicleBrand;
    private String vehicleColor;
    private String manufactureYear;
    private String ownerName;
    private String ownerNIC;
    private Boolean status;
    
    @OneToMany(mappedBy = "vehicleDetails", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Report> reports;
}
