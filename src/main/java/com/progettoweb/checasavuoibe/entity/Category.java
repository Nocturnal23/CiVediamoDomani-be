package com.progettoweb.checasavuoibe.entity;

import com.progettoweb.checasavuoibe.commons.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Table(name = "categories")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity {
    @Id
    @SequenceGenerator(sequenceName = "categories_id_seq", allocationSize = 1, name = "categories_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_id_seq")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "category_name")
    @NonNull
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Advert> adverts;
}