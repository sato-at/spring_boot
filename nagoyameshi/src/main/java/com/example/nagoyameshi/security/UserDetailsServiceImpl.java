package com.example.nagoyameshi.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    try {
      User user = userRepository.findByEmail(email); // フォームから送信されたメールアドレスに一致するユーザーを取得する
      String userRoleName = user.getRole(); // 取得したユーザ情報のRoleを取得
      Collection<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority(userRoleName)); // そのユーザーのロールを取得する
      return new UserDetailsImpl(user, authorities); // UserDetailsImplクラスのコンストラクタに渡し、インスタンスを生成
    } catch (Exception e) {
      throw new UsernameNotFoundException("ユーザーが見つかりませんでした。");
    }
  }
}
