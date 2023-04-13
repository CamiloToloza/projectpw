package com.northsouthairline.projectpw.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.northsouthairline.projectpw.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.Collection;
@Setter
@Getter
@AllArgsConstructor
public abstract class UserDetailsImpl implements  UserDetails {

    private Long id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;

    protected static UserDetails build(User user) {
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Transactional
    public abstract UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
}
