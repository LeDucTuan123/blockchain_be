package com.nft.BanTranhNFT.repository;

import com.nft.BanTranhNFT.model.Order;
import com.nft.BanTranhNFT.model.Painting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaintingRepository extends JpaRepository<Painting, Integer> {
    List<Painting> findByTitleContaining(String title);

    @Query("select o from Painting o where o.user.id = ?1")
	List<Painting> getPaintingForUser(Integer id);
}
