package bmcsdl_02.bmcsdl.Repository;

import bmcsdl_02.bmcsdl.Entity.Renewal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RenewalRepository extends JpaRepository<Renewal, Long> {
    
    Optional<Renewal> findById(Long id);
    
    List<Renewal> findByStatus(String status);
    
    List<Renewal> findByStatusIn(List<String> statuses);
    
    boolean existsByStatusAndCmnd(String status, String cmnd);
    
    @Query("SELECT r FROM Renewal r WHERE r.status IN ('APPROVED', 'REJECTED')")
    List<Renewal> findProcessedRenewals();
    
    @Query("SELECT r FROM Renewal r WHERE r.status = 'APPROVED'")
    List<Renewal> findApprovedRenewals();
    
    @Query("SELECT r FROM Renewal r WHERE r.status = 'REJECTED'")
    List<Renewal> findRejectedRenewals();
}
