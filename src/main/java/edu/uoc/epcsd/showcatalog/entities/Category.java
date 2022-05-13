package edu.uoc.epcsd.showcatalog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString(exclude = "shows")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "categories")
    private Set<Show> shows = new HashSet<>();

    public Category(Category category) {
        this.name = category.getName();
        this.description = category.getDescription();
    }

    public void addShow(Show show) {
        this.shows.add(show);
        show.getCategories().add(this);
    }

    public void removeShow(long showId) {
        Show show = this.shows.stream().filter(sh -> sh.getId() == showId).findFirst().orElse(null);
        if (show != null) this.shows.remove(show);
        show.getCategories().remove(this);
    }


}
