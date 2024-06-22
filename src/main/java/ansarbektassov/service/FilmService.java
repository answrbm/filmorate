package ansarbektassov.service;

import ansarbektassov.dto.FilmRequestDTO;
import ansarbektassov.exception.FilmNotCreatedException;
import ansarbektassov.exception.FilmNotFoundException;
import ansarbektassov.exception.UserNotFoundException;
import ansarbektassov.model.Film;
import ansarbektassov.model.User;
import ansarbektassov.repository.InMemoryFilmStorage;
import ansarbektassov.utils.ErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private final InMemoryFilmStorage filmStorage;
    private final UserService userService;

    @Autowired
    public FilmService(InMemoryFilmStorage filmStorage, UserService userService) {
        this.filmStorage = filmStorage;
        this.userService = userService;
    }

    public Film createFilm(Film film) {
        return filmStorage.save(film);
    }

    public Film updateFilm(String filmId, Film film) {
        film.setFilmId(filmId);
        return filmStorage.save(film);
    }

    public Collection<Film> getAllFilms() {
        return filmStorage.findAll();
    }

    public Film getFilmById(String filmId) {
        Film foundFilm = filmStorage.findById(filmId);
        if(foundFilm == null) {
            throw new FilmNotFoundException("Film with such id not found!");
        }
        return foundFilm;
    }

    public User addLike(String filmId, String userId) {
        Film film = getFilmById(filmId);
        User user = userService.getUserById(userId);

        film.getLikes().add(user.getUserId());
        return user;
    }

    public User removeLike(String filmId, String userId) {
        Film film = getFilmById(filmId);
        User user = userService.getUserById(userId);

        film.getLikes().remove(user.getUserId());
        return user;
    }

    public List<Film> getMostPopularFilms(int count) {
        List<Film> films = filmStorage.findAll();
        films.sort((o1, o2) -> o1.getLikes().size() - o2.getLikes().size());
        return films.stream().limit(count).collect(Collectors.toList());
    }
}
