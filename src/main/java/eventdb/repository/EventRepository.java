package eventdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import eventdb.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
