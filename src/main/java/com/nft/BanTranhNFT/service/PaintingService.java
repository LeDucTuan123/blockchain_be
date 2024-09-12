package com.nft.BanTranhNFT.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.nft.BanTranhNFT.model.Painting;

import java.util.List;
import java.util.Optional;

public interface PaintingService {

    public Painting addPainting(JsonNode painting);

    public Painting updatePainting(int id, Painting painting);

    public boolean deletePainting(int id);

    public List<Painting> getAllPainting();

    public Painting getOnePainting(Integer id);


}
