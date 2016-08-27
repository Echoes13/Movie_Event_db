package eventdb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import eventdb.domain.MovieChoice;
import eventdb.domain.Event;

public interface MovieChoiceRepository extends JpaRepository<MovieChoice, Long> {
    public List<MovieChoice> findByEvent(Event event);
}

