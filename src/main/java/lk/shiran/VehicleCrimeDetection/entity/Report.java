package lk.shiran.VehicleCrimeDetection.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleNo;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String nic;
    private String gender;
    private String birthday;
    private String reportDescription;
    private String address1;
    private String address2;
    private String address3;
    private String zipPostalCode;
    private String fineAmount;
    private String city;
    private String country;
    private Double lat;
    private Double lng;

    // Report.java
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private VehicleDetails vehicleDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alert_type_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AlertType alertType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SystemAlertPriority systemAlertPriority;
}
