package com.progettoweb.checasavuoibe.entity;

import com.progettoweb.checasavuoibe.commons.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "reviews")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review extends BaseEntity {
    @Id
    @SequenceGenerator(sequenceName = "reviews_id_seq", allocationSize = 1, name = "reviews_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_id_seq")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    private Advert recipient;

    @Column(name = "rating")
    @NonNull
    private Long rating;

    @Column(name = "description")
    private String description;
}