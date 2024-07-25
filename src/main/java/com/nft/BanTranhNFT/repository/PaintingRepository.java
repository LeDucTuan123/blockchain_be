package com.nft.BanTranhNFT.repository;

import com.nft.BanTranhNFT.model.Painting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaintingRepository extends JpaRepository<Painting, Integer> {
    
}
