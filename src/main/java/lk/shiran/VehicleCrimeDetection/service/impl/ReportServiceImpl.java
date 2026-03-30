package lk.shiran.VehicleCrimeDetection.service.impl;

import lk.shiran.VehicleCrimeDetection.entity.Report;
import lk.shiran.VehicleCrimeDetection.repo.AlertTypeRepo;
import lk.shiran.VehicleCrimeDetection.repo.ReportRepo;
import lk.shiran.VehicleCrimeDetection.repo.SystemAlertPriorityRepo;
import lk.shiran.VehicleCrimeDetection.repo.VehicleDetailsRepo;
import lk.shiran.VehicleCrimeDetection.service.ReportService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import lk.shiran.VehicleCrimeDetection.entity.VehicleDetails;
import lk.shiran.VehicleCrimeDetection.entity.AlertType;
import lk.shiran.VehicleCrimeDetection.entity.SystemAlertPriority;
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepo reportRepo;
    private final VehicleDetailsRepo vehicleRepo;
    private final AlertTypeRepo alertTypeRepo;
    private final SystemAlertPriorityRepo priorityRepo;

    @Override
    public Report saveReport(Report report) {

        if (report.getVehicleDetails() != null) {
            Long vehicleId = report.getVehicleDetails().getId();
            VehicleDetails vehicle = vehicleRepo.findById(vehicleId)
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));
            report.setVehicleDetails(vehicle);
        }

        if (report.getAlertType() != null) {
            Long alertId = report.getAlertType().getId();
            AlertType alert = alertTypeRepo.findById(alertId)
                    .orElseThrow(() -> new RuntimeException("AlertType not found"));
            report.setAlertType(alert);
        }

        if (report.getSystemAlertPriority() != null) {
            Long priorityId = report.getSystemAlertPriority().getId();
            SystemAlertPriority priority = priorityRepo.findById(priorityId)
                    .orElseThrow(() -> new RuntimeException("Priority not found"));
            report.setSystemAlertPriority(priority);
        }

        return reportRepo.save(report);
    }

    @Override
    public Report updateReport(Long id, Report report) {

        Report existing = reportRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        existing.setFirstName(report.getFirstName());
        existing.setLastName(report.getLastName());
        existing.setMobileNumber(report.getMobileNumber());
        existing.setNic(report.getNic());
        existing.setGender(report.getGender());
        existing.setBirthday(report.getBirthday());
        existing.setReportDescription(report.getReportDescription());
        existing.setAddress1(report.getAddress1());
        existing.setAddress2(report.getAddress2());
        existing.setAddress3(report.getAddress3());
        existing.setZipPostalCode(report.getZipPostalCode());
        existing.setFineAmount(report.getFineAmount());
        existing.setCity(report.getCity());
        existing.setCountry(report.getCountry());

        return saveReport(existing);
    }

    @Override
    public Report getReport(Long id) {
        return reportRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepo.findAll();
    }

    @Override
    public void deleteReport(Long id) {
        reportRepo.deleteById(id);
    }
}
