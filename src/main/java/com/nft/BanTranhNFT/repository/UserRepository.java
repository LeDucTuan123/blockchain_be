package com.nft.BanTranhNFT.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.nft.BanTranhNFT.model.Role;
import com.nft.BanTranhNFT.model.User;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String string);

    Optional<User> findByPhone(String phone);

    User findByRole(Role role);
}
