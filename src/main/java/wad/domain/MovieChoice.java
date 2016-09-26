package wad.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class MovieChoice extends AbstractPersistable<Long> {

    private String chosenBy;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Event event;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Movie movie;

    
    
    public String getChosenBy() {
        return chosenBy;
    }

    public void setChosenBy(String name) {
        this.chosenBy = name;
    }
    
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}