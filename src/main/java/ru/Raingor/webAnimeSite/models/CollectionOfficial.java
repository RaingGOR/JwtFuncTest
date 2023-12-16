package ru.Raingor.webAnimeSite.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * Official collections
 * * name collection
 * * author's anime / studio
 * * number of seasons
 * * description
 * * List Anime
 */
@Entity
@Table(name = "collection")
@Data
@NoArgsConstructor
public class CollectionOfficial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "number_of_season")
    private int numberOfSeasons;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "collectionName")
    private List<Anime> animeList;

}
