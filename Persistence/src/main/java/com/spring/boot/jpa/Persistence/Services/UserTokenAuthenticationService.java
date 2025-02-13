//package com.spring.boot.jpa.Persistence.Services;
//
//import com.spring.boot.jpa.Persistence.security.JwtService;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//@Getter
//@Setter
//@Service
//@RequiredArgsConstructor
//public class UserTokenAuthenticationService {
//    private final AuthenticationManager authenticationManager;
//    private final CustomUserDetailsService customUserDetailsService;
//    private final JwtService jwtService;
//
//    public String verifyUser(String username){
//        UserDetails userPrincipal = customUserDetailsService.loadUserByUsername(username);
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(
//                    userPrincipal.getUsername(),
//                    userPrincipal.getPassword()
//                ));
//        if(authentication.isAuthenticated()){
//            return "";
//        }
//        return "";
//    }
//    private String generateToken(String username, int validHours){
//        return jwtService.generateToken(username, validHours);
//    }
//    public String verifyToken(String token){
//        return jwtService.verifyToken(token);
//    }
//}
