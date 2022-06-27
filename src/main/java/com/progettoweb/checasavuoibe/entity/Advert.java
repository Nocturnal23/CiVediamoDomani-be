package com.progettoweb.checasavuoibe.entity;

import com.progettoweb.checasavuoibe.commons.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name = "adverts")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Advert extends BaseEntity {
    @Id
    @SequenceGenerator(sequenceName = "adverts_id_seq", allocationSize = 1, name = "adverts_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adverts_id_seq")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertiser_id")
    private User advertiser;

    @Column(name = "address")
    @NonNull
    private String address;

    @Column(name = "city")
    @NonNull
    private String city;

    @Column(name = "country")
    @NonNull
    private String country;

    @Column(name = "construction_year")
    private LocalDate constructionYear;

    @Column(name = "place_condition")
    private String placeCondition;

    @Column(name = "floor")
    private int floor;

    @Column(name = "square_meters")
    @NonNull
    private Double squareMeters;

    @Column(name = "no_locals")
    private int noLocals;

    @Column(name = "no_bathrooms")
    private int noBathrooms;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "description")
    @NonNull
    private String description;

    @Column(name = "price")
    @NonNull
    private Double price;

    @Column(name = "discount_price")
    private Double discountPrice;

    @Column(name = "adv_type")
    @NonNull
    private Long advType;

    @Column(name = "seller_type")
    @NonNull
    private Long sellerType;

    @Column(name = "parking")
    private Long parking;

    @Column(name = "media")
    private String media;

    @OneToMany(mappedBy = "recipient", fetch = FetchType.LAZY)
    private List<Review> review;

    @Column(name = "heating_type")
    private Long heatingType;

    @Column(name = "energeticClass")
    @NonNull
    private Long energeticClass;

    @Column(name = "details")
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}