package com.example.user.AuthService;

import com.example.account.entity.Account;
import com.example.jwt.JwtService;
import com.example.user.AuthController.dto.AuthenticationResponse;
import com.example.user.AuthController.dto.UserDTO;
import com.example.user.entity.Role;
import com.example.user.entity.User;
import com.example.user.mapper.UserDTOMapper;
import com.example.user.repository.RoleRepository;
import com.example.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationProvider authenticationProvider, JwtService jwtService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationProvider = authenticationProvider;
        this.jwtService = jwtService;
        this.roleRepository = roleRepository;
    }

    public UserDTO register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(getirDefaultTanimlanacakRoller());
        user.setAccount(userIcinAccountOlustur(user));
        userRepository.save(user);
        return UserDTOMapper.INSTANCE.toUserDTO(user);
    }

    private Account userIcinAccountOlustur(User user) {
        Account account = new Account();
        account.setName(user.getUsername());
        account.setBudget(new BigDecimal(0));

        account.setUser(user);
        return account;
    }

    private Set<Role> getirDefaultTanimlanacakRoller(){
        var role = roleRepository.findByName("ROLE_USER");
        return Set.of(role.get());
    }

    public AuthenticationResponse authenticate(User user) {
        //girişte kontrolü authenticationProvider yapar.
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            //username ve passworda göre girilen requesti bir authenticationTokena çeviririz.
            authenticationProvider.authenticate(authenticationToken); //Sonra bunu authentication providera veririz. eğer authenticate olamazsa hata fırlatr

            Optional<User> optionalUserVt = userRepository.findByUsername(user.getUsername()); //kullanıcı adına göre şifre useri doner. bu kulanıcıya göre token üretiriz ve döneriz
            String token = jwtService.generateToken(optionalUserVt.get());
            return new AuthenticationResponse(token);
        }
        catch (Exception e) {
            System.out.println("Kullanıcı adı ya da şifre hatalı" + e.getMessage());
        }
        return null;

    }
}
