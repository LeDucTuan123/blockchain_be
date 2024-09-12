package com.nft.BanTranhNFT.repository;

import com.nft.BanTranhNFT.model.Painting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaintingRepository extends JpaRepository<Painting, Integer> {
    List<Painting> findByTitleContaining(String title);
}
