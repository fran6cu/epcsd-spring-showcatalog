package edu.uoc.epcsd.showcatalog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private float price;

    @Column(name = "duration")
    private int duration;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "onsaledate")
    private Date onSaleDate;

    @Column(name = "status")
    private String status;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @CollectionTable(name="performance", joinColumns = @JoinColumn(name = "showid"))
    private List<Performance> performances;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE})
    @JoinTable(
            name = "show_categories",
            joinColumns = @JoinColumn(name = "id_show"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<Category> categories;

}
