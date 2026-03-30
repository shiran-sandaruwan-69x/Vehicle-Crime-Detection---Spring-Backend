package lk.shiran.VehicleCrimeDetection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alertType;
    private String alertTypeCode;
    private Boolean status;

    @OneToMany(mappedBy = "alertType", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Report> reports = new ArrayList<>();
}
