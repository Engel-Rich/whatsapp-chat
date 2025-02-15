package end.dev.whatsap_clone.services;

import end.dev.whatsap_clone.entities.Country;
import end.dev.whatsap_clone.entities.Authentication.User;
import end.dev.whatsap_clone.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserServices implements UserDetailsService {
    private  final UserRepository appUserRepository;
    private final CountryService countryService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = this.appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email ou mot de passe invalide"));

        return new org.springframework.security.core.userdetails.User
                    ( user.getEmail(), user.getPassword(),
                user.getRoles().stream().map(rule-> new SimpleGrantedAuthority(rule.getName())).
                        collect(Collectors.toList())
        );
    }

    public User savUserUser (User user){
        Country countyManaged = this.countryService.findById(user.getCountry().getId());
        user.setCountry(countyManaged);
        final List<User>  userList= this.appUserRepository.findByEmailOrUsername(user.getEmail(), user.getFullName());
        if(!userList.isEmpty()){
            throw  new RuntimeException("Un utilisateur existe deja avec cet email ou ce mot de nom d'utilisateur");
        }
        return  this.appUserRepository.save(user);
    }


   public  Optional<User> findByUsername(String username){
        return  this.appUserRepository.findByUsername(username);
    }

    public  Optional<User> findByEmail(String email){
        return  this.appUserRepository.findByEmail(email);
    }

    public  boolean updatePassword(String password, String username){
            final User user= findByUsername(username).orElseThrow(()-> new RuntimeException("No user found with this email address"));
            user.setPassword(password);
            this.appUserRepository.save(user);
            return  true;

    }

    public  boolean updatePassword(User user){
        this.appUserRepository.save(user);
        return  true;
    }
}
