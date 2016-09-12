package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.MovieChoice;
import wad.domain.Event;
import wad.domain.Movie;

public interface MovieChoiceRepository extends JpaRepository<MovieChoice, Long> {
    public List<MovieChoice> findByEvent(Event event);
    public List<MovieChoice> findByMovie(Movie movie);
}

