package com.example.news.news_letter_back.security;

import com.example.news.news_letter_back.entity.AdminUser;
import com.example.news.news_letter_back.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        AdminUser user = adminUserRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("사용자 없음"));

        return org.springframework.security.core.userdetails.User
            .builder()
            .username(user.getEmail())
            .password(user.getPasswordHash())
            .roles("ADMIN")
            .build();
    }
}