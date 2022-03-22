package com.geezylucas.oauth2auth.service.principal;

import com.geezylucas.oauth2auth.domain.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Boolean enabled;

    private Collection<SimpleGrantedAuthority> authorities;

    public UserPrincipal(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        setEnabled(user.getStatus() == 1);
        if (user.getRoles() != null) {
            authorities = new ArrayList<>();
            user.getRoles().forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
