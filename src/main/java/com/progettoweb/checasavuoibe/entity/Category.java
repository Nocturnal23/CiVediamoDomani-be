package com.progettoweb.checasavuoibe.entity;

import com.progettoweb.checasavuoibe.commons.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Table(name = "categorys")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity {
    @Id
    @SequenceGenerator(sequenceName = "category_id_seq", allocationSize = 1, name = "category_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY) //TODO: Controllare si collega a order_id.
    private List<Advert> adverts;
}