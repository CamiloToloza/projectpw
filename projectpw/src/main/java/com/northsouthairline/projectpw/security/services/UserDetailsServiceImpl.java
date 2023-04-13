package com.northsouthairline.projectpw.security.services;

import com.northsouthairline.projectpw.entities.User;
import com.northsouthairline.projectpw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl extends UserDetailsImpl {
    @Autowired
    UserRepository userRepository;

    public UserDetailsServiceImpl(Long id, String username, String email, String password) {
        super(id, username, email, password);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));
        return UserDetailsImpl.build(user);
    }
}
