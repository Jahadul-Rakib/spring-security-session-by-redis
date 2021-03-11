package com.rakib.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserInfo implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userEmail;
    private String userPassword;
    private boolean userIsActive;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRole> userRole;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        for (UserRole value : this.getUserRole()) {
            grantedAuthority.add(new SimpleGrantedAuthority(value.getRoleName().toString()));
        }
        return grantedAuthority;
    }

    @Override
    public String getPassword() {
        return this.getUserPassword();
    }

    @Override
    public String getUsername() {
        return this.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isUserIsActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isUserIsActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isUserIsActive();
    }

    @Override
    public boolean isEnabled() {
        return this.isUserIsActive();
    }
}
