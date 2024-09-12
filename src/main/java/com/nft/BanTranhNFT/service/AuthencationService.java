package com.nft.BanTranhNFT.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.nft.BanTranhNFT.model.JwtAuthenticationResponse;
import com.nft.BanTranhNFT.model.RefreshTokenRequest;
import com.nft.BanTranhNFT.model.SignInRequest;
import com.nft.BanTranhNFT.model.User;

public interface AuthencationService {
    

    public User signin(JsonNode data);

    public User signUp(User acc);

    public User updateWallet(JsonNode wallet, Integer id);

    // JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    
}
