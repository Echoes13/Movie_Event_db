package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    public Event findByName(String name);
}
