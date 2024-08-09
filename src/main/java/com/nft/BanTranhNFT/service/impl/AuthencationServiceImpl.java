package com.nft.BanTranhNFT.service.impl;

import java.util.HashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.nft.BanTranhNFT.model.JwtAuthenticationResponse;
import com.nft.BanTranhNFT.model.RefreshTokenRequest;
import com.nft.BanTranhNFT.model.SignInRequest;
// import com.nft.BanTranhNFT.model.SignUpRequest;
import com.nft.BanTranhNFT.model.Role;
import com.nft.BanTranhNFT.model.User;
import com.nft.BanTranhNFT.repository.UserRepository;
import com.nft.BanTranhNFT.service.AuthencationService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthencationServiceImpl implements AuthencationService{
    
     private final UserRepository userRepository;

    @Autowired
    UserRepository Repository;

    	@Override
	public User signin(JsonNode data) {
		// User account = Repository.findByEmail(data.findValue("email").asText());
		User account = Repository.findByEmail(data.findValue("email").asText());
		if(account == null) {
			return null;
		}
		if(account.getPassword().equals(data.findValue("password").asText())) {
			return account;
		}
		return null;
		
	}




    
}
