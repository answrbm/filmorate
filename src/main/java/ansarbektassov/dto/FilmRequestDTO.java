package ansarbektassov.dto;

import ansarbektassov.validation.Date;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class FilmRequestDTO {

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 1, message = "Name must be at least 1 character")
    private String name;
    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    @Size(max = 200, message = "Description must be maximum 200 characters")
    private String description;
    @NotNull(message = "Release date cannot be null")
    @Date
    private LocalDate releaseDate;
    @Positive(message = "Duration must be positive")
    @Min(value = 60, message = "Duration must be minimum: 60 minutes")
    private Integer duration;

    public FilmRequestDTO() {
    }

    public FilmRequestDTO(String name, String description, LocalDate releaseDate, Integer duration) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
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

}
