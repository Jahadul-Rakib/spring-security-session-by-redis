package com.rakib.common.security_service;

import com.rakib.common.repository.UserInfoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserInfoRepository repository;

    public UserDetailsServiceImpl(UserInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return repository.findByUserEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found By User Email " + userName));
    }
}
