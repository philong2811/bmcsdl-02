package bmcsdl_02.bmcsdl.Repository;

import bmcsdl_02.bmcsdl.Entity.Users;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
  @Override
  @Transactional
  List<Users> findAll();

  Optional<Users> findByUsername(String username);

  @Override
  <S extends Users> S save(S entity);

  Optional<Users> findById(Integer integer);

  List<Users> findByRole(String role);

  @Transactional
  @Modifying
  @Query(value = "CALL create_user(:username, :password)", nativeQuery = true)
  void createUserSystem(@Param("username") String username, @Param("password") String password);

}
