package ru.Raingor.webAnimeSite.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Anime page
 * * name anime
 * * author's anime / studio
 * * category
 * * count series
 * * description
 * * anime rating
 * * collection name
 * * commentaries
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "Anime")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;
    //need add category
    @Column(name = "count_series")
    private int countSeries;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private Float rating;

    @ManyToOne
    @JoinColumn(name = "collection_id", referencedColumnName = "id")
    private CollectionOfficial collectionName;
    //need add List with comms (Soon)

    public Anime(String name, String author, int countSeries, String description, float rating) {
        this.name = name;
        this.author = author;
        this.countSeries = countSeries;
        this.description = description;
        this.rating = rating;
    }
}
