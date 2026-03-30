package lk.shiran.VehicleCrimeDetection.service;

import lk.shiran.VehicleCrimeDetection.entity.Report;
import java.util.List;
public interface ReportService {

    Report saveReport(Report report);

    Report updateReport(Long id, Report report);

    Report getReport(Long id);

    List<Report> getAllReports();

    void deleteReport(Long id);
}
