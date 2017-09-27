package com.aim.foodtaxi.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="T_BRAND")
@Getter
@Setter
@NoArgsConstructor
public class BrandEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="brand_seq_gen")
    @SequenceGenerator(name="brand_seq_gen", sequenceName = "brand_seq", allocationSize = 1, initialValue=50)
    @Column
    private Long id;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="LOGO_PATH")
    private String logoPath;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    private ClientEntity client;
    
    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="brand")
    private List<ShopEntity> shops;
}
