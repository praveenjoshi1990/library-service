package com.mast.peen.libraryservice.service;


import com.mast.peen.libraryservice.domain.USER_ROLE;
import com.mast.peen.libraryservice.domain.User;
import com.mast.peen.libraryservice.exceptions.ApplicationErrorCodes;
import com.mast.peen.libraryservice.exceptions.ServiceException;
import com.mast.peen.libraryservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public User createUser(String username, String password, USER_ROLE role) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new ServiceException(ApplicationErrorCodes.USERNAME_NOT_AVAILABLE);
    }
    User newUser = new User();
    newUser.setUsername(username);
    newUser.setPassword(password);
    newUser.setRole(role);
    return userRepository.save(newUser);
  }

  public User authenticateUser(String username, String password) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new ServiceException(ApplicationErrorCodes.USER_NOT_FOUND));
    if (!user.getPassword().equals(password)) {
      throw new ServiceException(ApplicationErrorCodes.INVALID_PASSWORD);
    }
    return user;
  }

  public User getUserById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new ServiceException(ApplicationErrorCodes.USER_NOT_FOUND));
  }

}
