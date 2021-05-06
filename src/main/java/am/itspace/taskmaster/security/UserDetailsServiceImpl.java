package am.itspace.taskmaster.security;

import am.itspace.taskmaster.entities.User;
import am.itspace.taskmaster.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new CurrentUser(user);
    }
}
