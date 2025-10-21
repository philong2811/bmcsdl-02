package bmcsdl_02.bmcsdl.Repository;

import bmcsdl_02.bmcsdl.Entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PassportRepository extends JpaRepository<Passport, String> {
    
    Optional<Passport> findById(String id);
    
    List<Passport> findByCmnd(String cmnd);
    
    @Transactional
    @Modifying
    @Query("UPDATE Passport p SET p.end_date = :endDate WHERE p.id = :passportId")
    void updateEndDate(@Param("passportId") String passportId, @Param("endDate") Date endDate);
    
    @Query("SELECT p FROM Passport p WHERE p.id IN (SELECT r.pass_id FROM Renewal r WHERE r.status = 'APPROVED')")
    List<Passport> findApprovedPassports();
}
