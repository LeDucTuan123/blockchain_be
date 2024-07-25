package com.nft.BanTranhNFT.service.impl;

import java.util.HashMap;

import com.nft.BanTranhNFT.model.JwtAuthenticationResponse;
import com.nft.BanTranhNFT.model.RefreshTokenRequest;
import com.nft.BanTranhNFT.model.SignInRequest;
// import com.nft.BanTranhNFT.model.SignUpRequest;
import com.nft.BanTranhNFT.model.Role;
import com.nft.BanTranhNFT.model.User;
import com.nft.BanTranhNFT.repository.UserRepository;
import com.nft.BanTranhNFT.service.AuthencationService;
import com.nft.BanTranhNFT.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthencationServiceImpl implements AuthencationService{
    
     private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    // public User signup(SignUpRequest signUpRequest) {

    //     String email = signUpRequest.getEmail();
    //     String phone = signUpRequest.getPhone();

    //     // Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu chưa
    //     if (userRepository.findByEmail(email).isPresent()) {
    //         // Nếu email đã tồn tại, bạn có thể xử lý theo mong muốn của bạn, ví dụ:
    //         throw new IllegalArgumentException("Email đã tồn tại");
    //     }

    //     // Kiểm tra xem số điện thoại đã tồn tại trong cơ sở dữ liệu hay chưa
    //     if (userRepository.findByPhone(phone).isPresent()) {
    //         throw new IllegalArgumentException("Số điện thoại đã tồn tại");
    //     }

    //     // Nếu email chưa tồn tại, tiến hành tạo người dùng mới
    //     User user = new User();

    //     user.setEmail(signUpRequest.getEmail());
    //     user.setPhone(signUpRequest.getPhone());
    //     user.setRole(Role.USER);
    //     user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

    //     return userRepository.save(user);
    // }
    public JwtAuthenticationResponse Signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                signInRequest.getPassword()));
                
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

    
}
