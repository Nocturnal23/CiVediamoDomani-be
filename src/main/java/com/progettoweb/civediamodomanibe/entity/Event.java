package com.progettoweb.civediamodomanibe.entity;

import com.progettoweb.civediamodomanibe.core.templates.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name = "events")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event extends BaseEntity {
    @Id
    @SequenceGenerator(sequenceName = "events_id_seq", allocationSize = 1, name = "events_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_id_seq")
    @Column(name = "event_id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organiser_id")
    private UserAccount organiser;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "place")
    @NonNull
    private String place;

    @Column(name = "coordinates")
    @NonNull
    private String coordinates;

    @Column(name = "datetime")
    private LocalDate datetime;

    @Column(name = "description")
    @NonNull
    private String description;

    @Column(name = "price")
    @NonNull
    private Float price;

    @ManyToMany
    @JoinTable(name = "event_category", joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "event_attendees", joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<UserAccount> attendees;

    @ManyToMany
    @JoinTable(name = "user_favorites", joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<UserAccount> followers;
}