package lk.shiran.VehicleCrimeDetection.controller;

import lk.shiran.VehicleCrimeDetection.entity.Report;
import lk.shiran.VehicleCrimeDetection.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@CrossOrigin
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<Report> save(@RequestBody Report report) {
        return ResponseEntity.ok(reportService.saveReport(report));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> get(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getReport(id));
    }

    @GetMapping
    public ResponseEntity<List<Report>> getAll() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Report> update(
            @PathVariable Long id,
            @RequestBody Report report) {

        return ResponseEntity.ok(reportService.updateReport(id, report));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok("Report deleted successfully");
    }
}
