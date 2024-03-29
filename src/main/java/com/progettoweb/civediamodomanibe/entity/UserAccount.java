package com.progettoweb.civediamodomanibe.entity;

import com.progettoweb.civediamodomanibe.core.templates.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount extends BaseEntity {
    @Id
    @SequenceGenerator(sequenceName = "users_id_seq", allocationSize = 1, name = "users_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @Column(name = "user_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @Column(name = "app_role")
    @NonNull
    private Long appRole;

    @OneToMany(mappedBy = "organiser", fetch = FetchType.LAZY)
    private List<Event> organisedEvents;

    @ManyToMany
    @JoinTable(name = "event_attendees", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private List<Event> attending;

    @ManyToMany
    @JoinTable(name = "user_favorites", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private List<Event> favorites;

    @Column(name = "search_location")
    private String searchLocation;

    @Column(name = "search_latitude")
    private String searchLatitude;

    @Column(name = "search_longitude")
    private String searchLongitude;

}