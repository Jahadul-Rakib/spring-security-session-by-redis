package com.rakib.common;

import com.rakib.common.domain.UserInfo;
import com.rakib.common.domain.UserRole;
import com.rakib.common.domain.enums.RoleType;
import com.rakib.common.repository.UserInfoRepository;
import com.rakib.common.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

    private final UserInfoRepository userInfoRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public SpringSecurityApplication(UserInfoRepository userInfoRepository,
                                     UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Override
    public void run(String... args) {
        UserRole admin = userRoleRepository.save(UserRole.builder().roleName(RoleType.ADMIN).build());
        UserRole user = userRoleRepository.save(UserRole.builder().roleName(RoleType.USER).build());

        userInfoRepository.save(
                UserInfo.builder()
                        .userEmail("user@gmail.com")
                        .userPassword(passwordEncoder.encode("123456"))
                        .userRole(List.of(user))
                        .userIsActive(true)
                        .build());

        userInfoRepository.save(
                UserInfo.builder()
                        .userEmail("admin@gmail.com")
                        .userPassword(passwordEncoder.encode("123456"))
                        .userRole(List.of(admin))
                        .userIsActive(true)
                        .build());

        userInfoRepository.save(
                UserInfo.builder()
                        .userEmail("all@gmail.com")
                        .userPassword(passwordEncoder.encode("123456"))
                        .userRole(List.of(user, admin))
                        .userIsActive(true)
                        .build());
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        userInfoList.forEach(System.out::println);
    }
}
