package com.nft.BanTranhNFT.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Paintings")
public class Painting implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "painting_id")
    private int paintingId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "painting_description")
    private String paintingDescription;

    @Column(name = "price", nullable = false)
    private double price;

    @JsonManagedReference(value = "orderdetail-painting")
    @OneToMany(mappedBy = "painting")
    List<OrderItem> orderdetails;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @JsonIgnoreProperties(value = "paintings")
    // @JsonManagedReference("artist-id")
    private User user;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "status")
    private Boolean status;

    

    @Column(name = "created_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
