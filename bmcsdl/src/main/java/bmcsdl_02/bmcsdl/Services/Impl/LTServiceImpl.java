package bmcsdl_02.bmcsdl.Services.Impl;

import bmcsdl_02.bmcsdl.Entity.Passport;
import bmcsdl_02.bmcsdl.Entity.Renewal;
import bmcsdl_02.bmcsdl.Repository.PassportRepository;
import bmcsdl_02.bmcsdl.Repository.RenewalRepository;
import bmcsdl_02.bmcsdl.Services.LTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LTServiceImpl implements LTService {
    
    private final RenewalRepository renewalRepository;
    private final PassportRepository passportRepository;
    
    @Override
    public List<Renewal> getProcessedRenewals() {
        log.info("LT Service: Getting all processed renewals");
        return renewalRepository.findProcessedRenewals();
    }
    
    @Override
    public List<Renewal> getApprovedRenewals() {
        log.info("LT Service: Getting approved renewals");
        return renewalRepository.findApprovedRenewals();
    }
    
    @Override
    public List<Renewal> getRejectedRenewals() {
        log.info("LT Service: Getting rejected renewals");
        return renewalRepository.findRejectedRenewals();
    }
    
    @Override
    public boolean updatePassportExpiration(String passportId, Date newEndDate) {
        try {
            log.info("LT Service: Updating passport {} expiration to {}", passportId, newEndDate);
            
            // Check if the passport exists
            Optional<Passport> passportOpt = passportRepository.findById(passportId);
            if (passportOpt.isEmpty()) {
                log.warn("Passport {} not found", passportId);
                return false;
            }
            Passport passport = passportOpt.get();
            
            // Validate new date strictly greater than current end date
            Date currentEnd = passport.getEnd_date();
            if (currentEnd != null && !newEndDate.after(currentEnd)) {
                log.warn("New end date {} is not after current end date {}", newEndDate, currentEnd);
                return false;
            }
            
            // Verify that there's an approved renewal for this CMND
            boolean hasApprovedRenewal = renewalRepository.existsByStatusAndCmnd("APPROVED", passport.getCmnd());
            
            if (!hasApprovedRenewal) {
                log.warn("No approved renewal found for passport {}", passportId);
                return false;
            }
            
            // Update the passport expiration date
            passportRepository.updateEndDate(passportId, newEndDate);
            log.info("Successfully updated passport {} expiration date", passportId);
            return true;
            
        } catch (Exception e) {
            log.error("Error updating passport expiration: {}", e.getMessage());
            return false;
        }
    }
    
    @Override
    public Passport getApprovedPassport(String passportId) {
        log.info("LT Service: Getting approved passport {}", passportId);
        
        Optional<Passport> passportOpt = passportRepository.findById(passportId);
        if (passportOpt.isEmpty()) {
            log.warn("Passport {} not found", passportId);
            return null;
        }
        
        // Check if there's an approved renewal for this passport
        List<Renewal> approvedRenewals = renewalRepository.findApprovedRenewals();
        boolean hasApprovedRenewal = approvedRenewals.stream()
            .anyMatch(r -> r.getPass_id() != null && r.getPass_id().toString().equals(passportId));
        
        if (!hasApprovedRenewal) {
            log.warn("No approved renewal found for passport {}", passportId);
            return null;
        }
        
        return passportOpt.get();
    }
    
    @Override
    public List<Object[]> getRenewalStatusSummary() {
        log.info("LT Service: Getting renewal status summary");
        // This would typically be a custom query, but for now we'll return basic counts
        return List.of(
            new Object[]{"APPROVED", renewalRepository.findApprovedRenewals().size()},
            new Object[]{"REJECTED", renewalRepository.findRejectedRenewals().size()}
        );
    }
}
