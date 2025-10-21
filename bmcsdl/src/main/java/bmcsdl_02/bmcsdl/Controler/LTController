package bmcsdl_02.bmcsdl.Controler;

import bmcsdl_02.bmcsdl.Entity.Passport;
import bmcsdl_02.bmcsdl.Entity.Renewal;
import bmcsdl_02.bmcsdl.Services.LTService;
import bmcsdl_02.bmcsdl.Repository.PassportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/lt")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LTController {
    
    private final LTService ltService;
    private final PassportRepository passportRepository;
    
    @GetMapping("/home")
    public String ltHome(Model model) {
        System.out.println("LT User is: " + SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("LT Authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        
        // Get processed renewals for display and map passport string id
        List<Renewal> processedRenewals = ltService.getProcessedRenewals();
        processedRenewals.forEach(r -> {
            try {
                List<Passport> byCmnd = passportRepository.findByCmnd(r.getCmnd());
                if (byCmnd != null && !byCmnd.isEmpty()) {
                    r.setPassport_id(byCmnd.get(0).getId());
                }
            } catch (Exception ignored) { }
        });
        model.addAttribute("processedRenewals", processedRenewals);
        model.addAttribute("roleName", "Bộ phận Lưu trữ");
        
        return "lt";
    }
    
    @GetMapping("/api/processed-renewals")
    @ResponseBody
    public ResponseEntity<List<Renewal>> getProcessedRenewals() {
        List<Renewal> renewals = ltService.getProcessedRenewals();
        renewals.forEach(r -> {
            try {
                List<Passport> byCmnd = passportRepository.findByCmnd(r.getCmnd());
                if (byCmnd != null && !byCmnd.isEmpty()) {
                    r.setPassport_id(byCmnd.get(0).getId());
                }
            } catch (Exception ignored) { }
        });
        return ResponseEntity.ok(renewals);
    }
    
    @GetMapping("/api/approved-renewals")
    @ResponseBody
    public ResponseEntity<List<Renewal>> getApprovedRenewals() {
        List<Renewal> renewals = ltService.getApprovedRenewals();
        renewals.forEach(r -> {
            try {
                List<Passport> byCmnd = passportRepository.findByCmnd(r.getCmnd());
                if (byCmnd != null && !byCmnd.isEmpty()) {
                    r.setPassport_id(byCmnd.get(0).getId());
                }
            } catch (Exception ignored) { }
        });
        return ResponseEntity.ok(renewals);
    }
    
    @GetMapping("/api/rejected-renewals")
    @ResponseBody
    public ResponseEntity<List<Renewal>> getRejectedRenewals() {
        List<Renewal> renewals = ltService.getRejectedRenewals();
        renewals.forEach(r -> {
            try {
                List<Passport> byCmnd = passportRepository.findByCmnd(r.getCmnd());
                if (byCmnd != null && !byCmnd.isEmpty()) {
                    r.setPassport_id(byCmnd.get(0).getId());
                }
            } catch (Exception ignored) { }
        });
        return ResponseEntity.ok(renewals);
    }
    
    @PostMapping("/api/update-passport-expiration")
    @ResponseBody
    public ResponseEntity<String> updatePassportExpiration(
            @RequestParam String passportId,
            @RequestParam String newEndDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = dateFormat.parse(newEndDate);
            
            boolean success = ltService.updatePassportExpiration(passportId, endDate);
            if (success) {
                return ResponseEntity.ok("Passport expiration updated successfully");
            } else {
                return ResponseEntity.badRequest().body("Failed to update passport expiration");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid date format or error: " + e.getMessage());
        }
    }
    
    @GetMapping("/api/passport/{passportId}")
    @ResponseBody
    public ResponseEntity<Passport> getApprovedPassport(@PathVariable String passportId) {
        Passport passport = ltService.getApprovedPassport(passportId);
        if (passport != null) {
            return ResponseEntity.ok(passport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/api/status-summary")
    @ResponseBody
    public ResponseEntity<List<Object[]>> getStatusSummary() {
        List<Object[]> summary = ltService.getRenewalStatusSummary();
        return ResponseEntity.ok(summary);
    }
}
