package wad.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.AbstractPersistable;

//Luokka tapahtumille. Tapahtumaan liittyy yksi käyttäjäryhmä ja nollasta
//useampaan elokuvavalintaa.

@Entity
public class Event extends AbstractPersistable<Long> {

    @Column(unique=true)
    private String name;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @OneToMany(mappedBy="event", fetch = FetchType.EAGER)
    private List<MovieChoice> movies;
    
    private Integer length;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private GroupAccount group;
    
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
        this.length -= movie.getMovie().getLengthInMinutes();
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
    
    public GroupAccount getGroup() {
        return group;
    }
    
    public void setGroup(GroupAccount group) {
        this.group = group;
    }
}