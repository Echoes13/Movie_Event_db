package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Event;
import wad.domain.GroupAccount;

//Tapahtumarepositori

public interface EventRepository extends JpaRepository<Event, Long> {
    public Event findByName(String name);
    public List<Event> findByGroup(GroupAccount group);
}
