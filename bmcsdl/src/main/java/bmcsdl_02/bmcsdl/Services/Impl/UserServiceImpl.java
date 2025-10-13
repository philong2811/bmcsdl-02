package bmcsdl_02.bmcsdl.Services.Impl;

import bmcsdl_02.bmcsdl.Entity.Users;
import bmcsdl_02.bmcsdl.Repository.UserRepository;
import bmcsdl_02.bmcsdl.Services.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
  @Autowired
  UserRepository userRepository;

  @Override
  public List<Users> getAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<Users> getUser(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public List<Users> getUserByRole(String role) {
    return userRepository.findByRole(role);
  }

  @Override
  public List<String> testConnect(String username, String password) {
    return null;
  }

  @Override
  public Optional<Users> getByUserName(String username) {
    return userRepository.findByUsername(username);
  }
}
