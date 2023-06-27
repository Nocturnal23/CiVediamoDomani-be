package com.progettoweb.civediamodomanibe.entity;

import com.progettoweb.civediamodomanibe.core.templates.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import jakarta.persistence.*;

@Table(name = "categories")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity {
    @Id
    @SequenceGenerator(sequenceName = "categories_id_seq", allocationSize = 1, name = "categories_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_id_seq")
    @Column(name = "category_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category father;
}