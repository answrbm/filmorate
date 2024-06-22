package ansarbektassov.controller;

import ansarbektassov.dto.FilmRequestDTO;
import ansarbektassov.exception.FilmNotCreatedException;
import ansarbektassov.model.Film;
import ansarbektassov.model.User;
import ansarbektassov.service.FilmService;
import ansarbektassov.utils.ErrorBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

//добавление фильма;
//обновление фильма;
//получение всех фильмов.
@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;
    private final Logger log;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
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
        log.debug("Film created");
        return filmService.createFilm(film);
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
        log.debug("Film updated");
        return filmService.updateFilm(filmId,film);
    }

    @GetMapping
    public Collection<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @PutMapping("/films/{filmId}/like/{userId}")
    public User likeFilm(@PathVariable("filmId") String filmId, @PathVariable("userId") String userId) {
        return filmService.addLike(filmId,userId);
    }

    @DeleteMapping("/films/{filmId}/like/{userId}")
    public User removeLike(@PathVariable("filmId") String filmId, @PathVariable("userId") String userId) {
        return filmService.removeLike(filmId,userId);
    }

    @GetMapping("/films/popular")
    public List<Film> getMostPopularFilms(@RequestParam("count") int count) {
        return filmService.getMostPopularFilms(count);
    }

}
