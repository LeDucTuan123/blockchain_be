package com.nft.BanTranhNFT.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.nft.BanTranhNFT.model.JwtAuthenticationResponse;
import com.nft.BanTranhNFT.model.RefreshTokenRequest;
import com.nft.BanTranhNFT.model.SignInRequest;
// import com.nft.BanTranhNFT.model.SignUpRequest;
import com.nft.BanTranhNFT.model.User;
import com.nft.BanTranhNFT.service.AuthencationService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthencationService authenticationService;
    @Autowired
	AuthencationService service;
	@PostMapping("signup")
	public User signUp(@RequestBody User acc) {
		return service.signUp(acc);
	}

 
    @PostMapping("login")
	public User signin(@RequestBody JsonNode data) {
		return service.signin(data);
	}


    @PatchMapping("/wallet/{id}")
	public User updateWallet(@RequestBody JsonNode wallet, @PathVariable("id") Integer id) {
		return service.updateWallet(wallet, id);
	}

}
