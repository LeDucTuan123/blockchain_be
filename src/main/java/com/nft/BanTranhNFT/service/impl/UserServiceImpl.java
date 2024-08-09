package com.nft.BanTranhNFT.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nft.BanTranhNFT.model.Painting;
import com.nft.BanTranhNFT.model.User;
import com.nft.BanTranhNFT.repository.UserRepository;
import com.nft.BanTranhNFT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;





    @Override
    public User addUser(JsonNode user) {
        // if(user != null){
        //     return userRepository.save(user);
        // }
        // return null;
        System.out.println(user);
		ObjectMapper mapper = new ObjectMapper();
		User b = mapper.convertValue(user, User.class);
		userRepository.save(b);
		return null;
    }

    @Override
    public User updateUser(int id, User user) {
        if(user != null){
            // User user1 = userRepository.getById(id);
            User user1 = userRepository.findById(id).orElse(null);
            if(user1 != null){
                user1.setFirstname(user.getFirstname());
                user1.setPassword(user.getPassword());
                user1.setEmail(user.getEmail());
                user1.setPhone(user.getPhone());
                user1.setRole(user.getRole());
                return userRepository.save(user1);
            }
        }
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        if(id >=1){
            User user = userRepository.getById(id);
            if(user != null){
                userRepository.delete(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getOneUser(int id) {
        return userRepository.findById(id);
    }



//  public User convertToUser(User user) {
//         User users = new User();
//         users.setId(users.getId());
//         users.setUsername(users.getUsername());
//         users.setEmail(users.getEmail());
//         users.setAddress(users.getAddress());
//         users.setPhone(users.getPhone());
//         users.setRole(users.getRole());
//         users.setOrders(users.getOrders());
//         // user.setAuthorities(user.getAuthorities());
//         // user.setEnabled(user.isEnabled());
//         // user.setAccountNonExpired(user.isAccountNonExpired());
//         // user.setAccountNonLocked(user.isAccountNonLocked());
//         // user.setCredentialsNonExpired(user.isCredentialsNonExpired());

//         List<Painting> paintings = user.getPaintings().stream()
//                 .map(this::convertToPainting)
//                 .collect(Collectors.toList());
//         users.setPaintings(paintings);

//         return user;
//     }

//     public Painting convertToPainting(Painting painting) {
//         Painting paintings = new Painting();
//         paintings.setPaintingId(paintings.getPaintingId());
//         paintings.setTitle(paintings.getTitle());
//         paintings.setPaintingDescription(paintings.getPaintingDescription());
//         paintings.setPrice(paintings.getPrice());
//         paintings.setImageUrl(paintings.getImageUrl());
//         paintings.setCreatedAt(paintings.getCreatedAt());

//         return painting;
//     }

    
}
