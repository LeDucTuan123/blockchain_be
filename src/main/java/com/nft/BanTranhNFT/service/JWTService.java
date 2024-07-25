package com.nft.BanTranhNFT.service;

import org.springframework.security.core.userdetails.UserDetails;

// import com.nft.BanTranhNFT.model.User;

import java.util.Map;

public interface JWTService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
