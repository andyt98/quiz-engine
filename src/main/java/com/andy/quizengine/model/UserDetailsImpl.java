package com.andy.quizengine.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final QuizUser quizUser;
    private final List<GrantedAuthority> rolesAndAuthorities;

    public UserDetailsImpl(QuizUser quizUser) {
        this.quizUser = quizUser;
        this.rolesAndAuthorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolesAndAuthorities;
    }

    @Override
    public String getPassword() {
        return quizUser.getPassword();
    }

    @Override
    public String getUsername() {
        return quizUser.getEmail();
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
        return true;
    }
}
