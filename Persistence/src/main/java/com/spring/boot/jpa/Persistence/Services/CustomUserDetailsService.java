package com.spring.boot.jpa.Persistence.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var values = getByUsername(username);
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }
            @Override
            public String getPassword() {
                return (String) values[1];
            }
            @Override
            public String getUsername() {
                return (String) values[0];
            }
        };
        return userDetails;
    }

    private Object[] getByUsername(String username){
        var query = entityManager.createNativeQuery("SELECT username, password FROM users WHERE username = ?");
            query.setParameter(1, username);
                List result = query.getResultList();
                    if(result.isEmpty())
                        throw new UsernameNotFoundException("User Not Found");
           return (Object[])(result.toArray()[0]);
    }
}