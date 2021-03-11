package com.rakib.common.security_service;

import com.rakib.common.config.AppConstant;
import com.rakib.common.domain.UserInfo;
import com.rakib.common.domain.UserRole;
import com.rakib.common.domain.vm.LogInVM;
import com.rakib.common.domain.vm.ResponseVM;
import com.rakib.common.exception.classes.InvalidKeyException;
import com.rakib.common.exception.classes.NotFoundException;
import com.rakib.common.repository.UserInfoRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JsonWebTokenUtilities {
    private static UserInfoRepository userRepository;
    private static PasswordEncoder passwordEncoder;

    public JsonWebTokenUtilities(UserInfoRepository userRepository, PasswordEncoder passwordEncoder) {
        JsonWebTokenUtilities.userRepository = userRepository;
        JsonWebTokenUtilities.passwordEncoder = passwordEncoder;
    }

    public static String jwtTokenProvider(String email) throws NotFoundException {
        UserInfo users = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException("User Not Found."));
        Collection<? extends GrantedAuthority> collection = users.getAuthorities();
        List<String> authorityList = collection.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
                .setSubject("Token")
                .setIssuer(users.getUserEmail())
                .claim("authorities", authorityList.stream().map(String::valueOf).collect(Collectors.joining(",")))
                .setIssuedAt(new java.util.Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(Keys.hmacShaKeyFor(AppConstant.SECURITY_SECRATE.getBytes()))
                .compact();
    }

    public static ResponseVM getLogInAndAccessCredential(LogInVM logInVM) throws NotFoundException, InvalidKeyException {
        UserInfo users = userRepository.findByUserEmail(logInVM.getUserEmail())
                .orElseThrow(() -> new NotFoundException("User Not Found."));
        boolean checkPassword = passwordEncoder.matches(logInVM.getPassword(), users.getPassword());
        if (!checkPassword) {
            throw new InvalidKeyException("Credential is incorrect");
        }
        return ResponseVM.builder()
                .token(jwtTokenProvider(logInVM.getUserEmail()))
                .userEmail(users.getUserEmail())
                .userRole(users.getUserRole().stream().map(UserRole::getAuthority).collect(Collectors.toList()))
                .build();
    }
}