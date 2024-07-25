package com.nft.BanTranhNFT.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.nft.BanTranhNFT.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
public interface UserService {
    //Ham them User
    public User addUser(JsonNode user);

    //Ham chinh sua thong tin User
    public User updateUser(int id, User user);

    //Ham xoa User
    public boolean deleteUser(int id);

    //Ham lay ra danh sach User
    public List<User> getAllUser();

    //Ham lay ra 1 User
    public Optional<User> getOneUser(int id);

     UserDetailsService userDetailsService();

    //  public List<User> convertToUser();
}
