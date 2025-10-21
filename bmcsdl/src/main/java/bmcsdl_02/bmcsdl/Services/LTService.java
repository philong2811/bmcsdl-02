package bmcsdl_02.bmcsdl.Services;

import bmcsdl_02.bmcsdl.Entity.Passport;
import bmcsdl_02.bmcsdl.Entity.Renewal;

import java.util.Date;
import java.util.List;

public interface LTService {
    
    /**
     * Get all processed renewals (approved or rejected)
     * LT can only see the approval status, not personal information
     */
    List<Renewal> getProcessedRenewals();
    
    /**
     * Get only approved renewals for passport updates
     */
    List<Renewal> getApprovedRenewals();
    
    /**
     * Get only rejected renewals
     */
    List<Renewal> getRejectedRenewals();
    
    /**
     * Update passport expiration date for approved renewals
     * @param passportId The passport ID to update
     * @param newEndDate The new expiration date
     * @return true if successful, false otherwise
     */
    boolean updatePassportExpiration(String passportId, Date newEndDate);
    
    /**
     * Get passport information for approved renewals only
     * @param passportId The passport ID
     * @return Passport entity if found and approved, null otherwise
     */
    Passport getApprovedPassport(String passportId);
    
    /**
     * Get renewal status summary for monitoring
     * @return List of renewal statuses with counts
     */
    List<Object[]> getRenewalStatusSummary();
}
