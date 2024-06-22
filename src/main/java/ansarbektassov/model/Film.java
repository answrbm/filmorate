package ansarbektassov.model;


import ansarbektassov.dto.FilmRequestDTO;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//целочисленный идентификатор — id;
//название — name;
//описание — description;
//дата релиза — releaseDate;
//продолжительность фильма — duration.
public class Film {

    private String filmId;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Integer duration;
    private Set<String> likes;

    public Film() {
        this.filmId = UUID.randomUUID().toString();
        this.likes = new HashSet<>();
    }

    public Film(String name, String description, LocalDate releaseDate, Integer duration) {
        this.filmId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.likes = new HashSet<>();
    }

    public Film(FilmRequestDTO filmRequestDTO) {
        this.filmId = UUID.randomUUID().toString();
        this.name = filmRequestDTO.getName();
        this.description = filmRequestDTO.getDescription();
        this.releaseDate = filmRequestDTO.getReleaseDate();
        this.duration = filmRequestDTO.getDuration();
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<String> getLikes() {
        return likes;
    }

    public void setLikes(Set<String> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + filmId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", duration=" + duration +
                '}';
    }
}
