package ansarbektassov.repository;

import ansarbektassov.model.Film;
import ansarbektassov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class InMemoryFilmStorage {

    private final HashMap<String, Film> films;

    @Autowired
    public InMemoryFilmStorage(HashMap<String, Film> films) {
        this.films = films;
    }

    public Film save(Film film) {
        return films.put(film.getFilmId(),film);
    }

    public List<Film> findAll() {
        return films.values().stream().toList();
    }

    public Film findById(String filmId) {
        return films.get(filmId);
    }
}
