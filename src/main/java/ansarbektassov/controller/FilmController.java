package ansarbektassov.controller;

import ansarbektassov.dto.FilmRequestDTO;
import ansarbektassov.exception.FilmNotCreatedException;
import ansarbektassov.model.Film;
import ansarbektassov.utils.ErrorBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;

//добавление фильма;
//обновление фильма;
//получение всех фильмов.
@RestController
@RequestMapping("/films")
public class FilmController {

    private final HashMap<String, Film> films;
    private final Logger log;

    public FilmController() {
        this.films = new HashMap<>();
        this.log = LoggerFactory.getLogger(FilmController.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Film createFilm(@RequestBody @Validated FilmRequestDTO filmRequestDTO, BindingResult bindingResult) {
        log.debug("Film creation");
        if(bindingResult.hasErrors()) {
            try {
                throw new FilmNotCreatedException(ErrorBuilder.buildError(bindingResult.getFieldErrors()));
            } catch (FilmNotCreatedException e) {
                log.error("Film create error",e);
                throw new FilmNotCreatedException(ErrorBuilder.buildError(bindingResult.getFieldErrors()));
            }
        }
        Film film = new Film(filmRequestDTO);
        films.put(film.getFilmId(),film);
        log.debug("Film created");
        return film;
    }

    @PutMapping("/{filmId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Film updateFilm(@PathVariable("filmId") String filmId, @RequestBody @Validated FilmRequestDTO filmRequestDTO, BindingResult bindingResult) {
        log.debug("Film updating");
        if(bindingResult.hasErrors()) {
            try {
                throw new FilmNotCreatedException(ErrorBuilder.buildError(bindingResult.getFieldErrors()));
            } catch (FilmNotCreatedException e) {
                log.error("Film create error",e);
                throw new FilmNotCreatedException(ErrorBuilder.buildError(bindingResult.getFieldErrors()));
            }
        }
        Film film = new Film(filmRequestDTO);
        film.setFilmId(filmId);
        films.put(filmId,film);
        log.debug("Film updated");
        return film;
    }

    @GetMapping
    public Collection<Film> getAllFilms() {
        return films.values();
    }

}
