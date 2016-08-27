package eventdb.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Event extends AbstractPersistable<Long> {

    @ManyToOne
    private GroupAccount user;
    
    @NotBlank
    private String name;
    
    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @OneToMany(fetch = FetchType.EAGER)
    private List<MovieChoice> movies;
    
    private Integer length;
    
    
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
 
        this.movies.add(movie);
    }
 
    public List<MovieChoice> getMovies() {
        return movies;
    }
 
    public void setMovies(List<MovieChoice> movies) {
        this.movies = movies;
    }
    
    public Integer getLength() {
        return length;
    }
    
    public void setLength(Integer length) {
        this.length = length;
    }
}