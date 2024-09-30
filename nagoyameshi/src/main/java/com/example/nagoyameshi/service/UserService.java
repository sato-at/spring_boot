package com.example.nagoyameshi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.SignupForm;
import com.example.nagoyameshi.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public User createUser(SignupForm signupForm) {
    User user = new User();

    user.setName(signupForm.getName());
    user.setEmail(signupForm.getEmail());
    user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
    user.setRole("ROLE_GENERAL");
    user.setEnabled(false);

    return userRepository.save(user);
  }

  // メールアドレスが登録済みかどうかをチェックする
  public boolean isEmailRegistered(String email) {
    User user = userRepository.findByEmail(email);
    return user != null;
  }

  // パスワードとパスワード（確認用）の入力値が一致するかどうかをチェックする
  public boolean isSamePassword(String password, String passwordConfirmation) {
    return password.equals(passwordConfirmation);
  }

  // ユーザーを有効にする
  @Transactional
  public void enableUser(User user) {
    user.setEnabled(true);
    userRepository.save(user);
  }
}
