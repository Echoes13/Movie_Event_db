package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Movie;

//Elokuvarepositori

public interface MovieRepository extends JpaRepository<Movie, Long> {
    public Movie findByName(String name);
}