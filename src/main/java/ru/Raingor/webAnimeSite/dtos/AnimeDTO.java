package ru.Raingor.webAnimeSite.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeDTO {
    @NotEmpty(message = "The name must not be empty")
    private String name;
    @NotEmpty(message = "The author must not be empty")
    private String author;
    private int countSeries;
    private String description;
    private Float rating;
}
