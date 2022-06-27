package com.progettoweb.checasavuoibe.entity;

import com.progettoweb.checasavuoibe.commons.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @SequenceGenerator(sequenceName = "users_id_seq", allocationSize = 1, name = "users_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "password_digest")
    @NonNull
    private String passwordDigest;

    @Column(name = "password_salt")
    @NonNull
    private String passwordSalt;

    @Column(name = "phone_num")
    @NonNull
    private String phoneNum;

    @Column(name = "birthday")
    @NonNull
    private LocalDate birthday;

    @Column(name = "fiscal_code")
    @NonNull
    private String fiscalCode;

    @Column(name = "document_id")
    @NonNull
    private String documentId;

    @Column(name = "city")
    @NonNull
    private String city;

    @Column(name = "country")
    @NonNull
    private String country;

    @Column(name = "app_role")
    @NonNull
    private Long appRole;

    @OneToMany(mappedBy = "advertiser", fetch = FetchType.LAZY)
    private List<Advert> postedAds;

    @ManyToMany
    @JoinTable(name = "saved_adverts", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "advert_id")})
    private List<Advert> savedAds;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Review> postedReviews;
}