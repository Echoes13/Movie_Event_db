package wad.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Event extends AbstractPersistable<Long> {

    @ManyToOne
    private GroupAccount user;
    
    private String name;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @OneToMany(fetch = FetchType.EAGER)
    private List<MovieChoice> movies;
    
    private Integer length;
    
    public Event() {
        this.length = 0;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void addMovie(MovieChoice movie) {
        if (this.movies == null) {
            this.movies = new ArrayList<MovieChoice>();
        }
        addLength(movie);
        this.movies.add(movie);
    }
    
    public void removeMovie(MovieChoice movie) {
        if (!this.movies.contains(movie)) {
            return;            
        }
        this.movies.remove(movie);
    }
 
    public List<MovieChoice> getMovies() {
        return movies;
    }
 
    public void setMovies(List<MovieChoice> movies) {
        length = 0;
        for(MovieChoice movie : movies) {
            addLength(movie);
        }
        this.movies = movies;
    }
    
    public Integer getLength() {
        return length;
    }
    
    public void setLength(Integer length) {
        this.length = length;
    }
    
    public void addLength(MovieChoice movie) {
        length += movie.getMovie().getLengthInMinutes();
    }
}