package com.nft.BanTranhNFT.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.nft.BanTranhNFT.model.Painting;
import com.nft.BanTranhNFT.repository.PaintingRepository;
import com.nft.BanTranhNFT.service.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("painting")
public class PaintingController {
    @Autowired
    PaintingService paintingService;
    @Autowired
    private PaintingRepository PaintingRepository;

    @PostMapping("/add")
    public void addPainting(@RequestBody JsonNode painting){
         paintingService.addPainting(painting);
    }

    @PutMapping("/update/{id}")
    public Painting updatePainting(@PathVariable("id") int id, @RequestBody Painting painting){
        return paintingService.updatePainting(id, painting);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deletePainting(@PathVariable("id") int id){
        return paintingService.deletePainting(id);
    }

    @GetMapping("/list")
    public List<Painting> getListPainting(){
        return paintingService.getAllPainting();
    }

    @GetMapping("{id}")
    public Painting getOnePainting(@PathVariable("id") Integer id){
        return paintingService.getOnePainting(id);
    }

    @GetMapping("/search")
	public List<Painting> searchByName(@RequestParam String q) {
		return PaintingRepository.findByTitleContaining(q);
	}
}
