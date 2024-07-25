package com.nft.BanTranhNFT.service;

import com.nft.BanTranhNFT.model.JwtAuthenticationResponse;
import com.nft.BanTranhNFT.model.RefreshTokenRequest;
import com.nft.BanTranhNFT.model.SignInRequest;
import com.nft.BanTranhNFT.model.User;

public interface AuthencationService {
    

    JwtAuthenticationResponse Signin(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    
}
