package eventdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import eventdb.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}