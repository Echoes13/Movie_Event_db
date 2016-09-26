package wad.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wad.domain.GroupAccount;
import wad.repository.GroupRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GroupAccount account = groupRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such group: " + username);
        }
        
        switch (username) {
            case "Admin":
                return new org.springframework.security.core.userdetails.User(
                        account.getUsername(),
                        account.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
            default:
                return new org.springframework.security.core.userdetails.User(
                        account.getUsername(),
                        account.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        Arrays.asList(new SimpleGrantedAuthority("USER")));
        }
    }
}
