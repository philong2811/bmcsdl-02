package bmcsdl_02.bmcsdl.Services;

import bmcsdl_02.bmcsdl.Entity.Passport;
import bmcsdl_02.bmcsdl.Entity.Renewal;
import bmcsdl_02.bmcsdl.Entity.Users;
import bmcsdl_02.bmcsdl.Common.RoleEnum;
import bmcsdl_02.bmcsdl.Repository.PassportRepository;
import bmcsdl_02.bmcsdl.Repository.RenewalRepository;
import bmcsdl_02.bmcsdl.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataInitializationService implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final PassportRepository passportRepository;
    private final RenewalRepository renewalRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("Starting data initialization...");
        
        // Check if data already exists
        if (userRepository.count() > 0) {
            log.info("Data already exists, skipping initialization");
            return;
        }
        
        initializeUsers();
        initializePassports();
        initializeRenewals();
        
        log.info("Data initialization completed successfully");
    }
    
    private void initializeUsers() {
        log.info("Initializing users...");
        
        List<Users> users = List.of(
            Users.builder()
                .username("user1")
                .password(passwordEncoder.encode("password123"))
                .role(RoleEnum.USER)
                .cmnd("123456789")
                .district("Quận 1")
                .build(),
            Users.builder()
                .username("xt1")
                .password(passwordEncoder.encode("password123"))
                .role(RoleEnum.XT)
                .cmnd("987654321")
                .district("Quận 1")
                .build(),
            Users.builder()
                .username("xd1")
                .password(passwordEncoder.encode("password123"))
                .role(RoleEnum.XD)
                .cmnd("111222333")
                .district("Tất cả")
                .build(),
            Users.builder()
                .username("lt1")
                .password(passwordEncoder.encode("password123"))
                .role(RoleEnum.LT)
                .cmnd("444555666")
                .district("Tất cả")
                .build(),
            Users.builder()
                .username("gs1")
                .password(passwordEncoder.encode("password123"))
                .role(RoleEnum.GS)
                .cmnd("777888999")
                .district("Tất cả")
                .build()
        );
        
        userRepository.saveAll(users);
        log.info("Created {} users", users.size());
    }
    
    private void initializePassports() {
        log.info("Initializing passports...");
        
        Date currentDate = new Date();
        Date futureDate = new Date(currentDate.getTime() + (365L * 24 * 60 * 60 * 1000)); // 1 year from now
        
        List<Passport> passports = List.of(
            Passport.builder()
                .id("PC123456")
                .cmnd("123456789")
                .create_date(currentDate)
                .end_date(futureDate)
                .build(),
            Passport.builder()
                .id("PC789012")
                .cmnd("987654321")
                .create_date(currentDate)
                .end_date(futureDate)
                .build(),
            Passport.builder()
                .id("PC345678")
                .cmnd("111222333")
                .create_date(currentDate)
                .end_date(futureDate)
                .build(),
            Passport.builder()
                .id("PC901234")
                .cmnd("444555666")
                .create_date(currentDate)
                .end_date(futureDate)
                .build(),
            Passport.builder()
                .id("PC567890")
                .cmnd("777888999")
                .create_date(currentDate)
                .end_date(futureDate)
                .build()
        );
        
        passportRepository.saveAll(passports);
        log.info("Created {} passports", passports.size());
    }
    
    private void initializeRenewals() {
        log.info("Initializing renewals...");
        
        Date currentDate = new Date();
        
        List<Renewal> renewals = List.of(
            Renewal.builder()
                .cmnd("123456789")
                .pass_id(1L) // Reference to passport ID
                .create_date(currentDate)
                .status("APPROVED")
                .district("Quận 1")
                .verified_by(1L)
                .approved_by(1L)
                .descriptions("Gia hạn hộ chiếu - Đã được phê duyệt")
                .build(),
            Renewal.builder()
                .cmnd("987654321")
                .pass_id(2L)
                .create_date(currentDate)
                .status("REJECTED")
                .district("Quận 1")
                .verified_by(1L)
                .approved_by(1L)
                .descriptions("Gia hạn hộ chiếu - Bị từ chối do thiếu giấy tờ")
                .build(),
            Renewal.builder()
                .cmnd("111222333")
                .pass_id(3L)
                .create_date(currentDate)
                .status("APPROVED")
                .district("Quận 2")
                .verified_by(2L)
                .approved_by(1L)
                .descriptions("Gia hạn hộ chiếu - Đã được phê duyệt")
                .build(),
            Renewal.builder()
                .cmnd("444555666")
                .pass_id(4L)
                .create_date(currentDate)
                .status("PENDING")
                .district("Quận 3")
                .verified_by(3L)
                .approved_by(null)
                .descriptions("Gia hạn hộ chiếu - Chờ xét duyệt")
                .build(),
            Renewal.builder()
                .cmnd("777888999")
                .pass_id(5L)
                .create_date(currentDate)
                .status("VERIFIED")
                .district("Quận 4")
                .verified_by(4L)
                .approved_by(null)
                .descriptions("Gia hạn hộ chiếu - Đã xác thực, chờ phê duyệt")
                .build()
        );
        
        renewalRepository.saveAll(renewals);
        log.info("Created {} renewals", renewals.size());
    }
}
